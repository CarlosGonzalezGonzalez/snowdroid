package com.mygdx.world;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.helpers.AssetLoader;
import com.mygdx.objects.Android;
import com.mygdx.objects.ScrollHandler;

/**
 * Created by caalos on 19/02/2015.
 */
public class GameWorld {

    private Android android;
    private ScrollHandler sh;

    private int androidY;
    private int score;

    private GameState currentState;

    public GameWorld(int midPointY){
        android = new Android(5, 188, 17, 12);
        sh = new ScrollHandler(this,midPointY + 66);
        androidY = 188;
        score = 0;
        currentState = GameState.READY;
    }

    public enum GameState {

        READY, RUNNING, GAMEOVER,HIGHSCORE

    }

    public void update(float delta) {
        switch (currentState) {
            case READY:
                break;
            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;
        }

    }

    public void updateRunning(float delta) {
        android.update(delta);
        sh.update(delta);
        // Cuando android colisiona
        if(sh.collides(android)){
            sh.stop();
            AssetLoader.dead.play();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            AssetLoader.dead.dispose();
            android.die();
            if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }
            currentState = GameState.GAMEOVER;
        }
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        android.onRestart(androidY);
        sh.onRestart();
    }

    public Android getAndroid(){
        return android;
    }
    public ScrollHandler getScroller() {
        return sh;
    }

    public int getScore() {
        return score;
    }
    public void addScore(int increment) {
        score += increment;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }
    public boolean isReady() {
        return currentState == GameState.READY;
    }
    public void start() {
        currentState = GameState.RUNNING;
    }
    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }
}
