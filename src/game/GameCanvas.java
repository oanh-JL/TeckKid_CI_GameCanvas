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
        this.backGround= GameObject.create(BackGround.class);
        this.player=GameObject.create(Player.class);
        Enemy enemy=GameObject.create(Enemy.class);

    }
    public void run(){ GameObject.runAll();}
    public void render(Graphics g){ GameObject.renderAll(g);}


    @Override
    protected  void paintComponent(Graphics g){ this.render(g);}


}
