package gui;

import java.util.Optional;

import javax.management.loading.PrivateClassLoader;

import org.omg.CORBA.PUBLIC_MEMBER;

import controller.Objedn·vka;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main extends Application {

	private Button next = new Button("Vytvoriù Objedn·vku");
	private Button exit = new Button("UkonËiù");
	private Button adminmode = new Button("Admin Mode");
	private Label pozdrav = new Label();

	private Objedn·vka objednavka = new Objedn·vka();
	
	//Inner Class in GUI
	public class Login extends Stage{
		
		private Label username = new Label("Username");
		private Label password = new Label("Password");
		private Label notification = new Label();
		
		private TextField name = new TextField();
		private PasswordField pass= new PasswordField();
		
		private Button login = new Button("Login");
		
		public Login(){
			super();
			
			AnchorPane root = new AnchorPane();
			root.getChildren().addAll(username,password,pass,name,login,notification);
			initModality(Modality.APPLICATION_MODAL);
			
			
			name.setPromptText("Zadajte meno");
			pass.setPromptText("Zadajte heslo");
			
			username.setLayoutX(25);
			username.setLayoutY(25);
			username.setId("Username");
			
			password.setLayoutX(25);
			password.setLayoutY(50);
			password.setId("Password");
			
			name.setLayoutX(100);
			name.setLayoutY(25);			
			
			pass.setLayoutX(100);
			pass.setLayoutY(50);
			
			login.setLayoutX(125);
			login.setLayoutY(150);
			
			
			notification.setLayoutX(70);
			notification.setLayoutY(100);
			notification.setId("Notification");

			login.setOnAction(e->{
				
				try {
					
					if(name.getText().equals("Admin") && pass.getText().equals("123")){
						new AdminMode(objednavka);
						this.close();
					}
					
					else{
						notification.setText("NeplatnÈ meno alebo heslo");
						name.clear();
						pass.clear();
					}
					
				} catch (Exception e2) {
					notification.setText("NeplatnÈ ˙daje");
				}
				
			});
			
			getIcons().add(new Image(getClass().getResourceAsStream("/resources/login-logo.png")));
			Scene scene = new Scene(root,300,200);
			scene.getStylesheets().add(this.getClass().getResource("/gui/Design.css").toExternalForm());
			setTitle("Log In");
			setResizable(false);
			setScene(scene);
			show();
			
		}
	}
	
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {

			VBox root = new VBox(70);
			root.getChildren().addAll(pozdrav, next,exit,adminmode);
			root.setAlignment(Pos.BASELINE_CENTER);

			
			pozdrav.setText("Vitajte!");
			pozdrav.setTextFill(Color.rgb(245, 61, 0));
			pozdrav.setFont(Font.font("Cambria", 40));
			pozdrav.setAlignment(Pos.CENTER);
			exit.setPrefSize(75, 20);
			
			next.setOnAction(e -> {
				
				new Second(objednavka);
				primaryStage.close();
			});
			
			adminmode.setOnAction(e->{
				new Login();
				
			});
			
			exit.setOnAction(e -> {
				
				//ButtonType buttonOk = new ButtonType("OK");
				//ButtonType buttonClose = new ButtonType("Cancel");

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
			alert.setContentText("Something went wrong");
			alert.show();
			
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}