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

package cc.lasmgratel.foodcraftreloaded.common.item.food;

import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.api.init.FCRFoods;
import cc.lasmgratel.foodcraftreloaded.common.util.NameBuilder;
import cc.lasmgratel.foodcraftreloaded.common.util.loader.annotation.Load;
import cc.lasmgratel.foodcraftreloaded.common.util.loader.annotation.RegFood;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Arrays;

public class PropertiedFoodLoader {
    @Load
    public void registerFoods() {
        for (Field field : FCRFoods.class.getFields()) {
            field.setAccessible(true);
            try {
                RegFood anno = field.getDeclaredAnnotation(RegFood.class);
                if (anno == null) {
                    Object food = field.get(null);
                    if (food instanceof ItemPorridge)
                        registerPorridge((ItemPorridge) food);
                    else if (food instanceof ItemSoup)
                        registerSoup((ItemSoup) food);
                    else if (food instanceof ItemNoodles)
                        registerNoodles((ItemNoodles) food);
                    else if (food instanceof ItemLiqueur)
                        registerLiqueur((ItemLiqueur) food);
                    continue;
                }

                ItemPFood item = (ItemPFood) field.get(null);
                if (ArrayUtils.isNotEmpty(anno.modifier()))
                    item.setProperties(anno.modifier());
                if (anno.amount() == Integer.MIN_VALUE)
                    item.calcHealAmount();
                else
                    item.setHealAmount(anno.amount());
                ForgeRegistries.ITEMS.register(item.setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(anno.name())).setUnlocalizedName(NameBuilder.buildUnlocalizedName(anno.name())));
                Arrays.asList(anno.oreDict()).forEach(s -> OreDictionary.registerOre(s, item));
                OreDictionary.registerOre("listAllfoods", item);
            } catch (IllegalAccessException | NullPointerException e) {
                FoodCraftReloaded.getLogger().warn("Un-able to register food " + field.toGenericString(), e);
            }
        }
    }

    private void registerPorridge(ItemPorridge porridge) {
        ForgeRegistries.ITEMS.register(porridge);
        OreDictionary.registerOre("foodPorridge" + NameBuilder.buildUnlocalizedName(porridge.getName()), porridge);
        OreDictionary.registerOre("listAllporridge", porridge);
        OreDictionary.registerOre("listAllfoods", porridge);
    }

    private void registerSoup(ItemSoup soup) {
        ForgeRegistries.ITEMS.register(soup);
        OreDictionary.registerOre("foodSoup" + NameBuilder.buildUnlocalizedName(soup.getName()), soup);
        OreDictionary.registerOre("listAllsoup", soup);
        OreDictionary.registerOre("listAllfoods", soup);
    }

    private void registerNoodles(ItemNoodles noodles) {
        ForgeRegistries.ITEMS.register(noodles);
        OreDictionary.registerOre("foodNoodles" + NameBuilder.buildUnlocalizedName(noodles.getName()), noodles);
        OreDictionary.registerOre("listAllnoodles", noodles);
        OreDictionary.registerOre("listAllfoods", noodles);
    }

    private void registerRice(ItemRice rice) {
        ForgeRegistries.ITEMS.register(rice);
        OreDictionary.registerOre("foodRice" + NameBuilder.buildUnlocalizedName(rice.getName()), rice);
        OreDictionary.registerOre("listAllrice", rice);
        OreDictionary.registerOre("listAllfoods", rice);
    }

    private void registerLiqueur(ItemLiqueur liqueur) {
        ForgeRegistries.ITEMS.register(liqueur);
//        OreDictionary.registerOre("foodLiqueur" + liqueur.getUnlocalizedName().substring(0, liqueur.getUnlocalizedName().lastIndexOf("Liqueur")), liqueur);
        OreDictionary.registerOre("listAllliqueur", liqueur);
        OreDictionary.registerOre("listAllfoods", liqueur);
    }

    @Load(side = Side.CLIENT)
    public void registerRenders() {
        for (Field field : FCRFoods.class.getFields()) {
            field.setAccessible(true);
            RegFood anno = field.getAnnotation(RegFood.class);
            try {
                if (anno == null)
                    continue;

                ItemPFood item = (ItemPFood) field.get(null);
                if (item.getRegistryName() == null)
                    item.setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(anno.name())).setUnlocalizedName(NameBuilder.buildRegistryName(anno.name()));
                registerRender(item, 0);
            } catch (Exception e) {
                FoodCraftReloaded.getLogger().warn("Un-able to register item " + field.toGenericString(), e);
            }
        }
    }

    private void registerRender(Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
