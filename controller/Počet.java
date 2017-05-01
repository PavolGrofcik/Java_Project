package controller;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;

public class Po�et extends TextField implements SledovatelObjednavky {

	private int po�et=0;
	private Objedn�vka objedn�vka;
	
	public Po�et(Objedn�vka objedn�vka){		//agreg�cia by containment
		super();
		this.objedn�vka=objedn�vka;
	}
	
	
	public void upovedom(){
		po�et=objedn�vka.getPocetKontajnerov();							//zisti aktu�lny po�et kontajnerov
		setText(Integer.toString(po�et));
		setAlignment(Pos.BASELINE_CENTER);
		setEditable(false);												//zamkne textfield aby bol vidite�n� po�et kontajnerov
	}
	
	
	
}
