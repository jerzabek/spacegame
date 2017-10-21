package ga.jarza.world.entities;

import ga.jarza.main.Main;
import ga.jarza.world.World;
import ga.jarza.world.entities.attack.Attack;
import ga.jarza.world.entities.attack.Attacks;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Player extends HealthyEntity{
  public float minf = 0.04f,f = 0.5f, ac = 200f, xv = 0f, yv = 0f;
  private float ang = 0f;
  private int timeSinceLastShot = 0;
  private int dex;
  private World world;
  private Attack ult;

//  private Image img;
  private List<Entity> b = new ArrayList<>();

  public Player(World world){
    super(world);
    setMaxHp(100);
    ult = world.attacks.attacks.get("basic");
    x = 32f;
    y = 32f;
    width = 32f;
    height = width;
    this.world = world;
//    try {
//      img = new Image("res/basiccuck.png");
//    } catch (SlickException e) {
//      e.printStackTrace();
//    }
    dex = 200;
//    timeSinceLastShot = dex;
  }
  int buls = 0;
  double bps = 0d;
  int currentShootingSessionLength;

  public void update(int delta) {
    dex = 250;
    if(Keyboard.isKeyDown(Input.KEY_W)){
      yv -= ac;
    }
    if(Keyboard.isKeyDown(Input.KEY_S)){
      yv += ac;
    }
    if(Math.abs(yv) < minf){
      yv = 0f;
    }

    if(Keyboard.isKeyDown(Input.KEY_D)){
      xv += ac;
    }
    if(Keyboard.isKeyDown(Input.KEY_A)){
      xv -= ac;
    }
    if(Math.abs(xv) < minf){
      xv = 0f;
    }

//    if(Math.abs(xv) > maxac)
//      xv = maxac * (xv < 0 ? -1 : 1);
//    if(Math.abs(yv) > maxac)
//      yv = maxac  * (yv < 0 ? -1 : 1);

    x += xv*delta/1000f;
    y += yv*delta/1000f;

    xv *= f;
    yv *= f;

    if(x < 0)
      x = 0;

    if(y < 0)
      y = 0;

    if(x > Main.getGAME_WIDTH() - width)
      x = Main.getGAME_WIDTH() - width;

    if(y > Main.getGAME_HEIGHT() - height)
      y = Main.getGAME_HEIGHT() - height;

    if(Mouse.isButtonDown(0)) {
      currentShootingSessionLength += delta;
      if (timeSinceLastShot > dex) {
        b.add(new Bullet(x + width / 2, y + height / 2, world));

        timeSinceLastShot = 0;
        buls++;
      }
    }else{
      currentShootingSessionLength = 0;
      buls = 0;
    }

    ult.update(delta, x, y, b, world, this);

    timeSinceLastShot += delta;


    if(currentShootingSessionLength >= 1000){
      bps = ((double)buls/ currentShootingSessionLength)*1000;
//      currentShootingSessionLength = 0;
//      buls = 0;
    }
    for(Entity t : b){
      t.update(delta);
    }

    b.removeIf(b -> {
      Entity colHit = world.collisinCheck(b);
      if(colHit != null) {
        ((HealthyEnemy) colHit).dealDmg(2, true);
      }
      return !(new Rectangle(0, 0, Main.getGAME_WIDTH(), Main.getGAME_HEIGHT()).contains(new Point((int)b.x, (int)b.y)))  || (colHit == null ? false : true);
    });

    if(hp < 0){
      dead = true;
    }
  }

  public void render(Graphics g) {
    float mx, my, dx, dy;

    mx = Mouse.getX();
    my = Main.getGAME_HEIGHT() - Mouse.getY();

    dx = (x + width/2) - mx;
    dy = (y + height/2) - my;

    if(!world.bs.paused)
      ang = (float) Math.toDegrees((Math.atan2(dy, dx))) + 90f;

    g.rotate(x + width/2, y + height/2, ang);

    g.setColor(Color.white);

//    world.s.getSprite(1, 0).draw((int)x, (int)y);
    g.fillRect(x, y, width, height);
//    g.drawImage(img, x, y);


    g.resetTransform();
    if(world.debug) {
      g.setColor(Color.white);
      g.drawString(new StringBuilder().append(timeSinceLastShot).append(' ').append('m').append('s').toString(), 10, 32);
      g.drawString(new StringBuilder().append(new DecimalFormat("##.##").format(bps)).append(' ').append('b').append('p').append('s').toString(), 10, 48);
      g.drawString(new StringBuilder().append(buls).toString(), 10, 64);
      g.drawString(new StringBuilder().append(currentShootingSessionLength).append(' ').append('t').append('p').append('a').append('s').append('s').toString(), 10, 80);
    }
    for(Entity t : b){
      t.render(g);
    }
  }
}
