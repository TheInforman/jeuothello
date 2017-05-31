package Maquette.fenetres;

import Maquette.Main;
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
	public Label lbl_scoreGagnant = new Label(pseudoGagnant);
	
	/** Score du gagnant */
	public static int scoreGagnant;
	
	/** Pseudo du gagnant */
	public static String pseudoGagnant;
	
	

	/** Gère le retour au menu principal */
	public void handleRetourMenu() {
		Stage stage = (Stage) retour.getScene().getWindow();
		stage.close();
		Main.showMenuPrincipal();
	}

	/** Met à jour les paramètres du récapitulatif */
	public  void setRecapitulatif(String pseudoGG, int scoreGG){
		pseudoGagnant = pseudoGG;
		scoreGagnant = scoreGG;
		lbl_pseudoGagnant.setText(pseudoGG);
		lbl_scoreGagnant.setText(String.valueOf(scoreGG));
		
		
	}
	
	/** Affiche le récapitulatif (met à jour les labels) */
	public void afficherRecapitulatif(){
		lbl_pseudoGagnant.setText(pseudoGagnant);
		lbl_scoreGagnant.setText(String.valueOf(scoreGagnant));
	}
}
