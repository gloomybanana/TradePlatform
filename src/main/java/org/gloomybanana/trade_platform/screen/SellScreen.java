package org.gloomybanana.trade_platform.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.gloomybanana.trade_platform.TradePlatform;
import org.gloomybanana.trade_platform.container.SellContainer;

import java.util.ArrayList;
import java.util.List;

public class SellScreen extends ContainerScreen<SellContainer> {

    protected final ResourceLocation SCREEN_TEXTURE = new ResourceLocation(TradePlatform.MOD_ID, "textures/gui/sell.png");;
    protected final ResourceLocation BUTTON_TEXTURE = new ResourceLocation(TradePlatform.MOD_ID, "textures/gui/buttons.png");;
    private final String jsonPacket;

    private int field_214139_n;

    protected ImageButton back;
    protected ImageButton upload;

    public SellScreen(SellContainer container, PlayerInventory inv, ITextComponent titleIn) {
        super(container, inv, titleIn);
        this.jsonPacket = container.getPacketBuffier().readString();
        this.ySize = 166;
        this.xSize = 276;
        this.playerInventoryTitleX = 107;
    }
    protected void init() {
        super.init();
        upload = new ImageButton(guiLeft + 7,guiTop + 63,27,16,0,0,16, BUTTON_TEXTURE,this::upload);
        back = new ImageButton(guiLeft + 142,guiTop + 63,27,16,0,32,16, BUTTON_TEXTURE,this::back);
        this.addButton(upload);
        this.addButton(back);
    }


    private void upload(Button button) {
    }
    private void back(Button button) {
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(SCREEN_TEXTURE);
        blit(matrixStack, guiLeft, guiTop, this.getBlitOffset(), 0.0F, 0.0F, this.xSize, this.ySize, 256, 512);
    }
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack,int mouseX, int mouseY) {
        this.font.drawString(matrixStack,this.title.getString(), 8.0F, 6.0F, 4210752);

    }
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack,mouseX, mouseY, partialTicks);
        ArrayList entries = new ArrayList();
        entries.add("1");
        entries.add("2");
        entries.add("3");
        this.renderScroll(matrixStack, guiLeft, guiTop, entries);
    }
    private void renderScroll(MatrixStack matrixStack, int guiLeft, int guiTop, List entries) {
        this.minecraft.getTextureManager().bindTexture(SCREEN_TEXTURE);
        int i = entries.size() + 1 - 7;
        if (i > 1) {
            int j = 139 - (27 + (i - 1) * 139 / i);
            int k = 1 + j / i + 139 / i;
            int l = 113;
            int i1 = Math.min(113, this.field_214139_n * k);
            if (this.field_214139_n == i - 1) {
                i1 = 113;
            }

            blit(matrixStack, guiLeft + 265, guiTop + 18 + i1, this.getBlitOffset(), 276.0F, 0.0F, 6, 27, 256, 512);
        } else {
            blit(matrixStack, guiLeft + 265, guiTop + 18, this.getBlitOffset(), 282.0F, 0.0F, 6, 27, 256, 512);
        }

    }
}
