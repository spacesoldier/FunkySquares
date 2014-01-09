package com.sdlaviva.gaming.funkysquares.model;

//import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Actor;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScoreNumber extends Actor
{
	private int value;
	
//	private GameField mainField;
	private Map <String, TextureRegion> textureAtlas;
	
	public static final float W_SIZE = 0.5f;
	public static final float H_SIZE = 1f;
	
	private float ppuX;
	private float ppuY;
	
	public ScoreNumber(GameField mainField, Map<String, TextureRegion> textureAtlas,float posX, float posY, int value)
	{
		this.textureAtlas = textureAtlas;
//		this.mainField = mainField;
		this.value = value;
		ppuX = mainField.ppuX;
		ppuY = mainField.ppuY;
		this.setX(posX);
		this.setY(posY);
		this.setWidth(W_SIZE*ppuX);
		this.setHeight(H_SIZE*ppuY);
	}
	
	public void setValue(int newValue){
		if(newValue>=0 && newValue<=9){
			this.value = newValue;
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlfa){
		
		if ( value>=0 && value<=9 )
			batch.draw(textureAtlas.get("num"+value/*+String.valueOf(value)*/),getX(),getY(),getWidth(),getHeight());
		
	}
}
