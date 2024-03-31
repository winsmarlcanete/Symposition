package Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.GL20;
import com.symposition.game.Symposition;

import jdk.tools.jmod.Main;

import java.util.logging.Level;


public class MainMenuScreen extends Game {

    private Skin skin;
    private Stage stage;

    public SpriteBatch batch;
    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 200;
    public static final float PPM = 100; //pixels per meter
    public float dt;

    LevelScreen ls;
    Symposition symposition;
    @Override
    public void create() {
        batch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal("rainbowui/rainbow-ui.json"));

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table rootdummy = new Table();
        rootdummy.setFillParent(true);
        stage.addActor(rootdummy);

        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        TextButton playbtn= new TextButton("Play", skin);
        root.add(playbtn).width(350).expandX().left();
        playbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                symposition = new Symposition();
                symposition.render();
                symposition.create();



            }
        });

        root.row().space(25);


        TextButton tutorialBtn = new TextButton("Tutorial", skin);
        root.add(tutorialBtn).width(350).expandX().left();

        tutorialBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Tutorial clicked!");

            }
        });

        root.row().space(25);


        TextButton optionsbtn = new TextButton("Options", skin);
        root.add(optionsbtn).width(350).expandX().left();

        root.row().space(25);

        TextButton exitbtn = new TextButton("Exit", skin);
        root.add(exitbtn).width(350).expandX().left();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(.9f, .9f, .9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

    }

    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();


    }
}
