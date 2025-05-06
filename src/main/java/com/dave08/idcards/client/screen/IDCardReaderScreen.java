package com.dave08.idcards.client.screen;

import com.dave08.idcards.block.entity.menu.IDCardReaderMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class IDCardReaderScreen extends AbstractContainerScreen<IDCardReaderMenu> {
    private @SuppressWarnings("deprication") static final ResourceLocation TEXTURE = new ResourceLocation("idcards", "textures/gui/IdcardReaderMenu.png");
    private EditBox pulse;

    public IDCardReaderScreen(IDCardReaderMenu menu, Inventory playerInv, Component title) {
        super(menu, playerInv, title);
        this.imageWidth = 176;
        this.imageHeight = 222;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, 6 * 18 + 17); // main
        guiGraphics.blit(TEXTURE, x, y + 6 * 18 + 17, 0, 126, this.imageWidth, 96); // player inv
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
        pulse.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    protected void init() {
        super.init();
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        pulse = new EditBox(this.font, x + this.imageWidth + 5, y + 10, 50, 20, Component.literal("Number"));
        pulse.setMaxLength(5);
        pulse.setFilter(s -> s.matches("\\d*")); // digits only
        pulse.setResponder(this::onPulseChanged);
        this.addRenderableWidget(pulse);
    }

    private void onPulseChanged(String text) {
        if (!text.isEmpty()) {
            try {
                int value = Integer.parseInt(text);
                // Do something with 'value'
            } catch (NumberFormatException ignored) {}
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (pulse.keyPressed(keyCode, scanCode, modifiers) || pulse.isFocused()) {
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        if (pulse.charTyped(codePoint, modifiers)) {
            return true;
        }
        return super.charTyped(codePoint, modifiers);
    }
}