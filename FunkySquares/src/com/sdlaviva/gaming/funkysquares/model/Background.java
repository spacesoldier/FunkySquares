package com.sdlaviva.gaming.funkysquares.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import java.util.Map;

public class Background extends Actor
{
	public static final float SIZE = 5f;

	Vector2 position = new Vector2();
	Rectangle bounds = new Rectangle();
	
	private Map<String, TextureRegion> textureAtlas;

	private float ppuY;

	private float ppuX;

	public Background(Vector2 position, float ppuX, float ppuY, Map<String, TextureRegion> textureAtlas) {
		this.position = position;
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;
		this.ppuX = ppuX;
		this.ppuY = ppuY;
		this.textureAtlas = textureAtlas;
		
/*		addListener(new InputListener (){

				public boolean touchDown(InputEvent event,float x, float y, int pointer, int button){
	
					return false;
				}

				public void touchDragged(InputEvent event,float x, float y, int pointer){
	
				}

				public void touchUp(InputEvent event,int x, int y, int pointer, int button){
	

				}		
			});*/
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public Vector2 getPosition () {
		return position;
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlfa){

		batch.draw(textureAtlas.get("background"),
				   position.x * SIZE * ppuX,
						position.y*SIZE*ppuY,
						SIZE*ppuX,
						SIZE*ppuY);
	}
/*	
	@Override
	public Actor hit(float x, float y){
		return null;
	}
*/
}
