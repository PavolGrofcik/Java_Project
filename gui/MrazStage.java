package gui;

import java.util.Optional;

import controller.Objedn�vka;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;								//Aby si mohol prid�va� textfieldy tak mus� importova� presne tento import!! nie java.textfield.awt
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import controller.NespravnyRozsah;


public class MrazStage extends Stage {
	
	private TextField field = new TextField();							//na po�et kontajnerov
	private TextField field2 = new TextField();							//na rozsah stupnov, ktor� si zvoli u��vate�	->	(0 - 30)
	private ComboBox<String> box = new ComboBox<String>();				//U��vate� si zad� �i chce vytvori� hlbokomraziarensk� alebo len chladiaci
	private Label specs = new Label("Zadajte �pecifik�cie");
	private Label notification = new Label();
	private Button vytvor = new Button("Prida�");
	private Button koniec = new Button("Sp�");
	private CheckBox checkBox = new CheckBox("Zada� rozsah");
	
	
	
	public MrazStage(Objedn�vka objedn�vka) {

		VBox root = new VBox(5);
		
		root.getChildren().addAll(specs,box,field,checkBox,field2,vytvor,notification,koniec);
		
		root.setAlignment(Pos.BASELINE_CENTER);
		setTitle("Mraziarensk�");								
		initModality(Modality.APPLICATION_MODAL);
		
		box.getItems().addAll("Chladiaci", "Hlbokomraziarensk�");												//naplnenie ComboBoxa items
		box.setPromptText("Zvo�");
		
		specs.setFont(Font.font("Cambria", 20));
		specs.setTextFill(Color.DARKORANGE);
			
		
		field.setPromptText("Zadajte mno�stvo");
		field.setAlignment(Pos.CENTER);
		field.setVisible(true);
		field.setEditable(true);
		
		field2.setVisible(false);
		field2.setEditable(false);
		field2.setAlignment(Pos.CENTER);
		
		checkBox.setSelected(false);																		//nastav�me default hodnotu pre checkbox
		
		checkBox.setOnAction(e->{						//priradenie akcie pre Checkbox ak je selected tak sa zjav� textField2, aby program dod�val viac flexibilnosti pre u��vate�a
			if(checkBox.isSelected()){
				field2.setVisible(true);
				field2.setEditable(true);
				field2.setPromptText("Zadajte rozsah od 5-30");
			}
			
			else if (!checkBox.isSelected()) {
				field2.setVisible(false);
				field2.setEditable(false);
				field2.clear();
			}
		});
		
		
		
		vytvor.setOnAction(e->{
			//funkcia na vytvorenie mraziarensk�ch kontajenorov, z�le�� na checkbox-e �i nie/je selected
			try{
				
				if (checkBox.isSelected()) {
					//Information pre range ak je viac ako bolo povelene
									
					objedn�vka.addMyList(toString().substring(4, 13), Integer.parseInt(field.getText()), box.getSelectionModel().getSelectedItem(), Integer.parseInt(field2.getText()));
					field.clear();
					field2.clear();
					notification.setText("Polo�ky boli pridan�");
					
				} else {
					objedn�vka.addMyList(toString().substring(4, 13), Integer.parseInt(field.getText()), box.getSelectionModel().getSelectedItem(),0);	//vytvor� dan� typ kontajnera
					field.clear();
					notification.setText("Polo�ky boli pridan�");
				}
			
			
			} catch (NespravnyRozsah nrz) {
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Chyba");
				alert.setHeaderText("Error");
				alert.setContentText("Zadali ste neplatn� rozsah pre zadan� typ kontajnera");									
				alert.show();
			
			} catch (NumberFormatException e2) {	
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Chyba");
				alert.setHeaderText("Error");
				alert.setContentText("Zadali ste neplatn� mno�stvo");									//Alert pre NumberFormatException
				alert.show();
				
			} catch (Exception e1) {
				Alert alert1 = new Alert(AlertType.ERROR);
				alert1.setTitle("Chyba");
				alert1.setHeaderText("Error");
				alert1.setContentText("Nevyplnili ste niektor� polia");									//Alert pre GeneralException
				alert1.show();
			}
		});
		
		
		koniec.setOnAction(e -> {

			Alert info = new Alert(AlertType.CONFIRMATION);
			info.setTitle("Ukon�i�?");
			info.setHeaderText("Chcete ulo�i� a vr�ti� sa spa�?");
			info.setContentText("Zvo�te mo�nos�");

			Optional<ButtonType> result = info.showAndWait();

			if (result.get() == ButtonType.OK) {
				// skrytie okna
				info.close();
				hide();																					//hide() je ekvivalentn� ku close() from docs.oracle...

			} else if (result.get() == ButtonType.CANCEL) {
				// okno zost�va nezmenen�
				notification.setText("Zadajte polo�ky");
				info.close();
			}

		});																								//vr�ti sa sp� ku p�vodn�mu oknu
		
		Scene scene = new Scene(root, 250,250);
		getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.jpg")));
		setResizable(false);
		setScene(scene);
		show();
	
	}
}
