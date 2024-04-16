package Screens;

import Handlers.Note;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.symposition.game.Symposition;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Gameplay1 implements Screen {

    final Symposition game;

    OrthographicCamera camera;

    private Skin skin;
    private Stage stage;
    private Texture bg;
    private Table root;
    private Table note;
    private Table control;
    private  Sound doSound;
    private  Sound reSound;
    private  Sound miSound;
    private  Sound faSound;
    private Sound soSound;
    private Sound laSound;
    private Sound tiSound;
    private Sound do2Sound;
    private Sound swapSound;
    private Sound passSound;
    private int selectedNote1 = 0;
    private int selectedNote2 = 1;
    private Music music;
    private Music originalMusic;

    private ArrayList<Note> notes;

    private ArrayList<Note> notesOriginal;

    private boolean nextLevel;

    public Gameplay1(final Symposition game){

        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        skin = new Skin(Gdx.files.internal("rainbowui/rainbow-ui.json"));
        bg = new Texture(Gdx.files.internal("chapterSelectionBG.png"));
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/bgmusic/bg1.wav"));
        music.play();

        originalMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/doremi.wav"));
        originalMusic.play();

        swapSound = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/sfx2.wav"));
        passSound = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/sfx7.wav"));


        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        root = new Table();
        root.setFillParent(true);

        note = new Table();
        control = new Table();


        root.add(note);
        root.row();
        root.add(control).padTop(100);

        stage.addActor(root);





        // Do
        doSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Do.wav"));
        Note note1 = new Note("Do",skin, doSound, false);

        note1.textbutton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note1.playSound();
            }
        });




        //Re
        reSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Re.wav"));
        Note note2 = new Note("Re",skin, reSound, false);

        note2.textbutton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note2.playSound();
            }
        });



        //Mi
        miSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Mi.wav"));
        Note note3 = new Note("Mi",skin, miSound, false);

        note3.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note3.playSound();
            }
        });



        //Fa
        faSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Fa.wav"));
        Note note4 = new Note("Fa",skin, faSound, false);

        note4.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note4.playSound();
            }
        });

        //So
        soSound = Gdx.audio.newSound(Gdx.files.internal("sounds/so.wav"));
        Note note5 = new Note("So",skin,soSound, false);

        note5.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note5.playSound();
            }
        });

        //La
        laSound = Gdx.audio.newSound(Gdx.files.internal("sounds/la.wav"));
        Note note6 = new Note("La",skin, laSound, false);

        note6.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note6.playSound();
            }
        });

        //Ti
        tiSound = Gdx.audio.newSound(Gdx.files.internal("sounds/ti.wav"));
        Note note7 = new Note("Ti",skin, tiSound, false);

        note7.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note7.playSound();
            }
        });

        //Do2
        do2Sound = Gdx.audio.newSound(Gdx.files.internal("sounds/do2.wav"));
        Note note8 = new Note("Do",skin, do2Sound, false);

        note8.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note8.playSound();
            }
        });

        notesOriginal = new ArrayList<>();
        notesOriginal.add(note1);
        notesOriginal.add(note2);
        notesOriginal.add(note3);
        notesOriginal.add(note4);
        notesOriginal.add(note5);
        notesOriginal.add(note6);
        notesOriginal.add(note7);
        notesOriginal.add(note8);


        notes = new ArrayList<>();
        notes.add(note1);
        notes.add(note2);
        notes.add(note3);
        notes.add(note4);
        notes.add(note5);
        notes.add(note6);
        notes.add(note7);
        notes.add(note8);

        Collections.shuffle(notes);



        //Add the textbuttons to the table for it to be rendered
        for (Note i : notes) {
            note.add(i.textbutton);
        }



        control.row();
        TextButton swap = new TextButton("Swapper", skin);
        control.add(swap).width(400);
        swap.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                swapSound.play();
                swap(selectedNote1, selectedNote2);


            }
        });

        TextButton passer = new TextButton("Passer", skin);
        control.add(passer).width(400);
        passer.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                pass(selectedNote1, selectedNote2);
                passSound.play();
                originalMusic.play();


            }
        });

        TextButton finisher = new TextButton("Finish", skin);
        control.add(finisher).width(400);
        finisher.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                finish();


            }
        });

        TextButton play = new TextButton("Play My Song", skin);
        control.row();
        control.add(play).colspan(6).expand().spaceTop(20);
        play.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                play();

            }
        });



    }

    public void swap(int pair1, int pair2){
        Collections.swap(notes, pair1, pair2);
        note.reset();
        for (Note i : notes) {
            note.add(i.textbutton);
        }


    }

    public void pass(int pair1, int pair2){
        selectedNote1++;
        selectedNote2++;

        if(selectedNote1 == 7){
            selectedNote1 = 0;
            selectedNote2 = 1;

        }
    }

    public void finish(){
        boolean isEqual = notesOriginal.equals(notes);
        if (isEqual) {
            nextLevel = true;
        }
    }

    public void play(){
        notes.get(0).playSound();
        notes.get(1).playSound();

    }




    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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

        if (nextLevel) {
            game.setScreen(new Level1(game));
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
    }
}
