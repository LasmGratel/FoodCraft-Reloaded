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

import cc.lasmgratel.foodcraftreloaded.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.item.tool.EnumKitchenKnifeType;
import cc.lasmgratel.foodcraftreloaded.item.tool.ItemKitchenKnife;
import cc.lasmgratel.foodcraftreloaded.util.loader.annotation.Load;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class KitchenKnifeLoader {
    private Map<EnumKitchenKnifeType, ItemKitchenKnife> kitchenKnifeMap = new EnumMap<>(EnumKitchenKnifeType.class);

    @Load
    public void loadKitchenKnifes() {
        for (EnumKitchenKnifeType knifeType : EnumKitchenKnifeType.values())
            kitchenKnifeMap.put(knifeType, new ItemKitchenKnife(knifeType));
        kitchenKnifeMap.forEach((type, item) -> ForgeRegistries.ITEMS.register(item));
    }

    @Load(side = Side.CLIENT)
    public void loadKitchenKnifeRender() {
        kitchenKnifeMap.forEach((type, item) -> ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "kitchen_knife"), "inventory")));
    }

    @Load(value = LoaderState.POSTINITIALIZATION, side = Side.CLIENT)
    public void loadKitchenKnifeColor() {
        kitchenKnifeMap.forEach((type, item) -> Minecraft.getMinecraft().getItemColors().registerItemColorHandler(item, item));
    }

    public ItemKitchenKnife get(EnumKitchenKnifeType key) {
        return kitchenKnifeMap.get(key);
    }

    public ItemKitchenKnife put(EnumKitchenKnifeType key, ItemKitchenKnife value) {
        return kitchenKnifeMap.put(key, value);
    }

    public void forEach(BiConsumer<? super EnumKitchenKnifeType, ? super ItemKitchenKnife> action) {
        kitchenKnifeMap.forEach(action);
    }

    public Map<EnumKitchenKnifeType, ItemKitchenKnife> getKitchenKnifeMap() {
        return kitchenKnifeMap;
    }
}
