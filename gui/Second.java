package gui;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import controller.Objedn�vka;
import controller.Po�et;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.time.format.*;

public class Second extends Stage {
	
	private Label lbl1 = new Label("Zvo�te typ");
	private Label notification = new Label("Aktu�lny po�et");
	private Label date = new Label();
	private Button pridaj = new Button("Prida�");
	private Button back = new Button("Ukon�i�");
	private Button delete = new Button("Zmaza� objedn�vku");
	private Button transport = new Button("Dokon�i� transport");
	private Button saveFile = new Button("Ulo�i� objedn�vku");
	private Button finalizacia = new Button("Platba");
	private ComboBox<String> box = new ComboBox<String>();
	
	private Po�et po�et;
	private Objedn�vka objedn�vka;
	private LocalDate date2 = LocalDate.now();//.plusDays(0); date picker kedy pr�de objend�vka 
	
	

	public void vytvor(Button btn, int prefWidth,int prefHeight){
		btn.setPrefSize(prefWidth, prefHeight);
	}
	
	public void setposition(Button btn, int x, int y){
		btn.setTranslateX(x);
		btn.setTranslateY(y);
		
	}
	
	public Second(Objedn�vka pom) {
		super(); 
		this.objedn�vka=pom;
		
		VBox root = new VBox(5);
		setTitle("Objedn�vka");
		initModality(Modality.APPLICATION_MODAL);
	
		root.getChildren().addAll(date,lbl1, box,pridaj, delete,saveFile,transport,finalizacia,back);								//pridanie tla�idiel
		root.setAlignment(Pos.CENTER);
		
		
		lbl1.setPrefHeight(20);
		lbl1.setTranslateX(0);
		lbl1.setTranslateY(0);
		lbl1.setId("Header2");
		
		notification.setId("Aktual");
		
		//notification.setFont(Font.font("Cambria", 18));
		date.setText(date2.format(DateTimeFormatter.ofPattern("dd.MM.y")));
		date.setId("Date");

		box.setPromptText("Typ");

		po�et = new Po�et(objedn�vka);
		objedn�vka.pridajSledovatela(po�et);
		root.getChildren().add(po�et);
		root.getChildren().add(notification);

		back.setPrefSize(70, 25);

		box.getItems().addAll("Mraziarensk�", "N�dr�","Transportn�","Ubytovac�");
	
		finalizacia.setOnAction(e->{
			
			if(objedn�vka.getVzdialenost()==0){
				
				Alert chyba = new Alert(AlertType.INFORMATION);
	
				chyba.setTitle("Chyba");
				chyba.setHeaderText("Transport");
				chyba.setContentText("Nezvolili ste miesto doru�enia");
				chyba.show();
			}
			else{
				new Third(objedn�vka);
				close();
			}
			
		});
		
		
		
		pridaj.setOnAction(e -> {																				 		// Inicializacia tlacidla VYTVOR
			//pr�kaz switch namiesto if-else, kv�li objektovej paradigme
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
				
			} catch (Exception e2) {
				Alert b = new Alert(AlertType.ERROR);
														
				b.setTitle("Chyba");
				b.setHeaderText("Error!");
				b.setContentText("Nezvolili ste �iaden typ kontajnerov");
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
		
		transport.setOnAction(e-> {										//ukon�enie aktu�lneho frame-u a presko�enie do Third
			switch (objedn�vka.getPocetKontajnerov()) {
			case 0:
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("Chyba");
				alert2.setHeaderText("Pr�zdny ko��k");
				alert2.setContentText("Nezvolili ste �iaden typ kontajnerov"); 	// Alert
																			// pre
																			// GeneralException
				alert2.show();

				break;

			default:
				new Transport(objedn�vka);
				break;
			}
		//new Third(objedn�vka);
		//hide();	
		});
		
		
		back.setOnAction(e -> { // Funkcia vracaj�ca hlavne okno
			
			
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
