package net.infstudio.foodcraftreloaded.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

@SideOnly(Side.CLIENT)
public interface GuiUtils {
    static void renderFluid(FluidTank fluidTank, int x, int y, int height, int width) {
        try {
            Fluid fluid = fluidTank.getFluid().getFluid();
            Minecraft.getMinecraft().renderEngine.bindTexture(fluid.getStill());
            ITextureObject texture = Minecraft.getMinecraft().renderEngine.getTexture(fluid.getStill());
            Tessellator tessellator = Tessellator.getInstance();
            VertexBuffer vertexbuffer = tessellator.getBuffer();
            Color color = new Color(fluid.getColor(fluidTank.getFluid()));
            vertexbuffer.color(color.getRGBColorComponents(null)[0], color.getRGBColorComponents(null)[1], color.getRGBColorComponents(null)[2], 1.0f);

        } catch (NullPointerException ignored) {
        }
    }
}
