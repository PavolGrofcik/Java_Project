package gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public  class Main extends Application {
	private Button next = new Button("Vytvori Objednávku");						//deklarácia nového tlaèidla "next"
	private Button exit = new Button("Ukonèi");
	public Label pozdrav = new Label();
	
	@Override
	public  void start(Stage primaryStage) {
		try {
	
			Pane root = new Pane();
	
			root.getChildren().addAll(next,exit,pozdrav);							//pridanie tlaèidiel na stage
			next.setOnAction(e->new Druhé());	
			next.setTranslateX(150);
			next.setTranslateY(50);
			
			pozdrav.setText("Vitajte");
			pozdrav.setTextFill(Color.rgb(245, 61, 0));
			pozdrav.setFont(Font.font("Cambria", 40));
			pozdrav.setAlignment(Pos.CENTER);
			pozdrav.setTranslateX(155);
			pozdrav.setTranslateY(160);
						
			exit.setPrefSize(75,20);												//pozícia tlaèidla exit
			exit.setTranslateX(180);
			exit.setTranslateY(360);

			//exit.setLayoutX(50);													//	SetLayout nefunguje ???
			//exit.setLayoutX(50);
			exit.setOnAction(e->{
				primaryStage.close();
				//System.exit(0);													//nastavenie tlaèidla exit aby ukonèil CELÝ program!
			});
			
			primaryStage.setTitle("Zadaj Objednávku");//
			Scene scene = new Scene(root,415,415);
			primaryStage.setScene(scene);
			primaryStage.show();
			//primaryStage.close();
					
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}