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
                    list.add(getTab("set_command"));
                }
            }

            return list;
        }

        if(args.length >= 4) {
            if(args[0].toLowerCase(Locale.ROOT).equals("item")) {
                if(args[1].toLowerCase(Locale.ROOT).equals("lore")) {
                    if(args[2].toLowerCase(Locale.ROOT).equals("add")) {
                        list.add(getTab("new_line_lore"));
                    }
                }
            }

            if(args[0].toLowerCase(Locale.ROOT).equals("edit")) {
                if(args[1].toLowerCase(Locale.ROOT).equals("name")) {
                    list.add(getTab("new_name_gui"));
                }

                if(args[1].toLowerCase(Locale.ROOT).equals("command")) {
                    list.add(getTab("set_command_slot"));
                }
            }

            return list;
        }

        if(args.length >= 3) {
            if(args[0].toLowerCase(Locale.ROOT).equals("create")) {
                list.add("9");
                list.add("18");
                list.add("27");
                list.add("36");
                list.add("45");
                list.add("54");
                if(args[2] != null) {
                    if(!(args[2].equals("9") || args[2].equals("18") || args[2].equals("27") || args[2].equals("36") || args[2].equals("45") || args[2].equals("54"))) {
                        list.add(getTab("invalid_size"));
                    }
                }
            }

            if(args[0].toLowerCase(Locale.ROOT).equals("edit")) {
                if(args[1].equals("command") || args[1].equals("gui") || args[1].equals("name")) {
                    list = allInventories(args[2].toLowerCase(Locale.ROOT));
                }
            }

            if(args[0].toLowerCase(Locale.ROOT).equals("item")) {
                if(args[1].toLowerCase(Locale.ROOT).equals("lore")) {
                    if("add".startsWith(args[2].toLowerCase(Locale.ROOT))) {
                        list.add("add");
                    }

                    if("clear".startsWith(args[2].toLowerCase(Locale.ROOT))) {
                        list.add("clear");
                    }

                    if("add".contains(args[2].toLowerCase(Locale.ROOT)) && !("add".startsWith(args[2].toLowerCase(Locale.ROOT)))) {
                        list.add("add");
                    }

                    if("clear".contains(args[2].toLowerCase(Locale.ROOT)) && !("clear".startsWith(args[2].toLowerCase(Locale.ROOT)))) {
                        list.add("clear");
                    }
                } else if(args[1].toLowerCase(Locale.ROOT).equals("name")) {
                    list.add(getTab("new_name_item"));
                }
            }

            return list;
        }

        if(args.length >= 2) {
            if(args[0].toLowerCase(Locale.ROOT).equals("create")) {
                list.add(getTab("create_inventory_name"));
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
            }

            if(args[0].toLowerCase(Locale.ROOT).equals("open") || args[0].toLowerCase(Locale.ROOT).equals("delete")) {
                list = allInventories(args[1].toLowerCase(Locale.ROOT));
            }

            return list;
        }

        if(args.length >= 1) {
            if("create".startsWith(args[0].toLowerCase(Locale.ROOT))) {
                list.add("create");
            }

            if("delete".startsWith(args[0].toLowerCase(Locale.ROOT))) {
                list.add("delete");
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

            if("list".startsWith(args[0].toLowerCase(Locale.ROOT))) {
                list.add("list");
            }

            if("create".contains(args[0].toLowerCase(Locale.ROOT)) && !("create".startsWith(args[0].toLowerCase(Locale.ROOT)))) {
                list.add("create");
            }

            if("delete".contains(args[0].toLowerCase(Locale.ROOT)) && !("delete".startsWith(args[0].toLowerCase(Locale.ROOT)))) {
                list.add("delete");
            }

            if("edit".contains(args[0].toLowerCase(Locale.ROOT)) && !("edit".startsWith(args[0].toLowerCase(Locale.ROOT)))) {
                list.add("edit");
            }

            if("item".contains(args[0].toLowerCase(Locale.ROOT)) && !("item".startsWith(args[0].toLowerCase(Locale.ROOT)))) {
                list.add("item");
            }

            if("list".contains(args[0].toLowerCase(Locale.ROOT)) && !("list".startsWith(args[0].toLowerCase(Locale.ROOT)))) {
                list.add("list");
            }

            if("open".contains(args[0].toLowerCase(Locale.ROOT)) && !("open".startsWith(args[0].toLowerCase(Locale.ROOT)))) {
                list.add("open");
            }

            return list;
        }

        return list;
    }

    public static List<String> allInventories(String currentInput) {
        if(currentInput == null) {
            List<String> allInventories = new ArrayList<String>();

            if(CustomGUIMaker.Instance.getInventoriesConfig().getConfigurationSection("gui") == null) {
                return allInventories;
            }

            for (String key : CustomGUIMaker.Instance.getInventoriesConfig().getConfigurationSection("gui").getKeys(false)) {
                allInventories.add(key);
            }

            return allInventories;
        }
        List<String> allInventories = new ArrayList<String>();

        if(CustomGUIMaker.Instance.getInventoriesConfig().getConfigurationSection("gui") == null) {
            allInventories.add(getTab("no_inventory_found"));

            return allInventories;
        }

        for (String key : CustomGUIMaker.Instance.getInventoriesConfig().getConfigurationSection("gui").getKeys(false)) {
            if(key != null) {
                if(key.startsWith(currentInput)) {
                    allInventories.add(key);
                }
            }
        }

        for (String key : CustomGUIMaker.Instance.getInventoriesConfig().getConfigurationSection("gui").getKeys(false)) {
            if(key != null) {
                if(key.contains(currentInput) && !(key.startsWith(currentInput))) {
                    allInventories.add(key);
                }
            }
        }

        return allInventories;
    }

    public static String getTab(String tab) {
        return CustomGUIMaker.Instance.getMessagesConfig().getString(CustomGUIMaker.language + ".tab." + tab);
    }
}