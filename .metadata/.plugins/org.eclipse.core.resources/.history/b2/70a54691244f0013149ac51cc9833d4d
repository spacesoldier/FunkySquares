package com.sdlaviva.gaming.funkysquares.logic;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.sdlaviva.gaming.funkysquares.model.*;
//import java.util.*;
import java.util.Map;
import java.util.ArrayList;
//import com.badlogic.gdx.math.Rectangle;

public class LogicContainer extends Group
{
	private float ppuX;
	private float ppuY;
	
	private float centerX;
	private float centerY;

//	private Label debugText;

	private GameField mainField;

	Map<String, TextureRegion> textureAtlas;

	private static final int FIELD_SIZE = 6;

	static int getSize() {
		return FIELD_SIZE;
	}	
	
	private NodRec [][] logicField = new NodRec [FIELD_SIZE+2][FIELD_SIZE+2];

	private DomainHolder sortedNods;
	
	ArrayList<CubeNod> nodsToDelete;
	
	//the game status
	private boolean fieldClear;
	private boolean gameEnded;
	
	public LogicContainer(GameField field, Map<String, TextureRegion> textureAtlas){

		this.mainField = field;
		this.textureAtlas = textureAtlas;
		this.ppuX = mainField.ppuX;
		this.ppuY = mainField.ppuY;
		centerX = GameField.CAMERA_WIDTH/2;
		centerY = GameField.CAMERA_HEIGHT/2;
		
		setX((centerX-FIELD_SIZE/2)*ppuX);
		setY((centerY-FIELD_SIZE/2)*ppuY);
		setWidth((FIELD_SIZE)*ppuX);
		setHeight((FIELD_SIZE)*ppuY);
		
		fieldClear = true;
		gameEnded = false;
		
		sortedNods = new DomainHolder(FIELD_SIZE);
		nodsToDelete = new ArrayList<CubeNod>();
		
		for (int i=0; i<FIELD_SIZE+2; i++)
			for (int j=0; j<FIELD_SIZE+2; j++) {
				logicField[i][j] = new NodRec();
			}

	
		// Firstly fill the logic field		
		for (int i=0; i<FIELD_SIZE; i++)
		{
			//lower row	
			CubeNod cube = new CubeNod(new Vector2(i+1,1),
									   ppuX,ppuY,textureAtlas,0,centerX,centerY,FIELD_SIZE,
									   CubeNod.ColorDef.getRandom());
			logicField[i+1][1].setNod(cube);
			
			//upper row
			cube = new CubeNod(new Vector2(i+1,FIELD_SIZE),
							   ppuX,ppuY,textureAtlas,0,centerX,centerY,FIELD_SIZE,
							   CubeNod.ColorDef.getRandom());
			logicField[i+1][FIELD_SIZE].setNod(cube);
			
		}
		
		for (int i=0; i<FIELD_SIZE-2; i++)
		{
			
			//left column
			CubeNod cube = new CubeNod(new Vector2(1,i+2),
									   ppuX,ppuY,textureAtlas,0,centerX,centerY,FIELD_SIZE,
									   CubeNod.ColorDef.getRandom());
			logicField[1][i+2].setNod(cube);
			
			//right column
			cube = new CubeNod(new Vector2(FIELD_SIZE,i+2),
							   ppuX,ppuY,textureAtlas,0,centerX,centerY,FIELD_SIZE,
							   CubeNod.ColorDef.getRandom());
			logicField[FIELD_SIZE][i+2].setNod(cube);				   
			
		}
		
		
		//Then add nods to the scene
		for (int i=0; i<FIELD_SIZE+2; i++){
			for (int j=0; j<FIELD_SIZE+2; j++){
				NodRec rec = logicField[i][j];
				if (!rec.isEmpty) this.addActor(rec.nod);
			}
		}
		
		
		//hidden rows and columns
		addNewNods();
		
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlfa){
		this.drawChildren(batch, parentAlfa);
		
	}
	
	//add new nods to the hidden area
	private void addNewNods(){
		
		for (int i=0; i<FIELD_SIZE; i++){
			CubeNod cube;
			//lower hidden row
			if (logicField[i+1][0].isEmpty){
				cube = new CubeNod(new Vector2(i+1,0),
								   ppuX,ppuY,textureAtlas,0,centerX,centerY,FIELD_SIZE,
								   CubeNod.ColorDef.getRandom());
				logicField[i+1][0].setNod(cube);
				
			}
			
			
			//upper hidden row
			if (logicField[i+1][FIELD_SIZE+1].isEmpty){
				cube = new CubeNod(new Vector2(i+1,FIELD_SIZE+1),
								   ppuX,ppuY,textureAtlas,0,centerX,centerY,FIELD_SIZE,
								   CubeNod.ColorDef.getRandom());
				logicField[i+1][FIELD_SIZE+1].setNod(cube);
				
			}

			//left hidden column			
			if (logicField[0][i+1].isEmpty){
				cube = new CubeNod(new Vector2(0,i+1),
								   ppuX,ppuY,textureAtlas,0,centerX,centerY,FIELD_SIZE,
								   CubeNod.ColorDef.getRandom());
				logicField[0][i+1].setNod(cube);				   
				
			}

			//right hidden column
			if (logicField[FIELD_SIZE+1][i+1].isEmpty){
				cube = new CubeNod(new Vector2(FIELD_SIZE+1,i+1),
								   ppuX,ppuY,textureAtlas,0,centerX,centerY,FIELD_SIZE,
								   CubeNod.ColorDef.getRandom());
				logicField[FIELD_SIZE+1][i+1].setNod(cube);				   
								
			}
			
		}
		
		
		//add nods to the scene
		for (int i=0; i<FIELD_SIZE+2; i++){
			for (int j=0; j<FIELD_SIZE+2; j++){
				NodRec rec = logicField[i][j];
				if ((!rec.isEmpty) && (this.hasActor(rec.nod) == false)) this.addActor(rec.nod);
			}
		}
		
	}	
	
	//check the logicField for ending the game condition
	private boolean noMoreMoves(){
		
		for (int i=1; i<FIELD_SIZE+1; i++) {
			for (int j=1; j<FIELD_SIZE+1; j++){
				if (logicField[i][j].isEmpty) return false;
			}
		}
		
		return true;
	}
	
	boolean hasActor(Actor findActor){
		for (int i = 0; i< this.getChildren().size; i++){
			if (this.getChildren().get(i) == findActor){
				return true;
			}
		}
		return false;
	}
	
	//checking whether the move has been ended: all cubes stopped moving or stopped morphing
	private boolean moveEnded(){
		
		for (int i=1; i<FIELD_SIZE+1; i++) {
			for (int j=1; j<FIELD_SIZE+1; j++){
				if ((logicField[i][j].nod != null) && (!logicField[i][j].nod.isSteady())){
					return false;					
				}
			}
		}
		
		return true;
	}
	
	//checking whether all cubes stopped stopped morphing
	private boolean stopMorphing(){

		for (int i=1; i<FIELD_SIZE+1; i++) {
			for (int j=1; j<FIELD_SIZE+1; j++){
				if ((logicField[i][j].nod != null) && (logicField[i][j].nod.getCurrState() == CubeNod.State.REMOVE)){
					return false;					
				}
			}
		}

		return true;
	}
	
	// remove killed cubes from the field
	public void removeKilled(){
		for (int i = 0; i< this.getChildren().size; i++){
			if (this.getChildren().get(i) instanceof CubeNod){
				CubeNod cube = (CubeNod) this.getChildren().get(i);
				if(cube.getCurrState() == CubeNod.State.NONE){
					removeActor(cube);
				}
			}
		}
	}
	
	public void shiftUp(){
		
		if (moveEnded()){
			//vertical scan, up to down
			for (int j=FIELD_SIZE; j>0; j--){
				for (int i=1; i< FIELD_SIZE+1; i++) {
					if ((logicField[i][j].isEmpty == true) && (logicField[i][j-1].isEmpty == false)) {
						logicField[i][j-1].nod.shiftUp();
						logicField[i][j].setNod(logicField[i][j-1].nod);
						logicField[i][j-1].clear();
						mainField.Score.plusValue(1);
					}
				}
			}
			
		}
		
		fieldClear = false;
		addNewNods();
	}
	
	public void shiftDown(){

		if (moveEnded()){
			//vertical scan, down to up
			for (int j=1; j< FIELD_SIZE+1; j++){
				for (int i=1; i< FIELD_SIZE+1; i++) {
					if ((logicField[i][j].isEmpty == true) && (logicField[i][j+1].isEmpty == false)) {
						logicField[i][j+1].nod.shiftDown();
						logicField[i][j].setNod(logicField[i][j+1].nod);
						logicField[i][j+1].clear();
						mainField.Score.plusValue(1);
					}
				}
			}
		}		
		
		fieldClear = false;
		addNewNods();
		
	}
	
	public void shiftLeft(){

		if (moveEnded()){
			//horizontal scan, left to right
			for (int i=1; i< FIELD_SIZE+1; i++){
				for (int j=1; j< FIELD_SIZE+1; j++) {
					if ((logicField[i][j].isEmpty == true) && (logicField[i+1][j].isEmpty == false)) {
						logicField[i+1][j].nod.shiftLeft();
						logicField[i][j].setNod(logicField[i+1][j].nod);
						logicField[i+1][j].clear();
						mainField.Score.plusValue(1);
					}
				}
			}
		}
		
		fieldClear = false;
		addNewNods();
	}

	public void shiftRight(){

		if (moveEnded()){
			//horizontal scan, right to left
			for (int i=FIELD_SIZE; i>0; i--){
				for (int j=1; j< FIELD_SIZE+1; j++) {
					if ((logicField[i][j].isEmpty == true) && (logicField[i-1][j].isEmpty == false)) {
						logicField[i-1][j].nod.shiftRight();
						logicField[i][j].setNod(logicField[i-1][j].nod);
						logicField[i-1][j].clear();
						mainField.Score.plusValue(1);
					}
				}
			}			
		}
		
		fieldClear = false;
		addNewNods();
	}

	
	private void analyzeField(){
		if (fieldClear == false){
			for (int i = 0; i<FIELD_SIZE; i++){
				for (int j = 0; j<FIELD_SIZE; j++){
					if (!logicField[i+1][j+1].isEmpty) {
						
						// old logics
						sortedNods.addNod(logicField[i+1][j+1]);
						
						//new logics
					/*	sortedNods.putBlock( logicField[i+1][j+1],
											 (j < FIELD_SIZE-1) ? logicField[i+1][j+2] : null,
											 (j > 1) ? logicField[i+1][j] : null,
											 (i < FIELD_SIZE-1) ? logicField[i+2][j+1] : null,
											 (i > 1) ? logicField[i][j+1] : null
										   );
						*/
					}
				}
			}
			 nodsToDelete = sortedNods.getTargetNods();
			 /*
			 mainField.debugLabel.setText("Nods to delete: "+nodsToDelete.size()+
			 "\n Domains collected:"+sortedNods.getDomainQty() + sortedNods.getDomainContent());
			 mainField.debugLabel.setPosition(0,ppuY*(mainField.CAMERA_HEIGHT/2)-mainField.debugLabel.getHeight()/2);
			 */
			 mainField.Score.plusValue(nodsToDelete.size()*3);
			
			
			for (int i=0; i<nodsToDelete.size(); i++){
			 	CubeNod cube = nodsToDelete.get(i);
			 	if (cube != null){
					logicField[(int)cube.getGamePos().x][(int)cube.getGamePos().y].nod.kill();
					logicField[(int)cube.getGamePos().x][(int)cube.getGamePos().y].clear();
				//	removeActor(cube);	
				}
			 }
			 
			 nodsToDelete.clear();
			
			 sortedNods.clear();
			
			 fieldClear = true;
			 
			if (noMoreMoves()){
				gameEnded = true;
			}
		}
		
	}
	
	public void update(float delta){
		for (Actor actor : this.getChildren()){
			if(actor instanceof CubeNod)
				((CubeNod)actor).update(delta);			
		}
		
		if (!fieldClear && stopMorphing() && moveEnded()){
			removeKilled();
			analyzeField();
		}
		
		if (gameEnded){
				mainField.gameOver();
		}		
		
	}
	
}
