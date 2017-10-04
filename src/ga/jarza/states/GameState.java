package ga.jarza.states;

import ga.jarza.world.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState{

  private int ID;
  private World w;

  public GameState(int id){
    this.ID = id;
  }

  public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    w = new World();
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
