package com.zanelatto.simpleskywars.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Este comando só pode ser usado por jogadores!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            // Sem argumentos, alterna o modo de jogo do jogador
            player.setGameMode(player.getGameMode() == GameMode.SURVIVAL ? GameMode.CREATIVE : GameMode.SURVIVAL);
            player.sendMessage("Seu modo de jogo foi alternado.");
            return true;
        }

        if (args.length == 1) {
            // Se um argumento foi passado
            String modeArg = args[0].toLowerCase();
            GameMode gameMode;

            switch (modeArg) {
                case "0":
                case "survival":
                    gameMode = GameMode.SURVIVAL;
                    break;
                case "1":
                case "creative":
                    gameMode = GameMode.CREATIVE;
                    break;
                case "2":
                case "adventure":
                    gameMode = GameMode.ADVENTURE;
                    break;
                case "3":
                case "spectator":
                    gameMode = GameMode.SPECTATOR;
                    break;
                default:
                    player.sendMessage("Modo de jogo inválido! Use: /gm [0|1|2|3|survival|creative|adventure|spectator]");
                    return true;
            }

            player.setGameMode(gameMode);
            player.sendMessage("Seu modo de jogo foi definido como " + gameMode.name().toLowerCase() + ".");
            return true;
        }

        return false; // Comando inválido
    }
}
