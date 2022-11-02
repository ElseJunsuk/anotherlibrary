package com.anotherspectrum.anotherlibrary;

import com.google.common.base.Preconditions;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * @since 0.1.0
 * @update 0.1.2/02-11-22
 * @see #getKey() add NamespacedKey
 */
public final class AnotherLibrary {

    private final NamespacedKey key;
    private final JavaPlugin plugin;
    private static String pluginName;

    /**
     * 해당 라이브러리의 소스를 사용하려면 반드시
     * 메인 클래스가 필요합니다.
     * @param plugin
     */
    public AnotherLibrary(@NotNull String pluginName, @NotNull JavaPlugin plugin) {
        Preconditions.checkNotNull(pluginName);
        Preconditions.checkNotNull(plugin);

        this.plugin = plugin;
        this.pluginName = pluginName;
        this.key = NamespacedKey.minecraft(pluginName);
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public static String getPluginName() {
        return pluginName;
    }

    public NamespacedKey getKey() {
        return key;
    }

}
