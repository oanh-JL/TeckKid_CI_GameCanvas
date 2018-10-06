package base;

import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerBullet extends GameObject implements Physics{
    Vector2D velocity;
    BoxCollider collider;
    public PlayerBullet(){
        super();
        ArrayList<BufferedImage> images= SpriteUtils.loadImages(
                "assets/images/player-bullets/a/0.png",
                "assets/images/player-bullets/a/1.png",
                "assets/images/player-bullets/a/2.png",
                "assets/images/player-bullets/a/3.png"
        );
        this.renderer=new AnimationRenderer(images);
        this.position=new Vector2D(0,0);
        this.velocity=new Vector2D(0,0);
         this.collider=new BoxCollider(24,24);

    }
    @Override
    public void run(){
        Enemy enemy=GameObject.intersect(Enemy.class,this);
        if(enemy!=null){
            enemy.destroy();
            this.destroy();
            return;
        }
        if(this.position.y<0){
            this.destroy();
            return;
        }

        this.position.addThis(velocity.x , velocity.y);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
