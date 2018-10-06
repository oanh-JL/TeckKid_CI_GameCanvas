package base;

import base.renderer.SingleImageRenderer;
import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class EnemyBullet extends GameObject implements Physics {
    Vector2D velocity;
    BoxCollider collider;
    public static int dammage=100;
    public EnemyBullet() {
        BufferedImage image = SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png");
        this.position=new Vector2D(0,0);
        this.renderer=new SingleImageRenderer(image);
        this.velocity=new Vector2D(0,0);
        this.collider=new BoxCollider(16,16);

    }
   @Override
   public void run(){
        Player player=GameObject.intersect(Player.class,this);
        if(player!=null){
            this.destroy();

            if(player.Blood>0){
                player.Blood-=this.dammage;
                return;
            }
            else {
                player.destroy();
            }
            return;
        }
//        if(this.position.y<0){
//            this.destroy();
//            return;
//        }
        this.position.addThis(velocity.x,velocity.y);


   }

    @Override
    public BoxCollider getBoxCollider() {
        return this.collider;
    }
}
