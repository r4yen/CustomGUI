package customguimaker.customguimaker;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigInventory {

    /*
    * MATERIAL OF ITEMSTACK
    */

    public static void setConfigMaterial(String inventory, Integer slot, String material) {
        CustomGUIMaker.Instance.getConfig().set("gui." + inventory + "." + slot + ".material", material.toString());
        CustomGUIMaker.Instance.saveConfig();
    }

    public static Material getConfigMaterial(String inventory, Integer slot) {
        Material m = customguimaker.customguimaker.Material.getMaterialFromString(CustomGUIMaker.Instance.getConfig().getString("gui." + inventory + "." + slot + ".material"));

        return m;
    }

    /*
    * LORE OF ITEMSTACK
    */

    public static List<String> getConfigLore(String inventory, Integer slot) {
        String loreAsString = CustomGUIMaker.Instance.getConfig().getString("gui." + inventory + "." + slot + ".lore");

        List<String> lore = new ArrayList<String>();

        if(loreAsString != null) {
            if (loreAsString.contains(" #SPLIT# ")) {
                lore = Arrays.asList(loreAsString.split(" #SPLIT# "));
            } else {
                lore.add(loreAsString);
            }
        }

        if(loreAsString.equals("") || loreAsString.equals("#EMPTY#")) {
            return null;
        }

        return lore;
    }

    public static void setConfigLore(String inventory, Integer slot, List<String> lore) {
        StringBuilder loreAsString = null;

        if(lore == null) {
            CustomGUIMaker.Instance.getConfig().set("gui." + inventory + "." + slot + ".lore", "#EMPTY#");
            CustomGUIMaker.Instance.saveConfig();
        } else {
            for (String s : lore) {
                if (loreAsString == null) {
                    loreAsString = new StringBuilder(s);
                } else {
                    loreAsString.append(" #SPLIT# ").append(s);
                }
            }

            CustomGUIMaker.Instance.getConfig().set("gui." + inventory + "." + slot + ".lore", loreAsString.toString());
            CustomGUIMaker.Instance.saveConfig();
        }
    }

    /*
    * NAME OF ITEMSTACK
    */

    public static void setConfigName(String inventory, Integer slot, String name) {
        CustomGUIMaker.Instance.getConfig().set("gui." + inventory + "." + slot + ".name", name.toString());
        CustomGUIMaker.Instance.saveConfig();
    }

    public static String getConfigName(String inventory, Integer slot) {
        String name = CustomGUIMaker.Instance.getConfig().getString("gui." + inventory + "." + slot + ".name");

        return name;
    }

    /*
    * COMMAND OF ITEMSTACK
    */

    public static void setConfigCommand(String inventory, Integer slot, String command) {
        CustomGUIMaker.Instance.getConfig().set("gui." + inventory + "." + slot + ".command", command);
        CustomGUIMaker.Instance.saveConfig();
    }

    public static String getConfigCommand(String inventory, Integer slot) {
        String command = CustomGUIMaker.Instance.getConfig().getString("gui." + inventory + "." + slot + ".command");

        return command;
    }

    /*
    * NAME OF GUI
    * */

    public static void setConfigGuiName(String inventory, String name) {
        CustomGUIMaker.Instance.getConfig().set("gui." + inventory + ".name", name);
        CustomGUIMaker.Instance.saveConfig();
    }

    public static String getConfigGuiName(String inventory) {
        String name = CustomGUIMaker.Instance.getConfig().getString("gui." + inventory + ".name");

        return name;
    }

    /*
    * SIZE OF GUI
    * */

    public static void setConfigGuiSize(String inventory, Integer size) {
        CustomGUIMaker.Instance.getConfig().set("gui." + inventory + ".size", size);
        CustomGUIMaker.Instance.saveConfig();
    }

    public static Integer getConfigGuiSize(String inventory) {
        Integer size = CustomGUIMaker.Instance.getConfig().getInt("gui." + inventory + ".size");

        return size;
    }

    /*
    * SAVE / LOAD INVENTORY
    * */

    public static void saveInventory(Inventory inventory, String name, String inventoryTitle) {
        Integer size = inventory.getSize();
        setConfigGuiSize(name, size);
        setConfigGuiName(name, inventoryTitle);

        Integer slot = 0;
        for(ItemStack s: inventory.getContents()) {
            if(s == null) {
                setConfigMaterial(name, slot, customguimaker.customguimaker.Material.getStringFromMaterial(Material.AIR));
                setConfigName(name, slot, "");
                setConfigLore(name, slot, null);
            } else if(s.getItemMeta() != null) {
                setConfigMaterial(name, slot, customguimaker.customguimaker.Material.getStringFromMaterial(s.getType()));

                if(s.getItemMeta().getDisplayName().equals("")) {
                    setConfigName(name, slot, "§r");
                } else {
                    setConfigName(name, slot, s.getItemMeta().getDisplayName());
                }

                if(s.getItemMeta().getLore() != null) {
                    setConfigLore(name, slot, s.getItemMeta().getLore());
                } else {
                    setConfigLore(name, slot, null);
                }
            } else {
                setConfigMaterial(name, slot, customguimaker.customguimaker.Material.getStringFromMaterial(Material.AIR));
                setConfigName(name, slot, "");
                setConfigLore(name, slot, null);
            }

            slot++;
        }
    }

    public static void saveInventory(Inventory inventory, String name) {
        Integer size = inventory.getSize();
        setConfigGuiSize(name, size);
        setConfigGuiName(name, name);

        Integer slot = 0;
        for(ItemStack s: inventory.getContents()) {
            if(s == null) {
                setConfigMaterial(name, slot, customguimaker.customguimaker.Material.getStringFromMaterial(Material.AIR));
                setConfigName(name, slot, "");
                setConfigLore(name, slot, null);
            } else if(s.getItemMeta() != null) {
                setConfigMaterial(name, slot, customguimaker.customguimaker.Material.getStringFromMaterial(s.getType()));

                if(s.getItemMeta().getDisplayName().equals("")) {
                    setConfigName(name, slot, "§r");
                } else {
                    setConfigName(name, slot, s.getItemMeta().getDisplayName());
                }

                if(s.getItemMeta().getLore() != null) {
                    setConfigLore(name, slot, s.getItemMeta().getLore());
                } else {
                    setConfigLore(name, slot, null);
                }
            } else {
                setConfigMaterial(name, slot, customguimaker.customguimaker.Material.getStringFromMaterial(Material.AIR));
                setConfigName(name, slot, "");
                setConfigLore(name, slot, null);
            }

            slot++;
        }
    }

    public static void saveInventory(String name, Inventory inventory) {
        Integer size = inventory.getSize();
        setConfigGuiSize(name, size);

        Integer slot = 0;
        for(ItemStack s: inventory.getContents()) {
            if(s == null) {
                setConfigMaterial(name, slot, customguimaker.customguimaker.Material.getStringFromMaterial(Material.AIR));
                setConfigName(name, slot, "");
                setConfigLore(name, slot, null);
            } else if(s.getItemMeta() != null) {
                setConfigMaterial(name, slot, customguimaker.customguimaker.Material.getStringFromMaterial(s.getType()));

                if(s.getItemMeta().getDisplayName().equals("")) {
                    setConfigName(name, slot, "§r");
                } else {
                    setConfigName(name, slot, s.getItemMeta().getDisplayName());
                }

                if(s.getItemMeta().getLore() != null) {
                    setConfigLore(name, slot, s.getItemMeta().getLore());
                } else {
                    setConfigLore(name, slot, null);
                }
            } else {
                setConfigMaterial(name, slot, customguimaker.customguimaker.Material.getStringFromMaterial(Material.AIR));
                setConfigName(name, slot, "");
                setConfigLore(name, slot, null);
            }

            slot++;
        }
    }

    public static Inventory getInventory(String name) {
        Inventory inventory = Bukkit.createInventory(null, getConfigGuiSize(name), getConfigGuiName(name));

        for(int i = 0; i <= getConfigGuiSize(name) - 1; i++)
        {
            inventory.setItem(i, itemMaker(getConfigName(name, i), getConfigLore(name, i), getConfigMaterial(name, i)));
        }

        return inventory;
    }

    public static Inventory getInventory(String name, String beforeGuiName, String afterGuiName) {
        Inventory inventory = Bukkit.createInventory(null, getConfigGuiSize(name), beforeGuiName + getConfigGuiName(name) + afterGuiName);

        for(int i = 0; i <= getConfigGuiSize(name) - 1; i++)
        {
            inventory.setItem(i, itemMaker(getConfigName(name, i), getConfigLore(name, i), getConfigMaterial(name, i)));
        }

        return inventory;
    }

    public static ItemStack itemMaker(String name, List<String> lore, Material material) {
        ItemStack itemStack = null;

        if (material == null) {
            itemStack = new ItemStack(Material.AIR);
        } else {
            itemStack = new ItemStack(material);
        }

        ItemMeta itemMeta = itemStack.getItemMeta();
        if(itemMeta != null) {
            itemMeta.setDisplayName(name);
            if(lore != null) {
                itemMeta.setLore(lore);
            }
        }

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}