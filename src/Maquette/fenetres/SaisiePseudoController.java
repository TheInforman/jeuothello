package Maquette.fenetres;

import Maquette.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SaisiePseudoController {

	@FXML
	public TextField tf_pseudoJ1;

	@FXML 
	public TextField tf_pseudoJ2;

	@FXML
	public Button MainMenuButton;

	@FXML
	public Button Aide;

	@FXML
	public Button Valider;

	/**
	 * Constructeur de la classe
	 */
	public SaisiePseudoController() {

	}

	@FXML
	public void handleMenuPrincipal(ActionEvent event) {
		Stage stage = (Stage) MainMenuButton.getScene().getWindow();
		stage.close();
		Main.showMenuPrincipal();
	}

	@FXML
	public void handleValider(ActionEvent event) {
		Stage stage = (Stage) Valider.getScene().getWindow();
		stage.close();
		PlateauController.initPartie(tf_pseudoJ1.getText(), tf_pseudoJ2.getText());
		Main.showPlateau();
	}

	/**
	 * Affiche l'aide 
	 */
	public void handleAide() {
		Main.showAide();
	}

	


}
