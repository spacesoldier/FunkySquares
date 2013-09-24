package com.sdlaviva.gaming.funkysquares.model;

import java.util.Map;


import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.badlogic.gdx.scenes.scene2d.Group;
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
	
	public static float CAMERA_WIDTH = 16f;
	public static float CAMERA_HEIGHT = 10f;
	
	public ScoreCounter Score;
	
	public LogicContainer allNods;
	public Label gameOverText;
	
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
		
		allNods = new LogicContainer(this,textureAtlas);
		this.addActor(allNods);
		this.addActor(new FieldFrame(new Vector2(CAMERA_WIDTH/2,CAMERA_HEIGHT/2),ppuX,ppuY,textureAtlas));
		

		//this.addActor(debugLabel);
		//debugLabel.setPosition(0,ppuY*(CAMERA_HEIGHT-4));
		
		// CREATING SCORE COUNTER
		Score = new ScoreCounter(this,textureAtlas,0);
		
		this.addActor(Score);
		
		this.addActor(new ControlGroup(this,textureAtlas));
	//	debugLabel.setText(debugLabel.getText()+"\n Touch hits:");
		
		gameOverText = null;
}
	
	public void gameOver(){
		if (gameOverText == null){
			Label.LabelStyle fontType = new Label.LabelStyle(new BitmapFont(), new Color(1f,0.5f,0.01f,1f));
			fontType.font.setScale(ppuX*150f/getWidth(),ppuY*80f/getHeight());
			gameOverText = new Label("GAME OVER",fontType);
			float w = CAMERA_WIDTH*gameOverText.getWidth()/getWidth();
			float h = CAMERA_HEIGHT*gameOverText.getHeight()/getHeight();
			gameOverText.setSize(w*ppuX, h*ppuY);
			gameOverText.setPosition(ppuX*(CAMERA_WIDTH/2-ppuX*75f/getWidth()),ppuY*(CAMERA_HEIGHT/2));
			this.addActor(gameOverText);			
		}
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
			((ControlButton)selectedActor).setCurrentState(ControlButton.BtnState.RELEASED);
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
			
		return actor;
	}
	
}
