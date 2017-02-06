package net.infstudio.foodcraftreloaded.utils;

import javax.annotation.Nonnull;
import java.util.Arrays;

public interface NameBuilder {
    @Nonnull
    static String buildRegistryName(String... params) {
        StringBuilder stringBuilder = new StringBuilder(params[0]);
        String[] copied = Arrays.copyOfRange(params, 1, params.length);
        for (String s : copied) {
            stringBuilder.append('_');
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    @Nonnull
    static String buildUnlocalizedName(String... params) {
        StringBuilder stringBuilder = new StringBuilder(params[0]);
        String[] copied = Arrays.copyOfRange(params, 1, params.length);
        for (String s : copied) {
            stringBuilder.append(Character.toUpperCase(s.charAt(0)));
            stringBuilder.append(s.substring(1));
        }
        return stringBuilder.toString();
    }
}
