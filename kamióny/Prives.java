package kamióny;

import containers.Kontajner;
/**
 * 
 * @author Pavol Grofèík
 * Trieda Prives, ktorá prioritne slúia na agregáciu v kamióne
 *
 */
public class Prives {
	/**
	 * Špecifické atribúty objektu Príves
	 * Nosnos max 6000 kg
	 * Boolean nalozeny
	 */
	private final int nosnost=6000;									//kg
	private Boolean nalozeny;										//True / False
	
	/**
	 * Konstruktor
	 */
	public Prives(){												//Konštruktor
		this.nalozeny=false;
	}
	
	/**
	 * Konštruktor
	 * @param kontajner Objekt typu kontajner
	 */
	public Prives(Kontajner kontajner){								//Konštruktor, naloí príves
		nalozPrives(kontajner);
	}
	
	
	public int getNosnost(){										//Zistí nosnost prívesu
			return this.nosnost;
	}
	
	
	/**
	 * Metóda naloí príves s prípustnou hmotnosou max 6000kg jeden typ kontajnera
	 * @param kontajner Objekt typu Kontajner
	 */
	public void nalozPrives(Kontajner kontajner){					//Metóda naloí príves s prípustnou hmotnostou max 6000 kg
		
		if (kontajner.zistiHmotnost()<nosnost) {
			this.nalozeny=true;
		}
		
	}
	
	public boolean getNalozeny(){									//Zisti èi je príves naloenı
		return this.nalozeny;
	}
	
}
