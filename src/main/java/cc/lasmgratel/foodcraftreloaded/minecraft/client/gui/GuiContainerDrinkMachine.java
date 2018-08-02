/*
 * FoodCraft Mod - Add more food to your Minecraft.
 * Copyright (C) 2017 Lasm Gratel
 *
 * This file is part of FoodCraft Mod.
 *
 * FoodCraft Mod is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FoodCraft Mod is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FoodCraft Mod.  If not, see <http://www.gnu.org/licenses/>.
 */

package cc.lasmgratel.foodcraftreloaded.minecraft.client.gui;

import cc.lasmgratel.foodcraftreloaded.minecraft.client.util.FluidStackRenderer;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.FoodCraftReloadedMod;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.block.tileentity.TileEntityProgressiveMachine;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.block.tileentity.TileEntitySmeltingDrinkMachine;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.container.ContainerDrinkMachine;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.AutomatedGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.lwjgl.util.Rectangle;

public class GuiContainerDrinkMachine extends GuiContainerFCR implements AutomatedGui {
    private static final ResourceLocation DRINK_MACHINE_TEXTURE = new ResourceLocation(FoodCraftReloadedMod.MODID, "textures/gui/container/drink_machine.png");
    protected Rectangle fluidBar = new Rectangle(26, 11, 16, 58);

    /**
     * The player inventory bound to this GUI.
     */
    private final InventoryPlayer playerInventory;
    private final IItemHandlerModifiable handler;
    private final TileEntity tileEntity;
    private final IFluidTank fluidTank;
    private final Rectangle rectangle = new Rectangle(26, 11, 16, 58);
    private final FluidStackRenderer renderer;

    public GuiContainerDrinkMachine(InventoryPlayer playerInventory, TileEntity tileEntity) {
        super(new ContainerDrinkMachine(playerInventory, tileEntity));
        this.tileEntity = tileEntity;
        this.playerInventory = playerInventory;
        this.handler = (IItemHandlerModifiable) tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        this.fluidTank = (IFluidTank) tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
        this.renderer = new FluidStackRenderer(fluidTank.getCapacity(), true, 16, 58);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = I18n.format("container.drink_machine");
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);

        renderer.render(Minecraft.getMinecraft(), 26, 11, fluidTank.getFluid());
        if (rectangle.contains(mouseX - guiLeft, mouseY - guiTop) && fluidTank.getFluidAmount() != 0) {
            ScaledResolution scaledresolution = new ScaledResolution(mc);
            GuiUtils.drawHoveringText(ItemStack.EMPTY, renderer.getTooltip(mc, fluidTank.getFluid(), ITooltipFlag.TooltipFlags.NORMAL), mouseX - guiLeft, mouseY - guiTop, scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight(), -1, fontRenderer);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(DRINK_MACHINE_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        int l = this.getCookProgressScaled(24);
        this.drawTexturedModalRect(i + 88, j + 31, 176, 0, l + 1, 16);
    }

    private int getCookProgressScaled(int pixels) {
        int i = TileEntityProgressiveMachine.getProgress(tileEntity);
        int j = TileEntitySmeltingDrinkMachine.BURN_TIME;
        return i != 0 ? i * pixels / j : 0;
    }
}
