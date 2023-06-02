package com.anotherspectrum.anotherlibrary;

import com.anotherspectrum.anotherlibrary.menu.listener.InventoryListeners;
import com.google.common.base.Preconditions;
import lombok.Getter;
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
 * @see #AnotherLibrary(JavaPlugin)
 * @since 0.1.0 - UPDATE FOR 0.5.0-SNAPSHOT
 */
public final class AnotherLibrary {

    // 라이브러리가 활성화되어 있는가
    private static boolean enabled;

    // JavaPlugin 클래스를 상속받는 메인 클래스
    private static @Getter JavaPlugin plugin;

    /**
     * 라이브러리의 소스를 사용하려면 반드시
     * 해당 생성자가 필요합니다.
     *
     * @param plugin 메인 클래스
     */
    public AnotherLibrary(@NotNull JavaPlugin plugin) {
        Preconditions.checkNotNull(plugin);
        enabled = true;
        AnotherLibrary.plugin = plugin;

        new InventoryListeners(plugin);
    }

    /**
     * @return 라이브러리가 활성화되어 있는가
     */
    public static boolean isEnabled() {
        return enabled;
    }

    /**
     *
     * @return 라이브러리가 어느 프로젝트에 정상적으로 연결되어 있는가
     */
    public static boolean checkValid() {
        return plugin != null;
    }

}
