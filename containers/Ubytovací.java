package containers;


public class Ubytovac� extends Kontajner {
	
	private final int hmotnost = 5000;			//kg
	private  int zaruka =10;					//roky
	
	private int velkost;


	public Ubytovac�(int num) {
		nastavCenu(num);
		nastavProdT(num);
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
		if(mnozstvo>5){
			cena=2500;			//+ zisti velkost
		}
		else{
			cena=3000;
		}
	}
	
	public void nastavProdT(int mnozstvo){										//podla mno�stva sa ur�uje aj �as na produkciu jednotliv�ch kontajnerov, ��m viac t�m r�chlej�ie
		if (mnozstvo>10) {
			this.prodtime=100;
		}
		else {
			this.prodtime=125;
		}
	}

	@Override
	public int zistiHmotnost() {
		return this.hmotnost;
	}
	
	public int zistiVelkost(){
		return this.velkost;
	}
	
	public void nastavVelkost(int velky){
		this.velkost=velky;
	}

}
