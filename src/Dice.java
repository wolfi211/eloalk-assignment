import java.util.Random;

public class Dice {
    private int side;
    private Random dice;

    public Dice(int side) {
        this.side = side;
        dice = new Random();
    }

    public int getSide() {
        return side;
    }

    public int throwDice() {
        return dice.nextInt(side) + 1;
    }

    public String print() {
        return "d" + side;
    }
}
