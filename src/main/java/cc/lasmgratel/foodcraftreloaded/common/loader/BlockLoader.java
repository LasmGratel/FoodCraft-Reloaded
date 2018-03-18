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

import cc.lasmgratel.foodcraftreloaded.api.init.FCRBlocks;
import cc.lasmgratel.foodcraftreloaded.client.util.masking.CustomModelMasking;
import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.common.block.tileentity.TileEntityDrinkMachine;
import cc.lasmgratel.foodcraftreloaded.common.block.tileentity.TileEntityPressureCooker;
import cc.lasmgratel.foodcraftreloaded.common.block.tileentity.TileEntitySmeltingDrinkMachine;
import cc.lasmgratel.foodcraftreloaded.common.loader.register.RegisterManager;
import cc.lasmgratel.foodcraftreloaded.common.util.NameBuilder;
import cc.lasmgratel.foodcraftreloaded.common.util.loader.annotation.Load;
import cc.lasmgratel.foodcraftreloaded.common.util.loader.annotation.RegBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

public class BlockLoader {
    @Load(value = LoaderState.CONSTRUCTING)
    public void registerBlocks() {
        for (Field field : FCRBlocks.class.getFields()) {
            field.setAccessible(true);
            RegBlock anno = field.getAnnotation(RegBlock.class);
            if (anno==null) continue;

            try {
                Block block = (Block) field.get(null);
                RegisterManager.getInstance().putRegister(block.setRegistryName(NameBuilder.buildRegistryName(anno.value())).setUnlocalizedName(NameBuilder.buildUnlocalizedName(anno.value())));

                // Register item block.
                Class<? extends ItemBlock> itemClass = anno.itemClass();
                Constructor<? extends ItemBlock> con = itemClass.getConstructor(Block.class);
                con.setAccessible(true);
                RegisterManager.getInstance().putRegister(con.newInstance(block).setRegistryName(block.getRegistryName()).setUnlocalizedName(block.getUnlocalizedName()));

                Arrays.asList(anno.oreDict()).forEach(s -> OreDictionary.registerOre(s, block));
            } catch (Exception e) {
                FoodCraftReloaded.getLogger().warn("Un-able to register block " + field.toGenericString(), e);
            }
        }
    }

    @Load
    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityDrinkMachine.class, "drink_machine");
        GameRegistry.registerTileEntity(TileEntityPressureCooker.class, "pressure_cooker");
        GameRegistry.registerTileEntity(TileEntitySmeltingDrinkMachine.class, "smelting_drink_machine");
    }

    @Load(side = Side.CLIENT)
    public void registerRenders() {
        for (Field field : FCRBlocks.class.getFields()) {
            field.setAccessible(true);
            RegBlock anno = field.getAnnotation(RegBlock.class);
            if (anno==null) continue;

            try {
                Block block = (Block) field.get(null);
                if (block instanceof CustomModelMasking) {
                    ModelLoader.setCustomStateMapper(block, b -> ((CustomModelMasking) b).getStateModelLocations());
                    if (((CustomModelMasking) block).getModelLocation() != null) {
                        registerRender(Item.getItemFromBlock(block), 0, ((CustomModelMasking) block).getModelLocation());
                        return;
                    }
                }
                registerRender(block,0);
            } catch (Exception e) {
                FoodCraftReloaded.getLogger().warn("Un-able to register block " + field.toGenericString(), e);
            }
        }
    }

    private void registerRender(Item item, int meta, ModelResourceLocation location) {
        ModelLoader.setCustomModelResourceLocation(item, meta, location);
    }

    private void registerRender(Block block, int meta) {
        Item item = Item.getItemFromBlock(block);
        registerRender(item, meta, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}
