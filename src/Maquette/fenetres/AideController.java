package Maquette.fenetres;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AideController {

	@FXML
	public Button CloseHelp;
	
	public AideController() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Retourne au menu principal et ferme la fenêtre courante
	 */
	public void handleCloseHelp() {
		Stage stage = (Stage) CloseHelp.getScene().getWindow();
		stage.close();
	}

}
