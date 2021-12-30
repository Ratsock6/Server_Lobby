package fr.ratsock.lobby.utils.player;

import fr.ratsock.api.style.Prefix;
import fr.ratsock.lobby.Lobby;
import fr.ratsock.lobby.utils.time.Cooldown;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerUtils {

	public static void clearInventory(Player player) {
		player.getInventory().clear();
		player.getInventory().setHelmet(new ItemStack(Material.AIR));
		player.getInventory().setChestplate(new ItemStack(Material.AIR));
		player.getInventory().setLeggings(new ItemStack(Material.AIR));
		player.getInventory().setBoots(new ItemStack(Material.AIR));
	}

	public static void clearEffect(Player player) {
		for (PotionEffect effects : player.getActivePotionEffects()) {
			player.removePotionEffect(effects.getType());
		}
	}

	public static boolean isPlayer(String name) {
		return Bukkit.getPlayer(name) != null;
	}

	public static Player getPlayer(String name) {
		if (isPlayer(name)) {
			return Bukkit.getPlayer(name);
		} else {
			return null;
		}
	}

	public static void addMetadata(Player player, String key) {
		player.setMetadata(key, new FixedMetadataValue(Lobby.getInstance(), key));
	}

	public static boolean haveMetadata(Player player, String key) {
		return player.hasMetadata(key);
	}

	public static void removeMetadata(Player player, String key) {
		player.removeMetadata(key, Lobby.getInstance());
	}

	public static void addPotionEffect(Player player, PotionEffect potionEffect) {
		if (player.hasPotionEffect(potionEffect.getType()))
			player.removePotionEffect(potionEffect.getType());

		player.addPotionEffect(potionEffect);
	}

	public static void removePotionEffect(Player player, PotionEffectType type) {
		if (player.hasPotionEffect(type))
			player.removePotionEffect(type);
	}

	public static void noDamage(Player player, int time) {
		addMetadata(player, "invincibility");
		new Cooldown(time) {
			@Override
			public void onFinish() {
				removeMetadata(player, "invincibility");
			}
		};
	}

	public static double getAbsoHearths(Player player) {
		return ((CraftPlayer) player).getHandle().getAbsorptionHearts();
	}

	public static void giveItemSafely(Player player, ItemStack item) {
		PlayerInventory inventory = player.getInventory();

		if (inventory.firstEmpty() == -1) {
			player.sendMessage(Prefix.ERROR + "Votre inventaire est plein, l'item a été déposé au sol.");
			player.getWorld().dropItemNaturally(player.getLocation(), item);
			return;
		}

		inventory.addItem(item);
	}

	public static void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
		CraftPlayer craftPlayer = (CraftPlayer) player;
		PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
				IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}"), fadeIn, stay, fadeOut);
		PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
				IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}"), fadeIn, stay, fadeOut);

		craftPlayer.getHandle().playerConnection.sendPacket(titlePacket);
		craftPlayer.getHandle().playerConnection.sendPacket(subtitlePacket);
	}

}
