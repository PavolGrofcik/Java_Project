package gui;

import java.util.Optional;

import containers.Objedn·vka;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;								//Aby si mohol prid·vaù textfieldy tak musÌö importovaù presne tento import!! nie java.textfield.awt
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class MrazStage extends Stage {
	
	private TextField field = new TextField();							//na poËet kontajnerov
	private TextField field2 = new TextField();							//na rozsah stupnov, ktorÈ si zvoli uûÌvateæ	->	(0 - 30)
	private ComboBox<String> box = new ComboBox<String>();				//UûÌvateæ si zad· Ëi chce vytvoriù hlbokomraziarensk˝ alebo len chladiaci
	private Label specs = new Label("Zadajte öpecifik·cie");
	private Label notification = new Label();
	private Button vytvor = new Button("Pridaj");
	private Button koniec = new Button("Sp‰ù");
	private CheckBox checkBox = new CheckBox("Zadaù rozsah");
	
	
	
	public MrazStage(Objedn·vka objedn·vka) {

		VBox root = new VBox(5);
		
		root.setAlignment(Pos.BASELINE_CENTER);
		setTitle("Mraziarensk˝");								
		initModality(Modality.APPLICATION_MODAL);
		
		
		root.getChildren().addAll(specs,box,field,checkBox,field2,vytvor,notification,koniec);
		
		box.getItems().addAll("Chladiaci", "Hlbokomraziarensk˝");			//naplnenie ComboBoxa items
		
		field.setPromptText("Zadajte mnoûstvo");
		field.setAlignment(Pos.CENTER);
		field.setVisible(true);
		field.setEditable(true);
		
		field2.setVisible(false);
		field2.setEditable(false);
		field2.setAlignment(Pos.CENTER);
		
		checkBox.setSelected(false);										//nastavÌme default hodnotu pre checkbox
		
		checkBox.setOnAction(e->{											//priradenie akcie pre Checkbox ak je selected tak sa zjavÌ textField2, aby program dod·val viac flexibilnosti pre uûÌvateæa
			if(checkBox.isSelected()){
				field2.setVisible(true);
				field2.setEditable(true);
				field2.setPromptText("Zadajte rozsah od 0-30");
			}
			
			else if (!checkBox.isSelected()) {
				field2.setVisible(false);
				field2.setEditable(false);
				field2.clear();
			}
		});
		
		
		
		vytvor.setOnAction(e->{
			//funkcia na vytvorenie mraziarensk˝ch kontajenorov, z·leûÌ na checkbox-e Ëi nie/je selected
			try{
				
				if (checkBox.isSelected()) {
					//Information pre range ak je viac ako bolo povelene
									
					objedn·vka.addMyList(toString().substring(4, 13), Integer.parseInt(field.getText()), box.getSelectionModel().getSelectedItem(), Integer.parseInt(field2.getText()));
					field.clear();
					field2.clear();
					notification.setText("Poloûky boli pridanÈ");
					
				} else {
					objedn·vka.addMyList(toString().substring(4, 13), Integer.parseInt(field.getText()), box.getSelectionModel().getSelectedItem(),0);	//vytvorÌ dan˝ typ kontajnera
					field.clear();
					notification.setText("Poloûky boli pridanÈ");
				}
			
			
			} catch (NumberFormatException e2) {	
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Chyba");
				alert.setHeaderText("Error");
				alert.setContentText("Zadali ste neplatnÈ mnoûstvo");									//Alert pre NumberFormatException
				alert.show();
				
			} catch (Exception e1) {
				Alert alert1 = new Alert(AlertType.ERROR);
				alert1.setTitle("Chyba");
				alert1.setHeaderText("Error");
				alert1.setContentText("Nevyplnili ste niektorÈ polia");								//Alert pre vöeobecnÈ bugs Exception
				alert1.show();
			}
		});
		
		
		koniec.setOnAction(e -> {

			ButtonType buttonOk = new ButtonType("OK");
			ButtonType buttonCancel = new ButtonType("Cancel");

			Alert info = new Alert(AlertType.CONFIRMATION);
			info.setTitle("UkonËiù?");
			info.setHeaderText("Chcete uloûiù a vr·tiù sa spaù?");
			info.setContentText("Vyberte moûnosù");

			Optional<ButtonType> result = info.showAndWait();

			if (result.get() == buttonOk.OK) {
				// skrytie okna
				notification.setText("Zadajte poloûky");
				info.close();
				hide();

			} else if (result.get() == buttonCancel.CANCEL) {
				// okno zost·va nezmenenÈ
				notification.setText("Zadajte poloûky");
				info.close();
			}

		});																//vr·ti sa sp‰ù ku pÙvodnÈmu oknu
		
		Scene scene = new Scene(root, 250,250);
		setResizable(false);
		setScene(scene);
		show();
	
	}
}
