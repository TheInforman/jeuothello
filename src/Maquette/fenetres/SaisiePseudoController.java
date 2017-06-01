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
 * G�re la saisie des pseudos des deux utilisateurs sur la fen�tre associ�e
 * @author Arthur Pradier Micka�l Queudet
 */
public class SaisiePseudoController {

	/** le champ texte ou le joueur 1 entrera son pseudo */
	@FXML
	private TextField tf_pseudoJ1;

	/** le champ texte ou le joueur 2 entrera son pseudo */
	@FXML 
	private TextField tf_pseudoJ2;

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
	public SaisiePseudoController() {

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
	 * Ferme la fen�tre courante, initialise une partie avec 
	 * les pseudos entr�s dans les champs texte et affiche le plateau
	 */
	@FXML
	private void handleValider(ActionEvent event) {
		Stage stage = (Stage) Valider.getScene().getWindow();
		stage.close();
		//Initialisation d'une partie avec les pseudos entr�s par les diff�rents joueurs
		PlateauController.initPartie(
				tf_pseudoJ1.getText(),
				tf_pseudoJ2.getText(),
				0);
		Main.showPlateau();
	}

	/**
	 * Affiche l'aide
	 */
	@FXML
	private void handleAide() {
		Main.showAide();
	}

	


}
