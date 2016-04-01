package com.cgossip.psyphysics.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Psy-Physics";
		config.width = com.cgossip.psyphysics.main.Game.V_WIDTH;
		config.height = com.cgossip.psyphysics.main.Game.V_HEIGHT;

		/*
		config.width = 1024;
		config.height = 732;
		*/

		new LwjglApplication(new com.cgossip.psyphysics.main.Game(), config);
	}
}
