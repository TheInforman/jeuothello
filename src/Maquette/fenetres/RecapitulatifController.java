/*RecapitulatifController.java		25/06/2017
 * IUT Rodez groupe Othello
 */
package Maquette.fenetres;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Contrôleur d'une fenêtre affichant un bref récapitulatif de la partie terminée
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
	
	
	/** Initialise la fenêtre en calculant les différents scores */
	public void initialize() {
		afficherRecapitulatif();
		afficherRecapitulatifIA();
	}

	/** Affiche le récapitulatif (met à jour les labels) lors d'une partie               
	 *  Joueur contre Joueur*/
	private void afficherRecapitulatif(){
		System.out.println(PlateauController.pseudoGagnant + " " + 
	                       PlateauController.scoreGagnant);

		lbl_pseudoGagnant.setText(PlateauController.pseudoGagnant);
		lbl_scoreGagnant.setText(String.valueOf(PlateauController.scoreGagnant));
	}
	
	/** Affiche le récapitulatif (met à jour les labels) lors d'une partie contre
	 * une Intelligence Artificielle
	 */
	private void afficherRecapitulatifIA(){
		lbl_pseudoGagnant.setText(PlateauIAController.pseudoGagnant);
		lbl_scoreGagnant.setText(String.valueOf(PlateauIAController.scoreGagnant));
	}
	
	/** Gère le retour au menu principal */
	@FXML
	private void fermetureRecapitulatif() {
		Stage stage = (Stage) retour.getScene().getWindow();
		stage.close();
	}

	
	
}
