package com.sdlaviva.gaming.funkysquares.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sdlaviva.gaming.funkysquares.controls.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.sdlaviva.gaming.funkysquares.logic.*;

public class GameField extends Stage
{
	public float ppuX;
	public float ppuY;
	
	public Actor selectedActor = null;
	
	//public Label debugLabel;
	
	public Map<String, TextureRegion> textureAtlas;
	
	private SpriteBatch fieldBatch;
	
	private Vector2 coords = new Vector2(0,0);
	
	public static float CAMERA_WIDTH = 10f;
	public static float CAMERA_HEIGHT = 16f;
	
	public static float getCAMERA_WIDTH() {
		return CAMERA_WIDTH;
	}

	public static void setCAMERA_WIDTH(float cAMERA_WIDTH) {
		CAMERA_WIDTH = cAMERA_WIDTH;
	}

	public static float getCAMERA_HEIGHT() {
		return CAMERA_HEIGHT;
	}

	public static void setCAMERA_HEIGHT(float cAMERA_HEIGHT) {
		CAMERA_HEIGHT = cAMERA_HEIGHT;
	}

	RestartButton restart;
	GameOverText stopmessage;
	
	public ScoreCounter Score;
	
	public LogicContainer allNods;
	//public Label gameOverText;
	//GameOverCase gameOverWidget;
	
	/* updating color cubes positions */
	public void update(float delta){
	//	debugLabel.setText("Delta time:  "+delta);
		for (Actor actor: this.getActors()){
			if(actor instanceof CubeNod)
				((CubeNod)actor).update(delta);
			if(actor instanceof LogicContainer)
				((LogicContainer)actor).update(delta);	
		}
			
	}
	
	public void reset(){
		if (allNods != null) allNods.reset();
		Score.setValue(0);

		///TODO REMOVE THESE FUCKIN GAMEOVER WIDGETS
		
		removeGameOverCase();

		//gameOverWidget.hide();
		
	}

	private void removeGameOverCase() {
		for (Actor act : this.getRoot().getChildren()){
			if (act instanceof RestartButton) this.getRoot().removeActor(act);
			if (act instanceof GameOverText) this.getRoot().removeActor(act);
		}
	}
	
	public GameField(int x, int y, boolean b, SpriteBatch spriteBatch, Map<String, TextureRegion> fieldTextureMap){
		super(x,y,b,spriteBatch);
		
		this.textureAtlas = fieldTextureMap;
		
		fieldBatch = spriteBatch;
		
		ppuX = getWidth()/CAMERA_WIDTH;
		ppuY = getHeight()/CAMERA_HEIGHT;
		
		for (int i=0; i<(int)(GameField.CAMERA_WIDTH/Background.SIZE); i++)
			for (int j=0; j<(int)(GameField.CAMERA_HEIGHT/Background.SIZE); j++)
				this.addActor(new Background(new Vector2(i,j),ppuX,ppuY,textureAtlas));

		//debugLabel = new Label("Button coordinates:",new Label.LabelStyle(new BitmapFont(), new Color(1,1,1,1)));
		
		allNods = new LogicContainer(this,textureAtlas, GameField.CAMERA_WIDTH/2, GameField.CAMERA_HEIGHT-5);
		this.addActor(allNods);
		this.addActor(new FieldFrame(new Vector2(CAMERA_WIDTH/2,GameField.CAMERA_HEIGHT-5),ppuX,ppuY,textureAtlas));
		

		//this.addActor(debugLabel);
		//debugLabel.setPosition(0,ppuY*(CAMERA_HEIGHT-4));
		
		// CREATING SCORE COUNTER
		Score = new ScoreCounter(this,textureAtlas,0);
		
		this.addActor(Score);
		
		this.addActor(new ControlGroup(this,textureAtlas, GameField.CAMERA_WIDTH/2, 3));

		restart = new RestartButton(new Vector2(CAMERA_WIDTH/2-1,3),this);
		stopmessage = new GameOverText(new Vector2(CAMERA_WIDTH/2-4, CAMERA_HEIGHT/2+2), this);

		//restart.setVisible(false);
	//	debugLabel.setText(debugLabel.getText()+"\n Touch hits:");

		
}
	
	public void gameOver(){
		this.addActor(stopmessage);
		this.addActor(restart);
	}
	
	@Override
	public void draw(){
		fieldBatch.begin();
			for (Actor actor: this.getActors())
			{
				if(actor instanceof Background)
					((Background)actor).draw(fieldBatch,1);
				else if(actor instanceof LogicContainer)
					((LogicContainer)actor).draw(fieldBatch,1);
				else if(actor instanceof FieldFrame)
					((FieldFrame)actor).draw(fieldBatch,1);	
				else if(actor instanceof Label)
					((Label)actor).draw(fieldBatch,1);
				else if(actor instanceof ControlButton)
					((ControlButton)actor).draw(fieldBatch,1);
				else if(actor instanceof ScoreCounter)
					((ScoreCounter)actor).draw(fieldBatch,1);
				else if(actor instanceof ControlGroup)
					((ControlGroup)actor).draw(fieldBatch,1);
				else if(actor instanceof RestartButton)
					((RestartButton)actor).draw(fieldBatch,1);
				else if(actor instanceof GameOverCase)
					((GameOverCase)actor).draw(fieldBatch,1);
				else if(actor instanceof GameOverText)
					((GameOverText)actor).draw(fieldBatch,1);
			}
				
		fieldBatch.end();
	}
	
	public void setPP(float x, float y){
		ppuX = x;
		ppuY = y;
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button){
		super.touchDown(x,y,pointer,button);
		//debugLabel.setText(debugLabel.getText()+"\n   Scene touchDown: X="+x+" Y="+y);
		return true;
	}
	
	@Override
	public boolean touchUp(int x, int y, int pointer, int button){
		super.touchUp(x,y,pointer,button);
		//debugLabel.setText(debugLabel.getText()+"\n   Scene touchUp: X="+x+" Y="+y);
		
		//if (selectedActor != null)
			//debugLabel.setText(debugLabel.getText()+"\n    Actor:"+ selectedActor.getClass().getName());
		resetSelected();		
		return true;
	}
	
	private void resetSelected(){
		if (selectedActor != null) {
			if (selectedActor instanceof ControlButton) ((ControlButton)selectedActor).setCurrentState(ControlButton.BtnState.RELEASED);
			if (selectedActor instanceof RestartButton) {
				((RestartButton)selectedActor).setCurrentState(ControlButton.BtnState.RELEASED);
				removeGameOverCase();
			}
			selectedActor = null;
		} 
	}
	
	@Override
	public Actor hit(float x, float y, boolean touchable){
		coords.set(x,y);
		coords = this.stageToScreenCoordinates(coords);
		Actor actor = super.hit(coords.x,coords.y,touchable);
	//	debugLabel.setText(debugLabel.getText()+"\n   Scene hit: X="+x+" Y="+y);
		if(actor != null && actor instanceof ControlButton) {
			selectedActor = actor;
			
	//		((ControlButton)selectedActor).setCurrentState(ControlButton.BtnState.PRESSED);
		}
		
		if(actor != null && actor instanceof RestartButton) {
			selectedActor = actor;
			
	//		((ControlButton)selectedActor).setCurrentState(ControlButton.BtnState.PRESSED);
		}
			
		return actor;
	}
	
}
