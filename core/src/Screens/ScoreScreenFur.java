package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.symposition.game.Symposition;

public class ScoreScreenFur implements Screen {
    final Symposition game;
    OrthographicCamera camera;
    private Stage stage;
    private final Texture bg;
    private TextButton continueButton;
    private Label titleLabel;
    private final Table root;
    private final Table musicalnote;
    private final Table details;
    private final Skin skin;
    private final Skin fontskin;


    private Label timeCompletion;
    private ImageButton next;
    private Skin skin2;
    private boolean exit = false;
    private final Sound sfx1;
    Music music;

    public ScoreScreenFur(final Symposition game){
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        bg = new Texture(Gdx.files.internal("bgImages/Studio_Score_4.png"));
        skin = new Skin(Gdx.files.internal("rainbowui/rainbow-ui.json"));
        fontskin = new Skin(Gdx.files.internal("menu/label/regular.json"));

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/levelmusic/furv4.wav"));
        music.play();


        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        root = new Table();
        musicalnote = new Table();
        details = new Table();
        root.setFillParent(true);
        stage.addActor(root);



        root.add(musicalnote).expandX();
        root.add(details).expandX();



        timeCompletion = new Label(String.format("%03d", game.levelTimer), fontskin);
        timeCompletion.setFontScale(.8F);

        details.add(timeCompletion).padTop(150);

        skin2 = new Skin(Gdx.files.internal("menu/exit.json"));

        next = new ImageButton(skin2);
        details.row();

        details.add(next).spaceTop(250).padLeft(300);

        sfx1 = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/sfx8.wav"));

        next.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                sfx1.play();
                exit = true;
                music.stop();
            }
        });










//        Table ui = new Table();
//        root.row();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        Gdx.gl.glClearColor(.9f, .9f, .9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(bg, 0, 0,800,500);

        game.batch.end();

        stage.act();
        stage.draw();

        if(exit){
            game.setScreen(new LevelScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
        bg.dispose();
    }
}






