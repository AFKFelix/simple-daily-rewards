# SimpleDailyRewards

SimpleDailyRewards is a lightweight and straightforward Paper plugin for Minecraft 1.21.1 that provides players with a
daily rewards system. Players can claim their rewards through an interactive GUI, making it a fun addition to any
server.

> **Note:** This plugin was created as a personal project and is not intended for production use.

---

## Features

- Daily reward system
- GUI for claiming rewards
- Command to delete player data

---

## Commands

- **`/dailyreward`**  
  Opens the daily reward GUI, allowing players to claim their rewards.

- **`/stats delete`**  
  Deletes the player's reward data from the configuration file.

---

## Configuration

The plugin stores player data in a YAML configuration file, including:

- **Last Claim Date**: Tracks the date when a player last claimed their reward.
- **Reward Streak**: Stores the total number of rewards claimed by each player.

The configuration file is automatically generated on the first run.

---

## Requirements

- **Minecraft Version**: 1.21.1
- **Server Platform**: [Paper](https://papermc.io/)

---

## Disclaimer

This plugin is a personal project and is not extensively tested. Use at your own risk in non-production environments.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
