package gui;

import java.util.Optional;

import controller.Objedn·vka;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main extends Application {

	private Button next = new Button("Vytvoriù Objedn·vku"); // deklar·cia
	private Button exit = new Button("UkonËiù");
	private Label pozdrav = new Label();

	private Objedn·vka objednavka = new Objedn·vka();
	
	@Override
	public void start(Stage primaryStage) {
		try {

			VBox root = new VBox(100);
			root.getChildren().addAll(pozdrav, next, exit);
			root.setAlignment(Pos.BASELINE_CENTER);

			next.setOnAction(e -> {
				
				new Second(objednavka);
				primaryStage.close();
			});

			pozdrav.setText("Vitajte!");
			pozdrav.setTextFill(Color.rgb(245, 61, 0));
			pozdrav.setFont(Font.font("Cambria", 40));
			pozdrav.setAlignment(Pos.CENTER);

			exit.setPrefSize(75, 20);
			exit.setOnAction(e -> {
				
				ButtonType buttonOk = new ButtonType("OK");
				ButtonType buttonClose = new ButtonType("Cancel");

				Alert exit = new Alert(AlertType.CONFIRMATION);		
				exit.setTitle("UkonËiù?");
				exit.setHeaderText("Ste si ist˝, ûe chcete ukonËiù program?");
				exit.setContentText("Zvoæte moûnosù");

				Optional<ButtonType> result = exit.showAndWait();

				if (result.get() == ButtonType.OK) {
					exit.close();
					primaryStage.close();
				} else if (result.get() == ButtonType.CLOSE) {
					exit.close();
				}
			});

			primaryStage.setTitle("Zadaj Objedn·vku");//
			Scene scene = new Scene(root, 470, 450);
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.jpg")));
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Chyba");
			alert.setHeaderText("Error");
			alert.setContentText("Program sa bohuûiaæ musÌ ukonËiù");
			alert.show();
			
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}