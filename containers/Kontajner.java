package containers;

public abstract class Kontajner {
	protected int cena;
	protected int prodtime;
	
	//pri kadom kontajneri zvláš budem musie vytvori explicitnı konštruktor + override methods from abstract class
	
	public  int zistiCenu(){
		return this.cena;
	}
	
	public abstract int zistiHmotnost();
	public abstract int zistiZaruku();
	public abstract int zistiCas();
	public abstract void nastavCenu(int mnozstvo);
		
}
// V GUI môeš prida listview v ktorom budu zobrazene typy jednotlivıch kontajnerov a spolu s poètom RTTI aby objasnili ake kontajnery si zakaznik objednal . poèas flow programu => body navyse 
//Vymysliet logiku v preprave kontajnerov, nakladanie kontajnerov, a zoskupi produkènı èas spolu s exportnym èasom.
//Odrátavat cas vyrobu loading bar1, odratavat celkovı cas loading bar 2(including prodTime)
//Na konci objednávky by sa dalo urobi nieèo ako zlavovı kupón, ktoré budú uloené vo File neake èísla ak sa neaké bude zhodova tak sa odráta 5% z celkovej ceny
