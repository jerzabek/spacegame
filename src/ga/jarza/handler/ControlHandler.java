package ga.jarza.handler;

import ga.jarza.components.icomponents.ControlComp;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Input;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

public class ControlHandler {

  private static HashMap<UUID, ControlComp> listeners;

  public ControlHandler(){
    listeners = new HashMap<>();
  }

  public static void update(int delta){
    for(Entry<UUID, ControlComp> entry : listeners.entrySet()) {
      if (Keyboard.isKeyDown(Input.KEY_A)) {
        //Left
        entry.getValue().left(EffectHandler.getEffectFor(entry.getKey()).getAttackOffset(), EffectHandler.getEffectFor(entry.getKey()).getAttackMultiplyer());
      }else if(Keyboard.isKeyDown(Input.KEY_D)){
        //Right
        entry.getValue().right(EffectHandler.getEffectFor(entry.getKey()).getAttackOffset(), EffectHandler.getEffectFor(entry.getKey()).getAttackMultiplyer());
      }

      if(Keyboard.isKeyDown(Input.KEY_S)){
        //Down
        entry.getValue().down(EffectHandler.getEffectFor(entry.getKey()).getAttackOffset(), EffectHandler.getEffectFor(entry.getKey()).getAttackMultiplyer());
      }else if(Keyboard.isKeyDown(Input.KEY_W)){
        //Up
        entry.getValue().up(EffectHandler.getEffectFor(entry.getKey()).getAttackOffset(), EffectHandler.getEffectFor(entry.getKey()).getAttackMultiplyer());
      }

      entry.getValue().move(delta);
    }

  }

  public static void register(UUID id, ControlComp c){
    listeners.put(id, c);
  }

  public static void remove(UUID id){
    if(listeners.containsKey(id)){
      listeners.remove(id);
    }
  }

  public static void clear(){
    listeners.clear();
  }

}
