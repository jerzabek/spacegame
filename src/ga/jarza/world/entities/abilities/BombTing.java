package ga.jarza.world.entities.abilities;

import ga.jarza.world.World;
import ga.jarza.world.entities.Entity;
import ga.jarza.world.entities.HealthyEnemy;
import ga.jarza.world.entities.HealthyEntity;
import ga.jarza.world.entities.Player;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Ellipse;

public class BombTing extends Entity{

  public float x, y, rad;
  private Animation a;
  private Ellipse dm;
  public BombTing(World world, float x, float y, float rad){
    super(world);
    this.x = x;
    this.y = y;
    this.rad = rad;
    Image[] ims = new Image[13];
    int[] durs = new int[13];
    for(int i = 0; i < 13; i++){
      try {
        ims[i] = new Image("res/explosion/1/" + i + ".png", false, Image.FILTER_NEAREST).getScaledCopy(0.25f);
        durs[i] = 60 + i * 3;
      } catch (SlickException e) {
        e.printStackTrace();
      }
    }
    a = new Animation(ims, durs);
    dm = new Ellipse(x, y, rad, rad);
    a.setLooping(false);
  }
  int life;
  public void update(int delta) {
    world.e.forEach(en -> {
      if (en.getClass().getSuperclass().getName().equals(HealthyEntity.class.getName())) {
        if (en.getCol().intersects(dm) || dm.contains(en.getCol())) {
          if (en.getClass().getName().equals(Player.class.getName())) {
            ((HealthyEntity) en).dealDmg(1, true);
          }else{
            ((HealthyEntity) en).dealDmg(2, true);
          }
        }
      }else if(en.getClass().getSuperclass().getName().equals(HealthyEnemy.class.getName())){
        if (en.getCol().intersects(dm) || dm.contains(en.getCol())) {
          ((HealthyEnemy) en).dealDmg(4, true);
        }
      }
    });

    life += delta;
    if(life >= 1300){
      dead = true;
    }
  }

  public void render(Graphics g) {
    a.draw(dm.getCenterX() - a.getWidth()/2, dm.getCenterY() - a.getHeight()/2);
    if(world.debug){
      g.setColor(Color.red);
      g.draw(dm);
    }
  }
}
