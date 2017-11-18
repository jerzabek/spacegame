package ga.jarza.world.entities;

import ga.jarza.components.EntityModel;
import ga.jarza.handler.ControlHandler;
import ga.jarza.handler.EffectHandler;
import ga.jarza.handler.RenderHandler;
import ga.jarza.world.World;
import org.newdawn.slick.Graphics;

import java.util.HashMap;
import java.util.UUID;

public class EntityManager {

  private static HashMap<UUID, EntityModel> entities;
  private World world;
  private ControlHandler controlhandler;
  private EffectHandler effecthandler;
  private RenderHandler renderhandler;

  public EntityManager(World world){
    this.world = world;
    entities = new HashMap<>();
    controlhandler = new ControlHandler();
    effecthandler = new EffectHandler();
    renderhandler = new RenderHandler();
  }

  public void update(int delta){
    controlhandler.update(delta);
  }

  public void render(Graphics g){
    renderhandler.render(g);
  }

  public EntityManager spawn(EntityModel e){
    synchronized (entities){
      UUID temp = UUID.randomUUID();
      entities.put(temp, e);
    }
    return this;
  }
}
