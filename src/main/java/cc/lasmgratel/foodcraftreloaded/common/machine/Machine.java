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

package cc.lasmgratel.foodcraftreloaded.common.machine;

import java.util.ArrayList;
import java.util.List;

/**
 * Machines' behavior definition.
 */
public interface Machine<T extends Machine<T>> {
    /**
     * Get progress of first process.
     */
    static int getProgress(Machine<?> machine) {
        return machine.getProcesses().stream().map(Process::getProgress).findAny().orElse(0);
    }

    /**
     * Get maximum progress of first process.
     */
    static int getMaxProgress(Machine<?> machine) {
        return machine.getProcesses().stream().map(Process::getMaxProgress).findAny().orElse(0);
    }

    default List<Process<T>> getProcesses() {
        return new ArrayList<>();
    }

    /**
     * Returns whether machine can be started.
     */
    boolean canStart();

    /**
     * Returns whether machine can continue progressing at this tick.
     */
    boolean canProgress();

    /**
     * Called when machine started progressing.
     */
    void startProgress();

    /**
     * Called when machine is progressing.
     * Use {@link Process#onProgress} instead.
     */
    default void progress() {
        getProcesses().forEach(progress -> progress.update((T) this));
    }

    /**
     * Called when machine has progressed completely.
     */
    void progressCompleted();

    /**
     * Reset current progress.
     */
    default void resetProgress() {
        getProcesses().forEach(Process::reset);
    }
}
