package ga.jarza.components.icomponents;

import ga.jarza.components.Component;
import ga.jarza.components.EntityModel;
import ga.jarza.handler.ControlHandler;

public class ControlComp extends Component{

  private EntityModel em;
  private int acceleration;
  private float xv, yv, fric = 1f;
  private boolean canMove = true;
  private boolean drag = false;

  public ControlComp(EntityModel me){
    this.em = me;
    ControlHandler.register(me.getUuid(), this);
    xv = yv = 0;
  }

  public void move(int delta){
    if(canMove){
      em.getComponent(PositionComp.class).addX(xv*delta);
      em.getComponent(PositionComp.class).addY(yv*delta);
      if(drag)
        dragv();
      else
        xv = yv = 0;
    }
  }

  public void dragv(){
    xv *= fric;
    yv *= fric;
    if(Math.abs(xv) < 0.0001f)
      xv = 0f;
    if(Math.abs(yv) < 0.0001f)
      yv = 0f;
  }

  public void left(int speedOff, float speedMul){
    if(drag)
      xv -= (acceleration/60f + speedOff)*speedMul;
    else
      xv = -(acceleration/60f + speedOff)*speedMul;

    if(Math.abs(xv) > (acceleration/60f + speedOff)*speedMul)
      xv = (acceleration/60f + speedOff)*speedMul*(xv < 0 ? -1 : 1);
  }


  public void right(int speedOff, float speedMul){
    if(drag)
      xv += (acceleration/60f + speedOff)*speedMul;
    else
      xv = (acceleration/60f + speedOff)*speedMul;

    if(Math.abs(xv) > (acceleration/60f + speedOff)*speedMul)
      xv = (acceleration/60f + speedOff)*speedMul*(xv < 0 ? -1 : 1);
  }


  public void up(int speedOff, float speedMul){
    if(drag)
      yv -= (acceleration/60f + speedOff)*speedMul;
    else
      yv = -(acceleration/60f + speedOff)*speedMul;

    if(Math.abs(yv) > (acceleration/60f + speedOff)*speedMul)
      yv = (acceleration/60f + speedOff)*speedMul*(yv < 0 ? -1 : 1);
  }


  public void down(int speedOff, float speedMul){
    if(drag)
      yv += (acceleration/60f + speedOff)*speedMul;
    else
      yv = (acceleration/60f + speedOff)*speedMul;

    if(Math.abs(yv) > (acceleration/60f + speedOff)*speedMul)
      yv = (acceleration/60f + speedOff)*speedMul*(yv < 0 ? -1 : 1);
  }

  public int getSpeed() {
    return acceleration;
  }

  public ControlComp setSpeed(int speed) {
    this.acceleration = speed;
    return this;
  }

  public float getXv() {
    return xv;
  }

  public float getYv() {
    return yv;
  }

  public float getFric() {
    return fric;
  }

  public boolean canMove() {
    return canMove;
  }

  public boolean isDrag() {
    return drag;
  }

  public ControlComp setXv(float xv) {
    this.xv = xv;
    return this;
  }

  public ControlComp setYv(float yv) {
    this.yv = yv;
    return this;
  }

  public ControlComp setFric(float fric) {
    this.fric = fric;
    return this;
  }

  public ControlComp setCanMove(boolean canMove) {
    this.canMove = canMove;
    return this;
  }

  public ControlComp setDrag(boolean drag) {
    this.drag = drag;
    return this;
  }

  public EntityModel getEm() {
    return em;
  }
}
