public class GameController {
    private final int BOARD_SIZE = 3; //with another move solution in Npc this can be changed
    private Npc wizard;
    private Npc fighter;

    public GameController() {
        this.wizard = new Npc('V', 6);
        this.fighter = new Npc('H', 6);
    }

    public GameController(char npc1, char npc2) {
        this.wizard = new Npc(npc1, 6);
        this.fighter = new Npc(npc2, 6);
    }

    public void setInitialPositions() {
        wizard.setPosition(2);
        fighter.setPosition(0);
    }

    public String printBoard(){
        StringBuilder board = new StringBuilder();
        for(int i = 0; i < BOARD_SIZE; i++) {
            if(wizard.getPosition() == i && wizard.getPosition() == fighter.getPosition()) {
                board.append('X');
            } else if (wizard.getPosition() == i) {
                board.append(wizard.print());
            } else if (fighter.getPosition() == i) {
                board.append(fighter.print());
            } else {
                board.append('_');
            }
        }
        return board.toString();
    }

    public String getGameStatus() {
        String status = printBoard() +
                " --> " +
                fighter.printHealth() +
                ", " +
                wizard.printHealth();
        return status;
    }

    public void printGame() {
        System.out.println(getGameStatus());
    }
}
