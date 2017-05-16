package kamióny;
/**
 * 
 * Interface slúiaci na implementovanie závislej metódy ExportTime
 *
 */

public interface Export {

																		//Metóda nemohla by obsiahnutá v superclass Auto, pretoe kadı typ subclass má svoju špecifickú rıchlos, ktorou je charakteristickı
	public int ExportTime(int vzdialenost);
	
}
