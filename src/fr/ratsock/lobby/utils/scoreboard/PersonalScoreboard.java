package fr.ratsock.lobby.utils.scoreboard;

import fr.ratsock.api.API;
import fr.ratsock.api.mysql.PlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/*
 * This file is part of SamaGamesAPI.
 *
 * SamaGamesAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SamaGamesAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SamaGamesAPI.  If not, see <http://www.gnu.org/licenses/>.
 */
public class PersonalScoreboard {

	private final UUID uuid;
	private final ObjectiveSign objectiveSign;
	private final Player player;

	PersonalScoreboard(Player player) {
		this.player = player;
		uuid = player.getUniqueId();
		objectiveSign = new ObjectiveSign("sidebar", "Attente");

		reloadData();
		objectiveSign.addReceiver(player);
	}

	private final String space = "  §8» §7";

	public void reloadData() {
	}

	private String base() {
		return API.getInstance().getStyle().getFirstColor().toString();
	}

	private String transition() {
		return ChatColor.DARK_GRAY + ": " + API.getInstance().getStyle().getSecondColor();
	}

	public void setLines(String ip) {
		objectiveSign.setDisplayName(API.getInstance().getStyle().getScoreboardName());

		objectiveSign.setLine(0, "§7§o " + (new SimpleDateFormat("dd/MM/yyyy")).format(new Date()));
		objectiveSign.setLine(1, "§1");
		objectiveSign.setLine(2, space + "§e" + player.getName());
		objectiveSign.setLine(3, space + "§7Grade: " + PlayerInfo.getPlayerInfo(player).getRank().getDisplayname());
		objectiveSign.setLine(4, space + "§7Temps: " + ChatColor.LIGHT_PURPLE + PlayerInfo.getPlayerInfo(player).getTimeLeft(this.player));
		objectiveSign.setLine(5, "§2");
		objectiveSign.setLine(6, space + "§7" + API.getInstance().getStyle().getCoinsName() + ": §a" + PlayerInfo.getPlayerInfo(this.player).getCoins() + API.getInstance().getStyle().getLogoCoin());
		objectiveSign.setLine(7, space + "§7" + API.getInstance().getStyle().getArgentName() + ": §d" + PlayerInfo.getPlayerInfo(this.player).getMoney() + API.getInstance().getStyle().getLogoArgent());
		objectiveSign.setLine(8, "§3");
		objectiveSign.setLine(9, space + "Connecté(s): §6" + Bukkit.getOnlinePlayers().size());
		objectiveSign.setLine(10, space + "Serveur: §bLobby");
		objectiveSign.setLine(11, "§4");
		objectiveSign.setLine(12, ip);
		objectiveSign.updateLines();
		objectiveSign.clearScores();
	}

	public void onLogout() {
		objectiveSign.removeReceiver(Bukkit.getServer().getOfflinePlayer(uuid));
	}

}
