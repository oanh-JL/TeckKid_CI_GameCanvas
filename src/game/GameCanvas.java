package game;

import base.BackGround;
import base.Enemy;
import base.GameObject;
import base.Player;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {
    BackGround backGround;
    Player player;

    public GameCanvas(){
        this.backGround= GameObject.recycle(BackGround.class);
        this.player=GameObject.recycle(Player.class);
        Enemy enemy=GameObject.recycle(Enemy.class);

    }
    public void run(){ GameObject.runAll();}
    public void render(Graphics g){ GameObject.renderAll(g);}


    @Override
    protected  void paintComponent(Graphics g){ this.render(g);}


}
