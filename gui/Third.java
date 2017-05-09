package gui;

import containers.Ubytovac�;
import controller.NespravnyRozsah;
import controller.ObjectNotFound;
import controller.Objedn�vka;
import controller.Po�et;
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
	private Label nadpis = new Label("U� len krok k odoslaniu...");
	
	private TextArea vypis = new TextArea();
	
	private Button kosik = new Button("Ko��k");
	private Button info = new Button("Zobraz info");
	private Button cena = new Button("Cena");
	
	private ComboBox<String> typ = new ComboBox<String>();
	
	
	private Objedn�vka objedn�vka;
	private Po�et po�et;
	
	//Generic method for Nodes
		public void setPosition(Node node, int x, int y){
			
			node.setLayoutX(x);
			node.setLayoutY(y);
		}
		
		public String zobrazObjednavku(Objedn�vka objedn�vka, String druh){
			
			String line = "********";
			String newline = "\n";
			String container = "Kontajner: ";
			String space = " ";
			String pom = "Po�et: ";
					
			return (line+ newline + container + druh + space + newline + pom + objedn�vka.rtti(objedn�vka, druh) + newline + newline);
		}
	
	public Third(Objedn�vka parameter, Po�et parameter2){																//parametrom je arralist s objektami r�znych kontajnerov, v tejto �asti sa dokon�� objedn�vka: t.j zist� celkov� ceny, �as...
		
		super();
		
		Pane root = new Pane();
		
		this.objedn�vka=parameter;
		this.po�et=parameter2;
		
		root.getChildren().addAll(nadpis,vypis,kosik,info,typ,cena);
		
		setPosition(vypis, 20, 55);//TextArea
		setPosition(kosik, 20, 25);//Button - kosik
		setPosition(nadpis, 150, 0);//Label -nadpis
		setPosition(info, 70, 25);//Button -pre typ kontajnerov
		setPosition(typ, 150, 25);//ComboBox
		setPosition(cena, 275, 25);//Button - zobraz� cenu kontajnerov
		
		nadpis.setId("Header3");
		
		typ.getItems().addAll("Ubytovac�", "Transportn�", "N�dr�", "Mraziarensk�");
		
		vypis.setEditable(false);
		vypis.setPrefSize(250, 270);
		
		cena.setOnAction(e->{
			
			vypis.clear();
			vypis.appendText(objedn�vka.ObjednavkaInfo());
		});
		
		info.setOnAction(e->{
			
			try {
				vypis.clear();
				vypis.appendText(objedn�vka.kontajnerInfo(typ.getSelectionModel().getSelectedItem()));
				
			} catch (ObjectNotFound ex){
				
				Alert b = new Alert(AlertType.INFORMATION);
				
				b.setTitle("Chyba");
				b.setHeaderText("Error!");
				b.setContentText("Zvolen� typ kontajnera sa nenach�dza v objedn�vke");
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
			vypis.appendText(zobrazObjednavku(objedn�vka, "Ubytovac�"));
			vypis.appendText(zobrazObjednavku(objedn�vka, "Transportn�"));
			vypis.appendText(zobrazObjednavku(objedn�vka, "N�dr�"));
			vypis.appendText(zobrazObjednavku(objedn�vka, "Mraziarensk�"));
			vypis.appendText("Po�et Kontajnerov: " + Integer.toString(objedn�vka.zistiPo�et()));
			
		});
		

		getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.jpg")));								//nastavenie ikony
		
		Scene scene = new Scene(root, 450,450);
		scene.getStylesheets().add(this.getClass().getResource("/gui/Design.css").toExternalForm());
		setTitle("Odoslanie objedn�vky");
		setScene(scene);
		show();
		
	}
	
}
