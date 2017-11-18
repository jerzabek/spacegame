package ga.jarza.main;

import org.newdawn.slick.Image;

import java.util.HashMap;

public class Gfx {

  public static HashMap<String, Image> data;

  public Gfx(){
    data = new HashMap<>();
  }

  public static Image loadGfx(String key){
    if(!data.containsKey(key)){
      try{
        data.put(key, new Image(key + ".png"));
      }catch(Throwable e){
        e.printStackTrace();
      }
    }
    return data.get(key);
  }

}
