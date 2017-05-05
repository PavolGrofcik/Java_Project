package containers;

import java.io.Serializable;

public abstract class Kontajner implements Serializable{
	
	protected int cena;
	protected int prodtime;
	
	
	public  int zistiCenu(){											//zisti cenu kadeho kontajneru
		
		return this.cena;
	}
	
	public int zistiCas(){												//zisti produkèny èas daneho kontajnera
		
		return this.prodtime;
	}
	
	public abstract int zistiHmotnost();
	public abstract int zistiZaruku();
	public abstract void nastavCenu(int mnozstvo);		
}

// V GUI môeš prida listview v ktorom budu zobrazene typy jednotlivıch kontajnerov a spolu s poètom RTTI aby objasnili ake kontajnery si zakaznik objednal . poèas flow programu => body navyse 
//Vymysliet logiku v preprave kontajnerov, nakladanie kontajnerov, a zoskupi produkènı èas spolu s exportnym èasom.
//Odrátavat cas vyroby loading bar1, odratavat celkovı cas loading bar 2(including prodTime)
//Na konci objednávky by sa dalo urobi nieèo ako zlavovı kupón, ktoré budú uloené vo File neake èísla ak sa neaké bude zhodova tak sa odráta 5% z celkovej ceny(simply checkbox.setOnAction(e->...setvisible field)
// Pre jednu hierarchiu dedenia dorobi poriadne JavaDoc dokumentaciu aby to bolo zmysluplné 
//Dorobi správu o realizácii prejekt  2-3 strany, jednoduchy popis ako ako ktroé triedy fungujú plus dorobi Class Diagram in UML, vysvetlenie vzahov
//Nieèo ako zhodnotenie 