/**
 * FoodCraft Mod for Minecraft.
 * Copyright (C) 2016 Infinity Studio.
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
package org.infinitystudio.foodcraftreloaded.utils.annotation;

import net.minecraftforge.fml.common.event.FMLStateEvent;

public abstract class AbstractFieldRegistration<T> {
    public abstract T init(AutoInstance annotation, T object, FMLStateEvent state);
    public T initClient(AutoInstance annotation, T object, FMLStateEvent state) {
        return init(annotation, object, state);
    }
    public abstract T newInstance() throws Exception;
}
