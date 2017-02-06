package net.infstudio.foodcraftreloaded.item;

import net.infstudio.foodcraftreloaded.init.FCRCreativeTabs;
import net.minecraft.item.Item;

/**
 * Base item used by this mod.
 */
public class ItemBase extends Item {
    public ItemBase() {
        setCreativeTab(FCRCreativeTabs.BASE);
    }
}
