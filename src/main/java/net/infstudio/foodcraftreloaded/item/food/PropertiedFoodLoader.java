package net.infstudio.foodcraftreloaded.item.food;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.init.food.FCRFoods;
import net.infstudio.foodcraftreloaded.util.NameBuilder;
import net.infstudio.foodcraftreloaded.util.loader.annotation.Load;
import net.infstudio.foodcraftreloaded.util.loader.annotation.RegFood;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.reflect.Field;
import java.util.Arrays;

public class PropertiedFoodLoader {
    @Load
    public void registerFoods() {
        for (Field field : FCRFoods.class.getFields()) {
            field.setAccessible(true);
            try {
                RegFood anno = field.getDeclaredAnnotation(RegFood.class);
                if (anno == null) {
                    Object food = field.get(null);
                    if (food instanceof ItemPorridge)
                        registerPorridge((ItemPorridge) food);
                    else if (food instanceof ItemSoup)
                        registerSoup((ItemSoup) food);
                    else if (food instanceof ItemNoodles)
                        registerNoodles((ItemNoodles) food);
                    continue;
                }

                ItemPFood item = (ItemPFood) field.get(null);
                item.setProperties(anno.modifier());
                ForgeRegistries.ITEMS.register(item.setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(anno.name())).setUnlocalizedName(NameBuilder.buildUnlocalizedName(anno.name())));
                Arrays.asList(anno.oreDict()).forEach(s -> OreDictionary.registerOre(s, item));
                OreDictionary.registerOre("listAllfoods", item);
            } catch (IllegalAccessException | NullPointerException e) {
                FoodCraftReloaded.getLogger().warn("Un-able to register food " + field.toGenericString(), e);
            }
        }
    }

    private void registerPorridge(ItemPorridge porridge) {
        ForgeRegistries.ITEMS.register(porridge);
        OreDictionary.registerOre("foodPorridge" + porridge.getUnlocalizedName().substring(0, porridge.getUnlocalizedName().lastIndexOf("Porridge")), porridge);
        OreDictionary.registerOre("listAllporridge", porridge);
        OreDictionary.registerOre("listAllfoods", porridge);
    }

    private void registerSoup(ItemSoup soup) {
        ForgeRegistries.ITEMS.register(soup);
        OreDictionary.registerOre("foodSoup" + soup.getUnlocalizedName().substring(0, soup.getUnlocalizedName().lastIndexOf("Soup")), soup);
        OreDictionary.registerOre("listAllsoup", soup);
        OreDictionary.registerOre("listAllfoods", soup);
    }

    private void registerNoodles(ItemNoodles noodles) {
        ForgeRegistries.ITEMS.register(noodles);
        OreDictionary.registerOre("foodNoodles" + noodles.getUnlocalizedName().substring(0, noodles.getUnlocalizedName().lastIndexOf("Noodles")), noodles);
        OreDictionary.registerOre("listAllnoodles", noodles);
        OreDictionary.registerOre("listAllfoods", noodles);
    }

    private void registerRice(ItemRiceF rice) {
        ForgeRegistries.ITEMS.register(rice);
        OreDictionary.registerOre("foodRice" + rice.getUnlocalizedName().substring(0, rice.getUnlocalizedName().lastIndexOf("Rice")), rice);
        OreDictionary.registerOre("listAllrice", rice);
        OreDictionary.registerOre("listAllfoods", rice);
    }

    @Load(side = Side.CLIENT)
    public void registerRenders() {
        for (Field field : FCRFoods.class.getFields()) {
            field.setAccessible(true);
            RegFood anno = field.getAnnotation(RegFood.class);
            try {
                if (anno == null)
                    continue;

                ItemPFood item = (ItemPFood) field.get(null);
                registerRender(item, 0);
            } catch (Exception e) {
                FoodCraftReloaded.getLogger().warn("Un-able to register item " + field.toGenericString(), e);
            }
        }
    }

    private void registerRender(Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
