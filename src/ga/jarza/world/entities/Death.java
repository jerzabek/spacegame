package ga.jarza.world.entities;

import ga.jarza.world.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.List;

public class Death extends Entity{

  private List<deathRect> r = new ArrayList<>();
  private int life, ttime;
  private float ang = -2;

  public Death(World world, float x, float y, int life){
    super(world);
    this.x = x;
    this.y = y;
    this.life = life;
  }

  public Death(World world, float x, float y, int life, float ang){
    this(world, x, y, life);
    this.ang = ang;
  }

  int time = 101;
  public void update(int delta) {
    time += delta;
    ttime += delta;
    if(ttime > 600){
      dead = true;
      return;
    }
    if(time > 100 && ttime < life){
      Color c = new Color((float) Math.random(), (float) Math.random()*0.18f, (float) Math.random()*0.2f);
      if(ang == -2)
        r.add(new deathRect(world, true, x, y, c, 3000));
      else
        r.add(new deathRect(world, false, ang, x, y, c, 3000));

    }
    for(deathRect a : r){
      a.update(delta);
    }
    r.removeIf(bub -> bub.dead);
  }

  public void render(Graphics g) {
    for(deathRect a : r){
      a.render(g);
    }
  }
}
