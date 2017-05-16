package containers;

/**
 * 
 * @author Pavol Grofèík
 *
 */

public class Mraziarenský extends Kontajner implements Atributy {

	/**
	 * Špecifické atribúty pre Mraziarenský kontajner
	 */
	private static final long serialVersionUID = 1L;
	private int range;																//Stupne °C
	private String name;
	
	private final int zaruka = 8;
	private final int hmotnost = 3000;												//kg
	
	
	/**
	 * 																					
	 * @param num Poèet jednotlivých kontajnerov
	 */
	public Mraziarenský(int num) {		
		nastavCenu(num);
		nastavProdT(num);
	}
	
	/**
	 * Konštruktor
	 * @param typ 		Chladiaci/Hlbokomraziarenský
	 * @param pocet		Poèet kontajnerov
	 * @param rozsah	Rozsah hôdnot pre typ kontajnera
	 */
	public Mraziarenský(String typ, int pocet, int rozsah){
		this.range=rozsah;
		if (rozsah>15) {
			this.name="Hlbokomraziarenský";												//Pod¾a rozsahu teploty sa automaticky vygeneruje èi je chladiaci/hlbokomraziarensky
			nastavCenu(2+pocet);														//Poskytnutie zlavy na Hlbokomraziarenský
			nastavProdT(2+pocet);
		}
		else{
			this.name="Chladiaci";
			nastavCenu(pocet);
			nastavProdT(pocet);
		}
	}
	
	/**
	 * Konštruktor
	 * @param typ Chladiaci/Hlbokomraziarenský
	 * @param pocet	Poèet kontajnerov
	 */
	public Mraziarenský(String typ, int pocet) {
		this.name=typ;
		
		if (typ.equals("Chladiaci")) {
			this.range=15;																//Max range pre Chladiaci
		}
		else{	
			this.range=29;																//Max range pre Hlbokomraziarenský
		}
		nastavCenu(pocet);																//Nastaví cenu, výrobný èas podla poètu kontajnerov
		nastavProdT(pocet);
		
	}
	
	/**
	 * @return Aktuálna hmotnos
	 */
	@Override
	public int zistiHmotnost(){
		return this.hmotnost;
	}

	/**
	 *@return Aktuálna záruka 
	 */
	@Override
	public int zistiZaruku() {
		return this.zaruka;
	}

	/**
	 * Nastaví cenu pod¾a množstva kontajnerov, uplatní z¾avu pri väèšom množstve
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
	 * Nastaví produkèný èas, závisiac od poètu kontajnerov daného typu
	 * @param mnozstvo Poèet Kontajnerov
	 */
	public void nastavProdT(int mnozstvo){													//Pod¾a množstva sa urèuje aj èas na produkciu jednotlivých kontajnerov, èím viac tým rýchlejšie
		if (mnozstvo>10) {
			this.prodtime=80;
		}
		else {
			this.prodtime=100;
		}
	}
	
	/**
	 * Nastaví rozsah stupòov
	 * @param range Zvolený rozsah chladiacej stupnice pre typ  mraziarenský typ kontajnera
	 */
	public void nastavRange(int range){														//Metóda nastaví rozsah mraziarenskej stupnice
		if (range<0 || range >30) {
			return;
		}
		else{
			if (range>15) {
				this.range=range;
				this.name="Hlbokomraziarenský";
			}
			else{
				this.range=range;
				this.name="Chladiaci";
			}
			
		}
		
	}





}
