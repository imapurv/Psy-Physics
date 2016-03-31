package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.Tmp;

/**
 * Created by Dell on 31-03-2016.
 */
public class Start extends Game {
    public OrthographicCamera camera;
    public SpriteBatch batch;
    public BitmapFont font;



    @Override
    public void create() {

        camera=new OrthographicCamera(800,480);
        batch=new SpriteBatch();


        setScreen(new Tmp(this));

    }

}