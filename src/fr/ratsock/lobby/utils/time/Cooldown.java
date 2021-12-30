package fr.ratsock.lobby.utils.time;

import fr.ratsock.lobby.Lobby;
import org.bukkit.Bukkit;

public abstract class Cooldown {

	public Cooldown(int seconds) {
		Bukkit.getScheduler().runTaskLater(Lobby.getInstance(), new Runnable() {
			@Override
			public void run() {
				onFinish();
			}
		}, 20 * seconds);
	}

	public abstract void onFinish();

}