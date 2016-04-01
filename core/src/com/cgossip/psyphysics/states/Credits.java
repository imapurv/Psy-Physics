package com.cgossip.psyphysics.states;

/**
 * Created by Dell on 05-03-2016.
 */

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cgossip.psyphysics.handlers.GameStateManager;
import com.cgossip.psyphysics.view.Button;

/**
 * A very simple game where an image is initially hidden (transparent). The
 * image is revealed by 'scribbling' over the window with the mouse.
 *
 * Demonstrates 1) using a Pixmap to generate a texture dynamically, and 2)
 * using a texture as a mask via a custom shader.
 *
 * @author Tyler Coles
 */
public class Credits extends GameState implements InputProcessor,ApplicationListener {


    private Stage stage; //** stage holds the Button **//
    private BitmapFont font;
    private TextureAtlas buttonsAtlas; //** image of buttons **//
    private Skin buttonSkin,textSkin; //** images are used as skins of the button **//
    private TextButton button;
    TextureRegion logo,play,credit,exit;
    Table root;
    float stateTime;
    TextureRegion                   currentFrame;           // #7
    TextureRegion[]                 drawFrames;
    Texture background,wood;
    Animation drawAnimation;
    private TextureAtlas textatlas;
    private Viewport viewport;
    private OrthographicCamera camera;
    public Button buttons[];
    TextureRegion tx,ty;
    public Credits(final GameStateManager gsm) {

        super(gsm);
        background =new Texture(Gdx.files.internal("dataa/credits.png"));

        tx=new TextureRegion(background);
        tx.setRegionHeight(480);
        tx.setRegionWidth(800);
        wood=new Texture(Gdx.files.internal("dataa/roll.png"));
        ty=new TextureRegion(wood);

        ty.setRegionHeight(480);
        ty.setRegionWidth(800);
        //camera = new OrthographicCamera();
        //camera.setToOrtho(false, Game.V_WIDTH, Game.V_HEIGHT);
        // cam = new OrthographicCamera();
        // viewport = new FitViewport(Game.V_WIDTH,Game.V_HEIGHT,cam);
        // viewport.apply();
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);








    }



    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }
    Sprite ss;
    int alpha=0;
    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime();           // #15
        //  currentFrame = drawAnimation.getKeyFrame(stateTime, true);  // #16
        System.out.println(stateTime);
        sb.begin();

        // cam.update();
        // sb.setProjectionMatrix(cam.combined);
        //  ss=new Sprite(background);
        //  ss.setSize(800, 480);
        //  ss.draw(sb);
/*
            if(stateTime>5&&(1-((stateTime-3)/10))<1) {
                sb.setColor(1.0f, 1.0f, 1.0f, 1 - ((stateTime - 3) / 10));
                sb.draw(ty, 0, 0, 800, 480);
            }
            else if(stateTime>5&&1-((stateTime-3)/10)>1) {
              //  sb.setColor(1.0f, 1.0f, 1.0f, 0);
               // sb.draw(ty, 0, 0, 800, 480);
            }
            else {
                sb.setColor(1.0f, 1.0f, 1.0f, 1);
                sb.draw(ty, 0, 0, 800, 480);
            }
*/
        ss=new Sprite(background);
        ss.setSize(800, 480);
        ss.draw(sb);

        // Texture t=new Texture(ss);
        // sb.setColor(1.0f, 1.0f, 1.0f, 1);
        //  sb.draw(tx, 800, 480);



        // ss.draw(sb);



        // sb.draw(background, 0, 0);
        //   alpha+=stateTime;
        //stage.draw();
        sb.end();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void rendersb(SpriteBatch sb) {

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        background.dispose();
        wood.dispose();

    }

    @Override
    public void create() {
        cam = new OrthographicCamera();
        viewport = new FitViewport(com.cgossip.psyphysics.main.Game.V_WIDTH, com.cgossip.psyphysics.main.Game.V_HEIGHT,cam);
        viewport.apply();

    }

    @Override
    public void resize(int w, int h) {
        // viewport.update(w,h);
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK||keycode == Input.Keys.SPACE){
            // Do your optional back button handling (show pause menu?)
            gsm.setState(GameStateManager.MENU);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        /*
        for(int i=0;i<buttons.length;i++) {
            if (buttons[i].isPressed(touchPos)) {
                if (i == 0) {
                    gsm.setState(GameStateManager.PLAY);
                }

                else if (i == 1) {
                    game.startGameKidsMode();
                    game.gotoGameScreen(null);
                }
                else if (i == 2) {
                    game.showHighscores();
                }
                break;

            }
        }
*/
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}