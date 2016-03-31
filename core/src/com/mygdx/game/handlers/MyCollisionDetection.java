package com.mygdx.game.handlers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.states.Play;

/**
 * Created by Dell on 31-03-2016.
 */
public class MyCollisionDetection implements ContactListener {
    private int numFootTouches;
    private Array<Body> bodiesToRemove;

    public MyCollisionDetection() {
        super();
        bodiesToRemove = new Array<Body>();
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        if (fa == null || fb == null) return;
        //System.out.println(");
        if ( (fa.getUserData() == Play.starsp && fb.getUserData() == Play.ballsp) ||  (fa.getUserData() == Play.ballsp && fb.getUserData() == Play.starsp)){
            System.out.println("Collision");
            if (fa.getType() == Shape.Type.Circle)
                fb.getBody().applyForceToCenter(new Vector2(100,0),false);
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

    public boolean footTouched() { return numFootTouches > 0; }
    public Array<Body> getBodiesToRemove() { return bodiesToRemove; }
}
