package ga.jarza.world.entities;

import ga.jarza.world.World;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public abstract class Entity {

  public float x, y, xv, yv, width, height;
  protected World world;
  public boolean dead;
  public Rectangle col;
  public float ang = 0f;
  public Entity(World world) {
    this.world = world;
  }

  public abstract void update(int delta);
  public abstract void render(Graphics g);

  public Shape getCol(){
    return col.transform(Transform.createRotateTransform((float)Math.toRadians(ang), col.getCenterX(), col.getCenterY()));
  }
}
