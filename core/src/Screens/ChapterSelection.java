package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.symposition.game.Symposition;

public class ChapterSelection implements Screen {
    final Symposition game;
    public SpriteBatch batch;

    private Stage stage;
    private Table table;

    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;

    private Texture bg;

    TextureRegion upRegion;
    TextureRegion downRegion;
    BitmapFont buttonFont;

    Image level1;
    Image level2;
    Image level3;
    Skin style;
    Texture texture;


    public ChapterSelection(final Symposition game){
        this.game = game;

        batch = new SpriteBatch();
        stage = game.stage;
        Gdx.input.setInputProcessor(stage);
        bg = new Texture(Gdx.files.internal("chapterSelectionBG.png"));

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        shapeRenderer = new ShapeRenderer();
        style = new Skin(Gdx.files.internal("rainbowui/rainbow-ui.json"));
        texture = new Texture(Gdx.files.internal("bg.png"));
        level1 = new Image(texture);
        TextButton button1 = new TextButton("Button 1",style);
        table.add(button1);
        table.add(level1);

        level1.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("image is clicked.");
            }
        });



    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(bg, 0, 0,800,500);
        batch.end();
        stage.act();
        stage.draw();

        table.drawDebug(shapeRenderer);
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
        stage.dispose();
        shapeRenderer.dispose();
    }
}
