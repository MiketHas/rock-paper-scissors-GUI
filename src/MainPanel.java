import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainPanel extends JPanel implements ActionListener, MouseListener {

    final int PANEL_WIDTH = 850;
    final int PANEL_HEIGHT = 650;

    static JTextPane textPane;

    static JPanel choicePanel;
    ImageIcon rpsIconSmall, rpsIconDarkSmall, spockIconSmall, spockIconDarkSmall, questionIcon;
    GameButton questionButton, rpsPick, spockPick;
    static RPS_Panel rpsGame;
    static RPSLS_Panel rpslsGame;

    ImageIcon background;
    Image bcgImage;

    public MainPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setLayout(null);
        background = new ImageIcon(getClass().getClassLoader().getResource("res/images/retroWallpaper.png"));
        bcgImage = background.getImage();

        textPane = new JTextPane();
        textPane.setBounds(250, 50, 350, 100);
        textPane.setText("--- Rock-Paper-Scissors ---\nYou know the rules!\nFirst one to get 6 points wins!\nPick your game and go!");
        textPane.setFont(new Font("Consolas", Font.PLAIN, 16));
        textPane.setForeground(Color.WHITE);
        textPane.setEditable(false);
        textPane.setOpaque(false);

        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        // -- Images --
        ImageIcon rpsIcon = new ImageIcon(getClass().getClassLoader().getResource("res/images/rps.png"));
        rpsIconSmall = new ImageIcon(rpsIcon.getImage().getScaledInstance(270,270,100));
        ImageIcon rpsIconDark = new ImageIcon(getClass().getClassLoader().getResource("res/images/rps_dark.png"));
        rpsIconDarkSmall = new ImageIcon(rpsIconDark.getImage().getScaledInstance(270,270,100));

        ImageIcon spockIcon = new ImageIcon(getClass().getClassLoader().getResource("res/images/lizardSpock.png"));
        spockIconSmall = new ImageIcon(spockIcon.getImage().getScaledInstance(350, 320, 100));
        ImageIcon spockIconDark = new ImageIcon(getClass().getClassLoader().getResource("res/images/lizardSpock_dark.png"));
        spockIconDarkSmall = new ImageIcon(spockIconDark.getImage().getScaledInstance(350, 320, 100));

        questionIcon = new ImageIcon(getClass().getClassLoader().getResource("res/images/question.png"));
        ImageIcon questionSmall = new ImageIcon(questionIcon.getImage().getScaledInstance(20, 20, 100));

        // -- CHOOSING A GAME MODE --
        choicePanel = new JPanel();
        choicePanel.setBounds(120, 150, 625, 300);
        choicePanel.setLayout(null);
        choicePanel.setOpaque(false);

        rpsPick = new GameButton(rpsIconSmall);
        rpsPick.setBounds(0, 0, 270, 270);
        rpsPick.addMouseListener(this);
        rpsPick.setOpaque(false);
        choicePanel.add(rpsPick);

        spockPick = new GameButton(spockIconSmall);
        spockPick.setBounds(270, 0, 320, 270);
        spockPick.addMouseListener(this);
        spockPick.setOpaque(false);
        choicePanel.add(spockPick);

        questionButton = new GameButton(questionSmall);
        questionButton.setBounds(574, 20, 60, 50);
        questionButton.addActionListener(this);
        choicePanel.add(questionButton);

        // -- Game Modes --
        rpsGame = new RPS_Panel();
        rpsGame.setBounds(0, 0, 850, 650);
        rpsGame.setVisible(false);

        rpslsGame = new RPSLS_Panel();
        rpslsGame.setBounds(0, 0, 850, 650);
        rpslsGame.setVisible(false);

        this.add(rpslsGame);
        this.add(rpsGame);
        this.add(textPane);
        this.add(choicePanel);
        this.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) { // background Image
        super.paintComponent(g);
        g.drawImage(bcgImage, 0,0, this.getWidth(), this.getHeight(), null);
    }

    @Override
    public void actionPerformed(ActionEvent e) { // Question Mark action
        Object source = e.getSource();
        if (source==questionButton) {
            JOptionPane.showMessageDialog(this, "This is Rock-Paper-Scissor-Lizard-Spock.\nIt's really simple!\nTo basic R/P/S rules all you gotta remember is that:\nLizard poisons Spock,\nSpock vaporizes Rock,\nRock crushes Lizard,\nLizard eats Paper,\nPaper disproves Spock,\nSpock smashes Scissors,\n...aaand finally Scissors decapitate Lizard.", "That the hell is this?!", JOptionPane.QUESTION_MESSAGE);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object source = e.getComponent();
        if (source == rpsPick) {
            textPane.setVisible(false);
            choicePanel.setVisible(false);
            rpsGame.setVisible(true);
        } else if (source == spockPick) {
            textPane.setVisible(false);
            choicePanel.setVisible(false);
            rpslsGame.setVisible(true);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object source = e.getComponent();
        if (source == rpsPick) {
            rpsPick.setIcon(rpsIconDarkSmall);
        } else if (source == spockPick) {
            spockPick.setIcon(spockIconDarkSmall);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object source = e.getComponent();
        if (source == rpsPick) {
            rpsPick.setIcon(rpsIconSmall);
        } else if (source == spockPick) {
            spockPick.setIcon(spockIconSmall);
        }
    }
}
