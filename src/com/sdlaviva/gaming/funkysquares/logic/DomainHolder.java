package com.sdlaviva.gaming.funkysquares.logic;
import java.util.ArrayList;
import com.sdlaviva.gaming.funkysquares.model.*;

public class DomainHolder{
	private int FIELD_SIZE;
	private int maxSize;
	private int domainQty;
	private NodDomain[] domains;
	
	public int getDomainQty(){
		return domainQty;
	}
	
	// show the log on the screen	
	/*
	public StringBuffer getDomainContent(){
		StringBuffer result = new StringBuffer();
		result.append("\n Contains:\n");
		for (int i=0; i<domainQty; i++){
			String color = "";
			switch (domains[i].getDColor()){
				case 1: color = "BLUE";
						break;
				case 2: color = "GREEN";
						break;
				case 3: color = "GREY";
						break;
				case 4: color = "PINK";
						break;
				case 5: color = "YELLOW";
						break;
			}
			StringBuilder coords = new StringBuilder();
			for (int j=0; j<domains[i].nodQty; j++){
				coords.append(" ("+(int)domains[i].nods[j].nod.getGamePos().x+", "+
							  (int)domains[i].nods[j].nod.getGamePos().y+")");
			}
			result.append("   color: "+color+ " count: "+domains[i].nodQty+" | "+coords+"\n");
		}
		return result;
	}
	*/
	public StringBuffer getDomainContent(){
		StringBuffer result = new StringBuffer();
		result.append("\n Contains:\n");
		for (int i=0; i<domainQty; i++){
			String color = "";
			switch (domains[i].getDColor()){
				case 1: color = "BLUE";
					break;
				case 2: color = "GREEN";
					break;
				case 3: color = "GREY";
					break;
				case 4: color = "PINK";
					break;
				case 5: color = "YELLOW";
					break;
			}
			StringBuilder coords = new StringBuilder();
			for (int j=0; j<domains[i].colorNods.size(); j++){
				coords.append(" ("+(int)domains[i].colorNods.get(j).nod.getGamePos().x+", "+
							  (int)domains[i].colorNods.get(j).nod.getGamePos().y+")");
			}
			result.append("   color: "+color+ " count: "+domains[i].colorNods.size()+" | "+coords+"\n");
		}
		return result;
	}

	public DomainHolder(int fieldSize){
		domainQty = 0;
		FIELD_SIZE = fieldSize;
		maxSize = FIELD_SIZE*FIELD_SIZE;
		domains = new NodDomain[maxSize];
		for (int i=0; i<maxSize; i++){
			domains[i] = new NodDomain(FIELD_SIZE);
		}
	}

	public void insert(NodRec rec){
		if (rec.nod != null && !rec.isEmpty){
			for (int i=0; i<maxSize; i++){
				domains[i].insert(rec);
			}
		}
	}
	
	
	public void addNod(NodRec rec){
		
		// firstly we group the cubes by color
		if (rec.nod != null && !rec.isEmpty){
			
			for (int i=0; i<maxSize; i++){
				if (domains[i].insert(rec)) {
					if (i == domainQty) domainQty = i+1;
						break;
				}
			}
		}
		
		
	}
	
	private ArrayList<NodDomain> getRawDomains(){
		ArrayList<NodDomain> targets = new ArrayList<NodDomain>();
		for (int i=0; i<domainQty; i++){
			if (domains[i].colorNods.size() > 0){
					targets.add(domains[i]);		
			}
		}
		return targets;
	}
	
	public ArrayList<CubeNod> getTargetNods(){

		// secondly we try to merge the groups of cubes by finding the nearest ones in groups
		// ERROR PRONE
		ArrayList<NodDomain> colorGroups = getRawDomains();
		ArrayList<NodDomain> mergeGroups = new ArrayList<NodDomain>();

		//for all raw groups
		
		//for each global color group
		for (int i = 0; i<colorGroups.size(); i++){
			NodDomain mainColor = colorGroups.get(i);
			if (mainColor.colorNods.size()>0){
				NodDomain firstDomain = new NodDomain(maxSize);
				firstDomain.insert(mainColor.getNodRec(0));
				mainColor.removeNodRec(0);
				
				//for each element of current domain
				for (int k=0; k < firstDomain.colorNods.size(); k++){
					// find nearest elements to the current in the main color group
					ArrayList<Integer> findNearest = mainColor.findNearList(firstDomain.colorNods.get(k));
					if (findNearest.size() > 0){
						ArrayList<NodRec> delList = new ArrayList<NodRec>();
						//add merged elements into the result set
						for (int l=0; l < findNearest.size(); l++){
							firstDomain.colorNods.add(mainColor.colorNods.get(findNearest.get(l)));
							//remember which elements to delete from source set
							delList.add(mainColor.colorNods.get(findNearest.get(l)));
						}
						for (int l=0; l < delList.size(); l++){
							mainColor.colorNods.remove(delList.get(l));
						}
					}					
				}
				
				if (firstDomain.colorNods.size()>2){
					mergeGroups.add(firstDomain);
				}
				if (mainColor.colorNods.size()>0){
					i--;
				}
			}	
									
		}
			
		 

		ArrayList<CubeNod> targets = new ArrayList<CubeNod>();
		for (int i=0; i<mergeGroups.size(); i++){
			NodDomain tmp = mergeGroups.get(i);
			if (tmp.colorNods.size() > 2){
				for (int j=0; j<tmp.colorNods.size(); j++){
					targets.add(tmp.colorNods.get(j).nod);
				}
			}
		}
		
		return targets;
	}

	public void clear(){
		for (int i = 0; i<domainQty; i++){
			domains[i].clear();
		}
		domainQty = 0;
	}

}
