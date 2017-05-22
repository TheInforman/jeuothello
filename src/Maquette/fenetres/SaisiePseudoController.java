package Maquette.fenetres;

import Maquette.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SaisiePseudoController {
	
	@FXML
	public Button MainMenuButton;
	
	@FXML
	public Button Aide;
	
	@FXML
	public Button Valider;
	
	private Main main;

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
		Main.showPlateau();
		
	}
	
	/**
	 * Affiche l'aide 
	 */
	public void handleAide() {
		Main.showAide();
	}
	
	//TODO: gérer la saisie des pseudo lorsque le code sera terminé
	

}
