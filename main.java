package Controller;
import Model.*;


public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stu
				
				coolContainer cool = new coolContainer();
				chemContainer chem = new chemContainer();
				
				cool.setattributes(1500, 140, true);
				cool.setfoody(true);
				cool.setPrice(2000);
				cool.setName("Chladiarensky");
			
				chem.setattributes(1000, 300, false);

				chem.setfoody(true);
				chem.sethazard(3);
				chem.setPrice(chem.checkhazard()*1000);
				chem.setName("Chemický");
				
				System.out.println("Zvolené kontajnery na výrobu:");
				System.out.println("1. " + cool.checkName() + " " + "2. " + chem.checkName());
				System.out.println("Atribúty 1 " + "cena " + cool.checkPrice() + " záruka " + cool.checkwarr() + " Atribúty 2" + " cena " + chem.checkPrice() + " záruka " + chem.checkwarr());
		
	}

}
