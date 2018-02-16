package cc.lasmgratel.foodcraftreloaded.common.block.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

/**
 * Machine type that can be progressed in tick.
 */
public abstract class TileEntityProgressiveMachine extends TileEntity implements ITickable, Machine {
    private int maxProgress;
    private boolean started = false;
    private int progress = 0;

    public TileEntityProgressiveMachine(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    @Override
    public void update() {
        if (isStarted()) {
            if (getProgress() > 0) { // Progress is not completed
                if (canProgress()) {
                    progress();
                    setProgress(getProgress() - 1);
                }
            } else {
                setProgress(0);
                progressCompleted();
            }
        } else {
            if (canStart()) { // If machine can be started
                setProgress(getMaxProgress());
                setStarted(true);
            }
        }
    }

    @Override
    public void resetProgress() {
        setStarted(false);
        setProgress(0);
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
}
