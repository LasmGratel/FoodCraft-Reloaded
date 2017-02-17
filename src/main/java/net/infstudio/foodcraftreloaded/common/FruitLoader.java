package net.infstudio.foodcraftreloaded.common;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.block.BlockFruitLeaves;
import net.infstudio.foodcraftreloaded.item.food.*;
import net.infstudio.foodcraftreloaded.utils.NameBuilder;
import net.infstudio.foodcraftreloaded.utils.loader.annotation.Load;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class FruitLoader {
    private Map<EnumFruitType, BlockFruitLeaves> leavesMap = new EnumMap<>(EnumFruitType.class);
    private ItemFruits fruits;
    private ItemJuices juices;
    private ItemSodas sodas;
    private ItemIcecreams icecreams;
    private ItemCakes cakes;

    @Load
    public void loadJuices() {
        fruits = new ItemFruits();
        GameRegistry.register(fruits);
        Arrays.stream(EnumFruitType.values()).forEach(fruitType -> {
            BlockFruitLeaves fruitLeaves = new BlockFruitLeaves(fruitType);
            GameRegistry.register(fruitLeaves);
            leavesMap.put(fruitType, fruitLeaves);
        });
        juices = new ItemJuices();
        GameRegistry.register(juices);
        sodas = new ItemSodas();
        GameRegistry.register(sodas);
        icecreams = new ItemIcecreams();
        GameRegistry.register(icecreams);
        cakes = new ItemCakes();
        GameRegistry.register(cakes);
        for (int i = 0; i < EnumFruitType.values().length; i++) {
            OreDictionary.registerOre("listAllfruit", new ItemStack(fruits, 1, i));
            OreDictionary.registerOre(NameBuilder.buildUnlocalizedName("crop", EnumFruitType.values()[i].toString()), new ItemStack(fruits, 1, i));
            OreDictionary.registerOre(NameBuilder.buildUnlocalizedName("fruit", EnumFruitType.values()[i].toString()), new ItemStack(fruits, 1, i));
            OreDictionary.registerOre("listAlljuice", new ItemStack(juices, 1, i));
            OreDictionary.registerOre(NameBuilder.buildUnlocalizedName("food", EnumFruitType.values()[i].toString()) + "juice", new ItemStack(juices, 1, i));
            OreDictionary.registerOre("listAllicecream", new ItemStack(icecreams, 1, i));
            OreDictionary.registerOre(NameBuilder.buildUnlocalizedName("food", EnumFruitType.values()[i].toString()) + "icecream", new ItemStack(juices, 1, i));
        }
    }

    @Load(side = Side.CLIENT)
    public void loadRenders() {
        for (int i = 0; i < EnumFruitType.values().length; i++) {
            registerRender(fruits, i, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName("fruit", EnumFruitType.values()[i].toString())), "inventory"));
            registerRender(juices, i, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "juice"), "inventory"));
            registerRender(sodas, i, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "soda"), "inventory"));
            registerRender(icecreams, i, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "ice_cream"), "inventory"));
            registerRender(cakes, i, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "cake"), "inventory"));
            ModelLoader.setCustomStateMapper(leavesMap.get(EnumFruitType.values()[i]), blockIn -> Collections.singletonMap(blockIn.getDefaultState(), new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "fruit_leaves"), "normal")));
            registerRender(Item.getItemFromBlock(leavesMap.get(EnumFruitType.values()[i])), 0, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "fruit_leaves"), "inventory"));
        }
    }

    @Load(value = LoaderState.POSTINITIALIZATION, side = Side.CLIENT)
    public void loadColors() {
        leavesMap.values().forEach((leaves) -> Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, worldIn, pos, tintIndex) -> tintIndex == 0 ? BiomeColorHelper.getFoliageColorAtPos(worldIn, pos) : tintIndex == 1 ? ((BlockFruitLeaves) state.getBlock()).getFruitType().getColor().getRGB() : -1, leaves));
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> tintIndex == 1 && stack.getItem() instanceof ItemJuices ? EnumFruitType.values()[stack.getMetadata()].getColor().getRGB() : -1, juices);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> tintIndex == 1 && stack.getItem() instanceof ItemSodas ? EnumFruitType.values()[stack.getMetadata()].getColor().getRGB() : -1, sodas);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> tintIndex == 1 && stack.getItem() instanceof ItemIcecreams ? EnumFruitType.values()[stack.getMetadata()].getColor().getRGB() : -1, icecreams);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> tintIndex == 1 && stack.getItem() instanceof ItemCakes ? EnumFruitType.values()[stack.getMetadata()].getColor().getRGB() : -1, cakes);
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

    public Map<EnumFruitType, BlockFruitLeaves> getLeavesMap() {
        return leavesMap;
    }
}
