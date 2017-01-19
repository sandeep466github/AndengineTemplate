package com.example.chintu.andenginetemplate.base;

import android.app.Activity;

import com.example.chintu.andenginetemplate.manager.ResourcesManager;
import com.example.chintu.andenginetemplate.manager.SceneManager.SceneType;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;


/**
 * @author Mateusz Mysliwiec
 * @author www.matim-dev.com
 * @version 1.0
 */
public abstract class BaseScene extends Scene
{
	//---------------------------------------------
	// VARIABLES
	//---------------------------------------------
	
	protected Engine mEngine;
	protected Activity mActivity;
	protected ResourcesManager mResourceManager;
	protected VertexBufferObjectManager mVertexbufferObjectManager;
	protected BoundCamera mCamera;
	protected int mScreenWidth, mScreenHeight;
	
	//---------------------------------------------
	// CONSTRUCTOR
	//---------------------------------------------
	
	public BaseScene()
	{
		this.mResourceManager = ResourcesManager.getInstance();
		this.mEngine = mResourceManager.mEngine;
		this.mActivity = mResourceManager.mActivity;
		this.mVertexbufferObjectManager = mResourceManager.mVertexBufferObjectManager;
		this.mCamera = mResourceManager.mCamera;
        this.mScreenWidth = mResourceManager.mScreenWidth;
        this.mScreenHeight = mResourceManager.mScreenHeight;
		createScene();
	}
	
	//---------------------------------------------
	// ABSTRACTION
	//---------------------------------------------
	
	public abstract void createScene();
	
	public abstract void onBackKeyPressed();
	
	public abstract SceneType getSceneType();
	
	public abstract void disposeScene();
}