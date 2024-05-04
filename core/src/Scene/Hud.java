package Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.symposition.game.Symposition;

public class Hud {
    public Stage stage;
    private Viewport viewport;


    private Integer worldTimer;
    private float timeCount;
    private Integer score;
    private Skin skin;


    Label scoreLabel;
    Label timeLabel;

    public Hud(SpriteBatch sb) {
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(Symposition.V_WIDTH, Symposition.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        skin = new Skin(Gdx.files.internal("rainbowui/rainbow-ui.json"));

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel = new Label(String.format("%06d", score),skin);
        timeLabel = new Label(String.format("%03d", worldTimer), skin);

        table.add(scoreLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);

        stage.addActor(table);
    }





}
