package cc.lasmgratel.foodcraftreloaded.api.config;

import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigManager {
    private final Map<Class<? extends Configured<?>>, Config> configuredMap = new ConcurrentHashMap<>();
    private final Map<Class<? extends Config>, Config> configMap = new ConcurrentHashMap<>();

    public Map<Class<? extends Configured<?>>, Config> getConfiguredMap() {
        return configuredMap;
    }

    public Map<Class<? extends Config>, Config> getConfigMap() {
        return configMap;
    }

    public <T extends Config> T getOrCreateConfig(Class<T> configClass) {
        try {
            Config config = configClass.newInstance();
            configMap.put(configClass, config);
            return (T) config;
        } catch (InstantiationException | IllegalAccessException e) {
            FoodCraftReloaded.getLogger().error("Cannot create instance for " + configClass, e);
            return null;
        }
    }

    public <T extends Config> T getOrCreateConfig(Class<T> configClass, Class<? extends Configured<?>> configuredClass) {
        try {
            Config config = configClass.newInstance();
            configMap.put(configClass, config);
            configuredMap.put(configuredClass, config);
            return (T) config;
        } catch (InstantiationException | IllegalAccessException e) {
            FoodCraftReloaded.getLogger().error("Cannot create instance for " + configClass, e);
            return null;
        }
    }
}
