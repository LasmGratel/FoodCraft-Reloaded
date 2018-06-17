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

package cc.lasmgratel.foodcraftreloaded.api.util.selection;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.regex.Pattern;

public interface SelectPredicates {
    BiPredicate<String, String> REGEX = (s, regex) -> {
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        return pattern.matcher(s).find();
    };

    BiPredicate<String, String> CONTAINS = String::contains;
    BiPredicate<String, String> STARTS = String::startsWith;
    BiPredicate<String, String> ENDS = String::endsWith;
    BiPredicate<String, String> EQUAL = Objects::equals;
}
