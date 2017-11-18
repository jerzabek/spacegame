package ga.jarza.components.icomponents;

import ga.jarza.components.Component;
import ga.jarza.components.IRender;
import ga.jarza.handler.RenderHandler;
import org.newdawn.slick.Graphics;

import java.util.UUID;

public class RenderComp extends Component{

  private IRender render;

  public RenderComp(UUID id, IRender render){
    this.render = render;
    RenderHandler.addRender(id, this);
  }

  public void render(Graphics g){
    render.render(g);
  }

}
