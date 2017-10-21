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
import cc.lasmgratel.foodcraftreloaded.item.food.vegetable.*;
import cc.lasmgratel.foodcraftreloaded.util.NameBuilder;
import cc.lasmgratel.foodcraftreloaded.util.loader.annotation.Load;
import cc.lasmgratel.foodcraftreloaded.block.BlockFluidVegetableJuice;
import cc.lasmgratel.foodcraftreloaded.block.BlockVegetableCake;
import cc.lasmgratel.foodcraftreloaded.fluid.FluidVegetableJuice;
import net.minecraft.block.BlockCake;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class VegetableLoader {
    private Map<VegetableType, FluidVegetableJuice> juiceMap = new EnumMap<>(VegetableType.class);
    private Map<VegetableType, BlockFluidVegetableJuice> fluidJuiceMap = new EnumMap<>(VegetableType.class);
    private Map<VegetableType, BlockVegetableCake> blockFruitCakeMap = new EnumMap<>(VegetableType.class);
    private ItemVegetables vegetables;
    private ItemVegetableJuices juices;
    private ItemVegetableSodas sodas;
    private ItemVegetableIcecreams icecreams;
    private ItemVegetableCakes cakes;

    @Load
    public void loadFruits() {
        vegetables = new ItemVegetables();
        ForgeRegistries.ITEMS.register(vegetables);
        Arrays.stream(VegetableType.values()).forEach(vegetableType -> {
            MinecraftForge.addGrassSeed(new ItemStack(vegetables, 1, vegetableType.ordinal()), 4);
            FluidVegetableJuice fluidVegetableJuice = new FluidVegetableJuice(vegetableType);
            FluidRegistry.registerFluid(fluidVegetableJuice);
            FluidRegistry.addBucketForFluid(fluidVegetableJuice);
            BlockFluidVegetableJuice blockFluidJuice = new BlockFluidVegetableJuice(vegetableType);
            ForgeRegistries.BLOCKS.register(blockFluidJuice);
            fluidJuiceMap.put(vegetableType, blockFluidJuice);
            BlockVegetableCake vegetableCake = new BlockVegetableCake(vegetableType);
            ForgeRegistries.BLOCKS.register(vegetableCake);
            blockFruitCakeMap.put(vegetableType, vegetableCake);
        });
        juices = new ItemVegetableJuices();
        ForgeRegistries.ITEMS.register(juices);
        sodas = new ItemVegetableSodas();
        ForgeRegistries.ITEMS.register(sodas);
        icecreams = new ItemVegetableIcecreams();
        ForgeRegistries.ITEMS.register(icecreams);
        cakes = new ItemVegetableCakes();
        ForgeRegistries.ITEMS.register(cakes);
        for (int i = 0; i < VegetableType.values().length; i++) {
            OreDictionary.registerOre("listAllveggie", new ItemStack(vegetables, 1, i));
            OreDictionary.registerOre(NameBuilder.buildUnlocalizedName("crop", VegetableType.values()[i].toString()), new ItemStack(vegetables, 1, i));
            OreDictionary.registerOre(NameBuilder.buildUnlocalizedName("vegetable", VegetableType.values()[i].toString()), new ItemStack(vegetables, 1, i));
            OreDictionary.registerOre("listAlljuice", new ItemStack(juices, 1, i));
            OreDictionary.registerOre(NameBuilder.buildUnlocalizedName("food", VegetableType.values()[i].toString()) + "juice", new ItemStack(juices, 1, i));
            OreDictionary.registerOre("listAllicecream", new ItemStack(icecreams, 1, i));
            OreDictionary.registerOre(NameBuilder.buildUnlocalizedName("food", VegetableType.values()[i].toString()) + "icecream", new ItemStack(juices, 1, i));
        }
    }

    @Load(side = Side.CLIENT)
    public void loadRenders() {
        for (int i = 0; i < VegetableType.values().length; i++) {
            registerRender(vegetables, i, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName("vegetable", VegetableType.values()[i].toString())), "inventory"));
            registerRender(juices, i, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "juice"), "inventory"));
            registerRender(sodas, i, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "soda"), "inventory"));
            registerRender(icecreams, i, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "ice_cream"), "inventory"));
            registerRender(cakes, i, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "cake"), "inventory"));
            ModelLoader.setCustomStateMapper(blockFruitCakeMap.get(VegetableType.values()[i]), blockIn -> {
                Map<IBlockState, ModelResourceLocation> map = new HashMap<>();
                map.put(blockIn.getDefaultState().withProperty(BlockCake.BITES, 0), new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "vegetable_cake"), "bites=0"));
                for (int j = 1; j <= 6; j++)
                    map.put(blockIn.getDefaultState().withProperty(BlockCake.BITES, j), new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "vegetable_cake"), "bites=" + j));
                return map;
            });
            registerFluidRender(fluidJuiceMap.get(VegetableType.values()[i]), fluidJuiceMap.get(VegetableType.values()[i]).getRegistryName().getResourcePath());
        }
    }

    @SideOnly(Side.CLIENT)
    private void registerFluidRender(BlockFluidBase blockFluid, String blockStateName) {
        final String location = FoodCraftReloaded.MODID + ":" + blockStateName;
        final Item itemFluid = Item.getItemFromBlock(blockFluid);
        ModelLoader.setCustomMeshDefinition(itemFluid, stack -> new ModelResourceLocation(location, "fluid"));
        ModelLoader.setCustomStateMapper(blockFluid, new StateMapperBase() {
            @Nonnull
            @Override
            protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
                return new ModelResourceLocation(location, "fluid");
            }
        });
    }

    @Load(value = LoaderState.POSTINITIALIZATION, side = Side.CLIENT)
    public void loadColors() {
        fluidJuiceMap.values().forEach(block -> Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, worldIn, pos, tintIndex) -> (tintIndex == 0 ? ((BlockFluidVegetableJuice) state.getBlock()).getVegetableType().getColor().getRGB() : -1)));
        blockFruitCakeMap.values().forEach(blockFruitCake ->
            Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(
                (state, worldIn, pos, tintIndex) -> tintIndex == 0 ? ((BlockVegetableCake) state.getBlock()).getVegetableType().getColor().getRGB() : -1, blockFruitCake
            ));
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            if (tintIndex == 1 && stack.getItem() instanceof ItemVegetableJuices)
                return VegetableType.values()[stack.getMetadata()].getColor().getRGB();
            else return -1;
        }, juices);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            if (tintIndex == 1 && stack.getItem() instanceof ItemVegetableSodas)
                return VegetableType.values()[stack.getMetadata()].getColor().getRGB();
            else return -1;
        }, sodas);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            if (tintIndex == 1 && stack.getItem() instanceof ItemVegetableIcecreams)
                return VegetableType.values()[stack.getMetadata()].getColor().getRGB();
            else return -1;
        }, icecreams);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            if (tintIndex == 0 && stack.getItem() instanceof ItemVegetableCakes)
                return VegetableType.values()[stack.getMetadata()].getColor().getRGB();
            else return -1;
        }, cakes);
    }

    private void registerRender(Item item, int meta, ModelResourceLocation location) {
        ModelLoader.setCustomModelResourceLocation(item, meta, location);
    }

    public ItemVegetables getVegetables() {
        return vegetables;
    }

    public ItemVegetableJuices getJuices() {
        return juices;
    }

    public ItemVegetableSodas getSodas() {
        return sodas;
    }

    public ItemVegetableIcecreams getIcecreams() {
        return icecreams;
    }

    public ItemVegetableCakes getCakes() {
        return cakes;
    }

    public Map<VegetableType, FluidVegetableJuice> getJuiceMap() {
        return juiceMap;
    }

    public Map<VegetableType, BlockFluidVegetableJuice> getFluidJuiceMap() {
        return fluidJuiceMap;
    }

    public Map<VegetableType, BlockVegetableCake> getBlockFruitCakeMap() {
        return blockFruitCakeMap;
    }
}
