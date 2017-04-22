package gui;

import java.io.File;
import java.io.IOException;

import controller.Objedn�vka;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Third extends Stage {
		private Button zistiCenu = new Button("Zisti celkov� cenu");
		private Button nacitaj = new Button("Na��taj polo�ky");
		private Button zisticas = new Button("Zisti �as v�roby");
		private Button zistidni = new Button("Celkove dni");
		private Label label3 = new Label("Dni v�roby");
		private Label label2 = new Label("Celkov� �as v�roby");
		private Label label = new Label("Celkov� cena: ");
		//private TextField textField = new TextField("Hello");
	
	public Third(Objedn�vka list){																//parametrom je arralist s objektami r�znych kontajnerov, v tejto �asti sa dokon�� objedn�vka: t.j zist� celkov� ceny, �as...
		super();
		
		GridPane third = new GridPane();
		
		setTitle("Finaliz�cia objedn�vky");
		third.getChildren().addAll(label,zistiCenu,label2,zisticas,zistidni,label3,nacitaj);			
		third.setAlignment(Pos.TOP_CENTER);
		
		third.setVgap(5);
		third.setHgap(5);
		
		GridPane.setConstraints(label, 1, 0);
		GridPane.setConstraints(nacitaj, 1, 0);
		GridPane.setConstraints(zisticas, 0, 1);
		GridPane.setConstraints(label2, 1, 1);
		GridPane.setConstraints(zistidni, 0, 3);
		GridPane.setConstraints(label3, 1, 3);
		GridPane.setConstraints(nacitaj, 0, 4);
		
		zistiCenu.setOnAction(e->{																//Funkcia zist� celkov� cenu kontajnerov
			label.setText(Integer.toString(list.zistiCenu(list)));
			});
		
		
		zisticas.setOnAction(e->{
			label2.setText(Integer.toString(list.zistiCas(list)));
		});
		
		
		zistidni.setOnAction(e->{
			label3.setText(Integer.toString(list.zistiPocetDni(list)));
		});
		
		
		
		
		nacitaj.setOnAction(e->{																	//funkcia na��ta funk�ne ulo�en� subor s kontajnermi, zatial nahadzuje error no �oskoro to bude pojazdne :)
			FileChooser fc = new FileChooser();
  			fc.setTitle("Nacitaj");
  			File f = fc.showOpenDialog(this);
  			try {
				list.nacitaj(f);
				
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
		});
		
		getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.jpg")));			//nastavenie ikony
		Scene scene = new Scene(third, 450,450);
		setScene(scene);
		show();
		
	}
	
}
