package base;

import base.counter.FrameCounter;
import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends GameObject implements Physics {

    BoxCollider collider;
    FrameCounter fireCount;

    public Enemy() {
        super();
        ArrayList<BufferedImage> images = SpriteUtils.loadImages(
                "assets/images/enemies/level0/pink/0.png",
                "assets/images/enemies/level0/pink/1.png",
                "assets/images/enemies/level0/pink/2.png",
                "assets/images/enemies/level0/pink/3.png"
        );
        this.renderer = new AnimationRenderer(images);
        this.position = new Vector2D(200, 100);
        this.collider = new BoxCollider(28, 28);
        this.fireCount = new FrameCounter(20);
        this.fire();

    }

    public void fire() {
        EnemyBullet enemyBullet = GameObject.create(EnemyBullet.class);
        EnemyBullet enemyBullet2 = GameObject.create(EnemyBullet.class);
        EnemyBullet enemyBullet3 = GameObject.create(EnemyBullet.class);

        enemyBullet.velocity = new Vector2D(0, 1);
        enemyBullet2.velocity = new Vector2D(-1, 1);
        enemyBullet3.velocity = new Vector2D(1, 1);

        enemyBullet.position.set(this.position.x, this.position.y);
        enemyBullet2.position.set(this.position.x, this.position.y);
        enemyBullet3.position.set(this.position.x, this.position.y);
        this.fireCount.reset();

    }
    @Override
    public void run(){

        boolean fireCountRun=this.fireCount.run();
        if(fireCountRun){
            this.fire();
        }

    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}

