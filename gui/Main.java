package gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public  class Main extends Application {
	
	private Button next = new Button("Vytvori� Objedn�vku");						//deklar�cia nov�ho tla�idla "next"
	private Button exit = new Button("Ukon�i");
	private Label pozdrav = new Label();
	
	
	@Override
	public  void start(Stage primaryStage) {
		try {
	
			VBox root = new VBox(100);
	
			root.getChildren().addAll(pozdrav,next,exit);							//pridanie tla�idiel na stage
			root.setAlignment(Pos.BASELINE_CENTER);
			
			next.setOnAction(e->{
				new Druh�();
				primaryStage.close();
				});	
			
			pozdrav.setText("Vitajte!");
			pozdrav.setTextFill(Color.rgb(245, 61, 0));
			pozdrav.setFont(Font.font("Cambria", 40));
			pozdrav.setAlignment(Pos.CENTER);
						
			exit.setPrefSize(75,20);												
			exit.setOnAction(e->{													//ukon�enie programu
				Alert cancel = new Alert(AlertType.CONFIRMATION);
				cancel.setTitle("Ukon�i�?");
				cancel.setHeaderText("Ukon�i� objedn�vku?");
				cancel.setContentText("Objedn�vka nebude ulo�en�");
			});
			
			primaryStage.setTitle("Zadaj Objedn�vku");//
			Scene scene = new Scene(root,470,450);
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.jpg")));			//nastavenie ikony  skrz resource package
			primaryStage.setScene(scene);
			primaryStage.show();
					
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}