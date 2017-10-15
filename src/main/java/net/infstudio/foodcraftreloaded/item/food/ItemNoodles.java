package net.infstudio.foodcraftreloaded.item.food;

import net.infstudio.foodcraftreloaded.util.NameBuilder;
import org.apache.commons.lang3.ArrayUtils;

public class ItemNoodles extends ItemPFood {
    public ItemNoodles(int healAmount, String... name) {
        setHealAmount(healAmount);
        setProperties(0f, 0.4f, 0f, 0f, 0.3f);
        setRegistryName(NameBuilder.buildRegistryName(ArrayUtils.add(name, "noodles")));
        setUnlocalizedName(NameBuilder.buildUnlocalizedName(ArrayUtils.add(name, "noodles")));
    }
}
