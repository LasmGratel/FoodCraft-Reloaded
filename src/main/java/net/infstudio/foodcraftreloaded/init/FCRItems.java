package net.infstudio.foodcraftreloaded.init;

import net.infstudio.foodcraftreloaded.item.food.ItemRice;
import net.infstudio.foodcraftreloaded.utils.loader.annotation.RegItem;
import net.minecraft.item.Item;

public interface FCRItems {
    @RegItem({"glass", "bottle"})
    Item GLASS_BOTTLE = new Item().setCreativeTab(FCRCreativeTabs.BASE).setHasSubtypes(false);

    @RegItem(value = {"original", "ice", "cream"}, oreDict = {"foodIcecream", "listAllicecream"})
    Item ORIGINAL_ICE_CREAM = new Item().setCreativeTab(FCRCreativeTabs.INGREDIENTS).setHasSubtypes(false);

    @RegItem(value = {"rice"}, oreDict = {"cropRice"})
    ItemRice RICE = new ItemRice();
}
