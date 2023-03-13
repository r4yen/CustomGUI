package customgui.customgui.events;

import customgui.customgui.ConfigInventory;
import customgui.customgui.CustomGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class InventoryClickEvent implements Listener {

    @EventHandler
    public void onInventoryClick(org.bukkit.event.inventory.InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();

        if(event.getView().getTitle().startsWith("§r") && event.getView().getTitle().endsWith("§r")) {
            event.setCancelled(true);

            Integer slot = event.getSlot();

            String command = ConfigInventory.getConfigCommand(CustomGUI.currentInventory.get(p), slot);

            customguimaker.customguimaker.Player.sendCommand(p, command);
        }
    }
}
