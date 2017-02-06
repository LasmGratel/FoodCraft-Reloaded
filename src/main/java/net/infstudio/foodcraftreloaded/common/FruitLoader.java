package net.infstudio.foodcraftreloaded.common;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.item.food.*;
import net.infstudio.foodcraftreloaded.utils.NameBuilder;
import net.infstudio.foodcraftreloaded.utils.loader.annotation.Load;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;

public class FruitLoader {
    private ItemFruits fruits;
    private ItemJuices juices;
    private ItemSodas sodas;
    private ItemIcecreams icecreams;

    @Load
    public void loadJuices() {
        fruits = new ItemFruits();
        GameRegistry.register(fruits);
        juices = new ItemJuices();
        GameRegistry.register(juices);
        sodas = new ItemSodas();
        GameRegistry.register(sodas);
        icecreams = new ItemIcecreams();
        GameRegistry.register(icecreams);
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
        }
    }

    @Load(value = LoaderState.POSTINITIALIZATION, side = Side.CLIENT)
    public void loadColors() {
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(juices, juices);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(sodas, sodas);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(icecreams, icecreams);
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
}
