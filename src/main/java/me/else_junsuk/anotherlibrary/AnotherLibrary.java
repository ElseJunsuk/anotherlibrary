package me.else_junsuk.anotherlibrary;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * @since 0.1.0
 * @update 0.1.1/30-10-22
 */
public final class AnotherLibrary {

    private final JavaPlugin plugin;
    private static String pluginName;

    /**
     * 해당 라이브러리의 소스를 사용하려면 반드시
     * 메인 클래스가 필요합니다.
     * @param plugin
     */
    public AnotherLibrary(@NotNull String pluginName, @NotNull JavaPlugin plugin) {
        this.plugin = plugin;
        this.pluginName = pluginName;
    }

    public static String getPluginName() {
        return pluginName;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

}
