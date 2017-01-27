package me.cherrykit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class RandomTeleport extends JavaPlugin {

	World w;
	
	@Override
	public void onEnable() {
		w = Bukkit.getWorld("world");
	}
	
	@Override
	public void onDisable() {}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
       
		//Resigsters /random command
		if (cmd.getName().equalsIgnoreCase("random") && sender instanceof Player) {
			Player p = (Player) sender;
			int x,y,z;
        
			//Gets random coordinates
			do {
				x = (int) (Math.random()*2000 - 1000);
				z = (int) (Math.random()*2000 - 1000);
				y = w.getHighestBlockYAt(x, z);
			} while (w.getBlockAt(x, y-1, z).getType() == Material.LAVA);
			
			//Teleports player
			p.teleport(new Location(w, x, y, z));
			p.sendMessage(ChatColor.DARK_GRAY + "You have been teleported to a random location");
			return true;
		}
		
		return false;
	}
}
