package gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import containers.UbytovacÌ;
import controller.NespravnyRozsah;
import controller.ObjectNotFound;
import controller.Objedn·vka;
import controller.PoËet;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Third extends Stage {
	
	private Label datum = new Label();
	private Label nadpis = new Label("Uû len krok k odoslaniu...");
	
	private TextArea vypis = new TextArea();
	
	private Button kosik = new Button("KoöÌk");
	private Button info = new Button("Zobraz info");
	private Button cena = new Button("Cena");
	private Button zmenCenu = new Button("K˙pon");
	private Button odosli = new Button("Odoslaù");
	
	private TextField kupon = new TextField();
	
	private ComboBox<String> typ = new ComboBox<String>();
	private LocalDate date = LocalDate.now();
	
	private Objedn·vka objedn·vka;
	
	//Generic method for Nodes
		public void setPosition(Node node, int x, int y){
			
			node.setLayoutX(x);
			node.setLayoutY(y);
		}
		
		public String zobrazObjednavku(Objedn·vka objedn·vka, String druh){
			
			String line = "********";
			String newline = "\n";
			String container = "Kontajner: ";
			String space = " ";
			String pom = "PoËet: ";
					
			return (line+ newline + container + druh + space + newline + pom + objedn·vka.rtti(objedn·vka, druh) + newline + newline);
		}
	
	public Third(Objedn·vka parameter){																//parametrom je arralist s objektami rÙznych kontajnerov, v tejto Ëasti sa dokonËÌ objedn·vka: t.j zistÌ celkovÈ ceny, Ëas...
		
		super();
		
		Pane root = new Pane();
		
		this.objedn·vka=parameter;
		
		root.getChildren().addAll(nadpis,vypis,kosik,info,typ,cena,kupon,zmenCenu,odosli,datum);
		
		setPosition(vypis, 20, 55);//TextArea
		setPosition(kosik, 20, 25);//Button - kosik
		setPosition(nadpis, 150, 0);//Label -nadpis
		setPosition(info, 70, 25);//Button -pre typ kontajnerov
		setPosition(typ, 150, 25);//ComboBox
		setPosition(cena, 290, 25);//Button - zobrazÌ cenu kontajnerov
		setPosition(kupon, 290, 55);
		setPosition(zmenCenu, 290, 85);
		setPosition(odosli, 290, 125);
		setPosition(datum, 150, 375);
		
		nadpis.setId("Header3");
		kupon.setPrefWidth(60);
		kupon.setPromptText("KÛd");
		
		typ.getItems().addAll("UbytovacÌ", "Transportn˝", "N·drû", "Mraziarensk˝");
		typ.setPromptText("Typ");
		
		vypis.setEditable(false);
		vypis.setPrefSize(250, 270);
		
		cena.setOnAction(e->{
			
			vypis.clear();
			vypis.appendText(objedn·vka.ObjednavkaInfo());
		});
		
		odosli.setOnAction(e->{
			vypis.appendText("\n" + "PoËet vozidiel: " + Integer.toString(objedn·vka.NalozKontajnery(objedn·vka.getVzdialenost())));
			//posunie d·tum
			date = date.plusDays(objedn·vka.getPocetDni());
			//System.out.println(date);//////////////////////////////////////////////////////////////////
			datum.setText("Objedn·vka dorazÌ:  " + date.format(DateTimeFormatter.ofPattern("dd.MM.y")));
			date=LocalDate.now();
		});
		
		
		zmenCenu.setOnAction(e->{
			try {
				if(objedn·vka.zlavovyKupon(kupon.getText())){
					Alert b = new Alert(AlertType.INFORMATION);
					
					b.setTitle("PotvrdenÈ");
					b.setHeaderText("KupÛn");
					b.setContentText("Zæavov˝ kupÛn uplatnen˝");
					b.show();
					
					boolean pom = objedn·vka.zlavovyKupon(kupon.getText());//prerobiù eöte na dve funkcie
					kupon.clear();
					vypis.clear();
					vypis.appendText("Cena: " + Integer.toString(objedn·vka.getTotalCena()));
				}
				else{
					Alert b = new Alert(AlertType.INFORMATION);
					
					b.setTitle("Chyba");
					b.setHeaderText("KupÛn");
					b.setContentText("Zæavov˝ kupÛn je neplatn˝");
					b.show();
					kupon.clear();
				}
				
			} catch (Exception e2) {
				Alert b = new Alert(AlertType.INFORMATION);
				
				b.setTitle("Chyba");
				b.setHeaderText("Error!");
				b.setContentText("NeplatnÈ ˙daje");
				b.show();
				kupon.clear();
			}
			
		});
		
		info.setOnAction(e->{
			
			try {
				vypis.clear();
				vypis.appendText(objedn·vka.kontajnerInfo(typ.getSelectionModel().getSelectedItem()));
				
			} catch (ObjectNotFound ex){
				
				Alert b = new Alert(AlertType.INFORMATION);
				
				b.setTitle("Chyba");
				b.setHeaderText("Error!");
				b.setContentText("Zvolen˝ typ kontajnera sa nenach·dza v objedn·vke");
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
			vypis.appendText(zobrazObjednavku(objedn·vka, "UbytovacÌ"));
			vypis.appendText(zobrazObjednavku(objedn·vka, "Transportn˝"));
			vypis.appendText(zobrazObjednavku(objedn·vka, "N·drû"));
			vypis.appendText(zobrazObjednavku(objedn·vka, "Mraziarensk˝"));
			vypis.appendText("PoËet Kontajnerov: " + Integer.toString(objedn·vka.zistiPoËet()));
			
		});
		

		getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.jpg")));								//nastavenie ikony
		
		Scene scene = new Scene(root, 450,450);
		scene.getStylesheets().add(this.getClass().getResource("/gui/Design.css").toExternalForm());
		setTitle("Odoslanie objedn·vky");
		setScene(scene);
		show();
		
	}
	
}
