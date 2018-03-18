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

import cc.lasmgratel.foodcraftreloaded.api.init.FCRFoods;
import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.common.item.food.ItemPFood;
import cc.lasmgratel.foodcraftreloaded.common.loader.register.RegisterManager;
import cc.lasmgratel.foodcraftreloaded.common.util.NameBuilder;
import cc.lasmgratel.foodcraftreloaded.common.util.loader.annotation.Load;
import cc.lasmgratel.foodcraftreloaded.common.util.loader.annotation.RegFood;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Arrays;

public class PropertiedFoodLoader {
    public PropertiedFoodLoader() {
        for (Field field : FCRFoods.class.getFields()) {
            field.setAccessible(true);
            try {
                RegFood anno = field.getDeclaredAnnotation(RegFood.class);
                if (anno == null) {
                    IForgeRegistryEntry food = (IForgeRegistryEntry) field.get(null);
                    RegisterManager.getInstance().putRegister(food);
                    continue;
                }

                ItemPFood item = (ItemPFood) field.get(null);
                if (ArrayUtils.isNotEmpty(anno.modifier()))
                    item.setProperties(anno.modifier());
                if (anno.amount() == Integer.MIN_VALUE)
                    item.calcHealAmount();
                else
                    item.setHealAmount(anno.amount());

                RegisterManager.getInstance().putRegister(item.setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(anno.name())).setUnlocalizedName(NameBuilder.buildUnlocalizedName(anno.name())));
            } catch (IllegalAccessException | NullPointerException e) {
                FoodCraftReloaded.getLogger().warn("Un-able to register food " + field.toGenericString(), e);
            }
        }
    }

    @Load(LoaderState.AVAILABLE)
    public void registerOreDict() {
        for (Field field : FCRFoods.class.getFields()) {
            field.setAccessible(true);
            try {
                RegFood anno = field.getDeclaredAnnotation(RegFood.class);
                if (anno == null) {
                    continue;
                }

                ItemPFood item = (ItemPFood) field.get(null);
                Arrays.asList(anno.oreDict()).forEach(s -> OreDictionary.registerOre(s, item));
                OreDictionary.registerOre("listAllfoods", item);
            } catch (IllegalAccessException | NullPointerException e) {
                FoodCraftReloaded.getLogger().warn("Un-able to register food " + field.toGenericString(), e);
            }
        }
    }
}
