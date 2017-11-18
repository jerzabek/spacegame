package ga.jarza.components.icomponents;

import ga.jarza.components.Component;
import ga.jarza.world.World;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class PositionComp extends Component{

  private float x, y;
  private float width, height;
  private float ang;
  private Shape col;
  public World world;


  public PositionComp(World world){
    this.world = world;
  }

  public float getX() {
    return x;
  }

  public PositionComp setX(float x) {
    this.x = x;
    return this;
  }

  public PositionComp addX(float x) {
    this.x += x;
//    System.out.println("added" + x + " now we're at: " + this.x);
    return this;
  }

  public float getY() {
    return y;
  }

  public PositionComp setY(float y) {
    this.y = y;
    return this;
  }

  public PositionComp addY(float y) {
    this.y += y;
//    System.out.println("added" + x + " now we're at: " + this.x);
    return this;
  }

  public float getWidth() {
    return width;
  }

  public PositionComp setWidth(float width) {
    this.width = width;
    return this;
  }

  public float getHeight() {
    return height;
  }

  public PositionComp setHeight(float height) {
    this.height = height;
    return this;
  }

  public float getAng() {
    return ang;
  }

  public PositionComp setAng(float ang) {
    this.ang = ang;
    return this;
  }

  public Shape getCol() {
    return col;
  }

  public PositionComp setCol(Rectangle col) {
    this.col = col;
    return this;
  }

  public Shape getColAdjust(){
    return col.transform(Transform.createRotateTransform((float)Math.toRadians(ang), col.getCenterX(), col.getCenterY()));
  }
}
