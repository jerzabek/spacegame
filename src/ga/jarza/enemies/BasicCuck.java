package ga.jarza.enemies;

import ga.jarza.main.Main;
import ga.jarza.world.World;
import ga.jarza.world.entities.HealthyEnemy;
import ga.jarza.world.entities.HealthyEntity;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class BasicCuck extends HealthyEnemy {

//  private Image img;
  float ang;

  public BasicCuck(World world) {
    super(world);
    setMaxHp(10);
    width = 32f;
    height = 32f;
//    try {
//      img = new Image("res/basiccuck.png");
//    } catch (SlickException e) {
//      e.printStackTrace();
//    }
    x = (float) (Main.getGAME_WIDTH()*Math.random());
    y = (float) (Main.getGAME_HEIGHT()*Math.random());
  }

  public void update(int delta) {
    ang += 0.5f*delta;

    if(hp < 0){
      dead = true;
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
    g.setColor(Color.red);
    g.fillRect(x, y, width, height);
    g.resetTransform();
  }
}
