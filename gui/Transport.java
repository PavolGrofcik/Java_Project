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
	private Label saveInfo = new Label("Ulo�i�");
	private Label mesto = new Label("Mesto");
	
	
	private ComboBox<String> countrybox = new ComboBox<String>();														//Kraj SR
	private ComboBox<String> type = new ComboBox<String>();																//Trieda 1./2.
	private ComboBox<String> city = new ComboBox<String>();																//Mesto
	
	private Button back = new Button("Sp�");
	private Button save = new Button("Ulo�i�");
	
	private TextField number = new TextField();
	
	private String [] BA = {"Bratislava", "Pezinok", "Malacky"};
	private String [] TT = {"Trnava", "Galanta", "Senica"};
	private String [] TN = {"Tren��n", "Prievidza", "P�chov"};
	private String [] NR = {"Nitra", "Kom�rno", "Levice"};
	private String [] BB = {"Bansk� Bystrica", "Ro��ava", "Fi�akovo"};
	private String [] ZA = {"�ilina", "Martin", "Tvrdo��n"};
	private String [] PE = {"Pre�ov", "Stropkov", "Humenn�"};
	private String [] KE = {"Ko�ice", "Michalovce", "Vy�n� Nemeck�"};
	

	public Transport(Objedn�vka objedn�vka){
		
		super();
		GridPane root = new GridPane();
		
		root.setHgap(3);
		root.setVgap(7);
		root.getChildren().addAll(country,classtype,info,countrybox,type,saveInfo,save,back,number,mesto,city);
		
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
		GridPane.setConstraints(saveInfo, 0, 4);
		GridPane.setConstraints(save, 0, 5);
		GridPane.setConstraints(back, 3, 6);
		GridPane.setConstraints(mesto, 0, 1);
		GridPane.setConstraints(city, 3, 1);
		
		mesto.setVisible(false);
		city.setPromptText("Mesto");
		city.setVisible(false);
		
		countrybox.getItems().addAll("Bratislavsk�", "Banskobystrick�", "Ko�ick�", "Nitriansky", "Pre�ovsk�", "Tren�iansky", "Trnavsk�", "�ilinsk�");
		countrybox.setPromptText("Kraj");
		type.getItems().addAll("1.", "2.");
		type.setPromptText("Typ");
		
		countrybox.setOnAction(e -> {

			if (!city.getSelectionModel().isEmpty()) {
				
				city.getSelectionModel().clearSelection();
				city.getItems().clear();
			
				switch (countrybox.getSelectionModel().getSelectedItem()) {

				case "Bratislavsk�":
					mesto.setVisible(true);
					city.setVisible(true);
					city.getItems().addAll(BA);
					break;

				case "Banskobystrick�":
					mesto.setVisible(true);
					city.setVisible(true);
					city.getItems().addAll(BB);
					break;
				case "Ko�ick�":
					mesto.setVisible(true);
					city.setVisible(true);
					city.getItems().addAll(KE);
					break;
				case "Nitriansky":
					mesto.setVisible(true);
					city.setVisible(true);
					city.getItems().addAll(NR);
					break;
				case "Pre�ovsk�":
					mesto.setVisible(true);
					city.setVisible(true);
					city.getItems().addAll(PE);
					break;
				case "Tren�iansky":
					mesto.setVisible(true);
					city.setVisible(true);
					city.getItems().addAll(TN);
					break;
				case "Trnavsk�":
					mesto.setVisible(true);
					city.setVisible(true);
					city.getItems().addAll(TT);
					break;
				case "�ilinsk�":
					mesto.setVisible(true);
					city.setVisible(true);
					city.getItems().addAll(ZA);
					break;
				}
			}
			else{
				
				city.getSelectionModel().clearSelection();
				city.getItems().clear();
				
				switch (countrybox.getSelectionModel().getSelectedItem()) {

				case "Bratislavsk�":
					mesto.setVisible(true);
					city.setVisible(true);
					city.getItems().addAll(BA);
					break;

				case "Banskobystrick�":
					mesto.setVisible(true);
					city.setVisible(true);
					city.getItems().addAll(BB);
					break;
				case "Ko�ick�":
					mesto.setVisible(true);
					city.setVisible(true);
					city.getItems().addAll(KE);
					break;
				case "Nitriansky":
					mesto.setVisible(true);
					city.setVisible(true);
					city.getItems().addAll(NR);
					break;
				case "Pre�ovsk�":
					mesto.setVisible(true);
					city.setVisible(true);
					city.getItems().addAll(PE);
					break;
				case "Tren�iansky":
					mesto.setVisible(true);
					city.setVisible(true);
					city.getItems().addAll(TN);
					break;
				case "Trnavsk�":
					mesto.setVisible(true);
					city.setVisible(true);
					city.getItems().addAll(TT);
					break;
				case "�ilinsk�":
					mesto.setVisible(true);
					city.setVisible(true);
					city.getItems().addAll(ZA);
					break;
				}
			}
			
		});
		
		
		
		
		// Tla�idlo nalo�Cargo zmaza� a premiestni� do Fin�lnej triedy
		// Urobi� e�te Rtti do textarey, aspectj platba kartou, 
		
		save.setOnAction(e -> {
			try {

				objedn�vka.saveTransport(countrybox.getSelectionModel().getSelectedItem(),
				type.getSelectionModel().getSelectedItem(), city.getSelectionModel().getSelectedItem());

				Alert info = new Alert(AlertType.INFORMATION);

				info.setTitle("Ulo�en�");
				info.setHeaderText("�daje boli ulo�en�");
				info.setContentText("Dokon�ite objedn�vku");
				info.show();

			} catch (Exception e2) {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Chyba");
				alert.setHeaderText("Error");
				alert.setContentText("Nevyplnili ste niektor� polia");
				alert.show();
			}

		});
		
		
		//met�da tla�idla sp�
		back.setOnAction(e->{

			Alert exit = new Alert(AlertType.CONFIRMATION);
			exit.setTitle("Ulo�i�");
			exit.setHeaderText("Chcete ulo�i� objedn�vku?");
			exit.setContentText("Polo�ky bud� ulo�en�\n Vyberte mo�nos�");
			
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
