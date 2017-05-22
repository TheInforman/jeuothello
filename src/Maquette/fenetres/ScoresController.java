package Maquette.fenetres;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ScoresController {

	@FXML
	public Button Retour;
	
	public ScoresController() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
	public void HandleRetour() {
		Stage stage = (Stage) Retour.getScene().getWindow();
		stage.close();
	}

}
