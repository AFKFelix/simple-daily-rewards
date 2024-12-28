package de.afkfelix.simpledailyrewards.commands;

import de.afkfelix.simpledailyrewards.SimpleDailyRewards;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.time.LocalDate;
import java.time.ZoneId;

public class DailyRewardCommand implements CommandExecutor {

    private final SimpleDailyRewards plugin;

    public DailyRewardCommand(SimpleDailyRewards plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command is only for players!");
            return true;
        }


        Player player = (Player) sender;
        Inventory gui = Bukkit.createInventory(null, 9, "Daily Reward");

        FileConfiguration config = plugin.getConfigData();
        String lastClaim = config.getString("players." + player.getUniqueId() + ".lastClaim", "");
        int day = config.getInt("players." + player.getUniqueId() + ".day", 1);
        LocalDate today = LocalDate.now(ZoneId.of("UTC"));

        if (lastClaim.isEmpty() || lastClaim == "" || !lastClaim.equals(today.toString())) {
            ItemStack diamond = new ItemStack(Material.DIAMOND);
            ItemMeta meta = diamond.getItemMeta();
            meta.setDisplayName("§aReward day " + day);
            diamond.setItemMeta(meta);

            gui.setItem(4, diamond);

            player.openInventory(gui);
        } else {
            player.sendMessage("You have already claimed your daily reward today!");
        }

        return true;
    }
}