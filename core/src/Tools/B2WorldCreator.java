package Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.symposition.game.Symposition;

public class B2WorldCreator {
    public B2WorldCreator(World world, TiledMap map) {

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        Fixture fixture;


        for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+ rect.getWidth()/2)/ Symposition.PPM, (rect.getY()+rect.getHeight()/2)/Symposition.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2/Symposition.PPM,rect.getHeight()/2/Symposition.PPM);
            fdef.shape = shape;
            body.createFixture(fdef).setUserData("ground");
        }


        for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+ rect.getWidth()/2)/Symposition.PPM, (rect.getY()+rect.getHeight()/2)/Symposition.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2/Symposition.PPM,rect.getHeight()/2/Symposition.PPM);
            fdef.shape = shape;
            body.createFixture(fdef).setUserData("wall");
        }


        for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+ rect.getWidth()/2)/Symposition.PPM, (rect.getY()+rect.getHeight()/2)/Symposition.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2/Symposition.PPM,rect.getHeight()/2/Symposition.PPM);
            fdef.shape = shape;
            fdef.isSensor = true;
            body.createFixture(fdef).setUserData("level1");

        }


        for(MapObject object: map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+ rect.getWidth()/2)/Symposition.PPM, (rect.getY()+rect.getHeight()/2)/Symposition.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2/Symposition.PPM,rect.getHeight()/2/Symposition.PPM);
            fdef.shape = shape;
            body.createFixture(fdef).setUserData("level2");
        }


        for(MapObject object: map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+ rect.getWidth()/2)/Symposition.PPM, (rect.getY()+rect.getHeight()/2)/Symposition.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2/Symposition.PPM,rect.getHeight()/2/Symposition.PPM);
            fdef.shape = shape;
            body.createFixture(fdef).setUserData("level3");
        }


        for(MapObject object: map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+ rect.getWidth()/2)/Symposition.PPM, (rect.getY()+rect.getHeight()/2)/Symposition.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2/Symposition.PPM,rect.getHeight()/2/Symposition.PPM);
            fdef.shape = shape;

            body.createFixture(fdef).setUserData("back2Menu");
        }


        for(MapObject object: map.getLayers().get(12).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+ rect.getWidth()/2)/Symposition.PPM, (rect.getY()+rect.getHeight()/2)/Symposition.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2/Symposition.PPM,rect.getHeight()/2/Symposition.PPM);
            fdef.shape = shape;

            body.createFixture(fdef).setUserData("level1sensor");
        }


        for(MapObject object: map.getLayers().get(14).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+ rect.getWidth()/2)/Symposition.PPM, (rect.getY()+rect.getHeight()/2)/Symposition.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2/Symposition.PPM,rect.getHeight()/2/Symposition.PPM);
            fdef.shape = shape;

            body.createFixture(fdef).setUserData("usearrow");
        }
    }
}
