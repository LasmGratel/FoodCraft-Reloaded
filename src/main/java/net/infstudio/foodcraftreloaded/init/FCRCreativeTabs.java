package net.infstudio.foodcraftreloaded.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;

import static net.infstudio.foodcraftreloaded.FoodCraftReloaded.MODID;

public interface FCRCreativeTabs {
    CreativeTabs BASE = new CreativeTabs(CreativeTabs.getNextID(), MODID + ".base") {
        @Nonnull
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Items.GOLDEN_APPLE);
        }
    };

    CreativeTabs INGREDIENTS = new CreativeTabs(CreativeTabs.getNextID(), MODID + ".ingredient") {
        @Nonnull
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Items.POTATO);
        }
    };

    CreativeTabs DRINK = new CreativeTabs(CreativeTabs.getNextID(), MODID + ".drink") {
        @Nonnull
        @Override
        public ItemStack getTabIconItem() {
            return OreDictionary.getOres("foodLemonjuice").get(0);
        }
    };
}
