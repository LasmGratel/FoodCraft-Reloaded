package cc.lasmgratel.foodcraftreloaded.common.util;

public class EnergyUnit {
    public static final int JOULES_PER_CALORIE = 4184;

    private final double value;

    public EnergyUnit(double value) {
        this.value = value;
    }

    public static EnergyUnit fromJoules(double value) {
        return new EnergyUnit(value);
    }

    public static EnergyUnit fromCalories(double value) {
        return new EnergyUnit(JOULES_PER_CALORIE * value);
    }

    public double toJoules() {
        return value;
    }

    public double toCalories() {
        return value / JOULES_PER_CALORIE;
    }
}
