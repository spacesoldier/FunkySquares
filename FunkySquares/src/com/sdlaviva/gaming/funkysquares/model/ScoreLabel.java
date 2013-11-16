package com.sdlaviva.gaming.funkysquares.model;

//import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Actor;
import java.util.Map;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScoreLabel extends Actor
{
		//private GameField mainField;
		private Map <String, TextureRegion> textureAtlas;

		public static final float W_SIZE = 2f;
		public static final float H_SIZE = 1f;

		private float ppuX;
		private float ppuY;

	public ScoreLabel(GameField mainField, Map<String, TextureRegion> textureAtlas, float posX, float posY)
	{
		this.textureAtlas = textureAtlas;
		//this.mainField = mainField;

		ppuX = mainField.ppuX;
		ppuY = mainField.ppuY;
		
		this.setX(posX);
		this.setY(posY);
		this.setWidth(W_SIZE*ppuX);
		this.setHeight(H_SIZE*ppuY);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlfa){

		batch.draw(textureAtlas.get("score"),getX(),getY(),getWidth(),getHeight());

	}
}