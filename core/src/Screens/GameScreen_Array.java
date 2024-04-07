package Screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.symposition.game.Symposition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameScreen_Array implements Screen, ApplicationListener {
    final Symposition game;

    public static Skin skin;
    public static Skin skin2;

    public static Stage stage;

    private int selectedPairIndex = 0;

    int numberOfButtons = 4;
    TextButton[] textButtons = new TextButton[numberOfButtons];

    Table numArrTable = new Table();

    public GameScreen_Array(Symposition game) {
        this.game = game;

        skin = new Skin(Gdx.files.internal("quantum horizon/quantum-horizon-ui.json"));
        skin2 = new Skin(Gdx.files.internal("lgdxs/lgdxs-ui.json"));

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        TextButton swapButton = new TextButton("Swap", skin);
        TextButton passButton = new TextButton("Pass", skin);
        TextButton finishButton = new TextButton("Finish", skin);

        // Add click listeners to buttons
        swapButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                swapButtons();
            }
        });

        passButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                nextPair();
            }
        });

        finishButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkSorted();
            }
        });



        //1st Row - Number Array Table

        numArrTable.defaults().pad(20);
        root.add(numArrTable);

        TextButton.TextButtonStyle buttonStyle = skin2.get("oval1", TextButton.TextButtonStyle.class);
        TextButton.TextButtonStyle buttonStyle2 = skin2.get("oval2", TextButton.TextButtonStyle.class);
        TextButton.TextButtonStyle buttonStyle3 = skin2.get("oval3", TextButton.TextButtonStyle.class);
        TextButton.TextButtonStyle buttonStyle4 = skin2.get("oval4", TextButton.TextButtonStyle.class);

        List<ButtonPair> buttonPairs = new ArrayList<>();
            buttonPairs.add(new ButtonPair(buttonStyle, "1"));
            buttonPairs.add(new ButtonPair(buttonStyle2, "2"));
            buttonPairs.add(new ButtonPair(buttonStyle3, "3"));
            buttonPairs.add(new ButtonPair(buttonStyle4, "4"));

        Collections.shuffle(buttonPairs);



        for (int i = 0; i < numberOfButtons; i++) {
            textButtons[i] = new TextButton(buttonPairs.get(i).number, buttonPairs.get(i).style);
            numArrTable.add(textButtons[i]);
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

    private static class ButtonPair {
        TextButton.TextButtonStyle style;
        String number;

        ButtonPair(TextButton.TextButtonStyle style, String number) {
            this.style = style;
            this.number = number;
        }
    }

    private void swapButtons() {
        if (selectedPairIndex >= 0 && selectedPairIndex < numberOfButtons - 1) {
            // Swap positions of buttons
            TextButton temp = textButtons[selectedPairIndex];
            textButtons[selectedPairIndex] = textButtons[selectedPairIndex + 1];
            textButtons[selectedPairIndex + 1] = temp;

            // Remove old buttons and add swapped buttons to table
            numArrTable.clearChildren();
            for (TextButton button : textButtons) {
                numArrTable.add(button);
            }
        }
    }

    private void nextPair() {
        selectedPairIndex++;
        if (selectedPairIndex >= numberOfButtons - 1) {
            selectedPairIndex = 0; // Wrap around to the first pair
        }

        // Update border highlight for selected pair
        updateBorderHighlight();
    }

    private void updateBorderHighlight() {
        // Your code to highlight the selected pair of buttons with a border
    }

    private void checkSorted() {
        boolean sorted = true;
        for (int i = 0; i < numberOfButtons - 1; i++) {
            if (Integer.parseInt(textButtons[i].getText().toString()) > Integer.parseInt(textButtons[i + 1].getText().toString())) {
                sorted = false;
                break;
            }
        }

        if (sorted) {
            System.out.println("The numbers are sorted!");
        } else {
            System.out.println("The numbers are not sorted!");
        }
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.9f, .9f, .9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void create() {}

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
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
    }
}
