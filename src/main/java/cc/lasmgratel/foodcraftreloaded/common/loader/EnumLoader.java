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

package cc.lasmgratel.foodcraftreloaded.common.loader;

import cc.lasmgratel.foodcraftreloaded.FoodCraftReloaded;
import com.google.common.reflect.TypeToken;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import java.lang.reflect.Constructor;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EnumLoader<T extends Enum<T>> {
    private final Map<Class<?>, Map<T, ?>> enumInstanceMap = new HashMap<>();

    /**
     * Attempt to register all entries from {@link #enumInstanceMap} to {@link ForgeRegistries}.
     * @see ForgeRegistries
     */
    public void register() {
        enumInstanceMap.forEach((instanceClass, enumMap) -> {
            if (Item.class.isAssignableFrom(instanceClass))
                enumMap.values().stream().map(o -> (Item) o).forEach(ForgeRegistries.ITEMS::register);
            else if (Block.class.isAssignableFrom(instanceClass))
                enumMap.values().stream().map(o -> (Block) o).forEach(ForgeRegistries.BLOCKS::register);
            else if (Potion.class.isAssignableFrom(instanceClass))
                enumMap.values().stream().map(o -> (Potion) o).forEach(ForgeRegistries.POTIONS::register);
            else if (PotionType.class.isAssignableFrom(instanceClass))
                enumMap.values().stream().map(o -> (PotionType) o).forEach(ForgeRegistries.POTION_TYPES::register);
            else if (SoundEvent.class.isAssignableFrom(instanceClass))
                enumMap.values().stream().map(o -> (SoundEvent) o).forEach(ForgeRegistries.SOUND_EVENTS::register);
            else if (Enchantment.class.isAssignableFrom(instanceClass))
                enumMap.values().stream().map(o -> (Enchantment) o).forEach(ForgeRegistries.ENCHANTMENTS::register);
            else if (VillagerRegistry.VillagerProfession.class.isAssignableFrom(instanceClass))
                enumMap.values().stream().map(o -> (VillagerRegistry.VillagerProfession) o).forEach(ForgeRegistries.VILLAGER_PROFESSIONS::register);
            else if (EntityEntry.class.isAssignableFrom(instanceClass))
                enumMap.values().stream().map(o -> (EntityEntry) o).forEach(ForgeRegistries.ENTITIES::register);
            else if (IRecipe.class.isAssignableFrom(instanceClass))
                enumMap.values().stream().map(o -> (IRecipe) o).forEach(ForgeRegistries.RECIPES::register);
            else if (Fluid.class.isAssignableFrom(instanceClass))
                enumMap.values().stream().map(o -> (Fluid) o).forEach(fluid -> {
                    FluidRegistry.registerFluid(fluid);
                    FluidRegistry.addBucketForFluid(fluid);
                });
        });
    }

    public Class<T> getType() {
        return (Class<T>) new TypeToken<T>(){}.getRawType();
    }

    public <V> Map<T, V> getInstanceMap(Class<V> containerClass) {
        if (!enumInstanceMap.containsKey(containerClass))
            enumInstanceMap.put(containerClass, new EnumMap<T, V>(getType()));
        return (Map<T, V>) enumInstanceMap.get(containerClass);
    }

    public <V> V getValue(T enumInstance) {
        Class<V> valueClass = (Class<V>) new TypeToken<V>(){}.getRawType();
        return (V) enumInstanceMap.get(valueClass).get(enumInstance);
    }

    public <V> List<V> getValue() {
        Class<V> valueClass = (Class<V>) new TypeToken<V>(){}.getRawType();
        return enumInstanceMap.get(valueClass).values().stream().map(value -> (V) value).collect(Collectors.toList());
    }

    public <V> void putValue(T enumInstance, Class<V> valueClass) {
        V instance = null;
        try {
            for (Constructor<?> constructor : valueClass.getConstructors()) {
                if (constructor.getParameterCount() == 0)
                    instance = (V) constructor.newInstance();
                else if (constructor.getParameterTypes()[0].isAssignableFrom(enumInstance.getDeclaringClass()))
                    instance = (V) constructor.newInstance(enumInstance);
            }
            if (instance != null)
                putValue(enumInstance, instance);
        } catch (Exception e) {
            FoodCraftReloaded.getLogger().error("Un-able to create a instance", e);
        }
    }

    public <V> void putValue(Class<V> valueClass) {
        for (T e : getType().getEnumConstants()) putValue(e, valueClass);
    }

    public <V> void putValue(T enumInstance, V value) {
        Map enumMap;
        Class<V> valueClass = (Class<V>) new TypeToken<V>(){}.getRawType();
        if (enumInstanceMap.containsKey(valueClass)) enumMap = enumInstanceMap.get(valueClass);
        else enumMap = new EnumMap<>(getType());
        enumMap.put(enumInstance, value);
        enumInstanceMap.put(valueClass, enumMap);
    }

}
