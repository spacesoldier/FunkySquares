package com.sdlaviva.gaming.funkysquares.controls;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.Map;
import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.sdlaviva.gaming.funkysquares.model.*;

public class ControlButton extends Actor
{
	public enum Direction {
		LEFT, RIGHT, UP, DOWN
	}
	
	public enum BtnState {
		PRESSED, RELEASED
	}
		
//	private Label debugText;
	
	public static final float H_SIZE = 2f;
	public static final float W_SIZE = 1f;
	
	public Direction direction;
	public BtnState currentState;
	
	public GameField mainField;
	
	public void setCurrentState(BtnState newState){
		this.currentState = newState;
	}
	

	Map<String, TextureRegion> textureAtlas;
	
	public ControlButton(Vector2 position, float ppuX, float ppuY, Map<String, TextureRegion> textureAtlas, Direction direction, GameField mainField /*, Label debugText*/) {

		this.textureAtlas = textureAtlas;
		this.direction = direction;
		this.mainField = mainField;
		currentState = BtnState.RELEASED;
		
	//	this.debugText = debugText;

		
		switch (direction){
			case UP:
					this.setX(position.x*ppuX);
					this.setY(position.y*ppuY);
					this.setWidth(H_SIZE*ppuX);
					this.setHeight(W_SIZE*ppuY);				
					//this.debugText.setText(debugText.getText()+"\n UP: X="+ this.getX()+" Y="+this.getY()+" H="+ this.getHeight()+" W="+ this.getWidth());
				break;
			case DOWN:
					this.setX(position.x*ppuX);
					this.setY(position.y*ppuY-W_SIZE*ppuY);
					this.setWidth(H_SIZE*ppuX);
					this.setHeight(W_SIZE*ppuY);
					//this.debugText.setText(debugText.getText()+"\n DOWN: X="+ this.getX()+" Y="+this.getY()+" H="+ this.getHeight()+" W="+ this.getWidth());
				break;
			case LEFT:
					this.setX(position.x*ppuX - W_SIZE*ppuX);
					this.setY(position.y*ppuY);
					this.setWidth(W_SIZE*ppuX);
					this.setHeight(H_SIZE*ppuY);				
					//this.debugText.setText(debugText.getText()+"\n LEFT: X="+ this.getX()+" Y="+this.getY()+" H="+ this.getHeight()+" W="+ this.getWidth());
				break;
			case RIGHT:
					this.setX(position.x*ppuX);
					this.setY(position.y*ppuY);
					this.setWidth(W_SIZE*ppuX);
					this.setHeight(H_SIZE*ppuY);
					//this.debugText.setText(debugText.getText()+"\n RIGHT: X="+ this.getX()+" Y="+this.getY()+" H="+ this.getHeight()+" W="+ this.getWidth());
				break;
		}
		
		
		addListener(new InputListener (){
				
				public boolean touchDown(InputEvent event,float x, float y, int pointer, int button){
					currentState = BtnState.PRESSED;
					shiftAll();					
					return true;
				}
				
				public void touchUp(InputEvent event,int x, int y, int pointer, int button){
					currentState = BtnState.RELEASED;
				}		
		});
	}

	private void shiftAll(){
		switch (direction) {
			case UP:
				mainField.allNods.shiftUp();
				break;
			case DOWN:
				mainField.allNods.shiftDown();
				break;
			case LEFT:
				mainField.allNods.shiftLeft();
				break;
			case RIGHT:
				mainField.allNods.shiftRight();
				break;	
		}
		
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentBlending){
		if (this!=mainField.selectedActor) currentState = BtnState.RELEASED;
		
		TextureRegion textureRegion = new TextureRegion();
		if (currentState == BtnState.RELEASED)  
			textureRegion = textureAtlas.get("shift_unpressed");
		if (currentState == BtnState.PRESSED)  
			textureRegion = textureAtlas.get("shift_pressed");

		switch (direction)
		{
			case UP:
				batch.draw(textureRegion,
				 getX(),
				 getY()+getHeight(),
				 0, 
				 0,
				 getHeight(),
				 getWidth(),
				 1,1,
				 -90);

				break;
			case DOWN:
			
				batch.draw(textureRegion,
						   getX()+getWidth(),
						   getY(),
						   0, 
						   0,
						   getHeight(),
						   getWidth(),
						   1,1,
						   90);
						   
				break;
			case LEFT:
				batch.draw(textureRegion,
						   getX(),
						   getY(),
						   getWidth(),
						   getHeight()
						   );
				break;
			case RIGHT:
				batch.draw(textureRegion,
						   getX()+ getWidth(),
						   getY()+ getHeight(),
						   0, 
						   0,
						   getWidth(),
						   getHeight(),
						   1,1,
						   180);
				break;
		}
	}
	
	
	//@Override
	public Actor hit(float x, float y){
		return x>0 && x<this.getWidth() && y>0 && y< this.getHeight() ? this : null;
	}
}