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

package cc.lasmgratel.foodcraftreloaded.common;

import cc.lasmgratel.foodcraftreloaded.util.loader.annotation.Load;
import cc.lasmgratel.foodcraftreloaded.item.food.ItemLiqueur;
import cc.lasmgratel.foodcraftreloaded.item.food.LiqueurType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

public class LiqueurLoader {
    @Load
    public void loadLiqueurs() {
        ForgeRegistries.ITEMS.getKeys().stream().filter(s -> s.getResourcePath().contains("liqueur")).map(ForgeRegistries.ITEMS::getValue).forEach(liqueur -> {
            Item agedLiqueur = new ItemLiqueur(6)
                .setType(LiqueurType.AGED)
                .setRegistryName(liqueur.getRegistryName().getResourceDomain(), "aged_" + liqueur.getRegistryName().getResourcePath())
                .setUnlocalizedName(liqueur.getUnlocalizedName());
            ForgeRegistries.ITEMS.register(agedLiqueur);
            OreDictionary.registerOre("foodAgedLiqueur" + agedLiqueur.getUnlocalizedName().substring(0, agedLiqueur.getUnlocalizedName().lastIndexOf("Liqueur")), agedLiqueur);
            OreDictionary.registerOre("listAllAgedliqueur", agedLiqueur);
            OreDictionary.registerOre("listAllliqueur", agedLiqueur);
            OreDictionary.registerOre("listAllfoods", agedLiqueur);
            Item cocktailLiqueur = new ItemLiqueur(7)
                .setType(LiqueurType.COCKTAIL)
                .setRegistryName(liqueur.getRegistryName().getResourceDomain(), "cocktail_" + liqueur.getRegistryName().getResourcePath())
                .setUnlocalizedName(liqueur.getUnlocalizedName());
            ForgeRegistries.ITEMS.register(cocktailLiqueur);
            OreDictionary.registerOre("foodCocktailLiqueur" + cocktailLiqueur.getUnlocalizedName().substring(0, cocktailLiqueur.getUnlocalizedName().lastIndexOf("Liqueur")), cocktailLiqueur);
            OreDictionary.registerOre("listAllCocktailliqueur", cocktailLiqueur);
            OreDictionary.registerOre("listAllliqueur", cocktailLiqueur);
            OreDictionary.registerOre("listAllfoods", cocktailLiqueur);
        });
    }
}
