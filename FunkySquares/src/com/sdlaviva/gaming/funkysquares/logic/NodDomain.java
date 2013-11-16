package com.sdlaviva.gaming.funkysquares.logic;
import com.sdlaviva.gaming.funkysquares.model.*;
import java.util.*;

public class NodDomain{
//	private int FIELD_SIZE;
//	private int maxSize;
	private int dColor;
	
	public ArrayList<NodRec> colorNods;
	
	public NodDomain(int fieldSize){
		
//		FIELD_SIZE = fieldSize;
//		maxSize = FIELD_SIZE*FIELD_SIZE;
		
		//dColor = CubeNod.ColorDef.BOMB;
		dColor = -1;
		
		colorNods = new ArrayList<NodRec>();
	}

	public void setDColor(int dColor)
	{
		this.dColor = dColor;
	}

	public int getDColor()
	{
		return dColor;
	}
	
	//to sort cubes by color only
	public boolean insert(NodRec rec){
		if (!rec.isEmpty){
			if (dColor == -1){
				dColor = rec.nod.getNodeColor();
				colorNods.add(rec);
				return true;
			}
			else if (dColor == rec.nod.getNodeColor()){
				colorNods.add(rec);
				return true;
			}
			else return false;
		}
		return false;
	}
	
	public ArrayList<Integer> findNearList(NodRec rec){
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (rec.nod != null && rec.nod.getNodeColor() == dColor){
			for (int i = 0; i < colorNods.size(); i++){
				CubeNod tmp = colorNods.get(i).nod;
				if (Math.abs(tmp.getGamePos().x - rec.nod.getGamePos().x) == 0 &&
					Math.abs(tmp.getGamePos().y - rec.nod.getGamePos().y) == 1){
					result.add(new Integer(i));
				} else
				if (Math.abs(tmp.getGamePos().x - rec.nod.getGamePos().x) == 1 &&
					Math.abs(tmp.getGamePos().y - rec.nod.getGamePos().y) == 0){
					result.add(new Integer(i));
				}
			}			
		}
		return result;
	}
	
	public void removeNodRec(int pos){
		if (pos < colorNods.size()){
			colorNods.remove(pos);
		}
	}
	
	public NodRec getNodRec(int pos){
		NodRec result = null;
		if (pos < colorNods.size()){
			result = colorNods.get(pos);
		}
		return result;
	}
	
	
	
	public void clear(){
		dColor = -1;
		colorNods.clear();
	}
}
