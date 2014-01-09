package com.sdlaviva.gaming.funkysquares.model;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.math.Vector2;
import java.util.Map;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class FieldFrame extends Group
{

//	private float ppuX;
//	private float ppuY;
	private float center_X;
	private float center_Y;
//	private Map<String,TextureRegion> textureAtlas;
	

	public FieldFrame(Vector2 position, float ppuX, float ppuY, Map<String, TextureRegion> textureAtlas) {
//		this.ppuX = ppuX;
//		this.ppuY = ppuY;
//		this.textureAtlas = textureAtlas;
		this.center_X = position.x;
		this.center_Y = position.y;
		
		this.addActor(new FrameElement(new Vector2(center_X-4,center_Y+2),ppuX,ppuY,textureAtlas,FrameElement.FrameType.CORNER_UP_LEFT));
		this.addActor(new FrameElement(new Vector2(center_X-4,center_Y-4),ppuX,ppuY,textureAtlas,FrameElement.FrameType.CORNER_DOWN_LEFT));	

		this.addActor(new FrameElement(new Vector2(center_X-2,center_Y+2),ppuX,ppuY,textureAtlas,FrameElement.FrameType.EDGE_UP_LEFT_H));
		this.addActor(new FrameElement(new Vector2(center_X-2,center_Y-4),ppuX,ppuY,textureAtlas,FrameElement.FrameType.EDGE_DOWN_LEFT_H));	
		this.addActor(new FrameElement(new Vector2(center_X,center_Y+2),ppuX,ppuY,textureAtlas,FrameElement.FrameType.EDGE_UP_RIGHT_H));
		this.addActor(new FrameElement(new Vector2(center_X,center_Y-4),ppuX,ppuY,textureAtlas,FrameElement.FrameType.EDGE_DOWN_RIGHT_H));	

		this.addActor(new FrameElement(new Vector2(center_X-4,center_Y),ppuX,ppuY,textureAtlas,FrameElement.FrameType.EDGE_UP_LEFT_V));
		this.addActor(new FrameElement(new Vector2(center_X-4,center_Y-2),ppuX,ppuY,textureAtlas,FrameElement.FrameType.EDGE_DOWN_LEFT_V));	
		this.addActor(new FrameElement(new Vector2(center_X+2,center_Y),ppuX,ppuY,textureAtlas,FrameElement.FrameType.EDGE_UP_RIGHT_V));
		this.addActor(new FrameElement(new Vector2(center_X+2,center_Y-2),ppuX,ppuY,textureAtlas,FrameElement.FrameType.EDGE_DOWN_RIGHT_V));	

		this.addActor(new FrameElement(new Vector2(center_X+2,center_Y+2),ppuX,ppuY,textureAtlas,FrameElement.FrameType.CORNER_UP_RIGHT));
		this.addActor(new FrameElement(new Vector2(center_X+2,center_Y-4),ppuX,ppuY,textureAtlas,FrameElement.FrameType.CORNER_DOWN_RIGHT));
		
		for (Actor actor : this.getChildren())
			actor.setTouchable(Touchable.disabled);
	}
	
	@Override
	public void draw(Batch batch, float parentAlfa){
		this.drawChildren(batch, parentAlfa);
	}
	
	//@Override
	public boolean touchDown(float x, float y, int pointer){
		return false;
	}

}
