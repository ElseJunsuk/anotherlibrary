package com.anotherspectrum.anotherlibrary.utils;

import com.anotherspectrum.anotherlibrary.AnotherLibrary;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 * {@link BukkitRunnable}API가 포함되어있는 클래스입니다.
 *
 * @deprecated 현재 개발 준비중인 클래스입니다.
 */
@Deprecated
public class RunnableUtil {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

//        public RunnableHandler setRunningBlock(Running running) {
//            return new RunnableHandler(running);
//        }

//        public static class RunnableHandler {
//
//            private final BukkitRunnable bukkitRunnable;
//            private final Running running;
//
//            private RunnableHandler(Running running) {
//                this.running = running;
//                this.bukkitRunnable = new BukkitRunnable() {
//                    @Override
//                    public void run() {
//                        running.run();
//                    }
//                };
//            }
//
//            public BukkitTask applyLater(long delay) {
//                return this.bukkitRunnable.runTaskLater(.getPlugin(), delay);
//            }
//
//            public BukkitTask applyTimer() {
//                return applyTimer(0L, 20L);
//            }
//
//            public BukkitTask applyTimer(long delay) {
//                return applyTimer(delay, 20L);
//            }
//
//            public BukkitTask applyTimer(long delay, long period) {
//                return this.bukkitRunnable.runTaskTimer(AnotherLibrary.getPlugin(), delay, period);
//            }
//
//            public BukkitTask applyCountdown(final int count, Running whenCancelled) {
//                return applyCountdown(count, 20L, whenCancelled);
//            }
//
//            public BukkitTask applyCountdown(final int count, long period, Running whenCancelled) {
//                int[] counting = new int[] { count };
//                return new BukkitRunnable() {
//                    @Override
//                    public void run() {
//                        if (counting[0] < 1) {
//                            cancel();
//                            whenCancelled.run();
//                        }
//                        running.run();
//                        counting[0]--;
//                    }
//                }.runTaskTimer(AnotherLibrary.getPlugin(), 0L, period);
//            }
//
//        }

        public interface Running {
            void run();
        }

    }

}
