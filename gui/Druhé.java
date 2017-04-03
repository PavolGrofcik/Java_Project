package gui;

import containers.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Druhé extends Stage {
	private Arraylist myList = new Arraylist(); // trieda Arraylist ktora ukladá
												// kontajnery do ArrayList-u

	private Label lbl1 = new Label("Zadaj mnozstvo");
	private TextField txt1 = new TextField();
	private Button zistipoèet = new Button("Zisti poèet");
	private Button vytvor = new Button("Vytvor");
	private ComboBox<String> box = new ComboBox<String>();
	private Button back = new Button("Spä");
	private Button delete = new Button("Zmaž položky");

	public Druhé() {
		super(); // zavolanie superclass metódy start, netreba znovu vola
					// funkciu start

		VBox root = new VBox();
		setTitle("Vytvor Objednávku");

		root.getChildren().addAll(lbl1, box, txt1, vytvor, zistipoèet, delete, back);

		Scene scene = new Scene(root, 415, 415); // nastavenie druhého okna s
													// parametrami
		setScene(scene); // zobrazenie sceny
		show(); // show ukaz scenu

		// delete.setLayoutX();
		// delete.setLayoutY();
		// nastav.setTranslateX(0);
		// nastav.setTranslateY(365);

		lbl1.setPrefHeight(25);
		lbl1.setTranslateX(5);
		lbl1.setTranslateY(5);

		box.setTranslateX(0);
		box.setTranslateY(0);

		txt1.setTranslateX(0);
		txt1.setTranslateY(0);
		txt1.focusedProperty().addListener((p, o, n) -> {
			if (n) {
				txt1.setAlignment(Pos.CENTER_LEFT);
			} else
				txt1.setAlignment(Pos.CENTER);
		});
		// txt1.setAlignment(Pos.CENTER); //nastavenie prompt textu do pozície
		// CENTER
		txt1.setPromptText("Zadajte poèet a vyberte druh kontajnera");

		vytvor.setLayoutX(100);
		vytvor.setLayoutY(50);
		vytvor.setPrefSize(60, 25);

		zistipoèet.setTranslateX(165);
		// zisticenu.setTranslateY();

		back.setTranslateX(275);
		back.setTranslateY(240);
		back.setPrefSize(70, 25);

		box.getItems().addAll("Prepravný", "Mraziarenský", "Ubytovací", "Multifunkèný");

		vytvor.setOnAction(e -> { // Inicializacia tlacidla VYTVOR

			try {
				lbl1.setText("Zadaj poèet Kontajnerov");
				myList.addmyList(Integer.parseInt(txt1.getText()),
						box.getSelectionModel().getSelectedItem().toString());

			} catch (Exception e2) {
				Alert b = new Alert(AlertType.ERROR); // chyba pri nezadaní
														// èísla
				b.setTitle("Chyba");
				b.setContentText("Invalid number");
				b.show();
			}
		});
	
		delete.setOnAction(e->{
			myList.zmaž();
			
		});
		
		zistipoèet.setOnAction(e->{										//Funkcia zisti cenu
			try {
				lbl1.setText("Poèet " + Integer.toString(myList.zistipoèet()));

			} catch (Exception except) {

				Alert c = new Alert(AlertType.ERROR);
				c.setTitle("Chyba");
				c.setContentText("Nevybral si žiadne kontajnery");
				c.showAndWait();
			}
			
		});

		back.setOnAction(e -> { // Funkcia vracajúca hlavne okno
			close();
		});

	}

}
