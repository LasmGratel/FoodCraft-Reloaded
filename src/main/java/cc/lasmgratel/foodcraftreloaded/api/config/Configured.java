package cc.lasmgratel.foodcraftreloaded.api.config;

import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;

public interface Configured<T extends Config> {
    default T getConfig() {
        return FoodCraftReloaded.getConfigManager().getOrCreateConfig(getConfigClass(), (Class<? extends Configured<?>>) getClass());
    }

    Class<T> getConfigClass() ;
}
