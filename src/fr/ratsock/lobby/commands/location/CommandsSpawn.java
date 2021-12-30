package fr.ratsock.lobby.commands.location;

import fr.ratsock.api.API;
import fr.ratsock.api.mysql.PlayerInfo;
import fr.ratsock.api.mysql.RankEnums;
import fr.ratsock.api.style.Prefix;
import fr.ratsock.api.utils.commands.Command;
import fr.ratsock.api.utils.commands.CommandArgs;
import fr.ratsock.lobby.Lobby;
import org.bukkit.entity.Player;

public class CommandsSpawn {

	@Command(name = "setspawn", description = "Set le spawn du lobby", usage = "Commands: /setspawn", aliases = {"setloclobby"})
	public void onSetSpawnCommand(CommandArgs args) {

		if (!args.isPlayer()) return;

		Player player = args.getPlayer();
		PlayerInfo playerInfo = new PlayerInfo(player);

		if(playerInfo.getRank() != RankEnums.ADMIN){
			player.sendMessage(Prefix.ERROR + "Erreur ! Tu n'es pas " + RankEnums.ADMIN.getName());
			return;
		}

		Lobby.getInstance().getConfig().set("spawn.world", player.getWorld().getName());
		Lobby.getInstance().getConfig().set("spawn.x", player.getLocation().getBlockX());
		Lobby.getInstance().getConfig().set("spawn.y", player.getLocation().getBlockY());
		Lobby.getInstance().getConfig().set("spawn.z", player.getLocation().getBlockZ());
		Lobby.getInstance().getConfig().set("spawn.pitch", player.getLocation().getPitch());
		Lobby.getInstance().getConfig().set("spawn.yaw", player.getLocation().getYaw());
		player.sendMessage(Prefix.SUCCESS + "Vous venez de définir le spawn.");
	}
}
