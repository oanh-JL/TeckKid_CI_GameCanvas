package base.enemy;

import base.GameObject;
import base.Vector2D;
import base.action.*;
import base.physics.BoxCollider;
import base.physics.Physics;
import base.renderer.AnimationRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy extends GameObject implements Physics {
    BoxCollider collider;
    Action action;

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

        this.defineAction();
    }

    void defineAction() {
        ActionWait actionWait = new ActionWait(20);
        Action actionFire = new Action() {
            @Override
            public void run(GameObject master) {
                fire();
                this.isDone = true;
            }

            @Override
            public void reset() {
                this.isDone = false;
            }
        };
        Action actionLeft=new Action() {
            @Override
            public void run(GameObject master) {
                if(position.x>9) {
                    position.addThis(-9, 5);

                }
                this.isDone=true;
            }

            @Override
            public void reset() {
                this.isDone=false;

            }
        };
        Action actionRight=new Action() {
            @Override
            public void run(GameObject master) {
                if(position.x<10)
                position.addThis(9,0);
                this.isDone=true;
            }

            @Override
            public void reset() {
                this.isDone=false;

            }
        };

        ActionParallel actionParallel=new ActionParallel(actionFire,actionLeft);
        ActionSequence actionSequence=new ActionSequence(actionWait,actionParallel);
        ActionRepeat actionRepeat = new ActionRepeat(actionSequence);

        ActionParallel actionParallel1=new ActionParallel(actionFire,actionRight);
        ActionSequence actionSequence1=new ActionSequence(actionWait,actionParallel1);
        ActionRepeat actionRepeat1 = new ActionRepeat(actionSequence1);

        ActionSequence actionSequence2=new ActionSequence(actionRepeat,actionRepeat1);

        this.action = actionSequence2;
    }

    @Override
    public void run() {
        this.action.run(this);
    }

    public void fire() {
        EnemyBullet bullet = GameObject.recycle(EnemyBullet.class);
        bullet.position.set(this.position.x, this.position.y + 5);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
