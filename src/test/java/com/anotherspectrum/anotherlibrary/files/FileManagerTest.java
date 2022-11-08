package com.anotherspectrum.anotherlibrary.files;

import com.anotherspectrum.anotherlibrary.AnotherLibrary;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest extends FileManager {

    class A extends JavaPlugin {
        private AnotherLibrary library;

        @Override
        public void onEnable() {
            library = new AnotherLibrary("TestPlugin", this);
        }

        public AnotherLibrary getLibrary() {
            return library;
        }
    }

    FileManagerTest() { super("test/test", "test.yml"); }

    @Test
    void testing() {
        boolean bool = contains("some.path");

        isNotContains("some.path_two", () -> {
            getConfig().set("some.path_two", "Else_JunSuk is Fkin AWeSoMe!!");
            save();
        });

        String msg = getConfig().getString("some.path_two");

        System.out.println("some.path 경로 존재 여부 : " + (bool ? "있음" : "없음"));
        System.out.println("some.path_two 경로 내용물 : " + msg);
    }

}