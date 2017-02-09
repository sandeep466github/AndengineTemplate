package com.example.chintu.andenginetemplate.scene;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.example.chintu.andenginetemplate.base.BaseScene;
import com.example.chintu.andenginetemplate.manager.SceneManager;
import com.example.chintu.andenginetemplate.manager.SceneManager.SceneType;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.color.Color;


/**
 * @author Mateusz Mysliwiec
 * @author www.matim-dev.com
 * @version 1.0
 */
public class GameScene extends BaseScene implements IOnSceneTouchListener {
    private HUD mGameHud;
    private PhysicsWorld mPhysicsWorld;
    private final int PHYSICS_STEPS_PER_SECOND = 60; // steps per second of the physics world
    private int GRAVITY_VALUE = -17; // gravity of the physics world.

    @Override
    public void createScene() {
        createBackground();
        createHUD();
        createPhysics();

        setOnSceneTouchListener(this);
    }

    private ContactListener getContactListener() {
        ContactListener contactListener = new ContactListener() {
            @Override
            public void beginContact(Contact contact) {

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
        };
        return contactListener;
    }

    @Override
    public void onBackKeyPressed() {
        SceneManager.getInstance().loadMenuSceneFromGameScene(mEngine);
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SCENE_GAME;
    }

    @Override
    public void disposeScene() {
        mCamera.setHUD(null);
        mCamera.setChaseEntity(null); //TODO
        mCamera.setCenter(400, 240);

        // TODO code responsible for disposing scene
        // removing all game scene objects.
    }

    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
        if (pSceneTouchEvent.isActionDown()) {
            //  touch events
        }
        return false;
    }

    /**
     * Creating game HUD. attach objects to gameHUD here.
     */
    private void createHUD() {
        mGameHud = new HUD();
        mCamera.setHUD(mGameHud);
    }

    private void createBackground() {
        setBackground(new Background(Color.BLUE));
    }

    private void createPhysics() {
        mPhysicsWorld = new PhysicsWorld(new Vector2(0, GRAVITY_VALUE), false);
        mPhysicsWorld.setContactListener(getContactListener());
        registerUpdateHandler(mPhysicsWorld);
    }

}