package containers;

public class Mraziarensk� extends Kontajner implements Atributy {

	private int range;
	private String name;
	
	private final int zaruka = 8;
	private final int hmotnost = 3000;						
	
	
	//pre�a�enie
	public Mraziarensk�(int num) {		
		nastavCenu(num);
		nastavProdT(num);
	}
	
	//pre�a�enie
	public Mraziarensk�(String typ, int pocet, int rozsah) {
		//this.name=typ;	ur�� sa automaticky podla hodnoty rozsahu
		this.range=rozsah;
		if (rozsah>15) {
			this.name="Hlbokomraziarensk�";							//podla rozsahu teploty sa automaticky vygeneruje �i je chladiaci/hlbokomraziarensky
		}
		else{
			this.name="Chladiaci";
		}

		nastavCenu(pocet);
		nastavProdT(pocet);
	}
	//pre�a�enie
	public Mraziarensk�(String typ, int pocet) {
		this.name=typ;
		
		if (typ.equals("Chladiaci")) {
			this.range=15;
		}
		else{
			this.range=29;
		}
		nastavCenu(pocet);
		nastavProdT(pocet);
		
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
	
	public void nastavProdT(int mnozstvo){										//podla mno�stva sa ur�uje aj �as na produkciu jednotliv�ch kontajnerov, ��m viac t�m r�chlej�ie
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
			if (range>15) {
				this.range=range;
				this.name="Hlbokomraziarensk�";
			}
			else{
				this.range=range;
				this.name="Chladiaci";
			}
			
		}
		
	}





}
