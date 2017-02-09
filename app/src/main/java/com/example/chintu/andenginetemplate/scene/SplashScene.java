package com.example.chintu.andenginetemplate.scene;

import com.example.chintu.andenginetemplate.base.BaseScene;
import com.example.chintu.andenginetemplate.manager.SceneManager.SceneType;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;


public class SplashScene extends BaseScene
{
	private Sprite splash;
	
	@Override
	public void createScene()
	{
		splash = new Sprite(0, 0, mResourceManager.splash_region, mVertexbufferObjectManager)
    	{
    		@Override
            protected void preDraw(GLState pGLState, Camera pCamera) 
    		{
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
    	};
    	
    	splash.setWidth(mScreenWidth);
		splash.setHeight(mScreenHeight);
    	splash.setPosition(mScreenWidth/2, mScreenHeight/2);
    	attachChild(splash);
	}

	@Override
	public void onBackKeyPressed()
	{
		return;
	}

	@Override
	public SceneType getSceneType()
	{
		return SceneType.SCENE_SPLASH;
	}

	@Override
	public void disposeScene()
	{
		splash.detachSelf();
		splash.dispose();
		this.detachSelf();
		this.dispose();
	}
}