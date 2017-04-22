package gui;


import containers.*;
import controller.Objedn�vka;
import controller.Po�et;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.*;

public class Druh� extends Stage {
	//private Objedn�vka objedn�vka = new Objedn�vka(); // trieda Controller ktora uklad�
												// kontajnery do ArrayList-u
	private Label lbl1 = new Label("Druhy kontajnerov");
	private TextField txt1 = new TextField();
	private Button zistipo�et = new Button("Zisti po�et");
	private Button pridaj = new Button("Pridaj");
	private Button back = new Button("Ukon�i");
	private Button delete = new Button("Zma� polo�ky");
	private Button nextScene = new Button("Dokon�i objedn�vku");
	private Button saveFile = new Button("Ulo� objedn�vku");
	private ComboBox<String> box = new ComboBox<String>();
	
	private Po�et po�et;
	private Objedn�vka objedn�vka;
	/*
	Pomocn� funkcia na vytvorenie Buttonons
	*/
	public static void vytvor(Button btn, int prefWidth,int prefHeight){
		btn.setPrefSize(prefWidth, prefHeight);
	}
	
	public static void setposition(Button btn, int x, int y){
		btn.setTranslateX(x);
		btn.setTranslateY(y);
		
	}
	
	public Druh�(Objedn�vka pom) {
		super(); 
		this.objedn�vka=pom;
		VBox root = new VBox(5);
		setTitle("Objedn�vka");
		initModality(Modality.APPLICATION_MODAL);																	//zamkne okno
		
		root.getChildren().addAll(lbl1, box, txt1, pridaj, zistipo�et, delete,saveFile,nextScene,back);				//pridanie tla�idiel
		root.setAlignment(Pos.CENTER);
		
		
		lbl1.setPrefHeight(20);
		lbl1.setTranslateX(0);
		lbl1.setTranslateY(0);
		lbl1.setFont(Font.font("Cambria",27));
		
		box.setTranslateX(0);
		box.setTranslateY(0);

		txt1.setTranslateX(0);
		txt1.setTranslateY(0);
		txt1.setAlignment(Pos.CENTER); //nastavenie prompt textu do poz�cie CENTER
		txt1.setPromptText("Zadajte po�et a vyberte druh kontajnera");
		po�et = new Po�et(objedn�vka);
		objedn�vka.pridajSledovatela(po�et);
		root.getChildren().add(po�et);
		

		pridaj.setTranslateX(180);
		pridaj.setPrefSize(75, 25);
		
		delete.setTranslateX(180);
		
		zistipo�et.setTranslateX(180);
		zistipo�et.setPrefWidth(75);
		
		// zisticenu.setTranslateY();

		//back.setTranslateX(275);
		//back.setTranslateY(240);
		back.setPrefSize(70, 25);

		box.getItems().addAll("Transportn�", "Mraziarensk�", "Ubytovac�", "N�dr�");

		pridaj.setOnAction(e -> {																				 		// Inicializacia tlacidla VYTVOR

			try {
				lbl1.setText("Zadaj po�et Kontajnerov");
				/*objedn�vka.addmyList(Integer.parseInt(txt1.getText()),
						box.getSelectionModel().getSelectedItem().toString());
						*/
				if(box.getSelectionModel().getSelectedItem().equals("Mraziarensk�")){
					new MrazStage(objedn�vka);
				}
				else if(box.getSelectionModel().getSelectedItem().equals("Transportn�")){
					new TranStage(objedn�vka);
					
				}
				txt1.clear();																							//nastav� p�vodn� promptText

			} catch (Exception e2) {
				Alert b = new Alert(AlertType.ERROR); 	// chyba pri nezadan�
														// ��sla
				b.setTitle("Chyba");
				b.setHeaderText("Error!");
				b.setContentText("Invalid number");
				b.show();
			}
			finally {
				lbl1.setText("Pokra�uj alebo potvr� objedn�vku");
			}
		});
	
		delete.setOnAction(e->{											//Funkcia pre tla�idlo Zma� -> zma�e v�etky polo�ky v arrayList-e
			objedn�vka.zma�();
		});
		
		zistipo�et.setOnAction(e->{										//Funkcia zisti cenu
			try {
				lbl1.setText("Po�et-" + Integer.toString(objedn�vka.zistiPo�et()));

			} catch (Exception except) {

				Alert c = new Alert(AlertType.ERROR);
				c.setTitle("Chyba");
				c.setContentText("Nevybral si �iadne kontajnery");
				c.showAndWait();
			}
			
		});
		
		saveFile.setOnAction(e->{
			/*
			FileChooser fc = new FileChooser();
  			fc.setTitle("Ulo�i� ako");
  			File f = fc.showSaveDialog(this);

			try {
				objedn�vka.uloz(f);
			} catch ( Exception e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}*/
			new MrazStage(objedn�vka);
		});
		
		nextScene.setOnAction(e-> {										//ukon�enie aktu�lneho frame-u a presko�enie do Third
		new Third(objedn�vka);
		hide();	
		});
		
		
		back.setOnAction(e -> { // Funkcia vracaj�ca hlavne okno
			close();
		});
		
		getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.jpg")));
		Scene scene = new Scene(root, 450, 450); 
		setScene(scene);
		show(); 

	}

}
