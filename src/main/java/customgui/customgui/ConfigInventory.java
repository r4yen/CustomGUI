package customgui.customgui;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

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
        return CustomGUI.Instance.getInventoriesConfig().getItemStack("gui." + inventory + "." + slot + ".item");
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
        return CustomGUI.Instance.getInventoriesConfig().getString("gui." + inventory + "." + slot + ".command");
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
        return CustomGUI.Instance.getInventoriesConfig().getString("gui." + inventory + ".name");
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
        return CustomGUI.Instance.getInventoriesConfig().getInt("gui." + inventory + ".size");
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

    public static Inventory getInventory(String name, String beforeGuiName, String afterGuiName) {
        Inventory inventory = Bukkit.createInventory(null, getConfigGuiSize(name), beforeGuiName + getConfigGuiName(name) + afterGuiName);

        for(int i = 0; i <= getConfigGuiSize(name) - 1; i++)
        {
            inventory.setItem(i, getConfigItem(name, i));
        }

        return inventory;
    }
}