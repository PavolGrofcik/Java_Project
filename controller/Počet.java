package controller;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
/**
 * 
 * @author Pavol Grofèík
 * Trieda reprezentuje návrhový vzor Observer a všetky súvisiace metódy
 */
public class Poèet extends TextField implements SledovatelObjednavky {

	private int poèet=0;
	private Objednávka objednávka;
	
	/**
	 * Konštruktor
	 * @param objednávka Aktuálna objednávka, poèet objednaných kontajnerov
	 */
	public Poèet(Objednávka objednávka){								//Konštruktor slúžiaci na vytvorenie a aktualizovanie poètu pri zmene kontajnerov v objednávke
		super();
		this.objednávka=objednávka;
		setPromptText("0");
		setEditable(false);
		setAlignment(Pos.BASELINE_CENTER);
	}
	
	
	/**
	 * Metóda aktualizuje poèet objednaných kontajnerov
	 */
	public void upovedom(){
		poèet=objednávka.getPocetKontajnerov();							//zisti aktuálny poèet kontajnerov
		setText(Integer.toString(poèet));
		setAlignment(Pos.BASELINE_CENTER);
		setEditable(false);												//zamkne textfield aby bol vidite¾ný poèet kontajnerov
	}
	
	
	
}
