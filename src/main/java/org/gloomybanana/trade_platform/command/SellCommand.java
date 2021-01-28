package org.gloomybanana.trade_platform.command;

import com.alibaba.fastjson.JSONObject;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkHooks;
import org.gloomybanana.trade_platform.containerprovider.SellContainerProvider;
import org.gloomybanana.trade_platform.file.FileManager;

import java.nio.file.Path;

public class SellCommand implements Command<CommandSource> {
    public static SellCommand instance = new SellCommand();

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity serverPlayer = context.getSource().asPlayer();
        String playerName = serverPlayer.getName().getString();
        Path loottablePath = FileManager.getWorldFolder(serverPlayer.getServerWorld()).toPath().resolve("datapacks").resolve("add_by_"+playerName).resolve("data").resolve("minecraft").resolve("loot_table");
        JSONObject jsonPacket = new JSONObject(true);
        jsonPacket.put("player_name",playerName);
        jsonPacket.put("loottablePath",loottablePath);

        NetworkHooks.openGui(serverPlayer,new SellContainerProvider(), (PacketBuffer packetBuffer) -> {
            packetBuffer.writeString(jsonPacket.toJSONString());
        });

        return 0;
    }
}
