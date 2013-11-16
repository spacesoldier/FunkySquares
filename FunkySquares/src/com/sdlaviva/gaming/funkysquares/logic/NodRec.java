package com.sdlaviva.gaming.funkysquares.logic;
import com.sdlaviva.gaming.funkysquares.model.*;
//import com.badlogic.gdx.math.*;

public class NodRec{
	public boolean isEmpty;
	public CubeNod nod;
	public boolean toDelete;
	private int groupId;

	public NodRec(){
		isEmpty = true;
		nod = null;
		toDelete = false;
		groupId = -1;
	}
	public NodRec(CubeNod nod){
		this.isEmpty = false;
		this.nod = nod;
		toDelete = false;
		groupId = -1;
	}

	public void setGroupId(int groupId)
	{
		this.groupId = groupId;
	}

	public int getGroupId()
	{
		return groupId;
	}
	public void setNod(CubeNod nod){
		if (nod != null){
			this.isEmpty = false;
			this.nod = nod;				
		}
	}
	public void clear(){
		this.isEmpty = true;
		this.nod = null;
		toDelete = false;
		groupId = -1;
	}
}
	
	
