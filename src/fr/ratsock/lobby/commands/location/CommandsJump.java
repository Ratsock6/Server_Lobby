package fr.ratsock.lobby.commands.location;

import fr.ratsock.api.mysql.PlayerInfo;
import fr.ratsock.api.mysql.RankEnums;
import fr.ratsock.api.style.Prefix;
import fr.ratsock.api.utils.commands.Command;
import fr.ratsock.api.utils.commands.CommandArgs;
import fr.ratsock.lobby.Lobby;
import org.bukkit.entity.Player;

public class CommandsJump {

	@Command(name = "setjump", description = "Set le spawn du lobby", usage = "Commands: /setjump", aliases = {"setlocjump"})
	public void onSetSpawnCommand(CommandArgs args) {

		if (!args.isPlayer()) return;

		Player player = args.getPlayer();
		PlayerInfo playerInfo = new PlayerInfo(player);

		if(playerInfo.getRank() != RankEnums.ADMIN){
			player.sendMessage(Prefix.ERROR + "Erreur ! Tu n'es pas " + RankEnums.ADMIN.getName());
			return;
		}

		Lobby.getInstance().getConfig().set("jump.world", player.getWorld().getName());
		Lobby.getInstance().getConfig().set("jump.x", player.getLocation().getBlockX());
		Lobby.getInstance().getConfig().set("jump.y", player.getLocation().getBlockY());
		Lobby.getInstance().getConfig().set("jump.z", player.getLocation().getBlockZ());
		Lobby.getInstance().getConfig().set("jump.pitch", player.getLocation().getPitch());
		Lobby.getInstance().getConfig().set("jump.yaw", player.getLocation().getYaw());
		player.sendMessage(Prefix.SUCCESS + "Vous venez de définir le jump.");
	}
}
