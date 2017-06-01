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
	
	public void initialize() {
		afficherRecapitulatif();
	}

	/** Gère le retour au menu principal */
	public void fermetureRecapitulatif() {
		Stage stage = (Stage) retour.getScene().getWindow();
		stage.close();
	}

	/** Met à jour les paramètres du récapitulatif */
	public  void setRecapitulatif(String pseudoGG, int scoreGG){
		lbl_pseudoGagnant.setText(pseudoGG);
		lbl_scoreGagnant.setText(String.valueOf(scoreGG));
		
		
	}
	
	/** Affiche le récapitulatif (met à jour les labels) */
	public void afficherRecapitulatif(){
		System.out.println(PlateauController.pseudoGagnant + " " + PlateauController.scoreGagnant);
		lbl_pseudoGagnant.setText(PlateauController.pseudoGagnant);
		lbl_scoreGagnant.setText(String.valueOf(PlateauController.scoreGagnant));
	}
}
