package ga.jarza.world.entities;

import ga.jarza.main.Main;
import ga.jarza.world.World;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Player extends Entity{

  public float width = 32f, height = width;
  public float minf = 0.04f,f = 0.5f, ac = 0.2f, xv = 0f, yv = 0f, maxac = 0.5f;

  public Player(){
    x = 32f;
    y = 32f;
  }

  public void update(int delta, World world) {
    if(Keyboard.isKeyDown(Input.KEY_W)){
      yv -= ac;
    }
    if(Keyboard.isKeyDown(Input.KEY_S)){
      yv += ac;
    }
    if(Math.abs(yv) < minf){
      yv = 0f;
    }

    if(Keyboard.isKeyDown(Input.KEY_D)){
      xv += ac;
    }
    if(Keyboard.isKeyDown(Input.KEY_A)){
      xv -= ac;
    }
    if(Math.abs(xv) < minf){
      xv = 0f;
    }

    if(Math.abs(xv) > maxac)
      xv = maxac * (xv < 0 ? -1 : 1);
    if(Math.abs(yv) > maxac)
      yv = maxac  * (yv < 0 ? -1 : 1);

    x += xv*delta;
    y += yv*delta;

    xv *= f;
    yv *= f;
  }

  public void render(Graphics g) {
    float ang, mx, my, dx, dy;

    mx = Mouse.getX();
    my = Main.getGAME_HEIGHT() - Mouse.getY();

    dx = (x + 16f) - mx;
    dy = (y + 16f) - my;

    ang = (float) Math.toDegrees((Math.atan2(dy, dx)));

    g.rotate(x + 16f, y + 16f, ang);

    g.setColor(Color.white);
    g.fillRect(x, y, width, height);

    g.resetTransform();
  }
}
