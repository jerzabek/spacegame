package ga.jarza.world.entities;

import ga.jarza.main.Main;
import ga.jarza.world.World;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;


public class MenuItem extends Entity{

  private float w, h;
  private Image i, i2;
  private float ac, maxSize, sca;
  private boolean prev = false;
  private Runnable r;

  public MenuItem(float x, float y, String imgname, Runnable r){
    super(null);
    this.x = x;
    this.y = y;
    this.r = r;

    try {
      i = new Image("res/" + imgname + ".png");
      i2 = new Image("res/arrow.png");
      w = i.getWidth();
      h = i.getHeight();
    } catch (SlickException e) {
      e.printStackTrace();
    }

    maxSize = 1.1f;
    ac = 0.004f;
    sca = 1f;
  }

  public void update(int delta) {
    if(new Rectangle(x, y, w, h).contains(new Point(Mouse.getX(), Main.getGAME_HEIGHT() - Mouse.getY()))){
      sca += ac*delta;
      if(Mouse.isButtonDown(0) && !prev){
        r.run();
      }
    }else{
      sca -= ac*delta;
    }

    if(sca > maxSize) {
      sca = maxSize;
    }else if(sca < 1f){
      sca = 1f;
    }

    prev = Mouse.isButtonDown(0);
  }

  public void render(Graphics g) {

    g.drawImage(i, x-((w*sca)-w)/2, y-((h*sca)-h)/2, x+w*sca, y+h*sca, 0f, 0f, w, h);
    if(sca > 1f){
      g.drawImage(i2, x - 29f - 0.1f*w, y + 12f);
    }
  }
}
