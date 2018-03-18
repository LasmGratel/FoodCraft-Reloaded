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

package cc.lasmgratel.foodcraftreloaded.client;

import cc.lasmgratel.foodcraftreloaded.client.gui.GuiContainerDrinkMachine;
import cc.lasmgratel.foodcraftreloaded.client.gui.GuiContainerPressureCooker;
import cc.lasmgratel.foodcraftreloaded.client.gui.GuiContainerSmeltingDrinkMachine;
import cc.lasmgratel.foodcraftreloaded.common.container.ContainerDrinkMachine;
import cc.lasmgratel.foodcraftreloaded.common.container.ContainerPressureCooker;
import cc.lasmgratel.foodcraftreloaded.common.container.ContainerSmeltingDrinkMachine;
import cc.lasmgratel.foodcraftreloaded.common.util.AutomatedGui;

import javax.annotation.Nonnull;

public enum EnumGui {
    DRINK_MACHINE(ContainerDrinkMachine.class, GuiContainerDrinkMachine.class),
    PRESSURE_COOKER(ContainerPressureCooker.class, GuiContainerPressureCooker.class),
    SMELTING_PRESSURE_COOKER(ContainerSmeltingDrinkMachine.class, GuiContainerSmeltingDrinkMachine.class);

    @Nonnull
    private Class<? extends AutomatedGui> containerClass;

    @Nonnull
    private Class<? extends AutomatedGui> guiClass;

    EnumGui(@Nonnull Class<? extends AutomatedGui> containerClass, @Nonnull Class<? extends AutomatedGui> guiClass) {
        this.guiClass = guiClass;
        this.containerClass = containerClass;
    }

    public Class<?> getGuiClass() {
        return guiClass;
    }

    public Class<?> getContainerClass() {
        return containerClass;
    }
}
