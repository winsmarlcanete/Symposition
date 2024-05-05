package Screens;

import Handlers.Note;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.symposition.game.Symposition;

import java.util.ArrayList;
import java.util.Collections;

public class FurElise7 implements Screen {

    final Symposition game;

    OrthographicCamera camera;

    private final Skin skin;
    private final Skin skin2;

    private final Stage stage;
    private final Texture bg;
    private final Table root;
    private final Table ui;
    private final Table note;
    private final Table control;
    private final Sound firstNote;
    private final Sound secondNote;
    private final Sound thirdNote;
    private final Sound fourthNote;
    private final Sound fifthNote;
    private final Sound sixthNote;
    private final Sound seventhNote;
    private final Sound eighthNote;
    private final Sound ninthNote;
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


    public FurElise7(final Symposition game){

        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);



        skin = new Skin(Gdx.files.internal("rainbowui/rainbow-ui.json"));
        skin2 = new Skin(Gdx.files.internal("quantum horizon/quantum-horizon-ui.json"));


        bg = new Texture(Gdx.files.internal("bgImages/littlestar1.png"));
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/bgmusic/bg1.wav"));
//        music.play();

        originalMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/levelmusic/FE7.wav"));
//        originalMusic.play();


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
            }
        });

        con.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                stage.getRoot().removeActor(pausewindow);
                music.play();
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


        // Do
        firstNote = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/E5.wav"));
        Note note1 = new Note("1",skin, firstNote, false);

        note1.textbutton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note1.playSound();
            }
        });




        //Re
        secondNote = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/E6.wav"));
        Note note2 = new Note("2",skin, secondNote, false);

        note2.textbutton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note2.playSound();
            }
        });



        //Mi
        thirdNote = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/E6.wav"));
        Note note3 = new Note("2",skin, thirdNote, false);

        note3.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note3.playSound();
            }
        });



        //Fa
        fourthNote = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/E7.wav"));
        Note note4 = new Note("3",skin, fourthNote, false);

        note4.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note4.playSound();
            }
        });

        //So
        fifthNote = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/D#6.wav"));
        Note note5 = new Note("4",skin, fifthNote, false);

        note5.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note5.playSound();
            }
        });

        //La
        sixthNote = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/E6.wav"));
        Note note6 = new Note("2",skin, sixthNote, false);

        note6.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note6.playSound();
            }
        });

        //Ti
        seventhNote = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/D#6.wav"));
        Note note7 = new Note("4",skin, seventhNote, false);

        note7.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note7.playSound();
            }
        });

        eighthNote = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/E6.wav"));
        Note note8 = new Note("2",skin, eighthNote, false);

        note8.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note8.playSound();
            }
        });

        ninthNote = Gdx.audio.newSound(Gdx.files.internal("sounds/Notes/D#6.wav"));
        Note note9 = new Note("4",skin, ninthNote, false);

        note9.textbutton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                note9.playSound();
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


//        Collections.shuffle(notes);



        note.padLeft(-38);
        //Add the textbuttons to the table for it to be rendered
        for (Note i : notes) {
            note.add(i.textbutton).width(120);
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
        for (Note i : notes) {
            note.add(i.textbutton).width(120);
        }
    }

    public void pass(){
        selectedNote1++;
        selectedNote2++;

        if(selectedNote1 == notes.size()-1){
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
                notesOriginal.get(8).noteName == notes.get(8).noteName
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
            Thread.sleep(220);
            notes.get(1).playSound();
            Thread.sleep(220);
            notes.get(2).playSound();
            Thread.sleep(210);
            notes.get(3).playSound();
            Thread.sleep(210);
            notes.get(4).playSound();
            Thread.sleep(210);
            notes.get(5).playSound();
            Thread.sleep(210);
            notes.get(6).playSound();
            Thread.sleep(210);
            notes.get(7).playSound();
            Thread.sleep(210);
            notes.get(8).playSound();
            Thread.sleep(210);
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
            game.setScreen(new FurElise8(game));
            music.dispose();
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
