package de.afkfelix.simpledailyrewards.listeners;

import de.afkfelix.simpledailyrewards.SimpleDailyRewards;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

public class GuiClickListener implements Listener {

    private final SimpleDailyRewards plugin;

    public GuiClickListener(SimpleDailyRewards plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equals("Daily Reward")) {
            event.setCancelled(true);

            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

            if (clickedItem.getType() == Material.DIAMOND) {
                FileConfiguration config = plugin.getConfigData();
                UUID playerUUID = player.getUniqueId();

                LocalDate today = LocalDate.now(ZoneId.of("UTC"));
                config.set("players." + playerUUID + ".lastClaim", today.toString());

                int oldCount = config.getInt("players." + playerUUID + ".day", 1);
                int newCount = oldCount + 1;
                config.set("players." + playerUUID + ".day", newCount);
                plugin.saveConfigFile();

                player.getInventory().addItem(new ItemStack(Material.DIAMOND, oldCount));

                player.sendMessage("You have claimed your daily reward!");
                player.closeInventory();
            }
        }
    }
}
