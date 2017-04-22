package containers;

public class Transportn� extends Kontajner implements Atributy {
	
	private final int hmotnost = 1000;			//kg
	private  int zaruka =5;						//roky

	private int nosnost;						//kg
	private String name;						//typ Big alebo Medium
	
	public Transportn�(int num){
		nastavCenu(num);
		nastavProdT(num);
	}
	
	
	//overloading
	public Transportn�(int num, String nazov){
		this.nosnost=num;
		this.name=nazov;
		if(name.equals("Medium")){
			
			nastavCenu(num);							//Error prerobit u Transportneho::::::::::::::::::::::::::::::::::::
			nastavProdT(num);							//Rovnako prerobit:::::::::::::::::::::::::::::::::::
		}
		else if(name.equals("Big")){
			
			nastavCenu(2*num);						//z�le�� aj ak� velkos� m� dan� kontajner Big || Medium
			nastavProdT(2*num);
		}
	}
	
	public int zistiZaruku() {
		return this.zaruka;
	}

	public void nastavProdT(int mnozstvo){										//podla mno�stva sa ur�uje aj �as na produkciu jednotliv�ch kontajnerov, ��m viac t�m r�chlej�ie
		if (mnozstvo>10) {
			this.prodtime=50;
		}
		else {
			this.prodtime=70;
		}
	}
	
	@Override
	public int zistiCas() {
		return this.prodtime;
	}

	@Override
	public void nastavCenu(int mnozstvo) {										//poskytuje zlavu 33% ak objedn�vka presahuje viac ako 10 polo�iek
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
