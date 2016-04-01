package com.cgossip.psyphysics.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cgossip.psyphysics.handlers.GameStateManager;

public abstract class GameState {
	
	protected GameStateManager gsm;
	protected com.cgossip.psyphysics.main.Game game;

	protected SpriteBatch sb;
	protected OrthographicCamera cam;
	protected OrthographicCamera hudCam;
	
	protected GameState(GameStateManager gsm) {
		this.gsm = gsm;
		game = gsm.game();
		sb = game.getSpriteBatch();
		cam = game.getCamera();
		hudCam = game.getHUDCamera();
	}
	
	public abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render();
	public abstract void rendersb(SpriteBatch sb);
	public abstract void dispose();
	public abstract void resize(int w,int h);
	
}









