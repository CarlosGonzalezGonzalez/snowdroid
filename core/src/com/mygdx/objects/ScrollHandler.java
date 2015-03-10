package com.mygdx.objects;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.helpers.AssetLoader;
import com.mygdx.world.GameWorld;

/**
 * Created by caalos on 23/02/2015.
 */
public class ScrollHandler {
    public static final int SCROLL_SPEED = -59;
    public static final int SEPARACION = 49;

    private Suelo suelo1,suelo2;
    private MunecoGrande mg1,mg2,mg3;

    private GameWorld gameWorld;

    public ScrollHandler(GameWorld gameWorld,float yPos) {
        this.gameWorld = gameWorld;

        suelo1 = new Suelo(0, yPos, 165,11, SCROLL_SPEED);
        suelo2 = new Suelo(suelo1.getTailX(), yPos, 165,11, SCROLL_SPEED);

        mg1 = new MunecoGrande(200,178,25,25, SCROLL_SPEED,yPos);
        mg2 = new MunecoGrande(260,178,25,25,SCROLL_SPEED,yPos);
        mg3 = new MunecoGrande(320,178,25,25,SCROLL_SPEED,yPos);

        mg1.fixitposition.y=188;
        mg2.fixitposition.y=188;
        mg3.fixitposition.y=188;

        mg1.reset(200);
        mg2.reset(260);
        mg3.reset(320);

    }

    public void update(float delta) {
        suelo1.update(delta);
        suelo2.update(delta);
        mg1.update(delta);
        mg2.update(delta);
        mg3.update(delta);

        if (mg1.isScrolledLeft()) {
            mg1.reset(mg3.getTailX() + SEPARACION);
        } else if (mg2.isScrolledLeft()) {
            mg2.reset(mg1.getTailX() + SEPARACION);
        } else if (mg3.isScrolledLeft()) {
            mg3.reset(mg2.getTailX() + SEPARACION);
        }

        if (suelo1.isScrolledLeft()) {
            suelo1.reset(suelo2.getTailX());

        } else if (suelo2.isScrolledLeft()) {
            suelo2.reset(suelo1.getTailX());

        }
    }

    public void stop() {
        suelo1.stop();
        suelo2.stop();
        mg1.stop();
        mg2.stop();
        mg3.stop();
    }

    public boolean collides(Android android) {
        if (!mg1.isScored() && mg1.getX() + (mg1.getWidth() / 2) < android.getX()) {
            addScore(1);
            mg1.setScored(true);
            AssetLoader.moneda.play();
        } else if (!mg2.isScored() && mg2.getX() + (mg2.getWidth() / 2) < android.getX()) {
            addScore(1);
            mg2.setScored(true);
            AssetLoader.moneda.play();
        } else if (!mg3.isScored() && mg3.getX() + (mg3.getWidth() / 2) < android.getX()) {
            addScore(1);
            mg3.setScored(true);
            AssetLoader.moneda.play();
            }
        return (mg1.collides(android) || mg2.collides(android) || mg3.collides(android));
    }

    public void onRestart() {
        suelo1.onRestart(0, SCROLL_SPEED);
        suelo2.onRestart(suelo1.getTailX(), SCROLL_SPEED);
        mg1.onRestart(200, SCROLL_SPEED);
        mg2.onRestart(mg1.getTailX() + SEPARACION, SCROLL_SPEED);
        mg3.onRestart(mg2.getTailX() + SEPARACION, SCROLL_SPEED);
    }

    private void addScore(int increment) {
        gameWorld.addScore(increment);
    }

    public static int getScrollSpeed() {
        return SCROLL_SPEED;
    }

    public Suelo getSuelo1() {
        return suelo1;
    }

    public Suelo getSuelo2() {
        return suelo2;
    }

    public MunecoGrande getMg1() {
        return mg1;
    }

    public MunecoGrande getMg2() {
        return mg2;
    }

    public MunecoGrande getMg3() {
        return mg3;
    }
}
