package customgui.customgui.events;

import customgui.customgui.ConfigInventory;
import customgui.customgui.CustomGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class InventoryCloseEvent implements Listener {

    @EventHandler
    public void onInventoryClose(org.bukkit.event.inventory.InventoryCloseEvent event) {
        Player p = (Player) event.getPlayer();

        if(event.getView().getTitle().startsWith("§r") && event.getView().getTitle().endsWith(" §8(Editor)")) {
            ConfigInventory.saveInventory(CustomGUI.editInventory.get(p), event.getInventory());

            p.sendMessage("§e§lCustomGUI §r§8» §7Inventory saved.");
        }
    }

}
