package net.infstudio.foodcraftreloaded.item.tool;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.init.FCRCreativeTabs;
import net.infstudio.foodcraftreloaded.util.NameBuilder;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ItemKitchenKnife extends Item implements IItemColor {
    private EnumKitchenKnifeType type;

    public ItemKitchenKnife(EnumKitchenKnifeType type) {
        this.type = type;
        setMaxDamage(type.getMaxDamage());
        setMaxStackSize(1);
        setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName("kitchen", "knife", type.toString()));
        setUnlocalizedName(NameBuilder.buildUnlocalizedName("kitchen", "knife", type.toString()));
        setCreativeTab(FCRCreativeTabs.BASE);
    }

    public EnumKitchenKnifeType getType() {
        return type;
    }

    public void setType(EnumKitchenKnifeType type) {
        this.type = type;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getColorFromItemstack(@Nonnull ItemStack stack, int tintIndex) {
        if (tintIndex == 0 && stack.getItem() instanceof ItemKitchenKnife)
            return ((ItemKitchenKnife) stack.getItem()).getType().getColor().getRGB();
        return -1;
    }
}
