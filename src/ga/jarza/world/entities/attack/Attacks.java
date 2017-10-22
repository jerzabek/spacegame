package ga.jarza.world.entities.attack;

import ga.jarza.world.World;
import ga.jarza.world.entities.BlastBullet;
import ga.jarza.world.entities.Entity;
import ga.jarza.world.entities.Player;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Input;
import java.util.HashMap;
import java.util.List;

public class Attacks {

  public HashMap<String, Attack> attacks = new HashMap<>();

  public Attacks(){
    attacks.put("basic", new Attack() {
      int timeSinceUlt, itr;
      double atri;
      boolean prevUlt;
      public void update(int delta, float x, float y, List<Entity> b, World world, Player p) {
        timeSinceUlt += delta;
        if(Keyboard.isKeyDown(Input.KEY_SPACE) && !prevUlt){
          if(timeSinceUlt > 750){
            itr++;
            if(itr % 2 == 0)
              atri = -1;
            else
              atri = 1;

            for(int i = -7; i <= 7; i += 1){
              b.add(new BlastBullet(x, y, world, p, -Math.toRadians(i*5), 25*i*atri));
//              timeSinceUlt = 0;
            }
          }
        }
        prevUlt = Keyboard.isKeyDown(Input.KEY_SPACE);
      }
    });
  }

}
