package net.infstudio.foodcraftreloaded.api;

import net.infstudio.foodcraftreloaded.item.tool.EnumKitchenKnifeType;

import java.awt.*;

public class EnumHelper extends net.minecraftforge.common.util.EnumHelper {

    public static EnumKitchenKnifeType addKitchenKnifeType(String enumName, Color knifeColor) {
        return addEnum(EnumKitchenKnifeType.class, enumName, new Class[]{Color.class}, knifeColor);
    }
}
