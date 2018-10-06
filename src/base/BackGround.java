package base;

import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class BackGround extends GameObject {
    public BackGround(){
        super();
        BufferedImage image= SpriteUtils.loadImage("assets/images/background/0.png");
        this.renderer=new SingleImageRenderer(image);
        this.position=new Vector2D(0,-(image.getHeight()-Settings.SCREEN_HEIGTH));
    }
    @Override
    public void run(){
        if(this.position.y>=0){
            return;
        }
        else {
            this.position.y += 5 /*speed*/;
        }
    }

}
