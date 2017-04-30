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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Nadr�Stage extends Stage {
	
	private Label header = new Label("Zadajte �pecifik�cie");
	private Label title = new Label("Vyberte ve�kos�");
	private Label notification = new Label();
	private TextField number = new TextField();
	private ComboBox<String> size = new ComboBox<String>();
	private Button add = new Button("Prida�");
	private Button back = new Button("Sp�");
	
	
	public Nadr�Stage(Objedn�vka objedn�vka){
		
		VBox root = new VBox(5);
		root.getChildren().addAll(header,number,title,size,add,notification,back);
		
		root.setAlignment(Pos.BASELINE_CENTER);
		initModality(Modality.APPLICATION_MODAL);
		setTitle("N�dr�");
		
		//prerobi� automaticke pridavanie podla checboxu zada� vlastn� objem!!!
		size.getItems().addAll("Small", "Medium", "Big");
		
		//nastavenie vlastnot� Label=u header
		header.setFont(Font.font("Cambria", 20));
		header.setTextFill(Color.MEDIUMPURPLE);
		
		number.setPromptText("Zadajte po�et");
		number.setAlignment(Pos.BASELINE_CENTER);
		number.setEditable(true);
		number.setVisible(true);
		
		add.setOnAction(e->{
			try {
				
				objedn�vka.addMyList(toString().substring(4, 14), Integer.parseInt(number.getText()), size.getSelectionModel().getSelectedItem(), 0);
				number.clear();
				notification.setText("Polo�ky boli pridan�");
				
			} catch (NumberFormatException nfe) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Chyba");
				alert.setHeaderText("Error");
				alert.setContentText("Zadali ste neplatn� hodnotu");									//Alert pre NumberFormatException
				alert.show();
			
			} catch (Exception e2) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Chyba");
				alert.setHeaderText("Error");
				alert.setContentText("Nevyplnili ste niektor� polia");									//Alert pre GeneralException
				alert.show();
			}
			
		});
		
		back.setOnAction(e->{
			ButtonType buttonOk= new ButtonType("OK");
			ButtonType buttonClose = new ButtonType("Close");
			
			Alert exit = new Alert(AlertType.CONFIRMATION);
			exit.setTitle("Ukon�i�?");
			exit.setHeaderText("Chcete ulo�i� a vr�ti� sa sp�?");
			exit.setContentText("Zvo�te mo�nos�");
			
			Optional<ButtonType> result = exit.showAndWait();
			
			if(result.get() == ButtonType.OK){
				
				hide();
				exit.close();
			}
			else if(result.get()==ButtonType.CANCEL){
				
				notification.setText("Zadajte polo�ky");
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
