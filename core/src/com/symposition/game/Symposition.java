package com.symposition.game;

import Screens.LevelScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Symposition extends Game {
	public SpriteBatch batch;
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 200;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new LevelScreen(this));
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
