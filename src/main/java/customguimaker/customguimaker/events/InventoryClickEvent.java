package customguimaker.customguimaker.events;

import customguimaker.customguimaker.ConfigInventory;
import customguimaker.customguimaker.CustomGUIMaker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class InventoryClickEvent implements Listener {

    @EventHandler
    public void onInventoryClick(org.bukkit.event.inventory.InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();

        if(event.getView().getTitle().startsWith("§4--> ") && event.getView().getTitle().endsWith(" §r§4<--")) {
            event.setCancelled(true);

            Integer slot = event.getSlot();

            String command = ConfigInventory.getConfigCommand(CustomGUIMaker.currentInventory.get(p), slot);

            customguimaker.customguimaker.Player.sendCommand(p, command);
        }
    }
}
