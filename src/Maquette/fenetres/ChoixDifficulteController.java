package Maquette.fenetres;


import Maquette.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChoixDifficulteController {

	@FXML
	public Button Aide;
	
	@FXML
	public Button MenuPrincipal;
	
	public ChoixDifficulteController() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Retourne au menu principal et ferme la fenêtre courante
	 */
	public void handleMenuPrincipal() {
		Stage stage = (Stage) MenuPrincipal.getScene().getWindow();
		stage.close();
		Main.showMenuPrincipal();
	}
	
	/**
	 * Affiche l'aide
	 */
	public void handleAide() {
		Main.showAide();
	}
	
	//TODO -> ajouter la gestion du choix de la difficulte une fois les méthodes terminées

}
