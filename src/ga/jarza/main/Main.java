package ga.jarza.main;

import ga.jarza.states.GameState;
import ga.jarza.states.MenuState;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class Main extends StateBasedGame {
  public static final String gamename = "RPG";
  public static final String version = "alpha";
  public static final boolean FULLSCREEN = false;
  public static final int menu = 0;
  public static final int ingame = 1;
  public static int GAME_WIDTH = 800;
  public static int GAME_HEIGHT = 600;

  public Main(String name) {
    super(name);

    // INITIALIZE STATES IN HERE
    this.addState(new MenuState(menu));
    this.addState(new GameState(ingame));
  }

  public static void main(String[] args) {
    AppGameContainer agc;
    try {
      agc = new AppGameContainer(new Main(gamename));
      if (FULLSCREEN) {
        GAME_WIDTH = agc.getScreenWidth();
        GAME_HEIGHT = agc.getScreenHeight();
      }
      agc.setUpdateOnlyWhenVisible(true);
      agc.setDisplayMode(GAME_WIDTH, GAME_HEIGHT, FULLSCREEN);
      agc.start();
    } catch (SlickException e) {
      e.printStackTrace();
    }
  }

  public static String getVersion() {
    return version;
  }

  public static int getGAME_WIDTH() {
    return GAME_WIDTH;
  }

  public static int getGAME_HEIGHT() {
    return GAME_HEIGHT;
  }

  public static boolean isFullscreen() {
    return FULLSCREEN;
  }

  public void initStatesList(GameContainer gc) throws SlickException {
    this.getState(menu).init(gc, this);
    this.getState(ingame).init(gc, this);

    this.enterState(menu);
  }
}
