package com.sdlaviva.gaming.funkysquares.controls;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.Map;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

import com.sdlaviva.gaming.funkysquares.controls.ControlButton.BtnState;
import com.sdlaviva.gaming.funkysquares.model.*;

public class GameOverCase extends Actor {

	public static final float H_SIZE = 2f;
	public static final float W_SIZE = 8f;
	
	public BtnState currentState;
	
	public GameField mainField;
	
	public void setCurrentState(BtnState newState){
		this.currentState = newState;
	}
	

	Map<String, TextureRegion> textureAtlas;
	
	TextureRegion textureGame;
	TextureRegion textureOver;

	
	public GameOverCase(Vector2 position, float ppuX, float ppuY, Map<String, TextureRegion> textureAtlas, GameField mainField /*, Label debugText*/){
	
		this.textureAtlas = textureAtlas;
		this.mainField = mainField;
		currentState = BtnState.RELEASED;
		
		this.setX(position.x*ppuX);
		this.setY(position.y*ppuY);
		this.setWidth(W_SIZE*ppuX);
		this.setHeight(H_SIZE*ppuY);
		
		textureGame = new TextureRegion();  
		textureGame = textureAtlas.get("game");
		  
		textureOver = new TextureRegion();  
		textureOver = textureAtlas.get("over");
		
		double k = textureGame.getRegionWidth()/textureOver.getRegionWidth();
		textureGame.setRegionWidth((int)(ppuX*W_SIZE/(k+1)));
		textureOver.setRegionWidth((int)(k*textureGame.getRegionWidth()));
		
		/*
		addListener(new InputListener (){
			
			public boolean touchDown(InputEvent event,float x, float y, int pointer, int button){
				currentState = BtnState.PRESSED;
									
				return true;
			}
			
			public void touchUp(InputEvent event,int x, int y, int pointer, int button){
					currentState = BtnState.RELEASED;
				}		
			});
		*/
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentBlending){

		batch.draw(textureGame,
				   getX(),
				   getY(),
				   textureGame.getRegionWidth(),
				   getHeight()
				   );
		batch.draw(textureOver,
				   getX()+textureGame.getRegionWidth(),
				   getY(),
				   textureOver.getRegionWidth(),
				   getHeight()
				   );
				   
	}
	
	
	//@Override
	/*
	public Actor hit(float x, float y){
		return x>0 && x<this.getWidth() && y>0 && y< this.getHeight() ? this : null;
	}
	*/

	
}