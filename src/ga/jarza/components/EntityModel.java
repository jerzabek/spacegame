package ga.jarza.components;

import java.util.HashMap;
import java.util.UUID;

public class EntityModel {

  protected HashMap<Class<? extends Component>, Component> components;
  protected UUID uuid;

  public EntityModel(){
    components = new HashMap<>();
  }

  public <T extends Component> EntityModel addComponent(T t){
    synchronized (components) {
      if(!containsComponent(t.getClass()))
        components.put(t.getClass(), t);
    }
    return this;
  }

  public HashMap<Class<? extends Component>, Component> getComponents() {
    return components;
  }

  public boolean containsComponent(Class<? extends Component> t){
    return (components.containsKey(t));
  }

  public <T extends Component> T getComponent(Class<T> t){
    return (T) getComponents().get(t);
  }

  public UUID getUuid() {
    return uuid;
  }

  public EntityModel setUuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }
}
