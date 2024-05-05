package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.symposition.game.Symposition;

public class MenuScreen implements Screen {


    final Symposition game;
    OrthographicCamera camera;
    private final Skin skin;
    private final Skin skin2;
    private final Skin skin3;
    private final Skin skin4;
    private final Stage stage;
    private final SpriteBatch batch;
    private boolean clickLevelScreen = false;

    private boolean clickExitScreen = false;
    private final Texture bgimage;
    Music music;
    private final Sound sfx1;






    public MenuScreen(final Symposition game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
//        stage = game.stage;
        stage = new Stage(new FillViewport(Symposition.V_WIDTH, Symposition.V_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        bgimage = new Texture(Gdx.files.internal("bgImages/bg.png"));
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/bgmusic/menu.wav"));
        music.play();
        skin = new Skin(Gdx.files.internal("rainbowui/rainbow-ui.json"));
        skin2 = new Skin(Gdx.files.internal("menu/play.json"));
        skin3 = new Skin(Gdx.files.internal("menu/chapter.json"));
        skin4 = new Skin(Gdx.files.internal("menu/exit.json"));
        sfx1 = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/sfx8.wav"));

        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        root.padRight(150);
        root.padTop(0);

        Texture texture = new Texture(Gdx.files.internal("menu/Title.png"));
        Image title = new Image(texture);
        root.add(title).width(130).height(20).padLeft(4);
        root.row().padTop(70);

        ImageButton play = new ImageButton(skin2);
        root.add(play).width(100).height(15).left();
//        TextButton tutorialBtn = new TextButton("Tutorial", skin);
//        root.add(tutorialBtn).width(350).expandX().left();

        root.row().space(5);

        ImageButton chapter = new ImageButton(skin3);
//        root.add(chapter).width(100).height(15).left();
//        TextButton tutorialBtn = new TextButton("Tutorial", skin);
//        root.add(tutorialBtn).width(350).expandX().left();

        root.row().space(5);

        ImageButton exit = new ImageButton(skin4);
        root.add(exit).width(100).height(15).left();
//        TextButton optionsbtn = new TextButton("Options", skin);
//        root.add(optionsbtn).width(350).expandX().left();

//        root.row().space(25);

//        TextButton exitbtn = new TextButton("Exit", skin);
//        root.add(exitbtn).width(350).expandX().left();

        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                sfx1.play();
                clickLevelScreen = true;
                music.dispose();
            }
        });

        chapter.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                sfx1.play();

            }
        });

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clickExitScreen = true;
                music.dispose();
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

        if (clickLevelScreen) {
            game.setScreen(new LevelScreen(game));
            music.stop();
            music.dispose();

        }

        if (clickExitScreen) {
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
        skin.dispose();
        stage.dispose();

    }
}
