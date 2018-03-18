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

import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RegisterManager {
    private static final RegisterManager INSTANCE = new RegisterManager();

    private final Map<Type, Set<RegisterHandler<? extends IForgeRegistryEntry<?>>>> registerMap = new HashMap<>();

    public static RegisterManager getInstance() {
        return INSTANCE;
    }

    public <T extends IForgeRegistryEntry<T>> void putRegister(@Nonnull T value) {
        putRegister(new RegisterHandler<>(value));
    }

    public <T extends IForgeRegistryEntry<T>> void putRegister(RegisterHandler<T> value) {
        putRegister(value, value.getType());
    }

    public <T extends IForgeRegistryEntry<T>> void putRegister(RegisterHandler<T> value, Type valueType) {
        Set<RegisterHandler<? extends IForgeRegistryEntry<?>>> handlerSet;
        if (registerMap.containsKey(valueType))
            handlerSet = registerMap.get(valueType);
        else
            handlerSet = new HashSet<>();
        handlerSet.add(value);
        registerMap.put(valueType, handlerSet);
    }

    public <T extends IForgeRegistryEntry<T>> void register(IForgeRegistry<T> registry) {
        Type valueType = registry.getRegistrySuperType();
        register(valueType, registry);
    }

    public <T extends IForgeRegistryEntry<T>> void register(Type type, IForgeRegistry<T> registry) {
        FoodCraftReloaded.getLogger().info("[FoodCraft Reloaded Register Manager] Registering type " + type);
        for (Map.Entry<Type, Set<RegisterHandler<? extends IForgeRegistryEntry<?>>>> entry : registerMap.entrySet())
            if (((Class<?>) type).isAssignableFrom((Class<?>) entry.getKey()))
                entry.getValue().stream().map(handler -> (RegisterHandler<T>) handler).forEach(handler -> handler.register(registry));
    }

    @SideOnly(Side.CLIENT)
    public void registerRender() {
        for (Map.Entry<Type, Set<RegisterHandler<? extends IForgeRegistryEntry<?>>>> entry : registerMap.entrySet())
            entry.getValue().forEach(RegisterHandler::registerRender);
    }

    public void registerOre() {
        for (Map.Entry<Type, Set<RegisterHandler<? extends IForgeRegistryEntry<?>>>> entry : registerMap.entrySet())
            entry.getValue().forEach(RegisterHandler::registerOre);
    }
}
