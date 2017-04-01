package containers;

public class Mraziarenský extends Kontajner {

	private int cena;
	private final int vaha = 2500;			//kg
	private  int zaruka =0;					//roky
	private final int vyrobnycas=250;		//hodiny
											// 1 true - potravinarsky, 2 false iný chemicky...
	
	@Override
	public int zisticenu() {
		// TODO Auto-generated method stub
		return this.cena;
	}

	@Override
	public int zistivahu() {
		// TODO Auto-generated method stub
		return this.vaha;
	}

	@Override
	public int zistizaruku() {
		// TODO Auto-generated method stub
		return this.zaruka;
	}

	@Override
	public int zisticas() {
		// TODO Auto-generated method stub
		return this.vyrobnycas;
	}

	@Override
	public void nastavcenu(int mnozstvo) {
		this.cena=vyrobnycas*10*mnozstvo;
		
	}

	@Override
	public void nastavzaruku(int pocet) {
		this.zaruka=5;
		
	}

}
