package cc.lasmgratel.foodcraftreloaded.api.config;

import javax.annotation.Nullable;

public interface Config {
    @Nullable
    default Class<?> getConfiguredClass() {
        return null;
    }
}
