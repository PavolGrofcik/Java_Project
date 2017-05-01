package containers;

import java.io.Serializable;

public abstract class Kontajner implements Serializable{
	
	protected int cena;
	protected int prodtime;
	
	
	public  int zistiCenu(){											//zisti cenu každeho kontajneru
		
		return this.cena;
	}
	
	public int zistiCas(){												//zisti produkèny èas daneho kontajnera
		
		return this.prodtime;
	}
	
	public abstract int zistiHmotnost();
	public abstract int zistiZaruku();
	public abstract void nastavCenu(int mnozstvo);		
}

// V GUI môžeš prida listview v ktorom budu zobrazene typy jednotlivých kontajnerov a spolu s poètom RTTI aby objasnili ake kontajnery si zakaznik objednal . poèas flow programu => body navyse 
//Vymysliet logiku v preprave kontajnerov, nakladanie kontajnerov, a zoskupi produkèný èas spolu s exportnym èasom.
//Odrátavat cas vyroby loading bar1, odratavat celkový cas loading bar 2(including prodTime)
//Na konci objednávky by sa dalo urobi nieèo ako zlavový kupón, ktoré budú uložené vo File neake èísla ak sa neaké bude zhodova tak sa odráta 5% z celkovej ceny(simply checkbox.setOnAction(e->...setvisible field)
