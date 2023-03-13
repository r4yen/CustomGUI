package customgui.customgui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigInventory {

    public static void setConfigItem(String inventory, Integer slot, ItemStack item) {
        CustomGUI.Instance.getInventoriesConfig().set("gui." + inventory + "." + slot + ".item", item);

        try {
            CustomGUI.Instance.getInventoriesConfig().save(CustomGUI.inventoriesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ItemStack getConfigItem(String inventory, Integer slot) {
        ItemStack item = CustomGUI.Instance.getInventoriesConfig().getItemStack("gui." + inventory + "." + slot + ".item");

        return item;
    }

    public static void setConfigCommand(String inventory, Integer slot, String command) {
        CustomGUI.Instance.getInventoriesConfig().set("gui." + inventory + "." + slot + ".command", command);

        try {
            CustomGUI.Instance.getInventoriesConfig().save(CustomGUI.inventoriesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getConfigCommand(String inventory, Integer slot) {
        String command = CustomGUI.Instance.getInventoriesConfig().getString("gui." + inventory + "." + slot + ".command");

        return command;
    }

    public static void setConfigGuiName(String inventory, String name) {
        CustomGUI.Instance.getInventoriesConfig().set("gui." + inventory + ".name", name);

        try {
            CustomGUI.Instance.getInventoriesConfig().save(CustomGUI.inventoriesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getConfigGuiName(String inventory) {
        String name = CustomGUI.Instance.getInventoriesConfig().getString("gui." + inventory + ".name");

        return name;
    }
    public static void setConfigGuiSize(String inventory, Integer size) {
        CustomGUI.Instance.getInventoriesConfig().set("gui." + inventory + ".size", size);

        try {
            CustomGUI.Instance.getInventoriesConfig().save(CustomGUI.inventoriesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Integer getConfigGuiSize(String inventory) {
        Integer size = CustomGUI.Instance.getInventoriesConfig().getInt("gui." + inventory + ".size");

        return size;
    }

    public static void saveInventory(Inventory inventory, String name) {
        Integer size = inventory.getSize();
        setConfigGuiSize(name, size);

        Integer slot = 0;
        for(ItemStack s: inventory.getContents()) {
            setConfigItem(name, slot, s);
            slot++;
        }
    }

    public static void saveInventory(String name, Inventory inventory) {
        Integer size = inventory.getSize();
        setConfigGuiSize(name, size);

        Integer slot = 0;
        for(ItemStack s: inventory.getContents()) {
            setConfigItem(name, slot, s);
            slot++;
        }
    }

    public static Inventory getInventory(String name) {
        Inventory inventory = Bukkit.createInventory(null, getConfigGuiSize(name), getConfigGuiName(name));

        for(int i = 0; i <= getConfigGuiSize(name) - 1; i++)
        {
            inventory.setItem(i, getConfigItem(name, i));
        }

        return inventory;
    }

    public static Inventory getInventory(String name, String beforeGuiName, String afterGuiName) {
        Inventory inventory = Bukkit.createInventory(null, getConfigGuiSize(name), beforeGuiName + getConfigGuiName(name) + afterGuiName);

        for(int i = 0; i <= getConfigGuiSize(name) - 1; i++)
        {
            inventory.setItem(i, getConfigItem(name, i));
        }

        return inventory;
    }
}