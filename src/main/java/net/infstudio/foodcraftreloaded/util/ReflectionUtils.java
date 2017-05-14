package net.infstudio.foodcraftreloaded.util;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;

import javax.annotation.Nullable;

public interface ReflectionUtils {
    @SuppressWarnings("unchecked")
    @Nullable
    static <K, V> K getFieldIn(Class<V> clz, String fieldName, @Nullable V clzObj) {
        try {
            return (K) clz.getDeclaredField(fieldName).get(clzObj);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            FoodCraftReloaded.getLogger().error("[FoodCraft]Cannot access field " + clz + "#" + fieldName + "!", e);
        }
        return null;
    }

    static <T> void setField(Class<T> clz, String fieldName, Object objToSet, @Nullable T clzObj) {
        try {
            clz.getDeclaredField(fieldName).set(clzObj, objToSet);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            FoodCraftReloaded.getLogger().error("[FoodCraft]Cannot access field " + clz + "#" + fieldName + "!", e);
        }
    }
}
