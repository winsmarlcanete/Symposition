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

public class Waltz10 implements Screen {

    final Symposition game;

    OrthographicCamera camera;

    private final Skin skin;
    private final Skin skin2;
    private final Table ui;
    private final Stage stage;
    private final Texture bg;
    private final Table root;
    private final Table note;
    private final Table control;
    private final Sound sound1;
    private final Sound sound2;
    private final Sound sound3;
    private final Sound sound4;
    private final Sound sound5;
    private final Sound sound6;
    private final Sound sound7;
    private final Sound sound8;
    private final Sound sound9;
    private final Sound sound10;
    private final Sound sound11;
    private final Sound sound12;
    private final Sound sound13;
    private final Sound sound14;
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


    public Waltz10(final Symposition game){

        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        hud = new Hud(game.batch, game);



        skin = new Skin(Gdx.files.internal("rainbowui/rainbow-ui.json"));
        skin2 = new Skin(Gdx.files.internal("quantum horizon/quantum-horizon-ui.json"));

        bg = new Texture(Gdx.files.internal("bgImages/Waltz_Part_10.png"));
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/bgmusic/bg1.wav"));
        music.play();

        originalMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/levelmusic/waltzpart10.wav"));
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

        root.add(ui);
        ui.padTop(100);
        root.row().padTop(100);
        root.add(note);
        root.row().padBottom(180);
        root.add(control).padTop(100);

        stage.addActor(root);

        TextButton pause = new TextButton("ll", skin);
        pause.setPosition(ui.getWidth() / 4, ui.getHeight() / 2, Align.center);
        ui.add(pause).width(130).expandX().left().spaceRight(980).padLeft(-36);
        Window pausewindow = new Window("", skin2);
        pausewindow.setWidth(600);
        pausewindow.setHeight(400);
        pausewindow.setPosition(stage.getWidth()/2 - pausewindow.getWidth()/2, stage.getHeight()/2 - pausewindow.getHeight()/2);

        TextButton con = new TextButton("Continue", skin2);
        pausewindow.add(con).width(200).height(65);
        pausewindow.row().padTop(30);
        TextButton exit = new TextButton("Exit", skin2);
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

        TextButton playMelody = new TextButton(">", skin);
        playMelody.setPosition(ui.getWidth() / 4 * 2, ui.getHeight() / 2, Align.center);
        ui.add(playMelody).width(130);
        playMelody.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                originalMusic.play();
            }
        });


        sound1 = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/A6.wav"));
        Note note1 = new Note("A6",skin, sound1, false);

        note1.textbutton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note1.playSound();
            }
        });





        sound2 = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/B6.wav"));
        Note note2 = new Note("B6",skin, sound2, false);

        note2.textbutton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note2.playSound();
            }
        });




        sound3 = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/A6.wav"));
        Note note3 = new Note("A6",skin, sound3, false);

        note3.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note3.playSound();
            }
        });




        sound4 = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/G#6.wav"));
        Note note4 = new Note("G#6",skin, sound4, false);

        note4.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note4.playSound();
            }
        });


        sound5 = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/E6.wav"));
        Note note5 = new Note("E6",skin,sound5, false);

        note5.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note5.playSound();
            }
        });


        sound6 = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/F6.wav"));
        Note note6 = new Note("F6",skin, sound6, false);

        note6.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note6.playSound();
            }
        });



        sound7= Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/E6.wav"));
        Note note7 = new Note("E6",skin, sound7, false);

        note7.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note7.playSound();
            }
        });


        sound8 = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/E6.wav"));
        Note note8 = new Note("E6",skin, sound8, false);

        note8.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note8.playSound();
            }
        });


        sound9 = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/F6.wav"));
        Note note9 = new Note("F6",skin, sound9, false);

        note9.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note9.playSound();
            }
        });


        sound10 = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/E6.wav"));
        Note note10 = new Note("E6",skin, sound10, false);

        note10.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note10.playSound();
            }
        });

        sound11 = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/C5.wav"));
        Note note11 = new Note("C5",skin, sound11, false);

        note11.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note11.playSound();
            }
        });

        sound12 = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/A5.wav"));
        Note note12 = new Note("A5",skin, sound12, false);

        note12.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note12.playSound();
            }
        });

        sound13 = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/B5.wav"));
        Note note13 = new Note("B5",skin, sound13, false);

        note13.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note13.playSound();
            }
        });

        sound14 = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/A5.wav"));
        Note note14 = new Note("A5",skin, sound14, false);

        note14.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note14.playSound();
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
        notesOriginal.add(note9);
        notesOriginal.add(note10);
        notesOriginal.add(note11);
        notesOriginal.add(note12);
        notesOriginal.add(note13);
        notesOriginal.add(note14);




        notes = new ArrayList<>();
        notes.add(note1);
        notes.add(note2);
        notes.add(note3);
        notes.add(note4);
        notes.add(note5);
        notes.add(note6);
        notes.add(note7);
        notes.add(note8);
        notes.add(note9);
        notes.add(note10);
        notes.add(note11);
        notes.add(note12);
        notes.add(note13);
        notes.add(note14);




        Collections.shuffle(notes);

        note.padLeft(-38);
        for (int i = 0; i<=6 ; i++){
            note.add(notes.get(i).textbutton).width(180);
        }

        note.row().padTop(40);

        for (int i = 7; i <= 13 ; i++){
            note.add(notes.get(i).textbutton).width(180);
        }



        control.row();
        TextButton swap = new TextButton("Swap", skin);
        control.add(swap).width(400).padLeft(-38);
        swap.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                swapSound.play();
                swap(selectedNote1, selectedNote2);
            }
        });

        TextButton passer = new TextButton("Pass", skin);
        control.add(passer).width(400);
        passer.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                pass();
                passSound.play();



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

        TextButton play = new TextButton("Play Current Melody", skin);
        control.row();
        control.add(play).colspan(6).expand().spaceTop(20);
        play.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                play();
            }
        });


        notes.get(selectedNote1).setHighlighted(true);
        notes.get(selectedNote2).setHighlighted(true);







    }

    public void swap(int pair1, int pair2){
        Collections.swap(notes, pair1, pair2);
        note.reset();
        note.padLeft(-38);
        for (int i = 0; i<=6 ; i++){
            note.add(notes.get(i).textbutton).width(180);
        }

        note.row().padTop(40);

        for (int i = 7; i <= 13 ; i++){
            note.add(notes.get(i).textbutton).width(180);
        }



    }

    public void pass(){
        selectedNote1++;
        selectedNote2++;

        if(selectedNote1 == 13){
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
        notes.get(7).setHighlighted(false);
        notes.get(8).setHighlighted(false);
        notes.get(9).setHighlighted(false);
        notes.get(10).setHighlighted(false);
        notes.get(11).setHighlighted(false);
        notes.get(12).setHighlighted(false);
        notes.get(13).setHighlighted(false);



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
                notesOriginal.get(6).noteName == notes.get(6).noteName &&
                notesOriginal.get(7).noteName == notes.get(7).noteName &&
                notesOriginal.get(8).noteName == notes.get(8).noteName &&
                notesOriginal.get(9).noteName == notes.get(9).noteName &&
                notesOriginal.get(10).noteName == notes.get(10).noteName &&
                notesOriginal.get(11).noteName == notes.get(11).noteName &&
                notesOriginal.get(12).noteName == notes.get(12).noteName &&
                notesOriginal.get(13).noteName == notes.get(13).noteName


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
            Thread.sleep(80);
            notes.get(1).playSound();
            Thread.sleep(80);
            notes.get(2).playSound();
            Thread.sleep(80);
            notes.get(3).playSound();
            Thread.sleep(250);
            notes.get(4).playSound();
            Thread.sleep(250);
            notes.get(5).playSound();
            Thread.sleep(250);
            notes.get(6).playSound();
            Thread.sleep(500);
            notes.get(7).playSound();
            Thread.sleep(80);
            notes.get(8).playSound();
            Thread.sleep(80);
            notes.get(9).playSound();
            Thread.sleep(80);
            notes.get(10).playSound();
            Thread.sleep(250);
            notes.get(11).playSound();
            Thread.sleep(250);
            notes.get(12).playSound();
            Thread.sleep(250);
            notes.get(13).playSound();
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
        game.batch.draw(bg, 0, 0,800,500);
        game.batch.end();

        stage.act();
        stage.draw();

        if (nextLevel) {
            game.setScreen(new Waltz11(game));
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

