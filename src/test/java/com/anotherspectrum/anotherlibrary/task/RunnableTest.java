package com.anotherspectrum.anotherlibrary.task;

import com.anotherspectrum.anotherlibrary.utils.RunnableUtil;
import org.bukkit.scheduler.BukkitTask;
import org.junit.jupiter.api.Test;

class RunnableTest {

    @Test
    void test() {
        BukkitTask someOne = RunnableUtil.builder()
                .setRunningBlock(() -> {
                    // some code..
                }).applyCountdown(5, 20L, () -> {
                    // if cancelled..
                });

        BukkitTask someTwo = RunnableUtil.builder()
                .setRunningBlock(() -> {
                    // some code..
                }).applyTimer();
    }

}