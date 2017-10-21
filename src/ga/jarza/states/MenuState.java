package ga.jarza.states;

import ga.jarza.main.Main;
import ga.jarza.world.entities.Entity;
import ga.jarza.world.entities.MenuItem;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;


public class MenuState extends BasicGameState{

  private int ID;
  private List<Entity> e = new ArrayList<Entity>();

  public MenuState(int id){
    this.ID = id;
  }

  public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    gc.setVSync(true);
    gc.setTargetFrameRate(60);
    e.add(new MenuItem(132, 38, "play", () -> sbg.enterState(Main.ingame)));
    e.add(new MenuItem(132, 128, "exit", () -> System.exit(0)));
  }

  public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    for(Entity w : e) {
      w.render(g);
    }
  }

  public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    for(Entity w : e) {
      w.update(delta);
    }
  }

  public int getID() {
    return ID;
  }
}
