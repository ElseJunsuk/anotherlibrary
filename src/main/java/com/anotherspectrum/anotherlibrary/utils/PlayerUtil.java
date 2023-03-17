package com.anotherspectrum.anotherlibrary.utils;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * 플레이어 관련 툴이 제공됩니다.
 *
 * @since 0.1.0 - UPDATE FOR 0.3.0
 */
public final class PlayerUtil {

    /**
     * 서버에 접속해있는 '플레이어의 이름' 을 사용하여 해당 플레이어를 반환합니다.
     *
     * @param name 타겟 플레이어의 닉네임
     * @return 서버에 접속중인 name 닉네임을 가진 플레이어
     */
    @Deprecated
    public static Player getTarget(String name) {
        Player target = Bukkit.getPlayer(name);
        if (target == null)
            throw new NullPointerException("[AnotherLibrary] '" + name + "' 닉네임을 가진 플레이어가 현재 서버에 존재하지 않습니다.");
        return target;
    }

    /**
     * 서버의 접속해있는 '플레이어의 UUID' 를 사용하여 해당 플레이어를 반환합니다.
     *
     * @param uuid 타겟 플레이어의 {@link UUID}
     * @return 서버에 접속중인 플레이어 중 해당 uuid 를 가진 플레이어
     */
    @Deprecated
    public static Player getTarget(UUID uuid) {
        Player target = Bukkit.getPlayer(uuid);
        if (target == null)
            throw new NullPointerException("[AnotherLibrary] '" + uuid + "' UUID 를 가진 플레이어가 현재 서버에 존재하지 않습니다.");
        return target;
    }

    /**
     * 서버에 접속중이 아닐 수도 있는 플레이어의 '닉네임' 를 사용해 오프라인 플레이어로 가져옵니다.
     *
     * @param name 타겟 플레이어의 닉네임
     * @return {@link OfflinePlayer}
     */
    @Deprecated
    public static OfflinePlayer getOfflineTarget(String name) {
        return Bukkit.getOfflinePlayer(name);
    }

    /**
     * 서버에 접속중이 아닐 수도 있는 플레이어의 'UUID' 를 사용해 오프라인 플레이어로 가져옵니다.
     *
     * @param uuid 타겟 플레이어의 {@link UUID}
     * @return {@link OfflinePlayer}
     */
    @Deprecated
    public static OfflinePlayer getOfflineTarget(UUID uuid) {
        return Bukkit.getOfflinePlayer(uuid);
    }

}
