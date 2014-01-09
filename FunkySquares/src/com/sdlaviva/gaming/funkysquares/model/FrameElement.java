package com.sdlaviva.gaming.funkysquares.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.Batch;
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
	
	private TextureRegion cornerTex;
	private TextureRegion edgeTex;

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
		
		switch (frameCode) 
		{
			case CORNER_UP_LEFT:
				cornerTex = new TextureRegion(textureAtlas.get("corner"));
				edgeTex = null;
				cornerTex.flip(false, true);
				this.setX(position.x * ppuX);
				this.setY(position.y * ppuY);
				this.setWidth(SIZE*ppuX);
				this.setHeight(SIZE*ppuY);
				
				break;
			case CORNER_DOWN_LEFT:
				cornerTex = new TextureRegion(textureAtlas.get("corner"));
				edgeTex = null;
				this.setX(position.x * ppuX);
				this.setY(position.y * ppuY);
				this.setWidth(SIZE*ppuX);
				this.setHeight(SIZE*ppuY);
				
				break;
			case CORNER_UP_RIGHT:
				cornerTex = new TextureRegion(textureAtlas.get("corner"));
				edgeTex = null;
				cornerTex.flip(true,true);
				this.setX(position.x * ppuX);
				this.setY(position.y * ppuY);
				this.setWidth(SIZE*ppuX);
				this.setHeight(SIZE*ppuY);
				
				break;
			case CORNER_DOWN_RIGHT:
				cornerTex = new TextureRegion(textureAtlas.get("corner"));
				edgeTex = null;
				cornerTex.flip(true, false);
				this.setX(position.x * ppuX);
				this.setY(position.y * ppuY);
				this.setWidth(SIZE*ppuX);
				this.setHeight(SIZE*ppuY);
				
				break;
			case EDGE_DOWN_LEFT_H:
				edgeTex = new TextureRegion(textureAtlas.get("edge"));
				cornerTex = null;
				this.setX(position.x * ppuX);
				this.setY(position.y * ppuY);
				this.setWidth(SIZE*ppuX);
				this.setHeight(SIZE*ppuY);
				
				break;
			case EDGE_UP_LEFT_H:
				edgeTex = new TextureRegion(textureAtlas.get("edge"));
				cornerTex = null;
				edgeTex.flip(false, true);
				this.setX(position.x * ppuX);
				this.setY(position.y * ppuY);
				this.setWidth(SIZE*ppuX);
				this.setHeight(SIZE*ppuY);
				
				break;
			case EDGE_UP_LEFT_V:
				edgeTex = new TextureRegion(textureAtlas.get("edge"));
				cornerTex = null;
				this.setX(position.x * ppuX);
				this.setY(position.y * ppuY);
				this.setWidth(SIZE*ppuY);
				this.setHeight(SIZE*ppuX);
				
				break;
			case EDGE_DOWN_LEFT_V:
				edgeTex = new TextureRegion(textureAtlas.get("edge"));
				cornerTex = null;
				edgeTex.flip(true, false);
				this.setX(position.x * ppuX);
				this.setY(position.y * ppuY);
				this.setWidth(SIZE*ppuY);
				this.setHeight(SIZE*ppuX);
				
				break;	
			case EDGE_UP_RIGHT_H:
				edgeTex = new TextureRegion(textureAtlas.get("edge"));
				cornerTex = null;
				edgeTex.flip(true, true);
				this.setX(position.x * ppuX);
				this.setY(position.y * ppuY);
				this.setWidth(SIZE*ppuX);
				this.setHeight(SIZE*ppuY);
				
				break;
			case EDGE_DOWN_RIGHT_H:
				edgeTex = new TextureRegion(textureAtlas.get("edge"));
				cornerTex = null;
				edgeTex.flip(true, false);
				this.setX(position.x * ppuX);
				this.setY(position.y * ppuY);
				this.setWidth(SIZE*ppuX);
				this.setHeight(SIZE*ppuY);
				
				break;
			case EDGE_UP_RIGHT_V:
				edgeTex = new TextureRegion(textureAtlas.get("edge"));
				cornerTex = null;
				edgeTex.flip(false, true);
				this.setX(position.x * ppuX);
				this.setY(position.y * ppuY);
				this.setWidth(SIZE*ppuX);
				this.setHeight(SIZE*ppuY);
				
				break;
			case EDGE_DOWN_RIGHT_V:
				edgeTex = new TextureRegion(textureAtlas.get("edge"));
				cornerTex = null;
				edgeTex.flip(true, true);
				this.setX(position.x * ppuX);
				this.setY(position.y * ppuY);
				this.setWidth(SIZE*ppuX);
				this.setHeight(SIZE*ppuY);
				
				break;	
		}	
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public Vector2 getPosition () {
		return position;
	}

	@Override
	public void draw(Batch batch, float parentAlfa){
		switch (frameCode)
		{
		case CORNER_UP_LEFT:
			batch.draw(cornerTex,
					   getX(),
					   getY(),
					   getWidth(),
					   getHeight());
			break;		

			case CORNER_DOWN_LEFT:
				batch.draw(cornerTex,
						   getX(),
						   getY(),
						   getWidth(),
						   getHeight());
				break;
			case CORNER_UP_RIGHT:
				batch.draw(cornerTex,
						   getX(),
						   getY(),
						   getWidth(),
						   getHeight());
				break;

			case CORNER_DOWN_RIGHT:
				batch.draw(cornerTex,
						   getX(),
						   getY(),
						   getWidth(),
						   getHeight());

				break;
			case EDGE_DOWN_LEFT_H:
				batch.draw(edgeTex,
						   getX(),
						   getY(),
						   getWidth(),
						   getHeight());
				break;
			case EDGE_UP_LEFT_H:
				batch.draw(edgeTex,
						   getX(),
						   getY(),
						   getWidth(),
						   getHeight());
				break;

			case EDGE_UP_LEFT_V:
				batch.draw(edgeTex,
						   getX(),
						   getY(),
						   getWidth()/2, 
						   getHeight()/2,
						   getWidth(),
						   getHeight(),
						   1f,1f,
						   0,true);
						   
				break;
			case EDGE_DOWN_LEFT_V:		
				batch.draw(edgeTex,
						   getX(),
						   getY(),
						   getWidth()/2, 
						   getHeight()/2,
						   getWidth(),
						   getHeight(),
						   1f,1f,
						   0,true);
				break;	
			case EDGE_UP_RIGHT_H:
				batch.draw(edgeTex,
						   getX(),
						   getY(),
						   getWidth(),
						   getHeight());
				break;

			case EDGE_DOWN_RIGHT_H:
				batch.draw(edgeTex,
						   getX(),
						   getY(),
						   getWidth(),
						   getHeight());
				break;

			case EDGE_UP_RIGHT_V:
				batch.draw(edgeTex,
						   getX(),
						   getY(),
						   getWidth()/2, 
						   getHeight()/2,
						   getWidth(),
						   getHeight(),
						   1f,1f,
						   0,true);
				break;
			case EDGE_DOWN_RIGHT_V:
				batch.draw(edgeTex,
						   getX(),
						   getY(),
						   getWidth()/2, 
						   getHeight()/2,
						   getWidth(),
						   getHeight(),
						   1f,1f,
						   0,true);
				break;	
		}
	}
	
	
	//@Override
	public Actor hit(float x, float y){
		return null;
	}
				
}
