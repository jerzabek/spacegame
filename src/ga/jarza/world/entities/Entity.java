package ga.jarza.world.entities;

import ga.jarza.world.World;
import org.newdawn.slick.Graphics;

public abstract class Entity {

  public float x, y, width, height;
  protected World world;

  public Entity(World world) {
    this.world = world;
  }

  public abstract void update(int delta);
  public abstract void render(Graphics g);

}
