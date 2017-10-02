package ga.jarza.states;

import ga.jarza.main.Main;
import ga.jarza.world.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class MenuState extends BasicGameState{

  private int ID;
  private World w;
  public MenuState(int id){
    this.ID = id;
    w = new World();
  }

  public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    gc.setVSync(true);
    gc.setTargetFrameRate(60);
  }

  public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    w.render(g);
  }

  public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    w.update(delta);
  }

  public int getID() {
    return ID;
  }
}
