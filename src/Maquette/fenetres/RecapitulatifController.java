/*RecapitulatifController.java		25/06/2017
 * IUT Rodez groupe Othello
 */
package Maquette.fenetres;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Contr�leur d'une fen�tre affichant un bref r�capitulatif de la partie termin�e
 * @author Arthur Pradier 
 */
public class RecapitulatifController {
	
	/** Bouton de retour au menu */
	@FXML
	private Button retour;

	/** Label du pseudo du gagnant */
	@FXML 
	private Label lbl_pseudoGagnant;
	
	/** Label du score du gagnant */
	@FXML 
	private Label lbl_scoreGagnant /*= new Label(pseudoGagnant)*/;
	
	
	/** Initialise la fen�tre en calculant les diff�rents scores */
	public void initialize() {
		afficherRecapitulatif();
		afficherRecapitulatifIA();
	}

	/** Affiche le r�capitulatif (met � jour les labels) lors d'une partie               
	 *  Joueur contre Joueur*/
	private void afficherRecapitulatif(){
		System.out.println(PlateauController.pseudoGagnant + " " + 
	                       PlateauController.scoreGagnant);

		lbl_pseudoGagnant.setText(PlateauController.pseudoGagnant);
		lbl_scoreGagnant.setText(String.valueOf(PlateauController.scoreGagnant));
	}
	
	/** Affiche le r�capitulatif (met � jour les labels) lors d'une partie contre
	 * une Intelligence Artificielle
	 */
	private void afficherRecapitulatifIA(){
		lbl_pseudoGagnant.setText(PlateauIAController.pseudoGagnant);
		lbl_scoreGagnant.setText(String.valueOf(PlateauIAController.scoreGagnant));
	}
	
	/** G�re le retour au menu principal */
	@FXML
	private void fermetureRecapitulatif() {
		Stage stage = (Stage) retour.getScene().getWindow();
		stage.close();
	}

	
	
}
