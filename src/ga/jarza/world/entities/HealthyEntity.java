package ga.jarza.world.entities;

import ga.jarza.world.World;

public abstract class HealthyEntity extends Entity{

  public int hp, maxhp = -1;
  public int def, maxdef = -1;
  public boolean dead;

  public HealthyEntity(World world) {
    super(world);
    hp = maxhp;
    def = maxdef;
  }

  public void setMaxHp(int hp){
    this.maxhp = hp;
  }

  public void setMaxDef(int def){
    this.maxdef = def;
  }

  /**
   * @param ignoreDef true if u want true armor penetration
   */
  public void dealDmg(int dmg, boolean ignoreDef){
    if(!ignoreDef){
      hp -= (dmg - def);
    }else{
      hp -= dmg;
    }
  }
}
