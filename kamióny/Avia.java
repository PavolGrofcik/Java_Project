package kamióny;

import containers.Kontajner;
/**
 * 
 * @author Pavol Grofèík
 * Trieda Avia, špecifikovanı typ vozidla urèenı prioritne na prepravu do krátkych vzdialeností
 */
public class Avia extends Auto implements Export {

	/**
	 * Rıchlos špecifickı atribút
	 */
	private final int rychlost = 1;																//Kadı druh vozidla je špecifickı svojou rıchlosou a nosnosou
	
	/**
	 * Konštruktor
	 */
	public Avia(){
		this.nalozeny=false;
		this.nosnost=6000;
	}
	
	/**
	 * Metóda nalo auto, nedochádza k prekonávaniu, pouije pôvodnú metódu superClass Auto
	 * @param kontajner Objekt typu Kontajner
	 */
	public void nalozAuto(Kontajner kontajner){													//Pôvodná metóda nalo auto zo superClass Auto
		super.nalozAuto(kontajner);
	}
	

	/**
	 * Zistí exportnı èas urèenı pod¾a rıchlosti vozidla
	 * @param vzdialenost Vzdialenos v km
	 */
	@Override
	public int ExportTime(int vzdialenost) {													//Funkcia zistí dobu prepravy
	
		cas=(vzdialenost/rychlost)/100;
		
		return cas;
	}
	
}
