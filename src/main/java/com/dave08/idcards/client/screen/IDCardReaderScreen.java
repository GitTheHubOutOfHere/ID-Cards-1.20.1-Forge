package com.dave08.idcards.client.screen;

import com.dave08.idcards.IDCards;
import com.dave08.idcards.ModNetworking;
import com.dave08.idcards.block.entity.menu.IDCardReaderMenu;
import com.dave08.idcards.idcardReaderPacket;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class IDCardReaderScreen extends AbstractContainerScreen<IDCardReaderMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("idcards", "textures/gui/idcard_reader_menu.png");
    //private EditBox pulseLengthBox;
    private IDCardReaderMenu guiMenu;

    public IDCardReaderScreen(IDCardReaderMenu menu, Inventory playerInv, Component title) {
        super(menu, playerInv, title);
        this.guiMenu = menu;
        this.imageWidth = 176;
        this.imageHeight = 222;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    protected void init() {
        super.init();
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        // Place the text box to the right of the GUI without shifting the rest
        int boxX = x + this.imageWidth + 10; // 10 px padding to the right
        int boxY = y + 10;

        /*this.pulseLengthBox = new EditBox(this.font, boxX, boxY, 100, 20, Component.literal("Enter text"));
        this.pulseLengthBox.setMaxLength(64);
        this.pulseLengthBox.setBordered(false);
        this.pulseLengthBox.setVisible(true);
        this.pulseLengthBox.setTextColor(0xFFFFFF);
        this.pulseLengthBox.setResponder(this::pulseChanged);
        IDCards.LOGGER.info("Getting value (3): " + menu.getPulseLength());
        this.pulseLengthBox.setValue(String.valueOf(menu.getPulseLength()));
        this.addRenderableWidget(this.pulseLengthBox);/**/
    }

    /*private void pulseChanged(String text)
    {
        if (!text.isBlank())
        {
            try {
                int value = Integer.parseInt(text);
                //menu.setPulseLength(value);
                int pulse = Integer.parseInt(pulseLengthBox.getValue());
                ModNetworking.CHANNEL.sendToServer(new idcardReaderPacket(menu.getBlockEntity().getBlockPos(), pulse));
            } catch (NumberFormatException ignored) {}
        }
    }/**/

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, 6 * 18 + 17); // main
        guiGraphics.blit(TEXTURE, x, y + 6 * 18 + 17, 0, 126, this.imageWidth, 96); /* player inv*/
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title, 8, 6, 4210752, false);
        guiGraphics.drawString(this.font, this.playerInventoryTitle, 8, this.inventoryLabelY, 4210752, false);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    /*@Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.pulseLengthBox != null && this.pulseLengthBox.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        if (this.pulseLengthBox != null && this.pulseLengthBox.charTyped(codePoint, modifiers)) {
            return true;
        }
        return super.charTyped(codePoint, modifiers);
    }

    @Override
    public void resize(net.minecraft.client.Minecraft minecraft, int width, int height) {
        String currentText = this.pulseLengthBox.getValue();
        this.init(minecraft, width, height);
        this.pulseLengthBox.setValue(currentText);
    }/**/
}