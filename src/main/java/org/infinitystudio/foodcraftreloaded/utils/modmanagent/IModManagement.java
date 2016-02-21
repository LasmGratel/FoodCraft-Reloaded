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

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.annotation.Annotation;

/**
 * Interface of Mod Manager
 *
 * @param <T> annotation type
 * @author ustc_zzzz
 */
public interface IModManagement<T extends Annotation> {
    /**
     * @return annotation class
     */
    Class<T> getAnnotationClass();

    /**
     * @return stage
     */
    Stage getStage();

    /**
     * @return stage on client side
     */
    Stage getStageClient();

    /**
     * @param annotation annotation of the field
     * @param clazz      type of the field
     * @return the instance
     */
    Object init(String modid, T annotation, Class<?> clazz) throws Exception;

    /**
     * @param annotation annotation of the instance
     * @param instance   the instance
     */
    void register(String modid, T annotation, Object instance) throws Exception;

    /**
     * only for client side
     *
     * @param annotation annotation of the instance
     * @param instance   the instance
     */
    @SideOnly(Side.CLIENT)
    void registerClient(String modid, T annotation, Object instance) throws Exception;

    /**
     * Stage
     */
    enum Stage {
        PREINIT, INIT, POSTINIT
    }
}
