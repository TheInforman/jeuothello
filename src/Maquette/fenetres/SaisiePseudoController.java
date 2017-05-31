package Maquette.fenetres;

import Maquette.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SaisiePseudoController {

	/** le champ texte ou le joueur 1 entrera son pseudo */
	@FXML
	public TextField tf_pseudoJ1;

	/** le champ texte ou le joueur 2 entrera son pseudo */
	@FXML 
	public TextField tf_pseudoJ2;

	/** Bouton permettant de retourner au menu principal */
	@FXML
	public Button MainMenuButton;

	/** Bouton permettant d'afficher l'aide */
	@FXML
	public Button Aide;

	/** Bouton permettant de valider la saisie des pseudos et de lancer la partie */
	@FXML
	public Button Valider;

	/**
	 * Constructeur de la classe
	 */
	public SaisiePseudoController() {

	}

	/**
	 *  ferme la fenêtre courante et
	 *  affiche le menu principal
	 */
	@FXML
	public void handleMenuPrincipal(ActionEvent event) {
		Stage stage = (Stage) MainMenuButton.getScene().getWindow();
		stage.close();
		Main.showMenuPrincipal();
	}

	/**
	 * ferme la fenêtre courante, initialise une partie avec 
	 * les pseudos entrés dans les champs texte et affiche le plateau
	 */
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
