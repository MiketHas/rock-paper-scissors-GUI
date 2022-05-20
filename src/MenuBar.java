import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class MenuBar extends JMenuBar implements ActionListener {

    JMenu mProgram, mOptions, mInfo;
    JMenuItem iMainMenu, iExit, iAboutMe, iAboutRPS;
    JCheckBoxMenuItem cbMusic;
    ImageIcon imgMain, imgExit;
    Clip clip;

    URL musicURL;

    public MenuBar() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        imgMain = new ImageIcon(getClass().getClassLoader().getResource("res/images/imgMain.png"));
        imgExit = new ImageIcon(getClass().getClassLoader().getResource("res/images/imgExit.png"));

        musicURL = getClass().getResource("res/music/lotus.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicURL);
        clip = AudioSystem.getClip();
        clip.open(audioStream);

        mProgram = new JMenu("Program");
        this.add(mProgram);

            iMainMenu = new JMenuItem("Main Menu");
            iMainMenu.setIcon(imgMain);
            iMainMenu.setAccelerator(KeyStroke.getKeyStroke("ctrl M"));
            iMainMenu.addActionListener(this);
            mProgram.add(iMainMenu);

            mProgram.addSeparator();

            iExit = new JMenuItem("Exit");
            iExit.setIcon(imgExit);
            iExit.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
            iExit.addActionListener(this);
            mProgram.add(iExit);

        mOptions = new JMenu("Options");
        this.add(mOptions);

            cbMusic = new JCheckBoxMenuItem("Background music");
            cbMusic.addActionListener(this);
            mOptions.add(cbMusic);

        mInfo = new JMenu("Info");
        this.add(mInfo);

        iAboutMe = new JMenuItem("The creator");
        iAboutMe.addActionListener(this);
        mInfo.add(iAboutMe);
        iAboutRPS = new JMenuItem("The game");
        iAboutRPS.addActionListener(this);
        mInfo.add(iAboutRPS);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == iMainMenu) {
            RPS_Panel.newGame();
            MainPanel.rpsGame.setVisible(false);
            RPSLS_Panel.newGame();
            MainPanel.rpslsGame.setVisible(false);
            MainPanel.textPane.setVisible(true);
            MainPanel.choicePanel.setVisible(true);
        } else if (source == iExit) {
            System.exit(0);
        } else if (source == cbMusic) {
            if (cbMusic.isSelected()) {
                clip.start();
            } else {
                clip.stop();
                clip.setMicrosecondPosition(0);
            }
        } else if (source == iAboutMe) {
            JOptionPane.showMessageDialog(this, "I never thought that at some point in my life I'd learn to code.\nThen again, I never thought I'd have anything to do with pharmaceutical industry,\nyet here I am, a \"Medical Consultant\" coding my Rock-Paper-Scissors game.\nComing up next! A space-ship simulator!", "About Me", JOptionPane.INFORMATION_MESSAGE);
        } else if (source == iAboutRPS) {
            JOptionPane.showMessageDialog(this, "I made RPS entirely by myself. First off started as an output game, then I made the GUI for it." +
                    "\nHad to adjust it slightly. Sure, I had some problems but I figured it out." +
                    "\nTook me 2 days to make it, gotta admit I'm proud of it :)" +
                    "\nMusic: Lotus Turbo Challenge 2 Theme...it's a 1991 game released for Amiga." +
                    "\nYes, Amiga. I was there Gandalf. I was there 3000 years ago...");
        }
    }
}
