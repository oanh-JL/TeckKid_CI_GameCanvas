package base;


import base.counter.FrameCounter;
import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends GameObject implements Physics {
    FrameCounter fireCounter;
    BoxCollider collider;
    int Blood=2000;
    public Player() {
        super();
        ArrayList<BufferedImage> images = SpriteUtils.loadImages(
                "assets/images/players/straight/0.png",
                "assets/images/players/straight/1.png",
                "assets/images/players/straight/2.png",
                "assets/images/players/straight/3.png",
                "assets/images/players/straight/4.png",
                "assets/images/players/straight/5.png",
                "assets/images/players/straight/6.png"
        );
        this.renderer = new AnimationRenderer(images);
        this.position = new Vector2D(Settings.START_PLAYER_POSITION_X, Settings.START_PLAYER_POSITION_Y);
        this.collider=new BoxCollider(32,48);
         this.fireCounter = new FrameCounter(10);
    }
        @Override
         public void run() {
            if (KeyEventPress.isUpPress) {
                if(KeyEventPress.isLeftPress)
                 this.move(-1, -1);
                if(KeyEventPress.isRightPress)
                    this.move(1, -1);
            }
            if (KeyEventPress.isDownPress) {
                if(KeyEventPress.isLeftPress)
                    this.move(-1, 1);
                if(KeyEventPress.isRightPress)
                    this.move(1, 1);
            }
            if(KeyEventPress.isRightPress) {
                this.move(1,0);
            }
            if(KeyEventPress.isLeftPress) {
                this.move(-1,0);
            }
            boolean fireCounterRun=this.fireCounter.run();
            if( KeyEventPress.isFirePress &&fireCounterRun) {
                this.fire();
            }
        }


        public void fire() {
            PlayerBullet bullet1=GameObject.create(PlayerBullet.class);
            PlayerBullet bullet2=GameObject.create(PlayerBullet.class);
            PlayerBullet bullet3=GameObject.create(PlayerBullet.class);

            bullet1.velocity.set(0,-1);
            bullet2.velocity.set(1,-1);
            bullet3.velocity.set(-1,-1);

            bullet1.position.set(this.position.x,this.position.y);
            bullet2.position.set(this.position.x,this.position.y);
            bullet3.position.set(this.position.x,this.position.y);

            this.fireCounter.reset();

    }
        public void move(int translateX,int translateY){
            this.position.addThis(translateX,translateY);

         }


    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
