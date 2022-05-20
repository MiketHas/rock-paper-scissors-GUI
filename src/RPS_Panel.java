import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class RPS_Panel extends JPanel implements MouseListener {

    ImageIcon rock, paper, scissors, rockDark, paperDark, scissorsDark;
    static GameButton rockB, paperB, scissorsB;
    static JLabel compR, compP, compS;
    static JTextPane result;
    static JPanel userPicks, computerPicks;

    static String computerChoice;

    // Starting points
    static int playerPoints = 0;
    static int systemPoints = 0;

    // Counting how many times each of these were picked by the player. Zeroes after two picks in a row.
    static int userChoiceRock = 0;
    static int userChoicePaper = 0;
    static int userChoiceScissors = 0;

    public RPS_Panel() {
        this.setPreferredSize(new Dimension(850, 650));
        this.setLayout(null);
        this.setOpaque(false);

        // -- ImageIcons --
        rock = new ImageIcon(getClass().getClassLoader().getResource("res/gameIcons/rock.png"));
        paper = new ImageIcon(getClass().getClassLoader().getResource("res/gameIcons/paper.png"));
        scissors = new ImageIcon(getClass().getClassLoader().getResource("res/gameIcons/scissors.png"));

        rockDark = new ImageIcon(getClass().getClassLoader().getResource("res/gameIcons/dark/rock_dark.png"));
        paperDark = new ImageIcon(getClass().getClassLoader().getResource("res/gameIcons/dark/paper_dark.png"));
        scissorsDark = new ImageIcon(getClass().getClassLoader().getResource("res/gameIcons/dark/scissors_dark.png"));

        // -- Player Buttons --
        userPicks = new JPanel();
        userPicks.setBounds(190, 150, 460, 150);
        userPicks.setLayout(null);
        userPicks.setOpaque(false);

        rockB = new GameButton(rock);
        rockB.setBounds(0, 0, 150, 150);
        rockB.addMouseListener(this);

        paperB = new GameButton(paper);
        paperB.setBounds(160, 0, 150, 150);
        paperB.addMouseListener(this);

        scissorsB = new GameButton(scissors);
        scissorsB.setBounds(310, 0, 150, 150);
        scissorsB.addMouseListener(this);

        userPicks.add(rockB);
        userPicks.add(paperB);
        userPicks.add(scissorsB);

        // -- Computer Picks Images --
        computerPicks = new JPanel();
        computerPicks.setBounds(350, 300, 150, 150);
        computerPicks.setLayout(null);
        computerPicks.setOpaque(false);

        compR = new JLabel();
        compR.setIcon(rock);
        compR.setBounds(0, 0, 150, 150);
        compR.setVisible(false);

        compP = new JLabel();
        compP.setIcon(paper);
        compP.setBounds(0, 0, 150, 150);
        compP.setVisible(false);

        compS = new JLabel();
        compS.setIcon(scissors);
        compS.setBounds(0, 0, 150, 150);
        compS.setVisible(false);

        computerPicks.add(compR);
        computerPicks.add(compP);
        computerPicks.add(compS);

        // -- Results TextPane --
        result = new JTextPane();
        result.setBounds(250, 450, 350, 100);
        result.setText("--- Rock-Paper-Scissors ---\nGO!");
        result.setFont(new Font("Consolas", Font.PLAIN, 20));
        result.setForeground(Color.WHITE);
        result.setEditable(false);
        result.setOpaque(false);

        StyledDocument doc = result.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        this.add(result);
        this.add(computerPicks);
        this.add(userPicks);
    }

    // Round ending results:
    public static void youWin() {
        playerPoints++;
        result.setText("You win! " + playerPoints + ":" + systemPoints);
        if (playerPoints == 6) {
            result.setText("You won the game! " + playerPoints + ":" + systemPoints+"\nGAME OVER!");
            gameOver();
        }
    }

    public static void youLose() {
        systemPoints++;
        result.setText("You lose! " + playerPoints + ":" + systemPoints);
        if (systemPoints == 6) {
            result.setText("You lost the game! " + playerPoints + ":" + systemPoints+"\nGAME OVER!");
            gameOver();
        }
    }

    public static void draw() {
        result.setText("Draw! Nobody gets a point! " + playerPoints + ":" + systemPoints);
    }

    public static void chosenScissors(String computerChoice) {
        switch (computerChoice) {
            case "Paper" -> {
                youWin();
                userChoseScissors();
            }
            case "Rock" -> {
                youLose();
                userChoseScissors();
            }
            case "Scissors" -> {
                draw();
                userChoseScissors();
            }
        }
    }

    public static void chosenPaper(String computerChoice) {
        switch (computerChoice) {
            case "Scissors" -> {
                youLose();
                userChosePaper();
            }
            case "Rock" -> {
                youWin();
                userChosePaper();
            }
            case "Paper" -> {
                draw();
                userChosePaper();
            }
        }
    }

    public static void chosenRock(String computerChoice) {
        switch (computerChoice) {
            case "Rock" -> {
                draw();
                userChoseRock();
            }
            case "Paper" -> {
                youLose();
                userChoseRock();
            }
            case "Scissors" -> {
                youWin();
                userChoseRock();
            }
        }
    }

    // Methods counting player's choices.
    // After 2 picks of the same type in a row (ex.2xRock), Computer will automatically pick the winning scenario.
    public static void userChoseRock() {
        userChoiceRock++;
        userChoicePaper=0;
        userChoiceScissors=0;
    }

    public static void userChosePaper() {
        userChoicePaper++;
        userChoiceRock=0;
        userChoiceScissors=0;
    }

    public static void userChoseScissors() {
        userChoiceScissors++;
        userChoiceRock=0;
        userChoicePaper=0;
    }

    // Detrmining computer's pick
    public void computerChoice() {

        String[] rpsTab = {"Rock", "Paper", "Scissors"};
        Random rpsSys = new Random();
        int n = rpsSys.nextInt(3);

        if (userChoiceRock == 2) {
            n = 1;
            userChoiceRock = 0;
        } else if (userChoiceScissors == 2) {
            n = 0;
            userChoiceScissors = 0;
        } else if (userChoicePaper == 2) {
            n = 2;
            userChoicePaper = 0;
        }

        computerChoice = rpsTab[n];

        switch (computerChoice) {
            case "Paper" -> compP.setVisible(true);
            case "Rock" -> compR.setVisible(true);
            case "Scissors" -> compS.setVisible(true);
        }
    }

    public static void gameOver() {

        for (Component button : userPicks.getComponents() ){
            button.setEnabled(false);
        }

        String[] responses = {"YES!", "Take me to the Lizard-Spock version!", "No, I want to quit this game"};
        int answer = JOptionPane.showOptionDialog(
                null,
                "Would you care for a rematch?",
                "Again?",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                responses,
                0);

        if (answer == 0) {
            newGame();
        } else if (answer == 1) {
            newGame();
            MainPanel.rpsGame.setVisible(false);
            MainPanel.rpslsGame.setVisible(true);
        } else if (answer == 2) {
            System.exit(0);
        }
    }

    public static void newGame() {
        for (Component button : userPicks.getComponents() ){
            button.setEnabled(true);
        }

        for (Component comp : computerPicks.getComponents() ){
            comp.setVisible(false);
        }

        result.setText("--- Rock-Paper-Scissors ---\nGO!");

        playerPoints = 0;
        systemPoints = 0;

        userChoiceRock = 0;
        userChoicePaper = 0;
        userChoiceScissors = 0;
    }

    public void hideComp() {
        for (Component comp : computerPicks.getComponents() ){
            comp.setVisible(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getComponent();
        hideComp();
        computerChoice();

        // -- Round's result based on user's and computer's picks
        if (source == rockB) {
            chosenRock(computerChoice);
        } else if (source == paperB) {
            chosenPaper(computerChoice);
        } else if (source == scissorsB) {
            chosenScissors(computerChoice);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object source = e.getComponent();
        if (source == rockB) {
            rockB.setIcon(rockDark);
        } else if (source == paperB) {
            paperB.setIcon(paperDark);
        } else if (source == scissorsB) {
            scissorsB.setIcon(scissorsDark);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object source = e.getComponent();
        if (source == rockB) {
            rockB.setIcon(rock);
        } else if (source == paperB) {
            paperB.setIcon(paper);
        } else if (source == scissorsB) {
            scissorsB.setIcon(scissors);
        }
    }
}
