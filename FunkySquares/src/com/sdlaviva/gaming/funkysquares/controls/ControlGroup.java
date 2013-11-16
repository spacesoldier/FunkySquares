package com.sdlaviva.gaming.funkysquares.controls;

import com.badlogic.gdx.scenes.scene2d.Group;
//import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.scenes.scene2d.ui.*;
import java.util.Map;
import com.sdlaviva.gaming.funkysquares.model.*;

public class ControlGroup extends Group
{
	private float ppuX;
	private float ppuY;
	
	private float centerX;
	private float centerY;

//	private Label debugText;

	private GameField mainField;
	
	Map<String, TextureRegion> textureAtlas;
	
	public ControlGroup(GameField field, Map<String, TextureRegion> textureAtlas){
		
		this.mainField = field;
		this.textureAtlas = textureAtlas;
		this.ppuX = mainField.ppuX;
		this.ppuY = mainField.ppuY;
		this.centerX = GameField.CAMERA_WIDTH/2;
		this.centerY = GameField.CAMERA_HEIGHT/2;
		
//		this.debugText = mainField.debugLabel;
		
		this.addActor(new ControlButton(new Vector2(centerX-ControlButton.H_SIZE/2,centerY+4),ppuX,ppuY,textureAtlas,ControlButton.Direction.UP, mainField /*, debugText*/));
		this.addActor(new ControlButton(new Vector2(centerX-4,centerY-ControlButton.W_SIZE),ppuX,ppuY,textureAtlas,ControlButton.Direction.LEFT, mainField /*, debugText*/));
		this.addActor(new ControlButton(new Vector2(centerX-ControlButton.W_SIZE,centerY-4),ppuX,ppuY,textureAtlas,ControlButton.Direction.DOWN, mainField /*, debugText*/));
		this.addActor(new ControlButton(new Vector2(centerX+4,centerY-ControlButton.W_SIZE),ppuX,ppuY,textureAtlas,ControlButton.Direction.RIGHT, mainField /*, debugText*/));
		
		
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlfa){
		this.drawChildren(batch, parentAlfa);		
	}
}
