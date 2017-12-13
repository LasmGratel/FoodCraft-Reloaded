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

package cc.lasmgratel.foodcraftreloaded.common.item;

import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.api.init.FCRItems;
import cc.lasmgratel.foodcraftreloaded.common.util.NameBuilder;
import cc.lasmgratel.foodcraftreloaded.common.util.loader.annotation.Load;
import cc.lasmgratel.foodcraftreloaded.common.util.loader.annotation.RegItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.reflect.Field;
import java.util.Arrays;

public class ItemLoader {
    @Load
    public void registerItems() {
        for (Field field : FCRItems.class.getFields()) {
            field.setAccessible(true);
            try {
                RegItem annoItem = field.getAnnotation(RegItem.class);
                if (annoItem == null)
                    continue;

                Item item = (Item) field.get(null);
                ForgeRegistries.ITEMS.register(item.setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(annoItem.value())).setUnlocalizedName(NameBuilder.buildUnlocalizedName(annoItem.value())));

                Arrays.stream(annoItem.oreDict()).forEach(s -> OreDictionary.registerOre(s, item));
            } catch (Throwable e) {
                FoodCraftReloaded.getLogger().warn("Un-able to register item " + field.toGenericString(), e);
            }
        }
    }

    @Load(side = Side.CLIENT)
    public void registerRenders() {
        for (Field field : FCRItems.class.getFields()) {
            field.setAccessible(true);
            RegItem anno = field.getAnnotation(RegItem.class);
            try {
                if (anno == null)
                    continue;

                Item item = (Item) field.get(null);
                if (item.getHasSubtypes()) {
                    if (item instanceof IMetadatable) {
                        for (int i = 0; i < ((IMetadatable) item).getMaxMetadata(); i++)
                            registerRender(item, i);
                    }
                } else {
                    registerRender(item, 0);
                }
            } catch (Exception e) {
                FoodCraftReloaded.getLogger().warn("Un-able to register item " + field.toGenericString(), e);
            }
        }
    }

    private void registerRender(Item item, int meta)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
