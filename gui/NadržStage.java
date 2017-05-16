package gui;

import java.util.Optional;

import controller.Objedn·vka;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NadrûStage extends Stage {
	
	private Label header = new Label("Zadajte äpecifik·cie");
	private Label title = new Label("Vyberte veækosù");
	private Label notification = new Label();
	private TextField number = new TextField();
	private ComboBox<String> size = new ComboBox<String>();
	private Button add = new Button("Pridaù");
	private Button back = new Button("Sp‰ù");
	
	
	public NadrûStage(Objedn·vka objedn·vka){
		
		VBox root = new VBox(5);
		root.getChildren().addAll(header,number,title,size,add,notification,back);
		
		root.setAlignment(Pos.BASELINE_CENTER);
		initModality(Modality.APPLICATION_MODAL);
		setTitle("N·drû");
		
		//prerobiù automaticke pridavanie podla checboxu zadaù vlastn˝ objem!!!
		size.getItems().addAll("Small", "Medium", "Big");
		size.setPromptText("Zvoæ");
		
		//nastavenie vlastnotÌ Label=u header
		header.setFont(Font.font("Cambria", 20));
		header.setTextFill(Color.MEDIUMPURPLE);
		
		number.setPromptText("Zadajte poËet");
		number.setAlignment(Pos.BASELINE_CENTER);
		number.setEditable(true);
		number.setVisible(true);
		
		add.setOnAction(e->{
			try {
				
				objedn·vka.addMyList(toString().substring(4, 14), Integer.parseInt(number.getText()), size.getSelectionModel().getSelectedItem(), 0);
				number.clear();
				notification.setText("Poloûky boli pridanÈ");
				
			} catch (NumberFormatException nfe) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Chyba");
				alert.setHeaderText("Error");
				alert.setContentText("Zadali ste neplatn˙ hodnotu");									//Alert pre NumberFormatException
				alert.show();
			
			} catch (Exception e2) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Chyba");
				alert.setHeaderText("Error");
				alert.setContentText("Nevyplnili ste niektorÈ polia");									//Alert pre GeneralException
				alert.show();
			}
			
		});
		
		back.setOnAction(e->{
			
			Alert exit = new Alert(AlertType.CONFIRMATION);
			exit.setTitle("UkonËiù?");
			exit.setHeaderText("Chcete uloûiù a vr·tiù sa sp‰ù?");
			exit.setContentText("Zvoæte moûnosù");
			
			Optional<ButtonType> result = exit.showAndWait();
			
			if(result.get() == ButtonType.OK){
				
				hide();
				exit.close();
			}
			else if(result.get()==ButtonType.CANCEL){
				
				notification.setText("Zadajte poloûky");
				exit.close();
			}
		});
		
		

		getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.jpg")));
		Scene scene = new Scene(root, 250,250);
		setResizable(false);
		setScene(scene);
		show();
		
	}

}
