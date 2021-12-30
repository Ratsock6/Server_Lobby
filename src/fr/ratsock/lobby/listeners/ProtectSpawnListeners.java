package fr.ratsock.lobby.listeners;

import fr.ratsock.lobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class ProtectSpawnListeners implements Listener {

	@EventHandler
	public void onBreak(BlockBreakEvent event){
		event.setCancelled(true);
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent event){
		event.setCancelled(true);
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent event){
		event.setCancelled(true);
	}

	@EventHandler
	public void onPickup(PlayerPickupItemEvent event){
		event.setCancelled(true);
	}

	@EventHandler
	public void onFoodLevel(FoodLevelChangeEvent event){
		event.setCancelled(true);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		event.setCancelled(true);
	}

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		e.setCancelled(true);
		if (e.getEntity() instanceof Player) {
			Player player = (Player)e.getEntity();
			Lobby instance = Lobby.getInstance();
			if(player.getLocation().getBlockY() < 0) player.teleport(new Location(player.getWorld(), instance.getConfig().getInt("spawn.x"), instance.getConfig().getInt("spawn.y"), instance.getConfig().getInt("spawn.z"), instance.getConfig().getLong("spawn.pitch"), instance.getConfig().getLong("spawn.yaw")));


		}
	}

	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event){
		event.setCancelled(true);
	}

}
