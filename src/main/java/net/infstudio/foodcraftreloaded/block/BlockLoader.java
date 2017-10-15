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

package net.infstudio.foodcraftreloaded.block;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.block.tileentity.TileEntityDrinkMachine;
import net.infstudio.foodcraftreloaded.init.FCRBlocks;
import net.infstudio.foodcraftreloaded.util.NameBuilder;
import net.infstudio.foodcraftreloaded.util.loader.annotation.Load;
import net.infstudio.foodcraftreloaded.util.loader.annotation.RegBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

public class BlockLoader {
    @Load
    public void registerBlocks() {
        for (Field field : FCRBlocks.class.getFields()) {
            field.setAccessible(true);
            RegBlock anno = field.getAnnotation(RegBlock.class);
            if (anno==null) continue;

            try {
                Block block = (Block) field.get(null);
                ForgeRegistries.BLOCKS.register(block.setRegistryName(NameBuilder.buildRegistryName(anno.value())).setUnlocalizedName(NameBuilder.buildUnlocalizedName(anno.value())));

                //Register item block.
                Class<? extends ItemBlock> itemClass = anno.itemClass();
                Constructor<? extends ItemBlock> con = itemClass.getConstructor(Block.class);
                con.setAccessible(true);
                ForgeRegistries.ITEMS.register(con.newInstance(block).setRegistryName(block.getRegistryName()).setUnlocalizedName(block.getUnlocalizedName()));

                Arrays.asList(anno.oreDict()).forEach(s -> OreDictionary.registerOre(s, block));
            } catch (Exception e) {
                FoodCraftReloaded.getLogger().warn("Un-able to register block " + field.toGenericString(), e);
            }
        }
    }

    @Load
    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityDrinkMachine.class, "drink_machine");
    }

    @Load(side = Side.CLIENT)
    public void registerRenders() {
        for (Field field : FCRBlocks.class.getFields()) {
            field.setAccessible(true);
            RegBlock anno = field.getAnnotation(RegBlock.class);
            if (anno==null) continue;

            try {
                Block block = (Block) field.get(null);
                registerRender(block,0);
            } catch (Exception e) {
                FoodCraftReloaded.getLogger().warn("Un-able to register block " + field.toGenericString(), e);
            }
        }
    }

    private void registerRender(Block block, int meta)
    {
        Item item = Item.getItemFromBlock(block);
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}
