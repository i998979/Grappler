package to.epac.factorycraft.Grappler;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import to.epac.factorycraft.Grappler.event.Grappler;

public class Main extends JavaPlugin implements Listener{
	public void onEnable(){
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new Grappler(), this);
	}
}
