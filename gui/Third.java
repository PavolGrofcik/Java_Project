package gui;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import controller.Objedn·vka;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Third extends Stage {
		private Button zistiCenu = new Button("Zisti celkov˙ cenu");
		private Button nacitaj = new Button("NaËÌtaj poloûky");
		private Button zisticas = new Button("Zisti Ëas v˝roby");
		private Button zistidni = new Button("Celkove dni");
		private Button back = new Button("Sp‰ù");
		private Label label3 = new Label("Dni v˝roby");
		private Label label2 = new Label("Celkov˝ Ëas v˝roby");
		private Label label = new Label("Celkov· cena: ");
	
	public Third(Objedn·vka objedn·vka){																//parametrom je arralist s objektami rÙznych kontajnerov, v tejto Ëasti sa dokonËÌ objedn·vka: t.j zistÌ celkovÈ ceny, Ëas...
		super();
		
		GridPane third = new GridPane();
		
		third.getChildren().addAll(label,zistiCenu,label2,zisticas,zistidni,label3,nacitaj,back);			
		third.setAlignment(Pos.TOP_CENTER);
		initModality(Modality.APPLICATION_MODAL);
		setTitle("Finaliz·cia objedn·vky");
		
		third.setVgap(5);
		third.setHgap(5);
		
		GridPane.setConstraints(label, 1, 0);
		GridPane.setConstraints(nacitaj, 1, 0);
		GridPane.setConstraints(zisticas, 0, 1);
		GridPane.setConstraints(label2, 1, 1);
		GridPane.setConstraints(zistidni, 0, 3);
		GridPane.setConstraints(label3, 1, 3);
		GridPane.setConstraints(nacitaj, 0, 4);
		GridPane.setConstraints(back, 0, 5);
		
		zistiCenu.setOnAction(e->{																//Funkcia zistÌ celkov˙ cenu kontajnerov
			label.setText(Integer.toString(objedn·vka.zistiCenu(objedn·vka)));
			});
		
		
		zisticas.setOnAction(e->{
			label2.setText(Integer.toString(objedn·vka.zistiCas(objedn·vka)));
		});
		
		
		zistidni.setOnAction(e->{
			label3.setText(Integer.toString(objedn·vka.zistiPocetDni(objedn·vka)));
		});
		
		
		
		
		nacitaj.setOnAction(e->{																	//funkcia naËÌta funkËne uloûen˝ subor s kontajnermi, zatial nahadzuje error no Ëoskoro to bude pojazdne :)
			FileChooser fc = new FileChooser();
  			fc.setTitle("NaËÌtaù");
  			File f = fc.showOpenDialog(this);
  			try {
				objedn·vka.nacitaj(f);
				
			} catch (ClassNotFoundException | IOException e1) {
				Alert alert1 = new Alert(AlertType.ERROR);
				alert1.setTitle("Chyba");
				alert1.setHeaderText("Error");
				alert1.setContentText("NenaËital sa ûiadny s˙bor");									//Alert pre GeneralException
				alert1.show();
			}
		});
		
		
		
		
		back.setOnAction(e->{
			ButtonType buttonOk= new ButtonType("OK");
			ButtonType buttonClose = new ButtonType("Close");
			
			Alert exit = new Alert(AlertType.CONFIRMATION);
			exit.setTitle("UkonËiù?");
			exit.setHeaderText("Chcete sa chcete vr·tiù sp‰ù");
			exit.setContentText("NiektorÈ zmeny sa neuloûia\n Vyberte moûnosù");
			
			Optional<ButtonType> result = exit.showAndWait();
			
			if(result.get() == ButtonType.OK){
				
				hide();
				exit.close();
			}
			else if(result.get()==ButtonType.CANCEL){
				
				//lbl1.setText("Zadajte poloûky");		dokonËiù
				exit.close();
			}
		});
		
		getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.jpg")));			//nastavenie ikony
		Scene scene = new Scene(third, 450,450);
		setScene(scene);
		show();
		
	}
	
}
