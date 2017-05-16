package kami�ny;

import containers.Kontajner;
/**
 * 
 * @author Pavol Grof��k
 * Trieda Avia, �pecifikovan� typ vozidla ur�en� prioritne na prepravu do kr�tkych vzdialenost�
 */
public class Avia extends Auto implements Export {

	/**
	 * R�chlos� �pecifick� atrib�t
	 */
	private final int rychlost = 1;																//Ka�d� druh vozidla je �pecifick� svojou r�chlos�ou a nosnos�ou
	
	/**
	 * Kon�truktor
	 */
	public Avia(){
		this.nalozeny=false;
		this.nosnost=6000;
	}
	
	/**
	 * Met�da nalo� auto, nedoch�dza k prekon�vaniu, pou�ije p�vodn� met�du superClass Auto
	 * @param kontajner Objekt typu Kontajner
	 */
	public void nalozAuto(Kontajner kontajner){													//P�vodn� met�da nalo� auto zo superClass Auto
		super.nalozAuto(kontajner);
	}
	

	/**
	 * Zist� exportn� �as ur�en� pod�a r�chlosti vozidla
	 * @param vzdialenost Vzdialenos� v km
	 */
	@Override
	public int ExportTime(int vzdialenost) {													//Funkcia zist� dobu prepravy
	
		cas=(vzdialenost/rychlost)/100;
		
		return cas;
	}
	
}
