public class Npc implements Comparable<Npc> {
  private final char playingPiece;
  private int hitPoints;
  private final Dice dice;
  private int position;

  public Npc(char playingPiece, int diceSide, int position) {
    this.playingPiece = playingPiece;
    this.dice = new Dice(diceSide);
    this.hitPoints = dice.throwDice() + 3;
    this.position = position;
  }

  public void takeDamage(int damage) {
    hitPoints = Math.max(hitPoints - damage, 0);
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

  public int getPosition() {
    return position;
  }

  public void move() {
    int diceThrow = dice.throwDice();
    switch (diceThrow) {
      case 1, 2 -> this.position = 0;
      case 3, 4 -> this.position = 1;
      case 5, 6 -> this.position = 2;
      default -> {}
    }
  }

  public String print() {
    return String.valueOf(playingPiece);
  }

  @Override
  public int compareTo(Npc npc) {
    return this.position - npc.position;
  }
}
