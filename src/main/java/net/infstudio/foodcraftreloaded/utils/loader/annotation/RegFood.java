package net.infstudio.foodcraftreloaded.utils.loader.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RegFood {
    float[] modifier();
    String[] name();
    String[] oreDict() default {};
}
