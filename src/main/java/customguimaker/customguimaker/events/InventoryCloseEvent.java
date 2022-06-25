package customguimaker.customguimaker.events;

import customguimaker.customguimaker.ConfigInventory;
import customguimaker.customguimaker.CustomGUIMaker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class InventoryCloseEvent implements Listener {

    @EventHandler
    public void onInventoryClose(org.bukkit.event.inventory.InventoryCloseEvent event) {
        Player p = (Player) event.getPlayer();

        if(event.getView().getTitle().startsWith("§a--> ") && event.getView().getTitle().endsWith(" §r§a<--")) {
            ConfigInventory.saveInventory(CustomGUIMaker.editInventory.get(p), event.getInventory());

            p.sendMessage("§e§lCustomGUI §r§8» §7Inventory saved.");
        }
    }

}
