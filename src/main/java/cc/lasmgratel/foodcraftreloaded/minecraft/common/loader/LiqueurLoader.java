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

package cc.lasmgratel.foodcraftreloaded.minecraft.common.loader;

import cc.lasmgratel.foodcraftreloaded.minecraft.client.util.masking.CustomModelMasking;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.item.food.ItemLiqueur;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.enumeration.EnumColorable;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.loader.annotation.Load;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

public class LiqueurLoader {
    private Map<ItemLiqueur, CustomModelMasking> liqueurCustomModelMap = new HashMap<>();

    public Map<ItemLiqueur, CustomModelMasking> getLiqueurCustomModelMap() {
        return liqueurCustomModelMap;
    }

    @SideOnly(Side.CLIENT)
    public void loadRenders() {
        liqueurCustomModelMap.forEach((itemLiqueur, customModelMasking) -> {
            if (customModelMasking.getModelLocation() != null) {
                ModelBakery.registerItemVariants(itemLiqueur, customModelMasking.getModelLocation());
                ModelLoader.setCustomModelResourceLocation(itemLiqueur, 0, customModelMasking.getModelLocation());
            }
        });
    }

    @Load(side = Side.CLIENT, value = LoaderState.POSTINITIALIZATION)
    @SideOnly(Side.CLIENT)
    public void loadColors() {
        liqueurCustomModelMap.forEach((itemLiqueur, customModelMasking) -> {
            if (customModelMasking.getTintIndex() != -1 && customModelMasking instanceof EnumColorable) {
                Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
                    try {
                        if (customModelMasking.getTintIndex() != -1)
                            if (tintIndex == customModelMasking.getTintIndex())
                                return ((EnumColorable) customModelMasking).getColor(tintIndex).getRGB();
                    } catch (ClassCastException ignored) {
                        try {
                            if (tintIndex == 1)
                                return ((EnumColorable) customModelMasking).getColor(tintIndex).getRGB();
                        } catch (ClassCastException ignored2) { }
                    }
                    return -1;
                }, itemLiqueur);
            }
        });
    }
}
