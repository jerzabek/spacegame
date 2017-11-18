package ga.jarza.world.entities;

import ga.jarza.main.Main;
import ga.jarza.world.World;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class NastiBullet extends Entity{

  private float xv, yv, ac;
  private int deployTime = 0;
  private Player p;
  private Image img;

  public NastiBullet(float x, float y, World world, double... delAng){
    super(world);
    this.x = x;
    this.y = y;
    width = 32f;
    height = 18f;
    col = new Rectangle(x, y, width - 4, height - 4);
    try {
      img = new Image("res/bub.png");
    } catch (SlickException e) {
      e.printStackTrace();
    }
    float mx, my, dx, dy;

    mx = world.player.x;
    my = world.player.y;

    dx = -(x) + mx;
    dy = -(y) + my;

    ang = (float) (Math.atan2(dy, dx));

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

  public NastiBullet  (float x, float y, World world, Player p, double... delAng){
    this(x, y, world, delAng);
    this.p = p;
  }

  int currTime = 0;
  boolean ready = false;

  public void update(int delta) {
    if(deployTime != 0 && !ready){
      currTime += delta;
      if(currTime > deployTime) {
        if(p != null){
          x = p.x + p.width/2 + 60;
          y = p.y + p.height*2;
        }
        ready = true;
      }
    }else{
      ready = true;
    }
    if (ready) {
      x += xv * delta;
      y += yv * delta;
    }


    col.setX(x);
    col.setY(y);
  }

  public void render(Graphics g) {
    if(ready) {
      g.rotate(x, y, (float) Math.toDegrees(ang));
      g.setColor(Color.red);
      if(world.debug)
        g.draw(getCol());
      //        g.drawRect(x, y, width, height);
      img.draw(x, y - img.getHeight()/2 +height/2);
      g.resetTransform();
    }
  }
}
