package containers;

public class N�dr� extends Kontajner implements Atributy {
	private  int zaruka =7;					//roky
	private final int hmotnost = 1500;
	
	private int Objem;						//objem v m3 kubick�ch, max 7 m3
	private String Velkost;
	
	
	//Constructor
	public N�dr�(int num, String size) {
		
		this.Velkost=size;
		
		//nastavenie ceny a produk�neho �asu na zaklade v�beru velkosti z�kaznika										
		if(Velkost.equals("Small")){
			nastavCenu(num);
			nastavProdT(num);
			nastavObjem(size);
		}
		else if(Velkost.equals("Medium")){
			nastavCenu(zmenMnozstvo(num,-2));
			nastavProdT(zmenMnozstvo(num,-2));
			nastavObjem(size);
		}else if(Velkost.equals("Big")){
			nastavCenu(zmenMnozstvo(num,-3));
			nastavProdT(zmenMnozstvo(num,-3));
			nastavObjem(size);
		}
	}
	
	//Met�da zmen� mnozstvo o dan� po�et
	//number = zvolene mnozstvo, delta = o kolko zmenit, kon�tanta
	public int zmenMnozstvo(int number, int delta){
		return number-delta;
	}

	
	@Override
	public int zistiZaruku() {
		return this.zaruka;
	}

	@Override
	public void nastavCenu(int mnozstvo) {
		if(mnozstvo>5)
			this.cena=2000;
		else {
			this.cena=2500;
		}
	}
	
	public void nastavProdT(int mnozstvo){										//podla mno�stva sa  automaticky ur�uje aj �as ur�en� na produkciu jednotliv�ch kontajnerov, ��m viac t�m r�chlej�ie
		if (mnozstvo>5) {
			this.prodtime=90;
		}
		else {
			this.prodtime=100;
		}
	}

	
	public void nastavObjem(String objem){
		
		switch (objem) {
		case "Small":{
			this.Objem=3000;
				}		
			break;
		case "Medium":{
			this.Objem=7000;
			}
			break;
		case "Big":{
			this.Objem=10000;
			}
			break;
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
