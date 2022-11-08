package com.anotherspectrum.anotherlibrary.files;

import com.anotherspectrum.anotherlibrary.AnotherLibrary;
import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.Nullable;
import java.io.File;
import java.io.IOException;

/**
 * 파일을 생성하거나, 생성한 파일을 관리할 수 있습니다.
 * @since 0.1.1
 * @update 0.2.6-SNAPSHOT/08-11-22
 * @see #contains(String path)
 */
public class FileManager {

    private File file;
    private FileConfiguration config;

    /**
     * 파일을 생성하고 관리할 수 있습니다.
     * '플러그인의 이름' 의 경우는 해당 라이브러리의 인스턴스를 정의할 때
     * 설정할 수 있습니다.
     * @param folderPath '~/plugins/플러그인의 이름/folderPath' 이는 생략할 수 있습니다.
     *                   생략된 경우 하위 디렉토리인 '~/plugins/플러그인의 이름' 에 파일이 저장됩니다.
     * @param fileName '~/plugins/플러그인의 이름/folderPath/fileName(.확장자)'
     */
    public FileManager(@Nullable String folderPath, @NotNull String fileName) {
        this(folderPath, fileName, null);
    }

    /**
     * 파일을 생성하고 관리할 수 있습니다.
     * '플러그인의 이름' 의 경우는 해당 라이브러리의 인스턴스를 정의할 때
     * 설정할 수 있습니다.
     * @param folderPath '~/plugins/플러그인의 이름/folderPath' 이는 생략할 수 있습니다.
     *                   생략된 경우 하위 디렉토리인 '~/plugins/플러그인의 이름' 에 파일이 저장됩니다.
     * @param fileName '~/plugins/플러그인의 이름/folderPath/fileName(.확장자)'
     * @param defaultDraw 파일 생성 후 기본적으로 어떤 데이터 섹션을 기입할지 세팅합니다.
     */
    public FileManager(@Nullable String folderPath, @NotNull String fileName, @Nullable DefaultDraw defaultDraw) {
        Preconditions.checkNotNull(AnotherLibrary.getPluginName(), "[AnotherLibrary] 라이브러리가 당신의 플러그인 속 메인 클래스에 인스턴스 선언되지 않았습니다.\n" +
                "따라서 디렉토리와 하위 파일이 정상적으로 세팅되지 않았습니다.");
        if (folderPath == null) {
            File folder = new File(Bukkit.getPluginsFolder().getAbsolutePath() + "/" + AnotherLibrary.getPluginName());
            if (!folder.exists()) folder.mkdirs();
            this.file = new File(folder, fileName);
            this.config = YamlConfiguration.loadConfiguration(file);
            return;
        }
        File folder = new File(Bukkit.getPluginsFolder().getAbsolutePath() + "/" + AnotherLibrary.getPluginName() + "/" + folderPath);
        if (!folder.exists()) folder.mkdirs();
        this.file = new File(folder, fileName);
        this.config = YamlConfiguration.loadConfiguration(file);

        if (defaultDraw != null) defaultDraw.draw(config);
        save();
    }

    /**
     * 해당 플레이어의 데이터 파일을 생성합니다.
     * 저장 위치는 '~/plugins/플러그인의 이름/data/' 의 하위 디렉토리 입니다.
     * @param player 데이터 파일의 타겟
     * @param dataFileName 데이터 파일의 이름
     */
    public FileManager(@NotNull Player player, @NotNull String dataFileName) {
        this("data/" + player.getName() + "(" + player.getUniqueId().toString() + ")", dataFileName, null);
    }

    /**
     * 해당 플레이어의 데이터 파일을 생성합니다.
     * 저장 위치는 '~/plugins/플러그인의 이름/data/' 의 하위 디렉토리 입니다.
     * @param player 데이터 파일의 타겟
     * @param dataFileName 데이터 파일의 이름
     * @param defaultDraw 파일 생성 후 기본적으로 어떤 데이터 섹션을 기입할지 세팅합니다.
     */
    public FileManager(@NotNull Player player, @NotNull String dataFileName, @Nullable DefaultDraw defaultDraw) {
        this("data/" + player.getName() + "(" + player.getUniqueId().toString() + ")", dataFileName, defaultDraw);
    }

    public interface DefaultDraw {
        void draw(FileConfiguration config);
    }

    public interface ContainsDraw {
        void draw();
    }

    /**
     * 파일에 세팅된 모든 데이터를 저장합니다.
     */
    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 인스턴스를 정의할 때 기입했던 로케이션 속
     * 파일을 불러옵니다.
     * @return {@link File}
     */
    public File getFile() {
        return file;
    }

    /**
     * 인스턴스를 정의할 때 기입했던 로케이션 속
     * 파일의 configuration 을(를) 불러옵니다.
     * @return {@link FileConfiguration}
     */
    public FileConfiguration getConfig() {
        return config;
    }

    /**
     * 해당 path 가 파일 내에 기입되어 있는지
     * 확인할 수 있습니다.
     * 존재할 경우 true, 그렇지 않은 경우는 false 를
     * 반환합니다.
     * @see FileConfiguration#contains(String path)
     * @param path 확인할 경로
     * @return 경로 존재 여부
     */
    public boolean contains(String path) {
        return config.contains(path);
    }

    /**
     * 해당 path 가 파일 내에 기입되어 있지 않다면
     * DefaultDraw 인터페이스를 사용하여
     * 기본 세팅을 추가할 수 있습니다.
     * @see #contains(String path) path 가 존재하는지 확인하려면 이 메소드를 사용하세요.
     * @param path 해당 경로가 파일에 기입되어 있지 않다면
     * @param containsDraw 해당 인터페이스를 실행
     */
    public void isNotContains(String path, ContainsDraw containsDraw) {
        if (!contains(path)) {
            containsDraw.draw();
        }
    }
}
