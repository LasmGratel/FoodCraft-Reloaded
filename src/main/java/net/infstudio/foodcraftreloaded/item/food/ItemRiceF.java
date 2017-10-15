package net.infstudio.foodcraftreloaded.item.food;

import net.infstudio.foodcraftreloaded.util.NameBuilder;
import org.apache.commons.lang3.ArrayUtils;

public class ItemRiceF extends ItemPFood {
    public ItemRiceF(int healAmount, String... name) {
        setHealAmount(healAmount);
        setProperties(0f, 0.6f, 0f, 0f, 0.2f);
        setRegistryName(NameBuilder.buildRegistryName(ArrayUtils.add(name, "rice")));
        setUnlocalizedName(NameBuilder.buildUnlocalizedName(ArrayUtils.add(name, "rice")));
    }
}
