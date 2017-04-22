package controller;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;

public class Poèet extends TextField implements SledovatelObjednavky {

	private int poèet=0;
	private Objednávka objednávka;
	
	public Poèet(Objednávka objednávka){
		super();
		this.objednávka=objednávka;
	}
	
	
	public void upovedom(){
		poèet=objednávka.getPocetKontajnerov();
		setText(Integer.toString(poèet));
		setAlignment(Pos.BASELINE_CENTER);
	}
	
	
	
}
