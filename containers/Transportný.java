package containers;

public class Transportný extends Kontajner {

	private int cena;
	private final int vaha = 1000;			//kg
	private  int zaruka =0;					//roky
	private final int vyrobnycas=100;		//hodiny
	
	@Override
	public int zisticenu() {
		return cena;
	}

	@Override
	public int zistivahu() {
		return vaha;
	}

	@Override
	public int zistizaruku() {
		return zaruka;
	}

	@Override
	public int zisticas() {
		return vyrobnycas;
	}

	@Override
	public void nastavcenu(int mnozstvo) {
		this.cena=mnozstvo*1000;

		
	}

	@Override
	public void nastavzaruku(int pocet){
		if (pocet>20) {
			this.zaruka=10;
			
		}
		else {
			this.zaruka=7;
		}
		
	}

}
