package game;

import city.cs.engine.UserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuControlPanel extends UserView {
    private JButton STARTButton;
    private JPanel mainPanel;
    private JFrame frame;
    private final Image background;
    private JButton InstructionsButton;
    GameLevel level;
    public MenuControlPanel(GameLevel w, int width, int height,JFrame f) {
        super(w, width, height);
        level=w;
        frame=f;
        background = new ImageIcon("data/lobby.jpg").getImage();
        STARTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level.game.goToNextLevel();
                frame.dispose();
            }
        });
        InstructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInstructionsTab();
            }
        });
    }
    private void showInstructionsTab() {
        JOptionPane.showMessageDialog(frame,
                " Welcome to Overwatch !\n" +
                "1. There are 4 levels.\n" +
                "2. For going to next levels, all enemies have to die.\n" +
                "3. Collect all ammos and healthpacks, you'll need them for boss fight...! \n" +
                "4. Good Luck !\n","Instructions",1);
    }
    @Override
    protected void paintBackground(Graphics2D g){
        g.drawImage(background,0,0,800,600,this);
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
