package base;

import base.Bullet_Children.*;
import base.counter.FrameCounter;
import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends GameObject {
    FrameCounter fireCounter;
    public Player() {
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
        PlayerBullet bullet=GameObject.create(PlayerBullet.class);
        bullet.position.set(this.position.x,this.position.y);

            Blue bull1=GameObject.create(Blue.class);
            bull1.position.set(this.position.x,this.position.y);

            Green bull2=GameObject.create(Green.class);
            bull2.position.set(this.position.x,this.position.y);

            Cyan bull3=GameObject.create(Cyan.class);
            bull3.position.set(this.position.x,this.position.y);

            Pink bull4=GameObject.create(Pink.class);
            bull4.position.set(this.position.x,this.position.y);

            Red bull5=GameObject.create(Red.class);
            bull5.position.set(this.position.x,this.position.y);

            White bull6=GameObject.create(White.class);
            bull6.position.set(this.position.x,this.position.y);

        this.fireCounter.reset();

    }
        public void move(int translateX,int translateY){
            this.position.addThis(translateX,translateY);

         }

}
