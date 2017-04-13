package gui;

import java.io.File;
import java.io.IOException;
import containers.Arraylist;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Third extends Stage {
		private Button zistiCenu = new Button("Zisti celkovú cenu");
		private Button nacitaj = new Button("Naèítaj položky");
		private Button zisticas = new Button("Zisti èas výroby");
		private Button zistidni = new Button("Celkove dni");
		private Label label3 = new Label("Dni výroby");
		private Label label2 = new Label("Celkový èas výroby");
		private Label label = new Label("Celková cena: ");
		
	
	public Third(Arraylist list){																//parametrom je arralist s objektami rôznych kontajnerov, v tejto èasti sa dokonèí objednávka: t.j zistí celkové ceny, èas...
		super();
		
		GridPane third = new GridPane();
		
		setTitle("Finalizácia objednávky");
		third.getChildren().addAll(label,zistiCenu,label2,zisticas,zistidni,label3,nacitaj);			
		third.setAlignment(Pos.TOP_CENTER);
		
		third.setVgap(5);
		third.setHgap(5);
		
		third.setConstraints(label, 1, 0);
		third.setConstraints(nacitaj, 1, 0);
		third.setConstraints(zisticas, 0, 1);
		third.setConstraints(label2, 1, 1);
		third.setConstraints(zistidni, 0, 3);
		third.setConstraints(label3, 1, 3);
		third.setConstraints(nacitaj, 0, 4);
		
		
		
		zistiCenu.setOnAction(e->{																//Funkcia zistí celkovú cenu kontajnerov
			label.setText(Integer.toString(list.zistiCenu(list)));
			});
		
		
		zisticas.setOnAction(e->{
			label2.setText(Integer.toString(list.zistiCas(list)));
		});
		
		
		zistidni.setOnAction(e->{
			label3.setText(Integer.toString(list.zistiPocetDni(list)));
		});
		
		
		
		
		nacitaj.setOnAction(e->{																	//funkcia naèíta funkène uložený subor s kontajnermi, zatial nahadzuje error no èoskoro to bude pojazdne :)
			FileChooser fc = new FileChooser();
  			fc.setTitle("Nacitaj");
  			File f = fc.showOpenDialog(this);
  			try {
				list.nacitaj(f);
				
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
		});
		
		Scene scene = new Scene(third, 450,450);
		setScene(scene);
		show();
		
	}
	
}
