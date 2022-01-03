package fr.ratsock.lobby.listeners.player;

import fr.ratsock.api.API;
import fr.ratsock.api.mysql.PlayerInfo;
import fr.ratsock.api.style.Prefix;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		event.setCancelled(true);
		Player player = event.getPlayer();
		PlayerInfo playerInfo = new PlayerInfo(player);

		if(playerInfo.isMute()){
			player.sendMessage("");
			player.sendMessage(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Pseudo » " + ChatColor.LIGHT_PURPLE + player.getName());
			player.sendMessage(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Mute » " + ChatColor.LIGHT_PURPLE + playerInfo.getEnd_Mute());
			player.sendMessage(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Raison » " + ChatColor.LIGHT_PURPLE + playerInfo.getReason());
			player.sendMessage("");
			player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
			return;
		}

		if(!playerInfo.isActivateChat()) {
			player.sendMessage(Prefix.ERROR + "Tu as désactivé le chat.");
			return;
		}

		for(Player players : Bukkit.getOnlinePlayers()){
			PlayerInfo playersInfo = new PlayerInfo(players);
			if(!playersInfo.isActivateChat()) return;
			System.out.println(playerInfo.getRank().getDisplayname() + " " + player.getName() + ChatColor.DARK_GRAY + " » " + ChatColor.WHITE + event.getMessage());
			if(playerInfo.getRank().getPower() >= 2){
				players.sendMessage(playerInfo.getRank().getDisplayname() + " " + player.getName() + ChatColor.DARK_GRAY + " » " + ChatColor.WHITE + event.getMessage());
				return;
			}
			players.sendMessage(playerInfo.getRank().getDisplayname() + " " + player.getName() + ChatColor.DARK_GRAY + " » " + ChatColor.GRAY + event.getMessage());
		}
	}
}
