package org.gloomybanana.trade_platform.containerprovider;

import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.gloomybanana.trade_platform.container.SellContainer;

import javax.annotation.Nullable;

public class SellContainerProvider implements INamedContainerProvider {
    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity player) {
        PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
        return new SellContainer(id,playerInventory,packetBuffer);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("gui.trade_platform.sell.title");
    }
}
