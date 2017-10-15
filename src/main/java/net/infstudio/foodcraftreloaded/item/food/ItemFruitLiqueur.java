package net.infstudio.foodcraftreloaded.item.food;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.util.NameBuilder;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;

public class ItemFruitLiqueur extends ItemLiqueur {
    private FruitType type;

    public ItemFruitLiqueur(FruitType type) {
        super(5);
        setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(type.toString(), "liqueur"));
        setMaxDamage(0);
        setHasSubtypes(false);
        this.type = type;
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return I18n.format("item.fruitLiqueur", I18n.format("item.fruit" + StringUtils.capitalize(type.toString())));
    }
}
