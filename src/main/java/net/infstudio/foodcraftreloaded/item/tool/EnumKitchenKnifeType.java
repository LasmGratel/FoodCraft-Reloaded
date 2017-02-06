package net.infstudio.foodcraftreloaded.item.tool;

import net.infstudio.foodcraftreloaded.utils.masking.IColorable;

import java.awt.Color;

public enum EnumKitchenKnifeType implements IColorable {
    STONE(100, new Color(128, 128, 128)),
    IRON(300, new Color(234, 234, 234)),
    GOLD(500, new Color(254, 254, 5)),
    DIAMOND(1000, new Color(154, 255, 243)),
    EMERALD(1750, new Color(0, 254, 0));

    private int maxDamage;
    private Color color;

    EnumKitchenKnifeType(int maxDamage, Color color) {
        this.maxDamage = maxDamage;
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
