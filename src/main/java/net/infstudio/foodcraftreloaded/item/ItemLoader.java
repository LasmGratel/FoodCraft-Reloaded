package net.infstudio.foodcraftreloaded.item;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.init.FCRItems;
import net.infstudio.foodcraftreloaded.util.NameBuilder;
import net.infstudio.foodcraftreloaded.util.loader.annotation.Load;
import net.infstudio.foodcraftreloaded.util.loader.annotation.RegItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.reflect.Field;
import java.util.Arrays;

public class ItemLoader {
    @Load
    public void registerItems() {
        for (Field field : FCRItems.class.getFields()) {
            field.setAccessible(true);
            try {
                RegItem annoItem = field.getAnnotation(RegItem.class);
                if (annoItem == null)
                    continue;

                Item item = (Item) field.get(null);
                ForgeRegistries.ITEMS.register(item.setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(annoItem.value())).setUnlocalizedName(NameBuilder.buildUnlocalizedName(annoItem.value())));

                Arrays.stream(annoItem.oreDict()).forEach(s -> OreDictionary.registerOre(s, item));
            } catch (Throwable e) {
                FoodCraftReloaded.getLogger().warn("Un-able to register item " + field.toGenericString(), e);
            }
        }
    }

    @Load(side = Side.CLIENT)
    public void registerRenders() {
        for (Field field : FCRItems.class.getFields()) {
            field.setAccessible(true);
            RegItem anno = field.getAnnotation(RegItem.class);
            try {
                if (anno == null)
                    continue;

                Item item = (Item) field.get(null);
                if (item.getHasSubtypes()) {
                    if (item instanceof IMetadatable) {
                        for (int i = 0; i < ((IMetadatable) item).getMaxMetadata(); i++)
                            registerRender(item, i);
                    }
                } else {
                    registerRender(item, 0);
                }
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
