package kami�ny;

import containers.Kontajner;
/**
 * 
 * @author Pavol Grof��k
 * Trieda Prives, ktor� prioritne sl��ia na agreg�ciu v kami�ne
 *
 */
public class Prives {
	/**
	 * �pecifick� atrib�ty objektu Pr�ves
	 * Nosnos� max 6000 kg
	 * Boolean nalozeny
	 */
	private final int nosnost=6000;									//kg
	private Boolean nalozeny;										//True / False
	
	/**
	 * Konstruktor
	 */
	public Prives(){												//Kon�truktor
		this.nalozeny=false;
	}
	
	/**
	 * Kon�truktor
	 * @param kontajner Objekt typu kontajner
	 */
	public Prives(Kontajner kontajner){								//Kon�truktor, nalo�� pr�ves
		nalozPrives(kontajner);
	}
	
	
	public int getNosnost(){										//Zist� nosnost pr�vesu
			return this.nosnost;
	}
	
	
	/**
	 * Met�da nalo�� pr�ves s pr�pustnou hmotnos�ou max 6000kg jeden typ kontajnera
	 * @param kontajner Objekt typu Kontajner
	 */
	public void nalozPrives(Kontajner kontajner){					//Met�da nalo�� pr�ves s pr�pustnou hmotnostou max 6000 kg
		
		if (kontajner.zistiHmotnost()<nosnost) {
			this.nalozeny=true;
		}
		
	}
	
	public boolean getNalozeny(){									//Zisti �i je pr�ves nalo�en�
		return this.nalozeny;
	}
	
}
