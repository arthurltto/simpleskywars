package com.zanelatto.simpleskywars.methods;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PlayerManager {
    private Map<Player, PlayerState> playerStates = new HashMap<>();
    private final SkyWarsScoreboardManager scoreboardManager;

    public PlayerManager(SkyWarsScoreboardManager scoreboardManager) {
        this.scoreboardManager = scoreboardManager;
    }

    public void setPlayerState(Player player, PlayerState state) {
        playerStates.put(player, state);
        scoreboardManager.updateScoreboard(player, state); // Atualiza a scoreboard

        switch (state) {
            case LOBBY:
                player.sendMessage(" ");
                player.sendMessage("§aVocê está conectado ao Lobby-SA01!");
                player.sendMessage(" ");
                player.getInventory().clear();
                ItemUtils.addItemToPlayer(player, "§eMenu", Material.STORAGE_MINECART, 4);
                break;
            case WAITING:
                player.sendMessage(" ");
                player.sendMessage("§aVocê está conectado a sala DesertLands-SW01!");
                player.sendMessage(" ");
                player.getInventory().clear();
                ItemUtils.addItemToPlayer(player, "§eKits", Material.CHEST, 3);
                ItemUtils.addItemToPlayer(player, "§eLoja", Material.EMERALD, 5);
                break;
            case IN_GAME:
                player.sendMessage(" ");
                player.sendMessage("A partida começou!");
                player.sendMessage(" ");
                player.getInventory().clear();
                break;
        }
    }

    public PlayerState getPlayerState(Player player) {
        return playerStates.getOrDefault(player, PlayerState.LOBBY);
    }

    public void removePlayer(Player player) {
        playerStates.remove(player);
    }

    public Collection<Player> getAllPlayers() {
        return playerStates.keySet(); // Retorna todos os jogadores gerenciados
    }


}
