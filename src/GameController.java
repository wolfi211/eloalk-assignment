public class GameController extends Thread{
    private final Npc wizard;
    private final Npc fighter;
    private String board;

    public GameController() {
        this.wizard = new Npc('V', 6,2);
        this.fighter = new Npc('H', 6,0);
        updateBoard();
    }

    @Override
    public void run(){
        printGame();
        try {
            synchronized (this) {
                wait();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while(!isFinished()) {
            makeTurn();
            printGame();
            if(isFinished()) {
                printWinner();
            }
            try {
                synchronized (this) {
                    wait();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void makeTurn() {
        wizard.move();
        fighter.move();
        updateBoard();
        if(isBattle()) {
            battle();
        }
    }

    private void updateBoard(){
        StringBuilder board = new StringBuilder();
        int BOARD_SIZE = 3;
        for(int i = 0; i < BOARD_SIZE; i++) {
            if(wizard.getPosition() == i && wizard.compareTo(fighter) == 0) {
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

    public String getBoard() {
        return board;
    }

    public String getGameStatus() {
        String status = board +
                " --> ";
        if(isBattle()) {
            status += "harc: ";
        }
        status += getHitPoints();
        return status;
    }

    public String getHitPoints(){
        return fighter.printHealth() +
                "," +
                wizard.printHealth();
    }

    public boolean isBattle(){
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

    public boolean isFinished() {
        return wizard.getHitPoints() <= 0 || fighter.getHitPoints() <= 0;
    }

    public String getWinner() {
        if(wizard.getHitPoints() > 0) {
            return "A Varázsló nyert!";
        } else if(fighter.getHitPoints() > 0) {
            return "A Harcos nyert!";
        } else {
            return "Mind a két fél halott!";
        }
    }

    private void printWinner() {
        System.out.println(getWinner());
    }

    public void printGame() {
        System.out.println(getGameStatus());
    }
}
