package containers;

import java.io.Serializable;

/**
 * @author Pavol Grofèík
 */

/**
 * 
 * Abstraktná trieda slúiaca ako vzor pre konkrétne typy kontajerov so spoloènımi metódami a atribútmi
 *
 */

public abstract class Kontajner implements Serializable{
	/**
	 * Spoloèné atribúty cena a produkèny èas
	 */
	protected int cena;
	protected int prodtime;
	
	/**
	 * Metóda zistí aktuálnu cenu kontajnera
	 * @return Cena  jedného kontajnera
	 */
	public  int zistiCenu(){											//Zistí cenu kadého kontajneru
		
		return this.cena;
	}
	/**
	 * Metóda zistí aktuálny produkènı èas kontajnera
	 * @return Produkènı èas jedného kontajnera
	 */
	public int zistiCas(){												//Zistí produkènı èas daného kontajnera
		
		return this.prodtime;
	}
	
	/**
	 * Overridden metóda
	 * @return Hmotnos kontajnera
	 */
	public abstract int zistiHmotnost();								//Prekonávajúce metódy abstraktnej triedy
	/**
	 * Overridden metóda
	 * @return Záruka kontajnera
	 */
	public abstract int zistiZaruku();
	/**
	 * Overridden metóda
	 * @param mnozstvo Poèet kontajnerov
	 */
	public abstract void nastavCenu(int mnozstvo);		
}
