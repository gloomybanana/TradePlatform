package org.gloomybanana.trade_platform.handler;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.gloomybanana.trade_platform.command.SellCommand;

@Mod.EventBusSubscriber
public class CommandEventHander {
    @SubscribeEvent
    public static void onServerStarting(RegisterCommandsEvent event){

        Boolean onlyOperatorCanUse = Config.ONLY_OP_CAN_USE.get();
        Integer permissionLevel = onlyOperatorCanUse?2:0;

        //命令节点"dplm"
        LiteralArgumentBuilder<CommandSource> trade_platform = Commands.literal("trade_platform");
        trade_platform.requires((commandSource)-> commandSource.hasPermissionLevel(permissionLevel));
        //命令节点"block_drop"
        LiteralArgumentBuilder<CommandSource> sell = Commands.literal("sell");
        sell.requires((commandSource)-> commandSource.hasPermissionLevel(permissionLevel));
        sell.executes(SellCommand.instance);
        trade_platform.then(sell);

        //注册命令
        CommandDispatcher<CommandSource> commandDispatcher = event.getDispatcher();
        commandDispatcher.register(trade_platform);
    }
}
