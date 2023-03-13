package customgui.customgui;

public class Player {

    public static void sendCommand(org.bukkit.entity.Player player, String command) {
        if(command != null && !command.equalsIgnoreCase("")) {
            player.chat(command);
        }
    }

}
