package containers;

public class N�dr� extends Kontajner implements Atributy {
	private  int zaruka =7;					//roky
	private final int hmotnost = 1000;
	
	private int Objem;						//objem v m3 kubick�ch, max 7 m3
	
	

	public N�dr�(int num) {
	nastavCenu(num);
	nastavProdT(num);
	}


	@Override
	public int zistiZaruku() {
		return this.zaruka;
	}

	@Override
	public int zistiCas() {
		// TODO Auto-generated method stub
		return this.prodtime;
	}

	@Override
	public void nastavCenu(int mnozstvo) {
		if(mnozstvo>10)
			this.cena=2000;
		else {
			this.cena=2500;
		}
	}
	
	public void nastavProdT(int mnozstvo){										//podla mno�stva sa  automaticky ur�uje aj �as ur�en� na produkciu jednotliv�ch kontajnerov, ��m viac t�m r�chlej�ie
		if (mnozstvo>10) {
			this.prodtime=90;
		}
		else {
			this.prodtime=100;
		}
	}

	
	
	public void nastavObjem(int objem){
		if (objem>8 || objem<0) {
			this.Objem=objem;
		}
	}
	
	public int zistiObjem(){
		return this.Objem;
	}

	@Override
	public int zistiHmotnost() {
		return this.hmotnost;
	}

}
