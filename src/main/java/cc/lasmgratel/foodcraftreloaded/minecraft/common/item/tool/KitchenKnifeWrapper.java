package cc.lasmgratel.foodcraftreloaded.minecraft.common.item.tool;

import cc.lasmgratel.foodcraftreloaded.minecraft.client.util.GuiUtils;
import cc.lasmgratel.foodcraftreloaded.minecraft.client.util.masking.Colorable;
import net.minecraft.item.Item;

import java.awt.*;

public class KitchenKnifeWrapper implements Colorable {
    private Color color;
    private Item.ToolMaterial material;

    public KitchenKnifeWrapper(Item.ToolMaterial material) {
        this.material = material;
    }

    @Override
    public Color getColor() {
        return GuiUtils.getAverageColorOfItem(material.getRepairItemStack()).orElse(Color.black);
    }

    @Override
    public void setColor(Color color) {
    }

    public Item.ToolMaterial getMaterial() {
        return material;
    }
}
