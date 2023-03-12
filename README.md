# CustomGUIMaker

* Create your own GUIs with CustomGUIMaker. Unlimited GUIs, own commands and easy use!
* This plugin is for **Bukkit / Spigot / Paper** (bukkit based) servers and for the **minecraft versions 1.8.x - 1.19.x**. 

# Commands

### `/gui` & `/inventory`
* Both commands are running the same code, just as another command (gui is the alias from inventory). **since Version 1.0** <br>


### ``/inventory open <gui>``
* Open an inventory. **since Version 1.0** <br>


### ``/inventory edit gui <gui>``
* Edit an inventory. **since Version 1.0** <br>


### ``/inventory edit command <gui> <slot> <command>``
* Edit the command that will run as the player when the player clicks the item on the slot. **since Version 1.0** <br>


### ``/inventory edit name <gui> <name>``
* Edit the name of the gui (this will only change the inventory name). **since Version 1.0** <br>


### ``/inventory create <name> <size>``
* Create a new inventory. Size must be between 9 and 54 and must be divisible by 9. **since Version 1.0** <br>


### ``/inventory delete <gui>``
* Delete an old inventory. **since Version 2.0** <br>


### ``/inventory item lore add <text>``
* Add a line to the item you hold in your hand. **since Version 2.0** <br>


### ``/inventory item lore clear``
* Clear the lore of the item you hold in your hand. **since Version 2.0** <br>


### ``/inventory item name <text>``
* Rename the item that you hold in your hand. **since Version 2.0** <br>


# Permissions
  
### ``Without permissions``
* /inventory open <gui> <br>
  
### ``guimaker.edit_name``
* /inventory edit name <gui> <name> <br>

### ``guimaker.edit_command``
* /inventory edit command <gui> <slot> <command> <br>

### ``guimaker.edit_gui``
* /inventory edit command <gui> <slot> <command>
* /inventory edit name <gui> <name>
* /inventory edit gui <gui> <br>

### ``guimaker.items``
* /inventory item lore add <text>
* /inventory item lore clear
* /inventory item name <text> <br>

### ``guimaker.*`` / ``Server-Operator``
* /inventory create <name> <size>

* /inventory edit command <gui> <slot> <command>
* /inventory edit name <gui> <name>
* /inventory edit gui <gui>

* /inventory delete <gui>

* /inventory open <gui>
  
* /inventory list
  
* /inventory item lore add <text>
* /inventory item lore clear
* /inventory item name <text>
