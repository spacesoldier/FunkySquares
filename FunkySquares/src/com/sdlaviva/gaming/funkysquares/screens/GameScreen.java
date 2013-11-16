package com.sdlaviva.gaming.funkysquares.screens;

import java.util.Map;
import java.util.HashMap;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.sdlaviva.gaming.funkysquares.model.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
//import android.util.Log;

public class GameScreen implements Screen, InputProcessor
{

	public OrthographicCamera cam;
	public GameField mainField;

	Texture mainTexture;
	public Map<String, TextureRegion> mainTexturesMap = new HashMap<String, TextureRegion>();
	
	public int width;
	public int height;
	
	SpriteBatch spriteBatch;
	
	@Override
	public void show() {
		
		this.cam = new OrthographicCamera(GameField.CAMERA_WIDTH, GameField.CAMERA_HEIGHT);
		setCamera(GameField.CAMERA_WIDTH/2f, GameField.CAMERA_HEIGHT/2f);
		spriteBatch = new SpriteBatch();
		loadTextures();
		mainField = new GameField(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),false,spriteBatch,mainTexturesMap);
		
		Gdx.input.setInputProcessor(mainField);
			
	}
	
	@Override
	public void hide() {
		
		Gdx.input.setInputProcessor(null);
	}
	
	@Override
	public void pause() {
		
		
	}
	
	@Override
	public void resume() {
		Gdx.input.setInputProcessor(mainField);
		
	}
	
	@Override
	public void resize (int width, int height){
		this.width = width;
		this.height = height;
		
		mainField.setViewport(width, height, true);
		
	}
	
	@Override
	public void render (float delta){
		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		mainField.update(delta);
		mainField.draw();

	}
	
	@Override
	public void dispose () {
		
		Gdx.input.setInputProcessor(null);
	}
	
	private void loadTextures(){

		mainTexture = new Texture(Gdx.files.internal("images/funkysquares_atlas.png"));
		
		/* color bricks loading */
		
		TextureRegion nodsTexs[][] = TextureRegion.split(mainTexture, mainTexture.getWidth()/8, mainTexture.getHeight()/8);
		mainTexturesMap.put("blueNod",nodsTexs[2][0]);
		mainTexturesMap.put("greenNod",nodsTexs[2][1]);
		mainTexturesMap.put("greyNod",nodsTexs[2][2]);
		mainTexturesMap.put("pinkNod",nodsTexs[2][3]);
		mainTexturesMap.put("yellowNod",nodsTexs[2][4]);
		/* loading texture for restart button */
		mainTexturesMap.put("restartUp", nodsTexs[2][5]);
		mainTexturesMap.put("restartDown", nodsTexs[2][6]);
		
		
		/* loading background */
		
		TextureRegion backTex[][] = TextureRegion.split(mainTexture, mainTexture.getWidth()/4, mainTexture.getHeight()/4);		
		mainTexturesMap.put("background",backTex[0][0]);
		
		/* loading field edges */
		TextureRegion fieldTex[][] = TextureRegion.split(mainTexture, mainTexture.getWidth()/4, mainTexture.getHeight()/4);
		mainTexturesMap.put("corner", fieldTex[0][1]);
		mainTexturesMap.put("edge", fieldTex[0][2]);
		
		/* loading button textures */
		TextureRegion shiftBtnTex[][] = TextureRegion.split(mainTexture, mainTexture.getWidth()/8, mainTexture.getHeight()/4);
		mainTexturesMap.put("shift_unpressed", shiftBtnTex[0][6]);
		mainTexturesMap.put("shift_pressed", shiftBtnTex[0][7]);
		
		/* loading score font textures */
		TextureRegion scoreTex = new TextureRegion(mainTexture, 0, 448, 192, 64);
		mainTexturesMap.put("score",scoreTex);
		
		TextureRegion scoreNumTex[][] = TextureRegion.split(mainTexture, mainTexture.getWidth()/32, mainTexture.getHeight()/16);
		mainTexturesMap.put("num0",scoreNumTex[6][0]);
		mainTexturesMap.put("num1",scoreNumTex[6][1]);
		mainTexturesMap.put("num2",scoreNumTex[6][2]);
		mainTexturesMap.put("num3",scoreNumTex[6][3]);
		mainTexturesMap.put("num4",scoreNumTex[6][4]);
		mainTexturesMap.put("num5",scoreNumTex[6][5]);
		mainTexturesMap.put("num6",scoreNumTex[6][6]);
		mainTexturesMap.put("num7",scoreNumTex[6][7]);
		mainTexturesMap.put("num8",scoreNumTex[6][8]);
		mainTexturesMap.put("num9",scoreNumTex[6][9]);
		
		/*game over text */
		// "game"
		TextureRegion gameWordTex = new TextureRegion(mainTexture, 0, 930, 256, 92);
		mainTexturesMap.put("game",gameWordTex);
		// "over"
		TextureRegion overWordTex = new TextureRegion(mainTexture, 257, 930, 246, 92);
		mainTexturesMap.put("over",overWordTex);
		

	}
	
	
	public void setCamera(float x, float y){
		this.cam.position.set(x,y,0);
		this.cam.update();
		
		
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button){
		if(!Gdx.app.getType().equals(ApplicationType.Android))
			return false;
		return true;
	}
	 
	@Override
	public boolean touchUp(int x, int y, int pointer, int button){
		if(!Gdx.app.getType().equals(ApplicationType.Android))
			return false;
		return true;

	}

	@Override
	public boolean touchDragged(int x, int y, int pointer){
		return false;
	}
	
	@Override
	public boolean scrolled(int delta){
		return false;
	}
	
	@Override
	public boolean keyUp(int code){
		return false;
	}
	
	@Override
	public boolean keyDown(int code){
		return true;
	}
	
	@Override
	public boolean keyTyped(char key){
		return false;
	}
	
	@Override
	public boolean mouseMoved(int x, int y){
		return false;
	}
}