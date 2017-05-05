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
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Transport extends Stage {
	
	private Label country = new Label("Zvoæte Kraj");
	private Label classtype = new Label("Trieda");
	private Label info = new Label("Aktu·lny poËet");
	private Label savaInfo = new Label("Uloûiù");
	
	private ComboBox<String> countrybox = new ComboBox<String>();														//Kraj SR
	private ComboBox<String> type = new ComboBox<String>();																//Trieda 1./2.
	
	private Button back = new Button("Sp‰ù");
	private Button save = new Button("Uloûiù");
	
	private TextField number = new TextField();
	
	//private PoËet poËet;
	public Transport(Objedn·vka objedn·vka){
		
		super();
		GridPane root = new GridPane();
		
		root.setHgap(3);
		root.setVgap(7);
		root.getChildren().addAll(country,classtype,info,countrybox,type,savaInfo,save,back,number);
		
		initModality(Modality.APPLICATION_MODAL);
		//root.setAlignment(Pos.BASELINE_CENTER);
		
		
		number.setText(Integer.toString(objedn·vka.getPocetKontajnerov()));
		number.setAlignment(Pos.BASELINE_CENTER);
		number.setEditable(false);

		
		GridPane.setConstraints(country, 0, 0);
		GridPane.setConstraints(countrybox, 3, 0);
		GridPane.setConstraints(classtype, 0, 2);
		GridPane.setConstraints(type, 3, 2);
		GridPane.setConstraints(info, 0, 3);
		GridPane.setConstraints(number, 2, 3);
		GridPane.setConstraints(savaInfo, 0, 4);
		GridPane.setConstraints(save, 0, 5);
		GridPane.setConstraints(back, 3, 6);
		
		countrybox.getItems().addAll("Bratislavsk˝", "Banskobystrick˝", "Koöick˝", "Nitriansky", "Preöovsk˝", "TrenËiansky", "Trnavsk˝", "éilinsk˝");
		countrybox.setPromptText("Kraj");
		type.getItems().addAll("1.", "2.");
		type.setPromptText("Typ");
		
		
		// TlaËidlo naloûCargo zmazaù a premiestniù do Fin·lnej triedy
		// Urobiù eöte Rtti do textarey, aspectj platba kartou, 
		
		save.setOnAction(e->{
			try{
			objedn·vka.saveTransport(countrybox.getSelectionModel().getSelectedItem(), type.getSelectionModel().getSelectedItem());
			
			}
			catch (Exception e2){
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Chyba");
				alert.setHeaderText("Error");
				alert.setContentText("Nevyplnili ste niektorÈ polia");									
				alert.show();
			}
			
		});
		
		
		//metÛda tlaËidla sp‰ù
		back.setOnAction(e->{

			Alert exit = new Alert(AlertType.CONFIRMATION);
			exit.setTitle("Uloûiù");
			exit.setHeaderText("Chcete uloûiù objedn·vku?");
			exit.setContentText("Poloûky bud˙ uloûenÈ\n Vyberte moûnosù");
			
			Optional<ButtonType> result = exit.showAndWait();
			
			if(result.get() == ButtonType.OK){
				
				hide();
				exit.close();
			}
			else if(result.get()==ButtonType.CANCEL){
				
				exit.close();
			}
		});
		
		
		getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.jpg")));
		Scene scene = new Scene(root);
		setTitle("Transport");
		setScene(scene);
		setResizable(false);
		show();
	}

}
