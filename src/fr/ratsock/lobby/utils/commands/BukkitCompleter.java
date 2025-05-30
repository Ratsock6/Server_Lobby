package fr.ratsock.lobby.utils.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Command Framework - BukkitCompleter <br>
 * An implementation of the TabCompleter class allowing for multiple tab
 * completers per command
 *
 * @author minnymin3
 */
public class BukkitCompleter implements TabCompleter {

	private final Map<String, Entry<Method, Object>> completer = new HashMap<String, Entry<Method, Object>>();

	public void addCompleter(String label, Method m, Object obj) {
		completer.put(label, new AbstractMap.SimpleEntry<Method, Object>(m, obj));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		for (int i = args.length; i >= 0; i--) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(label.toLowerCase());
			for (int x = 0; x < i; x++) {
				if (!args[x].equals("") && !args[x].equals(" ")) {
					buffer.append("." + args[x].toLowerCase());
				}
			}
			String cmdLabel = buffer.toString();
			if (completer.containsKey(cmdLabel)) {
				Entry<Method, Object> entry = completer.get(cmdLabel);
				try {
					return (List<String>) entry.getKey().invoke(entry.getValue(),
							new CommandArgs(sender, command, label, args, cmdLabel.split("\\.").length - 1));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}