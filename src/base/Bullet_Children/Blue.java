package base.Bullet_Children;

import base.GameObject;
import base.Vector2D;
import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class Blue extends GameObject {
        public Blue(){
            BufferedImage image= SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png");
            this.renderer=new SingleImageRenderer(image);
            this.position=new Vector2D(0,0);
        }
        @Override
        public void run(){
            this.position.addThis(-5,-4);
        }

    }


