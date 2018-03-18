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

package cc.lasmgratel.foodcraftreloaded.common.util.machine;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * One of the process in machine.
 * Asynchronous callbacks available.
 * @param <T> the machine
 */
public class Process<T> {
    private int progress = 0;
    private int maxProgress;
    private boolean completed = false;
    private Consumer<T> onCompleted = (T) -> {};
    private Predicate<T> runnable = (T) -> false;

    public Process(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public Consumer<T> getOnCompleted() {
        return onCompleted;
    }

    public void setOnCompleted(Consumer<T> onCompleted) {
        this.onCompleted = onCompleted;
    }

    public Predicate<T> getRunnable() {
        return runnable;
    }

    public void setRunnable(Predicate<T> runnable) {
        this.runnable = runnable;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void update(T machine) {
        if (!isCompleted()) {
            if (runnable.test(machine)) {
                ++progress;
                if (progress >= getMaxProgress()) {
                    progress = getMaxProgress();
                    setCompleted(true);
                }
            }
        } else {
            onCompleted.accept(machine);
        }
    }
}
