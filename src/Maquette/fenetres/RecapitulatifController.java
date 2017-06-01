package Maquette.fenetres;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class RecapitulatifController {
	
	/** Bouton de retour au menu */
	@FXML
	public Button retour;

	/** Label du pseudo du gagnant */
	@FXML 
	public Label lbl_pseudoGagnant;
	
	/** Label du score du gagnant */
	@FXML 
	public Label lbl_scoreGagnant /*= new Label(pseudoGagnant)*/;
	
	
	/** Initialise la fen�tre en calculant les diff�rents scores */
	public void initialize() {
		afficherRecapitulatif();
		afficherRecapitulatifIA();
	}

	/** G�re le retour au menu principal */
	public void fermetureRecapitulatif() {
		Stage stage = (Stage) retour.getScene().getWindow();
		stage.close();
	}

	/** Met � jour les param�tres du r�capitulatif */
	public  void setRecapitulatif(String pseudoGG, int scoreGG){
		lbl_pseudoGagnant.setText(pseudoGG);
		lbl_scoreGagnant.setText(String.valueOf(scoreGG));
		
		
	}
	
	/** Affiche le r�capitulatif (met � jour les labels) lors d'une partie               
	 *  Joueur contre Joueur*/
	public void afficherRecapitulatif(){
		System.out.println(PlateauController.pseudoGagnant + " " + 
	                       PlateauController.scoreGagnant);

		lbl_pseudoGagnant.setText(PlateauController.pseudoGagnant);
		lbl_scoreGagnant.setText(String.valueOf(PlateauController.scoreGagnant));
	}
	
	/** Affiche le r�capitulatif (met � jour les labels) lors d'une partie contre
	 * une Intelligence Artificielle
	 */
	public void afficherRecapitulatifIA(){
		lbl_pseudoGagnant.setText(PlateauIAController.pseudoGagnant);
		lbl_scoreGagnant.setText(String.valueOf(PlateauIAController.scoreGagnant));
	}
}
