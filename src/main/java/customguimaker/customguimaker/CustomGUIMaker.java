package customguimaker.customguimaker;

import customguimaker.customguimaker.commands.Inventory;
import customguimaker.customguimaker.commands.InventoryTAB;
import customguimaker.customguimaker.events.InventoryClickEvent;
import customguimaker.customguimaker.events.InventoryCloseEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class CustomGUIMaker extends JavaPlugin {
    public static CustomGUIMaker Instance = null;

    public static HashMap<Player, String> editInventory = new HashMap<Player, String>();
    public static HashMap<Player, String> currentInventory = new HashMap<Player, String>();

    @Override
    public void onEnable() {
        Instance = this;

        Instance.saveDefaultConfig();

        this.getCommand("inventory").setExecutor(new Inventory());
        this.getCommand("inventory").setTabCompleter(new InventoryTAB());

        Bukkit.getPluginManager().registerEvents(new InventoryClickEvent(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryCloseEvent(), this);
    }
}
