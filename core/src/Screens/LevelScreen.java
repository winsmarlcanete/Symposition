package Screens;

import Handlers.ListenerClass;
import Sprites.Mc;
import Tools.B2WorldCreator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.symposition.game.Symposition;

public class LevelScreen implements Screen {

    private Symposition game;
    private TextureAtlas atlas;

    private OrthographicCamera gamecam;
    private Viewport gamePort;

    //Tiled-map variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private TiledMapTileLayer layer;
    private TiledMapTileLayer layer2;
    private TiledMapTileLayer layer3;
    private TiledMapTileLayer layer4;
    private TiledMapTileLayer layer5;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;

    private boolean goback2menu;
    private boolean backMenu = false;
    private boolean goplaylevel1;
    private boolean playlevel1 = false;
    private boolean goplaylevel2;
    private boolean playlevel2 = false;
    private boolean goplaylevel3;
    private boolean playlevel3 = false;





    private Mc mc;
    Music music;



    public LevelScreen(final Symposition game){
        atlas = new TextureAtlas("Mc.atlas");

        this.game = game;

        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(Symposition.V_WIDTH/Symposition.PPM,Symposition.V_HEIGHT/Symposition.PPM,gamecam);
        gamePort.apply();
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("tiles/chooseLevelMap.tmx");

        layer = (TiledMapTileLayer)map.getLayers().get(10);
        layer2 = (TiledMapTileLayer)map.getLayers().get(11);
        layer3 = (TiledMapTileLayer)map.getLayers().get(13);
        layer4 = (TiledMapTileLayer)map.getLayers().get(15);
        layer5 = (TiledMapTileLayer)map.getLayers().get(16);


        renderer = new OrthogonalTiledMapRenderer(map, 1/Symposition.PPM);
        gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2, 0);
        world = new World(new Vector2(0,-10), true);
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(world,map);

        mc = new Mc(world,this);
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/bgmusic/bg2.wav"));
        music.play();
        ContactListener ListenerClass = null;
        world.setContactListener(new ListenerClass(){
            @Override
            public void beginContact(Contact contact) {
                Fixture fa = contact.getFixtureA();
                Fixture fb = contact.getFixtureB(); //fixture of mc

                if(fa.getUserData().equals("back2Menu") && fa.getUserData() != null) {
                    layer.setVisible(true);
                    goback2menu = true;
                }
                if(fa.getUserData().equals("level1sensor") && fa.getUserData() != null) {
                    layer2.setVisible(true);
                    goplaylevel1 = true;
                }
                if(fa.getUserData().equals("usearrow") && fa.getUserData() != null) {

                }
                if(fa.getUserData().equals("level2sensor") && fa.getUserData() != null) {
                    layer4.setVisible(true);
                    goplaylevel2 = true;
                }

                if(fa.getUserData().equals("level3sensor") && fa.getUserData() != null) {
                    layer5.setVisible(true);
                    goplaylevel3 = true;
                }




            }
            @Override
            public void endContact(Contact contact) {
                super.endContact(contact);
                Fixture fa = contact.getFixtureA();
                Fixture fb = contact.getFixtureB(); //fixture of mc

                if(fa.getUserData().equals("back2Menu") && fa.getUserData() != null) {
                    layer.setVisible(false);
                    goback2menu = false;
                }
                if(fa.getUserData().equals("usearrow") && fa.getUserData() != null) {
                    layer3.setVisible(false);
                }

                if(fa.getUserData().equals("level1sensor") && fa.getUserData() != null) {
                    layer2.setVisible(false);
                    goplaylevel1 = false;
                }

                if(fa.getUserData().equals("level2sensor") && fa.getUserData() != null) {
                    layer4.setVisible(false);
                    goplaylevel2 = false;
                }

                if(fa.getUserData().equals("level3sensor") && fa.getUserData() != null) {
                    layer5.setVisible(false);
                    goplaylevel3 = false;
                }
            }


        });





    }

    public TextureAtlas getAtlas(){
        return atlas;
    }
    @Override
    public void show() {

    }
    public void handleInput(float dt){

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && mc.mcbody.getLinearVelocity().x <= 2) {
            mc.mcbody.applyLinearImpulse(new Vector2(0.05f, 0), mc.mcbody.getWorldCenter(), true);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && mc.mcbody.getLinearVelocity().x >= -2) {
            mc.mcbody.applyLinearImpulse(new Vector2(-0.05f, 0), mc.mcbody.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && goback2menu) {
            backMenu = true;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.E) && goplaylevel1) {
            playlevel1 = true;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.E) && goplaylevel2) {
            playlevel2 = true;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.E) && goplaylevel3) {
            playlevel3 = true;
        }
    }
    public void update (float dt) {
        handleInput(dt);
        world.step(1/60f,6,2);

        mc.update(dt);

        //attach gamecam to b2body mc
        gamecam.position.x = mc.mcbody.getPosition().x;
        gamecam.update();
        renderer.setView(gamecam);


    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render the map
        renderer.render();

        //render the Box2DDebugLines
         //b2dr.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        mc.draw(game.batch);

        game.batch.end();

        if (playlevel1) {

            game.setScreen(new Twinkle1(game));

        } if (playlevel2){
            game.setScreen(new Twinkle2(game));
        }

        if (playlevel3){
            game.setScreen(new Twinkle3(game));
        }
        if (backMenu) {
            game.setScreen(new MenuScreen(game));
        }

    }

    @Override
    public void resize(int width, int height) {
    gamePort.update(width,height);
    }

    @Override
    public void pause() {
        music.pause();
    }

    @Override
    public void resume() {
        music.play();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();

    }
}
