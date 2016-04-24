package com.cgossip.psyphysics.handlers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
	
	private com.cgossip.psyphysics.main.Game game;
	
	private Stack<com.cgossip.psyphysics.states.GameState> gameStates;
	
	public static final int PLAY = 912837;
	public static final int MENU = 912830;
	public static final int CREATE = 912831;
	public static final int SELECTLEVEL = 975234;
	public static final int CREDITS = 912832;
	public static int CURLEVEL = 1;

	public static int getTOTAlLEVEL() {
		return TOTAlLEVEL;
	}

	public static void setTOTAlLEVEL(int TOTAlLEVEL) {
		GameStateManager.TOTAlLEVEL = TOTAlLEVEL;
	}

	public static int TOTAlLEVEL;
	
	public GameStateManager(com.cgossip.psyphysics.main.Game game) {
		this.game = game;
		gameStates = new Stack<com.cgossip.psyphysics.states.GameState>();

		//pushState(PLAY);
		pushState(MENU);
		//pushState(CREATE);
		//pushState(SELECTLEVEL);
	}
	
	public com.cgossip.psyphysics.main.Game game() { return game; }
	
	public void update(float dt) {
		gameStates.peek().update(dt);
	}
	
	public void render() {
		gameStates.peek().render();
	}

	public void rendersb(SpriteBatch sb) {
		gameStates.peek().rendersb(sb);
	}

	public static int getCURLEVEL() {
		return CURLEVEL;
	}

	public static void setCURLEVEL(int CURLEVEL) {
		GameStateManager.CURLEVEL = CURLEVEL;
	}

	private com.cgossip.psyphysics.states.GameState getState(int state) {
		if(state == PLAY) return new com.cgossip.psyphysics.states.Play(this);
		if(state == MENU) return new com.cgossip.psyphysics.states.MainMenu(this);
		if(state == CREATE) return new com.cgossip.psyphysics.states.Create(this);
		if(state==CREDITS) return new com.cgossip.psyphysics.states.Credits(this);
		if (state == SELECTLEVEL) return new com.cgossip.psyphysics.levels.selectLevel(this);
		return null;
	}
	
	public void setState(int state) {
		popState();
		pushState(state);
	}
	
	public void pushState(int state) {
		gameStates.push(getState(state));
	}
	
	public void popState() {
		com.cgossip.psyphysics.states.GameState g = gameStates.pop();
		g.dispose();
	}
	public void resize(int w,int h){
		gameStates.peek().resize(w,h);
	}
	
}















