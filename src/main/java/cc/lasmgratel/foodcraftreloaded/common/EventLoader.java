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
import cc.lasmgratel.foodcraftreloaded.util.loader.annotation.Load;
import cc.lasmgratel.foodcraftreloaded.init.FCRBlocks;
import cc.lasmgratel.foodcraftreloaded.init.FCRItems;
import cc.lasmgratel.foodcraftreloaded.item.food.FruitType;
import cc.lasmgratel.foodcraftreloaded.item.food.ItemCakes;
import cc.lasmgratel.foodcraftreloaded.item.food.ItemVegetableCakes;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.apache.commons.lang3.StringUtils;

public class EventLoader {

    @Load(value = LoaderState.INITIALIZATION)
    public void register() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onNeighborNotify(BlockEvent.NeighborNotifyEvent event) {
        if (event.getWorld().getBlockState(event.getPos().up()).getBlock() == FCRBlocks.RICE_PLANT &&
            event.getState().getBlock() != Blocks.FARMLAND)
            event.getWorld().destroyBlock(event.getPos().up(), true);
    }

    @SubscribeEvent
    public void onRegister(RegistryEvent.Register<IRecipe> event) {
        FruitLoader loader = FoodCraftReloaded.getProxy().getLoaderManager().getLoader(FruitLoader.class).get();
        for (FruitType fruitType : FruitType.values()) {
            event.getRegistry().register(new ShapedOreRecipe(new ResourceLocation("food"), new ItemStack(loader.getSaplingMap().get(fruitType)), " F ", "FXF", " F ", 'F', "crop" + StringUtils.capitalize(fruitType.toString()), 'X', "treeSapling").setRegistryName("fruit_sapling"));
            event.getRegistry().register(new ShapelessOreRecipe(new ResourceLocation("food"), new ItemStack(loader.getIcecreams(), 1, fruitType.ordinal()), "food" + StringUtils.capitalize(fruitType.toString()) + "juice", "foodIcecream").setRegistryName("fruit_icecream"));
        }
    }

    @SubscribeEvent
    public void onCraftingCake(PlayerEvent.ItemCraftedEvent event) {
        if (event.crafting.getItem() instanceof ItemCakes || event.crafting.getItem() instanceof ItemVegetableCakes)
            event.craftMatrix.setInventorySlotContents(1, new ItemStack(FCRItems.GLASS_BOTTLE));
    }
}
