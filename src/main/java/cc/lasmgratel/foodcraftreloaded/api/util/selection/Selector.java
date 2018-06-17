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

import java.util.Collection;
import java.util.stream.Stream;

public interface Selector {
    static SelectionModel<StringNamedWrapper> wrap(String... strings) {
        return wrap(Stream.of(strings));
    }

    static SelectionModel<StringNamedWrapper> wrap(Stream<String> stream) {
        return createSelectionFrom(stream.map(StringNamedWrapper::new));
    }

    static <T extends NamedProperty> SelectionModel<T> createSelectionFrom(T... objects) {
        return createSelectionFrom(Stream.of(objects));
    }

    static <T extends NamedProperty> SelectionModel<T> createSelectionFrom(Collection<T> objects) {
        return createSelectionFrom(objects.stream());
    }

    static <T extends NamedProperty> SelectionModel<T> createSelectionFrom(Stream<T> stream) {
        return new SelectionModel<>(stream);
    }
}
