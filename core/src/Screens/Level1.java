package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.symposition.game.Symposition;

public class Level1 implements Screen {

    private Skin skin;
    private Stage stage;

    public Level1(final Symposition game){
        skin = new Skin(Gdx.files.internal("rainbowui/rainbow-ui.json"));

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        TextButton playMusicbtn = new TextButton("Play music", skin);
        root.add(playMusicbtn).colspan(4);

        root.row().padTop(100);

        TextButton note1btn = new TextButton("Note 1", skin);
        root.add(note1btn).space(10);

        TextButton note2btn = new TextButton("Note 2", skin);
        root.add(note2btn).space(10);

        TextButton note3btn = new TextButton("Note 3", skin);
        root.add(note3btn).space(10);

        TextButton note4btn = new TextButton("Note 4", skin);
        root.add(note4btn).space(10);

        root.row().padTop(100);

        TextButton swapbtn = new TextButton("SWAP", skin);
        root.add(swapbtn).space(10);

        TextButton passbtn = new TextButton("PASS", skin);
        root.add(passbtn).space(10);

        TextButton finishbtn = new TextButton("FINISH", skin);
        root.add(finishbtn).space(10);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.9f, .9f, .9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();


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
    }
}
