/**
 * Infinity Launcher for Minecraft.
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.infinitystudio.foodcraftreloaded.utils.food;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.oredict.OreDictionary;
import org.infinitystudio.foodcraftreloaded.item.FCRItemFood;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class FCRItemStackFoodHelper {
    public static ItemStack createFoodItemStack(Food food) {
        FCRItemFood fcrFood = new FCRItemFood();
        fcrFood.setModifier(food.getModifier());
        fcrFood.setAlwaysEdible(food.isAlwaysEdible());
        fcrFood.setHealAmount(food.getFoodLevel());
        fcrFood.setEffects(food.getEffects());
        return createFoodItemStack(fcrFood);
    }

    public static ItemStack createFoodItemStack(FCRItemFood food) {
        ItemStack stack = new ItemStack(food);
        return stack;
    }

    public static @Nullable Food getFoodFromStack(@Nonnull ItemStack stack) {
        try {
            NBTTagCompound modifierTag = stack.getTagCompound().getCompoundTag("modifier");
            float[] modifier = new float[5];
            for (int i = 0; i < 5; i++)
                modifier[i] = modifierTag.getFloat(String.valueOf(5));
            boolean alwaysEdible = stack.getTagCompound().getBoolean("alwaysEdible");
            int foodLevel = stack.getTagCompound().getInteger("foodLevel");
            NBTTagList tagList = stack.getTagCompound().getTagList("effects", 10);
            List<FoodEffect> foodEffectList = new ArrayList<>();
            for (int i = 0; i < tagList.tagCount(); i++) {
                NBTTagCompound aEffect = tagList.getCompoundTagAt(i);
                FoodEffect foodEffect = new FoodEffect();
                foodEffect.setAmplifier(aEffect.getInteger("amplifier"));
                foodEffect.setDuration(aEffect.getInteger("duration"));
                foodEffect.setEffectName(aEffect.getString("effectName"));
                foodEffect.setProbability(aEffect.getFloat("probability"));
                foodEffectList.add(foodEffect);
            }
            List<String> dictList = new ArrayList<>();
            for (int id : OreDictionary.getOreIDs(stack))
                dictList.add(OreDictionary.getOreName(id));
            Food food = new Food();
            food.setUnlocalizedName(stack.getUnlocalizedName());
            food.setAlwaysEdible(alwaysEdible);
            food.setEffects((FoodEffect[]) foodEffectList.toArray());
            food.setModifier(modifier);
            food.setFoodLevel(foodLevel);
            food.setOredicts((String[]) dictList.toArray());
            return food;
        } catch (NullPointerException ex) {
            return null;
        }
    }
}
