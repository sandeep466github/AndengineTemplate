package com.example.chintu.andenginetemplate.scene;

import com.example.chintu.andenginetemplate.base.BaseScene;
import com.example.chintu.andenginetemplate.manager.SceneManager;
import com.example.chintu.andenginetemplate.manager.SceneManager.SceneType;

import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;


public class MainMenuScene extends BaseScene implements IOnMenuItemClickListener
{
	//---------------------------------------------
	// VARIABLES
	//---------------------------------------------
	
	private MenuScene menuChildScene;
	
	private final int MENU_PLAY = 0;
	private final int MENU_OPTIONS = 1;
	
	//---------------------------------------------
	// METHODS FROM SUPERCLASS
	//---------------------------------------------

	@Override
	public void createScene()
	{
		createBackground();
		createMenuChildScene();
	}

	@Override
	public void onBackKeyPressed()
	{
		System.exit(0);
	}

	@Override
	public SceneType getSceneType()
	{
		return SceneType.SCENE_MENU;
	}
	

	@Override
	public void disposeScene()
	{
		// TODO Auto-generated method stub
	}
	
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY)
	{
		switch(pMenuItem.getID())
		{
			case MENU_PLAY:
				//Load Game Scene!
				SceneManager.getInstance().loadGameScene(mEngine);
				return true;
			case MENU_OPTIONS:
				return true;
			default:
				return false;
		}
	}
	
	//---------------------------------------------
	// CLASS LOGIC
	//---------------------------------------------
	
	private void createBackground()
	{
		Sprite backgroundSprite = new Sprite(mScreenWidth/2, mScreenHeight/2, mResourceManager.menu_background_region, mVertexbufferObjectManager);
		backgroundSprite.setWidth(mScreenWidth);
		backgroundSprite.setHeight(mScreenHeight);
        attachChild(backgroundSprite);
	}
	
	private void createMenuChildScene()
	{
		menuChildScene = new MenuScene(mCamera);
		menuChildScene.setPosition(mScreenWidth/2, mScreenHeight/2);
		
		final IMenuItem playMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY, mResourceManager.play_region, mVertexbufferObjectManager), 1.2f, 1);
		final IMenuItem optionsMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_OPTIONS, mResourceManager.options_region, mVertexbufferObjectManager), 1.2f, 1);
		
		menuChildScene.addMenuItem(playMenuItem);
		menuChildScene.addMenuItem(optionsMenuItem);
		
		menuChildScene.buildAnimations();
		menuChildScene.setBackgroundEnabled(false);
		
		playMenuItem.setPosition(0, playMenuItem.getHeight());
		optionsMenuItem.setPosition(0,  - optionsMenuItem.getHeight());
		
		menuChildScene.setOnMenuItemClickListener(this);
		
		setChildScene(menuChildScene);
	}
}