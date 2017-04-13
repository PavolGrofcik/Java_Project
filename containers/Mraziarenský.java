package containers;

public class Mraziarenský extends Kontajner implements Atributy {

	private int range;
	private String name;
	
	private final int zaruka = 8;
	private final int hmotnost = 3000;						
	
	
	public Mraziarenský(int num) {		
		nastavCenu(num);
		nastavProdT(num);
	}
	

	@Override
	public int zistiHmotnost(){
		return this.hmotnost;
	}

	@Override
	public int zistiZaruku() {
		return this.zaruka;
	}

	@Override
	public int zistiCas() {
		return this.prodtime;
	}

	@Override
	public void nastavCenu(int mnozstvo) {
		if(mnozstvo>7){
			cena=2000;
		}
		else{
			cena=2500;
		}
	}
	
	public void nastavProdT(int mnozstvo){										//podla množstva sa urèuje aj èas na produkciu jednotlivých kontajnerov, èím viac tým rýchlejšie
		if (mnozstvo>10) {
			this.prodtime=80;
		}
		else {
			this.prodtime=100;
		}
	}
	
	public void nastavRange(int range){
		if (range<0 || range >30) {
			return;
		}
		else{
			if (range<15) {
				this.range=range;
				this.name="Potravinársky";
			}
			else{
				this.range=range;
				this.name="Hlbokomraziarensky";
			}
			
		}
		
	}





}
