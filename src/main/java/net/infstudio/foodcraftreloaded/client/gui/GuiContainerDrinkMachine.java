package net.infstudio.foodcraftreloaded.client.gui;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.common.container.ContainerDrinkMachine;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.lwjgl.util.Rectangle;

import java.awt.*;
import java.util.Optional;

public class GuiContainerDrinkMachine extends GuiContainer {
    private static final ResourceLocation DRINK_MACHINE_TEXTURE = new ResourceLocation(FoodCraftReloaded.MODID, "textures/gui/container/drink_machine.png");
    protected Rectangle fluidBar = new Rectangle(26, 11, 16, 58);

    /**
     * The player inventory bound to this GUI.
     */
    private final InventoryPlayer playerInventory;
    private final IItemHandlerModifiable handler;
    private final IFluidTank fluidTank;

    public GuiContainerDrinkMachine(InventoryPlayer playerInventory, TileEntity entity) {
        super(new ContainerDrinkMachine(playerInventory, entity));
        this.playerInventory = playerInventory;
        this.handler = (IItemHandlerModifiable) entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        this.fluidTank = (IFluidTank) entity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = I18n.format("container.drink_machine");
        Optional.ofNullable(fluidTank.getFluid()).ifPresent(fluidStack -> {
            TextureAtlasSprite fluidTexture = mc.getTextureMapBlocks().registerSprite(fluidStack.getFluid().getStill());
            mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            int fluidHeight = MathHelper.ceil(((float) fluidTank.getFluidAmount() / (float) fluidTank.getCapacity()) * (float) fluidBar.getHeight());
            drawTexturedModalRect1(fluidBar.getX() + guiLeft, fluidBar.getY() + guiTop + (fluidBar.getHeight() - fluidHeight), fluidTexture, fluidBar.getWidth(), fluidHeight);
        });
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    private void drawTexturedModalRect1(int xCoord, int yCoord, TextureAtlasSprite textureSprite, int widthIn, int heightIn) {
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        Color color = new Color(fluidTank.getFluid().getFluid().getColor());
        vertexbuffer.color(color.getRGBColorComponents(null)[0], color.getRGBColorComponents(null)[1], color.getRGBColorComponents(null)[2], fluidTank.getFluid().getFluid().isGaseous() ? 0.5f : 0.8f);
        vertexbuffer.pos((double)(xCoord), (double)(yCoord + heightIn), (double)this.zLevel).tex((double)textureSprite.getMinU(), (double)textureSprite.getMaxV()).endVertex();
        vertexbuffer.pos((double)(xCoord + widthIn), (double)(yCoord + heightIn), (double)this.zLevel).tex((double)textureSprite.getMaxU(), (double)textureSprite.getMaxV()).endVertex();
        vertexbuffer.pos((double)(xCoord + widthIn), (double)(yCoord), (double)this.zLevel).tex((double)textureSprite.getMaxU(), (double)textureSprite.getMinV()).endVertex();
        vertexbuffer.pos((double)(xCoord), (double)(yCoord), (double)this.zLevel).tex((double)textureSprite.getMinU(), (double)textureSprite.getMinV()).endVertex();
        tessellator.draw();
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
