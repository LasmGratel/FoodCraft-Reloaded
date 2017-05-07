package net.infstudio.foodcraftreloaded.item.food;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.init.FCRFoods;
import net.infstudio.foodcraftreloaded.utils.NameBuilder;
import net.infstudio.foodcraftreloaded.utils.loader.annotation.Load;
import net.infstudio.foodcraftreloaded.utils.loader.annotation.RegFood;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
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
                RegFood anno = field.getAnnotation(RegFood.class);
                if (anno == null) {
                    if (field.getDeclaringClass().equals(ItemPorridge.class)) {
                        ItemPorridge porridge = (ItemPorridge) field.get(null);
                        GameRegistry.register(porridge);
                        OreDictionary.registerOre("foodPorridge" + porridge.getUnlocalizedName().substring(0, porridge.getUnlocalizedName().lastIndexOf("Porridge")), porridge);
                        OreDictionary.registerOre("listAllporridge", porridge);
                        OreDictionary.registerOre("listAllfoods", porridge);
                    }
                    continue;
                }

                ItemPFood item = (ItemPFood) field.get(null);
                item.setProperties(anno.modifier());
                GameRegistry.register(item.setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(anno.name())).setUnlocalizedName(NameBuilder.buildUnlocalizedName(anno.name())));
                Arrays.asList(anno.oreDict()).forEach(s -> OreDictionary.registerOre(s, item));
                OreDictionary.registerOre("listAllfoods", item);
            } catch (IllegalAccessException | NullPointerException e) {
                FoodCraftReloaded.getLogger().warn("Un-able to register food " + field.toGenericString(), e);
            }
        }
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

    private void registerRender(Item item, int meta)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
