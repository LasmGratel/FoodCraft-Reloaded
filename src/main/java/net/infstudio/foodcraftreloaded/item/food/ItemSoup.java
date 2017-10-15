package net.infstudio.foodcraftreloaded.item.food;

import net.infstudio.foodcraftreloaded.util.NameBuilder;
import org.apache.commons.lang3.ArrayUtils;

public class ItemSoup extends ItemPFood {
    public ItemSoup(int healAmount, String... name) {
        setHealAmount(healAmount);
        setProperties(0f, 0.3f, 0f, 0f, 0.2f);
        setRegistryName(NameBuilder.buildRegistryName(ArrayUtils.add(name, "soup")));
        setUnlocalizedName(NameBuilder.buildUnlocalizedName(ArrayUtils.add(name, "soup")));
    }
}
