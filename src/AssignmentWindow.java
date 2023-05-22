import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AssignmentWindow extends JFrame {
    private final int LINE_HEIGHT = 13;
    private JButton nextCycleButton;
    private JLabel boardLabel;
    private JLabel npc1Label;
    private JLabel npc2Label;
    private JLabel fightLabel;
    private JLabel winnerLabel;
    private final GameController gameController;
    private Timer timer;

    public static void main(String[] args) {
        AssignmentWindow assignmentWindow = new AssignmentWindow();
    }

    public AssignmentWindow(){
        super();
        this.getContentPane().setPreferredSize(new Dimension(300, 150));
        this.pack();

        gameController = new GameController();
        gameController.start();
        init_components();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
    }

    private void init_components() {
        boardLabel = new JLabel(gameController.getBoard());
        boardLabel.setBounds(136, 30, 25,LINE_HEIGHT);
        this.add(boardLabel);

        npc1Label = new JLabel(gameController.getHitPoints().split(",")[0]);
        npc1Label.setBounds(100, 50, 18,LINE_HEIGHT);
        this.add(npc1Label);

        npc2Label = new JLabel(gameController.getHitPoints().split(",")[1]);
        npc2Label.setBounds(182, 50, 18, LINE_HEIGHT);
        this.add(npc2Label);

        fightLabel = new JLabel("Harc!");
        fightLabel.setBounds(135, 50, 30, LINE_HEIGHT);
        fightLabel.setVisible(false);
        this.add(fightLabel);

        winnerLabel = new JLabel();
        winnerLabel.setBounds(100, 120, 200, LINE_HEIGHT);
        winnerLabel.setVisible(false);
        this.add(winnerLabel);

        nextCycleButton = new JButton("Next Turn");
        nextCycleButton.setBounds(100, 75, 100, 35);
        nextCycleButton.addActionListener(this::nextCycleButtonListener);
        this.add(nextCycleButton);

        timer = new Timer(500, event -> {
            updateLabels();
        });
    }

    private void nextCycleButtonListener(ActionEvent e) {
        synchronized (gameController) {
            gameController.notify();
        }
        timer.start();
    }

    private void updateLabels() {
        boardLabel.setText(gameController.getBoard());
        npc1Label.setText(gameController.getHitPoints().split(",")[0]);
        npc2Label.setText(gameController.getHitPoints().split(",")[1]);
        fightLabel.setVisible(gameController.isBattle());
        if(gameController.isFinished()) {
            winnerLabel.setText(gameController.getWinner());
            winnerLabel.setBounds(100, 120, 150, LINE_HEIGHT);
            winnerLabel.setVisible(true);
        }
    }

}
