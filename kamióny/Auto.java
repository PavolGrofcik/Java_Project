package kami�ny;

import containers.Kontajner;

/**
 * 
 * @author Pavol Grof��k
 * Trieda Auto reprezentuje z�kladn� triedu sl��iacu ako vzor pre vozidl� Avia, Kami�n
 *
 */
public class Auto {

	/**
	 * Spolo�n� atrib�ty
	 */
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

	/**
	 * Met�da nalo�� vozidlo ak je hmotnos� kontajner pr�pustna do miery nalo�enia
	 * @param kontajner Objekt kontajner
	 */
	public void nalozAuto(Kontajner kontajner){
		if (kontajner.zistiHmotnost()<=5000) {
		this.nalozeny=true;
		this.nosnost=1;
		}
	}
	
}
