package customguimaker.customguimaker;

public class Player {

    public static void sendCommand(org.bukkit.entity.Player player, String command) {
        if(command != null) {
            player.chat(command);
        }
    }

}
