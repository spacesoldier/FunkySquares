package com.sdlaviva.gaming.funkysquares.controls;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Map;
import com.sdlaviva.gaming.funkysquares.model.*;

public class ControlGroup extends Group
{
	private float ppuX;
	private float ppuY;
	
	private float centerX;
	private float centerY;


	private GameField mainField;
	
	Map<String, TextureRegion> textureAtlas;
	
	public ControlGroup(GameField field, Map<String, TextureRegion> textureAtlas, float centerX, float centerY){
		
		this.mainField = field;
		this.textureAtlas = textureAtlas;
		this.ppuX = mainField.ppuX;
		this.ppuY = mainField.ppuY;
		this.centerX = centerX; // GameField.CAMERA_WIDTH/2;
		this.centerY = centerY; // GameField.CAMERA_HEIGHT/2;
		
//		this.debugText = mainField.debugLabel;
		
		this.addActor(new ControlButton(new Vector2(this.centerX,this.centerY),ppuX,ppuY,textureAtlas,ControlButton.Direction.UP, mainField /*, debugText*/));
		this.addActor(new ControlButton(new Vector2(this.centerX,this.centerY),ppuX,ppuY,textureAtlas,ControlButton.Direction.LEFT, mainField /*, debugText*/));
		this.addActor(new ControlButton(new Vector2(this.centerX,this.centerY),ppuX,ppuY,textureAtlas,ControlButton.Direction.DOWN, mainField /*, debugText*/));
		this.addActor(new ControlButton(new Vector2(this.centerX,this.centerY),ppuX,ppuY,textureAtlas,ControlButton.Direction.RIGHT, mainField /*, debugText*/));
		
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlfa){
		this.drawChildren(batch, parentAlfa);		
	}
}
