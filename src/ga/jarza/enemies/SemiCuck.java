package ga.jarza.enemies;

import ga.jarza.main.Main;
import ga.jarza.world.World;
import ga.jarza.world.entities.Death;
import ga.jarza.world.entities.Entity;
import ga.jarza.world.entities.HealthyEnemy;
import ga.jarza.world.entities.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;

public class SemiCuck extends HealthyEnemy {

  //  private Image img;

  public SemiCuck(World world) {
    super(world);
    setMaxHp(120);
    width = 40f;
    height = 40f;
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
  }

  private Entity target;
  float speed = 0.1f;
  int sinceDmg, dex;
  public void update(int delta) {
    //    ang += 0.5f*delta;
    dex = 25;
    if(hp < 0){
      dead = true;
      //      world.e.add(new Death(world, x, y));
      world.spawn(new Death(world, x, y, 500));
    }

    for(Entity a : world.e){
      if(a.getClass().equals(Player.class)){
        if(Math.sqrt(Math.pow((a.x - x), 2) + Math.pow((a.y - y), 2)) < 750f){
          target = a;
        }
        break;
      }
    }

    if(target != null){
      float mx, my, dx, dy;

      mx = target.x;
      my = target.y;

      dx = mx - x;
      dy = my - y;

      ang = (float) (Math.atan2(dy, dx));

      if(Math.sqrt(dx*dx + dy*dy) > 740){
        target = null;

      }else {

        xv = (float) (((float) Math.cos(ang) * speed)*(0.5 + Math.random()* 2));
        yv = (float) (((float) Math.sin(ang) * speed)*(0.5 + Math.random()* 2));

        y += yv * delta/8;
        x += xv * delta/8;

      }
    }else if(Math.random() < 0.04d){
      xv = (float) ((float) Math.cos(Math.random()*2*Math.PI) * speed * (0.5d + Math.random()));
      yv = (float) ((float) Math.sin(Math.random()*2*Math.PI) * speed * (0.5d + Math.random()));
    }else if(Math.random() < 0.06d){{
      xv = 0;
      yv = 0;
    }
      //      if(Math.random() > 0.1d){
      //        x += xv*delta;
      //        y += yv*delta;
      //      }
    }
    x += xv*delta;
    y += yv*delta;

    if(x < 0)
      x = 0;

    if(y < 0)
      y = 0;

    if(x > Main.getGAME_WIDTH() - width)
      x = Main.getGAME_WIDTH() - width;

    if(y > Main.getGAME_HEIGHT() - height)
      y = Main.getGAME_HEIGHT() - height;


    col.setCenterX(x + width/2);
    col.setCenterY(y + height/2);
    col.transform(Transform.createRotateTransform(ang));
    sinceDmg += delta;
    if(world.player.getCol().intersects(getCol()) && sinceDmg > dex){
      world.player.dealDmg(1, false);
      sinceDmg = 0;
    }
  }

  public void render(Graphics g) {
    //    float mx, my, dx, dy;
    //
    //    mx = Mouse.getX();
    //    my = Main.getGAME_HEIGHT() - Mouse.getY();
    //
    //    dx = (x + 16f) - mx;
    //    dy = (y + 16f) - my;

    //    ang = (float) Math.toDegrees((Math.atan2(dy, dx))) - 90f;

    //    g.rotate(x + img.getWidth()/2, y + img.getHeight()/2, ang);
    //    g.drawImage(img, x, y);
    g.setColor(new Color(((float) hp)/maxhp, 0.8f, 0.1f));
    g.fillRect(x, y, width, height);
    if(world.debug) {
      g.setColor(Color.red);
      g.draw(getCol());
    }
    g.resetTransform();
  }
}
