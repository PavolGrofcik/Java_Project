package controller;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;

public class Poèet extends TextField implements SledovatelObjednavky {

	private int poèet=0;
	private Objednávka objednávka;
	
	public Poèet(Objednávka objednávka){		//agregácia by containment
		super();
		this.objednávka=objednávka;
	}
	
	
	public void upovedom(){
		poèet=objednávka.getPocetKontajnerov();							//zisti aktuálny poèet kontajnerov
		setText(Integer.toString(poèet));
		setAlignment(Pos.BASELINE_CENTER);
		setEditable(false);												//zamkne textfield aby bol vidite¾ný poèet kontajnerov
	}
	
	
	
}
