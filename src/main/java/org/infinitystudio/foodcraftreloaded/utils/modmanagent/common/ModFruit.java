package org.infinitystudio.foodcraftreloaded.utils.modmanagent.common;

/**
 * FoodCraft Mod for Minecraft.
 * Copyright (C) 2016 Infinity Studio.
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @license GPLv3
 */
public @interface ModFruit {
    enum FruitType {
        /**
         * Pear
         * 梨
         */
        Pear,

        /**
         * Litchi
         * 荔枝
         */
        Litchi,

        /**
         * Peach
         * 桃
         */
        Peach,

        /**
         * Orange
         * 橘子
         */
        Orange,

        /**
         * Mango
         * 芒果
         */
        Mango,

        /**
         * Lemon
         * 柠檬
         */
        Lemon,

        /**
         * Grapefruit
         * 柚子
         * NOTICE: NOT SAME WITH GRAPE!
         */
        Grapefruit,

        /**
         * Persimmon
         * 柿子
         */
        Persimmon,

        /**
         * Papaya
         * 木瓜
         */
        Papaya,

        /**
         * Hawthorn
         * 山楂
         */
        Hawthorn,

        /**
         * Pomegranate
         * 石榴
         */
        Pomegranate,

        /**
         * Dates
         * 红枣
         */
        Date,

        /**
         * Cherry
         * 樱桃
         */
        Cherry,

        /**
         * Coconut
         * 椰子
         */
        Coconut,

        /**
         * Banana
         * 香蕉
         */
        Banana
    }

    FruitType type();

    boolean itemRender() default true;
}
