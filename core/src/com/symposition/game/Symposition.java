package com.symposition.game;

import Screens.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Symposition extends Game {
	public SpriteBatch batch;
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 200;
	public static final float PPM = 100; //pixels per meter
	public Stage stage;
	public int levelTimer;

	@Override
	public void create () {
		batch = new SpriteBatch();


		stage = new Stage(new ScreenViewport());
		setScreen(new FurElise8(this));

	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();

	}


}
