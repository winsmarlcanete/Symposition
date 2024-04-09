package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.symposition.game.Symposition;

public class MenuScreen implements Screen {


    final Symposition game;
    OrthographicCamera camera;
    private Skin skin;
    private Stage stage;
    private SpriteBatch batch;
    private boolean clickLevelScreen = false;
    private boolean clickTutorialScreen = false;
    private boolean clickOptionScreen = false;
    private boolean clickExitScreen = false;
    private Texture bgimage;
    Music music;



    public MenuScreen(final Symposition game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        stage = game.stage;
        Gdx.input.setInputProcessor(stage);
        bgimage = new Texture(Gdx.files.internal("bg.png"));
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/menu.wav"));
        music.play();
        skin = new Skin(Gdx.files.internal("rainbowui/rainbow-ui.json"));
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);


        TextButton playbtn= new TextButton("Play", skin);
        root.add(playbtn).width(350).expandX().left();

        playbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clickLevelScreen = true;
            }
        });

        root.row().space(25);

        TextButton tutorialBtn = new TextButton("Tutorial", skin);
        root.add(tutorialBtn).width(350).expandX().left();

        root.row().space(25);

        TextButton optionsbtn = new TextButton("Options", skin);
        root.add(optionsbtn).width(350).expandX().left();

        root.row().space(25);

        TextButton exitbtn = new TextButton("Exit", skin);
        root.add(exitbtn).width(350).expandX().left();

        exitbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clickExitScreen = true;
            }
        });

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(bgimage, 0, 0,800,500);
        batch.end();
        stage.act();
        stage.draw();

        if (clickLevelScreen == true) {
            game.setScreen(new LevelScreen(game));
            dispose();
        }

        if (clickExitScreen == true) {
            dispose();
            Gdx.app.exit();
            System.exit(-1);

        }

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        music.pause();
    }

    @Override
    public void resume() {
        music.play();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        music.dispose();

    }
}
