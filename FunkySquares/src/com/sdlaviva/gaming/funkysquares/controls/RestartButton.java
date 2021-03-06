package com.sdlaviva.gaming.funkysquares.controls;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.Map;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.sdlaviva.gaming.funkysquares.controls.ControlButton.BtnState;

import com.sdlaviva.gaming.funkysquares.model.*;

public class RestartButton extends Actor {

	public static final float H_SIZE = 2f;
	public static final float W_SIZE = 2f;
	
	public BtnState currentState;
	
	public GameField mainField;
	
	public void setCurrentState(BtnState newState){
		this.currentState = newState;
	}
	
	private void restart(){
		mainField.reset();
	}
	Map<String, TextureRegion> textureAtlas;

	private boolean visible;
	
	public void show(){
		visible = true;
	}
	
	public void hide(){
		visible = false;
	}
	
	public RestartButton(Vector2 position, GameField mainField /*, Label debugText*/){
	
		this.textureAtlas = mainField.textureAtlas;
		this.mainField = mainField;
		currentState = BtnState.RELEASED;
		
		this.setX(position.x*mainField.ppuX);
		this.setY(position.y*mainField.ppuY);
		this.setWidth(H_SIZE*mainField.ppuX);
		this.setHeight(W_SIZE*mainField.ppuY);
		
		visible = false;
		
		addListener(new InputListener (){
			
			public boolean touchDown(InputEvent event,float x, float y, int pointer, int button){
				currentState = BtnState.PRESSED;
				restart();					
				return true;
			}
			
			public void touchUp(InputEvent event,int x, int y, int pointer, int button){
					//restart();
					currentState = BtnState.RELEASED;
				}		
			});
	}
	
	@Override
	public void draw(Batch batch, float parentBlending){

		if (this!=mainField.selectedActor) currentState = BtnState.RELEASED;
			
		TextureRegion textureRegion = new TextureRegion();
		if (currentState == BtnState.RELEASED)  
			textureRegion = textureAtlas.get("restartUp");
		if (currentState == BtnState.PRESSED)  
			textureRegion = textureAtlas.get("restartDown");

		batch.draw(textureRegion,
				   getX(),
				   getY(),
				   getWidth(),
				   getHeight()
				   );
		
	}
	
	
	@Override
	public Actor hit(float x, float y, boolean touchable){
		return x>0 && x<this.getWidth() && y>0 && y< this.getHeight() ? this : null;
	}

}
