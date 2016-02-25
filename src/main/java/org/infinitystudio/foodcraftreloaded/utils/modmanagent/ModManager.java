/**
 * Singularity Mod for Minecraft.
 * Copyright (C) 2016 Infinity Studio.
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @license GPLv3
 */
package org.infinitystudio.foodcraftreloaded.utils.modmanagent;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Mod Manager
 *
 * @author ustc_zzzz
 */
public class ModManager {

    public final String modid;
    protected final LinkedListMultimap<Field, Annotation> fields = LinkedListMultimap.create();
    protected final Multimap<Class<? extends Annotation>, IModManagement<?>> mPreInit = HashMultimap.create();
    protected final Multimap<Class<? extends Annotation>, IModManagement<?>> mInit = HashMultimap.create();
    protected final Multimap<Class<? extends Annotation>, IModManagement<?>> mPostInit = HashMultimap.create();
    protected final Multimap<Class<? extends Annotation>, IModManagement<?>> mPreInitClient = HashMultimap.create();
    protected final Multimap<Class<? extends Annotation>, IModManagement<?>> mInitClient = HashMultimap.create();
    protected final Multimap<Class<? extends Annotation>, IModManagement<?>> mPostInitClient = HashMultimap.create();

    public ModManager(String modid, Class<?> clz, IModManagement<?>... managements) {
        this.modid = modid;
        for (Field field : clz.getDeclaredFields()) {
            field.setAccessible(true);
            if (Modifier.isStatic(field.getModifiers())) {
                for (Annotation annotation : field.getDeclaredAnnotations()) {
                    fields.put(field, annotation);
                }
            }
        }
        for (IModManagement<?> management : managements) {
            switch (management.getStage()) {
            case PREINIT:
                mPreInit.put(management.getAnnotationClass(), management);
                break;
            case INIT:
                mInit.put(management.getAnnotationClass(), management);
                break;
            case POSTINIT:
                mPostInit.put(management.getAnnotationClass(), management);
                break;
            default:
            }
            switch (management.getStageClient()) {
            case PREINIT:
                mPreInitClient.put(management.getAnnotationClass(), management);
                break;
            case INIT:
                mInitClient.put(management.getAnnotationClass(), management);
                break;
            case POSTINIT:
                mPostInitClient.put(management.getAnnotationClass(), management);
                break;
            default:
            }
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void preInit() {
        for (java.util.Map.Entry<Field, Annotation> entry : fields.entries()) {
            Annotation annotation = entry.getValue();
            Field field = entry.getKey();
            try {
                for (IModManagement management : mPreInit.get(annotation.annotationType())) {
                    if (field.get(null) == null) {
                        field.set(null, management.init(this.modid, annotation, field.getType()));
                    }
                }
                if (field.get(null) != null) {
                    for (IModManagement management : mPreInit.get(annotation.annotationType())) {
                        management.register(this.modid, annotation, field.get(null));
                    }
                }
            } catch (Exception e) {
                String message = "Cannot register field '" + entry.getKey().getName() + "' with annotation '"
                        + annotation.toString() + "' in pre initialization.";
                throw new RuntimeException(message, e);
            }
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void init() {
        for (java.util.Map.Entry<Field, Annotation> entry : fields.entries()) {
            Annotation annotation = entry.getValue();
            Field field = entry.getKey();
            try {
                for (IModManagement management : mInit.get(annotation.annotationType())) {
                    if (field.get(null) == null) {
                        field.set(null, management.init(this.modid, annotation, field.getType()));
                    }
                }
                if (field.get(null) != null) {
                    for (IModManagement management : mInit.get(annotation.annotationType())) {
                        management.register(this.modid, annotation, field.get(null));
                    }
                }
            } catch (Exception e) {
                String message = "Cannot register field '" + entry.getKey().getName() + "' with annotation '"
                        + annotation.toString() + "' in initialization.";
                throw new RuntimeException(message, e);
            }
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void postInit() {
        for (java.util.Map.Entry<Field, Annotation> entry : fields.entries()) {
            Annotation annotation = entry.getValue();
            Field field = entry.getKey();
            try {
                for (IModManagement management : mPostInit.get(annotation.annotationType())) {
                    if (field.get(null) == null) {
                        field.set(null, management.init(this.modid, annotation, field.getType()));
                    }
                }
                if (field.get(null) != null) {
                    for (IModManagement management : mPostInit.get(annotation.annotationType())) {
                        management.register(this.modid, annotation, field.get(null));
                    }
                }
            } catch (Exception e) {
                String message = "Cannot register field '" + entry.getKey().getName() + "' with annotation '"
                        + annotation.toString() + "' in post initialization.";
                throw new RuntimeException(message, e);
            }
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void preInitClient() {
        for (java.util.Map.Entry<Field, Annotation> entry : fields.entries()) {
            Annotation annotation = entry.getValue();
            Field field = entry.getKey();
            try {
                if (field.get(null) != null) {
                    for (IModManagement management : mPreInitClient.get(annotation.annotationType())) {
                        management.registerClient(this.modid, annotation, field.get(null));
                    }
                }
            } catch (Exception e) {
                String message = "Cannot register field '" + entry.getKey().getName() + "' with annotation '"
                        + annotation.toString() + "' in pre initialization.";
                throw new RuntimeException(message, e);
            }
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void initClient() {
        for (java.util.Map.Entry<Field, Annotation> entry : fields.entries()) {
            Annotation annotation = entry.getValue();
            Field field = entry.getKey();
            try {
                if (field.get(null) != null) {
                    for (IModManagement management : mInitClient.get(annotation.annotationType())) {
                        management.registerClient(this.modid, annotation, field.get(null));
                    }
                }
            } catch (Exception e) {
                String message = "Cannot register field '" + entry.getKey().getName() + "' with annotation '"
                        + annotation.toString() + "' in initialization.";
                throw new RuntimeException(message, e);
            }
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void postInitClient() {
        for (java.util.Map.Entry<Field, Annotation> entry : fields.entries()) {
            Annotation annotation = entry.getValue();
            Field field = entry.getKey();
            try {
                if (field.get(null) != null) {
                    for (IModManagement management : mPostInitClient.get(annotation.annotationType())) {
                        management.registerClient(this.modid, annotation, field.get(null));
                    }
                }
            } catch (Exception e) {
                String message = "Cannot register field '" + entry.getKey().getName() + "' with annotation '"
                        + annotation.toString() + "' in post initialization.";
                throw new RuntimeException(message, e);
            }
        }
    }
}
