package net.infstudio.foodcraftreloaded.item.food;

import net.infstudio.foodcraftreloaded.util.masking.Colorable;

import java.awt.*;

public enum FruitType implements Colorable {
    /**
     * Pear
     * 梨
     */
    PEAR(new Color(0xe5db3b)),

    /**
     * Litchi
     * 荔枝
     */
    LITCHI(new Color(0xf6edd0)),

    /**
     * Peach
     * 桃
     */
    PEACH(new Color(0xffafaf)),

    /**
     * Orange
     * 橘子
     */
    ORANGE(new Color(0xf6ae24)),

    /**
     * Mango
     * 芒果
     */
    MANGO(new Color(0xffd986)),

    /**
     * Lemon
     * 柠檬
     */
    LEMON(new Color(0xfcf393)),

    /**
     * Grapefruit
     * 柚子
     */
    GRAPEFRUIT(new Color(0xece382)),

    /**
     * Persimmon
     * 柿子
     */
    PERSIMMON(new Color(0xeb8c30)),

    /**
     * Papaya
     * 木瓜
     */
    PAPAYA(new Color(0xf18a25)),

    /**
     * Hawthorn
     * 山楂
     */
    HAWTHORN(new Color(0xea7b0e)),

    /**
     * Pomegranate
     * 石榴
     */
    POMEGRANATE(new Color(0xf46c30)),

    /**
     * Date
     * 红枣
     */
    DATE(new Color(0xb57c63)),

    /**
     * Cherry
     * 樱桃
     */
    CHERRY(new Color(0xfd6d0d)),

    /**
     * Coconut
     * 椰子
     */
    COCONUT(new Color(0xfcf4d6)),

    /**
     * Banana
     * 香蕉
     */
    BANANA(new Color(0xf7eb6a));

    private Color color;

    FruitType(Color color) {
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

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
