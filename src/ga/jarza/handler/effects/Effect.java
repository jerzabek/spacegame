package ga.jarza.handler.effects;


public class Effect {

  private int speedOffset = 0;
  private float speedMultiplyer = 1f;
  private int attackOffset = 0;
  private float attackMultiplyer = 1f;
  private int defenseOffset = 0;
  private float defenseMultiplyer = 1f;

  public int getSpeedOffset() {
    return speedOffset;
  }

  public Effect setSpeedOffset(int speedOffset) {
    this.speedOffset = speedOffset;
    return this;
  }

  public float getSpeedMultiplyer() {
    return speedMultiplyer;
  }

  public Effect setSpeedMultiplyer(float speedMultiplyer) {
    this.speedMultiplyer = speedMultiplyer;
    return this;
  }

  public int getAttackOffset() {
    return attackOffset;
  }

  public Effect setAttackOffset(int attackOffset) {
    this.attackOffset = attackOffset;
    return this;
  }

  public float getAttackMultiplyer() {
    return attackMultiplyer;
  }

  public Effect setAttackMultiplyer(float attackMultiplyer) {
    this.attackMultiplyer = attackMultiplyer;
    return this;
  }

  public int getDefenseOffset() {
    return defenseOffset;
  }

  public Effect setDefenseOffset(int defenseOffset) {
    this.defenseOffset = defenseOffset;
    return this;
  }

  public float getDefenseMultiplyer() {
    return defenseMultiplyer;
  }

  public Effect setDefenseMultiplyer(float defenseMultiplyer) {
    this.defenseMultiplyer = defenseMultiplyer;
    return this;
  }
}
