package com.zanelatto.simpleskywars.commands;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SetLobbyCommand implements CommandExecutor {

    private final JavaPlugin plugin; // Referência para o plugin
    private Location lobbyLocation;

    public SetLobbyCommand(JavaPlugin plugin) {
        this.plugin = plugin;
        loadLobbyLocation(); // Carrega a localização ao iniciar
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("setlobby")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                // Captura a localização e a direção do jogador
                lobbyLocation = player.getLocation();
                saveLobbyLocation(); // Salva a localização no arquivo

                // Mensagem de confirmação
                player.sendMessage("§e[§fSimple SkyWars§e] §aLobby definido com sucesso!");

                return true;
            } else {
                sender.sendMessage("Apenas jogadores podem usar este comando.");
                return true;
            }
        }
        return false;
    }

    public Location getLobbyLocation() {
        return lobbyLocation;
    }

    private void saveLobbyLocation() {
        FileConfiguration config = plugin.getConfig();
        config.set("lobby.world", lobbyLocation.getWorld().getName());
        config.set("lobby.x", lobbyLocation.getX());
        config.set("lobby.y", lobbyLocation.getY());
        config.set("lobby.z", lobbyLocation.getZ());
        config.set("lobby.yaw", lobbyLocation.getYaw());
        config.set("lobby.pitch", lobbyLocation.getPitch());
        plugin.saveConfig(); // Salva o arquivo de configuração
    }

    private void loadLobbyLocation() {
        FileConfiguration config = plugin.getConfig();
        if (config.contains("lobby")) {
            String worldName = config.getString("lobby.world");
            double x = config.getDouble("lobby.x");
            double y = config.getDouble("lobby.y");
            double z = config.getDouble("lobby.z");
            float yaw = (float) config.getDouble("lobby.yaw");
            float pitch = (float) config.getDouble("lobby.pitch");

            // Cria a nova localização
            lobbyLocation = new Location(plugin.getServer().getWorld(worldName), x, y, z, yaw, pitch);
        }
    }
}
