package com.mygdx.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.helpers.AssetLoader;
import com.mygdx.objects.Android;
import com.mygdx.objects.MunecoGrande;
import com.mygdx.objects.ScrollHandler;
import com.mygdx.objects.Suelo;
import com.sun.org.apache.xpath.internal.operations.And;

/**
 * Created by caalos on 19/02/2015.
 */
public class GameRenderer {

    private GameWorld myWorld;
    private OrthographicCamera cam;

    private SpriteBatch batcher;

    private int midPointY;
    private int gameHeight;
    // Objetos
    private Android android;
    private ScrollHandler scroller;
    private Suelo suelo1, suelo2;
    private MunecoGrande mg1, mg2, mg3;
    // Assets
    private TextureRegion fondo,suelo;
    private Animation animacionNormal,animacionSalto,animacionCaida,animacionColision;
    private TextureRegion personaje,personaje2,personajeAgachado,personajeArriba;
    private TextureRegion munecoGrande;

    public GameRenderer(GameWorld gw,int midPointY,int gameHeight){
        this.myWorld = gw;
        this.gameHeight = gameHeight;
        this.midPointY = midPointY;
        // Creamos la camara y la fijamos
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, 204);
        // Inicializamos el SpriteBatch
        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

        initGameObjects();
        initAssets();
    }

    public void render(float runTime) {
        Android android = myWorld.getAndroid();

        // Inicializamos el batcher
        batcher.begin();
        // Quitamos la transparencia para el fondo
        batcher.disableBlending();
        // Pintamos el fondo
        batcher.draw(AssetLoader.fondo, 0, 0,259,194);
        // Pintamos el suelo
        pintarSuelo();
        // Ponemos transparencia
        batcher.enableBlending();
        // Aqui haremos las animaciones
        if(android.isAlive()){ // Cuando esta vivo...
            if(android.getY() == 188){ // Cuando esta en el suelo...
                batcher.draw(animacionNormal.getKeyFrame(runTime),5, 188, android.getWidth(), android.getHeight());
            }
            if(android.isFalling()){ // Cuando cae...
                batcher.draw(animacionCaida.getKeyFrame(runTime),5, android.getY(), android.getWidth(), android.getHeight());
            }
            if(android.getY() != 188 && !android.isFalling()){ // Cuando salta...
                batcher.draw(animacionSalto.getKeyFrame(runTime),5, android.getY(), android.getWidth(), android.getHeight());
            }
        }else{ // Cuando colisiona...
            batcher.draw(animacionColision.getKeyFrame(runTime),5, android.getY(), android.getWidth(), android.getHeight());
        }

        // Pintamos los munecos
        pintarMuneco();

        // Pintamos los textos
        if (myWorld.isReady()) {
            AssetLoader.shadow.draw(batcher, "Touch me", (136 / 2) - (42), 76);
            AssetLoader.font.draw(batcher, "Touch me", (136 / 2) - (42 - 1), 75);
        } else {
            if (myWorld.isGameOver() || myWorld.isHighScore()) {
                if (myWorld.isGameOver()) {
                    AssetLoader.shadow.draw(batcher, "Game Over", 25, 56);
                    AssetLoader.font.draw(batcher, "Game Over", 24, 55);

                    AssetLoader.shadow.draw(batcher, "High Score:", 23, 106);
                    AssetLoader.font.draw(batcher, "High Score:", 22, 105);

                    String highScore = AssetLoader.getHighScore() + "";

                    AssetLoader.shadow.draw(batcher, highScore, (136 / 2)
                            - (3 * highScore.length()), 128);
                    AssetLoader.font.draw(batcher, highScore, (136 / 2)
                            - (3 * highScore.length() - 1), 127);
                } else {
                    AssetLoader.shadow.draw(batcher, "High Score!", 19, 56);
                    AssetLoader.font.draw(batcher, "High Score!", 18, 55);
                }

                AssetLoader.shadow.draw(batcher, "Try again?", 23, 76);
                AssetLoader.font.draw(batcher, "Try again?", 24, 75);
            }
            String score = myWorld.getScore() + "";
            AssetLoader.shadow.draw(batcher, score,
                    (136 / 2) - (3 * score.length()), 12);
            AssetLoader.font.draw(batcher, score,
                    (136 / 2) - (3 * score.length() - 1), 11);
        }

        // Finalizamos el batcher
        batcher.end();
    }

    private void pintarSuelo(){
        batcher.draw(suelo, suelo1.getX(), 194,suelo1.getWidth(), suelo1.getHeight());
        batcher.draw(suelo,suelo2.getX(), 194,suelo2.getWidth(), suelo2.getHeight());
    }

    private void pintarMuneco(){
        batcher.draw(munecoGrande, mg1.getX(),mg1.getY(),mg1.getWidth(),mg1.getHeight());
        batcher.draw(munecoGrande, mg2.getX(),mg2.getY(),mg2.getWidth(),mg2.getHeight());
        batcher.draw(munecoGrande, mg3.getX(),mg3.getY(),mg3.getWidth(),mg3.getHeight());
    }

    private void initGameObjects() {
        android = myWorld.getAndroid();
        scroller = myWorld.getScroller();
        suelo1 = scroller.getSuelo1();
        suelo2 = scroller.getSuelo2();
        mg1 = scroller.getMg1();
        mg2 = scroller.getMg2();
        mg3 = scroller.getMg3();
    }

    private void initAssets() {
        fondo = AssetLoader.fondo;
        suelo = AssetLoader.suelo;

        personaje = AssetLoader.androidNormal;
        personaje2 = AssetLoader.androidNormal2;
        personajeAgachado = AssetLoader.androidAgachado;
        personajeArriba = AssetLoader.androidArriba;

        munecoGrande = AssetLoader.munecoGrande;

        animacionNormal = AssetLoader.animacionPrincipal;
        animacionSalto = AssetLoader.animacionSalto;
        animacionCaida = AssetLoader.animacionCaida;
        animacionColision = AssetLoader.animacionColision;
    }
}
