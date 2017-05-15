package gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.omg.CORBA.TIMEOUT;

import containers.UbytovacÌ;
import controller.NespravnyRozsah;
import controller.ObjectNotFound;
import controller.Objedn·vka;
import controller.PoËet;
import javafx.animation.PauseTransition;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Third extends Stage  {
	
	private Label datum = new Label();
	private Label nadpis = new Label("Uû len krok k odoslaniu...");
	
	private TextArea vypis = new TextArea();
	
	private Button kosik = new Button("KoöÌk");
	private Button info = new Button("Zobraz info");
	private Button cena = new Button("Cena");
	private Button zmenCenu = new Button("KupÛn");
	private Button odosli = new Button("Odoslaù");

	
	private TextField kupon = new TextField();
	
	private ComboBox<String> typ = new ComboBox<String>();
	private LocalDate date = LocalDate.now();
	
	private Objedn·vka objedn·vka;
	
	private int pom =0;
	private int num =0;
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
		setPosition(datum, 140, 375);
		
		datum.setId("Datum");
		nadpis.setId("Header3");
		kupon.setPrefWidth(60);
		kupon.setPromptText("KÛd");
		
		typ.getItems().addAll("UbytovacÌ", "Transportn˝", "N·drû", "Mraziarensk˝");
		typ.setPromptText("Typ");
		
		vypis.setEditable(false);
		vypis.setPrefSize(250, 270);
		
		cena.setOnAction(e->{
			if(num == 0){
			vypis.clear();
			vypis.appendText(objedn·vka.ObjednavkaInfo());
			num++;
			}
			else {
				vypis.clear();
				vypis.appendText("**********\nMesto: " + objedn·vka.getMesto() + "\n" + "Vzdialenosù: " + Integer.toString(objedn·vka.getVzdialenost()) +
						"\n" + "Cena: " + Integer.toString(objedn·vka.getTotalCena()) + "\n" + "PoËet dnÌ: " + Integer.toString(objedn·vka.getPocetDni()) + 
						"\n" + "PoËet Kontajnerov: " + Integer.toString(objedn·vka.getPocetKontajnerov()));
			}
		});
		
		odosli.setOnAction(e->{
			
			vypis.clear();
			vypis.appendText("PoËet vozidiel: " + Integer.toString(objedn·vka.NalozKontajnery(objedn·vka.getVzdialenost())) + "\n" + "PoËet Kontajnerov: " + Integer.toString(objedn·vka.zistiPoËet()) + "\n" +
			"Celkov˝ Ëas s Exportom: " + Integer.toString(objedn·vka.getPocetDni()) + "\n" +  "œakujeme za vaöu objedn·vku" + "\n" + "UkonËite aplik·ciu");
			nadpis.setText("Objedn·vka bola spracovan·");
			//posunie d·tum o produkËn˝ Ëas
			date = date.plusDays(objedn·vka.getPocetDni());
			datum.setText("Objedn·vka dorazÌ:  " + date.format(DateTimeFormatter.ofPattern("dd.MM.y")));
			date=LocalDate.now();
			
			//Vypne program po 3 sekund·ch odkliknutÌ tlaËidla odosli
			/*PauseTransition delay = new PauseTransition(Duration.seconds(4));
			delay.setOnFinished( event -> close() );
			delay.play();*/
			
			try {
				Thread.sleep(5000);
				hide();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		zmenCenu.setOnAction(e->{
			
			 if (pom == 0 && objedn·vka.zlavovyKupon(kupon.getText())!=0) {
				vypis.clear();
				vypis.appendText("Nov· cena: " + Integer.toString(objedn·vka.zlavovyKupon(kupon.getText())));
				kupon.clear();
				pom++;
			}
			else{
				Alert alert = new Alert(AlertType.INFORMATION);
				
				vypis.clear();
				kupon.clear();
				alert.setTitle("Chyba");
				alert.setHeaderText("");
				alert.setContentText("Nespr·vny kupÛn");
				alert.show();
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
			//vypis.appendText("PoËet Kontajnerov: " + Integer.toString(objedn·vka.zistiPoËet()));
			
		});
		

		getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.jpg")));								//nastavenie ikony
		
		Scene scene = new Scene(root, 450,450);
		scene.getStylesheets().add(this.getClass().getResource("/gui/Design.css").toExternalForm());
		setTitle("Odoslanie objedn·vky");
		setScene(scene);
		show();
		
	}
	
}
