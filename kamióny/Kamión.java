package kami�ny;

import containers.Kontajner;
/**
 * 
 * @author Pavol Grof��k
 * Konkr�tny typ vozidla Kami�n, ktor� je prioritne ur�en� na �alek� vzdialenosti
 */
public class Kami�n extends Auto implements Export{
	
	/**
	 * �pecifick� atrib�ty
	 */
	private final int rychlost = 2;																//�pecifick� atrib�ty pre typ Kami�n
	private boolean PrivesFull;
	
	/**
	 * Agreg�cia hodnotou Pr�ves
	 */
	private Prives prives;																		//Agreg�cia hodnotou - pr�ves
	
	/**
	 * Kon�truktor
	 */
	public Kami�n(){
		this.nalozeny=false;
		this.PrivesFull=false;
	}
	
	/**
	 * Kon�truktor
	 * @param prives Objekt typu Pr�ves - Agreg�cia by Containment
	 */
	public Kami�n(Prives prives){
		this.prives=prives;
		this.PrivesFull=false;
		this.nalozeny=false;
		this.nosnost=12000;
	}

	/**
	 * Met�da nalo�� najprv pr�ves s povolenou hmotnos�ou kontajnera a potom nalo�� kami�n
	 * @param kontajner Objekt typu Kontajner
	 */
	@Override
	public void nalozAuto(Kontajner kontajner){													//Prekon�vaj�ca met�da
	
		if(kontajner.zistiHmotnost()<6000){
			if (!PrivesFull) {
				this.PrivesFull=true;
				prives.nalozPrives(kontajner);
			}
			else{
				this.nalozeny=true;
			}
		}
	}
	
																								//Funkcia zisti �i je kami�n pln�(nalo�en�)
	
	public boolean zistiNaklad(){
		return this.nalozeny;
	}
	
																								//Funkcia zisti �i je pr�ves nalo�eny, prives.get nalozeny
	public boolean zistiNakladPrives() {
		
		return this.PrivesFull;
	}
	
	/**
	 * Prekon�vaj�ca met�da, ktor� zist� aktu�lny �as prepravy
	 * @param vzdialenost Vzdialenos� v km
	 */
	@Override
	public int ExportTime(int vzdialenost) {													//Zisti pod�a r�chlosti vozidla �as nutn� na transport

		cas=(vzdialenost/rychlost)/100;
		
		return cas;
	}
	
	
}
