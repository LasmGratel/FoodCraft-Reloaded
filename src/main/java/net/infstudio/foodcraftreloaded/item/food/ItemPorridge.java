package net.infstudio.foodcraftreloaded.item.food;

import net.infstudio.foodcraftreloaded.util.NameBuilder;
import org.apache.commons.lang3.ArrayUtils;

public class ItemPorridge extends ItemPFood {
    public ItemPorridge(int healAmount, String... name) {
        setHealAmount(healAmount);
        setProperties(0f, 0.7f, 0f, 0f, 0.2f);
        setRegistryName(NameBuilder.buildRegistryName(ArrayUtils.add(name, "porridge")));
        setUnlocalizedName(NameBuilder.buildUnlocalizedName(ArrayUtils.add(name, "porridge")));
    }
}
