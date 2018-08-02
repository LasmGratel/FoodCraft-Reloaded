package cc.lasmgratel.foodcraftreloaded.minecraft.api.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class WidgetBackground implements Widget {
    private final ResourceLocation location;

    public WidgetBackground(ResourceLocation location) {
        this.location = location;
    }

    @Override
    public void render(GuiScreen gui, int x, int y) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        gui.mc.getTextureManager().bindTexture(location);
        int i = (gui.width - x) / 2;
        int j = (gui.height - y) / 2;
        gui.drawTexturedModalRect(i, j, 0, 0, x, y);
    }
}
