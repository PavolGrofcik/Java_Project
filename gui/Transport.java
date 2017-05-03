package gui;

import java.util.Optional;

import controller.Objedn�vka;
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
	
	private Label country = new Label("Zvo�te Kraj");
	private Label classtype = new Label("Trieda");
	private Label info = new Label("Aktu�lny po�et");
	private Label setupcargo = new Label("Nalo� cargo");
	
	private ComboBox<String> countrybox = new ComboBox<String>();														//Kraj SR
	private ComboBox<String> type = new ComboBox<String>();																//Trieda 1./2.
	
	private Button load = new Button("Nalo�i�");
	private Button back = new Button("Sp�");
	
	private TextField number = new TextField();
	//private Po�et po�et;
	public Transport(Objedn�vka objedn�vka){
		
		super();
		GridPane root = new GridPane();
		
		root.setHgap(3);
		root.setVgap(7);
		root.getChildren().addAll(country,classtype,info,setupcargo,countrybox,type,load,back,number);
		
		initModality(Modality.APPLICATION_MODAL);
		//root.setAlignment(Pos.BASELINE_CENTER);
		
		
		number.setText(Integer.toString(objedn�vka.getPocetKontajnerov()));
		number.setAlignment(Pos.BASELINE_CENTER);
		number.setEditable(false);

		
		GridPane.setConstraints(country, 0, 0);
		GridPane.setConstraints(countrybox, 3, 0);
		GridPane.setConstraints(classtype, 0, 2);
		GridPane.setConstraints(type, 3, 2);
		GridPane.setConstraints(info, 0, 3);
		GridPane.setConstraints(number, 2, 3);
		GridPane.setConstraints(setupcargo, 1, 4);
		GridPane.setConstraints(load, 1, 5);
		GridPane.setConstraints(back, 3, 6);
		
		countrybox.getItems().addAll("Bratislavsk�", "Banskobystrick�", "Ko�ick�", "Nitriansky", "Pre�ovsk�", "Tren�iansky", "Trnavsk�", "�ilinsk�");
		countrybox.setPromptText("Kraj");
		type.getItems().addAll("1.", "2.");
		type.setPromptText("Typ");
		
		
		back.setOnAction(e->{

			
			Alert exit = new Alert(AlertType.CONFIRMATION);
			exit.setTitle("Ukon�i�?");
			exit.setHeaderText("Chcete v�ne zru�i� objedn�vku?");
			exit.setContentText("V�etky polo�ky budu zmazan�\n Vyberte mo�nos�");
			
			Optional<ButtonType> result = exit.showAndWait();
			
			if(result.get() == ButtonType.OK){
				
				//zma�e v�etok progres
				objedn�vka.zma�();
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
