package ga.jarza.world.entities;

import ga.jarza.world.World;
import org.newdawn.slick.Graphics;

public abstract class Entity {

  public float x, y;

  public abstract void update(int delta, World world);
  public abstract void render(Graphics g);

}
