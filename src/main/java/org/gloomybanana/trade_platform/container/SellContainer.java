package org.gloomybanana.trade_platform.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;
import org.gloomybanana.trade_platform.handler.Registry;
import org.gloomybanana.trade_platform.weidge.SingleSlot;

public class SellContainer extends Container {
    private final PacketBuffer packetBuffier;
    public IInventory craftResult = new CraftingInventory(this, 3, 3);
    public Slot[] slots = new Slot[3];
    public SellContainer(int id, PlayerInventory playerInventory, PacketBuffer packetBuffer) {
        super(Registry.entryListContainer.get(), id);
        layoutPlayerInventorySlots(playerInventory, 8, 84);
        slots[0] = this.addSlot(new Slot(this.craftResult, 0, 9, 18));
        slots[1] = this.addSlot(new Slot(this.craftResult,1,30,18));
        slots[2] = this.addSlot(new Slot(this.craftResult,2,51,18));
        this.packetBuffier = packetBuffer;
    }

    public PacketBuffer getPacketBuffier() {
        return packetBuffier;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    private void layoutPlayerInventorySlots(IInventory inventory, int leftCol, int topRow) {
        // Player inventory
        addSlotBox(inventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(inventory, 0, leftCol, topRow, 9, 18);
    }
    private int addSlotRange(IInventory inventory, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new Slot(inventory, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IInventory inventory, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(inventory, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }
}
