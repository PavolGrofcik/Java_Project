package containers;

public abstract class Kontajner {
	int mnozstvo;
	
	//pri kadom kontajner zvláš budem musie vytvori explicitnı konštruktor 
	
	public abstract int zisticenu();
	public abstract int zistivahu();
	public abstract int zistizaruku();
	public abstract int zisticas();
	
	public abstract void nastavcenu(int mnozstvo);
	public abstract void nastavzaruku(int pocet);
	
	
}
