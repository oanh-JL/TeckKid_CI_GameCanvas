package base;

import base.renderer.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameObject {
    public static ArrayList<GameObject> gameObjects=new ArrayList<>();
    public static ArrayList<GameObject> newGameObjects=new ArrayList<>();
    public static <E extends GameObject> E create (Class<E> childClass){
        try{
            GameObject newGameObject=childClass.newInstance();
            newGameObjects.add(newGameObject);
            return (E)newGameObject;
        }
        catch (Exception e){
            return  null;
        }
    }
    public static void runAll(){
        for(GameObject go:gameObjects){
            go.run();
        }
    }
    public static void renderAll(Graphics g){
      for(GameObject go:gameObjects){
          go.render(g);
      }
      gameObjects.addAll(newGameObjects);
      newGameObjects.clear();
    }

    public Renderer renderer;
    public Vector2D position;

    public GameObject(){}

     public GameObject(BufferedImage image){
        this.position=new Vector2D(0,0);
     }
     public void run(){

     }
     public void render(Graphics g){
        if(this.renderer!=null){
            this.renderer.render(g,this);
        }
     }

}
