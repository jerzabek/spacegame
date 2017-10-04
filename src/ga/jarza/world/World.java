package ga.jarza.world;

import ga.jarza.world.entities.Entity;
import ga.jarza.world.entities.Player;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.List;

public class World {

  private List<Entity> e = new ArrayList<Entity>();

  public World() {
    e.add(new Player());
  }

  public void update(int delta) {
    for(Entity a : e){
      a.update(delta, this);
    }
  }

  public void render(Graphics g) {
    for(Entity a : e){
      a.render(g);
    }

  }

}
