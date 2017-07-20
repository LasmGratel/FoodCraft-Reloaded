package net.infstudio.foodcraftreloaded.util;

import com.google.common.collect.Lists;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public interface NameBuilder {
    @Nonnull
    static String buildRegistryName(String... params) {
        if (Arrays.stream(params).anyMatch(s -> s.contains("_"))) {
            List<String> temp = Lists.newArrayList();
            for (String param : params) {
                if (param.contains("_"))
                    temp.addAll(Arrays.asList(param.split("_")));
                else
                    temp.add(param);
            }
            StringBuilder stringBuilder = new StringBuilder(temp.get(0));
            for (String s : temp.subList(1, temp.size())) {
                stringBuilder.append('_');
                stringBuilder.append(s);
            }
            return stringBuilder.toString();
        }
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
        if (Arrays.stream(params).anyMatch(s -> s.contains("_"))) {
            List<String> temp = Lists.newArrayList();
            for (String param : params) {
                if (param.contains("_"))
                    temp.addAll(Arrays.asList(param.split("_")));
                else
                    temp.add(param);
            }
            StringBuilder stringBuilder = new StringBuilder(temp.get(0));
            for (String s : temp.subList(1, temp.size())) {
                stringBuilder.append(Character.toUpperCase(s.charAt(0)));
                stringBuilder.append(s.substring(1));
            }
            return stringBuilder.toString();
        }
        StringBuilder stringBuilder = new StringBuilder(params[0]);
        String[] copied = Arrays.copyOfRange(params, 1, params.length);
        for (String s : copied) {
            stringBuilder.append(Character.toUpperCase(s.charAt(0)));
            stringBuilder.append(s.substring(1));
        }
        return stringBuilder.toString();
    }
}
