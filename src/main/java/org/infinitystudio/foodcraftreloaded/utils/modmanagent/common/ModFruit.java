package org.infinitystudio.foodcraftreloaded.utils.modmanagent.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ModFruit {
    public final int COlOR_PEAR = 0xe5db3b;
    public final int COlOR_LITCHI = 0xf6edd0;
    public final int COlOR_PEACH = 0xf6cc24;
    public final int COlOR_ORANGE = 0xf6ae24;
    public final int COlOR_MANGO = 0xffd986;
    public final int COlOR_LEMON = 0xfcf393;
    public final int COlOR_GRAPEFRUIT = 0xece382;
    public final int COlOR_PERSIMMON = 0xeb8c30;
    public final int COlOR_PAPAYA = 0xf18a25;
    public final int COlOR_HAWTHORN = 0xea7b0e;
    public final int COlOR_POMEGRANATE = 0xf46c30;
    public final int COlOR_DATE = 0xb57c63;
    public final int COlOR_CHERRY = 0xfd6d0d;
    public final int COlOR_COCONUT = 0xfcf4d6;
    public final int COlOR_BANANA = 0xf7eb6a;

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
