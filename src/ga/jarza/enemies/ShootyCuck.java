package ga.jarza.enemies;

import ga.jarza.main.Main;
import ga.jarza.world.World;
import ga.jarza.world.entities.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;

import java.util.ArrayList;
import java.util.List;

public class ShootyCuck extends HealthyEnemy {

  //  private Image img;
  private List<Entity> b = new ArrayList<>();
  public ShootyCuck(World world) {
    super(world);
    setMaxHp(20);
    width = 16f;
    height = 16f;
    //    try {
    //      img = new Image("res/basiccuck.png");
    //    } catch (SlickException e) {
    //      e.printStackTrace();
    //    }
    x = (float) (Main.getGAME_WIDTH()*Math.random());
    y = (float) (Main.getGAME_HEIGHT()*Math.random());

    col = new Rectangle(x, y, width - 4, height - 4);
    col.setCenterX(x + width/2);
    col.setCenterY(y + height/2);
    target = world.player;
  }

  private Entity target;
  float speed = 0.35f;
  int dex;
  float mx, my, dx, dy;
  int timeSinceLastShot;
  public void update(int delta) {
    //    ang += 0.5f*delta;
    dex = 200;
    if(hp < 0){
      dead = true;
      //      world.e.add(new Death(world, x, y));
      world.spawn(new Death(world, x, y, 250));
    }

    mx = target.x;
    my = target.y;

    dx = mx - x;
    dy = my - y;

    ang = (float) (Math.random()*2*Math.PI);

    if(Math.random() > 0.9d){
      xv = (float) (((float) Math.cos(ang) * speed) * (0.5 + Math.random() * 2));
      yv = (float) (((float) Math.sin(ang) * speed) * (0.5 + Math.random() * 2));
    }

    x += xv * delta;
    y += yv * delta;


    if(x < 0)
      x = 0;

    if(y < 0)
      y = 0;

    if(x > Main.getGAME_WIDTH() - width)
      x = Main.getGAME_WIDTH() - width;

    if(y > Main.getGAME_HEIGHT() - height)
      y = Main.getGAME_HEIGHT() - height;

    if (timeSinceLastShot > dex) {
      b.add(new NastiBullet(x + width / 2, y + height / 2, world));

      timeSinceLastShot = 0;
    }
    timeSinceLastShot += delta;

    for(Entity t : b){
      t.update(delta);
      if(t.getCol().intersects(world.player.getCol())){
        world.player.dealDmg(4, false);
        t.dead = true;
      }
    }

    b.removeIf(b -> b.dead);

    col.setCenterX(x + width/2);
    col.setCenterY(y + height/2);
    col.transform(Transform.createRotateTransform(ang));
  }

  public void render(Graphics g) {
    //    float mx, my, dx, dy;
    //
    //    mx = Mouse.getX();
    //    my = Main.getGAME_HEIGHT() - Mouse.getY(); Width()/2, y + img.getHeight()/2, ang);
    //    g.drawImage(img, x, y);
    g.setColor(new Color(((float) hp)/maxhp, 0.1f, 0.2f));
    g.fillRect(x, y, width, height);
    if(world.debug) {
      g.setColor(Color.red);
      g.draw(getCol());
    }
    g.resetTransform();

    for(Entity t : b){
      t.render(g);
    }
  }
}
