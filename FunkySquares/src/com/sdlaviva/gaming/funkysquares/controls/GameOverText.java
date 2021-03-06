package com.sdlaviva.gaming.funkysquares.controls;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.Map;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sdlaviva.gaming.funkysquares.model.GameField;

public class GameOverText extends Actor{

	public static final float H_SIZE = 2f;
	public static final float W_SIZE = 8f;
	
	public GameField mainField;

	Map<String, TextureRegion> textureAtlas;
	
	TextureRegion textureGame;
	TextureRegion textureOver;
	public GameOverText(Vector2 position, GameField mainField) {
		this.mainField = mainField;
		
		this.textureAtlas = mainField.textureAtlas;
		
		this.setX(position.x*mainField.ppuX);
		this.setY(position.y*mainField.ppuY);
		this.setWidth(W_SIZE*mainField.ppuX);
		this.setHeight(H_SIZE*mainField.ppuY);
		
		textureGame = new TextureRegion();  
		textureGame = textureAtlas.get("game");
		  
		textureOver = new TextureRegion();  
		textureOver = textureAtlas.get("over");
	}
	@Override
	public void draw(Batch batch, float parentBlending){

		batch.draw(textureGame,
				   getX(),
				   getY(),
				   getWidth()/2,
				   //textureGame.getRegionWidth(),
				   getHeight()
				   );
		batch.draw(textureOver,
				   //getX()+textureGame.getRegionWidth(),
				   getX()+getWidth()/2,
				   getY(),
				   getWidth()/2,
				   //textureOver.getRegionWidth(),
				   getHeight()
				   );
		   
	}	
	
}
