package ga.jarza.handler;

import ga.jarza.handler.effects.Effect;

import java.util.HashMap;
import java.util.UUID;

public class EffectHandler {
  private static HashMap<UUID, Effect> effects;

  public EffectHandler(){
    effects = new HashMap<>();
  }

  public static HashMap<UUID, Effect> getEffects(){
    return effects;
  }

  public static Effect getEffectFor(UUID id){
    return effects.getOrDefault(id, new Effect());
  }

}
