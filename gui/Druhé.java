package gui;


import java.io.File;
import containers.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.*;

public class Druhé extends Stage {
	private Objednávka objednávka = new Objednávka(); // trieda Controller ktora ukladá
												// kontajnery do ArrayList-u

	private Label lbl1 = new Label("Druhy kontajnerov");
	private TextField txt1 = new TextField();
	private Button zistipoèet = new Button("Zisti poèet");
	private Button pridaj = new Button("Pridaj");
	private Button back = new Button("Ukonèi");
	private Button delete = new Button("Zmaž položky");
	private Button nextScene = new Button("Dokonèi objednávku");
	private Button saveFile = new Button("Ulož objednávku");
	private ComboBox<String> box = new ComboBox<String>();
	
	
	/*
	Pomocná funkcia na vytvorenie Buttonons
	*/
	public static void vytvor(Button btn, int prefWidth,int prefHeight){
		btn.setPrefSize(prefWidth, prefHeight);
	}
	
	public static void setposition(Button btn, int x, int y){
		btn.setTranslateX(x);
		btn.setTranslateY(y);
		
	}
	
	public Druhé() {
		super(); 

		VBox root = new VBox(5);
		setTitle("Objednávka");
		initModality(Modality.APPLICATION_MODAL);																	//zamkne okno
		
		root.getChildren().addAll(lbl1, box, txt1, pridaj, zistipoèet, delete,saveFile,nextScene,back);				//pridanie tlaèidiel
		root.setAlignment(Pos.CENTER);
		Scene scene = new Scene(root, 450, 450); 
		setScene(scene);
		show(); 
		
		
	
		//listview.setPrefHeight(215);
		
		lbl1.setPrefHeight(20);
		lbl1.setTranslateX(0);
		lbl1.setTranslateY(0);
		lbl1.setFont(Font.font("Cambria",27));
		
		box.setTranslateX(0);
		box.setTranslateY(0);

		txt1.setTranslateX(0);
		txt1.setTranslateY(0);
		txt1.setAlignment(Pos.CENTER); //nastavenie prompt textu do pozície CENTER
		txt1.setPromptText("Zadajte poèet a vyberte druh kontajnera");
		

		pridaj.setTranslateX(180);
		pridaj.setPrefSize(75, 25);
		
		delete.setTranslateX(180);
		
		zistipoèet.setTranslateX(180);
		zistipoèet.setPrefWidth(75);
		
		// zisticenu.setTranslateY();

		//back.setTranslateX(275);
		//back.setTranslateY(240);
		back.setPrefSize(70, 25);

		box.getItems().addAll("Transportný", "Mraziarenský", "Ubytovací", "Nádrž");

		pridaj.setOnAction(e -> {																				 		// Inicializacia tlacidla VYTVOR

			try {
				lbl1.setText("Zadaj poèet Kontajnerov");
				/*objednávka.addmyList(Integer.parseInt(txt1.getText()),
						box.getSelectionModel().getSelectedItem().toString());
						*/
				if(box.getSelectionModel().getSelectedItem().equals("Mraziarenský")){
					new MrazStage(objednávka);
				}
				txt1.clear();																							//nastaví pôvodný promptText

			} catch (Exception e2) {
				Alert b = new Alert(AlertType.ERROR); 	// chyba pri nezadaní
														// èísla
				b.setTitle("Chyba");
				b.setHeaderText("Error!");
				b.setContentText("Invalid number");
				b.show();
			}
			finally {
				lbl1.setText("Pokraèuj alebo potvrï objednávku");
			}
		});
	
		delete.setOnAction(e->{											//Funkcia pre tlaèidlo Zmaž -> zmaže všetky položky v arrayList-e
			objednávka.zmaž();
		});
		
		zistipoèet.setOnAction(e->{										//Funkcia zisti cenu
			try {
				lbl1.setText("Poèet-" + Integer.toString(objednávka.zistiPoèet()));

			} catch (Exception except) {

				Alert c = new Alert(AlertType.ERROR);
				c.setTitle("Chyba");
				c.setContentText("Nevybral si žiadne kontajnery");
				c.showAndWait();
			}
			
		});
		
		saveFile.setOnAction(e->{
			/*
			FileChooser fc = new FileChooser();
  			fc.setTitle("Uloži ako");
  			File f = fc.showSaveDialog(this);

			try {
				objednávka.uloz(f);
			} catch ( Exception e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}*/
			new MrazStage(objednávka);
		});
		
		nextScene.setOnAction(e-> new Third(objednávka));
		
		
		back.setOnAction(e -> { // Funkcia vracajúca hlavne okno
			close();
		});

	}

}
