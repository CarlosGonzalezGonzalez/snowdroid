package com.mygdx.objects;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;



/**
 * Created by caalos on 23/02/2015.
 */
public class MunecoGrande extends Scrollable{

    private int random;
    private Rectangle rectangleBot;
    private Rectangle rectangleMid;
    private Rectangle rectangleTop;
    private float groundY;
    private boolean isScored;

    public MunecoGrande(float x, float y, int width, int height, float scrollSpeed,float groundY) {
        super(200, y, width, height, scrollSpeed);
        rectangleBot = new Rectangle();
        rectangleMid = new Rectangle();
        rectangleTop = new Rectangle();
        isScored = false;
        this.groundY = groundY;
    }

    @Override
    public void update(float delta){
        super.update(delta);

        //rectangle.set(this.getX(),this.getY(),this.getWidth(),this.getHeight());
        if(height == 25){ // Si es grande...
            rectangleBot.set(this.getX()+3,this.getY()+13,19,12);
            rectangleMid.set(this.getX()+5,this.getY()+5,13,8);
            rectangleTop.set(this.getX()+9,this.getY(),7,5);
        }else{ // Si es pequeño...
            rectangleBot.set(this.getX()+2,this.getY()+9,11,7);
            rectangleMid.set(this.getX()+3,this.getY()+3,9,6);
            rectangleTop.set(this.getX()+5,this.getY(),5,3);
        }

    }

    @Override
    public void reset(float newX) {
        random  = (int)((Math.random()*100000000)%10);
        if(random <= 5){
            height = 25;
            position.y =  fixitposition.y-10;
        }else{
            height = 15;
            position.y =  fixitposition.y;
        }

        width = height;
        isScored = false;
        super.reset(newX);
    }

    public boolean collides(Android android){
        if(position.x < android.getX() + android.getWidth()){
            return (Intersector.overlaps(android.getRectangle(),rectangleBot) ||
            Intersector.overlaps(android.getRectangle(),rectangleMid) ||
            Intersector.overlaps(android.getRectangle(),rectangleTop));
        }
        return false;
    }

    public void onRestart(float x, float scrollSpeed) {
        velocity.x = scrollSpeed;
        reset(x);
    }

    public boolean isScored() {
        return isScored;
    }
    public void setScored(boolean b) {
        isScored = b;
    }

    public Rectangle getRectangleBot() {
        return rectangleBot;
    }

    public Rectangle getRectangleMid() {
        return rectangleMid;
    }

    public Rectangle getRectangleTop() {
        return rectangleTop;
    }

    /*public Rectangle getRectangle() {
        return rectangle;
    }*/


}
