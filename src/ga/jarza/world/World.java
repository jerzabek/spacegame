package ga.jarza.world;

import ga.jarza.enemies.BasicCuck;
import ga.jarza.states.GameState;
import ga.jarza.world.entities.Entity;
import ga.jarza.world.entities.HealthyEnemy;
import ga.jarza.world.entities.HealthyEntity;
import ga.jarza.world.entities.Player;
import ga.jarza.world.entities.attack.Attack;
import ga.jarza.world.entities.attack.Attacks;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class World {

  public List<Entity> e = new ArrayList<Entity>();
  public GameState bs;
  public boolean debug ;
  public SpriteSheet s, buls;
  public Attacks attacks;

  public World(GameState state) {
    attacks = new Attacks();
    e.add(new Player(this));
    for(int i = 0; i < 10; i++){
      e.add(new BasicCuck(this));
    }
    try {
      s = new SpriteSheet(new Image("res/cross.png", false, Image.FILTER_LINEAR), 36, 60);
      buls = new SpriteSheet(new Image("res/bullets.png", false, Image.FILTER_LINEAR), 18, 9);
    } catch (SlickException e1) {
      e1.printStackTrace();
    }
    bs = state;
  }

  public void update(int delta) {
    for(Entity a : e){
      a.update(delta);
    }

    if(!spawnBuffer.isEmpty()) {
      for (Entity a : spawnBuffer) {
        e.add(a);
      }
      spawnBuffer.clear();
    }

    e.removeIf(entity -> {
      boolean res = false;
      if(!entity.getClass().getName().equals(Player.class.getName())) {
        res = entity.dead;
      }
//      System.out.println(entity.getClass().getName() + " & " + HealthyEnemy.class.getClass().getName());
      return res;
    });
//    e.removeIf(entity -> collisinCheck(entity) != null );
//    System.gc();
  }

  public void render(Graphics g) {
    for(Entity a : e){
      a.render(g);
    }

  }

  public Entity collisinCheck(Entity en) {
    Entity res = null;
    for(Entity ent : e)
      if(!ent.equals(en) && !en.getClass().getName().equals(Player.class.getName()) && ent.getClass().getName().equals(BasicCuck.class.getName()) && new Rectangle(ent.x, ent.y, ent.width, ent.height).intersects(new Rectangle(en.x, en.y, en.width, en.height)))
        res = ent;

    return res;
  }
  List<Entity> spawnBuffer = new ArrayList<>();
  public void spawn(Entity e){
    spawnBuffer.add(e);
  }
}
