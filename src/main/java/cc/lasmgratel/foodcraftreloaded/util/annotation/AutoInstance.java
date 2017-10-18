/*
 * FoodCraft Mod - Add more food to your Minecraft.
 * Copyright (C) 2017 Lasm Gratel
 *
 * This file is part of FoodCraft Mod.
 *
 * FoodCraft Mod is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FoodCraft Mod is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FoodCraft Mod.  If not, see <http://www.gnu.org/licenses/>.
 */

package cc.lasmgratel.foodcraftreloaded.util.annotation;

import net.minecraftforge.fml.common.event.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoInstance {
    State value() default State.PREINIT;
    enum State {
        PREINIT(FMLPreInitializationEvent.class),
        INIT(FMLInitializationEvent.class),
        POSTINIT(FMLPostInitializationEvent.class),
        LOADCOMPLETE(FMLLoadCompleteEvent.class);

        Class<? extends FMLStateEvent> event;

        State(Class<? extends FMLStateEvent> event) {
            this.event = event;
        }

        public Class<? extends FMLStateEvent> getEvent() {
            return event;
        }
    }
}
