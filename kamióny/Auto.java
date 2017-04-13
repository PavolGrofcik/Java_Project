package kamióny;

import containers.Kontajner;

public class Auto {

	private int nosnost;
	private int cas;
	
	public void setAtributy(int m, int time){
		this.nosnost=m;
		this.cas=time;
	}
	
	public int zistiCas(){
		return cas;
	}
	
	public int zistiNosnost(){
		return nosnost;
	}
	
	public void nalozAuto(Kontajner kontajner){
		
		this.nosnost=1;
		
	}
	
}
