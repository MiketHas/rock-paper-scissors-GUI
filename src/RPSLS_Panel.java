import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class RPSLS_Panel extends JPanel implements MouseListener {

    ImageIcon rock, paper, scissors, lizard, spock, rockDark, paperDark, scissorsDark, lizardDark, spockDark;
    static GameButton rockB, paperB, scissorsB, lizardB, spockB;
    static JLabel compR, compP, compS, compL, compSpk;
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
    static int userChoiceLizard = 0;
    static int userChoiceSpock = 0;

    public RPSLS_Panel() {
        this.setPreferredSize(new Dimension(850, 650));
        this.setLayout(null);
        this.setOpaque(false);

        // -- ImageIcons --
        rock = new ImageIcon(getClass().getClassLoader().getResource("res/gameIcons/rock.png"));
        paper = new ImageIcon(getClass().getClassLoader().getResource("res/gameIcons/paper.png"));
        scissors = new ImageIcon(getClass().getClassLoader().getResource("res/gameIcons/scissors.png"));
        lizard = new ImageIcon(getClass().getClassLoader().getResource("res/gameIcons/lizard.png"));
        spock = new ImageIcon(getClass().getClassLoader().getResource("res/gameIcons/spock.png"));

        rockDark = new ImageIcon(getClass().getClassLoader().getResource("res/gameIcons/dark/rock_dark.png"));
        paperDark = new ImageIcon(getClass().getClassLoader().getResource("res/gameIcons/dark/paper_dark.png"));
        scissorsDark = new ImageIcon(getClass().getClassLoader().getResource("res/gameIcons/dark/scissors_dark.png"));
        lizardDark = new ImageIcon(getClass().getClassLoader().getResource("res/gameIcons/dark/lizard_dark.png"));
        spockDark = new ImageIcon(getClass().getClassLoader().getResource("res/gameIcons/dark/spock_dark.png"));

        // -- Player Buttons --
        userPicks = new JPanel();
        userPicks.setBounds(50, 150, 750, 150);
        userPicks.setLayout(null);
        userPicks.setOpaque(false);

        rockB = new GameButton(rock);
        rockB.setBounds(0, 0, 150, 150);
        rockB.addMouseListener(this);

        paperB = new GameButton(paper);
        paperB.setBounds(150, 0, 150, 150);
        paperB.addMouseListener(this);

        scissorsB = new GameButton(scissors);
        scissorsB.setBounds(300, 0, 150, 150);
        scissorsB.addMouseListener(this);

        lizardB = new GameButton(lizard);
        lizardB.setBounds(450, 0, 150, 150);
        lizardB.addMouseListener(this);

        spockB = new GameButton(spock);
        spockB.setBounds(600, 0, 150, 150);
        spockB.addMouseListener(this);

        userPicks.add(rockB);
        userPicks.add(paperB);
        userPicks.add(scissorsB);
        userPicks.add(lizardB);
        userPicks.add(spockB);

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

        compL = new JLabel();
        compL.setIcon(lizard);
        compL.setBounds(0, 0, 150, 150);
        compL.setVisible(false);

        compSpk = new JLabel();
        compSpk.setIcon(spock);
        compSpk.setBounds(0, 0, 150, 150);
        compSpk.setVisible(false);

        computerPicks.add(compR);
        computerPicks.add(compP);
        computerPicks.add(compS);
        computerPicks.add(compL);
        computerPicks.add(compSpk);

        // -- Results TextPane --
        result = new JTextPane();
        result.setBounds(175, 450, 500, 100);
        result.setText("--- Rock-Paper-Scissors-Lizard-Spock ---\nGO!");
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

    public static void chosenScissors(String computerChoice) {
        switch (computerChoice) {
            case "Paper", "Lizard" -> {
                youWin();
                userChoseScissors();
            }
            case "Rock", "Spock" -> {
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
            case "Scissors", "Lizard" -> {
                youLose();
                userChosePaper();
            }
            case "Rock", "Spock" -> {
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
            case "Paper", "Spock" -> {
                youLose();
                userChoseRock();
            }
            case "Scissors", "Lizard" -> {
                youWin();
                userChoseRock();
            }
        }
    }

    public static void chosenLizard(String computerChoice) {
        switch (computerChoice) {
            case "Lizard" -> {
                draw();
                userChoseLizard();
            }
            case "Rock", "Scissors" -> {
                youLose();
                userChoseLizard();
            }
            case "Spock", "Paper" -> {
                youWin();
                userChoseLizard();
            }
        }
    }

    public static void chosenSpock(String computerChoice) {
        switch (computerChoice) {
            case "Spock" -> {
                draw();
                userChoseSpock();
            }
            case "Paper", "Lizard" -> {
                youLose();
                userChoseSpock();
            }
            case "Scissors", "Rock" -> {
                youWin();
                userChoseSpock();
            }
        }
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

    // Methods counting player's choices.
    // After player's 2 picks of the same type in a row (ex.2xRock),
    // computerChoice() will automatically pick the winning scenario and reset the counter.
    public static void userChoseRock() {
        userChoiceRock++;
        userChoicePaper=0;
        userChoiceScissors=0;
        userChoiceLizard=0;
        userChoiceSpock=0;
    }

    public static void userChosePaper() {
        userChoiceRock=0;
        userChoicePaper++;
        userChoiceScissors=0;
        userChoiceLizard=0;
        userChoiceSpock=0;
    }

    public static void userChoseScissors() {
        userChoiceRock=0;
        userChoicePaper=0;
        userChoiceScissors++;
        userChoiceLizard=0;
        userChoiceSpock=0;
    }

    public static void userChoseLizard() {
        userChoiceRock=0;
        userChoicePaper=0;
        userChoiceScissors=0;
        userChoiceLizard++;
        userChoiceSpock=0;
    }

    public static void userChoseSpock() {
        userChoiceRock=0;
        userChoicePaper=0;
        userChoiceScissors=0;
        userChoiceLizard=0;
        userChoiceSpock++;
    }

    // Detrmining computer's pick
    public static void computerChoice() {

        String[] rpsTab = {"Rock", "Paper", "Scissors", "Lizard", "Spock"};
        Random rpsSys = new Random();
        int n = rpsSys.nextInt(5);

        // Computer's choice if player makes the same choice 2x in a row.
        if (userChoiceRock == 2) {
            n = 1;
            userChoiceRock = 0;
        } else if (userChoiceScissors == 2) {
            n = 0;
            userChoiceScissors = 0;
        } else if (userChoicePaper == 2) {
            n = 2;
            userChoicePaper = 0;
        } else if (userChoiceLizard == 2) {
            n = 0;
            userChoiceLizard = 0;
        } else if (userChoiceSpock == 2) {
            n = 1;
            userChoiceSpock = 0;
        }

        computerChoice = rpsTab[n];

        switch (computerChoice) {
            case "Paper" -> compP.setVisible(true);
            case "Rock" -> compR.setVisible(true);
            case "Scissors" -> compS.setVisible(true);
            case "Lizard" -> compL.setVisible(true);
            case "Spock" -> compSpk.setVisible(true);
        }
    }

    public static void gameOver() {

        for (Component button : userPicks.getComponents() ){
            button.setEnabled(false);
        }

        String[] responses = {"YES!", "Take me to the regular version!", "No, I want to quit this game"};
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
            MainPanel.rpsGame.setVisible(true);
            MainPanel.rpslsGame.setVisible(false);
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

        result.setText("--- Rock-Paper-Scissors-Lizard-Spock ---\nGO!");

        playerPoints = 0;
        systemPoints = 0;

        userChoiceRock = 0;
        userChoicePaper = 0;
        userChoiceScissors = 0;
        userChoiceLizard=0;
        userChoiceSpock=0;
    }

    public void hideComp() {
        for (Component comp : computerPicks.getComponents() ){
            comp.setVisible(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();

        hideComp();
        computerChoice();

        // -- Round's result based on user's and computer's picks
        if (source == rockB) {
            chosenRock(computerChoice);
        } else if (source == paperB) {
            chosenPaper(computerChoice);
        } else if (source == scissorsB) {
            chosenScissors(computerChoice);
        } else if (source == lizardB) {
            chosenLizard(computerChoice);
        } else if (source == spockB) {
            chosenSpock(computerChoice);
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
        } else if (source == lizardB) {
            lizardB.setIcon(lizardDark);
        } else if (source == spockB) {
            spockB.setIcon(spockDark);
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
        } else if (source == lizardB) {
            lizardB.setIcon(lizard);
        } else if (source == spockB) {
            spockB.setIcon(spock);
        }
    }
}
