package fr.ratsock.lobby;

import fr.ratsock.lobby.commands.manager.CommandsManager;
import fr.ratsock.lobby.listeners.inventory.PlayerInventoryClick;
import fr.ratsock.lobby.listeners.player.ChatEvent;
import fr.ratsock.lobby.listeners.player.JoinAndQuitListeners;
import fr.ratsock.lobby.listeners.ProtectSpawnListeners;
import fr.ratsock.lobby.listeners.inventory.PlayerInteractInventory;
import fr.ratsock.lobby.utils.scoreboard.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Lobby extends JavaPlugin {

	public static Lobby instance;
	private ScoreboardManager scoreboardManager;
	private ScheduledExecutorService executorMonoThread;
	private ScheduledExecutorService scheduledExecutorService;

	private PlayerInteractInventory playerInteract;

	@Override
	public void onEnable() {
		saveDefaultConfig();
		instance = this;


		Bukkit.getPluginManager().registerEvents(new JoinAndQuitListeners(), this);
		Bukkit.getPluginManager().registerEvents(new ProtectSpawnListeners(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerInteractInventory(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerInventoryClick(), this);
		Bukkit.getPluginManager().registerEvents(new ChatEvent(), this);

		playerInteract = new PlayerInteractInventory();

		Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		World world = Bukkit.getWorld("world");
		world.setDifficulty(Difficulty.PEACEFUL);
		world.setTime(1000L);
		world.setGameRuleValue("doDaylightCycle", "false");
		world.setGameRuleValue("doMobSpawning", "false");
		world.setGameRuleValue("randomTickSpeed", "0");
		world.setGameRuleValue("keepInventory", "true");

		Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "weather clear");


		scheduledExecutorService = Executors.newScheduledThreadPool(16);
		executorMonoThread = Executors.newScheduledThreadPool(1);
		scoreboardManager = new ScoreboardManager();

		CommandsManager.setupCommandsSystem(this);

	}


	public static Lobby getInstance() {
		return instance;
	}


	public ScheduledExecutorService getExecutorMonoThread() {
		return executorMonoThread;
	}

	public ScheduledExecutorService getScheduledExecutorService() {
		return scheduledExecutorService;
	}

	public ScoreboardManager getScoreboardManager() {
		return scoreboardManager;
	}

	public PlayerInteractInventory getPlayerInteract() {
		return playerInteract;
	}
}
