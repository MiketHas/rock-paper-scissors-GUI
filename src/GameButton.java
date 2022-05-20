import javax.swing.*;

public class GameButton extends JButton {

    ImageIcon icon;

    public GameButton(ImageIcon icon) {
        this.icon = icon;
        this.setIcon(icon);
        this.setContentAreaFilled(false); // button area is transparent
        this.setBorderPainted(false); // button is borderless
        this.setFocusPainted(false); // no focus after click
        this.setVisible(true);
    }

    public GameButton() {
        this.setContentAreaFilled(false); // button area is transparent
        this.setBorderPainted(false); // button is borderless
        this.setFocusPainted(false); // no focus after click
        this.setVisible(true);
    }
}
