package com.sdlaviva.gaming.funkysquares.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import java.util.Map;

public class FrameElement extends Actor
{

	private float ppuX;
	private float ppuY;

	public enum FrameType {
		CORNER_UP_LEFT,
		CORNER_DOWN_LEFT,
		CORNER_UP_RIGHT,
		CORNER_DOWN_RIGHT,
		EDGE_UP_LEFT_H,
		EDGE_DOWN_LEFT_H,
		EDGE_UP_RIGHT_H,
		EDGE_DOWN_RIGHT_H,
		EDGE_UP_LEFT_V,
		EDGE_DOWN_LEFT_V,
		EDGE_UP_RIGHT_V,
		EDGE_DOWN_RIGHT_V
	}
	
	public static final float SIZE = 2f;
		
	Vector2 position = new Vector2();
	Rectangle bounds = new Rectangle();
	private FrameType frameCode;

	private Map<String, TextureRegion> textureAtlas;

	public FrameElement(Vector2 position, float ppuX, float ppuY, Map<String, TextureRegion> textureAtlas, FrameType frameType)
	{
		this.ppuX = ppuX;
		this.ppuY = ppuY;
		this.textureAtlas = textureAtlas;
		this.position = position;
		this.frameCode = frameType;
		
		addListener(new InputListener (){

				public boolean touchDown(InputEvent event,float x, float y, int pointer, int button){

					return false;
				}

				public void touchDragged(InputEvent event,float x, float y, int pointer){

				}

				public void touchUp(InputEvent event,int x, int y, int pointer, int button){


				}		
			});
		
			
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public Vector2 getPosition () {
		return position;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlfa){
		switch (frameCode)
		{
			case CORNER_UP_LEFT:
				batch.draw(textureAtlas.get("corner").getTexture(),
						   position.x * ppuX,
						   position.y * ppuY,
						   SIZE*ppuX,
						   SIZE*ppuY,
						   0,0,
						   textureAtlas.get("corner").getTexture().getWidth()/2,
						   textureAtlas.get("corner").getTexture().getHeight(),
						   false,true);
				break;
			case CORNER_DOWN_LEFT:
				batch.draw(textureAtlas.get("corner"),
						   position.x * ppuX,
						   position.y * ppuY,
						   SIZE*ppuX,
						   SIZE*ppuY);
				break;
			case CORNER_UP_RIGHT:
				batch.draw(textureAtlas.get("corner").getTexture(),
						   position.x * ppuX,
						   position.y * ppuY,
						   SIZE*ppuX,
						   SIZE*ppuY,
						   0,0,
						   textureAtlas.get("corner").getTexture().getWidth()/2,
						   textureAtlas.get("corner").getTexture().getHeight(),
						   true,true);
				break;
			case CORNER_DOWN_RIGHT:
				batch.draw(textureAtlas.get("corner").getTexture(),
						   position.x * ppuX,
						   position.y * ppuY,
						   SIZE*ppuX,
						   SIZE*ppuY,
						   0,0,
						   textureAtlas.get("corner").getTexture().getWidth()/2,
						   textureAtlas.get("corner").getTexture().getHeight(),
						   true,false);
				break;
			case EDGE_DOWN_LEFT_H:
				batch.draw(textureAtlas.get("edge"),
						   position.x * ppuX,
						   position.y * ppuY,
						   SIZE*ppuX,
						   SIZE*ppuY);
				break;
			case EDGE_UP_LEFT_H:
				batch.draw(textureAtlas.get("edge").getTexture(),
						   position.x * ppuX,
						   position.y * ppuY,
						   SIZE*ppuX,
						   SIZE*ppuY,
						   textureAtlas.get("edge").getTexture().getWidth()/2,
						   0,
						   textureAtlas.get("edge").getTexture().getWidth()/2,
						   textureAtlas.get("edge").getTexture().getHeight(),
						   false, true);
				break;
			case EDGE_UP_LEFT_V:
				batch.draw(textureAtlas.get("edge").getTexture(),
						   position.x * ppuX,
						   position.y * ppuY + SIZE*ppuY,
						   0, 
						   0,
						   SIZE*ppuY,
						   SIZE*ppuX,
						   1,1,
						   -90,
						   textureAtlas.get("edge").getTexture().getWidth()/2,
						   0,
						   textureAtlas.get("edge").getTexture().getWidth()/2,
						   textureAtlas.get("edge").getTexture().getHeight(),
						   false,false);
				break;
			case EDGE_DOWN_LEFT_V:
				batch.draw(textureAtlas.get("edge").getTexture(),
						   position.x * ppuX,
						   position.y * ppuY + SIZE*ppuY,
						   0, 
						   0,
						   SIZE*ppuY,
						   SIZE*ppuX,
						   1,1,
						   -90,
						   textureAtlas.get("edge").getTexture().getWidth()/2,
						   0,
						   textureAtlas.get("edge").getTexture().getWidth()/2,
						   textureAtlas.get("edge").getTexture().getHeight(),
						   true,false);
				break;	
			case EDGE_UP_RIGHT_H:
				batch.draw(textureAtlas.get("edge").getTexture(),
						   position.x * ppuX,
						   position.y * ppuY,
						   SIZE*ppuX,
						   SIZE*ppuY,
						   textureAtlas.get("edge").getTexture().getWidth()/2,
						   0,
						   textureAtlas.get("edge").getTexture().getWidth()/2,
						   textureAtlas.get("edge").getTexture().getHeight(),
						   true, true);
				break;
			case EDGE_DOWN_RIGHT_H:
				batch.draw(textureAtlas.get("edge").getTexture(),
						   position.x * ppuX,
						   position.y * ppuY,
						   SIZE*ppuX,
						   SIZE*ppuY,
						   textureAtlas.get("edge").getTexture().getWidth()/2,
						   0,
						   textureAtlas.get("edge").getTexture().getWidth()/2,
						   textureAtlas.get("edge").getTexture().getHeight(),
						   true, false);
				break;
			case EDGE_UP_RIGHT_V:
				batch.draw(textureAtlas.get("edge").getTexture(),
						   position.x * ppuX + SIZE*ppuX,
						   position.y * ppuY,
						   0, 
						   0,
						   SIZE*ppuY,
						   SIZE*ppuX,
						   1,1,
						   90,
						   textureAtlas.get("edge").getTexture().getWidth()/2,
						   0,
						   textureAtlas.get("edge").getTexture().getWidth()/2,
						   textureAtlas.get("edge").getTexture().getHeight(),
						   true,false);
				break;
			case EDGE_DOWN_RIGHT_V:
				batch.draw(textureAtlas.get("edge").getTexture(),
						   position.x * ppuX + SIZE*ppuX,
						   position.y * ppuY,
						   0, 
						   0,
						   SIZE*ppuY,
						   SIZE*ppuX,
						   1,1,
						   90,
						   textureAtlas.get("edge").getTexture().getWidth()/2,
						   0,
						   textureAtlas.get("edge").getTexture().getWidth()/2,
						   textureAtlas.get("edge").getTexture().getHeight(),
						   false,false);
				break;	
		}
	}
	
	
	//@Override
	public Actor hit(float x, float y){
		return null;
	}
				
}
