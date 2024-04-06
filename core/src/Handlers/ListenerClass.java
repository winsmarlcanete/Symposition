package Handlers;

import com.badlogic.gdx.physics.box2d.*;

public class ListenerClass implements ContactListener {


    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB(); //fixture of mc

        if(fa.getUserData().equals("level1") && fa.getUserData() != null) {
            System.out.println("level1");
        }
        if(fa.getUserData().equals("level2") && fa.getUserData() != null) {
            System.out.println("level2");
        }
        if(fa.getUserData().equals("level3") && fa.getUserData() != null) {
            System.out.println("level3");
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
