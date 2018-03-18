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

import cc.lasmgratel.foodcraftreloaded.minecraft.api.init.FCRItems;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.FoodCraftReloadedMod;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.item.food.fruit.ItemFruitCake;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.item.food.vegetable.ItemVegetableCake;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.recipe.CakeRecipe;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.recipe.KitchenKnifeRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class EventLoader {
    public EventLoader() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onNeighborNotify(BlockEvent.NeighborNotifyEvent event) {
//        if (event.getWorld().getBlockState(event.getPos().up()).getBlock() == FCRBlocks.RICE_PLANT &&
//            event.getState().getBlock() != Blocks.FARMLAND)
//            event.getWorld().destroyBlock(event.getPos().up(), true);
    }

    @SubscribeEvent
    public void onRegisterRecipe(RegistryEvent.Register<IRecipe> event) {
        FruitEnumLoader loader = FoodCraftReloadedMod.getProxy().getLoaderManager().getLoader(FruitEnumLoader.class).get();

        event.getRegistry().register(new KitchenKnifeRecipe().setRegistryName(FoodCraftReloadedMod.MODID, "kitchen_knife_recipe"));
        event.getRegistry().register(new CakeRecipe().setRegistryName(FoodCraftReloadedMod.MODID, "cake"));
    }

    @SubscribeEvent
    public void onCraftingCake(PlayerEvent.ItemCraftedEvent event) {
        if (event.crafting.getItem() instanceof ItemFruitCake || event.crafting.getItem() instanceof ItemVegetableCake)
            event.craftMatrix.setInventorySlotContents(1, new ItemStack(FCRItems.GLASS_BOTTLE));
    }
}
