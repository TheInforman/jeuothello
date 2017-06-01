/* ChoixDifficulteController   08/05/2017
 * info1 groupe Ohello
 */
package Maquette.fenetres;

import Maquette.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Classe controller de la fen�tre de choix de la difficult� de l'IA
 * @author Arthur Pradier, Micka�l Queudet
 */
public class ChoixDifficulteController {

	/** Bouton qui affiche l'aide */
	@FXML
	private Button Aide;
	
	/** Bouton pour retourner au menu principal */
	@FXML
	private Button MenuPrincipal;
	
	/** Choix d'une difficult� de l'IA Facile */
	@FXML
	private Button Facile;
	
	/** Choix d'une difficult� de l'IA Normale */
	@FXML 
	private Button Normal;
	
	/** Choix de la difficult� */
	public static int choixDifficulte;
	
	/**
	 * Constructeur
	 */
	public ChoixDifficulteController() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Retourne au menu principal et ferme la fen�tre courante
	 */
	@FXML
	private void handleMenuPrincipal() {
		Stage stage = (Stage) MenuPrincipal.getScene().getWindow();
		stage.close();
		Main.showMenuPrincipal();
	}
	
	/**
	 * Stocke le choix de l'utilisateur dans une variable, puis ouvre le fen�tre de saisie du pseudo
	 */
	@FXML
	private void handleChoixDifficulteFacile() {
		choixDifficulte = 1;
		Stage stage = (Stage) Facile.getScene().getWindow();
		stage.close();
		Main.showSaisiePseudoIA();
	}
	
	/**
	 *  Stocke le choix de l'utilisateur dans une variable, puis ouvre le fen�tre de saisie du pseudo
	 */
	@FXML
	private void handleChoixDifficulteNormal() {
		choixDifficulte = 2;
		Stage stage = (Stage) Normal.getScene().getWindow();
		stage.close();
		Main.showSaisiePseudoIA();
	}
	
	/**
	 * Affiche l'aide
	 */
	@FXML
	private void handleAide() {
		Main.showAide();
	}

}
