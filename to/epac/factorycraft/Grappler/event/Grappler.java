package to.epac.factorycraft.Grappler.event;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.util.Vector;

public class Grappler implements Listener {
	public void fallDamage(EntityDamageEvent event){
		if(event.getEntity().getType() == EntityType.PLAYER && event.getCause() == DamageCause.FALL){
			event.setDamage(event.getDamage()/2);
		}
	}
	public void Grappler(PlayerFishEvent event){
		Player player = event.getPlayer();
		if(event.getState() != State.FISHING){
			Location hookLoc = event.getHook().getLocation().clone().add(0,-1,0);
			if(hookLoc.getBlock().getType().isSolid()){
				Location playerloc = player.getLocation();
                Location loc = event.getHook().getLocation();
                if (playerloc.distance(loc) < 3.0D)
                    pullPlayerSlightly(player, loc);
                else
                    pullEntityToLocation(player, loc);
                player.getItemInHand().setDurability((short) 0);
			}
		}
	}
	
	private void pullPlayerSlightly(Player p, Location loc){
		if (loc.getY() > p.getLocation().getY()){
			p.setVelocity(new Vector(0.0D, 0.25D, 0.0D));
			return;
		}
		Location playerLoc = p.getLocation();

		Vector vector = loc.toVector().subtract(playerLoc.toVector());
		p.setVelocity(vector);
	}
	
	private void pullEntityToLocation(Entity e, Location loc){
		Location entityLoc = e.getLocation();

		entityLoc.setY(entityLoc.getY() + 0.5D);
		e.teleport(entityLoc);

		double g = -0.08D;
		double d = loc.distance(entityLoc);
		double t = d;
		double v_x = (1.0D + 0.07000000000000001D * t)
				* (loc.getX() - entityLoc.getX()) / t;
		double v_y = (1.0D + 0.03D * t) * (loc.getY() - entityLoc.getY()) / t
				- 0.5D * g * t;
		double v_z = (1.0D + 0.07000000000000001D * t)
				* (loc.getZ() - entityLoc.getZ()) / t;

		Vector v = e.getVelocity();
		v.setX(v_x);
		v.setY(v_y);
		v.setZ(v_z);
		e.setVelocity(v);
	}
}
