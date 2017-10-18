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

package net.infstudio.foodcraftreloaded.common;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.block.BlockFluidJuice;
import net.infstudio.foodcraftreloaded.block.BlockFruitCake;
import net.infstudio.foodcraftreloaded.block.BlockFruitLeaves;
import net.infstudio.foodcraftreloaded.block.BlockFruitSapling;
import net.infstudio.foodcraftreloaded.fluid.FluidJuice;
import net.infstudio.foodcraftreloaded.init.FCRCreativeTabs;
import net.infstudio.foodcraftreloaded.item.food.*;
import net.infstudio.foodcraftreloaded.util.NameBuilder;
import net.infstudio.foodcraftreloaded.util.loader.annotation.Load;
import net.infstudio.foodcraftreloaded.worldgen.BaseTreeGenerator;
import net.minecraft.block.BlockCake;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeColorHelper;
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
import java.util.*;

public class FruitLoader {
    private Map<FruitType, BlockFruitLeaves> leavesMap = new EnumMap<>(FruitType.class);
    private Map<FruitType, FluidJuice> juiceMap = new EnumMap<>(FruitType.class);
    private Map<FruitType, BlockFruitSapling> saplingMap = new EnumMap<>(FruitType.class);
    private Map<FruitType, BaseTreeGenerator> generatorTreeMap = new EnumMap<>(FruitType.class);
    private Map<FruitType, BlockFluidJuice> fluidJuiceMap = new EnumMap<>(FruitType.class);
    private Map<FruitType, BlockFruitCake> blockFruitCakeMap = new EnumMap<>(FruitType.class);
    private Map<FruitType, ItemFruitLiqueur> fruitLiqueurMap = new EnumMap<>(FruitType.class);
    private Map<FruitType, ItemFruitYogurt> fruitYogurtMap = new EnumMap<>(FruitType.class);
    private ItemFruits fruits;
    private ItemJuices juices;
    private ItemSodas sodas;
    private ItemIcecreams icecreams;
    private ItemCakes cakes;

    @Load
    public void loadFruits() {
        fruits = new ItemFruits();
        ForgeRegistries.ITEMS.register(fruits);
        Arrays.stream(FruitType.values()).forEach(fruitType -> {
            MinecraftForge.addGrassSeed(new ItemStack(fruits, 1, fruitType.ordinal()), 4);
            BlockFruitLeaves fruitLeaves = new BlockFruitLeaves(fruitType);
            ForgeRegistries.BLOCKS.register(fruitLeaves);
            FluidJuice fluidJuice = new FluidJuice(fruitType);
            FluidRegistry.registerFluid(fluidJuice);
            FluidRegistry.addBucketForFluid(fluidJuice);
            BlockFluidJuice blockFluidJuice = new BlockFluidJuice(fruitType);
            ForgeRegistries.BLOCKS.register(blockFluidJuice);
            fluidJuiceMap.put(fruitType, blockFluidJuice);
            BlockFruitSapling sapling = new BlockFruitSapling(fruitType);
            ForgeRegistries.BLOCKS.register(sapling);
            ForgeRegistries.ITEMS.register(new ItemBlock(sapling).setUnlocalizedName(NameBuilder.buildUnlocalizedName(fruitType.toString(), "sapling")).setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(fruitType.toString(), "sapling")).setCreativeTab(FCRCreativeTabs.BASE));
            leavesMap.put(fruitType, fruitLeaves);
            juiceMap.put(fruitType, fluidJuice);
            saplingMap.put(fruitType, sapling);
            generatorTreeMap.put(fruitType, new BaseTreeGenerator.Builder().minHeight(4).maxHeight(7)
                .log(BlockPlanks.EnumType.OAK)
                .altLeaves(BlockPlanks.EnumType.JUNGLE).leaves(fruitLeaves.getDefaultState()).build());
            BlockFruitCake fruitCake = new BlockFruitCake(fruitType);
            ForgeRegistries.BLOCKS.register(fruitCake);
            blockFruitCakeMap.put(fruitType, fruitCake);
            ItemFruitLiqueur fruitLiqueur = new ItemFruitLiqueur(fruitType);
            ForgeRegistries.ITEMS.register(fruitLiqueur);
            fruitLiqueurMap.put(fruitType, fruitLiqueur);
            ItemFruitYogurt fruitYogurt = new ItemFruitYogurt(fruitType);
            ForgeRegistries.ITEMS.register(fruitYogurt);
            fruitYogurtMap.put(fruitType, fruitYogurt);
        });
        juices = new ItemJuices();
        ForgeRegistries.ITEMS.register(juices);
        sodas = new ItemSodas();
        ForgeRegistries.ITEMS.register(sodas);
        icecreams = new ItemIcecreams();
        ForgeRegistries.ITEMS.register(icecreams);
        cakes = new ItemCakes();
        ForgeRegistries.ITEMS.register(cakes);
        for (int i = 0; i < FruitType.values().length; i++) {
            ItemStack fruit = new ItemStack(fruits, 1, i);
            ItemStack juice = new ItemStack(juices, 1, i);
            ItemStack icecream = new ItemStack(icecreams, 1, i);
            ItemStack liqueur = new ItemStack(fruitLiqueurMap.get(FruitType.values()[i]));
            ItemStack yogurt = new ItemStack(fruitYogurtMap.get(FruitType.values()[i]));
            OreDictionary.registerOre(NameBuilder.buildUnlocalizedName("crop", FruitType.values()[i].toString()), fruit);
            OreDictionary.registerOre(NameBuilder.buildUnlocalizedName("fruit", FruitType.values()[i].toString()), fruit);
            OreDictionary.registerOre(NameBuilder.buildUnlocalizedName("food", FruitType.values()[i].toString()) + "juice", juice);
            OreDictionary.registerOre(NameBuilder.buildUnlocalizedName("food", FruitType.values()[i].toString()) + "icecream", juice);
            OreDictionary.registerOre(NameBuilder.buildUnlocalizedName("food", FruitType.values()[i].toString()) + "liqueur", liqueur);
            OreDictionary.registerOre(NameBuilder.buildUnlocalizedName("food", FruitType.values()[i].toString()) + "yogurt", yogurt);
            OreDictionary.registerOre("listAllfruit", fruit);
            OreDictionary.registerOre("listAlljuice", juice);
            OreDictionary.registerOre("listAllicecream", icecream);
            OreDictionary.registerOre("listAllliqueur", liqueur);
            OreDictionary.registerOre("listAllyogurt", yogurt);
            OreDictionary.registerOre("listAllfoods", fruit);
            OreDictionary.registerOre("listAllfoods", juice);
            OreDictionary.registerOre("listAllfoods", icecream);
            OreDictionary.registerOre("listAllfoods", liqueur);
            OreDictionary.registerOre("listAllfoods", yogurt);
        }
    }

    @Load(side = Side.CLIENT)
    public void loadRenders() {
        for (int i = 0; i < FruitType.values().length; i++) {
            Item liqueur = fruitLiqueurMap.get(FruitType.values()[i]);
            Item yogurt = fruitYogurtMap.get(FruitType.values()[i]);
            registerRender(liqueur, 0, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName("fruit", FruitType.values()[i].toString(), "liqueur")), "inventory"));
            registerRender(yogurt, 0, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "yogurt"), "inventory"));
            registerRender(fruits, i, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName("fruit", FruitType.values()[i].toString())), "inventory"));
            registerRender(juices, i, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "juice"), "inventory"));
            registerRender(sodas, i, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "soda"), "inventory"));
            registerRender(icecreams, i, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "ice_cream"), "inventory"));
            registerRender(cakes, i, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "cake"), "inventory"));
            ModelLoader.setCustomStateMapper(leavesMap.get(FruitType.values()[i]), blockIn -> Collections.singletonMap(blockIn.getDefaultState(), new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "fruit_leaves"), "normal")));
            registerRender(Item.getItemFromBlock(leavesMap.get(FruitType.values()[i])), 0, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "fruit_leaves"), "inventory"));
            ModelLoader.setCustomStateMapper(saplingMap.get(FruitType.values()[i]), blockIn -> Collections.singletonMap(blockIn.getDefaultState(), new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "fruit_sapling"), "normal")));
            registerRender(Item.getItemFromBlock(saplingMap.get(FruitType.values()[i])), 0, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "fruit_sapling"), "inventory"));
            ModelLoader.setCustomStateMapper(blockFruitCakeMap.get(FruitType.values()[i]), blockIn -> {
                Map<IBlockState, ModelResourceLocation> map = new HashMap<>();
                map.put(blockIn.getDefaultState().withProperty(BlockCake.BITES, 0), new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "fruit_cake"), "bites=0"));
                for (int j = 1; j <= 6; j++)
                    map.put(blockIn.getDefaultState().withProperty(BlockCake.BITES, j), new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "fruit_cake"), "bites=" + j));
                return map;
            });
            registerFluidRender(fluidJuiceMap.get(FruitType.values()[i]), fluidJuiceMap.get(FruitType.values()[i]).getRegistryName().getResourcePath());
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
        leavesMap.values().forEach((leaves) -> Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, worldIn, pos, tintIndex) -> tintIndex == 0 ? BiomeColorHelper.getFoliageColorAtPos(worldIn, pos) : tintIndex == 1 ? ((BlockFruitLeaves) state.getBlock()).getFruitType().getColor().getRGB() : -1, leaves));
        fluidJuiceMap.values().forEach(block -> Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, worldIn, pos, tintIndex) -> (tintIndex == 0 ? ((BlockFluidJuice) state.getBlock()).getFruitType().getColor().getRGB() : -1)));
        blockFruitCakeMap.values().forEach(blockFruitCake ->
            Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(
                (state, worldIn, pos, tintIndex) -> tintIndex == 0 ? ((BlockFruitCake) state.getBlock()).getFruitType().getColor().getRGB() : -1, blockFruitCake
            ));
        saplingMap.values().forEach((sapling) -> Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            if (stack.getItem() == Item.getItemFromBlock(sapling) && tintIndex == 0)
                return FruitType.values()[stack.getMetadata()].getColor().getRGB();
            return -1;
        }, sapling));
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            if (tintIndex == 1 && stack.getItem() instanceof ItemJuices)
                return FruitType.values()[stack.getMetadata()].getColor().getRGB();
            else return -1;
        }, juices);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            if (tintIndex == 1 && stack.getItem() instanceof ItemSodas)
                return FruitType.values()[stack.getMetadata()].getColor().getRGB();
            else return -1;
        }, sodas);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            if (tintIndex == 1 && stack.getItem() instanceof ItemIcecreams)
                return FruitType.values()[stack.getMetadata()].getColor().getRGB();
            else return -1;
        }, icecreams);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            if (tintIndex == 0 && stack.getItem() instanceof ItemCakes)
                return FruitType.values()[stack.getMetadata()].getColor().getRGB();
            else return -1;
        }, cakes);
    }

    private void registerRender(Item item, int meta, ModelResourceLocation location) {
        ModelLoader.setCustomModelResourceLocation(item, meta, location);
    }

    public ItemFruits getFruits() {
        return fruits;
    }

    public ItemJuices getJuices() {
        return juices;
    }

    public ItemSodas getSodas() {
        return sodas;
    }

    public ItemIcecreams getIcecreams() {
        return icecreams;
    }

    public ItemCakes getCakes() {
        return cakes;
    }

    public Map<FruitType, BlockFruitLeaves> getLeavesMap() {
        return leavesMap;
    }

    public Map<FruitType, FluidJuice> getJuiceMap() {
        return juiceMap;
    }

    public Map<FruitType, BlockFruitSapling> getSaplingMap() {
        return saplingMap;
    }

    public Map<FruitType, BaseTreeGenerator> getGeneratorTreeMap() {
        return generatorTreeMap;
    }

    public Map<FruitType, BlockFluidJuice> getFluidJuiceMap() {
        return fluidJuiceMap;
    }
}
