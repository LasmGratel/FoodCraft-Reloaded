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

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

import java.util.UUID;

import static org.infinitystudio.foodcraftreloaded.FoodCraftReloaded.MODID;

public class WorldSavedFoodData extends WorldSavedData {
    private static final String DATA_NAME = MODID + "_FoodData";

    public WorldSavedFoodData(String name) {
        super(name);
    }

    /**
     * Reads all required food data from World's compound tag.
     * @param nbt current world's tag compound
     */
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        if (nbt.hasKey("foodData")) {
            if (nbt.hasKey("foodData", 11)) {
                NBTTagCompound foodData = nbt.getCompoundTag("foodData");
            } else {
                nbt.removeTag("foodData");

            }
        } else {

        }

    }

    /**
     * Writes all food data to the nbt.
     * @param compound current world's tag compound
     * @return written nbt tag compound
     */
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        if(compound.hasKey("foodData")) {
            compound.removeTag("foodData");
        }

        NBTTagCompound foodData = new NBTTagCompound();
        NBTTagCompound foodModifierData = new NBTTagCompound();

        for (UUID uuid : FoodModifier.getAllSavedPlayer()) {
            NBTTagCompound foodModifier = new NBTTagCompound();
            for (int i = 0; i < 5; i++) {
                foodModifier.setFloat(String.valueOf(i), FoodModifier.getModifierForPlayer(uuid)[i]);
            }
            foodModifierData.setTag(uuid.toString(), foodModifier);
        }

        foodData.setTag("foodModifierData", foodModifierData);
        compound.setTag("foodData", foodData);

        return compound;
    }
}
