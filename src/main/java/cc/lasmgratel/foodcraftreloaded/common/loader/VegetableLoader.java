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

import cc.lasmgratel.foodcraftreloaded.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.block.BlockFluidVegetableJuice;
import cc.lasmgratel.foodcraftreloaded.block.BlockVegetableCake;
import cc.lasmgratel.foodcraftreloaded.fluid.FluidVegetableJuice;
import cc.lasmgratel.foodcraftreloaded.item.food.vegetable.*;
import cc.lasmgratel.foodcraftreloaded.util.NameBuilder;
import cc.lasmgratel.foodcraftreloaded.util.loader.annotation.Load;
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
    private Map<VegetableType, ItemVegetableLiqueur> vegetableLiqueurMap = new EnumMap<>(VegetableType.class);
    private Map<VegetableType, ItemVegetableYogurt> vegetableYogurtMap = new EnumMap<>(VegetableType.class);
    private Map<VegetableType, ItemVegetableCake> vegetableCakeMap = new EnumMap<>(VegetableType.class);
    private Map<VegetableType, ItemVegetable> vegetableMap = new EnumMap<>(VegetableType.class);
    private Map<VegetableType, ItemVegetableJuice> vegetableJuiceMap = new EnumMap<>(VegetableType.class);
    private Map<VegetableType, ItemVegetableSoda> vegetableSodaMap = new EnumMap<>(VegetableType.class);
    private Map<VegetableType, ItemVegetableIcecream> vegetableIcecreamMap = new EnumMap<>(VegetableType.class);

    @Load
    public void loadVegetables() {
        Arrays.stream(VegetableType.values()).forEach(vegetableType -> {
            FluidVegetableJuice fluidVegetableJuice = new FluidVegetableJuice(vegetableType);
            FluidRegistry.registerFluid(fluidVegetableJuice);
            FluidRegistry.addBucketForFluid(fluidVegetableJuice);
            BlockFluidVegetableJuice blockFluidJuice = new BlockFluidVegetableJuice(vegetableType);
            ForgeRegistries.BLOCKS.register(blockFluidJuice);
            fluidJuiceMap.put(vegetableType, blockFluidJuice);
            BlockVegetableCake vegetableCake = new BlockVegetableCake(vegetableType);
            ForgeRegistries.BLOCKS.register(vegetableCake);
            blockFruitCakeMap.put(vegetableType, vegetableCake);

            // Vegetable Cake
            ItemVegetableCake itemVegetableCake = new ItemVegetableCake(vegetableType);
            ForgeRegistries.ITEMS.register(itemVegetableCake);
            vegetableCakeMap.put(vegetableType, itemVegetableCake);

            // Vegetable Liqueur
            ItemVegetableLiqueur vegetableLiqueur = new ItemVegetableLiqueur(vegetableType);
            ForgeRegistries.ITEMS.register(vegetableLiqueur);
            vegetableLiqueurMap.put(vegetableType, vegetableLiqueur);

            // Vegetable Yogurt
            ItemVegetableYogurt vegetableYogurt = new ItemVegetableYogurt(vegetableType);
            ForgeRegistries.ITEMS.register(vegetableYogurt);
            vegetableYogurtMap.put(vegetableType, vegetableYogurt);

            // Vegetable Juice
            ItemVegetableJuice vegetableJuice = new ItemVegetableJuice(vegetableType);
            ForgeRegistries.ITEMS.register(vegetableJuice);
            vegetableJuiceMap.put(vegetableType, vegetableJuice);

            // Vegetable Soda
            ItemVegetableSoda vegetableSoda = new ItemVegetableSoda(vegetableType);
            ForgeRegistries.ITEMS.register(vegetableSoda);
            vegetableSodaMap.put(vegetableType, vegetableSoda);

            // Vegetable Icecream
            ItemVegetableIcecream vegetableIcecream = new ItemVegetableIcecream(vegetableType);
            ForgeRegistries.ITEMS.register(vegetableIcecream);
            vegetableIcecreamMap.put(vegetableType, vegetableIcecream);

            // Vegetable
            ItemVegetable vegetable = new ItemVegetable(vegetableType);
            ForgeRegistries.ITEMS.register(vegetable);
            vegetableMap.put(vegetableType, vegetable);
            MinecraftForge.addGrassSeed(new ItemStack(vegetable, 1), 4);
        });
        for (int i = 0; i < VegetableType.values().length; i++) {
            VegetableType vegetableType = VegetableType.values()[i];
            OreDictionary.registerOre("listAllveggie", new ItemStack(vegetableMap.get(vegetableType)));
            OreDictionary.registerOre(NameBuilder.buildUnlocalizedName("crop", VegetableType.values()[i].toString()), new ItemStack(vegetableMap.get(vegetableType)));
            OreDictionary.registerOre(NameBuilder.buildUnlocalizedName("vegetable", VegetableType.values()[i].toString()), new ItemStack(vegetableMap.get(vegetableType)));
            OreDictionary.registerOre("listAlljuice", new ItemStack(vegetableJuiceMap.get(vegetableType)));
            OreDictionary.registerOre(NameBuilder.buildUnlocalizedName("food", VegetableType.values()[i].toString()) + "juice", new ItemStack(vegetableJuiceMap.get(vegetableType)));
            OreDictionary.registerOre("listAllicecream", new ItemStack(vegetableIcecreamMap.get(vegetableType)));
            OreDictionary.registerOre(NameBuilder.buildUnlocalizedName("food", VegetableType.values()[i].toString()) + "icecream", new ItemStack(vegetableJuiceMap.get(vegetableType)));
        }
    }

    @Load(side = Side.CLIENT)
    public void loadRenders() {
        for (int i = 0; i < VegetableType.values().length; i++) {
            VegetableType vegetableType = VegetableType.values()[i];
            registerRender(vegetableMap.get(vegetableType), 0, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName("vegetable", VegetableType.values()[i].toString())), "inventory"));
            registerRender(vegetableJuiceMap.get(vegetableType), 0, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "juice"), "inventory"));
            registerRender(vegetableSodaMap.get(vegetableType), 0, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "soda"), "inventory"));
            registerRender(vegetableIcecreamMap.get(vegetableType), 0, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "ice_cream"), "inventory"));
            registerRender(vegetableCakeMap.get(vegetableType), 0, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "cake"), "inventory"));
            ModelLoader.setCustomStateMapper(blockFruitCakeMap.get(vegetableType), blockIn -> {
                Map<IBlockState, ModelResourceLocation> map = new HashMap<>();
                map.put(blockIn.getDefaultState().withProperty(BlockCake.BITES, 0), new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "vegetable_cake"), "bites=0"));
                for (int j = 1; j <= 6; j++)
                    map.put(blockIn.getDefaultState().withProperty(BlockCake.BITES, j), new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "vegetable_cake"), "bites=" + j));
                return map;
            });
            registerFluidRender(fluidJuiceMap.get(vegetableType), fluidJuiceMap.get(vegetableType).getRegistryName().getResourcePath());
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
            if (tintIndex == 1 && stack.getItem() instanceof ItemVegetableJuice)
                return ((ItemVegetableJuice) stack.getItem()).getVegetableType().getColor().getRGB();
            else return -1;
        }, vegetableJuiceMap.values().toArray(new Item[0]));
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            if (tintIndex == 1 && stack.getItem() instanceof ItemVegetableSoda)
                return ((ItemVegetableSoda) stack.getItem()).getVegetableType().getColor().getRGB();
            else return -1;
        }, vegetableSodaMap.values().toArray(new Item[0]));
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            if (tintIndex == 1 && stack.getItem() instanceof ItemVegetableIcecream)
                return ((ItemVegetableIcecream) stack.getItem()).getVegetableType().getColor().getRGB();
            else return -1;
        }, vegetableIcecreamMap.values().toArray(new Item[0]));
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            if (tintIndex == 0 && stack.getItem() instanceof ItemVegetableCake)
                return ((ItemVegetableCake) stack.getItem()).getVegetableType().getColor().getRGB();
            else return -1;
        }, vegetableCakeMap.values().toArray(new Item[0]));
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            if (tintIndex == 0 && stack.getItem() instanceof ItemVegetableYogurt)
                return ((ItemVegetableYogurt) stack.getItem()).getVegetableType().getColor().getRGB();
            else return -1;
        }, vegetableYogurtMap.values().toArray(new Item[0]));
    }

    private void registerRender(Item item, int meta, ModelResourceLocation location) {
        ModelLoader.setCustomModelResourceLocation(item, meta, location);
    }
}
