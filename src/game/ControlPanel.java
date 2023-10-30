package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel {
    private JPanel mainPanel;
    private JButton restartGameButton;
    private JButton exitButton;
    private JButton resumeButton;
    private JButton pauseButton;
    private JButton hintsButton;
    private GameView view;

    public ControlPanel(GameView v) {
        view =v;
        restartGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getGame().restartTheGame();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.timer.stop();
                view.getWorld().stop();
                view.getGame().currentLevel.getGameMusic().pause();
            }
        });
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.timer.start();
                view.getWorld().start();
                view.getGame().currentLevel.getGameMusic().resume();
            }
        });
        hintsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.timer.stop();
                view.getWorld().stop();
                view.getGame().currentLevel.getGameMusic().pause();
                JOptionPane.showMessageDialog(view.getGame().getFrame(),
                            "* HINTS LEVEL1 *\n" +
                            "- You have the ability to change your character by pressing 1/2/3\n" +
                            "   1 as Genji / 2 as Sombra / 3 as Cassidy \n" +
                            "- You can only change to Genji if you have collected one STAR \n" +
                            "- You can roll with 'R' when you are Genji \n" +
                            "- Sombra can hack the purple wall... by pressing H \n" +
                            "- You know how to play, right? Left=A, Right=D, Jump=W, Shoot=SPACE \n"+
                            "*  HINTS LEVEL2 *\n" +
                            "- Click 'E' on red platform to activate the stairs. \n" +
                            "- remember to collect all items... \n"+
                            "*  HINTS LEVEL3 *\n" +
                            "- Can you see the turtles? KILL THEM BEFORE THEY KILL YOU !\n"+
                            "   they are 'bombers', and they will blow you up !\n"+
                            "*  HINTS LEVEL4 *\n" +
                            "- Welcome to boss fight ! \n" +
                            "- This is why you needed to have good amount of health and ammo ! \n"
                ,"HINTS",1);
                if(JOptionPane.OK_OPTION ==0){
                    view.timer.start();
                    view.getWorld().start();
                    view.getGame().currentLevel.getGameMusic().resume();
                }
            }
        });
    }
    public JPanel getMainPanel(){
        return mainPanel;
    }

}
