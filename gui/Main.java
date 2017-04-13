package gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public  class Main extends Application {
	private Button next = new Button("Vytvori Objednávku");						//deklarácia nového tlaèidla "next"
	private Button exit = new Button("Ukonèi");
	private Label pozdrav = new Label("Celková cena");
	
	@Override
	public  void start(Stage primaryStage) {
		try {
	
			VBox root = new VBox(100);
	
			root.getChildren().addAll(pozdrav,next,exit);							//pridanie tlaèidiel na stage
			root.setAlignment(Pos.BASELINE_CENTER);
			
			next.setOnAction(e->new Druhé());	
			
			pozdrav.setText("Vitajte!");
			pozdrav.setTextFill(Color.rgb(245, 61, 0));
			pozdrav.setFont(Font.font("Cambria", 40));
			pozdrav.setAlignment(Pos.CENTER);
						
			exit.setPrefSize(75,20);												//pozícia tlaèidla exit

			exit.setOnAction(e->{
				primaryStage.close();
				//System.exit(0);													//nastavenie tlaèidla exit aby ukonèil CELÝ program!
			});
			
			primaryStage.setTitle("Zadaj Objednávku");//
			Scene scene = new Scene(root,470,450);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.jpg")));			//nastavenie ikony  skrz resource package
					
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}