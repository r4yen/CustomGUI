package customguimaker.customguimaker.commands;

import customguimaker.customguimaker.CustomGUIMaker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class InventoryTAB implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();

        if(args.length >= 5) {
            if(args[0].toLowerCase(Locale.ROOT).equals("edit")) {
                if(args[1].toLowerCase(Locale.ROOT).equals("command")) {
                    list.add("Command to be executed as the player");
                    return list;
                }
            }
        }

        if(args.length >= 4) {
            if(args[0].toLowerCase(Locale.ROOT).equals("item")) {
                if(args[1].toLowerCase(Locale.ROOT).equals("lore")) {
                    if(args[2].toLowerCase(Locale.ROOT).equals("add")) {
                        list.add("New line for lore of time");
                        return list;
                    }
                }
            }

            if(args[0].toLowerCase(Locale.ROOT).equals("edit")) {
                if(args[1].toLowerCase(Locale.ROOT).equals("name")) {
                    list.add("New name for the GUI");
                    return list;
                }

                if(args[1].toLowerCase(Locale.ROOT).equals("command")) {
                    list.add("Choose slot, where the command activates.");
                    return list;
                }
            }
        }

        if(args.length >= 3) {
            if(args[0].toLowerCase(Locale.ROOT).equals("create")) {
                list.add("Size of inventory (between 9 and 54!)"); //TODO
                return list;
            }

            if(args[0].toLowerCase(Locale.ROOT).equals("edit")) {
                if(args[1].equals("command") || args[1].equals("gui") || args[1].equals("name")) {
                    list = allInventories(args[2].toLowerCase(Locale.ROOT));
                    return list;
                }
            }

            if(args[0].toLowerCase(Locale.ROOT).equals("item")) {
                if(args[1].equals("lore")) {
                    if("add".startsWith(args[1].toLowerCase(Locale.ROOT))) {
                        list.add("add");
                    }

                    if("clear".startsWith(args[1].toLowerCase(Locale.ROOT))) {
                        list.add("clear");
                    }

                    if("add".contains(args[1].toLowerCase(Locale.ROOT)) && !("add".startsWith(args[1].toLowerCase(Locale.ROOT)))) {
                        list.add("add");
                    }

                    if("clear".contains(args[1].toLowerCase(Locale.ROOT)) && !("clear".startsWith(args[1].toLowerCase(Locale.ROOT)))) {
                        list.add("clear");
                    }
                    return list;
                } else {
                    list.add("New name of item");
                    return list;
                }
            }
        }

        if(args.length >= 2) {
            if(args[0].toLowerCase(Locale.ROOT).equals("create")) {
                list.add("Inventory name (no space and points!)");
                return list;
            }

            if(args[0].toLowerCase(Locale.ROOT).equals("edit")) {
                if("command".startsWith(args[1].toLowerCase(Locale.ROOT))) {
                    list.add("command");
                }

                if("gui".startsWith(args[1].toLowerCase(Locale.ROOT))) {
                    list.add("gui");
                }

                if("name".startsWith(args[1].toLowerCase(Locale.ROOT))) {
                    list.add("name");
                }

                if("command".contains(args[1].toLowerCase(Locale.ROOT)) && !("command".startsWith(args[1].toLowerCase(Locale.ROOT)))) {
                    list.add("command");
                }

                if("gui".contains(args[1].toLowerCase(Locale.ROOT)) && !("gui".startsWith(args[1].toLowerCase(Locale.ROOT)))) {
                    list.add("gui");
                }

                if("name".contains(args[1].toLowerCase(Locale.ROOT)) && !("name".startsWith(args[1].toLowerCase(Locale.ROOT)))) {
                    list.add("name");
                }
                return list;
            }

            if(args[0].toLowerCase(Locale.ROOT).equals("item")) {
                if("lore".startsWith(args[1].toLowerCase(Locale.ROOT))) {
                    list.add("lore");
                }

                if("name".startsWith(args[1].toLowerCase(Locale.ROOT))) {
                    list.add("name");
                }

                if("lore".contains(args[1].toLowerCase(Locale.ROOT)) && !("lore".startsWith(args[1].toLowerCase(Locale.ROOT)))) {
                    list.add("lore");
                }

                if("name".contains(args[1].toLowerCase(Locale.ROOT)) && !("name".startsWith(args[1].toLowerCase(Locale.ROOT)))) {
                    list.add("name");
                }
                return list;
            }

            if(args[0].toLowerCase(Locale.ROOT).equals("open")) {
                list = allInventories(args[1].toLowerCase(Locale.ROOT));
                return list;
            }
        }

        if(args.length >= 1) {
            if("create".startsWith(args[0].toLowerCase(Locale.ROOT))) {
                list.add("create");
            }

            if("edit".startsWith(args[0].toLowerCase(Locale.ROOT))) {
                list.add("edit");
            }

            if("item".startsWith(args[0].toLowerCase(Locale.ROOT))) {
                list.add("item");
            }

            if("open".startsWith(args[0].toLowerCase(Locale.ROOT))) {
                list.add("open");
            }

            if("create".contains(args[0].toLowerCase(Locale.ROOT)) && !("create".startsWith(args[0].toLowerCase(Locale.ROOT)))) {
                list.add("create");
            }

            if("edit".contains(args[0].toLowerCase(Locale.ROOT)) && !("edit".startsWith(args[0].toLowerCase(Locale.ROOT)))) {
                list.add("edit");
            }

            if("item".contains(args[0].toLowerCase(Locale.ROOT)) && !("item".startsWith(args[0].toLowerCase(Locale.ROOT)))) {
                list.add("item");
            }

            if("open".contains(args[0].toLowerCase(Locale.ROOT)) && !("open".startsWith(args[0].toLowerCase(Locale.ROOT)))) {
                list.add("open");
            }

            return list;
        }

        return list;
    }

    public static List<String> allInventories(String currentInput) {
        List<String> allInventories = new ArrayList<String>();

        for (String key : CustomGUIMaker.Instance.getConfig().getConfigurationSection("gui").getKeys(false)) {
            if(key != null) {
                if(key.startsWith(currentInput)) {
                    allInventories.add(key);
                }
            }
        }

        for (String key : CustomGUIMaker.Instance.getConfig().getConfigurationSection("gui").getKeys(false)) {
            if(key != null) {
                if(key.contains(currentInput) && !(key.startsWith(currentInput))) {
                    allInventories.add(key);
                }
            }
        }

        return allInventories;
    }

}