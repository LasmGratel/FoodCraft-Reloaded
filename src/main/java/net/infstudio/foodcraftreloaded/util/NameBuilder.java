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

package net.infstudio.foodcraftreloaded.util;

import com.google.common.collect.Lists;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public interface NameBuilder {
    @Nonnull
    static String buildRegistryName(String... params) {
        if (Arrays.stream(params).anyMatch(s -> s.contains("_"))) {
            List<String> temp = Lists.newArrayList();
            for (String param : params) {
                if (param.contains("_"))
                    temp.addAll(Arrays.asList(param.split("_")));
                else
                    temp.add(param);
            }
            StringBuilder stringBuilder = new StringBuilder(temp.get(0));
            for (String s : temp.subList(1, temp.size())) {
                stringBuilder.append('_');
                stringBuilder.append(s);
            }
            return stringBuilder.toString();
        }
        StringBuilder stringBuilder = new StringBuilder(params[0]);
        String[] copied = Arrays.copyOfRange(params, 1, params.length);
        for (String s : copied) {
            stringBuilder.append('_');
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    @Nonnull
    static String buildUnlocalizedName(String... params) {
        if (Arrays.stream(params).anyMatch(s -> s.contains("_"))) {
            List<String> temp = Lists.newArrayList();
            for (String param : params) {
                if (param.contains("_"))
                    temp.addAll(Arrays.asList(param.split("_")));
                else
                    temp.add(param);
            }
            StringBuilder stringBuilder = new StringBuilder(temp.get(0));
            for (String s : temp.subList(1, temp.size())) {
                stringBuilder.append(Character.toUpperCase(s.charAt(0)));
                stringBuilder.append(s.substring(1));
            }
            return stringBuilder.toString();
        }
        StringBuilder stringBuilder = new StringBuilder(params[0]);
        String[] copied = Arrays.copyOfRange(params, 1, params.length);
        for (String s : copied) {
            stringBuilder.append(Character.toUpperCase(s.charAt(0)));
            stringBuilder.append(s.substring(1));
        }
        return stringBuilder.toString();
    }
}
