/*
 * FoodCraft Mod - Add more food to your Minecraft.
 * Copyright (C) 2017 Lasm Gratel
 *
 * This file is part of FoodCraft Mod.
 *
 * FoodCraft Mod is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FoodCraft Mod is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FoodCraft Mod.  If not, see <http://www.gnu.org/licenses/>.
 */

package cc.lasmgratel.foodcraftreloaded.common.util;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;

public interface JarUtil {
    /**
     * Extract file from a Zip.
     * @param zipFile Zip file to extract
     * @param targetDirectory Directory as extracted root
     * @param content Content name, empty to extract all files, add "/" suffix to extract directory, otherwise extract single file.
     */
    static void extractFromZip(Path zipFile, Path targetDirectory, String content) throws IOException {
        try (FileSystem fileSystem = zipFile.getFileSystem()) {
            System.out.println(fileSystem.getFileStores());
        }
    }
}
