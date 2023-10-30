package game;

import city.cs.engine.UserView;
import game.enemy.Bastion;
import game.levels.Level1;
import game.levels.Level2;
import game.levels.Level3;
import game.levels.Level4;
import game.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//The "GameView" class extends "UserView" to provide a view for the user in a game.
public class GameView extends UserView implements ActionListener {
    private Player player;
    private GameLevel level;
    private final Image background1;
    private final Image background2 = new ImageIcon("data/dessert.png").getImage();
    private final Image background3 = new ImageIcon("data/darkBack.jpg").getImage();
    private final Image background4 = new ImageIcon("data/Level4.png").getImage();
    private final Image ammoFore;
    private final Image healthFore;
    private final Image hacking = new ImageIcon("data/hacking2.gif").getImage();
    private final Image teleportationGif = new ImageIcon("data/teleportation.gif").getImage();
    public boolean levelChanged = false;
    private int counter;
    public int timeCounter;
    public Timer timer;
    private Game game;
    public GameView(GameLevel level, int width, int height){
        super (level, width, height);
        this.level = level;
        game = level.game;
        background1 = new ImageIcon("data/garageDoor.jpg").getImage();
        ammoFore = new ImageIcon("data/ammo.gif").getImage();
        healthFore = new ImageIcon("data/health.png").getImage();
        player =level.getPlayer();
        timer = new Timer(1000,this);
    }
    @Override
    protected void paintBackground(Graphics2D g){
        if(level instanceof Level1) {
            g.drawImage(background1, 0, 0, 800, 600, this);
        }
        if(level instanceof Level2) {
            g.drawImage(background2, 0, 0, 800, 600, this);
        }
        if(level instanceof Level3) {
            g.drawImage(background3,0,0,800,600,this);
        }
        if(level instanceof Level4){
            g.drawImage(background4,0,0, 800, 600, this);
        }
    }
    @Override
    protected void paintForeground(Graphics2D g){
        g.drawImage(ammoFore,10,20,70,70,this);
        g.drawImage(healthFore,110,30,80,50,this);
        //we convert the  health counts and coin counts to string to show it on foreground
        String aCount=Integer.toString(player.getAmmo());
        String hCount=Integer.toString(player.getHealth());

        //we set a new font
        g.setFont(new Font("Times New Roman",Font.PLAIN,40));
        g.setColor(Color.WHITE);

        if(player.getAmmo()>0) {
            //we draw it at a specific position which is in pixels(Left/Top)
            g.drawString(aCount, 85, 70);
        }else if(player.getAmmo()<=0){
            player.setAmmo(0);
            g.drawString(aCount, 85, 70);
        }
        g.drawString(hCount,175,70);

        g.setFont(new Font("Calibri",Font.BOLD,25));
        g.setColor(Color.RED);
        g.drawString("Timer: "+timeCounter,650,50);

        if (level instanceof Level4 && level.isCompleted()){
            String checkpoint = "Winner !";
            g.setFont(new Font("Ariel",Font.BOLD,170));
            g.setColor(Color.ORANGE);
            g.drawString(checkpoint,70,350);
            timer.stop();
            getWorld().stop();
        }

        if(player.getHealth()==0 || level.playerDead){
            String lost = "Loser !";
            g.setFont(new Font("Ariel",Font.BOLD,170));
            g.setColor(Color.RED);
            g.drawString(lost,100,350);
            getWorld().stop();
        }

        counter++;
        if(player.hack && level instanceof Level1){
            g.drawImage(hacking, 0, 0, 800, 600, this);
            if(counter%240==0) {
                player.hack = false;
                Level1.wall.destroy();
            }
        }

        if(levelChanged){
            g.drawImage(teleportationGif,0,0,800,600,this);
            if(counter%240==0){
                levelChanged=false;
            }
        }
    }
    public void setThisWorld(GameLevel gameLevel, GameLevel previousGame){
        this.level = gameLevel;
        player = gameLevel.getPlayer();
        player.setAmmo(previousGame.getPlayer().getAmmo());
        player.setHealth(previousGame.getPlayer().getHealth());
        player.setCheckPointCount(previousGame.getPlayer().getCheckPointCount());
    }
    public void setWorld(GameLevel world){
        super.setWorld(world);
        this.level=world;
        this.player=world.getPlayer();
        Player.genjiStar=false;
        Bastion.dead=0;
        Level2.shorterPlatformsState=false;
        player.heroChangedToGenji=false;
    }
    public Game getGame() {
        return game;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        timeCounter++;
    }
}
