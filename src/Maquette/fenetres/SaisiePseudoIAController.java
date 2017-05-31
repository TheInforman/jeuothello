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
	public TextField tf_pseudoJ1;

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
	public SaisiePseudoIAController() {

	}

	/**
	 *  ferme la fen�tre courante et
	 *  affiche le menu principal
	 */
	@FXML
	public void handleMenuPrincipal(ActionEvent event) {
		Stage stage = (Stage) MainMenuButton.getScene().getWindow();
		stage.close();
		Main.showMenuPrincipal();
	}

	/**
	 * ferme la fen�tre courante, initialise une partie avec 
	 * les pseudos entr�s dans les champs texte et affiche le plateau
	 */
	@FXML
	public void handleValider(ActionEvent event) {
		Stage stage = (Stage) Valider.getScene().getWindow();
		stage.close();
		PlateauController.initPartie(tf_pseudoJ1.getText(), "Bot"); //attribution d'un nom par d�faut � l'IA
		Main.showPlateauIA();
	}

	/**
	 * Affiche l'aide
	 */
	@FXML
	public void handleAide() {
		Main.showAide();
	}

}
