package ga.jarza.world.entities;

import org.newdawn.slick.Graphics;

public abstract class Entity {

  public float x, y;

  public abstract void update(int delta);
  public abstract void render(Graphics g);

}
