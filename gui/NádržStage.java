package gui;

import controller.Objedn�vka;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class N�dr�Stage extends Stage {
	
	
	public N�dr�Stage(Objedn�vka objedn�vka){
		
		VBox root = new VBox(5);
		root.getChildren().addAll();
		
		root.setAlignment(Pos.BASELINE_CENTER);
		initModality(Modality.APPLICATION_MODAL);
		setTitle("N�dr�");
		
		
		
		
		
		
		
		
		getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.jpg")));
		Scene scene = new Scene(root, 250,250);
		setScene(scene);
		show();
		
		
		
		
	}

}
