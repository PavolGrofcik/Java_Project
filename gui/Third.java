package gui;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import controller.Objednávka;
import controller.Poèet;
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
	
	private Objednávka objednávka;
	private Poèet poèet;
	
	
	
	public Third(Objednávka parameter, Poèet parameter2){																//parametrom je arralist s objektami rôznych kontajnerov, v tejto èasti sa dokonèí objednávka: t.j zistí celkové ceny, èas...
		
		super();
		
		Pane root = new Pane();
		
		
		this.objednávka=parameter;
		this.poèet=parameter2;

		getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.jpg")));				//nastavenie ikony
		Scene scene = new Scene(root, 450,450);
		setScene(scene);
		show();
		
	}
	
}
