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
 * An Example of Mod Manager, @see IModManagement
 *
 * @param <T> annotation type
 * @author ustc_zzzz
 */
public abstract class ModManagement<T extends Annotation> implements IModManagement<T> {

    protected final Stage stage, stageClient;
    protected Class<T> annotationClass;

    public ModManagement(Class<T> annotationClass) {
        this(annotationClass, Stage.INIT);
    }

    public ModManagement(Class<T> annotationClass, Stage stage) {
        this(annotationClass, stage, stage);
    }

    public ModManagement(Class<T> annotationClass, Stage stage, Stage stageClient) {
        this.annotationClass = annotationClass;
        this.stage = stage;
        this.stageClient = stageClient;
    }

    @Override
    public Class<T> getAnnotationClass() {
        return annotationClass;
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public Stage getStageClient() {
        return stageClient;
    }

    @Override
    public Object init(String modid, T annotation, Class<?> clazz) throws Exception {
        return clazz.newInstance();
    }

    @Override
    public void register(String modid, T annotation, Object instance) throws Exception {
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerClient(String modid, T annotation, Object instance) throws Exception {
    }
}
