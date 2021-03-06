package com.sdlaviva.gaming.funkysquares.controls;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.Map;
import com.badlogic.gdx.scenes.scene2d.Group;

import com.sdlaviva.gaming.funkysquares.model.*;

public class GameOverCase extends Group {

	public static final float H_SIZE = 2f;
	public static final float W_SIZE = 8f;
	
	public GameField mainField;

	Map<String, TextureRegion> textureAtlas;
	
	TextureRegion textureGame;
	TextureRegion textureOver;
	

	public GameOverCase(Vector2 position, GameField mainField /*, Label debugText*/){
	
		this.textureAtlas = mainField.textureAtlas;
		this.mainField = mainField;
		
		this.setX(position.x*mainField.ppuX);
		this.setY(position.y*mainField.ppuY);
		this.setWidth(W_SIZE*mainField.ppuX);
		this.setHeight(H_SIZE*mainField.ppuY);
		
		visible = false;
		
		textureGame = new TextureRegion();  
		textureGame = textureAtlas.get("game");
		  
		textureOver = new TextureRegion();  
		textureOver = textureAtlas.get("over");
		
		//double k = textureGame.getRegionWidth()/textureOver.getRegionWidth();
		//textureGame.setRegionWidth((int)(ppuX*W_SIZE/(k+1)));
		//textureOver.setRegionWidth((int)(k*textureGame.getRegionWidth()));
		//restart = new RestartButton(new Vector2(mainField.CAMERA_WIDTH-3,1),mainField);
		//this.mainField.addActor(new RestartButton(new Vector2(mainField.CAMERA_WIDTH-3,1),mainField));
		
		
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
	
	private boolean visible;
	
	public void show(){
		visible = true;
	}
	
	public void hide(){
		visible = false;
	}
	
	@Override
	public void draw(Batch batch, float parentBlending){
		this.drawChildren(batch, parentBlending);
		batch.draw(textureGame,
				   getX(),
				   getY(),
				   getWidth()/2,
				   getHeight()
				   );
		batch.draw(textureOver,
				   getX()+getWidth()/2,
				   getY(),
				   getWidth()/2,
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
