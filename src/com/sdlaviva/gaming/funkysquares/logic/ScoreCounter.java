package com.sdlaviva.gaming.funkysquares.logic;

import com.badlogic.gdx.scenes.scene2d.Group;
//import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Map;
import com.sdlaviva.gaming.funkysquares.model.*;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;

public class ScoreCounter extends Group
{
	private int value;
//	private GameField mainField;
//	private Map <String, TextureRegion> textureAtlas;
	private ArrayList<ScoreNumber> scoreArray = new ArrayList<ScoreNumber>();
	private float ppuX;
	private float ppuY;

	public ScoreCounter(GameField mainField, Map <String, TextureRegion> textureAtlas, int value)
	{
//		this.mainField = mainField;
//		this.textureAtlas = textureAtlas;
		this.ppuX = mainField.ppuX;
		this.ppuY = mainField.ppuY;
		this.value = value;
		for (int i=0; i<4; i++){
			scoreArray.add(new ScoreNumber(mainField,textureAtlas,
							mainField.getWidth()- ScoreNumber.W_SIZE*(i+4.2f)*ppuX,
							mainField.getHeight()-ScoreNumber.H_SIZE*2.5f*ppuY,
							0 ));
			this.addActor(scoreArray.get(i));				
		}
		this.addActor(new ScoreLabel(mainField,textureAtlas,
									 mainField.getWidth() - ScoreLabel.W_SIZE*1.8f*ppuX,
									 mainField.getHeight() - ScoreLabel.H_SIZE*1.2f*ppuY
									));
		setValue(this.value);							
	}
	
	public void setValue(int newValue){
		this.value = newValue;
		String str = String.valueOf(value);
		if (str.length()<=scoreArray.size())
			for (int i=str.length()-1; i>=0; i--)
				scoreArray.get(i).setValue(Character.getNumericValue(str.charAt(str.length()-1-i)));
		else for (int i=0; i<scoreArray.size(); i++)
				scoreArray.get(i).setValue(9);		
				
	}
	
	public int getValue(){
		return this.value;
	}
	
	public void plusValue(int plusV){
		setValue(value+plusV);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlfa){
		this.drawChildren(batch, parentAlfa);

	}
		
}