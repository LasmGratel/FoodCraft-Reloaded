package cc.lasmgratel.foodcraftreloaded.common.block.tileentity;

/**
 * Machines' behavior definition.
 */
public interface Machine {
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
     */
    void progress();

    /**
     * Called when machine has progressed completely.
     */
    void progressCompleted();

    /**
     * Reset current progress.
     */
    void resetProgress();
}
