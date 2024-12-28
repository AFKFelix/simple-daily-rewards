package de.afkfelix.simpledailyrewards.commands;

import de.afkfelix.simpledailyrewards.SimpleDailyRewards;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.UUID;

public class StatsCommand implements CommandExecutor {

    private final SimpleDailyRewards plugin;

    public StatsCommand(SimpleDailyRewards plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command is only for players!");
            return true;
        }

        Player player = (Player) sender;
        FileConfiguration config = plugin.getConfigData();
        UUID playerUUID = player.getUniqueId();

        if (args.length == 1 && args[0].equalsIgnoreCase("delete")) {
            if (config.contains("players." + playerUUID)) {
                config.set("players." + playerUUID, null);
                plugin.saveConfigFile();
                player.sendMessage("Your SimpleDailyRewards data has been deleted!");
            } else {
                player.sendMessage("No SimpleDailyRewards data found.");
            }
            return true;
        }

        return true;
    }
}