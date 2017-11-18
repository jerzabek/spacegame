package ga.jarza.world;

import ga.jarza.components.EntityModel;
import ga.jarza.components.icomponents.ControlComp;
import ga.jarza.components.icomponents.PositionComp;
import ga.jarza.components.icomponents.RenderComp;
import ga.jarza.enemies.BasicCuck;
import ga.jarza.enemies.SemiCuck;
import ga.jarza.enemies.ShootyCuck;
import ga.jarza.main.Gfx;
import ga.jarza.states.GameState;
import ga.jarza.world.entities.*;
import ga.jarza.world.entities.attack.Attacks;
import javafx.geometry.Pos;
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
  public Player player;
  private EntityManager entitiymanager;
  private Gfx gfx;

  public World(GameState state) {
//    attacks = new Attacks();
//    player = new Player(this);
//    e.add(player);
//    for(int i = 0; i < 10; i++){
//      e.add(new BasicCuck(this));
//    }
//    try {
//      s = new SpriteSheet(new Image("res/cross.png", false, Image.FILTER_LINEAR), 36, 60);
//      buls = new SpriteSheet(new Image("res/bullets.png", false, Image.FILTER_LINEAR), 18, 9);
//    } catch (SlickException e1) {
//      e1.printStackTrace();
//    }
//    bs = state;
    gfx = new Gfx();
    entitiymanager = new EntityManager(this);
    EntityModel player = new EntityModel();
    player.addComponent(new PositionComp(this).setX(32).setY(32))
      .addComponent(new RenderComp(player.getUuid(), g -> {
//        System.out.println("o shite");
//      g.drawString("player lel", player.getComponent(PositionComp.class).getX(), player.getComponent(PositionComp.class).getY());
      Gfx.loadGfx("res/bub").draw(player.getComponent(PositionComp.class).getX(), player.getComponent(PositionComp.class).getY());
    }))
      .addComponent(new ControlComp(player).setSpeed(50));
    entitiymanager.spawn(player);
  }

  public void update(int delta) {
//    for(Entity a : e){
//      a.update(delta);
//    }
//
//    if(!spawnBuffer.isEmpty()) {
//      for (Entity a : spawnBuffer) {
//        e.add(a);
//      }
//      spawnBuffer.clear();
//    }
//
//    e.removeIf(entity -> {
//      boolean res = false;
//      if(!entity.getClass().getName().equals(Player.class.getName())) {
//        res = entity.dead;
//      }
////      System.out.println(entity.getClass().getName() + " & " + HealthyEnemy.class.getClass().getName());
//      return res;
//    });

//    if(e.size() < 7){
//      spawn(new SemiCuck(this));
//      spawn(new ShootyCuck(this));
//    }
//    e.removeIf(entity -> collisinCheck(entity) != null );
//    System.gc();

    entitiymanager.update(delta);
  }

  public void render(Graphics g) {
//    for(Entity a : e){
//      a.render(g);
//    }
    entitiymanager.render(g);
  }

  public Entity collisinCheck(Entity en) {
    Entity res = null;
    for(Entity ent : e)
      if(!ent.equals(en) && !en.getClass().getName().equals(Player.class.getName()) && ent.getClass().getSuperclass().getName().equals(HealthyEnemy.class.getName())
        && new Rectangle(ent.x, ent.y, ent.width, ent.height).intersects(new Rectangle(en.x, en.y, en.width, en.height)))
        res = ent;

    return res;
  }
  List<Entity> spawnBuffer = new ArrayList<>();
  public void spawn(Entity e){
    spawnBuffer.add(e);
  }
}
