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

import com.google.common.collect.Maps;
import net.minecraft.entity.player.EntityPlayer;

import java.util.Map;
import java.util.UUID;

/**
 * Modify the food value of the Player.
 */
public class FoodModifier {
    private static Map<UUID, float[]> playerModifierMap = Maps.newHashMap();

    /**
     * Get current player's food level.
     * @param player current player
     * @return food level
     */
    public static int getPlayerFoodLevel(EntityPlayer player) {
        return player.getFoodStats().getFoodLevel();
    }

    public static float[] getModifierForPlayer(UUID uuid) {
        return playerModifierMap.get(uuid);
    }

    public static void putModifierForPlayer(UUID uuid, float[] modifiers) {
        playerModifierMap.put(uuid, modifiers);
    }

    public static UUID[] getAllSavedPlayer() {
        return (UUID[]) playerModifierMap.keySet().toArray();
    }

    public static void generateModifierForPlayer(UUID uuid) {

    }

}
