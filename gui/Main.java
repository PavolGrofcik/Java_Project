package gui;

import java.util.Optional;

import controller.Objedn�vka;
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

	private Button next = new Button("Vytvori� Objedn�vku"); // deklar�cia
	private Button exit = new Button("Ukon�i");
	private Label pozdrav = new Label();

	private Objedn�vka objednavka = new Objedn�vka();
	@Override
	public void start(Stage primaryStage) {
		try {

			VBox root = new VBox(100);

			root.getChildren().addAll(pozdrav, next, exit); // pridanie
															// tla�idiel na
															// stage
			root.setAlignment(Pos.BASELINE_CENTER);

			next.setOnAction(e -> {
				new Druh�(objednavka);
				primaryStage.close();
			});

			pozdrav.setText("Vitajte!");
			pozdrav.setTextFill(Color.rgb(245, 61, 0));
			pozdrav.setFont(Font.font("Cambria", 40));
			pozdrav.setAlignment(Pos.CENTER);

			exit.setPrefSize(75, 20);
			exit.setOnAction(e -> {
				ButtonType buttonOk = new ButtonType("OK"); // tla�idlo OK
				ButtonType buttonClose = new ButtonType("Cancel"); // tla�idlo
																	// Cancel

				// ukon�enie programu
				Alert exit = new Alert(AlertType.CONFIRMATION); // set
																// properties
																// and prompttex
																// of
																// Confirmation
				exit.setTitle("Ukon�i�?");
				exit.setHeaderText("Ste si ist�, �e chcete ukon�i� program?");
				exit.setContentText("Zvo�te mo�nos�");

				Optional<ButtonType> result = exit.showAndWait();

				if (result.get() == ButtonType.OK) {
					exit.close();
					primaryStage.close();
				} else if (result.get() == ButtonType.CLOSE) {
					exit.close();
				}
			});

			primaryStage.setTitle("Zadaj Objedn�vku");//
			Scene scene = new Scene(root, 470, 450);
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.jpg"))); // nastavenie
																											// ikony
																											// skrz
																											// resource
																											// package
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}