package Screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.symposition.game.Symposition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameScreen_Music implements Screen, ApplicationListener {
    final Symposition game;

    private static Skin skin;
    private static Skin skin2;

    private static Stage stage;

    private int selectedPairIndex = 0;

    private int numberOfButtons = 4;

    private Table numArrTable = new Table();

    private Sound[] audioFiles;

    private Array<Integer> buttonIndices;

    private TextButton[] textButtons;

    private int audioId = -1;

    public GameScreen_Music(Symposition game) {
        this.game = game;

        skin = new Skin(Gdx.files.internal("quantum horizon/quantum-horizon-ui.json"));
        skin2 = new Skin(Gdx.files.internal("lgdxs/lgdxs-ui.json"));

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        textButtons = new TextButton[numberOfButtons]; // Initialize textButtons array

        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        audioFiles = new Sound[numberOfButtons]; // Initialize audioFiles array

        // Initialize audio files
        audioFiles[0] = Gdx.audio.newSound(Gdx.files.internal("sounds/Do.wav"));
        audioFiles[1] = Gdx.audio.newSound(Gdx.files.internal("sounds/Re.wav"));
        audioFiles[2] = Gdx.audio.newSound(Gdx.files.internal("sounds/Mi.wav"));
        audioFiles[3] = Gdx.audio.newSound(Gdx.files.internal("sounds/Fa.wav"));

        // Play Doremi.wav when the screen starts
        Sound doremiSound = Gdx.audio.newSound(Gdx.files.internal("sounds/doremi.wav"));
        doremiSound.play();

        randomizeButtonsAndAudioFiles();

        TextButton swapButton = new TextButton("Swap", skin);
        TextButton passButton = new TextButton("Pass", skin);
        TextButton finishButton = new TextButton("Finish", skin);

        // Add click listener to swapButton
        swapButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (selectedPairIndex > 0) {
                    swapButtons(selectedPairIndex - 1, selectedPairIndex);
                    selectedPairIndex = 0; // Reset selected pair index after swapping
                }
            }
        });

        // Add click listener to passButton
        passButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                nextPair();
            }
        });

        // Add click listener to finishButton
        finishButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkSorted();
            }
        });

        // Initialize and set listeners for textButtons
        for (int i = 0; i < numberOfButtons; i++) {
            textButtons[i] = new TextButton("", skin2.get("oval" + (i + 1), TextButton.TextButtonStyle.class));

            final int buttonIndex = i;
            textButtons[i].addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (selectedPairIndex == 0) {
                        selectedPairIndex = buttonIndex;
                    } else {
                        int audioIndex1 = buttonIndices.get(selectedPairIndex);
                        int audioIndex2 = buttonIndices.get(buttonIndex);
                        playAudioWithFading(audioIndex1);
                        playAudioWithFading(audioIndex2);
                        swapButtons(selectedPairIndex, buttonIndex);
                        selectedPairIndex = 0; // Reset selectedPairIndex
                    }
                }
            });
        }

        //1st Row - Number Array Table
        numArrTable.defaults().pad(20);
        root.add(numArrTable);

        for (TextButton button : textButtons) {
            numArrTable.add(button);
        }

        //2nd Row - Control Buttons Table
        root.row();
        Table controlsTable = new Table();
        controlsTable.defaults().pad(0, 20, 0, 20).width(200).height(70);
        root.add(controlsTable);

        controlsTable.add(swapButton).expandX();
        controlsTable.add(passButton).expandX();
        controlsTable.add(finishButton).expandX();

        root.setDebug(true);
    }

    private void playAudioWithFading(int audioIndex) {
        if (audioId != -1) {
            // Stop any currently playing audio with fading effect
            audioFiles[audioId].stop();
        }
        // Start new audio with fading effect
        long soundId = audioFiles[audioIndex].play();
        audioFiles[audioIndex].setVolume(soundId, 1); // Set volume to full
        audioId = audioIndex; // Update audioId with the currently playing audio
    }

    private void swapButtons(int index1, int index2) {
        // Swap positions of buttons
        TextButton tempButton = textButtons[index1];
        textButtons[index1] = textButtons[index2];
        textButtons[index2] = tempButton;

        // Swap positions of button indices
        int tempIndex = buttonIndices.get(index1);
        buttonIndices.set(index1, buttonIndices.get(index2));
        buttonIndices.set(index2, tempIndex);

        // Swap positions of audio files
        Sound tempAudio = audioFiles[index1];
        audioFiles[index1] = audioFiles[index2];
        audioFiles[index2] = tempAudio;

        // Reset audioId
        audioId = -1;
    }



    private void randomizeButtonsAndAudioFiles() {
        // Randomize button indices
        buttonIndices = new Array<>();
        for (int i = 0; i < numberOfButtons; i++) {
            buttonIndices.add(i);
        }
        buttonIndices.shuffle();
    }

    private void loadAudioFiles() {
        audioFiles = new Sound[numberOfButtons];
        for (int i = 0; i < numberOfButtons; i++) {
            audioFiles[i] = Gdx.audio.newSound(Gdx.files.internal("AudioFiles/" + (char) ('A' + i) + ".mp3"));
        }
    }

    private void nextPair() {
        selectedPairIndex++;
        if (selectedPairIndex >= numberOfButtons - 1) {
            selectedPairIndex = 0; // Wrap around to the first pair
        }
    }

    private void checkSorted() {
        boolean sorted = true;
        for (int i = 0; i < numberOfButtons - 1; i++) {
            if (buttonIndices.get(i) > buttonIndices.get(i + 1)) {
                sorted = false;
                break;
            }
        }

        if (sorted) {
            System.out.println("The buttons are sorted!");
        } else {
            System.out.println("The buttons are not sorted!");
        }
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
    public void create() {
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
