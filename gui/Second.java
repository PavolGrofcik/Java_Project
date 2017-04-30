package gui;


import java.beans.DesignMode;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import controller.Objedn�vka;
import controller.Po�et;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;

public class Second extends Stage {
	
	private Label lbl1 = new Label("Vyberte typy kontajnerov");
	private Label notification = new Label("Aktu�lny po�et");
	private Button pridaj = new Button("Prida�");
	private Button back = new Button("Ukon�i�");
	private Button delete = new Button("Zmaza� polo�ky");
	private Button nextScene = new Button("Dokon�i� objedn�vku");
	private Button saveFile = new Button("Ulo�i� objedn�vku");
	private ComboBox<String> box = new ComboBox<String>();
	
	private Po�et po�et;
	private Objedn�vka objedn�vka;
	

	public static void vytvor(Button btn, int prefWidth,int prefHeight){
		btn.setPrefSize(prefWidth, prefHeight);
	}
	
	public static void setposition(Button btn, int x, int y){
		btn.setTranslateX(x);
		btn.setTranslateY(y);
		
	}
	
	public Second(Objedn�vka pom) {
		super(); 
		this.objedn�vka=pom;
		VBox root = new VBox(5);
		setTitle("Objedn�vka");
		initModality(Modality.APPLICATION_MODAL);																	//zamkne okno
	
		root.getChildren().addAll(lbl1, box,pridaj, delete,saveFile,nextScene,back);				//pridanie tla�idiel
		root.setAlignment(Pos.CENTER);
		
		
		lbl1.setPrefHeight(20);
		lbl1.setTranslateX(0);
		lbl1.setTranslateY(0);
		lbl1.setFont(Font.font("Cambria",27));
		lbl1.setTextFill(Color.SLATEBLUE);
		
		notification.setFont(Font.font("Cambria", 18));
		
		
		box.setTranslateX(0);
		box.setTranslateY(0);

		po�et = new Po�et(objedn�vka);
		objedn�vka.pridajSledovatela(po�et);
		root.getChildren().add(po�et);
		root.getChildren().add(notification);

		back.setPrefSize(70, 25);

		box.getItems().addAll("Mraziarensk�", "N�dr�","Transportn�","Ubytovac�");
	

		pridaj.setOnAction(e -> {																				 		// Inicializacia tlacidla VYTVOR
			//prerobi� na switch kv�li objektovej paradigme
			try {
				lbl1.setText("Zadaj po�et Kontajnerov");
				/*objedn�vka.addmyList(Integer.parseInt(txt1.getText()),
						box.getSelectionModel().getSelectedItem().toString());
						*/
				switch (box.getSelectionModel().getSelectedItem()) {
				case "Mraziarensk�":{
					new MrazStage(objedn�vka);
				}break;
				case "Transportn�":{
					new TranStage(objedn�vka);
				}break;
				case "Ubytovac�":{
					new UbytStage(objedn�vka);
				}break;
				case "N�dr�":{
					new Nadr�Stage(objedn�vka);
				}break;
				}
				
				/*
				if(box.getSelectionModel().getSelectedItem().equals("Mraziarensk�")){
					new MrazStage(objedn�vka);
				}
				else if(box.getSelectionModel().getSelectedItem().equals("Transportn�")){
					new TranStage(objedn�vka);
					
				}
				else if(box.getSelectionModel().getSelectedItem().equals("N�dr�")){
					new Nadr�Stage(objedn�vka);
				}
				else if(box.getSelectionModel().getSelectedItem().equals("Ubytovac�")){
					new UbytStage(objedn�vka);
				}
				*/
			} catch (Exception e2) {
				Alert b = new Alert(AlertType.ERROR); 	// chyba pri nezadan�
														// ��sla
				b.setTitle("Chyba");
				b.setHeaderText("Error!");
				b.setContentText("Nezadali ste typ kontajnera");
				b.show();
			}
			finally {
				lbl1.setText("Dokon�ite objedn�vku");
			}
		});
	
		delete.setOnAction(e->{											//Funkcia pre tla�idlo Zma� -> zma�e v�etky polo�ky v arrayList-e
			objedn�vka.zma�();
		});
	
		
		saveFile.setOnAction(e->{
			
			FileChooser fc = new FileChooser();
  			fc.setTitle("Ulo�i� ako");
  			File f = fc.showSaveDialog(this);

			try {
				objedn�vka.uloz(f);
			} catch ( ClassNotFoundException | IOException e1) {
				Alert alert1 = new Alert(AlertType.ERROR);
				alert1.setTitle("Chyba");
				alert1.setHeaderText("Error");
				alert1.setContentText("Neulo�il sa �iadny s�bor");									//Alert pre GeneralException
				alert1.show();
			}
			
		});
		
		nextScene.setOnAction(e-> {										//ukon�enie aktu�lneho frame-u a presko�enie do Third
		new Third(objedn�vka);
		//hide();	
		});
		
		
		back.setOnAction(e -> { // Funkcia vracaj�ca hlavne okno
			
			ButtonType buttonOk= new ButtonType("OK");
			ButtonType buttonClose = new ButtonType("Close");
			
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
				
				lbl1.setText("Zadajte polo�ky");
				exit.close();
			}
		});
		
		getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.jpg")));
		Scene scene = new Scene(root, 450, 450); 
		scene.getStylesheets().add(this.getClass().getResource("Design.css").toExternalForm());
		setScene(scene);
		show(); 

	}

}
