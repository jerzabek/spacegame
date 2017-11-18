package ga.jarza.handler;

import ga.jarza.components.icomponents.RenderComp;
import org.newdawn.slick.Graphics;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

public class RenderHandler {

  public static HashMap<UUID, RenderComp> renders;

  public RenderHandler(){
    renders = new HashMap<>();
  }

  public static void render(Graphics g){
    for(Entry<UUID, RenderComp> a : renders.entrySet()){
      a.getValue().render(g);
    }
  }

  public static RenderComp getRenderFor(UUID id){
    return renders.get(id);
  }

  public static HashMap<UUID, RenderComp> getRenders(){
    return renders;
  }

  public static void addRender(UUID id, RenderComp render){
    renders.put(id, render);
  }
}
