package containers;

import java.io.Serializable;

/**
 * 
 * Abstraktn� trieda sl��iaca ako vzor pre konkr�tne typy kontajerov s abstraktn�mi met�dami
 *
 */

public abstract class Kontajner implements Serializable{
	
	protected int cena;
	protected int prodtime;
	
	/**
	 * 
	 * @return cena kontajnera
	 */
	public  int zistiCenu(){											//zisti cenu ka�deho kontajneru
		
		return this.cena;
	}
	/**
	 * 
	 * @return produk�n� �as
	 */
	public int zistiCas(){												//zisti produk�ny �as daneho kontajnera
		
		return this.prodtime;
	}
	
	public abstract int zistiHmotnost();
	public abstract int zistiZaruku();
	public abstract void nastavCenu(int mnozstvo);		
}


//Odr�tavat cas vyroby loading bar1, odratavat celkov� cas loading bar 2(including prodTime)
//Na konci objedn�vky by sa dalo urobi� nie�o ako zlavov� kup�n, ktor� bud� ulo�en� vo File neake ��sla ak sa neak� bude zhodova� tak sa odr�ta 5% z celkovej ceny(simply checkbox.setOnAction(e->...setvisible field)
// Pre jednu hierarchiu dedenia dorobi� poriadne JavaDoc dokumentaciu aby to bolo zmyslupln� 
//Dorobi� spr�vu o realiz�cii prejekt  2-3 strany, jednoduchy popis ako ako ktro� triedy funguj� plus dorobi� Class Diagram in UML, vysvetlenie vz�ahov