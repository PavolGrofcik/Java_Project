package kamióny;

import containers.Kontajner;

public class Avia extends Auto implements Export {

	private final int rychlost = 1;		//Každý druh vozidla je špecifický svojou rýchlostou a nosnostou
	
	public Avia(){
		this.nalozeny=false;
		this.nosnost=6000;
	}
	
	
	public void nalozAuto(Kontajner kontajner){			//pôvodná metóda
		super.nalozAuto(kontajner);
	}
	
	public int exportTime(int vzdialenost){
		
		this.cas = vzdialenost/rychlost;
		return cas;
	}
	

	@Override
	public int ExportTime(int vzdialenost) {
	
		cas=(vzdialenost/rychlost)/100;
		
		return cas;
	}
	
}
