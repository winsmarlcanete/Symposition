package Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.symposition.game.Symposition;
import sun.jvm.hotspot.debugger.cdbg.Sym;

public class Hud extends Matrix4 {
    public Stage stage;
    private Viewport viewport;

    private float timeCount;
    private Integer score;
    private Skin skin;
    public Symposition game;


    Label timeLabel;

    public Hud(SpriteBatch sb, Symposition game) {
        timeCount = 0;
        this.game = game;


        viewport = new FitViewport(Symposition.V_WIDTH, Symposition.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        skin = new Skin(Gdx.files.internal("menu/Label/regular.json"));

        Table table = new Table();
        table.top();
        table.setFillParent(true);


        timeLabel = new Label(String.format("%03d", game.levelTimer), skin);


        table.add(timeLabel).expandX().padTop(10);

        stage.addActor(table);
    }

    public void update(float dt) {
        timeCount += dt;
        if (timeCount >= 1) {
            game.levelTimer++;
            timeLabel.setText(String.format("%03d", game.levelTimer));
            timeCount = 0;
        }


    }

}