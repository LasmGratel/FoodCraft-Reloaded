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

import cc.lasmgratel.foodcraftreloaded.api.capability.liqueur.LiqueurType;
import cc.lasmgratel.foodcraftreloaded.api.capability.liqueur.LiqueurTypes;
import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.common.item.food.ItemLiqueur;
import cc.lasmgratel.foodcraftreloaded.common.util.loader.annotation.Load;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IRegistryDelegate;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LiqueurLoader {
    private List<ItemLiqueur> cachedLiqueurs = new ArrayList<>();

    @Load
    public void loadLiqueurs() {
        ForgeRegistries.ITEMS.getKeys().stream().filter(s -> s.getResourcePath().contains("liqueur")).map(ForgeRegistries.ITEMS::getValue).collect(Collectors.toList()).forEach(liqueur -> {
            for (LiqueurType liqueurType : LiqueurTypes.values()) {
                if (liqueurType == LiqueurTypes.NORMAL)
                    continue;
                ItemLiqueur typedLiqueur = new ItemLiqueur(MathHelper.floor(liqueurType.getHealModifier() * ((ItemFood) liqueur).getHealAmount(new ItemStack(liqueur))));
                typedLiqueur.setLiqueurType(liqueurType);
                typedLiqueur.setRegistryName(liqueur.getRegistryName().getResourceDomain(), liqueurType.getUnlocalizedName() + "_" + liqueur.getRegistryName().getResourcePath());
                typedLiqueur.setUnlocalizedName(liqueur.getUnlocalizedName());
                ForgeRegistries.ITEMS.register(typedLiqueur);
                OreDictionary.registerOre("listAll" + StringUtils.capitalize(liqueurType.getUnlocalizedName()) + "liqueur", typedLiqueur);
                OreDictionary.registerOre("listAllliqueur", typedLiqueur);
                OreDictionary.registerOre("listAllfoods", typedLiqueur);
                cachedLiqueurs.add(typedLiqueur);
            }
        });
    }

    @Load(side = Side.CLIENT)
    @SideOnly(Side.CLIENT)
    public void loadRenders() {
        try {
            Field field = ModelLoader.class.getDeclaredField("customMeshDefinitions");
            field.setAccessible(true);
            Map<IRegistryDelegate<Item>, ItemMeshDefinition> customMeshDefinitions = (Map<IRegistryDelegate<Item>, ItemMeshDefinition>) field.get(null);
            cachedLiqueurs.forEach(liqueur -> {
                if (customMeshDefinitions.containsKey(liqueur))
                    ModelLoader.setCustomModelResourceLocation(liqueur, 0, customMeshDefinitions.get(liqueur).getModelLocation(new ItemStack(liqueur)));
            });
        } catch (Exception e) {
            FoodCraftReloaded.getLogger().error("Cannot get custom mesh definitions", e);
        }
    }

    @Load(side = Side.CLIENT, value = LoaderState.POSTINITIALIZATION)
    @SideOnly(Side.CLIENT)
    public void loadColors() {
        try {
            Field field = ItemColors.class.getDeclaredField("itemColorMap");
            field.setAccessible(true);
            Map<IRegistryDelegate<Item>, IItemColor> itemColorMap = (Map<IRegistryDelegate<Item>, IItemColor>) field.get(Minecraft.getMinecraft().getItemColors());
            cachedLiqueurs.forEach(liqueur -> itemColorMap.entrySet().stream().filter(entry -> entry.getKey().get().equals(liqueur)).forEach(entry -> Minecraft.getMinecraft().getItemColors().registerItemColorHandler(entry.getValue(), entry.getKey().get())));
        } catch (Exception e) {
            FoodCraftReloaded.getLogger().error("Cannot get custom mesh definitions", e);
        }
    }
}
