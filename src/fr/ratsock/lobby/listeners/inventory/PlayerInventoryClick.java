package fr.ratsock.lobby.listeners.inventory;

import com.google.common.io.ByteArrayDataOutput;
import fr.ratsock.api.API;
import fr.ratsock.api.mysql.PlayerInfo;
import fr.ratsock.api.style.Prefix;
import fr.ratsock.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInventoryClick implements Listener {


	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		if(!event.getClickedInventory().getTitle().equals("§3§lNavigation") && !event.getClickedInventory().getTitle().equals("§9§lStatistique") && !event.getClickedInventory().getTitle().equals("§6§lParamètres") && !event.getClickedInventory().getTitle().equals("§e§lBoutique") && !event.getClickedInventory().getTitle().equals("§5§lProfil")) return;
		Player player = (Player)event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		PlayerInfo playerInfo = new PlayerInfo(player);
		if (current != null) {
			if (current.getType() != null) {
				event.setCancelled(true);
				if ((current.getType() == Material.ENDER_PEARL || current.getType() == Material.EYE_OF_ENDER) && current.getItemMeta().getDisplayName().contains("§9§lMode Modération §8§l┃")) {
					Bukkit.dispatchCommand(player, "mod");
					Lobby.getInstance().getPlayerInteract().GUINav(player);
				}


				if (current.getType() == Material.FEATHER) {
					if (current.getItemMeta().getDisplayName().equals("§f§l► Jump ◄")) {
						Lobby instance = Lobby.getInstance();
						player.teleport(new Location(player.getWorld(), instance.getConfig().getInt("jump.x"), instance.getConfig().getInt("jump.y"), instance.getConfig().getInt("jump.z")));
						Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), () -> {
							player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);
						}, 1L);
					}

					if (current.getItemMeta().getDisplayName().equals("§6§lVoler dans le Hub §e§l☀")) {
						if (PlayerInfo.getPlayerInfo(player).getRank().getPower() >= 1) {
							playerInfo.setActivateFly(!playerInfo.isActivateFly(), player);
							Lobby.getInstance().getPlayerInteract().GUIPara(player);
						} else {
							player.sendMessage(Prefix.ERROR + "Erreur ! Tu n'as pas la permission.");
							player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0F, 1.0F);
						}
					}
				}

				if (current.getType() == Material.BARRIER) {
					if (current.getItemMeta().getDisplayName().equals("§c§lRetour")) {
						Lobby.getInstance().getPlayerInteract().GUINav(player);
					}

					if (current.getItemMeta().getDisplayName().equals("§c§lFermer")) {
						player.closeInventory();
					}
				}

				if (current.getType() == Material.BOOK && current.getItemMeta().getDisplayName().equals("§6§lChat §e§l✎")) {
					playerInfo.setActivateChat(!playerInfo.isActivateChat(), player);
					Lobby.getInstance().getPlayerInteract().GUIPara(player);
				}

				if (current.getType() == Material.SUGAR && current.getItemMeta().getDisplayName().equals("§6§lVitesse §e§l♞")) {
					if (PlayerInfo.getPlayerInfo(player).getRank().getPower() >= 1) {
						if(playerInfo.getSpeed() >= 3) {
							playerInfo.setSpeed(0, player);
							Lobby.getInstance().getPlayerInteract().GUIPara(player);
							return;
						}
						playerInfo.setSpeed(playerInfo.getSpeed() + 1, player);

						Lobby.getInstance().getPlayerInteract().GUIPara(player);
					} else {
						player.sendMessage(Prefix.ERROR + "Erreur ! Vous n'avez pas la permission.");
					}
				}



				String soon = Prefix.ERROR + "§cIndisponible pour le moment.";



				ByteArrayDataOutput out;
				if (current.getType() == Material.SKULL_ITEM) {
					if (current.getItemMeta().getDisplayName().equals("§5§lProfil"))
						Lobby.getInstance().getPlayerInteract().GUIprofil(player);

					if (current.getItemMeta().getDisplayName().equals("§6§lParamètres"))
						Lobby.getInstance().getPlayerInteract().GUIPara(player);

					if (current.getItemMeta().getDisplayName().equals("§e§lBoutique")) {
						Lobby.getInstance().getPlayerInteract().GUIBoutique(player);
						player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1.0F, 1.0F);
					}

					if (current.getItemMeta().getDisplayName().equals("§9Statistique")) {
						Lobby.getInstance().getPlayerInteract().GUIStatistique(player);
						player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1.0F, 1.0F);
					}

					if (current.getItemMeta().getDisplayName().equals("§e§lSocial")) {
						player.sendMessage(soon);
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 1.0F);
					}

					if (current.getItemMeta().getDisplayName().equals("§2§lVos succès")) {
						player.sendMessage(soon);
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 1.0F);
					}

					if (current.getItemMeta().getDisplayName().equals("§2§lSuccès")) {
						player.sendMessage(soon);
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 1.0F);
					}

					if (current.getItemMeta().getDisplayName().equals("§3§lRequête d'ami §d§l❤")) {
						player.sendMessage(soon);
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 1.0F);
					}

					if (current.getItemMeta().getDisplayName().equals("§3§lRequête de groupe §9§l◕")) {
						player.sendMessage(soon);
						player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 1.0F);
					}

					if (current.getItemMeta().getDisplayName().equals("§3§l► Spawn ◄")) {
						Lobby instance = Lobby.getInstance();
						player.teleport(new Location(player.getWorld(), instance.getConfig().getInt("spawn.x"), instance.getConfig().getInt("spawn.y"), instance.getConfig().getInt("spawn.z"), instance.getConfig().getLong("spawn.pitch"), instance.getConfig().getLong("spawn.yaw")));
						Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), () -> {
							player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);
						}, 1L);
					}
				}
			}
		}
	}


}
