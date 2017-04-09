package net.infstudio.foodcraftreloaded.client.gui;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.common.container.ContainerDrinkMachine;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.IItemHandlerModifiable;

public class GuiContainerDrinkMachine extends GuiContainer {
    private static final ResourceLocation DRINK_MACHINE_TEXTURE = new ResourceLocation(FoodCraftReloaded.MODID, "textures/gui/container/drink_machine.png");

    /**
     * The player inventory bound to this GUI.
     */
    private final InventoryPlayer playerInventory;
    private final IItemHandlerModifiable handler;

    public GuiContainerDrinkMachine(InventoryPlayer playerInventory, IItemHandlerModifiable handler) {
        super(new ContainerDrinkMachine(playerInventory, handler));
        this.playerInventory = playerInventory;
        this.handler = handler;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = I18n.format("container.drink_machine");
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(DRINK_MACHINE_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
    }
}
