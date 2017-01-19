package com.example.chintu.andenginetemplate.scene;

import com.example.chintu.andenginetemplate.base.BaseScene;
import com.example.chintu.andenginetemplate.manager.SceneManager.SceneType;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.util.adt.color.Color;



public class LoadingScene extends BaseScene
{
	@Override
	public void createScene()
	{
		setBackground(new Background(Color.WHITE));
		attachChild(new Text(400, 240, mResourceManager.font, "Loading...", mVertexbufferObjectManager));
	}

	@Override
	public void onBackKeyPressed()
	{
		return;
	}

	@Override
	public SceneType getSceneType()
	{
		return SceneType.SCENE_LOADING;
	}

	@Override
	public void disposeScene()
	{

	}
}