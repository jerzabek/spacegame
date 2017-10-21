package ga.jarza.world.entities;

import ga.jarza.main.Main;
import ga.jarza.world.World;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class BlastBullet extends Entity{

  private float ang;
  private float xv, yv, ac;
  private int deployTime = 0;
  private Player p;

  public BlastBullet(float x, float y, World world, Player p, double... delAng){
    this(x, y, world, delAng);
    this.p = p;
  }

  public BlastBullet(float x, float y, World world, double... delAng){
    super(world);
    this.x = x;
    this.y = y;
    width = 16f;
    height = 8f;

    float mx, my, dx, dy;

    mx = Mouse.getX();
    my = Main.getGAME_HEIGHT() - Mouse.getY();

    dx = -(x) + mx;
    dy = -(y) + my;

    ang = (float) (Math.atan2(dy, dx));
    //    ang = ang();

    if(delAng.length > 0){
      ang += delAng[0];
      if(delAng.length == 2){
        deployTime = (int)delAng[1];
      }
    }

    ac = 1.1f;

    yv = ac * (float) Math.sin(ang);
    xv = ac * (float) Math.cos(ang);
  }

  int currTime = 0;
  boolean ready = false;

  public void update(int delta) {
    if(!ready){
      currTime += delta;
      if(currTime > deployTime) {
        if(p != null){
          x = p.x + p.width/2;
          y = p.y + p.height/2;
        }
        ready = true;
      }
    }

    if (ready) {
      x += xv * delta;
      y += yv * delta;
    }
  }

  public void render(Graphics g) {
    if(ready) {
      g.rotate(x, y, (float) Math.toDegrees(ang));
      g.setColor(Color.blue);
//      g.fillRect(x, y, width, height);
      g.drawImage(world.buls.getSprite(0,1), x, y);
      g.resetTransform();
    }
  }
}
