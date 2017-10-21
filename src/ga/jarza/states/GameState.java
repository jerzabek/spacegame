package ga.jarza.states;

import ga.jarza.main.Main;
import ga.jarza.world.World;
import ga.jarza.world.entities.MenuItem;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;

public class GameState extends BasicGameState{

  private int ID;
  private World w;
  public boolean paused = false;
  private List<MenuItem> pauseMenu = new ArrayList<>();

  public GameState(int id){
    this.ID = id;
  }

  public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    w = new World(this);
    pauseMenu.add(new MenuItem(Main.getGAME_WIDTH()/2-200, Main.getGAME_HEIGHT()/2 - 118, "continue", () -> paused = false));

    pauseMenu.add(new MenuItem(Main.getGAME_WIDTH()/2-90, Main.getGAME_HEIGHT()/2 - 37, "back", () -> sbg.enterState(Main.menu)));

    pauseMenu.add(new MenuItem(Main.getGAME_WIDTH()/2-75, Main.getGAME_HEIGHT()/2 + 37, "exitsmol", () -> System.exit(0)));
  }
  String dbmode = "F3 for debug mode";
  boolean keyprev;
  public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    w.render(g);
    if(paused){
      for(MenuItem a : pauseMenu){
        Color bcol = new Color(0.6f,0.4f,0.8f, 0.04f);
        g.setColor(bcol);
        g.fillRect(0, 0, Main.getGAME_WIDTH(), Main.getGAME_HEIGHT());
//        g.drawString("what the heck", 258,  32);
        a.render(g);
      }
      g.setColor(Color.white);
      g.drawString(dbmode, 10, Main.getGAME_HEIGHT() - 2 - g.getFont().getHeight(dbmode));
    }
  }

  boolean prev = false;
  public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    if(!prev && Keyboard.isKeyDown(Input.KEY_ESCAPE)){
      paused = !paused;
    }

    if(!paused) {
      w.update(delta);
    }else{
      for(MenuItem a : pauseMenu) {
        a.update(delta);
      }
      if(!keyprev && Keyboard.isKeyDown(Input.KEY_F3)){
        w.debug = !w.debug;
      }
      keyprev = Keyboard.isKeyDown(Input.KEY_F3);
    }

    prev = Keyboard.isKeyDown(Input.KEY_ESCAPE);
  }

  public int getID() {
    return ID;
  }
}
