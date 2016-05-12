package com.cgossip.psyphysics.levels;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cgossip.psyphysics.handlers.GameStateManager;
import com.cgossip.psyphysics.states.GameState;
import com.cgossip.psyphysics.states.Play;
import com.cgossip.psyphysics.view.Button;

/**
 * Created by HP on 25-04-2016.
 */
public class levelcleared extends GameState implements InputProcessor,ApplicationListener{

    TextureRegion back,selectl,menu,restart,next;
    Button buttons[];
    private Viewport viewport;
    private OrthographicCamera camera;
    BitmapFont font;
    String s;

    public levelcleared(GameStateManager gsm) {
        super(gsm);
        font = new BitmapFont(Gdx.files.internal("newui/skikaria.fnt"),false); //** font **//
        font.setColor(1f, 1f, 1f, 1f);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, com.cgossip.psyphysics.main.Game.V_WIDTH, com.cgossip.psyphysics.main.Game.V_HEIGHT);
        back = new TextureRegion(new Texture(Gdx.files.internal("newui/background1.png")));
        selectl = new TextureRegion(new Texture(Gdx.files.internal("newui/levelcleared.png")));
        menu = new TextureRegion(new Texture(Gdx.files.internal("newui/gomenu.png")));
        restart = new TextureRegion(new Texture(Gdx.files.internal("newui/reload.png")));
        next = new TextureRegion(new Texture(Gdx.files.internal("newui/next.png")));
        buttons = new Button[3];
        buttons[0] = new Button(menu);
        buttons[0].setPos(260,45);
        buttons[1] = new Button(restart);
        buttons[1].setPos(360, 45);
        buttons[2] = new Button(next);
        buttons[2].setPos(460, 45);
        s="Score :  "+ (102-2*GameStateManager.getScore());
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
        sb.begin();
        cam.update();
        sb.setProjectionMatrix(cam.combined);
        sb.draw(back, 0, 0);
        sb.draw(selectl, 200, 70);
        font.draw(sb,s,280,310);
        buttons[0].draw(sb);
        buttons[1].draw(sb);
        buttons[2].draw(sb);
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
        back.getTexture().dispose();
        selectl.getTexture().dispose();
        menu.getTexture().dispose();
        restart.getTexture().dispose();
        next.getTexture().dispose();
        font.dispose();
    }

    @Override
    public void create() {
        cam = new OrthographicCamera();
        viewport = new FitViewport(com.cgossip.psyphysics.main.Game.V_WIDTH, com.cgossip.psyphysics.main.Game.V_HEIGHT,cam);
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
        Vector3 touchPos = new Vector3();
        touchPos.set(screenX, screenY, 0);
        camera.unproject(touchPos);
        System.out.println("In clear level");
        System.out.println(touchPos.x + " " + touchPos.y);
        //  System.out.println(buttons[0].isPressed(touchPos));
        System.out.println(touchPos.x + " " + touchPos.y);
        if (buttons[0].isPressed(touchPos)){
            gsm.setState(GameStateManager.MENU);
        }
        if (buttons[1].isPressed(touchPos)){
            gsm.setState(GameStateManager.PLAY);
        }
        if (buttons[2].isPressed(touchPos)){
            GameStateManager.setCURLEVEL(GameStateManager.getCURLEVEL() + 1);
            gsm.setState(GameStateManager.PLAY);
        }
        return true;
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
