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

import javax.annotation.Nonnegative;
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
    private boolean started = false;
    private boolean completed = false;
    private Consumer<T> onCompleted = (T) -> {};
    private Predicate<T> canStart = (T) -> false;
    private Predicate<T> canProgress = (T) -> false;
    private Consumer<T> onStarted = (T) -> {};
    private Consumer<T> onProgress = (T) -> {};

    public Process(@Nonnegative int maxProgress) {
        if (maxProgress <= 0)
            throw new IllegalArgumentException("Max progress cannot be zero or negative! Current value: " + maxProgress);
        this.maxProgress = maxProgress;
    }

    public Consumer<T> getOnCompleted() {
        return onCompleted;
    }

    public void setOnCompleted(Consumer<T> onCompleted) {
        this.onCompleted = onCompleted;
    }

    public Predicate<T> getCanProgress() {
        return canProgress;
    }

    public void setCanProgress(Predicate<T> canProgress) {
        this.canProgress = canProgress;
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

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public Predicate<T> getCanStart() {
        return canStart;
    }

    public void setCanStart(Predicate<T> canStart) {
        this.canStart = canStart;
    }

    public double getCompletePercentage() {
        return progress / maxProgress;
    }

    public Consumer<T> getOnStarted() {
        return onStarted;
    }

    public void setOnStarted(Consumer<T> onStarted) {
        this.onStarted = onStarted;
    }

    public Consumer<T> getOnProgress() {
        return onProgress;
    }

    public void setOnProgress(Consumer<T> onProgress) {
        this.onProgress = onProgress;
    }

    public void update(T machine) {
        if (isStarted()) {
            if (!isCompleted()) {
                if (getCanProgress().test(machine)) {
                    setProgress(progress + 1);
                    onProgress.accept(machine);
                    if (getProgress() >= getMaxProgress()) {
                        setProgress(getMaxProgress()); // Set to max value so that it wouldn't be over of maximum value.
                        setCompleted(true);
                    }
                }
            } else {
                setCompleted(false);
                setStarted(false);
                onCompleted.accept(machine);
            }
        } else {
            if (getCanStart().test(machine)) {
                setStarted(true);
                setCompleted(false);
                onStarted.accept(machine);
            }
        }
    }

    public void reset() {
        setProgress(0);
        setCompleted(false);
        setStarted(false);
    }

    public static class Builder<T> {
        private Process<T> process;

        public Builder(int maxProgress) {
            process = new Process<>(maxProgress);
        }

        public Builder<T> completed(Consumer<T> consumer) {
            process.setOnCompleted(consumer);
            return this;
        }

        public Builder<T> started(Consumer<T> consumer) {
            process.setOnStarted(consumer);
            return this;
        }

        public Builder<T> canStart(Predicate<T> predicate) {
            process.setCanStart(predicate);
            return this;
        }

        public Builder<T> canProgress(Predicate<T> predicate) {
            process.setCanProgress(predicate);
            return this;
        }

        public Process<T> build() {
            return process;
        }
    }
}
