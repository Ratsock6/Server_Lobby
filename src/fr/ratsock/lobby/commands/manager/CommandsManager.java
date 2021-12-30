package fr.ratsock.lobby.commands.manager;

import fr.ratsock.api.utils.commands.CommandFramework;
import fr.ratsock.lobby.commands.location.CommandsSpawn;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandsManager {

	private static CommandFramework framework;

	public static void setupCommandsSystem(JavaPlugin plugin) {
		framework = new CommandFramework(plugin);

		registerNewCommand(new CommandsSpawn());

	}

	public static void registerNewCommand(Object object) {
		framework.registerCommands(object);
	}

}
