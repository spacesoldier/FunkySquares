package com.sdlaviva.gaming.funkysquares;
import com.badlogic.gdx.Game;
import com.sdlaviva.gaming.funkysquares.screens.*;

//import android.content.Intent;

public class FunkySquaresGame extends Game
{
	public GameScreen mainGameScreen;
	@Override
	public void create(){
		mainGameScreen = new GameScreen();
		setScreen(mainGameScreen);
	};
	
	//MainActivity activity;
	
	//public void goHome(){
		
	//}
	
	public void render() {
		super.render();
	}
	
	@Override
	public void resize(int width, int height){
	
	}

}

