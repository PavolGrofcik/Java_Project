package kami�ny;

import containers.Kontajner;

public class Prives {
	
	private final int hmotnost;
	private Boolean nalozeny;
	
	public Prives(int m){												//hmotnos� je useless v tomto pr�pade?? delete ?
		this.hmotnost=m;
		this.nalozeny=false;
	}

	public int zistihmotnost(int hmotnost){
			return this.hmotnost;
	}
	
	public void nalozPrives(Kontajner kontajner){
		this.nalozeny=true;
	}
}
