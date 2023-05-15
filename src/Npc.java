public class Npc {
  private final char playingPiece;
  private int hitPoints;
  private final Dice dice;
  private int position;

  public Npc(char playingPiece, int diceSide) {
    this.playingPiece = playingPiece;
    this.dice = new Dice(diceSide);
    this.hitPoints = dice.throwDice() + 3;
  }

  public void takeDamage(int damage) {
    hitPoints -= damage;
  }

  public int giveDamage() {
    return dice.throwDice();
  }

  public String printHealth() {
    return playingPiece + ":" + hitPoints;
  }

  public int getHitPoints() {
    return hitPoints;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public int getPosition() {
    return position;
  }

  public void move() {
    int diceThrow = dice.throwDice();
    switch (diceThrow) {
      case 1, 2 -> this.position = 0;
      case 3, 4 -> this.position = 1;
      case 5, 6 -> this.position = 2;
      default -> {
      //To-Do: throw new UnexpectedMoveException
      }
    }
  }

  public String print() {
    return String.valueOf(playingPiece);
  }
}
