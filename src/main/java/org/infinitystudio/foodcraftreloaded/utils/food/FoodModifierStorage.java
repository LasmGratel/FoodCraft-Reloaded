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

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class FoodModifierStorage implements Capability.IStorage<FoodModifierCapability> {
    @Override
    public NBTBase writeNBT(Capability<FoodModifierCapability> capability,
                            FoodModifierCapability instance, EnumFacing side) {
        return null;
    }

    @Override
    public void readNBT(Capability<FoodModifierCapability> capability,
                        FoodModifierCapability instance, EnumFacing side, NBTBase nbt) {

    }
}
