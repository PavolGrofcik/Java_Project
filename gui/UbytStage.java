package gui;

import java.util.Optional;

import controller.NespravnyRozsah;
import controller.Objedn·vka;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
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

public class UbytStage extends Stage{

	private Label header = new Label("Zadajte äpecifik·cie");
	private Label title = new Label("Vyberte veækosù");
	private Label notification = new Label();
	private TextField number = new TextField();
	private TextField windows = new TextField();
	private Button add = new Button("Pridaù");
	private  Button back = new Button("Sp‰ù");
	private ComboBox<String> type = new ComboBox<String>();
	private CheckBox balcon = new CheckBox("BalkÛn");
	private CheckBox terrace = new CheckBox("Terasa");
	
	public UbytStage(Objedn·vka objedn·vka){
		
		VBox root = new VBox(5);
		root.getChildren().addAll(header,number,title,type,windows,balcon,terrace,add,notification,back);
		
		root.setAlignment(Pos.BASELINE_CENTER);
		initModality(Modality.APPLICATION_MODAL);
		setTitle("UbytovacÌ");
		
		header.setFont(Font.font("Cambria", 20));
		header.setTextFill(Color.SANDYBROWN);
		
		number.setPromptText("Zadajte poËet");
		number.setAlignment(Pos.BASELINE_CENTER);
		number.setEditable(true);
		number.setVisible(true);
		
		windows.setPromptText("Zadajte poËet okien");
		windows.setAlignment(Pos.BASELINE_CENTER);
		windows.setEditable(true);
		windows.setVisible(true);
		
		balcon.setSelected(false);
		balcon.setVisible(false);
		
		terrace.setSelected(false);
		terrace.setVisible(false);
		
		
		type.getItems().addAll("Rodinn˝", "Kancel·rsky");
		type.setPromptText("Zvoæ");
		type.setOnAction(e->{
			if(type.getSelectionModel().getSelectedItem().equals("Rodinn˝")){
				balcon.setVisible(true);
			
			}
			else{
				balcon.setVisible(false);
			}
		});
		
		balcon.setOnAction(e->{
			//nastavenie terasy
			if(balcon.isSelected()){
				terrace.setVisible(true);																//vytvorÌ vhniezden˙ triedu v Ubytovacom kontajneri
			}
			else{
				terrace.setVisible(false);
			}
			
		});
		
		add.setOnAction(e->{
			try {
				
				objedn·vka.addUbytovaci(toString().substring(4, 13), Integer.parseInt(number.getText()), type.getSelectionModel().getSelectedItem(), Integer.parseInt(windows.getText()), balcon.isSelected(),terrace.isSelected());
				
				number.clear();
				windows.clear();
				balcon.setSelected(false);
				terrace.setSelected(false);
				notification.setText("Poloûky boli pridanÈ");
				
			} catch (NespravnyRozsah nrh){
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Chyba");
				alert.setHeaderText("Error");
				alert.setContentText("Zadali ste nadmern˝ poËet okien ako je povolen˝");									
				alert.show();
				
			} catch (NumberFormatException nfe2) {
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
		Scene scene = new Scene(root,250,275);
		setResizable(false);
		setScene(scene);
		show();
		
	}
}
