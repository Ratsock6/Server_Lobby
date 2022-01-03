package fr.ratsock.lobby.listeners.player;

import fr.ratsock.api.API;
import fr.ratsock.api.mysql.PlayerInfo;
import fr.ratsock.api.style.Prefix;
import fr.ratsock.lobby.Lobby;
import fr.ratsock.lobby.utils.item.ItemBuilder;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class JoinAndQuitListeners implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();

		player.setHealth(player.getMaxHealth());
		player.setFoodLevel(20);

		Lobby instance = Lobby.getInstance();
		event.setJoinMessage(null);

		player.getInventory().setItem(8, (new ItemBuilder(Material.SKULL_ITEM, 1, (byte)3)).setSkullOwner("yeshjho_").setName("§2§lSuccès").toItemStack());
		player.getInventory().setItem(7, (new ItemBuilder(Material.SKULL_ITEM, 1, (byte)3)).setSkullOwner("Alistor").setName("§6§lParamètres").toItemStack());
		player.getInventory().setItem(4, (new ItemBuilder(Material.SKULL_ITEM, 1, (byte)3)).setSkullOwner("AverageJoe").setName("§3§lNavigation").toItemStack());
		player.getInventory().setItem(1, (new ItemBuilder(Material.SKULL_ITEM, 1, (byte)3)).setSkullOwner("Tom25W").setName("§e§lBoutique").toItemStack());
		player.getInventory().setItem(0, (new ItemBuilder(Material.SKULL_ITEM, 1, (byte)3)).setSkullOwner(player.getName()).setName("§5§lProfil").toItemStack());
		player.setGameMode(GameMode.ADVENTURE);
		Lobby.getInstance().getScoreboardManager().onLogin(player);

		player.teleport(new Location(player.getWorld(), instance.getConfig().getInt("spawn.x"), instance.getConfig().getInt("spawn.y"), instance.getConfig().getInt("spawn.z"), instance.getConfig().getLong("spawn.pitch"), instance.getConfig().getLong("spawn.yaw")));
		for (int i = 0; i < 10; i++) {
			player.sendMessage(" ");
		}


		player.setWalkSpeed(0.2F);
		player.sendMessage("    §b§lBienvenue sur " + API.getInstance().getStyle().getServerdName());
		player.sendMessage(" ");
		player.sendMessage("     §eVoici ce qui peut vous aider !");
		player.sendMessage(" ");
		player.sendMessage("       §6§l» §b/aide");
		player.sendMessage("       §6§l» §b/liens");
		player.sendMessage(" ");

		PlayerInfo playerInfo = new PlayerInfo(player);

		playerInfo.setActivateFly(false, player);

		if(playerInfo.getRank().getPower() >= 1){
			Bukkit.broadcastMessage(Prefix.NEUTRAL + playerInfo.getRank().getDisplayname() + " " + player.getName() + ChatColor.GRAY + " vient de rejoindre le serveur !");
		}

		if(playerInfo.getRank().getPower() >= 2){
			for(Player players: Bukkit.getOnlinePlayers()){
				players.playSound(player.getLocation(), Sound.AMBIENCE_THUNDER, 1.0F, 1.0F);
			}
			player.getWorld().strikeLightningEffect(player.getLocation());
			playerInfo.setActivateFly(true, player);
		}


		API.getInstance().getApiUtils().getScoreboard().getTeam(playerInfo.getRank().getOrdername()).addPlayer(player);






	}


	@EventHandler
	public void onQuit(PlayerQuitEvent event){
		Player player = event.getPlayer();
		PlayerInfo playerInfo = new PlayerInfo(player);
		playerInfo.setModeMod(false, player);
		Lobby.getInstance().getScoreboardManager().onLogout(player);
		event.setQuitMessage(null);
	}
}
