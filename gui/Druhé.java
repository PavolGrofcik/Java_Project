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

public class DruhÈ extends Stage {
	private Arraylist myList = new Arraylist(); // trieda Arraylist ktora uklad·
												// kontajnery do ArrayList-u

	private Label lbl1 = new Label("Druhy kontajnerov");
	private TextField txt1 = new TextField();
	private Button zistipoËet = new Button("Zisti poËet");
	private Button vytvor = new Button("Pridaj");
	private Button back = new Button("Sp‰ù");
	private Button delete = new Button("Zmaû poloûky");
	private Button nextScene = new Button("DokonËi objedn·vku");
	private Button saveFile = new Button("Uloû objedn·vku");
	private ComboBox<String> box = new ComboBox<String>();
	
	
	/*
	Pomocn· funkcia na vytvorenie Buttonons
	*/
	public static void vytvor(Button btn, int prefWidth,int prefHeight){
		btn.setPrefSize(prefWidth, prefHeight);
	}
	
	public static void setposition(Button btn, int x, int y){
		btn.setTranslateX(x);
		btn.setTranslateY(y);
		
	}
	
	public DruhÈ() {
		super(); 

		VBox root = new VBox(5);
		setTitle("Objedn·vka");
		initModality(Modality.APPLICATION_MODAL);																	//zamkne okno
		
		root.getChildren().addAll(lbl1, box, txt1, vytvor, zistipoËet, delete,saveFile,nextScene,back);				//pridanie tlaËidiel
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
		txt1.setAlignment(Pos.CENTER); //nastavenie prompt textu do pozÌcie CENTER
		txt1.setPromptText("Zadajte poËet a vyberte druh kontajnera");

		vytvor.setTranslateX(180);
		vytvor.setPrefSize(75, 25);
		
		delete.setTranslateX(180);
		
		zistipoËet.setTranslateX(180);
		zistipoËet.setPrefWidth(75);
		
		// zisticenu.setTranslateY();

		//back.setTranslateX(275);
		//back.setTranslateY(240);
		back.setPrefSize(70, 25);

		box.getItems().addAll("Transportn˝", "Mraziarensk˝", "UbytovacÌ", "N·drû");

		vytvor.setOnAction(e -> {																				 		// Inicializacia tlacidla VYTVOR

			try {
				lbl1.setText("Zadaj poËet Kontajnerov");
				myList.addmyList(Integer.parseInt(txt1.getText()),
						box.getSelectionModel().getSelectedItem().toString());
				txt1.clear();																							//nastavÌ pÙvodn˝ promptText

			} catch (Exception e2) {
				Alert b = new Alert(AlertType.ERROR); 	// chyba pri nezadanÌ
														// ËÌsla
				b.setTitle("Chyba");
				b.setHeaderText("Error!");
				b.setContentText("Invalid number");
				b.show();
			}
			finally {
				lbl1.setText("PokraËuj alebo potvrÔ objedn·vku");
			}
		});
	
		delete.setOnAction(e->{											//Funkcia pre tlaËidlo Zmaû -> zmaûe vöetky poloûky v arrayList-e
			myList.zmaû();
		});
		
		zistipoËet.setOnAction(e->{										//Funkcia zisti cenu
			try {
				lbl1.setText("PoËet-" + Integer.toString(myList.zistiPoËet()));

			} catch (Exception except) {

				Alert c = new Alert(AlertType.ERROR);
				c.setTitle("Chyba");
				c.setContentText("Nevybral si ûiadne kontajnery");
				c.showAndWait();
			}
			
		});
		
		saveFile.setOnAction(e->{
			FileChooser fc = new FileChooser();
  			fc.setTitle("Uloûiù ako");
  			File f = fc.showSaveDialog(this);

			try {
				myList.uloz(f);
			} catch ( Exception e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
		});
		
		nextScene.setOnAction(e-> new Third(myList));
		
		
		back.setOnAction(e -> { // Funkcia vracaj˙ca hlavne okno
			close();
		});

	}

}
