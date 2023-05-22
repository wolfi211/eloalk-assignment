public class GameController {
    private final int BOARD_SIZE = 3; //with another move solution in Npc this can be changed
    private final Npc wizard;
    private final Npc fighter;
    private String board;

    public GameController() {
        this.wizard = new Npc('V', 6);
        this.fighter = new Npc('H', 6);
        updateBoard();
    }

    public void makeTurn() {
        wizard.move();
        fighter.move();
        updateBoard();

        if(isBattle()) {
            battle();
        }
    }

    public void gameLoop() {
        printGame();
        while(!isFinished()) {
            makeTurn();
            printGame();
        }
        printWinner();
    }

    public void setInitialPositions() {
        wizard.setPosition(2);
        fighter.setPosition(0);
    }

    private void updateBoard(){
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
        this.board = board.toString();
    }

    public String getGameStatus() {
        String status = board +
                " --> ";
        if(isBattle()) {
            status += "harc: ";
        }
        status += fighter.printHealth() +
                ", " +
                wizard.printHealth();
        return status;
    }

    private boolean isBattle(){
        for (char field: board.toCharArray()) {
            if(field == 'X') {
                return true;
            }
        }
        return false;
    }

    private void battle() {
        wizard.takeDamage(fighter.giveDamage());
        fighter.takeDamage(wizard.giveDamage());
    }

    private boolean isFinished() {
        return wizard.getHitPoints() <= 0 || fighter.getHitPoints() <= 0;
    }

    private String declareWinner() {
        if(wizard.getHitPoints() > 0) {
            return "A Varázsló nyert!";
        } else if(fighter.getHitPoints() > 0) {
            return "A Harcos nyert!";
        } else {
            return "Mind a két fél halott!";
        }
    }

    private void printWinner() {
        System.out.println(declareWinner());
    }

    public void printGame() {
        System.out.println(getGameStatus());
    }
}
