package containers;

import java.io.Serializable;

public abstract class Kontajner implements Serializable{
	
	protected int cena;
	protected int prodtime;
	
	
	public  int zistiCenu(){											//zisti cenu ka�deho kontajneru
		
		return this.cena;
	}
	
	public int zistiCas(){												//zisti produk�ny �as daneho kontajnera
		
		return this.prodtime;
	}
	
	public abstract int zistiHmotnost();
	public abstract int zistiZaruku();
	public abstract void nastavCenu(int mnozstvo);		
}

// V GUI m��e� prida� listview v ktorom budu zobrazene typy jednotliv�ch kontajnerov a spolu s po�tom RTTI aby objasnili ake kontajnery si zakaznik objednal . po�as flow programu => body navyse 
//Vymysliet logiku v preprave kontajnerov, nakladanie kontajnerov, a zoskupi� produk�n� �as spolu s exportnym �asom.
//Odr�tavat cas vyroby loading bar1, odratavat celkov� cas loading bar 2(including prodTime)
//Na konci objedn�vky by sa dalo urobi� nie�o ako zlavov� kup�n, ktor� bud� ulo�en� vo File neake ��sla ak sa neak� bude zhodova� tak sa odr�ta 5% z celkovej ceny(simply checkbox.setOnAction(e->...setvisible field)
// Pre jednu hierarchiu dedenia dorobi� poriadne JavaDoc dokumentaciu aby to bolo zmyslupln� 
//Dorobi� spr�vu o realiz�cii prejekt  2-3 strany, jednoduchy popis ako ako ktro� triedy funguj� plus dorobi� Class Diagram in UML, vysvetlenie vz�ahov
//Nie�o ako zhodnotenie 