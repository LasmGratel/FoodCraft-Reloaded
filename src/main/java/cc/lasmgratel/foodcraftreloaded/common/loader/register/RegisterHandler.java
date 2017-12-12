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

package cc.lasmgratel.foodcraftreloaded.common.loader.register;

import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;
import java.lang.reflect.Type;

public final class RegisterHandler<T extends IForgeRegistryEntry<T>> {
    @Nonnull
    private final T value;

    private Class<?> typeClass;

    public RegisterHandler(@Nonnull T value) {
        this.value = value;
        typeClass = cast(value.getClass());
    }

    private Class<?> cast(Class<?> typeClass) {
        if (IForgeRegistryEntry.class.isAssignableFrom(typeClass.getSuperclass()) && typeClass.getSuperclass() != IForgeRegistryEntry.class && typeClass.getSuperclass() != IForgeRegistryEntry.Impl.class)
            return cast(typeClass.getSuperclass());
        return typeClass;
    }

    @Nonnull
    public T getValue() {
        return value;
    }

    public void register(IForgeRegistry<T> registry) {
        registry.register(value);
    }

    public Type getType() {
        return typeClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegisterHandler<?> that = (RegisterHandler<?>) o;

        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
