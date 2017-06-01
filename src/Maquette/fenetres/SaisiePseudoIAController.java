/*SaisiePseudoController	08/05/2017
 * IUT Rodez info1 group Othello
 */
package Maquette.fenetres;

import Maquette.BoitesMessage;
import Maquette.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Gère la saisie du pseudo de l'utilisateur lorsqu'il veut jouer contre l'IA
 * @author Vincent Galinier
 * @author Adrien Bouyssou
 * @author Kerian Georges
 * @author Arthur Pradier
 * @author Mickaël Queudet 
 */
public class SaisiePseudoIAController {

	/** Limite de caractères pour le pseudo */
	private static final int LIMITE_CARACTERES = 12;

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
	 *  ferme la fenêtre courante et
	 *  affiche le menu principal
	 */
	@FXML
	private void handleMenuPrincipal(ActionEvent event) {
		Stage stage = (Stage) MenuPrincipal.getScene().getWindow();
		stage.close();
		Main.showMenuPrincipal();
	}

	/**
	 * ferme la fenêtre courante, initialise une partie avec 
	 * les pseudos entrés dans les champs texte et affiche le plateau
	 */
	@FXML
	private void handleValider(ActionEvent event) {
		// Si les pseudos sont valides (voir verifierValiditePseudo) alors on crée la partie
		if(verifierValiditePseudo()) {
			Stage stage = (Stage) Valider.getScene().getWindow();
			stage.close();
			//Initialisation d'une partie avec le nom entré par le joueur et la difficulté
			PlateauIAController.initPartieIA(
					tf_pseudoJ1.getText(),
					ChoixDifficulteController.choixDifficulte);
			Main.showPlateauIA();
		}
	}

	/**
	 * Affiche l'aide
	 */
	@FXML
	private void handleAide() {
		Main.showAide();
	}
	
	/**
	 * Vérifie la validité du pseudo:
	 * <ul>
	 *     <li>Le pseudo ne doit pas être vide </li>
	 *     <li>Le pseudo doit faire moins de {@code LIMITE_CARACTERES}</li>
	 * </ul>
	 * @return {@code true} si le pseudo est valide, {@code false} sinon
	 */
    public boolean verifierValiditePseudo(){
		if (tf_pseudoJ1.getText().trim().equals("")){
			BoitesMessage.afficher_msgBoxErreur("Pseudo vide !",
					"Le pseudo est vide",
					"Veuillez retaper votre pseudo");
			tf_pseudoJ1.clear();
			return false;
		} else if(tf_pseudoJ1.getText().length() > LIMITE_CARACTERES){
			BoitesMessage.afficher_msgBoxErreur("Pseudo trop grand !",
					"Le ou les pseudos fait plus de " + LIMITE_CARACTERES +  " caractères",
					"Veuillez retaper vos pseudos");
			tf_pseudoJ1.clear();
			return false;
		}
		
		return true;
    }
}
