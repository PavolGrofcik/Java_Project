package kamióny;

import containers.Kontajner;

/**
 * 
 * @author Pavol Grofèík
 * Trieda Auto reprezentuje základnú triedu slúiacu ako vzor pre vozidlá Avia, Kamión
 *
 */
public class Auto {

	/**
	 * Spoloèné atribúty
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
	 * Metóda naloí vozidlo ak je hmotnos kontajner prípustna do miery naloenia
	 * @param kontajner Objekt kontajner
	 */
	public void nalozAuto(Kontajner kontajner){
		if (kontajner.zistiHmotnost()<=5000) {
		this.nalozeny=true;
		this.nosnost=1;
		}
	}
	
}
