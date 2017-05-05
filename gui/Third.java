package gui;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import controller.Objedn�vka;
import controller.Po�et;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Third extends Stage {
	
	private Objedn�vka objedn�vka;
	private Po�et po�et;
	
	
	
	public Third(Objedn�vka parameter, Po�et parameter2){																//parametrom je arralist s objektami r�znych kontajnerov, v tejto �asti sa dokon�� objedn�vka: t.j zist� celkov� ceny, �as...
		
		super();
		
		Pane root = new Pane();
		
		
		this.objedn�vka=parameter;
		this.po�et=parameter2;

		getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.jpg")));				//nastavenie ikony
		Scene scene = new Scene(root, 450,450);
		setScene(scene);
		show();
		
	}
	
}
