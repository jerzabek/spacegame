package ga.jarza.world.entities.attack;

import ga.jarza.world.World;
import ga.jarza.world.entities.Entity;
import ga.jarza.world.entities.Player;
import java.util.List;

public abstract class Attack {

  public abstract void update(int delta, float x, float y, List<Entity> b, World world, Player p);
}
