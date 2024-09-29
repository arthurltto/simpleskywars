package com.zanelatto.simpleskywars;

import com.zanelatto.simpleskywars.commands.GameModeCommand;
import com.zanelatto.simpleskywars.commands.SetLobbyCommand;
import com.zanelatto.simpleskywars.events.ItemClickListener;
import com.zanelatto.simpleskywars.events.PlayerEvent;
import com.zanelatto.simpleskywars.events.PlayerJoinListener;
import com.zanelatto.simpleskywars.methods.PlayerManager;
import com.zanelatto.simpleskywars.methods.Protection;
import com.zanelatto.simpleskywars.methods.SkyWarsScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private PlayerManager playerManager;
    private SkyWarsScoreboardManager scoreboardManager;
    private static Main instance;
    private SetLobbyCommand setLobbyCommand;

    @Override
    public void onEnable() {
        setLobbyCommand = new SetLobbyCommand(this);
        scoreboardManager = new SkyWarsScoreboardManager(this);
        playerManager = new PlayerManager(scoreboardManager);
        instance = this;
        this.saveDefaultConfig();
        Eventos();
        Comandos();
        CommandSender cs = Bukkit.getConsoleSender();
        cs.sendMessage(" ");
        cs.sendMessage(" §7- §eSimple SkyWars §7-");
        cs.sendMessage(" §aPlugin ativado com sucesso.");
        cs.sendMessage(" §7Desenvolvido por §d@znltto");
        cs.sendMessage(" ");
    }

    @Override
    public void onDisable() {
        CommandSender cs = Bukkit.getConsoleSender();
        cs.sendMessage(" ");
        cs.sendMessage(" §7- §eSimple SkyWars §7-");
        cs.sendMessage(" §cPlugin desativado com sucesso.");
        cs.sendMessage(" §7Desenvolvido por §d@znltto");
        cs.sendMessage(" ");
    }

    public void Eventos() {
        Bukkit.getPluginManager().registerEvents(new PlayerEvent(playerManager, scoreboardManager), this);
        Bukkit.getPluginManager().registerEvents(new Protection(playerManager), this);
        Bukkit.getPluginManager().registerEvents(new ItemClickListener(this), this);
    }

    public void Comandos() {

        this.getCommand("setlobby").setExecutor(setLobbyCommand);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(setLobbyCommand), this);

        this.getCommand("gm").setExecutor(new GameModeCommand());
    }

    public static Main getInstance() {
        return instance;
    }

}
