package customgui.customgui;

import customgui.customgui.commands.Inventory;
import customgui.customgui.commands.InventoryTAB;
import customgui.customgui.events.InventoryClickEvent;
import customgui.customgui.events.InventoryCloseEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

public final class CustomGUI extends JavaPlugin {
    public static CustomGUIMaker Instance = null;

    public static HashMap<Player, String> editInventory = new HashMap<Player, String>();
    public static HashMap<Player, String> currentInventory = new HashMap<Player, String>();

    public static String language = "english";
    public static Boolean sendMessages = true;

    @Override
    public void onEnable() {
        if(Bukkit.getBukkitVersion().contains("1.8") || Bukkit.getBukkitVersion().contains("1.9") || Bukkit.getBukkitVersion().contains("1.10") || Bukkit.getBukkitVersion().contains("1.11") || Bukkit.getBukkitVersion().contains("1.12")) {
            Instance = this;

            createServerConfig();
            createMessagesConfig();
            createInventoriesConfig();

            if(CustomGUIMaker.Instance.getServerConfig().getString("send-messages").toLowerCase(Locale.ROOT).equals("false")) {
                sendMessages = false;
            }

            language = CustomGUIMaker.Instance.getServerConfig().getString("language");

            this.getCommand("inventory").setExecutor(new Inventory());

            Bukkit.getPluginManager().registerEvents(new InventoryClickEvent(), this);
            Bukkit.getPluginManager().registerEvents(new InventoryCloseEvent(), this);
        } else if(Bukkit.getBukkitVersion().contains("1.13") || Bukkit.getBukkitVersion().contains("1.14") || Bukkit.getBukkitVersion().contains("1.15") || Bukkit.getBukkitVersion().contains("1.16") || Bukkit.getBukkitVersion().contains("1.17") || Bukkit.getBukkitVersion().contains("1.18") || Bukkit.getBukkitVersion().contains("1.19")) {
            Instance = this;

            createServerConfig();
            createMessagesConfig();
            createInventoriesConfig();

            if(CustomGUIMaker.Instance.getServerConfig().getString("send-messages").toLowerCase(Locale.ROOT).equals("false")) {
                sendMessages = false;
            }

            language = CustomGUIMaker.Instance.getServerConfig().getString("language");

            this.getCommand("inventory").setExecutor(new Inventory());
            if(getServerConfig().getString("tab-completer").toLowerCase(Locale.ROOT).equals("true")) {
                this.getCommand("inventory").setTabCompleter(new InventoryTAB());
            }

            Bukkit.getPluginManager().registerEvents(new InventoryClickEvent(), this);
            Bukkit.getPluginManager().registerEvents(new InventoryCloseEvent(), this);
        }


    }

    private File serverFile;
    private FileConfiguration serverConfig;

    public FileConfiguration getServerConfig() {
        return this.serverConfig;
    }

    private void createServerConfig() {
        serverFile = new File(getDataFolder(), "server.yml");
        saveResource("server.yml", false);

        serverConfig = new YamlConfiguration();
        try {
            serverConfig.load(serverFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    private File messagesFile;
    private FileConfiguration messagesConfig;

    public FileConfiguration getMessagesConfig() {
        return this.messagesConfig;
    }

    private void createMessagesConfig() {
        messagesFile = new File(getDataFolder(), "messages.yml");
        saveResource("messages.yml", false);

        messagesConfig = new YamlConfiguration();
        try {
            messagesConfig.load(messagesFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static File inventoriesFile;
    public static FileConfiguration inventoriesConfig;

    public FileConfiguration getInventoriesConfig() {
        return this.inventoriesConfig;
    }

    private void createInventoriesConfig() {
        inventoriesFile = new File(getDataFolder(), "inventories.yml");
        saveResource("inventories.yml", false);

        inventoriesConfig = new YamlConfiguration();
        try {
            inventoriesConfig.load(inventoriesFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}