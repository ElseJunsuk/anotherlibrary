package com.anotherspectrum.anotherlibrary;

import com.anotherspectrum.anotherlibrary.menu.listener.InventoryListeners;
import com.google.common.base.Preconditions;
import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

/**
 * <pre>{@code
 * // 메인 클래스에 아래와 같이 라이브러리 필드를 먼저 생성해주세요.
 * private AnotherLibrary anotherLibrary;
 *
 * // 그 후 재정의를 해줌으로써 플러그인에 라이브러리를 등록시킬 수 있습니다.
 * this.anotherLibrary = new AnotherLibrary(...parms);
 * }</pre>
 *
 * @see #AnotherLibrary(String, JavaPlugin)
 * @since 0.1.0 - UPDATE FOR 0.3.0
 */
public final class AnotherLibrary {

    // 라이브러리가 활성화되어 있는가
    private static boolean enabled;

    // 플러그인의 NamespacedKey
    private final @Getter NamespacedKey key;
    // JavaPlugin 클래스를 상속받는 메인 클래스
    private final @Getter JavaPlugin plugin;

    /**
     * 해당 라이브러리의 소스를 사용하려면 반드시
     * 메인 클래스가 필요합니다.
     * 또한 NamespacedKey 이름을 사용자 설정할 수 있습니다.
     * <p></p>
     * <p>첫번째 파라미터에는 다음이 포함되어선 안 됩니다.</p>
     * <li>숫자</li>
     * <li>공백</li>
     * <li>특수문자</li>
     * <li>영어 이외의 문자</li>
     *
     * @param nameSpacedKey {@link NamespacedKey}
     * @param plugin        메인 클래스
     */
    public AnotherLibrary(@NotNull String nameSpacedKey, @NotNull JavaPlugin plugin) {
        Preconditions.checkNotNull(plugin);
        enabled = true;

        this.plugin = plugin;
        this.key = NamespacedKey.minecraft(nameSpacedKey.toLowerCase(Locale.ROOT));
        new InventoryListeners(plugin);
    }

    /**
     * @return 라이브러리가 활성화되어 있는가
     */
    public static boolean isEnabled() {
        return enabled;
    }

}
