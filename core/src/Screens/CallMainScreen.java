package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.symposition.game.Symposition;

public class CallMainScreen extends Game {

    public SpriteBatch batch;
    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT = 200;
    public static final float PPM = 100; //pixels per meter
    MainMenuScreen mainmenu = new MainMenuScreen();
    private Skin skin;
    private Stage stage;
    Symposition symposition;
    MainMenuScreen ms;
    @Override
    public void create () {
        batch = new SpriteBatch();

        ms = new MainMenuScreen();
        ms.create();
        ms.resize(1280,800);




    }

    @Override
    public void render () {
        super.render();
        ms.render();

    }

    @Override
    public void dispose () {
        batch.dispose();
        ms.dispose();

    }


}

