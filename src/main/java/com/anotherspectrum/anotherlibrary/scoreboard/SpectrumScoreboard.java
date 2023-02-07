package com.anotherspectrum.anotherlibrary.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpectrumScoreboard {

    private ScoreboardManager manager;
    private Scoreboard board;

    private final List<UUID> activePlayers = new ArrayList<>();

    public SpectrumScoreboard() {
        this.manager = Bukkit.getScoreboardManager();
        this.board = manager.getNewScoreboard();
    }

    public void addPlayer(Player player) {
        if (activePlayers.contains(player.getUniqueId())) return;
        activePlayers.add(player.getUniqueId());
    }

    public void removePlayer(Player player) {
        if (!activePlayers.contains(player.getUniqueId())) return;
        player.setScoreboard(manager.getMainScoreboard());
        activePlayers.remove(player.getUniqueId());
    }


}
