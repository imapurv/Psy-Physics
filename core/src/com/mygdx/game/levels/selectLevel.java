package com.mygdx.game.levels;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.handlers.GameStateManager;
import com.mygdx.game.main.Game;
import com.mygdx.game.states.GameState;
import com.mygdx.game.view.Button;

/**
 * Created by HP on 30-03-2016.
 */
public class selectLevel extends GameState implements InputProcessor,ApplicationListener {

    public Button buttons[];
    private TextureRegion one,two,three,four;
    private TextureAtlas textatlas;
    private BitmapFont font;
    private Skin buttonSkin,textSkin;
    private Viewport viewport;
    private OrthographicCamera camera;
    private Texture background;

    public selectLevel(GameStateManager gsm) {
        super(gsm);

        background =new Texture(Gdx.files.internal("back.jpg"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Game.V_WIDTH, Game.V_HEIGHT);

        textatlas = new TextureAtlas("dataa/text.atlas");
        textSkin= new Skin();
        textSkin.addRegions(textatlas);
        font = new BitmapFont(Gdx.files.internal("data/arial-15.fnt"),false); //** font **//

        one=new TextureRegion(textatlas.findRegion("play"));
        two = new TextureRegion(textatlas.findRegion("credit"));
        three=new TextureRegion(textatlas.findRegion("play"));
        four = new TextureRegion(textatlas.findRegion("credit"));

        buttons = new Button [5];
        buttons[0] = new Button(one);
        buttons[0].setPos(50, 300);
        buttons[1] = new Button(two);
        buttons[1].setPos(200, 300);
        buttons[2] = new Button(three);
        buttons[2].setPos(450, 300);
        buttons[3] = new Button(four);
        buttons[3].setPos(600 , 300);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //stage.act();
        sb.begin();
        sb.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        cam.update();
        sb.setProjectionMatrix(cam.combined);

        buttons[0].draw(sb);
        buttons[1].draw(sb);
        buttons[2].draw(sb);
        buttons[3].draw(sb);
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

    }

    @Override
    public void create() {
        cam = new OrthographicCamera();
        viewport = new FitViewport(Game.V_WIDTH,Game.V_HEIGHT,cam);
        viewport.apply();
    }

    @Override
    public void resize(int w, int h) {

    }

    @Override
    public boolean keyDown(int keycode) {
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 touchPos = new Vector3();
        touchPos.set(screenX, screenY, 0);
        camera.unproject(touchPos);
        System.out.println(touchPos.x + " " + touchPos.y);
        //  System.out.println(buttons[0].isPressed(touchPos));
        System.out.println(touchPos.x + " " + touchPos.y);
        if(buttons[0].isPressed(touchPos)){
            System.out.println("Press 1");
            GameStateManager.setCURLEVEL(1);
            gsm.pushState(GameStateManager.PLAY);
        }
        if(buttons[1].isPressed(touchPos)){
            System.out.println("Press 2");
            GameStateManager.setCURLEVEL(2);
            gsm.pushState(GameStateManager.PLAY);
        }
        if(buttons[2].isPressed(touchPos)){
            System.out.println("Press 3");
            GameStateManager.setCURLEVEL(3);
            gsm.pushState(GameStateManager.PLAY);
        }
        if(buttons[3].isPressed(touchPos)){
            System.out.println("Press 4");
            GameStateManager.setCURLEVEL(4);
            gsm.pushState(GameStateManager.PLAY);
        }

        return true;
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
