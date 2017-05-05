package kamióny;

import containers.Kontajner;

public class Auto {

	protected int nosnost;
	protected int cas;
	protected boolean nalozeny;
	
	
	public void setAtributy(int m, int time){
		this.nosnost=m;
		this.cas=time;
	}
	
	public int zistiCas(){
		return cas;
	}
	public boolean zistiNalozeny(){
		return this.nalozeny;
	}
	
	public int zistiNosnost(){
		return nosnost;
	}
	
	
	public void nalozAuto(Kontajner kontajner){
		if (kontajner.zistiHmotnost()<=5000) {
		this.nalozeny=true;
		this.nosnost=1;
		}
	}
	
}
