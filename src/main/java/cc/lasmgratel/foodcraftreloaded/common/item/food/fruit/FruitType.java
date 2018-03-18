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

package cc.lasmgratel.foodcraftreloaded.common.item.food.fruit;

import cc.lasmgratel.foodcraftreloaded.client.util.masking.Colorable;
import cc.lasmgratel.foodcraftreloaded.common.item.food.EffectiveItem;
import net.minecraft.potion.PotionEffect;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public enum FruitType implements Colorable, EffectiveItem {
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
    private List<PotionEffect> effects;

    FruitType(Color color) {
        this.color = color;
    }

    FruitType(Color color, PotionEffect... effects) {
        this(color);
        this.effects = Arrays.asList(effects);
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

    @Override
    public List<PotionEffect> getEffects() {
        return effects;
    }
}
