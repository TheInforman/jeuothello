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
	public Button Aide;
	
	/** Bouton pour retourner au menu principal */
	@FXML
	public Button MenuPrincipal;
	
	/**
	 * Constructeur
	 */
	public ChoixDifficulteController() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Retourne au menu principal et ferme la fen�tre courante
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
	
	//TODO -> ajouter la gestion du choix de la difficulte une fois les m�thodes termin�es

}
