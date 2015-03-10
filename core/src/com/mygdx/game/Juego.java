package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.helpers.AssetLoader;
import com.mygdx.screen.GameScreen;

/**
 * Created by caalos on 19/02/2015.
 */
public class Juego extends Game{
    @Override
    public void create() {
        Gdx.app.log("Juego", "created");
        AssetLoader.load();
        // Creamos la pantalla del juego
        setScreen(new GameScreen());
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}