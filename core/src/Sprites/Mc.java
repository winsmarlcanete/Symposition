package Sprites;

import Screens.LevelScreen;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.symposition.game.Symposition;

public class Mc extends Sprite {
    public enum State {RUNNING, STANDING};
    public State currentState;
    public State previousState;
    public World world;
    public Body mcbody;
    private TextureRegion mcStand;
    private Animation<TextureRegion> mcRun;
    private float stateTimer;
    private boolean runningRight;

    public Mc (World world, LevelScreen screen) {
        super(screen.getAtlas().findRegion("main female-Sheet"));

        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        frames.add(new TextureRegion(getTexture(), 676, 592, 40, 50));
        frames.add(new TextureRegion(getTexture(), 628, 592, 40, 50));
        frames.add(new TextureRegion(getTexture(), 580, 592, 40, 50));

        mcRun = new Animation<TextureRegion>(0.2f, frames);
        frames.clear();

        this.world = world;
        defineMc();
        mcStand = new TextureRegion(getTexture(),630,496,43,49);
        setBounds(0,0,40/Symposition.PPM, 40/Symposition.PPM);
        setRegion(mcStand);
    }

    public void update(float dt){
        setPosition(mcbody.getPosition().x - getWidth()/2, mcbody.getPosition().y - getHeight()/2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();
        TextureRegion region;
        switch (currentState) {
            case RUNNING:
                region =  mcRun.getKeyFrame(stateTimer, true);
                break;
            case STANDING:
            default:
                region = mcStand;
                break;

        }

        if ((mcbody.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        } else if ((mcbody.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;

        return region;
    }



    public State getState(){
        if(mcbody.getLinearVelocity().x != 0){
            return State.RUNNING;
        }
        else {
            return State.STANDING;
        }
    }


    public void defineMc(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(250/ Symposition.PPM,100/Symposition.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        mcbody = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(17/Symposition.PPM);

        fdef.shape = shape;
        mcbody.createFixture(fdef).setUserData("player");
    }


}
