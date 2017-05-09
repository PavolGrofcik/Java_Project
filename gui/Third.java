package gui;

import containers.Ubytovací;
import controller.NespravnyRozsah;
import controller.ObjectNotFound;
import controller.Objednávka;
import controller.Poèet;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Third extends Stage {
	
	private Label datum = new Label();
	private Label nadpis = new Label("Už len krok k odoslaniu...");
	
	private TextArea vypis = new TextArea();
	
	private Button kosik = new Button("Košík");
	private Button info = new Button("Zobraz info");
	private Button cena = new Button("Cena");
	
	private ComboBox<String> typ = new ComboBox<String>();
	
	
	private Objednávka objednávka;
	private Poèet poèet;
	
	//Generic method for Nodes
		public void setPosition(Node node, int x, int y){
			
			node.setLayoutX(x);
			node.setLayoutY(y);
		}
		
		public String zobrazObjednavku(Objednávka objednávka, String druh){
			
			String line = "********";
			String newline = "\n";
			String container = "Kontajner: ";
			String space = " ";
			String pom = "Poèet: ";
					
			return (line+ newline + container + druh + space + newline + pom + objednávka.rtti(objednávka, druh) + newline + newline);
		}
	
	public Third(Objednávka parameter, Poèet parameter2){																//parametrom je arralist s objektami rôznych kontajnerov, v tejto èasti sa dokonèí objednávka: t.j zistí celkové ceny, èas...
		
		super();
		
		Pane root = new Pane();
		
		this.objednávka=parameter;
		this.poèet=parameter2;
		
		root.getChildren().addAll(nadpis,vypis,kosik,info,typ,cena);
		
		setPosition(vypis, 20, 55);//TextArea
		setPosition(kosik, 20, 25);//Button - kosik
		setPosition(nadpis, 150, 0);//Label -nadpis
		setPosition(info, 70, 25);//Button -pre typ kontajnerov
		setPosition(typ, 150, 25);//ComboBox
		setPosition(cena, 275, 25);//Button - zobrazí cenu kontajnerov
		
		nadpis.setId("Header3");
		
		typ.getItems().addAll("Ubytovací", "Transportný", "Nádrž", "Mraziarenský");
		
		vypis.setEditable(false);
		vypis.setPrefSize(250, 270);
		
		cena.setOnAction(e->{
			
			vypis.clear();
			vypis.appendText(objednávka.ObjednavkaInfo());
		});
		
		info.setOnAction(e->{
			
			try {
				vypis.clear();
				vypis.appendText(objednávka.kontajnerInfo(typ.getSelectionModel().getSelectedItem()));
				
			} catch (ObjectNotFound ex){
				
				Alert b = new Alert(AlertType.INFORMATION);
				
				b.setTitle("Chyba");
				b.setHeaderText("Error!");
				b.setContentText("Zvolený typ kontajnera sa nenachádza v objednávke");
				b.show();
			
			
			
			}catch (Exception e2) {
				
				Alert b = new Alert(AlertType.ERROR);
				
				b.setTitle("Chyba");
				b.setHeaderText("Error!");
				b.setContentText("Nezvolili ste typ kontajnera");
				b.show();
			}
		});
		
		kosik.setOnAction(e->{
			
			vypis.clear();
			vypis.appendText(zobrazObjednavku(objednávka, "Ubytovací"));
			vypis.appendText(zobrazObjednavku(objednávka, "Transportný"));
			vypis.appendText(zobrazObjednavku(objednávka, "Nádrž"));
			vypis.appendText(zobrazObjednavku(objednávka, "Mraziarenský"));
			vypis.appendText("Poèet Kontajnerov: " + Integer.toString(objednávka.zistiPoèet()));
			
		});
		

		getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.jpg")));								//nastavenie ikony
		
		Scene scene = new Scene(root, 450,450);
		scene.getStylesheets().add(this.getClass().getResource("/gui/Design.css").toExternalForm());
		setTitle("Odoslanie objednávky");
		setScene(scene);
		show();
		
	}
	
}
