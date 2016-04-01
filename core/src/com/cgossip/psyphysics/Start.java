package com.cgossip.psyphysics;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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


        setScreen(new com.cgossip.psyphysics.states.Tmp(this));

    }

}