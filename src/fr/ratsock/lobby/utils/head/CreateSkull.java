package fr.ratsock.lobby.utils.head;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class CreateSkull {

	public static ItemStack getHead(String name) {

		for (HeadList head : HeadList.values()) {

			if (head.getName().equalsIgnoreCase(name)) {

				return head.getItemStack();

			}

		}

		return null;
	}

	public static ItemStack createSkull(String url, String name) {

		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);

		if (url.isEmpty())
			return head;

		SkullMeta headMeta = (SkullMeta) head.getItemMeta();

		GameProfile profile = new GameProfile(UUID.randomUUID(), null);

		profile.getProperties().put("textures", new Property("textures", url));

		try {

			Field profileField = headMeta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(headMeta, profile);

		} catch (IllegalArgumentException | NoSuchFieldException | SecurityException | IllegalAccessException error) {

			error.printStackTrace();

		}

		head.setItemMeta(headMeta);
		return head;

	}

}
