package Sprites;

import Screens.LevelScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.symposition.game.Symposition;

public class Mc extends Sprite {
    public World world;
    public Body b2body;
    private TextureRegion mcStand;

    public Mc (World world, LevelScreen screen) {
        super(screen.getAtlas().findRegion("main female-Sheet"));
        this.world = world;
        defineMc();
        mcStand = new TextureRegion(getTexture(),630,496,43,50);
        setBounds(0,0,40/Symposition.PPM, 40/Symposition.PPM);
        setRegion(mcStand);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
    }
    public void defineMc(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(100/ Symposition.PPM,100/Symposition.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(17/Symposition.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }


}
