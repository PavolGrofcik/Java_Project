package kami�ny;

import containers.Kontajner;

public class Avia extends Auto implements Export {

	private final int rychlost = 1;		//Ka�d� druh vozidla je �pecifick� svojou r�chlostou a nosnostou
	
	public Avia(){
		this.nalozeny=false;
		this.nosnost=6000;
	}
	
	
	public void nalozAuto(Kontajner kontajner){			//p�vodn� met�da
		super.nalozAuto(kontajner);
	}
	

	@Override
	public int ExportTime(int vzdialenost) {
	
		cas=(vzdialenost/rychlost)/100;
		
		return cas;
	}
	
}
