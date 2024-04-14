package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.symposition.game.Symposition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Level1 implements Screen {

    private Skin skin;
    private Stage stage;
    private List<Sound> noteSounds;

    public Level1(final Symposition game){
        skin = new Skin(Gdx.files.internal("rainbowui/rainbow-ui.json"));

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        //sounds
        Sound melody = Gdx.audio.newSound(Gdx.files.internal("sounds/doremi.wav"));

        //List of Sound objects for note sounds
        noteSounds = new ArrayList<>();
        noteSounds.add(Gdx.audio.newSound(Gdx.files.internal("sounds/Do.wav")));
        noteSounds.add(Gdx.audio.newSound(Gdx.files.internal("sounds/Re.wav")));
        noteSounds.add(Gdx.audio.newSound(Gdx.files.internal("sounds/Mi.wav")));
        noteSounds.add(Gdx.audio.newSound(Gdx.files.internal("sounds/Fa.wav")));

        // Shuffle the list of note sounds
        Collections.shuffle(noteSounds);

        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        TextButton playMusicbtn = new TextButton("Play music", skin);
        root.add(playMusicbtn).colspan(4);
        //Play Melody
        playMusicbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                melody.play();
            }
        });


        root.row().padTop(100);

//        TextButton note1btn = new TextButton("Note 1", skin);
//        root.add(note1btn).space(10);
//        note1btn.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                Note1.play();
//            }
//        });
//
//
//        TextButton note2btn = new TextButton("Note 2", skin);
//        root.add(note2btn).space(10);
//        note2btn.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                Note2.play();
//            }
//        });
//
//        TextButton note3btn = new TextButton("Note 3", skin);
//        root.add(note3btn).space(10);
//        note3btn.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                Note3.play();
//            }
//        });
//
//        TextButton note4btn = new TextButton("Note 4", skin);
//        root.add(note4btn).space(10);
//        note4btn.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                Note4.play();
//            }
//        });
        // Add buttons and assign shuffled sounds
        for (int i = 0; i < noteSounds.size(); i++) {
            final Sound sound = noteSounds.get(i);
            TextButton noteBtn = new TextButton("Note " + (i + 1), skin);
            root.add(noteBtn).space(10);
            noteBtn.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    sound.play();
                }
            });
        }

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
