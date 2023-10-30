package game;

import game.levels.Level1;
import game.levels.Level2;
import game.levels.Level3;
import game.levels.Level4;
import game.levels.Menu;
import game.player.PlayerController;
import game.player.PlayerTracker;

import javax.swing.JFrame;
import java.awt.*;

/**
 * Your main game entry point
 */
public class Game {
    GameLevel currentLevel;
    private GameView view;
    private JFrame frame;
    PlayerController controller;
    ControlPanel menu;


    /**
     * Initialise a new Game.
     */
    public Game() {

        currentLevel = new game.levels.Menu(this);
        currentLevel.start();
        currentLevel.playMusic();
    }
    public GameView getView() {
        return view;
    }
    public JFrame getFrame(){
        return frame;
    }

    /**
     * Run the game.
     */
    public static void main(String[] args) {

        new Game();
    }
    public void goToNextLevel() {
        if (currentLevel instanceof Menu) {
            currentLevel.stopMusic();
            currentLevel.stop();
            GameLevel prevLevel = currentLevel;

            currentLevel = new Level1(this);
            currentLevel.playMusic();

            controller = new PlayerController(currentLevel.getPlayer());
            controller.updatePlayer(currentLevel.getPlayer());

            view = new GameView(currentLevel, 800, 600);
            view.setWorld(currentLevel);
            view.setThisWorld(currentLevel, prevLevel);
            view.setZoom(7);
            view.addKeyListener(controller);
            view.addMouseListener(controller);
            view.addMouseListener(new GiveFocus(view));
            currentLevel.addStepListener(new PlayerTracker(view, currentLevel.getPlayer(), currentLevel));

            frame = new JFrame("Overwatch");
            menu = new ControlPanel(view);
            frame.add(view, BorderLayout.CENTER);
            frame.add(menu.getMainPanel(), BorderLayout.NORTH);
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
            view.timer.start();
            currentLevel.start();
        } else if (currentLevel instanceof Level1) {
            currentLevel.stopMusic();
            currentLevel.stop();
            GameLevel prevLevel = currentLevel;
            currentLevel = new Level2(this);
            currentLevel.playMusic();
            controller.updatePlayer(currentLevel.getPlayer());
            view.setThisWorld(currentLevel, prevLevel);
            view.setWorld(currentLevel);
            view.setZoom(8);
            currentLevel.addStepListener(new PlayerTracker(view, currentLevel.getPlayer(), currentLevel));
            currentLevel.start();
        } else if (currentLevel instanceof Level2) {
            currentLevel.stopMusic();
            currentLevel.stop();
            GameLevel prevLevel = currentLevel;
            currentLevel = new Level3(this);
            currentLevel.playMusic();
            currentLevel.getPlayer().heroChangedToGenji = false;
            controller.updatePlayer(currentLevel.getPlayer());
            view.setThisWorld(currentLevel, prevLevel);
            view.setWorld(currentLevel);
            view.setZoom(8);
            currentLevel.addStepListener(new PlayerTracker(view, currentLevel.getPlayer(), currentLevel));
            currentLevel.start();
        } else if (currentLevel instanceof Level3) {
            currentLevel.stopMusic();
            currentLevel.stop();
            GameLevel prevLevel = currentLevel;
            currentLevel = new Level4(this);
            currentLevel.playMusic();
            currentLevel.getPlayer().heroChangedToGenji = false;
            controller.updatePlayer(currentLevel.getPlayer());
            view.setThisWorld(currentLevel, prevLevel);
            view.setWorld(currentLevel);
            view.setZoom(8);
            currentLevel.addStepListener(new PlayerTracker(view, currentLevel.getPlayer(), currentLevel));
            currentLevel.start();
        } else if (currentLevel instanceof Level4) {
            System.exit(0);
        }
    }

    public void restartTheGame() {
        currentLevel.stopMusic();
        currentLevel.stop();
        currentLevel = new Level1(this);
        currentLevel.playMusic();
        controller = new PlayerController(currentLevel.getPlayer());
        controller.updatePlayer(currentLevel.getPlayer());
        view = new GameView(currentLevel, 800, 600);
        view.setWorld(currentLevel);
        view.setZoom(7);

        view.addKeyListener(controller);
        view.addMouseListener(controller);
        view.addMouseListener(new GiveFocus(view));

        currentLevel.addStepListener(new PlayerTracker(view, currentLevel.getPlayer(), currentLevel));
        frame.dispose();
        frame = new JFrame("Overwatch");
        menu = new ControlPanel(view);
        frame.add(view, BorderLayout.CENTER);
        frame.add(menu.getMainPanel(), BorderLayout.NORTH);
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
        view.timer.start();
        currentLevel.start();
    }
}
