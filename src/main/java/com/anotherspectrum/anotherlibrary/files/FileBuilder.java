package com.anotherspectrum.anotherlibrary.files;

import com.google.common.base.Preconditions;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * FileBuilder System
 * @since 0.2.7-SNAPSHOT
 */
public class FileBuilder {

    private String folderPath;
    private String fileName;
    private FileRegister resultFile;

    public FileBuilder(String folderPath) {
        this.folderPath = folderPath;
    }

    public FileBuilder changeFolderPath(String folderPath) {
        this.folderPath = folderPath;
        return this;
    }

    public FileBuilder changeFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public FileBuilder setFile(String fileName) {
        this.fileName = fileName.toLowerCase(Locale.ROOT);
        return this;
    }

    public FileRegister build() {
        Preconditions.checkNotNull(folderPath, "[AnotherLibrary] File Build 중 오류가 발생했습니다. (folderPath 는 null 일 수 없습니다.)");
        Preconditions.checkNotNull(fileName, "[AnotherLibrary] File Build 중 오류가 발생했습니다. (fileName 은 null 일 수 없습니다.)");

        File folder = new File(folderPath);
        if (!folder.exists()) folder.mkdirs();
        this.resultFile = new FileRegister(folderPath, fileName);

        return this.resultFile;
    }

    class FileRegister extends File {

        private File file;
        private FileConfiguration config;

        private FileRegister(String parent, @NotNull String child) {
            super(parent, child);
            this.file = this;
            if (!file.exists()) try { file.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
            this.config = YamlConfiguration.loadConfiguration(this);
        }

        public File getFile() {
            return file;
        }

        public FileConfiguration getConfig() {
            return config;
        }

    }


}
