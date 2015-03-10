package com.mygdx.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by caalos on 19/02/2015.
 */
public class AssetLoader {

    public static Texture texture;
    public static TextureRegion fondo;
    public static TextureRegion suelo;
    public static TextureRegion munecoGrande;
    // Utilizamos TextureRegion para hacer las animaciones
    public static TextureRegion androidNormal,androidNormal2;
    public static TextureRegion androidAgachado,androidArriba;
    // Animaciones
    public static Animation animacionPrincipal;
    public static Animation animacionSalto;
    public static Animation animacionCaida;
    public static Animation animacionColision;
    // Sonidos
    public static Sound dead,jump,moneda;
    // Fuente
    public static BitmapFont font, shadow;
    // Preferences
    public static Preferences prefs;

    public static void load(){
        // Fondo
        texture = new Texture(Gdx.files.internal("imagenes/fondo.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        fondo = new TextureRegion(texture,0,0,259,194);
        fondo.flip(false,true);

        //Suelo
        texture = new Texture(Gdx.files.internal("imagenes/suelo.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        suelo = new TextureRegion(texture,0,0,165,11);
        suelo.flip(false,true);

        // Personaje normal
        texture = new Texture(Gdx.files.internal("imagenes/personaje.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        androidNormal = new TextureRegion(texture,0,0,25,25);
        androidNormal.flip(false,true);

        // Personaje normal2
        texture = new Texture(Gdx.files.internal("imagenes/personaje2.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        androidNormal2 = new TextureRegion(texture,0,0,25,25);
        androidNormal2.flip(false,true);

        // Personaje agachado
        texture = new Texture(Gdx.files.internal("imagenes/agachado.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        androidAgachado = new TextureRegion(texture,0,0,25,25);
        androidAgachado.flip(false,true);

        // Personaje arriba
        texture = new Texture(Gdx.files.internal("imagenes/arriba.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        androidArriba = new TextureRegion(texture,0,0,25,25);
        androidArriba.flip(false,true);

        // Muneco de nieve
        texture = new Texture(Gdx.files.internal("imagenes/munecoGrande.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        munecoGrande = new TextureRegion(texture,0,0,25,25);
        munecoGrande.flip(false,true);

        // Animacion principal
        TextureRegion[] personajeMoviendose = {androidNormal,androidNormal2};
        animacionPrincipal = new Animation(0.5f,personajeMoviendose);
        animacionPrincipal.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        // Animacion salto
        TextureRegion[] personajeSalto = {androidAgachado,androidArriba};
        animacionSalto = new Animation(0.5f,personajeSalto);
        animacionSalto.setPlayMode(Animation.PlayMode.NORMAL);

        //Animacion caida
        TextureRegion[] personajeCaida = {androidAgachado,androidNormal};
        animacionCaida = new Animation(0.5f,personajeCaida);
        animacionCaida.setPlayMode(Animation.PlayMode.NORMAL);

        // Animacion colision
        TextureRegion[] furioso = {androidAgachado,androidArriba};
        animacionColision = new Animation(0.1f,furioso);
        animacionColision.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        // Sonidos
        dead = Gdx.audio.newSound(Gdx.files.internal("sonidos/dead.wav"));
        jump = Gdx.audio.newSound(Gdx.files.internal("sonidos/salto.wav"));
        moneda = Gdx.audio.newSound(Gdx.files.internal("sonidos/coin.wav"));

        // Fuente
        font = new BitmapFont(Gdx.files.internal("fuentes/text.fnt"));
        font.setScale(.25f, -.25f);
        shadow = new BitmapFont(Gdx.files.internal("fuentes/shadow.fnt"));
        shadow.setScale(.25f, -.25f);

        // HighScore
        prefs = Gdx.app.getPreferences("ZombieBird");
        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }
    }

    public static void dispose() {
        texture.dispose();
        font.dispose();
        shadow.dispose();
        dead.dispose();
        jump.dispose();
    }

    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }
}
