package base;

import base.physics.Physics;
import base.renderer.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameObject {
    public static ArrayList<GameObject> gameObjects = new ArrayList<>();
    public static ArrayList<GameObject> newGameObjects = new ArrayList<>();

    public static <E extends GameObject> E create(Class<E> childClass) {
        try {
            GameObject newGameObject = childClass.newInstance();
            newGameObjects.add(newGameObject);
            return (E)newGameObject;
        }catch (Exception e) {
            return null;
        }
    }

    public static <E extends GameObject> E recycle(Class<E> childClass) {
//        for(GameObject go : gameObjects) {
        for(int i = 0; i < gameObjects.size(); i++) {
            GameObject go = gameObjects.get(i);
            if(!go.isActive && go.getClass().isAssignableFrom(childClass)) {
                go.isActive = true;
                return (E)go;
            }
        }
        return create(childClass);
    }

    public static <E extends GameObject> E intersect(Class<E> childClass, Physics physics) {
//        for(GameObject go : gameObjects) {
        for(int i = 0; i < gameObjects.size(); i++) {
            GameObject go = gameObjects.get(i);
            if(go.isActive && go.getClass().isAssignableFrom(childClass)
                && go instanceof Physics) {
                Physics physicsGo = (Physics) go;
                boolean intersected = physics.getBoxCollider().intersect(physicsGo,
                        (GameObject) physics);
                if(intersected) {
                    return (E) physicsGo;
                }
            }
        }
        return null;
    }

    public static void runAll() {
        for(int i = 0; i < gameObjects.size(); i++) {
            GameObject go = gameObjects.get(i);
//        for(GameObject go : gameObjects) {
            if(go.isActive) {
                go.run();
            }
        }
    }

    public static void renderAll(Graphics g) {
        for(int i = 0; i < gameObjects.size(); i++) {
            GameObject go = gameObjects.get(i);
//        for(GameObject go : gameObjects) {
            if(go.isActive) {
                go.render(g);
            }
        }
        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();
    }

    public Renderer renderer;
    public Vector2D position;
    public boolean isActive;

    public GameObject() {
        this.isActive = true;
    }

    public GameObject(BufferedImage image) {
        this.isActive = true;
        this.position = new Vector2D(0, 0);
    }

    public void destroy() {
        this.isActive = false;
    }

    public void run() {

    }

    public void render(Graphics g) {
        if(this.renderer != null) {
            this.renderer.render(g, this);
        }
    }
}
