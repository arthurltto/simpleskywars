package com.zanelatto.simpleskywars.methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SkyWarsScoreboardManager {
    private final JavaPlugin plugin;

    public SkyWarsScoreboardManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void updateScoreboard(Player player, PlayerState state) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("gameStats", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);


        switch (state) {
            case LOBBY:
                objective.setDisplayName("§6SkyWar §fLobby");
                objective.getScore("§a").setScore(10);
                objective.getScore(" Grupo: §7Membro").setScore(9);
                objective.getScore(" Créditos: §a0").setScore(8);
                objective.getScore("§b").setScore(7);
                objective.getScore(" Lobby: §a#1").setScore(6);
                objective.getScore(" Players: §a" + Bukkit.getOnlinePlayers().size()).setScore(5);
                objective.getScore("§c").setScore(4);
                objective.getScore(" §ewww.simplesw.com.br").setScore(3);
                objective.getScore("§d").setScore(2);
                break;
            case WAITING:
                objective.setDisplayName("§6SkyWar");
                objective.getScore("§bJogadores:").setScore(Bukkit.getOnlinePlayers().size());
                break;
            case IN_GAME:
                objective.setDisplayName("§6SkyWar");
                objective.getScore("§bJogadores:").setScore(Bukkit.getOnlinePlayers().size());
                objective.getScore("§bPvPOff:").setScore(5);
                objective.getScore("§bEspectadores:").setScore(3);
                break;
        }

        player.setScoreboard(scoreboard);
    }

    public void startUpdatingScoreboard(Player player, PlayerManager playerManager) {
        new BukkitRunnable() {
            @Override
            public void run() {
                PlayerState state = playerManager.getPlayerState(player);
                updateScoreboard(player, state);
            }
        }.runTaskTimer(plugin, 0, 20);
    }
}
