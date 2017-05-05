package gui;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import controller.Nespr·vnyTypVozidla;
import controller.Objedn·vka;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminMode extends Stage {

	private Label typ = new Label("Zadajte druh vozidla");
	private Label mnozstvo = new Label("Zadajte poËet");
	private Label notification = new Label();
	
	private TextField nazov = new TextField();
	private TextField pocet = new TextField();
	private TextField aktual = new TextField();
	
	private Button pridaj = new Button("Pridaù");
	private Button finish = new Button("Uloûiù");
	
	LocalDate date = LocalDate.now();
	
	//Generic method for Nodes
	public void setPosition(Node node, int x, int y){
		
		node.setLayoutX(x);
		node.setLayoutY(y);
	}
	
	public AdminMode(Objedn·vka objedn·vka){
		
		super();
		
		AnchorPane root = new AnchorPane();
		
		root.getChildren().addAll(typ,mnozstvo,notification,nazov,pocet,pridaj,finish,aktual);
		initModality(Modality.APPLICATION_MODAL);
		
		
		notification.setId("Header");
		typ.setId("Typ");
		mnozstvo.setId("Mnozstvo");
		
		
		notification.setText(date.format(DateTimeFormatter.ofPattern("dd.MM.y")));
		
		nazov.setPromptText("Zadajte n·zov vozidla");
		nazov.setAlignment(Pos.BASELINE_CENTER);
		setPosition(nazov, 125, 30);
		
		pocet.setPromptText("Zadajte poËet vozidiel");
		pocet.setAlignment(Pos.BASELINE_CENTER);
		setPosition(pocet, 125, 60);
		
		aktual.setPromptText("Aktu·lny poËet");
		aktual.setAlignment(Pos.BASELINE_CENTER);
		aktual.setEditable(false);
		setPosition(aktual, 125, 90);
		
		
		setPosition(notification, 135, 0);
		setPosition(typ, 0, 30);
		setPosition(mnozstvo, 0, 60);
		setPosition(pridaj, 125, 125);
		setPosition(finish, 170, 175);
		
		pridaj.setOnAction(e->{
			try {
				
				objedn·vka.addCars(Integer.parseInt(pocet.getText()), nazov.getText());
				aktual.setText(Integer.toString(objedn·vka.getCars()));
				pocet.clear();
				nazov.clear();
				
			} catch (Nespr·vnyTypVozidla ex) {
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Chyba");
				alert.setHeaderText("Error");
				alert.setContentText("Zadali ste nespr·vny druh vozidla");									
				alert.show();
			} catch (Exception e2) {
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Chyba");
				alert.setHeaderText("Error");
				alert.setContentText("Zadali ste neplatnÈ ˙daje");									
				alert.show();
			}
		});
		
		finish.setOnAction(e->{
			
			Alert exit = new Alert(AlertType.CONFIRMATION);		
			exit.setTitle("Uloûiù?");
			exit.setHeaderText("Uloûiù a ukonËiù?");
			exit.setContentText("Zvoæte moûnosù");

			Optional<ButtonType> result = exit.showAndWait();

			if (result.get() == ButtonType.OK) {
				exit.close();
				close();
			} else if (result.get() == ButtonType.CLOSE) {
				exit.close();
			}
			
		});
		
		
		getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.jpg")));
		Scene scene = new Scene(root,350,250);
		scene.getStylesheets().add(this.getClass().getResource("/gui/Design.css").toExternalForm());
		setTitle("Admin Mode");
		setScene(scene);
		show();
		
		
	}
	
	
}
