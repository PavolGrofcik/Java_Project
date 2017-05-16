package kamióny;

import containers.Kontajner;
/**
 * 
 * @author Pavol Grofèík
 * Konkrétny typ vozidla Kamión, ktorı je prioritne urèenı na ïaleké vzdialenosti
 */
public class Kamión extends Auto implements Export{
	
	/**
	 * Špecifické atribúty
	 */
	private final int rychlost = 2;																//Špecifické atribúty pre typ Kamión
	private boolean PrivesFull;
	
	/**
	 * Agregácia hodnotou Príves
	 */
	private Prives prives;																		//Agregácia hodnotou - príves
	
	/**
	 * Konštruktor
	 */
	public Kamión(){
		this.nalozeny=false;
		this.PrivesFull=false;
	}
	
	/**
	 * Konštruktor
	 * @param prives Objekt typu Príves - Agregácia by Containment
	 */
	public Kamión(Prives prives){
		this.prives=prives;
		this.PrivesFull=false;
		this.nalozeny=false;
		this.nosnost=12000;
	}

	/**
	 * Metóda naloí najprv príves s povolenou hmotnosou kontajnera a potom naloí kamión
	 * @param kontajner Objekt typu Kontajner
	 */
	@Override
	public void nalozAuto(Kontajner kontajner){													//Prekonávajúca metóda
	
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
	
																								//Funkcia zisti èi je kamión plnı(naloenı)
	
	public boolean zistiNaklad(){
		return this.nalozeny;
	}
	
																								//Funkcia zisti èi je príves naloeny, prives.get nalozeny
	public boolean zistiNakladPrives() {
		
		return this.PrivesFull;
	}
	
	/**
	 * Prekonávajúca metóda, ktorá zistí aktuálny èas prepravy
	 * @param vzdialenost Vzdialenos v km
	 */
	@Override
	public int ExportTime(int vzdialenost) {													//Zisti pod¾a rıchlosti vozidla èas nutnı na transport

		cas=(vzdialenost/rychlost)/100;
		
		return cas;
	}
	
	
}
