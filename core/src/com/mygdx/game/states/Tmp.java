package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.Start;
import com.mygdx.game.actors.MyActor;

/**
 * Created by Dell on 31-03-2016.
 */
public class Tmp implements Screen {
    Start game;
    private Stage stage; //** stage holds the Button **//
    private BitmapFont font;
    private TextureAtlas buttonsAtlas; //** image of buttons **//
    private Skin buttonSkin; //** images are used as skins of the button **//
    private TextButton button;


    public Tmp(Start game)
    {
        this.game=game;

    }

    @Override
    public void show() {


        buttonsAtlas = new TextureAtlas("dataa/button.pack"); //**button atlas image **//
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonsAtlas); //** skins for on and off **//
        font = new BitmapFont(Gdx.files.internal("data/arial-15.fnt"),false); //** font **//

        stage = new Stage();        //** window is stage **//
        stage.clear();
        Gdx.input.setInputProcessor(stage); //** stage is responsive **//

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(); //** Button properties **//
        style.up = buttonSkin.getDrawable("buttonOff");
        style.down = buttonSkin.getDrawable("buttonOn");

        style.font = font;

        button = new TextButton("START", style);
        //** Button text and style **//
        button.setHeight(Gdx.graphics.getHeight()/3); //** Button Height **//
        button.setWidth(Gdx.graphics.getWidth()/4); //** Button Width **//

        button.setPosition(10,10);

        button.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed"); //** Usually used to start Game, etc. **//


                // TODO Auto-generated method stub



                return true;

            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Rggggggeleased");

                ///and level


                dispose();

            }
        });


        MyActor my=new MyActor();
        AlphaAction ap= Actions.fadeIn(500);

        MoveToAction moveAction = new MoveToAction();//Add dynamic movement effects to button
        moveAction.setPosition(Gdx.graphics.getWidth() / 2 - button.getWidth() / 2, Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 6);
        moveAction.setDuration(3f);

        RotateToAction rotateAction = new RotateToAction();
        rotateAction.setRotation(90f);
        rotateAction.setDuration(5f);

        button.addAction(rotateAction);
        MoveToAction moveActions = new MoveToAction();
        moveActions.setPosition(10, 350);
        moveActions.setDuration(.2f);


        my.addAction(rotateAction);
      //  my.addAction(Actions.rotateBy(50,5f));
        //stage.addActor(my);
       stage.addActor(button);

    }
    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        game.batch.begin();
        stage.draw();
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

}