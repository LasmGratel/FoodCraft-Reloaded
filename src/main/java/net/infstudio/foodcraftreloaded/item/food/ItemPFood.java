package net.infstudio.foodcraftreloaded.item.food;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;

import static net.minecraft.util.math.MathHelper.ceil;

public class ItemPFood extends FCRItemFood {
    private float[] properties;

    public ItemPFood() {
        super(1, 1.0f, true);
    }

    public float[] getProperties() {
        return properties;
    }

    public void setProperties(float... properties) {
        Validate.validIndex(ArrayUtils.toObject(properties), 4);
        this.properties = properties;

    }

    private void calcHealAmount() {
        float sour = properties[0] * -10f;
        float sweet = properties[1] * 30f;
        float bitter = properties[2] * -15f;
        float spice = properties[3] * 10f;
        float salty = properties[4] * 5f;
        setHealAmount(ceil(sour) + ceil(sweet) + ceil(bitter) + ceil(spice) + ceil(salty));
    }
}
