package de.afkfelix.simpledailyrewards.listeners;

import de.afkfelix.simpledailyrewards.SimpleDailyRewards;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

public class PlayerJoinListener implements Listener {
    private SimpleDailyRewards plugin;

    public PlayerJoinListener(SimpleDailyRewards plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        FileConfiguration config = plugin.getConfigData();

        if (!config.contains("players." + playerUUID)) {
            config.set("players." + playerUUID + ".lastClaim", "");
            config.set("players." + playerUUID + ".day", 1);
            plugin.saveConfigFile();
        }

        String lastClaim = config.getString("players." + playerUUID + ".lastClaim", "");
        LocalDate today = LocalDate.now(ZoneId.of("UTC"));


        if (lastClaim.isEmpty() || lastClaim == "" || !lastClaim.equals(today.toString())) {
            player.sendMessage(Component.text("Your daily reward is available! ").append(
                    Component.text("[Claim]")
                            .color(TextColor.color(0x00FF00))
                            .clickEvent(ClickEvent.runCommand("/dailyreward"))
            ));
        } else {
            player.sendMessage("You have already claimed your daily reward today!");
        }

    }
}
