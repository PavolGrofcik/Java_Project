package controller;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;

public class Po�et extends TextField implements SledovatelObjednavky {

	private int po�et=0;
	private Objedn�vka objedn�vka;
	
	public Po�et(Objedn�vka objedn�vka){
		super();
		this.objedn�vka=objedn�vka;
	}
	
	
	public void upovedom(){
		po�et=objedn�vka.getPocetKontajnerov();
		setText(Integer.toString(po�et));
		setAlignment(Pos.BASELINE_CENTER);
	}
	
	
	
}
