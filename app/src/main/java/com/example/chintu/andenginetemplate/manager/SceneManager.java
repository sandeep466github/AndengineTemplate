package com.example.chintu.andenginetemplate.manager;

import com.example.chintu.andenginetemplate.base.BaseScene;
import com.example.chintu.andenginetemplate.scene.GameScene;
import com.example.chintu.andenginetemplate.scene.LoadingScene;
import com.example.chintu.andenginetemplate.scene.MainMenuScene;
import com.example.chintu.andenginetemplate.scene.SplashScene;

import org.andengine.engine.Engine;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

/**
 * @author Mateusz Mysliwiec
 * @author www.matim-dev.com
 * @version 1.0
 */
public class SceneManager {
    //---------------------------------------------
    // SCENES
    //---------------------------------------------

    private BaseScene splashScene;
    private BaseScene menuScene;
    private BaseScene gameScene;
    private BaseScene loadingScene;

    //---------------------------------------------
    // VARIABLES
    //---------------------------------------------

    private static final SceneManager INSTANCE = new SceneManager();

    private SceneType currentSceneType = SceneType.SCENE_SPLASH;

    private BaseScene currentScene;

    private Engine engine = ResourcesManager.getInstance().mEngine;

    public enum SceneType {
        SCENE_SPLASH,
        SCENE_MENU,
        SCENE_GAME,
        SCENE_LOADING,
    }

    //---------------------------------------------
    // CLASS LOGIC
    //---------------------------------------------

    public void setScene(BaseScene scene) {
        engine.setScene(scene);
        currentScene = scene;
        currentSceneType = scene.getSceneType();
    }

    public void setScene(SceneType sceneType) {
        switch (sceneType) {
            case SCENE_MENU:
                setScene(menuScene);
                break;
            case SCENE_GAME:
                setScene(gameScene);
                break;
            case SCENE_SPLASH:
                setScene(splashScene);
                break;
            case SCENE_LOADING:
                setScene(loadingScene);
                break;
            default:
                break;
        }
    }

    public void createMenuScene() {
        IAsyncCallback callback = new IAsyncCallback() {

            public void workToDo() {
                ResourcesManager.getInstance().loadMenuResources();
                loadingScene = new LoadingScene();
            }

            public void onComplete() {
                disposeSplashScene();
                menuScene = new MainMenuScene();
                SceneManager.getInstance().setScene(menuScene);
            }
        };

        new LoadSceneManager().execute(callback);
    }

    public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback) {
        ResourcesManager.getInstance().loadSplashScreen();
        splashScene = new SplashScene();
        currentScene = splashScene;
        pOnCreateSceneCallback.onCreateSceneFinished(splashScene);
    }

    private void disposeSplashScene() {
        ResourcesManager.getInstance().unloadSplashScreen();
        splashScene.disposeScene();
        splashScene = null;
    }

    public void loadGameScene(final Engine mEngine) {
        setScene(loadingScene);

        IAsyncCallback callback = new IAsyncCallback() {

            public void workToDo() {
                ResourcesManager.getInstance().unloadMenuTextures();
                ResourcesManager.getInstance().loadGameResources();
            }

            public void onComplete() {
                gameScene = new GameScene();
                setScene(gameScene);
            }
        };

        new LoadSceneManager().execute(callback);
    }

    public void loadMenuSceneFromGameScene(final Engine mEngine) {
        setScene(loadingScene);

        IAsyncCallback callback = new IAsyncCallback() {

            public void workToDo() {
                gameScene.disposeScene();
                ResourcesManager.getInstance().unloadGameTextures();
                ResourcesManager.getInstance().loadMenuTextures();
            }

            public void onComplete() {
                setScene(menuScene);
            }
        };

        new LoadSceneManager().execute(callback);
    }

    //---------------------------------------------
    // GETTERS AND SETTERS
    //---------------------------------------------

    public static SceneManager getInstance() {
        return INSTANCE;
    }

    public SceneType getCurrentSceneType() {
        return currentSceneType;
    }

    public BaseScene getCurrentScene() {
        return currentScene;
    }
}