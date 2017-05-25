package Maquette.fenetres;

import Maquette.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RecapitulatifController {
	
	@FXML
	public Button retour;

	public RecapitulatifController () {
		//TODO 
	}
	
	public void handleRetourMenu() {
		Stage stage = (Stage) retour.getScene().getWindow();
		stage.close();
		Main.showMenuPrincipal();
	}

}
