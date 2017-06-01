/*SaisiePseudoController	08/05/2017
 * IUT Rodez info1 group Othello
 */
package Maquette.fenetres;

import Maquette.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * G�re la saisie du pseudo de l'utilisateur lorsqu'il veut jouer contre l'IA
 * @author Arthur Pradier Micka�l Queudet
 */
public class SaisiePseudoIAController {

	/** le champ texte ou le joueur 1 entrera son pseudo */
	@FXML
	private TextField tf_pseudoJ1;

	/** Bouton permettant de retourner au menu principal */
	@FXML
	private Button MenuPrincipal;

	/** Bouton permettant d'afficher l'aide */
	@FXML
	private Button Aide;

	/** Bouton permettant de valider la saisie des pseudos et de lancer la partie */
	@FXML
	private Button Valider;

	/**
	 * Constructeur de la classe
	 */
	public SaisiePseudoIAController() {

	}

	/**
	 *  ferme la fen�tre courante et
	 *  affiche le menu principal
	 */
	@FXML
	private void handleMenuPrincipal(ActionEvent event) {
		Stage stage = (Stage) MenuPrincipal.getScene().getWindow();
		stage.close();
		Main.showMenuPrincipal();
	}

	/**
	 * ferme la fen�tre courante, initialise une partie avec 
	 * les pseudos entr�s dans les champs texte et affiche le plateau
	 */
	@FXML
	private void handleValider(ActionEvent event) {
		Stage stage = (Stage) Valider.getScene().getWindow();
		stage.close();
		//Initialisation d'une partie avec le nom entr� par le joueur et 
		PlateauIAController.initPartieIA(
				tf_pseudoJ1.getText(),
				ChoixDifficulteController.choixDifficulte);
		Main.showPlateauIA();
	}

	/**
	 * Affiche l'aide
	 */
	@FXML
	private void handleAide() {
		Main.showAide();
	}

}
