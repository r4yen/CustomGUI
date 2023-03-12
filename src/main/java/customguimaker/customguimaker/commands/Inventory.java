package customguimaker.customguimaker.commands;

import customguimaker.customguimaker.ConfigInventory;
import customguimaker.customguimaker.CustomGUIMaker;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Inventory implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        if(args.length >= 1) {
            if(args[0].toLowerCase(Locale.ROOT).equals("open")) {
                if(args.length >= 2) {
                    if(InventoryTAB.allInventories("").size() >= 1 && InventoryTAB.allInventories("").contains(args[1].toLowerCase(Locale.ROOT))) {
                        org.bukkit.inventory.Inventory inv = ConfigInventory.getInventory(args[1].toLowerCase(Locale.ROOT), "§r", "§r");

                        p.openInventory(inv);
                        if(CustomGUIMaker.sendMessages) {
                            p.sendMessage(getMessage("opened_inventory").replace("{0}", args[1].toLowerCase(Locale.ROOT)));
                        }

                        CustomGUIMaker.currentInventory.put(p, args[1].toLowerCase(Locale.ROOT));
                    } else {
                        if(CustomGUIMaker.sendMessages) {
                            p.sendMessage(getMessage("inventory_not_exists"));
                        }
                    }
                } else {
                    if(CustomGUIMaker.sendMessages) {
                        p.sendMessage(getMessage("wrong_arguments").replace("{0}", "/inventory open <inventory>"));
                    }
                }
            } else if(args[0].toLowerCase(Locale.ROOT).equals("edit")) {
                if(args.length >= 2) {
                    if(args[1].toLowerCase(Locale.ROOT).equals("command")) {
                        if(p.hasPermission("guimaker.*") || p.hasPermission("guimaker.edit_command") || p.isOp()) {
                            if (args[2] != null) {
                                if (InventoryTAB.allInventories("").size() >= 1 && InventoryTAB.allInventories("").contains(args[2].toLowerCase(Locale.ROOT))) {
                                    if (args[3] != null && args[4] != null && isNumeric(args[3])) {
                                        String cmd = args[4];
                                        for (int i = 5; i <= args.length - 1; i++) {
                                            cmd = cmd + " " + args[i];
                                        }

                                        cmd = cmd.replace("&", "§");

                                        ConfigInventory.setConfigCommand(args[2].toLowerCase(Locale.ROOT), Integer.parseInt(args[3]) - 1, cmd);
                                        if(CustomGUIMaker.sendMessages) {
                                            p.sendMessage(getMessage("set_command").replace("{0}", args[2].toLowerCase(Locale.ROOT)).replace("{1}", args[3]).replace("{2}", cmd));
                                        }
                                    } else {
                                        if(CustomGUIMaker.sendMessages) {
                                            p.sendMessage(getMessage("wrong_arguments").replace("{0}", "/inventory edit command <inventory> <slot> <command>"));
                                        }
                                    }
                                } else {
                                    if(CustomGUIMaker.sendMessages) {
                                        p.sendMessage(getMessage("inventory_not_exists"));
                                    }
                                }
                            } else {
                                if(CustomGUIMaker.sendMessages) {
                                    p.sendMessage(getMessage("wrong_arguments").replace("{0}", "/inventory edit command <inventory> <slot> <command>"));
                                }
                            }
                        }
                    } else if (args[1].toLowerCase(Locale.ROOT).equals("gui")) {
                        if(p.hasPermission("guimaker.*") || p.hasPermission("guimaker.edit_gui") || p.isOp()) {
                            if(args.length >= 3) {
                                if(InventoryTAB.allInventories("").size() >= 1 && InventoryTAB.allInventories("").contains(args[1].toLowerCase(Locale.ROOT))) {
                                    org.bukkit.inventory.Inventory inv = ConfigInventory.getInventory(args[2].toLowerCase(Locale.ROOT), "§r", " §8(Editor)");

                                    p.openInventory(inv);
                                    CustomGUIMaker.editInventory.put(p, args[2].toLowerCase(Locale.ROOT));
                                } else {
                                    if(CustomGUIMaker.sendMessages) {
                                        p.sendMessage(getMessage("inventory_not_exists"));
                                    }
                                }
                            } else {
                                if(CustomGUIMaker.sendMessages) {
                                    p.sendMessage(getMessage("wrong_arguments").replace("{0}", "/inventory edit gui <inventory>"));
                                }
                            }
                        } else {
                            if(CustomGUIMaker.sendMessages) {
                                p.sendMessage(getMessage("no_permission"));
                            }
                        }
                    } else if (args[1].toLowerCase(Locale.ROOT).equals("name")) {
                        if(p.hasPermission("guimaker.*") || p.hasPermission("guimaker.edit_name") || p.isOp()) {
                            if(args.length >= 4) {
                                if(InventoryTAB.allInventories("").size() >= 1 && InventoryTAB.allInventories("").contains(args[1].toLowerCase(Locale.ROOT))) {
                                    String name = args[3];
                                    for (int i = 4; i <= args.length - 1; i++) {
                                        name = name + " " + args[i];
                                    }

                                    name = name.replace("&", "§");

                                    ConfigInventory.setConfigGuiName(args[2].toLowerCase(Locale.ROOT), name);

                                    if(CustomGUIMaker.sendMessages) {
                                        p.sendMessage(getMessage("gui_name_set").replace("{0}", args[2].toLowerCase(Locale.ROOT)).replace("{1}", name));
                                    }
                                } else {
                                    if(CustomGUIMaker.sendMessages) {
                                        p.sendMessage(getMessage("inventory_not_exists"));
                                    }
                                }
                            } else {
                                if(CustomGUIMaker.sendMessages) {
                                    p.sendMessage(getMessage("wrong_arguments").replace("{0}", "/inventory edit name <inventory> <name>"));
                                }
                            }
                        } else {
                            if(CustomGUIMaker.sendMessages) {
                                p.sendMessage(getMessage("no_permission"));
                            }
                        }
                    } else {
                        if(CustomGUIMaker.sendMessages) {
                            p.sendMessage(getMessage("wrong_arguments").replace("{0}", "/inventory edit <command / gui / name>"));
                        }
                    }
                } else {
                    if(CustomGUIMaker.sendMessages) {
                        p.sendMessage(getMessage("wrong_arguments").replace("{0}", "/inventory edit <command / gui / name>"));
                    }
                }
            } else if(args[0].toLowerCase(Locale.ROOT).equals("create")) {
                if(p.hasPermission("guimaker.edit_gui") || p.hasPermission("guimaker.*") || p.isOp()) {
                    if (args.length >= 3) {
                        if(args[2].equals("9") || args[2].equals("18") || args[2].equals("27") || args[2].equals("36") || args[2].equals("45") || args[2].equals("54")) {
                            Integer size = Integer.parseInt(args[2]);
                            org.bukkit.inventory.Inventory inv = Bukkit.createInventory(null, size);

                            ConfigInventory.saveInventory(inv, args[1].toLowerCase(Locale.ROOT).replace(".", "_"));
                            ConfigInventory.setConfigGuiName(args[1].toLowerCase().replace(".", "_"), args[1].toLowerCase().replace(".", "_"));

                            p.sendMessage(getMessage("inventory_created").replace("{0}", args[1].toLowerCase(Locale.ROOT).replace(".", "_")));
                        } else {
                            if(CustomGUIMaker.sendMessages) {
                                p.sendMessage(getMessage("wrong_arguments").replace("{0}", "/inventory create <inventory-name> <size-of-inventory>"));
                            }
                        }
                    } else {
                        if(CustomGUIMaker.sendMessages) {
                            p.sendMessage(getMessage("wrong_arguments").replace("{0}", "/inventory create <inventory-name> <size-of-inventory>"));
                        }
                    }
                } else {
                    if(CustomGUIMaker.sendMessages) {
                        p.sendMessage(getMessage("no_permission"));
                    }
                }
            } else if(args[0].toLowerCase(Locale.ROOT).equals("item")) {
                if(p.hasPermission("guimaker.items") || p.hasPermission("guimaker.*") || p.isOp()) {
                    if (args.length >= 2) {
                        if (args[1].toLowerCase(Locale.ROOT).equals("name")) {
                            if (args.length >= 3) {
                                if(p.getItemInHand() == null) {
                                    p.sendMessage(getMessage("main_hand_is_empty"));

                                    return true;
                                }

                                String name = "##EMPTY##";
                                for (int i = 2; i <= args.length - 1; i++) {
                                    if (name.equals("##EMPTY##")) {
                                        name = args[i];
                                    } else {
                                        name = name + " " + args[i];
                                    }
                                }

                                name = name.replace("&", "§");

                                if (name.equals("##EMPTY##") || name.equals("")) {
                                    ItemMeta meta = p.getItemInHand().getItemMeta();
                                    meta.setDisplayName("§r");
                                    p.getItemInHand().setItemMeta(meta);
                                    if (CustomGUIMaker.sendMessages) {
                                        p.sendMessage(getMessage("item_name_renamed").replace("{0}", "§r"));
                                    }
                                } else {
                                    ItemMeta meta = p.getItemInHand().getItemMeta();
                                    meta.setDisplayName(name);
                                    p.getItemInHand().setItemMeta(meta);
                                    if (CustomGUIMaker.sendMessages) {
                                        p.sendMessage(getMessage("item_name_renamed").replace("{0}", name));
                                    }
                                }
                            } else {
                                if (CustomGUIMaker.sendMessages) {
                                    p.sendMessage(getMessage("wrong_arguments").replace("{0}", "/inventory item name <name>"));
                                }
                            }
                        } else if (args[1].toLowerCase(Locale.ROOT).equals("lore")) {
                            if (args.length >= 3) {
                                if (args[2].toLowerCase(Locale.ROOT).equals("add")) {
                                    if(p.getItemInHand() == null) {
                                        p.sendMessage(getMessage("main_hand_is_empty"));

                                        return true;
                                    }

                                    List<String> lore = new ArrayList<String>();
                                    if(p.getItemInHand().getItemMeta().getLore() != null) {
                                        for (String s : p.getItemInHand().getItemMeta().getLore()) {
                                            lore.add(s);
                                        }
                                    }

                                    String name = "";
                                    for (int i = 3; i <= args.length - 1; i++) {
                                        if (name.equals("")) {
                                            name = args[i];
                                        } else {
                                            name = name + " " + args[i];
                                        }
                                    }

                                    name = name.replace("&", "§");

                                    lore.add(name);

                                    ItemMeta meta = p.getItemInHand().getItemMeta();
                                    meta.setLore(lore);
                                    p.getItemInHand().setItemMeta(meta);

                                    p.sendMessage(getMessage("item_lore_line_added").replace("{0}", name));
                                } else if (args[2].toLowerCase(Locale.ROOT).equals("clear")) {
                                    if(p.getItemInHand() == null) {
                                        p.sendMessage(getMessage("main_hand_is_empty"));

                                        return true;
                                    }

                                    ItemMeta meta = p.getItemInHand().getItemMeta();
                                    meta.setLore(null);
                                    p.getItemInHand().setItemMeta(meta);

                                    p.sendMessage(getMessage("item_lore_cleared"));
                                } else {
                                    if (CustomGUIMaker.sendMessages) {
                                        p.sendMessage(getMessage("wrong_arguments").replace("{0}", "/inventory item lore <add / clear>"));
                                    }
                                }
                            } else {
                                if (CustomGUIMaker.sendMessages) {
                                    p.sendMessage(getMessage("wrong_arguments").replace("{0}", "/inventory item lore <add / clear>"));
                                }
                            }
                        } else {
                            if (CustomGUIMaker.sendMessages) {
                                p.sendMessage(getMessage("wrong_arguments").replace("{0}", "/inventory item <name / lore>"));
                            }
                        }
                    } else {
                        if (CustomGUIMaker.sendMessages) {
                            p.sendMessage(getMessage("wrong_arguments").replace("{0}", "/inventory item <name / lore>"));
                        }
                    }
                } else {
                    if(CustomGUIMaker.sendMessages) {
                        p.sendMessage(getMessage("no_permission"));
                    }
                }
            } else if(args[0].toLowerCase(Locale.ROOT).equals("delete")) {
                if(args.length >= 2) {
                    if(p.hasPermission("guimaker.edit_gui") || p.hasPermission("guimaker.*") || p.isOp()) {
                        InventoryTAB.allInventories(null);
                        if (InventoryTAB.allInventories("").contains(args[1].toLowerCase(Locale.ROOT))) {
                            CustomGUIMaker.Instance.getInventoriesConfig().set("gui." + args[1].toLowerCase(Locale.ROOT), null);
                            try {
                                CustomGUIMaker.Instance.getInventoriesConfig().save(CustomGUIMaker.inventoriesFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            p.sendMessage(getMessage("inventory_deleted").replace("{0}", args[1].toLowerCase(Locale.ROOT)));
                        } else {
                            if (CustomGUIMaker.sendMessages) {
                                p.sendMessage(getMessage("inventory_not_exists"));
                            }
                        }
                    } else {
                        if(CustomGUIMaker.sendMessages) {
                            p.sendMessage(getMessage("no_permission"));
                        }
                    }
                } else {
                    if(CustomGUIMaker.sendMessages) {
                        p.sendMessage(getMessage("wrong_arguments").replace("{0}", "/inventory delete <inventory>"));
                    }
                }
            } else if(args[0].toLowerCase(Locale.ROOT).equals("list")) {
                if(CustomGUIMaker.sendMessages) {
                    List<String> inventories = InventoryTAB.allInventories(null);

                    String inv = getMessage("no_inventories");

                    for(String s: inventories) {
                        if(inv.equals(getMessage("no_inventories"))) {
                            inv = s;
                        } else {
                            inv = inv + " / " + s;
                        }
                    }

                    p.sendMessage(getMessage("inventory_list").replace("{0}", String.valueOf(inventories.size())).replace("{1}", inv));
                }
            } else {
                if(CustomGUIMaker.sendMessages) {
                    p.sendMessage(getMessage("wrong_arguments").replace("{0}", "/inventory <create / delete / edit / item / open / list>"));
                }
            }
        } else {
            if(CustomGUIMaker.sendMessages) {
                p.sendMessage(getMessage("wrong_arguments").replace("{0}", "/inventory <create / delete / edit / item / open / list>"));
            }
        }

        return true;
    }

    public static boolean isNumeric(String string) {
        int intValue;

        if(string == null || string.equals("")) {
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException ignored) {}
        return false;
    }

    public static String getMessage(String message) {
        String newMessage = null;

        newMessage = CustomGUIMaker.Instance.getMessagesConfig().getString(CustomGUIMaker.language + ".messages." + message);

        return newMessage;
    }
}