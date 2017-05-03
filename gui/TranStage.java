package gui;

import java.util.Optional;

import controller.NespravnyRozsah;
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

public class TranStage extends Stage {
	
	private Label header = new Label("Zadajte äpecifik·cie");
	private Label title = new Label("Zadajte nosnosù");
	private Label title2 = new Label("Zadajte poËet");
	private Label notification = new Label();
	private TextField weight = new TextField();
	private TextField number = new TextField();
	private ComboBox<String> size = new ComboBox<String>();
	private Button add = new Button("Pridaù");
	private Button back = new Button("Sp‰ù");
	
	
	
	
	public TranStage(Objedn·vka objedn·vka){
		
		VBox root = new VBox(5);
		
		root.getChildren().addAll(header,title2,number,title,weight,size,add,notification,back);		//adding nodes to pane
		
		root.setAlignment(Pos.BASELINE_CENTER);											//nastavenie modality Stage-u and name
		initModality(Modality.APPLICATION_MODAL);
		setTitle("Transportn˝");
	
		size.getItems().addAll("Medium", "Big");										//pridanie poloûiek do ComboBox-a
		size.setPromptText("Zvoæ");
		
		header.setFont(Font.font("Cambria", 20));										//nastavenie font-u a color-u pre HlaviËku
		header.setTextFill(Color.LIGHTSKYBLUE);
		
		number.setPromptText("Zadajte poËet");											//
		number.setAlignment(Pos.BASELINE_CENTER);
		number.setEditable(true);
		number.setVisible(true);
		
		weight.setPromptText("Zadajte nosnoù 2-10 t");
		weight.setAlignment(Pos.BASELINE_CENTER);
		weight.setEditable(true);
		weight.setVisible(true);
		
		add.setOnAction(e->{
			
			try {
			/*	
				if(Integer.parseInt(weight.getText())>30 || Integer.parseInt(weight.getText())<2){
					val dialog = new TextInputDialog(default = "15");
				}*/
				
				objedn·vka.addMyList(toString().substring(4, 13), Integer.parseInt(number.getText()), size.getSelectionModel().getSelectedItem(),  Integer.parseInt(weight.getText()));
				number.clear();
				weight.clear();
				notification.setText("Poloûky boli pridanÈ");
				//title.setText(toString().substring(4, 13));
				
				
				
			} catch (NespravnyRozsah nr) {																//vyhadzovanie vlastnej v˝nimky ak klient zad· neplatn˝ rozsah
			
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Chyba");
				alert.setHeaderText("Error");
				alert.setContentText("Zadali ste neplatn˝ rozsah");									
				alert.show();
			}
						
			catch (NumberFormatException e1) {
				
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
			
			ButtonType buttonOk= new ButtonType("OK");
			ButtonType buttonClose = new ButtonType("Close");
			
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
		
		
		Scene scene = new Scene(root, 250,250);											//vytvorenie Scene-i, nastavenie properties
		getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.jpg")));
		setScene(scene);
		setResizable(false);
		show();
		
		
	}

}
