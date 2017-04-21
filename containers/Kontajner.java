package containers;

public abstract class Kontajner {
	protected int cena;
	protected int prodtime;
	
	//pri ka�dom kontajneri zvl᚝ budem musie� vytvori� explicitn� kon�truktor + override methods from abstract class
	
	public  int zistiCenu(){											//zisti cenu ka�deho kontajneru
		return this.cena;
	}
	
	public abstract int zistiHmotnost();
	public abstract int zistiZaruku();
	public abstract int zistiCas();
	public abstract void nastavCenu(int mnozstvo);		
}

// V GUI m��e� prida� listview v ktorom budu zobrazene typy jednotliv�ch kontajnerov a spolu s po�tom RTTI aby objasnili ake kontajnery si zakaznik objednal . po�as flow programu => body navyse 
//Vymysliet logiku v preprave kontajnerov, nakladanie kontajnerov, a zoskupi� produk�n� �as spolu s exportnym �asom.
//Odr�tavat cas vyroby loading bar1, odratavat celkov� cas loading bar 2(including prodTime)
//Na konci objedn�vky by sa dalo urobi� nie�o ako zlavov� kup�n, ktor� bud� ulo�en� vo File neake ��sla ak sa neak� bude zhodova� tak sa odr�ta 5% z celkovej ceny(simply checkbox.setOnAction(e->...setvisible field)
