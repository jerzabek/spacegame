package ga.jarza.world.entities;

import ga.jarza.world.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class deathRect extends Entity{

  public Color col;
  private double ang, fric, grav;
  private float xv, yv;
  private int life = -1, tim;
  public boolean isGrav;
  public int dir = 1;

  public deathRect(World world, boolean isG, float x, float y, Color col){
    super(world);
    this.x = x;
    this.y = y;
    this.col = col;
    ang = Math.random()*2*Math.PI;
    fric = 0.6d + (Math.random()*0.2);
    this.isGrav = isG;
    grav = 0.75;

    xv = (float) Math.cos(ang)*(19 + (int) Math.random()*12);
    yv = (float) Math.sin(ang)*(19 + (int) Math.random()*12);
  }

  public deathRect(World world, boolean isG, float x, float y, Color col, int life){
    this(world, isG, x, y, col);
    this.life = life;
  }

  public deathRect(World world, boolean isG, float ang, float x, float y, Color col, int i) {
    super(world);
    this.x = x;
    this.y = y;
    this.col = col;
    this.life = i;
    fric = 0.6d + (Math.random()*0.2);
    this.isGrav = isG;
    grav = 0.75;
    this.ang = (float) ((float) Math.toRadians(ang) + (-Math.PI/4 + Math.random()*Math.PI/2) -Math.PI/2); // + (-Math.PI/4 + Math.random()*Math.PI/2)
//    ang = Math.random()*2*Math.PI;
//    System.out.println(p.ang);
    xv = (float) Math.cos(this.ang)*(19 + (int) Math.random()*12);
    yv = (float) Math.sin(this.ang)*(19 + (int) Math.random()*12);
  }

  public deathRect(World world, boolean isG, float ang, float x, float y, Color col) {
    super(world);
    this.x = x;
    this.y = y;
    this.col = col;
    fric = 0.6d + (Math.random()*0.2);
    this.isGrav = isG;
    grav = 0.75;
    this.ang = (float) ((float) Math.toRadians(ang) * -1 + (-Math.PI/4 + Math.random()*Math.PI/2));
    xv = (float) Math.cos(this.ang)*(19 + (int) Math.random()*12);
    yv = (float) Math.sin(this.ang)*(19 + (int) Math.random()*12)*dir;
  }

  public void update(int delta) {
    x += xv;
    y += yv;
    xv *= fric;
    yv *= fric;

    if(isGrav)
      yv += grav*dir;

    tim += delta;
    if(life != -1){
      if(tim > life || Math.abs(xv) + Math.abs(xv) < 0.05){
        dead = true;
      }
    }
  }

  int w = 2 + (int) (Math.random()*4);
  public void render(Graphics g) {
    g.setColor(col);
    g.fillRect(x, y, w, w);
//    if(Math.random() > 0.5){
//      g.drawRect(x, y, w, w);
//    }else{
//
//    }
  }
}
