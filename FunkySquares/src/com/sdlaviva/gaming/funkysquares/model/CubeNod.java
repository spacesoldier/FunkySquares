package com.sdlaviva.gaming.funkysquares.model;

//import com.sdlaviva.gaming.funkysquares.model.GameField;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.*;
import java.util.Random;
import java.lang.Math;

public class CubeNod extends Actor
{

	public void setCurrState(State currState)
	{
		this.currState = currState;
	}

	public State getCurrState()
	{
		return currState;
	}

	public void setGamePos(Vector2 gamePos)
	{
		this.gamePos = gamePos;
	}

	public void setGamePos(int x, int y)
	{
		this.gamePos.x = x;
		this.gamePos.y = y;
	}
	
	public Vector2 getGamePos()
	{
		return gamePos;
	}
	public static enum State {
		NONE, IN_GAME, REMOVE
	}
	
	private boolean steady;
	private int colorCode;
	
	public boolean isSteady(){
		//return (steady && (currState == State.IN_GAME || currState == State.NONE)) ;
		return steady;
	}
	public static enum Direction {
		LEFT, RIGHT, UP, DOWN;
	}
	
	public static enum ColorDef {
		BLUE, 
		GREEN, 
		GREY, 
		PINK, 
		YELLOW, 
		BOMB;
		
		private static Random randomColor = new Random(new Date().getTime());

		//private static SecureRandom randomColor = new SecureRandom();
		
		
		public static ColorDef getRandom(){
			

			
//			int result = Math.round(randomColor.nextInt((values().length-1)*1000)/1000);
			int result = Math.abs(Math.round((int)(randomColor.nextDouble()*(values().length-1))));//*(values().length-1)/10);
			
						
			return values()[result];
		}
		
	}
	
//	private int node_id;
	private ColorDef nodeColor;
	private State currState;
	private float alpha;
	private float oldAlpha;

	public int getNodeColor(){
		return colorCode;
	}
	
	private Vector2 gamePos;

	private float ppuX; 
	private float ppuY;
//	private float cX;
//	private float cY;
//	private float fSize;
	
	private Vector2 position;
	private Vector2 newPosition;
	private Vector2 oldPosition;
	
	public static final float SIZE = 1f;
	
	private Map<String, TextureRegion> textureAtlas;
	
	public CubeNod( Vector2 gamePosition, 
					float ppuX, 
					float ppuY, 
					Map<String, TextureRegion> textureAtlas, 
					int id, 
					float centerX, 
					float centerY, 
					float FIELD_SIZE, 
					ColorDef nodeColor)
	{

		this.ppuX = ppuX;
		this.ppuY = ppuY;
		this.textureAtlas = textureAtlas;
//		this.cX = centerX;
//		this.cY= centerY;
//		this.fSize = FIELD_SIZE;
		gamePos = new Vector2(gamePosition.x, gamePosition.y);
		this.position = new Vector2(centerX-FIELD_SIZE/2-1+gamePos.x,centerY-FIELD_SIZE/2-1+gamePos.y);
		this.newPosition = new Vector2(this.position.x,this.position.y);
		this.oldPosition = new Vector2(this.position.x,this.position.y);
		
//		this.node_id = id;
		this.nodeColor = nodeColor;
		this.currState = State.IN_GAME;
		this.alpha = 1.0f;
		this.oldAlpha = 1.0f;
		
		colorCode = -1;
		
		steady = true;
	}
	
	public void shiftLeft(){
		newPosition.x -= 1;
		gamePos.x -= 1;
	}
	
	public void shiftRight(){
		newPosition.x += 1;
		gamePos.x += 1;
	}
	
	public void shiftUp(){
		newPosition.y += 1;
		gamePos.y += 1;
	}
	
	public void shiftDown(){
		newPosition.y -= 1;
		gamePos.y -= 1;
	}
	
	public void kill(){
		this.setCurrState(State.REMOVE);
	}
	
	public void update(float delta){
	
		if (currState == State.IN_GAME){
			if (Math.abs(newPosition.x - position.x) > 0.05* SIZE){
				float dx = (newPosition.x - position.x)/Math.abs(newPosition.x - oldPosition.x);
				position.x += 4*dx*delta;
				steady = false;
			}
			else {
				position.x = newPosition.x;
				oldPosition.x = position.x;
			}
			if (Math.abs(newPosition.y - position.y) > 0.05* SIZE){
				float dy = (newPosition.y - position.y)/Math.abs(newPosition.y - oldPosition.y);
				position.y += 4*dy*delta;	
				steady = false;
			}
			else {
				position.y = newPosition.y;
				oldPosition.y = position.y;
			}
			if ((Math.abs(newPosition.x - position.x) < 0.05*SIZE)&&(Math.abs(newPosition.y - position.y) < 0.05*SIZE)){
				position.x = newPosition.x;
				position.y = newPosition.y;
				steady = true;
			}			
		}
		// fade out
		if (currState == State.REMOVE){
			if (oldAlpha == 1.0f){
				oldAlpha = 0.01f;
			}
			float dAlpha = 5*(1.0f-oldAlpha);
			alpha -= dAlpha*delta;
			oldAlpha = alpha;
			steady = false;
			if (alpha<0.1){
				alpha = 0f;
				currState = State.NONE;
				steady = true;
			}
		}
		
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlfa){
		switch (nodeColor)
		{
			case BLUE:
				if (colorCode == -1) colorCode = 1;
				batch.setColor(1.0f,1.0f,1.0f,alpha);
				batch.draw(textureAtlas.get("blueNod"),
						   position.x * ppuX,
						   position.y *ppuY,
						   SIZE*ppuX,
						   SIZE*ppuY);
				batch.setColor(1.0f,1.0f,1.0f,1.0f);
				break;
			case GREEN:
				if (colorCode == -1) colorCode = 2;
				batch.setColor(1.0f,1.0f,1.0f,alpha);
				batch.draw(textureAtlas.get("greenNod"),
						   position.x * ppuX,
						   position.y * ppuY,
						   SIZE*ppuX,
						   SIZE*ppuY);
				batch.setColor(1.0f,1.0f,1.0f,1.0f);		   
				break;
			case GREY:
				if (colorCode == -1) colorCode = 3;
				batch.setColor(1.0f,1.0f,1.0f,alpha);
				batch.draw(textureAtlas.get("greyNod"),
						   position.x * ppuX,
						   position.y * ppuY,
						   SIZE*ppuX,
						   SIZE*ppuY);
				batch.setColor(1.0f,1.0f,1.0f,1.0f);
				break;
			case PINK:
				if (colorCode == -1) colorCode = 4;
				batch.setColor(1.0f,1.0f,1.0f,alpha);
				batch.draw(textureAtlas.get("pinkNod"),
						   position.x * ppuX,
						   position.y * ppuY,
						   SIZE*ppuX,
						   SIZE*ppuY);
				batch.setColor(1.0f,1.0f,1.0f,1.0f);
				break;
			case YELLOW:
				if (colorCode == -1) colorCode = 5;
				batch.setColor(1.0f,1.0f,1.0f,alpha);
				batch.draw(textureAtlas.get("yellowNod"),
						   position.x * ppuX,
						   position.y * ppuY,
						   SIZE*ppuX,
						   SIZE*ppuY);
				batch.setColor(1.0f,1.0f,1.0f,1.0f);
				break;
			case BOMB:
			
				break;
		}
		
	}
	
}
