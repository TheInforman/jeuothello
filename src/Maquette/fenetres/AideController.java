/* AideController.java		08/05/2017
 * info1 groupe Othello 
 */
package Maquette.fenetres;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * controlleur de la fen�tre d'aide
 * @author Arthur Pradier Micka�l Queudet
 */
public class AideController {

	/** Bouton de fermeture de l'aide */
	@FXML
	public Button CloseHelp;
	
	/**
	 * Constructeur de l'objet
	 */
	public AideController() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Retourne au menu principal et ferme la fen�tre courante
	 */
	public void handleCloseHelp() {
		Stage stage = (Stage) CloseHelp.getScene().getWindow(); //fermeture de la fen�tre ou le bouton est situ�e
		stage.close();
	}

}
