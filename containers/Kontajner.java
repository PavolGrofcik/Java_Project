package containers;

import java.io.Serializable;

/**
 * @author Pavol Grof��k
 */

/**
 * 
 * Abstraktn� trieda sl��iaca ako vzor pre konkr�tne typy kontajerov so spolo�n�mi met�dami a atrib�tmi
 *
 */

public abstract class Kontajner implements Serializable{
	/**
	 * Spolo�n� atrib�ty cena a produk�ny �as
	 */
	protected int cena;
	protected int prodtime;
	
	/**
	 * Met�da zist� aktu�lnu cenu kontajnera
	 * @return Cena  jedn�ho kontajnera
	 */
	public  int zistiCenu(){											//Zist� cenu ka�d�ho kontajneru
		
		return this.cena;
	}
	/**
	 * Met�da zist� aktu�lny produk�n� �as kontajnera
	 * @return Produk�n� �as jedn�ho kontajnera
	 */
	public int zistiCas(){												//Zist� produk�n� �as dan�ho kontajnera
		
		return this.prodtime;
	}
	
	/**
	 * Overridden met�da
	 * @return Hmotnos� kontajnera
	 */
	public abstract int zistiHmotnost();								//Prekon�vaj�ce met�dy abstraktnej triedy
	/**
	 * Overridden met�da
	 * @return Z�ruka kontajnera
	 */
	public abstract int zistiZaruku();
	/**
	 * Overridden met�da
	 * @param mnozstvo Po�et kontajnerov
	 */
	public abstract void nastavCenu(int mnozstvo);		
}
