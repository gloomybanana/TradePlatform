package org.gloomybanana.trade_platform.network;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import org.gloomybanana.trade_platform.TradePlatform;

import java.util.function.Supplier;

public class CRUDRecipe {
    String jsonString;

    public CRUDRecipe(PacketBuffer buffer) {
        this.jsonString = buffer.readString(3000000);
    }

    //反序列化
    public CRUDRecipe(String jsonString) {
        this.jsonString = jsonString;
    }

    //序列化
    public void toBytes(PacketBuffer buf) {
        buf.writeString(this.jsonString,3000000);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            TradePlatform.LOGGER.info("Send From Client:"+this.jsonString);
        });
        ServerPlayerEntity serverPlayer = null;
        try {
            serverPlayer = ctx.get().getSender().getCommandSource().asPlayer();
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }
        //还原接受包
        JSONObject jsonPacket = JSON.parseObject(jsonString, Feature.OrderedField);



        ctx.get().setPacketHandled(true);
    }
}
