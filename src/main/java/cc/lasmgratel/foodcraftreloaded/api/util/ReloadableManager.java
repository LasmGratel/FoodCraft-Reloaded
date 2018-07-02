package cc.lasmgratel.foodcraftreloaded.api.util;

public interface ReloadableManager {
    void load();
    void save();
    default void reload() {
        load();
        save();
    }
}
