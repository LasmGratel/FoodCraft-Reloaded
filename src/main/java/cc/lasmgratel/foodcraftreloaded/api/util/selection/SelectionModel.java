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

import cc.lasmgratel.foodcraftreloaded.api.util.NamedProperty;

import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class SelectionModel<T extends NamedProperty> {
    private Stream<T> stream;

    public SelectionModel(Stream<T> stream) {
        this.stream = stream;
    }

    public SelectionModel<T> regexFilter(String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        stream = stream.filter(o -> pattern.matcher(o.getName()).find());
        return this;
    }

    public SelectionModel<T> filter(Predicate<String> predicate) {
        stream = stream.filter(o -> predicate.test(o.getName()));
        return this;
    }

    public SelectionModel<T> containsFilter(String s) {
        stream = stream.filter(o -> o.getName().contains(s));
        return this;
    }

    public SelectionModel<T> startsFilter(String prefix) {
        stream = stream.filter(o -> o.getName().startsWith(prefix));
        return this;
    }

    public SelectionModel<T> endsFilter(String suffix) {
        stream = stream.filter(o -> o.getName().endsWith(suffix));
        return this;
    }

    public SelectionModel<T> equalFilter(String s) {
        stream = stream.filter(o -> o.getName().equals(s));
        return this;
    }

    /**
     * Using custom pattern.
     * Modes are switched by the first letter.
     * R for Regex
     * S for StartsWith
     * E for EndsWith
     * Q or any other letter for Equals
     * C for Contains
     */
    public SelectionModel<T> filter(String pattern) {
        return filter(s -> {
            if (s.isEmpty())
                return false;

            char firstChar = pattern.charAt(0);

            switch (firstChar) {
                case 'R':
                    return SelectPredicates.REGEX.test(s, pattern.substring(1));
                case 'S':
                    return SelectPredicates.STARTS.test(s, pattern.substring(1));
                case 'E':
                    return SelectPredicates.ENDS.test(s, pattern.substring(1));
                case 'Q':
                    return SelectPredicates.EQUAL.test(s, pattern.substring(1));
                case 'C':
                    return SelectPredicates.CONTAINS.test(s, pattern.substring(1));
                default:
                    return SelectPredicates.EQUAL.test(s, pattern);
            }
        });
    }

    public Stream<T> stream() {
        return stream;
    }
}
