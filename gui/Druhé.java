package gui;
import containers.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Druhé extends Stage {
	int mnozstvo;															//globalna premenna ktora bude slúi na nastavenie ceny v mraziarenskom kontajneri
	
	private Button nastav =  new Button("Nastav mnozstvo");
	private Label lbl1 = new Label("Zadaj mnozstvo");
	private TextField txt1 = new TextField();
	private Button zisticenu = new Button("Zisti cenu");
	private Button vytvor = new Button ("Vytvor");
	private ComboBox<String> box = new ComboBox<String>();
	private Button back = new Button("Spä");
	
	
	public Druhé(){
		super();															//zavolanie superclass metódy start, netreba znovu vola funkciu start
		
		VBox root = new VBox();
		setTitle("Vytvor Objednávku");
		
		root.getChildren().addAll(lbl1,nastav,txt1,zisticenu,vytvor, box,back);
		
		Scene scene = new Scene(root,415,415);								//nastavenie druhého okna s parametrami
		setScene(scene);													//zobrazenie sceny
		show();																//show ukaz scenu
		
		
		nastav.setTranslateX(0);
		nastav.setTranslateY(365);
		
		lbl1.setTranslateX(5);
		lbl1.setTranslateY(5);
		box.setTranslateX(0);
		box.setTranslateY(0);
		
		txt1.setTranslateX(0);
		txt1.setTranslateY(0);
		
		vytvor.setLayoutX(100);
		vytvor.setLayoutY(50);
		vytvor.setPrefSize(60, 25);
		
		back.setTranslateX(200);
		back.setTranslateY(240);
		back.setPrefSize(70, 25);
		
		box.getItems().addAll("Prepravnı", "Mraziarensky", "Ubytovací", "Multifunkènı");
		
		vytvor.setOnAction(e->{											//Inicializacia tlacidla VYTVOR
			
			try{
			lbl1.setText("Zadaj poèet Kontajnerov");
			mnozstvo = Integer.parseInt(txt1.getText());
			Mraziarenskı[] m = new Mraziarenskı[mnozstvo];
			for(int i = 1; i<=mnozstvo;i++){
				m[i] = new Mraziarenskı();
			}
			}catch (Exception ee){
				Alert b = new Alert(AlertType.ERROR);					//chyba pri nezadaní èísla(mnozstvo)
				b.setTitle("Chyba");
				b.setContentText("Zadaj poèet");
				//b.showAndWait();
				b.show();
			}
			
		});
		
		nastav.setOnAction(e->{
			try{
				
			mnozstvo=Integer.parseInt(txt1.getText());					//nastavenie mnozstva 
				
			
			txt1.clear();
			lbl1.setText(Integer.toString(mnozstvo));
			}catch (Exception ex) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Chyba");
				a.setContentText("Zadaj cenu");
				a.showAndWait();
			}
		});
		
		
		
		
		
		zisticenu.setOnAction(e->{										//Funkcia zisti cenu
		try{
		lbl1.setText(Integer.toString(mnozstvo));
			
			
		}catch(Exception except){
			
			Alert c = new Alert(AlertType.ERROR);
			c.setTitle("Chyba");
			c.setContentText("Nenastavil si iadne mnostvo");
			c.showAndWait();
		}

			
		});
		
		back.setOnAction(e->{										//Funkcia vracajúca hlavne okno
		close();
		});
				
	}
	
}
