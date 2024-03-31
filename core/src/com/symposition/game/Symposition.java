package com.symposition.game;

import Screens.LevelScreen;
import Screens.MainMenuScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import jdk.tools.jmod.Main;

public class Symposition extends Game {
	public SpriteBatch batch;
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 200;
	public static final float PPM = 100; //pixels per meter
	MainMenuScreen mainmenu = new MainMenuScreen();
	private Skin skin;
	private Stage stage;
	Symposition symposition;
	MainMenuScreen ms;

	
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
