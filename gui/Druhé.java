package gui;

import containers.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Druh� extends Stage {
	private Arraylist myList = new Arraylist(); // trieda Arraylist ktora uklad�
												// kontajnery do ArrayList-u

	private Label lbl1 = new Label("Zadaj mnozstvo");
	private TextField txt1 = new TextField();
	private Button zistipo�et = new Button("Zisti po�et");
	private Button vytvor = new Button("Vytvor");
	private ComboBox<String> box = new ComboBox<String>();
	private Button back = new Button("Sp�");
	private Button delete = new Button("Zma� polo�ky");

	public Druh�() {
		super(); // zavolanie superclass met�dy start, netreba znovu vola�
					// funkciu start

		VBox root = new VBox();
		setTitle("Vytvor Objedn�vku");

		root.getChildren().addAll(lbl1, box, txt1, vytvor, zistipo�et, delete, back);

		Scene scene = new Scene(root, 415, 415); // nastavenie druh�ho okna s
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
		// txt1.setAlignment(Pos.CENTER); //nastavenie prompt textu do poz�cie
		// CENTER
		txt1.setPromptText("Zadajte po�et a vyberte druh kontajnera");

		vytvor.setLayoutX(100);
		vytvor.setLayoutY(50);
		vytvor.setPrefSize(60, 25);

		zistipo�et.setTranslateX(165);
		// zisticenu.setTranslateY();

		back.setTranslateX(275);
		back.setTranslateY(240);
		back.setPrefSize(70, 25);

		box.getItems().addAll("Prepravn�", "Mraziarensk�", "Ubytovac�", "Multifunk�n�");

		vytvor.setOnAction(e -> { // Inicializacia tlacidla VYTVOR

			try {
				lbl1.setText("Zadaj po�et Kontajnerov");
				myList.addmyList(Integer.parseInt(txt1.getText()),
						box.getSelectionModel().getSelectedItem().toString());

			} catch (Exception e2) {
				Alert b = new Alert(AlertType.ERROR); // chyba pri nezadan�
														// ��sla
				b.setTitle("Chyba");
				b.setContentText("Invalid number");
				b.show();
			}
		});
	
		delete.setOnAction(e->{
			myList.zma�();
			
		});
		
		zistipo�et.setOnAction(e->{										//Funkcia zisti cenu
			try {
				lbl1.setText("Po�et " + Integer.toString(myList.zistipo�et()));

			} catch (Exception except) {

				Alert c = new Alert(AlertType.ERROR);
				c.setTitle("Chyba");
				c.setContentText("Nevybral si �iadne kontajnery");
				c.showAndWait();
			}
			
		});

		back.setOnAction(e -> { // Funkcia vracaj�ca hlavne okno
			close();
		});

	}

}
