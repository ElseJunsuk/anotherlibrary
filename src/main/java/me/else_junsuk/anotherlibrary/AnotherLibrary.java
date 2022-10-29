package me.else_junsuk.anotherlibrary;

import org.bukkit.plugin.java.JavaPlugin;

public final class AnotherLibrary {

    private final JavaPlugin plugin;

    /**
     * 해당 라이브러리의 소스를 사용하려면 반드시
     * 메인 클래스가 필요합니다.
     * @param plugin
     */
    public AnotherLibrary(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

}
