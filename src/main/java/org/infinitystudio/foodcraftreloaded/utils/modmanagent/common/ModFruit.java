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
 * it under the terms of the GNU Generalpublic License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Generalpublic License for more details.
 * <p/>
 * You should have received a copy of the GNU Generalpublic License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @license GPLv3
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ModFruit {
    enum FruitType {
        /**
         * Pear
         * 梨
         */
        Pear(0xe5db3b),

        /**
         * Litchi
         * 荔枝
         */
        Litchi(0xf6edd0),

        /**
         * Peach
         * 桃
         */
        Peach(0xf6cc24),

        /**
         * Orange
         * 橘子
         */
        Orange(0xf6ae24),

        /**
         * Mango
         * 芒果
         */
        Mango(0xffd986),

        /**
         * Lemon
         * 柠檬
         */
        Lemon(0xfcf393),

        /**
         * Grapefruit
         * 柚子
         * NOTICE: NOT SAME WITH GRAPE!
         */
        Grapefruit(0xece382),

        /**
         * Persimmon
         * 柿子
         */
        Persimmon(0xeb8c30),

        /**
         * Papaya
         * 木瓜
         */
        Papaya(0xf18a25),

        /**
         * Hawthorn
         * 山楂
         */
        Hawthorn(0xea7b0e),

        /**
         * Pomegranate
         * 石榴
         */
        Pomegranate(0xf46c30),

        /**
         * Dates
         * 红枣
         */
        Date(0xb57c63),

        /**
         * Cherry
         * 樱桃
         */
        Cherry(0xfd6d0d),

        /**
         * Coconut
         * 椰子
         */
        Coconut(0xfcf4d6),

        /**
         * Banana
         * 香蕉
         */
        Banana(0xf7eb6a);
        private int color;
        public int getcolor() {
            return color;
        }
        FruitType(int color) {
            this.color = color;
        }
    }

    FruitType type();

boolean itemRender() default true;
}
