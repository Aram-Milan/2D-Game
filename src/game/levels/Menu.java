package game.levels;

import game.Game;
import game.GameLevel;
import game.MenuControlPanel;

import javax.swing.*;
import java.awt.*;

public class Menu extends GameLevel {

    MenuControlPanel menu;
    JFrame frame;
    public Menu(Game g) {
        super(g);

        getGround().destroy();
        getPlayer().destroy();
        frame = new JFrame("Lobby");
        menu = new MenuControlPanel(this,800,600,frame);
        frame.add(menu, BorderLayout.SOUTH);
        frame.add(menu.getMainPanel(), BorderLayout.CENTER);

        // Enable the frame to quit the application when the x button is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the frame location by platform
        frame.setLocationByPlatform(true);
        // Do not let the frame be resizable
        frame.setResizable(true);
        // Size the frame to fit the world view
        frame.pack();
        // Make the frame visible
        frame.setVisible(true);

        // make a debugging view
        //JFrame debugView = new DebugViewer(world, 800, 600);
    }


    @Override
    public boolean isCompleted() {
        return false;
    }
}
