package com.anotherspectrum.anotherlibrary;

import com.anotherspectrum.anotherlibrary.menu.listener.InventoryListeners;
import com.google.common.base.Preconditions;
import lombok.Getter;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * <pre>{@code
 * // 프로젝트 메인 클래스에 아래와 같이 라이브러리 필드를 먼저 생성해주세요.
 * private AnotherLibrary anotherLibrary;
 *
 * // 그 후, 재정의를 해줌으로써 플러그인에 라이브러리를 등록시킬 수 있습니다.
 * this.anotherLibrary = new AnotherLibrary(JavaPlugin);
 * }</pre>
 *
 * @see #AnotherLibrary(Plugin)
 * @since 0.1.0 - UPDATE FOR 0.5.0-SNAPSHOT
 */
public final class AnotherLibrary {

    // JavaPlugin 클래스를 상속받는 메인 클래스
    private final Plugin plugin;

    /**
     * 라이브러리의 소스를 사용하려면 반드시
     * 해당 생성자가 필요합니다.
     *
     * @param plugin 메인 클래스
     */
    public AnotherLibrary(@NotNull Plugin plugin) {
        Preconditions.checkNotNull(plugin);
        this.plugin = plugin;

        new InventoryListeners(plugin);
    }

    /**
     *
     * @return 라이브러리가 연결된 프로젝트
     */
    public Plugin getPlugin() {
        return plugin;
    }

}
