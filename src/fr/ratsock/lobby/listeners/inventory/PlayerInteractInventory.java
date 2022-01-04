package fr.ratsock.lobby.listeners.inventory;

import fr.ratsock.api.API;
import fr.ratsock.api.mysql.PlayerInfo;
import fr.ratsock.api.style.Prefix;
import fr.ratsock.lobby.utils.head.HeadList;
import fr.ratsock.lobby.utils.item.ItemBuilder;
import fr.uhc.manager.game.Mode;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class PlayerInteractInventory implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		event.setCancelled(true);
		Player player = event.getPlayer();
		ItemStack it = event.getItem();
		if (it != null) {
			if (it.getType() == Material.SKULL_ITEM) {
				if (it.getItemMeta().getDisplayName().equals("§5§lProfil")) {
					GUIprofil(player);
				}

				if (it.getItemMeta().getDisplayName().equals("§e§lBoutique")) {
					GUIBoutique(player);
				}

				if (it.getItemMeta().getDisplayName().equals("§3§lNavigation")) {
					GUINav(player);
				}

				if (it.getItemMeta().getDisplayName().equals("§6§lParamètres")) {
					this.GUIPara(player);
				}

				if (it.getItemMeta().getDisplayName().equals("§2§lSuccès")) {
					player.sendMessage(Prefix.ERROR + "Indisponible pour le moment.");
					player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1.0F, 1.0F);
				}
			}

		}
	}

	public void infoGUI(Inventory inventory, Player player){
		PlayerInfo playerInfo = new PlayerInfo(player);
		inventory.setItem(4, (new ItemBuilder(Material.SKULL_ITEM, 1, (byte)3)).setSkullOwner(player.getName()).setName("§5§lProfil").setLoreList(Arrays.asList(" ", "§e§lInformations", " §7Compte » §b§n" + player.getName(), " §7Grade » " + playerInfo.getRank().getDisplayname(), " §7Temps » " + ChatColor.LIGHT_PURPLE + playerInfo.getTimeLeft(player), " §7Date d'arrivé » §e" + playerInfo.getJoinDate(), " ", "§e§lMonnaie", API.getInstance().getStyle().getCoinsColor() + API.getInstance().getStyle().getCoinsName() + " §7» " + API.getInstance().getStyle().getCoinsColor() + playerInfo.getCoins() + API.getInstance().getStyle().getLogoCoin(), API.getInstance().getStyle().getArgentColor() + API.getInstance().getStyle().getArgentName() + " §7» " + API.getInstance().getStyle().getArgentColor() + playerInfo.getMoney() + API.getInstance().getStyle().getLogoArgent(), " ")).toItemStack());
	}


	public void GUIprofil(Player player) {
		player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1.0F, 1.0F);
		Inventory profil = Bukkit.createInventory(null, 54, "§5§lProfil");
		int[] cases = new int[]{0, 1, 2, 6, 7, 8, 9, 17, 36, 44, 45, 46, 47, 48, 49, 50, 53, 52, 51};
		ItemStack i = (new ItemBuilder(Material.STAINED_GLASS_PANE, 1, API.getInstance().getStyle().getWindowColorProfil())).setName(API.getInstance().getStyle().getWindowName()).toItemStack();

		for (int c : cases) {
			profil.setItem(c, i);
		}

		infoGUI(profil, player);

		profil.setItem(21, (new ItemBuilder(HeadList.SOCIAL.getItemStack())).setName("§e§lSocial").setLoreList(Arrays.asList(" ", " ", " §7Permet de récuperer des informations", " §7sur vos §damis§7.", " ")).toItemStack());
		profil.setItem(22, (new ItemBuilder(HeadList.COSMETIQUE.getItemStack())).setName("§3§lCosmétiques").setLoreList(Arrays.asList(" ", " ", " §7Accede aux menus des §dcosmétiques §7pour", " §7pimper ton personnage mais aussi", " §7accèder à des §6gadjets §7des plus farfelus.", " ")).toItemStack());
		profil.setItem(23, (new ItemBuilder(HeadList.PROPOS.getItemStack())).setName("§9A Propos").setLoreList(Arrays.asList(" ", " ", " §7Permet de s'informer sur", " §7l'histoire du §bserveur §7et ses §epéripéties§7.", " ")).toItemStack());
		profil.setItem(31, (new ItemBuilder(Material.SKULL_ITEM, 1, (byte)3)).setSkullOwner("yeshjho_").setName("§2§lVos succès").setLoreList(Arrays.asList(" ", " ", " §7Permet de se diriger vers vos §3succès§7", " §7sur le serveur.", " ")).toItemStack());
		profil.setItem(49, (new ItemBuilder(Material.BARRIER)).setName("§c§lRetour").toItemStack());
		player.openInventory(profil);
	}

	public void GUIBoutique(Player player) {
		player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1.0F, 1.0F);
		Inventory boutique = Bukkit.createInventory((InventoryHolder)null, 54, "§e§lBoutique");

		for (int i = 0; i < 52; i++) {
			boutique.setItem(i, new ItemBuilder(Material.BARRIER).setName(ChatColor.RED + ChatColor.BOLD.toString() + "MAINTENANCE").toItemStack());
		}

		int[] cases = new int[]{0, 1, 2, 6, 7, 8, 9, 17, 36, 44, 45, 46, 47, 53, 52, 51};
		ItemStack i = (new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte)4)).setName("§b§k!!!!!").toItemStack();

		for (int c : cases) {
			boutique.setItem(c, i);
		}

		infoGUI(boutique, player);

		boutique.setItem(22, new ItemBuilder(Material.SIGN).setName(ChatColor.GOLD + "à voir directement avec Ratsock#3213.").addEnchantment(Enchantment.DURABILITY, 1).addFlag(ItemFlag.HIDE_ENCHANTS).toItemStack());
		boutique.setItem(49, (new ItemBuilder(Material.BARRIER)).setName("§c§lRetour").toItemStack());

		player.openInventory(boutique);
	}



	public void GUINav(Player player) {
		player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1.0F, 1.0F);
		PlayerInfo playerInfo = new PlayerInfo(player);
		Inventory nav = Bukkit.createInventory((InventoryHolder)null, 54, "§3§lNavigation");
		int[] cases = new int[]{1, 2, 6, 7, 8, 9, 17, 36, 44, 45, 46, 47, 53, 52, 51};
		ItemStack i = (new ItemBuilder(Material.STAINED_GLASS_PANE, 1, API.getInstance().getStyle().getWindowColorNav())).setName("§b§k!!!!!").toItemStack();

		for (int c : cases) {
			nav.setItem(c, i);
		}

		infoGUI(nav, player);

		nav.setItem(21, (new ItemBuilder(Material.GOLDEN_APPLE)).setName("§e§l♕ UHC Host").setLoreList(Arrays.asList("", "§c§l§kHEY§c§l Jeu Populaire §c§l§kYEH", "",  "", " §7Célèbre Battle Royal de minecraft,", " §7vous pouvez y jouer à des heures", " §7annoncé sur notre §9discord§7.", " §7avec de multiples §dcustomisations§7.", "", "§7§lJoueur(s) » §6§là faire", " ")).toItemStack());
		nav.setItem(49, (new ItemBuilder(Material.BARRIER)).setName("§c§lFermer").toItemStack());
		nav.setItem(23, (new ItemBuilder(HeadList.RRUMBLE.getItemStack())).setName("§6§lPvP Box").setLoreList(Arrays.asList(" ", "§e§l★ §e§l§nType: PvP§e§l ★", "", "", " §7Soit le dernier survivant dans l'arène.", " §7A l'aide de tes compétances en §6§lPvP §7tue", " §7tout tes ennemis qui vont arriver §d1 par 1", " §7dans l'arène.", " ", "§7§lJoueur(s) » §6§là faire", "")).toItemStack());
		nav.setItem(0, (new ItemBuilder(HeadList.HOST.getItemStack())).setName("§5§l☎ Création de serveurs hosts.").setLoreList(Arrays.asList(" ", "",  "", " §7Cliquez ici si vous voulez §5créer§7 un serveur", " §7en étant §dl'host§7 de la §6partie", " §7qui vous permettra de faire votre", " §econfiguation personnel§7.", "")).toItemStack());
		nav.setItem(48, (new ItemBuilder(HeadList.SPAWN.getItemStack())).setName("§3§l► Spawn ◄").setLoreList(Arrays.asList(" ", "§6Clique si tu veux retourner au §a§lspawn§7.", "")).toItemStack());
		nav.setItem(50, (new ItemBuilder(Material.FEATHER)).setName("§f§l► Jump ◄").setLoreList(Arrays.asList(" ", "§6Clique si tu veux te téléporter au §a§lJump§7.", "")).toItemStack());
		nav.setItem(5, (new ItemBuilder(HeadList.PARA.getItemStack())).setName("§6§lParamètres").toItemStack());
		nav.setItem(3, (new ItemBuilder(HeadList.BOUTIQUE.getItemStack())).setName("§e§lBoutique").toItemStack());
		player.openInventory(nav);
		if (playerInfo.getRank().getPower() == 10 || playerInfo.getRank().getPower() == 9 || playerInfo.getRank().getPower() >= 12) {
			if (playerInfo.isModeMod()) {
				nav.setItem(16, (new ItemBuilder(Material.EYE_OF_ENDER)).setName("§9§lMode Modération §8§l┃ §aActivé").toItemStack());
			} else {
				nav.setItem(16, (new ItemBuilder(Material.ENDER_PEARL)).setName("§9§lMode Modération §8§l┃ §cDésactivé").toItemStack());
			}
		}

		player.updateInventory();
	}



	public void GUIPara(Player player) {
		player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1.0F, 1.0F);
		PlayerInfo playerInfo = new PlayerInfo(player);
		Inventory para = Bukkit.createInventory((InventoryHolder)null, 54, "§6§lParamètres");
		int[] cases = new int[]{0, 1, 2, 6, 7, 8, 9, 17, 36, 44, 45, 46, 47, 53, 52, 51};
		ItemStack i = (new ItemBuilder(Material.STAINED_GLASS_PANE, 1, API.getInstance().getStyle().getWindowColorPara())).setName("§b§k!!!!!").toItemStack();

		for (int c : cases) {
			para.setItem(c, i);
		}

		para.setItem(31, (new ItemBuilder(HeadList.LANGUE.getItemStack())).setName("§6§lLangue §e§l♚").setLoreList(Arrays.asList(" ", " ", " §7Permet de changer la §9§llangue", " §7sur serveur dans toute ça §e§lglobalité§7.", "", "   §9§lFr§f§lanç§c§lais §7「 §a§l✔ §7§l」", " ")).toItemStack());
		para.setItem(49, (new ItemBuilder(Material.BARRIER)).setName("§c§lRetour").toItemStack());

		if(playerInfo.isActivateMsg()) para.setItem(23, (new ItemBuilder(Material.PAPER)).setName("§6§lMessage privé §e§l✉").setLoreList(Arrays.asList(" ", " ", " §7Permet de §cdésactiver §7ou bien", " §7d'§aactiver §7 les messages privée venant", " §7d'autre joueurs.", "", "   §a§lActivé §7§l「 §a§l✔ §7§l」", "   §7Désactivé", " ")).toItemStack());
		if(!playerInfo.isActivateMsg()) para.setItem(23, (new ItemBuilder(Material.PAPER)).setName("§6§lMessage privé §e§l✉").setLoreList(Arrays.asList(" ", " ", " §7Permet de §cdésactiver §7ou bien", " §7d'§aactiver §7 les messages privée venant", " §7d'autre joueurs.", "", "   §a§lActivé §7§l「 §a§l✔ §7§l」", "   §7Désactivé", " ")).toItemStack());

		if(playerInfo.isActivateFriends()) para.setItem(37, (new ItemBuilder(HeadList.AMI.getItemStack())).setName("§3§lRequête d'ami §d§l❤").setLoreList(Arrays.asList(" ", " ", " §7Permet de pouvoir §aactiver §7ou", " §7bien §cdésactiver §7les requêtes d'§damis", " §7que les joueurs vous envoie.", "", "   §a§lActivé §7「 §a§l✔ §7§l」", "   §7Désactivé", " ")).toItemStack());

		if(!playerInfo.isActivateFriends()) para.setItem(37, (new ItemBuilder(HeadList.AMI.getItemStack())).setName("§3§lRequête d'ami §d§l❤").setLoreList(Arrays.asList(" ", " ", " §7Permet de pouvoir §aactiver §7ou", " §7bien §cdésactiver §7les requêtes d'§damis", " §7que les joueurs vous envoie.", "", "   §7Activé", "   §c§lDésactivé §7§l「 §c§l✘ §7§l」", " ")).toItemStack());


		if (playerInfo.isActivateChat()) {
			para.setItem(21, (new ItemBuilder(Material.BOOK)).setName("§6§lChat §e§l✎").setLoreList(Arrays.asList(" ", " ", " §7Permet de d'§aactiver §7ou bien", " §cdésactiver §7le chat du lobby.", "", "   §a§lActivé §7§l「 §a§l✔ §7§l」", "   §7Désactivé", " ")).toItemStack());
		} else {
			para.setItem(21, (new ItemBuilder(Material.BOOK)).setName("§6§lChat §e§l✎").setLoreList(Arrays.asList(" ", " ", " §7Permet de d'§aactiver §7ou bien", " §cdésactiver §7le chat du lobby.", "", "   §7Activé", "   §c§lDésactivé §7§l「 §c§l✘ §7§l」", " ")).toItemStack());
		}

		if (playerInfo.getSpeed() == 0) para.setItem(16, (new ItemBuilder(Material.SUGAR)).setName("§6§lVitesse §e§l♞").setLoreList(Arrays.asList(" ", " ", " §7Permet de modifier la §bvitesse à la quel", " §7vous courrez dans le §dHub§7.", "", "   §7Vitesse » I", "   §7Vitesse » II", "   §7Vitesse » III", "   §c§lDésactivé §7§l「 §c§l✘ §7§l」", " ")).toItemStack());

		if (playerInfo.getSpeed() == 1) para.setItem(16, (new ItemBuilder(Material.SUGAR)).setName("§6§lVitesse §e§l♞").setLoreList(Arrays.asList(" ", " ", " §7Permet de modifier la §bvitesse à la quel", " §7vous courrez dans le §dHub§7.", "", "   §7Vitesse » §a§lI §7「 §a§l✔ §7§l」", "   §7Vitesse » II", "   §7Vitesse » III", "   §7Désactivé", " ")).toItemStack());

		if (playerInfo.getSpeed() == 2) para.setItem(16, (new ItemBuilder(Material.SUGAR)).setName("§6§lVitesse §e§l♞").setLoreList(Arrays.asList(" ",  " ", " §7Permet de modifier la §bvitesse à la quel", " §7vous courrez dans le §dHub§7.", "", "   §7Vitesse » I", "   §7Vitesse » §a§lII §7「 §a§l✔ §7§l」", "   §7Vitesse » III", "   §7Désactivé", " ")).toItemStack());

		if (playerInfo.getSpeed() == 3) para.setItem(16, (new ItemBuilder(Material.SUGAR)).setName("§6§lVitesse §e§l♞").setLoreList(Arrays.asList(" ", " ", " §7Permet de modifier la §bvitesse à la quel", " §7vous courrez dans le §dHub§7.", "", "   §7Vitesse » I", "   §7Vitesse » II", "   §7Vitesse » §a§lIII §7「 §a§l✔ §7§l」", "   §7Désactivé", " ")).toItemStack());

		if (playerInfo.isActivateFly()) {
			para.setItem(43, (new ItemBuilder(Material.FEATHER)).setName("§6§lVoler dans le Hub §e§l☀").setLoreList(Arrays.asList(" ", " ", " §7Permet de d'§aactiver §7ou bien", " §cdésactiver §7la capacité de", " §7pouvoir voler dans le §dHub§7.", "", "   §a§lActivé §7「 §a§l✔ §7§l」", "   §7Désactivé", " ")).toItemStack());
		} else {
			para.setItem(43, (new ItemBuilder(Material.FEATHER)).setName("§6§lVoler dans le Hub §e§l☀").setLoreList(Arrays.asList(" ", " ", " §7Permet de d'§aactiver §7ou bien", " §cdésactiver §7la capacité de", " §7pouvoir voler dans le §dHub§7.", "", "   §7Activé", "   §c§lDésactivé §7§l「 §c§l✘ §7§l」", " ")).toItemStack());
		}

		player.openInventory(para);
	}


	/*


	public void GUIMode(Player player) {
		player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1.0F, 1.0F);
		PlayerInfo playerInfo = new PlayerInfo(player);
		Inventory para = Bukkit.createInventory((InventoryHolder)null, 54, "§6§lParamètres");
		int[] cases = new int[]{0, 1, 2, 6, 7, 8, 9, 17, 36, 44, 45, 46, 47, 53, 52, 51};
		ItemStack i = (new ItemBuilder(Material.STAINED_GLASS_PANE, 1, API.getInstance().getStyle().getWindowColorPara())).setName("§b§k!!!!!").toItemStack();

		for (int c : cases) {
			para.setItem(c, i);
		}
		int[] cases_mode = new int[]{20 ,21, 22, 23, 29, 30, 31, 32, 38, 39, 40, 41};

		int n = 20;
		for(Mode mode : Mode.values()){
			if(mode.isActive()){
				para.setItem(n, (new ItemBuilder(mode.getItemStack())).setName(mode.getName()).setLore(mode.getDescription()).toItemStack());

			}
		}

		para.setItem(49, (new ItemBuilder(Material.BARRIER)).setName("§c§lRetour").toItemStack());


		player.openInventory(para);
	}

	 */

}
