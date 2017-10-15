package net.infstudio.foodcraftreloaded.item.food;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.init.FCRCreativeTabs;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;

public class ItemIcecreams extends FCRItemFood implements IItemColor {
    public ItemIcecreams() {
        super(5, 1.0f, false);
        setRegistryName(FoodCraftReloaded.MODID, "ice_cream");
        setCreativeTab(FCRCreativeTabs.DRINK);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> subItems) {
        for (FruitType fruitType : FruitType.values())
            subItems.add(new ItemStack(this, 1, fruitType.ordinal()));
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull ItemStack stack)
    {
        return I18n.format("item.iceCream", I18n.format("item.fruit" + StringUtils.capitalize(FruitType.values()[stack.getMetadata()].toString())));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int colorMultiplier(@Nonnull ItemStack stack, int tintIndex) {
        if (tintIndex == 1 && stack.getItem() instanceof ItemIcecreams)
            return FruitType.values()[stack.getMetadata()].getColor().getRGB();
        return -1;
    }
}
