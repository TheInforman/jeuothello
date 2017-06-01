package Maquette.fenetres;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import Maquette.BoitesMessage;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import othello.Scores;
import outils.OutilFichier;

public class ScoresController {

	/** Bouton de retour vers le menu principal */
	@FXML
	public Button btn_retour;

	/** Bouton de suppression de la sauvegarde des scores */
	@FXML
	public Button btn_supprimer;

	/** GridPane des scores */
	@FXML
	private GridPane grid;


	@FXML
	public void HandleRetour() {
		Stage stage = (Stage) btn_retour.getScene().getWindow();
		stage.close();
	}

	/**
	 * Initialise la fenêtre de consultation des scores:	 
	 * restaure le fichier des scores
	 */
	public void initialize(){
		// Restauration du fichier de sauvegarde 

		File file = new File(OutilFichier.getEmplacementSaveScores());

		// Vérification si le fichier de scores existe
		if(!file.exists()){
			Scores courant = new Scores();
			OutilFichier.enregistrerScores(courant);
		} else {
			Scores courant = OutilFichier.restaurerScores(
					OutilFichier.getEmplacementSaveScores());
			// Remplissage du tableau de score
			remplirScores(courant); 
		}
	}

	private void remplirScores(Scores aRemplir) {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				if (!(aRemplir.getScore()[i][j] == null)){
					Label lbl = new Label(aRemplir.getScore()[i][j]);
					lbl.setFont(new Font("Arial", 11));
					lbl.setAlignment(Pos.CENTER);
					grid.add(lbl, i, j);
				} else {
					Label lbl = new Label(" ");
					grid.add(lbl, i, j);
				}

			}
		}
	}

	/**
	 * Supprime le fichier de sauvegarde (après une BoîteMessage)
	 * 
	 */
	@FXML
	private void handleSupprimer(){
		if (
				BoitesMessage.afficher_msgBoxConfirmation(
						"Supprimer la sauvegarde des scors",
						"Vous êtes sur le point de supprimer votre fichier de sauvegarde",
						"Souhaitez vous réellement supprimer ?"
						)) {
			// Définition de l'emplacement de la sauvegarde
			Path emplacementSauvegarde;
			emplacementSauvegarde =
					Paths.get(OutilFichier.getEmplacementSaveScores());
			OutilFichier.supprimerSauvegarde(emplacementSauvegarde);

			// Fermeture de la fenêtre
			Stage stage = (Stage) btn_supprimer.getScene().getWindow();
			stage.close();
		}
	}

}

