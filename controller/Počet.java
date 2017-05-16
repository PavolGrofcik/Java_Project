package controller;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
/**
 * 
 * @author Pavol Grof��k
 * Trieda reprezentuje n�vrhov� vzor Observer a v�etky s�visiace met�dy
 */
public class Po�et extends TextField implements SledovatelObjednavky {

	private int po�et=0;
	private Objedn�vka objedn�vka;
	
	/**
	 * Kon�truktor
	 * @param objedn�vka Aktu�lna objedn�vka, po�et objednan�ch kontajnerov
	 */
	public Po�et(Objedn�vka objedn�vka){								//Kon�truktor sl��iaci na vytvorenie a aktualizovanie po�tu pri zmene kontajnerov v objedn�vke
		super();
		this.objedn�vka=objedn�vka;
		setPromptText("0");
		setEditable(false);
		setAlignment(Pos.BASELINE_CENTER);
	}
	
	
	/**
	 * Met�da aktualizuje po�et objednan�ch kontajnerov
	 */
	public void upovedom(){
		po�et=objedn�vka.getPocetKontajnerov();							//zisti aktu�lny po�et kontajnerov
		setText(Integer.toString(po�et));
		setAlignment(Pos.BASELINE_CENTER);
		setEditable(false);												//zamkne textfield aby bol vidite�n� po�et kontajnerov
	}
	
	
	
}
