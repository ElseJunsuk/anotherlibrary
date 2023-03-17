package com.anotherspectrum.anotherlibrary.utils;

import lombok.Getter;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;

/**
 * 파일 생성/관리에 필요한 도구가 제공됩니다.
 *
 * @since 0.2.8 - UPDATE FOR 0.3.4
 */
public final class ConfigurationUtil implements Serializable {

    private final File file;
    private FileConfiguration config;

    /**
     * 구성 파일을 생성하기 위한 인스턴스입니다.
     *
     * @param folderPath 데스크톱 폴더 경로
     * @param fileName   해당 폴더에 생성할 확장자명이 포함된 파일의 이름
     */
    public ConfigurationUtil(@NotNull String folderPath, @NotNull String fileName) {
        if (folderPath.isBlank() || folderPath.isEmpty())
            throw new NullPointerException("[AnotherLibrary] File Build 중 오류가 발생했습니다. (folderPath 는 null 일 수 없습니다.)");
        if (fileName.isBlank() || fileName.isEmpty())
            throw new NullPointerException("[AnotherLibrary] File Build 중 오류가 발생했습니다. (fileName 은 null 일 수 없습니다.)");

        final File folder = new File(folderPath);
        if (!folder.exists()) folder.mkdirs();
        this.file = new File(folder, fileName);
        this.config = YamlConfiguration.loadConfiguration(this.file);

        this.save();
    }

    /**
     * 인스턴스 생성 시 기입한 첫번째 파라미터의 경로에 위치한
     * 두번째 파라미터에 기입한 이름의 파일을 불러옵니다.
     *
     * @return {@link File}
     */
    public File getFile() {
        return file;
    }

    /**
     * 생성된 파일에 접근할 수 있습니다.
     *
     * @return {@link FileConfiguration}
     */
    public FileConfiguration getConfig() {
        return config;
    }

    /**
     * 플레이어의 닉네임, UUID 를 파일에 추가합니다.
     * 이는 플레이어의 데이터 파일 속 반 필수적으로 있어야 하는
     * 경로입니다.
     *
     * @param player 대상 플레이어
     * @return 해당 클래스를 한 번 더 리턴하여 내부 메소드를 이용할 수 있음
     */
    public ConfigurationUtil setDefaultUserInformationToConfig(Player player) {
        return setDefaultUserInformationToConfig(player.getName(), player.getUniqueId());
    }

    /**
     * 플레이어의 닉네임, UUID 를 파일에 추가합니다.
     * 이는 플레이어의 데이터 파일 속 반 필수적으로 있어야 하는
     * 경로입니다.
     *
     * @param offlinePlayer 대상 오프라인 플레이어
     * @return 해당 클래스를 한 번 더 리턴하여 내부 메소드를 이용할 수 있음
     */
    public ConfigurationUtil setDefaultUserInformationToConfig(OfflinePlayer offlinePlayer) {
        return setDefaultUserInformationToConfig(Objects.requireNonNull(offlinePlayer.getName()), offlinePlayer.getUniqueId());
    }

    public ConfigurationUtil setDefaultUserInformationToConfig(@NotNull String name, @NotNull UUID uuid) {
        config.set("name", name);
        config.set("uuid", uuid.toString());
        return this;
    }

    /**
     * 첫번째 파라미터 속 문자열에 기입된 경로에
     * 두번째 파라미터에 기입된 값이 설정됩니다.
     *
     * @param path  파일 속 섹션 경로
     * @param value 해당 경로의 값
     * @return 해당 클래스를 한 번 더 리턴하여 내부 메소드를 이용할 수 있음
     */
    public ConfigurationUtil andThen(String path, Object value) {
        if (config == null)
            throw new NullPointerException("[AnotherLibrary] File 접근/수정 중 오류가 발생했습니다. (config 가 null 입니다.)");
        if (path == null)
            throw new NullPointerException("[AnotherLibrary] File 접근/수정 중 오류가 발생했습니다. (path 가 null 입니다.)");
        if (value == null)
            throw new NullPointerException("[AnotherLibrary] File 접근/수정 중 오류가 발생했습니다. (value 가 null 입니다.)");
        if (!config.contains(path))
            config.set(path.toLowerCase(Locale.ROOT), value);
        return this;
    }

    /**
     * 빌더로 변경된 모든 사항을 저장하며,
     * 무언가 실행하고자 하는 코드를 실행합니다.
     *
     * @param saving 경로/값 추가 코드
     */
    public void save(@Nullable Supplier<String> saving) {
        if (file == null)
            throw new NullPointerException("[AnotherLibrary] File save 중 오류가 발생했습니다. (file 이 null 입니다.)");
        if (config == null)
            throw new NullPointerException("[AnotherLibrary] File save 중 오류가 발생했습니다. (config 가 null 입니다.)");
        if (saving != null) {
            String[] split = saving.get().split(" ");
            if (split.length <= 1) {
                config.set(split[0], "ERROR -> 공백을 기준으로 올바르게 Split 처리할 수 없습니다.");
                save();
                return;
            }
            config.set(split[0], split[1]);
        }
        save();
    }

    /**
     * 파일에서 변경된 모든 사항을 저장합니다.
     */
    public void save() {
        try {
            config.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * {@link InputStream} 을 {@link File} 로 카피합니다.
     *
     * @param resource {@link org.bukkit.plugin.java.JavaPlugin} 내부 resource 파일
     * @param file     카피 대상(target) 파일
     */
    public static void copyInputStreamToFile(@NotNull InputStream resource, @NotNull File file) throws IOException {
        if (resource == null)
            throw new NullPointerException("[AnotherLibrary] 스트림 복사 중 오류가 발생했습니다. (InputStream 이 null 입니다.)");
        if (file == null)
            throw new NullPointerException("[AnotherLibrary] 스트림 복사 중 오류가 발생했습니다. (File 이 null 입니다.)");
        final FileOutputStream outputStream = new FileOutputStream(file);
        int read;
        byte[] bytes = new byte[1024];
        while ((read = resource.read(bytes)) > 0)
            outputStream.write(bytes, 0, read);

        resource.close();
        outputStream.close();
    }

    @Override
    public String toString() {
        return "ConfigurationUtil{" +
                "file=" + file +
                ", config=" + config +
                '}';
    }
}
