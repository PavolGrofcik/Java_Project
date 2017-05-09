package gui;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import controller.Objedn·vka;
import controller.PoËet;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;
import java.time.*;
import java.time.format.*;

public class Second extends Stage {
	
	private Label lbl1 = new Label("Zvoæte typ");
	private Label notification = new Label("Aktu·lny poËet");
	private Label date = new Label();
	private Button pridaj = new Button("Pridaù");
	private Button back = new Button("UkonËiù");
	private Button delete = new Button("Zmazaù objedn·vku");
	private Button transport = new Button("DokonËiù transport");
	private Button saveFile = new Button("Uloûiù objedn·vku");
	private Button finalizacia = new Button("Platba");
	private ComboBox<String> box = new ComboBox<String>();
	
	private PoËet poËet;
	private Objedn·vka objedn·vka;
	private LocalDate date2 = LocalDate.now();//.plusDays(0); date picker kedy prÌde objend·vka 
	
	

	public void vytvor(Button btn, int prefWidth,int prefHeight){
		btn.setPrefSize(prefWidth, prefHeight);
	}
	
	public void setposition(Button btn, int x, int y){
		btn.setTranslateX(x);
		btn.setTranslateY(y);
		
	}
	
	public Second(Objedn·vka pom) {
		super(); 
		this.objedn·vka=pom;
		
		VBox root = new VBox(5);
		setTitle("Objedn·vka");
		initModality(Modality.APPLICATION_MODAL);
	
		root.getChildren().addAll(date,lbl1, box,pridaj, delete,saveFile,transport,finalizacia,back);								//pridanie tlaËidiel
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

		poËet = new PoËet(objedn·vka);
		objedn·vka.pridajSledovatela(poËet);
		root.getChildren().add(poËet);
		root.getChildren().add(notification);

		back.setPrefSize(70, 25);

		box.getItems().addAll("Mraziarensk˝", "N·drû","Transportn˝","UbytovacÌ");
	
		finalizacia.setOnAction(e->{
			
			if(objedn·vka.getVzdialenost()==0){
				
				Alert chyba = new Alert(AlertType.INFORMATION);
	
				chyba.setTitle("Chyba");
				chyba.setHeaderText("Transport");
				chyba.setContentText("Nezvolili ste miesto doruËenia");
				chyba.show();
			}
			else{
				new Third(objedn·vka,poËet);
			}
			
		});
		
		
		
		pridaj.setOnAction(e -> {																				 		// Inicializacia tlacidla VYTVOR
			//prÌkaz switch namiesto if-else, kvÙli objektovej paradigme
			try {
				lbl1.setText("Zadaj poËet Kontajnerov");
				/*objedn·vka.addmyList(Integer.parseInt(txt1.getText()),
						box.getSelectionModel().getSelectedItem().toString());
						*/
				switch (box.getSelectionModel().getSelectedItem()) {
				case "Mraziarensk˝":{
					new MrazStage(objedn·vka);
				}break;
				case "Transportn˝":{
					new TranStage(objedn·vka);
				}break;
				case "UbytovacÌ":{
					new UbytStage(objedn·vka);
				}break;
				case "N·drû":{
					new NadrûStage(objedn·vka);
				}break;
				}
				
			} catch (Exception e2) {
				Alert b = new Alert(AlertType.ERROR);
														
				b.setTitle("Chyba");
				b.setHeaderText("Error!");
				b.setContentText("Nezvolili ste ûiaden typ kontajnerov");
				b.show();
			}
			finally {
				lbl1.setText("DokonËite objedn·vku");
			}
		});
	
		delete.setOnAction(e->{											//Funkcia pre tlaËidlo Zmaû -> zmaûe vöetky poloûky v arrayList-e
			objedn·vka.zmaû();
		});
	
		
		saveFile.setOnAction(e->{
			
			FileChooser fc = new FileChooser();
  			fc.setTitle("Uloûiù ako");
  			File f = fc.showSaveDialog(this);

			try {
				objedn·vka.uloz(f);
			} catch ( ClassNotFoundException | IOException e1) {
				Alert alert1 = new Alert(AlertType.ERROR);
				alert1.setTitle("Chyba");
				alert1.setHeaderText("Error");
				alert1.setContentText("Neuloûil sa ûiadny s˙bor");									//Alert pre GeneralException
				alert1.show();
			}
			
		});
		
		transport.setOnAction(e-> {										//ukonËenie aktu·lneho frame-u a preskoËenie do Third
			switch (objedn·vka.getPocetKontajnerov()) {
			case 0:
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("Chyba");
				alert2.setHeaderText("Pr·zdny koöÌk");
				alert2.setContentText("Nezvolili ste ûiaden typ kontajnerov"); 	// Alert
																			// pre
																			// GeneralException
				alert2.show();

				break;

			default:
				new Transport(objedn·vka);
				break;
			}
		//new Third(objedn·vka);
		//hide();	
		});
		
		
		back.setOnAction(e -> { // Funkcia vracaj˙ca hlavne okno
			
			ButtonType buttonOk= new ButtonType("OK");
			ButtonType buttonClose = new ButtonType("Close");
			
			Alert exit = new Alert(AlertType.CONFIRMATION);
			exit.setTitle("UkonËiù?");
			exit.setHeaderText("Chcete v·ûne zruöiù objedn·vku?");
			exit.setContentText("Vöetky poloûky budu zmazanÈ\n Vyberte moûnosù");
			
			Optional<ButtonType> result = exit.showAndWait();
			
			if(result.get() == ButtonType.OK){
				
				//zmaûe vöetok progres
				objedn·vka.zmaû();
				hide();
				exit.close();
			}
			else if(result.get()==ButtonType.CANCEL){
				
				lbl1.setText("Zadajte poloûky");
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
