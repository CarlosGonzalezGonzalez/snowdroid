package com.mygdx.objects;

/**
 * Created by caalos on 23/02/2015.
 */
public class Suelo extends Scrollable{
    public Suelo(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);

    }

    public void onRestart(float x, float scrollSpeed) {
        position.x = x;
        velocity.x = scrollSpeed;
    }
}
