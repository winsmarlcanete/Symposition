package Screens;

import Handlers.Note;
import Scene.Hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.symposition.game.Symposition;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;

import java.util.Collections;

public class Waltz7 implements Screen {

    final Symposition game;

    OrthographicCamera camera;

    private final Skin skin;
    private final Skin skin3;
    private final Skin skin4;
    private final Skin skin5;
    private final Skin skin6;
    private final Skin skin7;
    private final Skin skin8;
    private final Table ui;
    private final Stage stage;
    private final Texture bg;
    private final Table root;
    private final Table note;
    private final Table control;
    private final Sound doSound;
    private final Sound reSound;
    private final Sound miSound;
    private final Sound faSound;
    private final Sound soSound;
    private final Sound laSound;
    private final Sound tiSound;

    private final Sound swapSound;
    private final Sound passSound;
    private final Sound wrongSound;
    private int selectedNote1 = 0;
    private int selectedNote2 = 1;
    private final Music music;
    private final Music originalMusic;

    private final ArrayList<Note> notes;

    private final ArrayList<Note> notesOriginal;

    private boolean nextLevel;

    private Hud hud;
    private Boolean pauseTimer = false;


    public Waltz7(final Symposition game){

        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 400, 200);

        hud = new Hud(game.batch, game);

        skin = new Skin(Gdx.files.internal("horizon/horizon.json"));
        skin3 = new Skin(Gdx.files.internal("GameButtons/swap.json"));
        skin4 = new Skin(Gdx.files.internal("GameButtons/pass.json"));
        skin5 = new Skin(Gdx.files.internal("GameButtons/play.json"));
        skin6 = new Skin(Gdx.files.internal("GameButtons/repeat.json"));
        skin7 = new Skin(Gdx.files.internal("GameButtons/finish.json"));
        skin8 = new Skin(Gdx.files.internal("GameButtons/pause.json"));

        bg = new Texture(Gdx.files.internal("bgImages/Waltz_Part_7.png"));
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/bgmusic/bg1.wav"));
        music.play();

        originalMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/levelmusic/waltzpart7.wav"));
        originalMusic.play();


        swapSound = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/sfx2.wav"));
        passSound = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/sfx7.wav"));
        wrongSound = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx/wrong.wav"));


        stage = new Stage(new ScreenViewport());
//        stage = new Stage(new FillViewport(Symposition.V_WIDTH, Symposition.V_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        root = new Table();
        root.setFillParent(true);

        ui = new Table();
        note = new Table();
        control = new Table();

//        root.setDebug(true);
        root.add(ui).left().padTop(200);
        root.row().padTop(100);
        root.add(note);
        root.row().padBottom(180);
        root.add(control).padTop(100);

        stage.addActor(root);
        ImageButton pause = new ImageButton(skin8);
        ui.add(pause).padBottom(200);
        Window pausewindow = new Window("", skin);
        pausewindow.setWidth(600);
        pausewindow.setHeight(400);
        pausewindow.setPosition(stage.getWidth()/2 - pausewindow.getWidth()/2, stage.getHeight()/2 - pausewindow.getHeight()/2);

        TextButton con = new TextButton("Continue", skin);
        pausewindow.add(con).width(200).height(65);
        pausewindow.row().padTop(30);
        TextButton exit = new TextButton("Exit", skin);
        pausewindow.add(exit).width(200).height(65);
        pause.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                stage.addActor(pausewindow);
                music.pause();
                pauseTimer = true;
            }
        });

        con.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                stage.getRoot().removeActor(pausewindow);
                music.play();
                pauseTimer = false;
            }
        });

        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new LevelScreen(game));
            }
        });

        // Do
        doSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/C6.wav"));
        Note note1 = new Note("C6",skin, doSound, false);

        note1.textbutton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note1.playSound();
            }
        });




        //Re
        reSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/D6.wav"));
        Note note2 = new Note("D6",skin, reSound, false);

        note2.textbutton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note2.playSound();
            }
        });



        //Mi
        miSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/E6.wav"));
        Note note3 = new Note("E6",skin, miSound, false);

        note3.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note3.playSound();
            }
        });



        //Fa
        faSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/E6.wav"));
        Note note4 = new Note("E6",skin, faSound, false);

        note4.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note4.playSound();
            }
        });

        //So
        soSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/F6.wav"));
        Note note5 = new Note("F6",skin,soSound, false);

        note5.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note5.playSound();
            }
        });

        //La
        laSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/G6.wav"));
        Note note6 = new Note("G6",skin, laSound, false);

        note6.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note6.playSound();
            }
        });

        //Ti
        tiSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/A6.wav"));
        Note note7 = new Note("A6",skin, tiSound, false);

        note7.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note7.playSound();
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





        notes = new ArrayList<>();
        notes.add(note1);
        notes.add(note2);
        notes.add(note3);
        notes.add(note4);
        notes.add(note5);
        notes.add(note6);
        notes.add(note7);




        Collections.shuffle(notes);

        note.padLeft(-38);
        //Add the textbuttons to the table for it to be rendered
        for (Note i : notes) {
            note.add(i.textbutton).width(140);
        }

        control.row().padTop(160);
        Table control1 = new Table();
        control.add(control1).center().padLeft(460);

        control1.row();
        ImageButton swap = new ImageButton(skin3);
        control1.add(swap).width(150);
        swap.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                swapSound.play();
                swap(selectedNote1, selectedNote2);
            }
        });

        ImageButton passer = new ImageButton(skin4);
        control1.add(passer).width(150);
        passer.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                pass();
                passSound.play();
            }
        });


        Table control2 = new Table();
        control.add(control2).right().padLeft(300);

        ImageButton play = new ImageButton(skin5);
        control2.add(play).width(40).space(20);
        play.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                play();
            }
        });

        ImageButton repeat = new ImageButton(skin6);
        control2.add(repeat).width(40).space(20);
        repeat.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                originalMusic.play();
            }
        });

        ImageButton finisher = new ImageButton(skin7);
        control2.add(finisher).width(40).space(23);
        finisher.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                finish();
            }
        });


        notes.get(selectedNote1).setHighlighted(true);
        notes.get(selectedNote2).setHighlighted(true);







    }

    public void swap(int pair1, int pair2){
        Collections.swap(notes, pair1, pair2);
        note.reset();
        note.padLeft(-38);
        for (Note i : notes) {
            note.add(i.textbutton).width(140);
        }
    }

    public void pass(){
        selectedNote1++;
        selectedNote2++;

        if(selectedNote1 == 6){
            selectedNote1 = 0;
            selectedNote2 = 1;
        }

        notes.get(0).setHighlighted(false);
        notes.get(1).setHighlighted(false);
        notes.get(2).setHighlighted(false);
        notes.get(3).setHighlighted(false);
        notes.get(4).setHighlighted(false);
        notes.get(5).setHighlighted(false);
        notes.get(6).setHighlighted(false);



        notes.get(selectedNote1).setHighlighted(true);
        notes.get(selectedNote2).setHighlighted(true);







    }

    public void finish(){

        if (    notesOriginal.get(0).noteName == notes.get(0).noteName &&
                notesOriginal.get(1).noteName == notes.get(1).noteName &&
                notesOriginal.get(2).noteName == notes.get(2).noteName &&
                notesOriginal.get(3).noteName == notes.get(3).noteName &&
                notesOriginal.get(4).noteName == notes.get(4).noteName &&
                notesOriginal.get(5).noteName == notes.get(5).noteName &&
                notesOriginal.get(6).noteName == notes.get(6).noteName
        ) {
            nextLevel = true;
        }
        else {
            wrongSound.play();
        }
    }

    public void play(){

        try {
            notes.get(0).playSound();
            Thread.sleep(250);
            notes.get(1).playSound();
            Thread.sleep(250);
            notes.get(2).playSound();
            Thread.sleep(500);
            notes.get(3).playSound();
            Thread.sleep(500);
            notes.get(4).playSound();
            Thread.sleep(250);
            notes.get(5).playSound();
            Thread.sleep(250);
            notes.get(6).playSound();
            Thread.sleep(250);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }



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
        game.batch.draw(bg, 0, 0,400,200);
        game.batch.end();

        stage.act();
        stage.draw();

        if (nextLevel) {
            game.setScreen(new Waltz8(game));
            music.dispose();
        }


        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        if(!pauseTimer) {
            hud.update(Gdx.graphics.getDeltaTime());
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
        music.dispose();
    }
}


