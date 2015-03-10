package com.mygdx.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.helpers.AssetLoader;
import com.mygdx.world.GameWorld;

/**
 * Created by caalos on 19/02/2015.
 */
public class Android {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private int width;
    private int height;

    private Rectangle rectangle;

    private boolean isAlive;
    private boolean jumping;

    public Android(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;

        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);

        rectangle = new Rectangle(getX(),getY(),getWidth()-2,getHeight());
        isAlive = true;

        jumping = false;
    }

    public void update(float delta) {
        velocity.add(acceleration.cpy().scl(delta));
        position.add(velocity.cpy().scl(delta));


        if (position.y >= 188) {
            position.y = 188;
        }
        if(position.y == 188){
            jumping = false;
        }
        rectangle.setPosition(this.getX(),this.getY());
    }

    public void onRestart(int y){
        position.y = y;
        velocity.x = 0;
        velocity.y = 0;
        acceleration.x = 0;
        acceleration.y = 460;
        isAlive = true;
        jumping = false;
    }

    public void die() {
        isAlive = false;
        velocity.y = 0;
    }

    public boolean isAlive(){
        return isAlive;
    }

    public void onClick() {
        if(!jumping){
            if (isAlive) {
                velocity.y = -185;
                AssetLoader.jump.play();
                jumping = true;
            }
        }
    }

    public boolean isFalling() {
        return velocity.y > 110;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

}
