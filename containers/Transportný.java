package containers;

public class Transportný extends Kontajner implements Atributy {
	
	private final int hmotnost = 1000;			//kg
	private  int zaruka =5;		

	private int nosnost;
	
	public Transportný(int num){
		nastavCenu(num);
		nastavProdT(num);
	}
	
	
	

	public int zistiZaruku() {
		return this.zaruka;
	}

	public void nastavProdT(int mnozstvo){										//podla množstva sa urèuje aj èas na produkciu jednotlivých kontajnerov, èím viac tým rýchlejšie
		if (mnozstvo>10) {
			this.prodtime=75;
		}
		else {
			this.prodtime=100;
		}
	}
	
	@Override
	public int zistiCas() {
		return this.prodtime;
	}

	@Override
	public void nastavCenu(int mnozstvo) {										//poskytuje zlavu 33% ak objednávka presahuje viac ako 10 položiek
		if (mnozstvo>10) {
			this.cena=1000;												
		}
		else
		this.cena=1500;		
	}
	

	public int zistiHmotnost(){
		return this.hmotnost;
	}
	
	public void nastavNosnost(int vaha){
		if (vaha<5000) {
			this.nosnost=vaha;
		}
	}
	
	public int zistiNosnost(){
		return this.nosnost;
	}


}
