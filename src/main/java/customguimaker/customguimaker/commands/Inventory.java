package customguimaker.customguimaker.commands;

import customguimaker.customguimaker.ConfigInventory;
import customguimaker.customguimaker.CustomGUIMaker;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class Inventory implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        if(args.length >= 1) {
            if(args[0].toLowerCase(Locale.ROOT).equals("open")) {
                if(args.length >= 2) {
                    org.bukkit.inventory.Inventory inv = ConfigInventory.getInventory(args[1].toLowerCase(Locale.ROOT), "§4--> ", " §r§4<--");
                    if(inv != null) {
                        p.openInventory(inv);
                        p.sendMessage("§e§lCustomGUI §r§8» §7Succesfully opened inventory " + args[1].toLowerCase(Locale.ROOT));

                        CustomGUIMaker.currentInventory.put(p, args[1].toLowerCase(Locale.ROOT));
                    } else {
                        p.sendMessage("§e§lCustomGUI §r§8» §4It seems that this inventory does not exist.");
                    }
                } else {
                    p.sendMessage("§e§lCustomGUI §r§8» §7Invalid arguments. Please use: §4/inventory open <inventory>");
                }
            } else if(args[0].toLowerCase(Locale.ROOT).equals("edit")) {
                if(args.length >= 2) {
                    if(args[1].toLowerCase(Locale.ROOT).equals("command")) {
                        if(p.hasPermission("guimaker.*") || p.hasPermission("guimaker.edit_command") || p.isOp()) {
                            if (args[2] != null) {
                                if (ConfigInventory.getInventory(args[2].toLowerCase(Locale.ROOT)) != null) {
                                    if (args[3] != null && args[4] != null && isNumeric(args[3])) {
                                        String cmd = args[4];
                                        for (int i = 5; i <= args.length - 1; i++) {
                                            cmd = cmd + " " + args[i];
                                        }

                                        cmd = cmd.replace("&", "§");

                                        ConfigInventory.setConfigCommand(args[2].toLowerCase(Locale.ROOT), Integer.parseInt(args[3]) - 1, cmd);
                                        p.sendMessage("§e§lCustomGUI §r§8» §7Set the command in §a" + args[2].toLowerCase(Locale.ROOT) + " §7of the item slot §a" + args[3] + " §7to §a" + cmd + "§7.");
                                    } else {
                                        p.sendMessage("§e§lCustomGUI §r§8» §7Invalid arguments. Please use: §4/inventory edit command <inventory> <slot> <command>");
                                    }
                                } else {
                                    p.sendMessage("§e§lCustomGUI §r§8» §4It seems that this inventory does not exist.");
                                }
                            } else {
                                p.sendMessage("§e§lCustomGUI §r§8» §7Invalid arguments. Please use: §4/inventory edit command <inventory> <slot> <command>");
                            }
                        }
                    } else if (args[1].toLowerCase(Locale.ROOT).equals("gui")) {
                        if(p.hasPermission("guimaker.*") || p.hasPermission("guimaker.edit_gui") || p.isOp()) {
                            if(args.length >= 3) {
                                org.bukkit.inventory.Inventory inv = ConfigInventory.getInventory(args[2].toLowerCase(Locale.ROOT), "§a--> ", " §r§a<--");
                                if(inv != null) {
                                    p.openInventory(inv);
                                    CustomGUIMaker.editInventory.put(p, args[2].toLowerCase(Locale.ROOT));
                                } else {
                                    p.sendMessage("§e§lCustomGUI §r§8» §4It seems that this inventory does not exist.");
                                }
                            } else {
                                p.sendMessage("§e§lCustomGUI §r§8» §7Invalid arguments. Please use: §4/inventory edit gui <inventory>");
                            }
                        } else {
                            p.sendMessage("§e§lCustomGUI §r§8» §4You don't have the permissions to do that.");
                        }
                    } else if (args[1].toLowerCase(Locale.ROOT).equals("name")) {
                        if(p.hasPermission("guimaker.*") || p.hasPermission("guimaker.edit_name") || p.isOp()) {
                            if(args.length >= 4) {
                                if(ConfigInventory.getInventory(args[2].toLowerCase(Locale.ROOT)) != null) {
                                    String name = args[3];
                                    for (int i = 4; i <= args.length - 1; i++) {
                                        name = name + " " + args[i];
                                    }

                                    name = name.replace("&", "§");

                                    ConfigInventory.setConfigGuiName(args[2].toLowerCase(Locale.ROOT), name);

                                    p.sendMessage("§e§lCustomGUI §r§8» §7Set the GUI name of the inventory §a" + args[2].toLowerCase(Locale.ROOT) + " §7to §a" + name);
                                } else {
                                    p.sendMessage("§e§lCustomGUI §r§8» §4It seems that this inventory does not exist.");
                                }
                            } else {
                                p.sendMessage("§e§lCustomGUI §r§8» §7Invalid arguments. Please use: §4/inventory edit name <inventory> <name>");
                            }
                        } else {
                            p.sendMessage("§e§lCustomGUI §r§8» §4You don't have the permissions to do that.");
                        }
                    } else {
                        p.sendMessage("§e§lCustomGUI §r§8» §7Invalid arguments. Please use: §4/inventory edit <command / gui / name>");
                    }
                } else {
                    p.sendMessage("§e§lCustomGUI §r§8» §7Invalid arguments. Please use: §4/inventory edit <command / gui / name>");
                }
            } else if(args[0].toLowerCase(Locale.ROOT).equals("create")) {
                if(p.hasPermission("guimaker.edit_gui") || p.hasPermission("guimaker.*") || p.isOp()) {
                    if (args.length >= 3) {
                        if (isNumeric(args[2])) {
                            Integer size = Integer.parseInt(args[2]);
                            org.bukkit.inventory.Inventory inv = Bukkit.createInventory(null, size);

                            ConfigInventory.saveInventory(inv, args[1].toLowerCase(Locale.ROOT).replace(".", "_"));
                        } else {
                            p.sendMessage("§e§lCustomGUI §r§8» §7Invalid arguments. Please use: §4/inventory create <inventory-name> §7(Please use an integer.) --> §4<size-of-inventory>");
                        }
                    } else {
                        p.sendMessage("§e§lCustomGUI §r§8» §7Invalid arguments. Please use: §4/inventory create <inventory-name> <size-of-inventory>");
                    }
                } else {
                    p.sendMessage("§e§lCustomGUI §r§8» §4You don't have the permissions to do that.");
                }
            } else if(args[0].toLowerCase(Locale.ROOT).equals("item")) {
                p.sendMessage("§e§lCustomGUI §r§8» §7This function is coming soon.");
            } else {
                p.sendMessage("§e§lCustomGUI §r§8» §7Invalid arguments. Please use: §4/inventory <open / edit / item / create>");
            }
        } else {
            p.sendMessage("§e§lCustomGUI §r§8» §7Invalid arguments. Please use: §4/inventory <open / edit / item / create>");
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
}
