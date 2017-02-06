package net.infstudio.foodcraftreloaded.common;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.init.FCRAchievements;
import net.infstudio.foodcraftreloaded.utils.loader.annotation.Load;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.fml.common.LoaderState;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class AchievementLoader {
    private AchievementPage fcrAchievementPage;

    @Load(LoaderState.POSTINITIALIZATION)
    public void registerAchievements() {
        Set<Achievement> achievements = new HashSet<>();
        for (Field field : FCRAchievements.class.getFields()) {
            try {
                Achievement achievement = (Achievement) field.get(null);
                achievement.registerStat();
                achievements.add(achievement);
            } catch (Exception e) {
                FoodCraftReloaded.getLogger().warn("Un-able to register achievement", e);
            }
        }
        fcrAchievementPage = new AchievementPage(FoodCraftReloaded.NAME, achievements.toArray(new Achievement[0]));
        AchievementPage.registerAchievementPage(fcrAchievementPage);
    }

    public AchievementPage getFCRAchievementPage() {
        return fcrAchievementPage;
    }
}
