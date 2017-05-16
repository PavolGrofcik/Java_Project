package containers;

/**
 * 
 * @author Pavol Grof��k
 *
 */

public class Mraziarensk� extends Kontajner implements Atributy {

	/**
	 * �pecifick� atrib�ty pre Mraziarensk� kontajner
	 */
	private static final long serialVersionUID = 1L;
	private int range;																//Stupne �C
	private String name;
	
	private final int zaruka = 8;
	private final int hmotnost = 3000;												//kg
	
	
	/**
	 * 																					
	 * @param num Po�et jednotliv�ch kontajnerov
	 */
	public Mraziarensk�(int num) {		
		nastavCenu(num);
		nastavProdT(num);
	}
	
	/**
	 * Kon�truktor
	 * @param typ 		Chladiaci/Hlbokomraziarensk�
	 * @param pocet		Po�et kontajnerov
	 * @param rozsah	Rozsah h�dnot pre typ kontajnera
	 */
	public Mraziarensk�(String typ, int pocet, int rozsah){
		this.range=rozsah;
		if (rozsah>15) {
			this.name="Hlbokomraziarensk�";												//Pod�a rozsahu teploty sa automaticky vygeneruje �i je chladiaci/hlbokomraziarensky
			nastavCenu(2+pocet);														//Poskytnutie zlavy na Hlbokomraziarensk�
			nastavProdT(2+pocet);
		}
		else{
			this.name="Chladiaci";
			nastavCenu(pocet);
			nastavProdT(pocet);
		}
	}
	
	/**
	 * Kon�truktor
	 * @param typ Chladiaci/Hlbokomraziarensk�
	 * @param pocet	Po�et kontajnerov
	 */
	public Mraziarensk�(String typ, int pocet) {
		this.name=typ;
		
		if (typ.equals("Chladiaci")) {
			this.range=15;																//Max range pre Chladiaci
		}
		else{	
			this.range=29;																//Max range pre Hlbokomraziarensk�
		}
		nastavCenu(pocet);																//Nastav� cenu, v�robn� �as podla po�tu kontajnerov
		nastavProdT(pocet);
		
	}
	
	/**
	 * @return Aktu�lna hmotnos�
	 */
	@Override
	public int zistiHmotnost(){
		return this.hmotnost;
	}

	/**
	 *@return Aktu�lna z�ruka 
	 */
	@Override
	public int zistiZaruku() {
		return this.zaruka;
	}

	/**
	 * Nastav� cenu pod�a mno�stva kontajnerov, uplatn� z�avu pri v��om mno�stve
	 */
	@Override
	public void nastavCenu(int mnozstvo) {
		if(mnozstvo>7){
			cena=2000;
		}
		else{
			cena=2500;
		}
	}
	
	/**
	 * Nastav� produk�n� �as, z�visiac od po�tu kontajnerov dan�ho typu
	 * @param mnozstvo Po�et Kontajnerov
	 */
	public void nastavProdT(int mnozstvo){													//Pod�a mno�stva sa ur�uje aj �as na produkciu jednotliv�ch kontajnerov, ��m viac t�m r�chlej�ie
		if (mnozstvo>10) {
			this.prodtime=80;
		}
		else {
			this.prodtime=100;
		}
	}
	
	/**
	 * Nastav� rozsah stup�ov
	 * @param range Zvolen� rozsah chladiacej stupnice pre typ  mraziarensk� typ kontajnera
	 */
	public void nastavRange(int range){														//Met�da nastav� rozsah mraziarenskej stupnice
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
