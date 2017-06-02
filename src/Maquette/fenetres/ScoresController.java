/* ScoresController.java	25/08/2017
 * IUT Rodez groupe Othello
 */
package Maquette.fenetres;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import Maquette.BoitesMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import othello.Scores;
import outils.OutilFichier;

/**
 * Gestion de la fenêtre affichant un historique de score des 6 dernieres
 * parties
 * @author Arthur Pradier
 */
public class ScoresController {

	/** Bouton de retour vers le menu principal */
	@FXML
	private Button btn_retour;

	/** Bouton de suppression de la sauvegarde des scores */
	@FXML
	private Button btn_supprimer;

	/** GridPane des scores */
	@FXML
	private GridPane grid;

	/**
	 * Initialise la fenêtre de consultation des scores:	 
	 * Crée le dossier et le fichier si nécessaire
	 */
	public void initialize(){
		// Restauration du fichier de sauvegarde 

		File file = new File(OutilFichier.getEmplacementSaveScores());
		/*
		 * On vérifie si le répertoire Othello existe : 
		 *     -  Il n'existe pas alors on le crée et on enregistre
		 *        le fichier
		 *     -  Il existe mais le fichier .sothl non alors 
		 *         on crée le fichier et on enregistre
		 *     -  Les deux existent, on restaure le fichier et on enregistre        
		 */
		if(!OutilFichier.isRepertoireOthelloExistant()){
			OutilFichier.creerRepertoireOthello();
			Scores courant = new Scores();
			OutilFichier.enregistrerScores(courant);
		} else {
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
	}

	/**
	 * Insère les éléments du tableau des scores dans les labels de l'interface 
	 * graphique
	 * @param aRemplir le score à remplir
	 */
	private void remplirScores(Scores aRemplir) {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				if (!(aRemplir.getScore()[i][j] == null)){
					Label lbl = new Label(aRemplir.getScore()[i][j]);
					lbl.setFont(new Font("Arial", 11));
					grid.add(lbl, i, j);
				} else {
					Label lbl = new Label(" ");
					grid.add(lbl, i, j);
				}

			}
		}
	}

	/**
	 * Supprime le fichier de sauvegarde après confirmation de
	 * l'utilisateur. 
	 */
	@FXML
	private void handleSupprimer(){
		if (
				BoitesMessage.afficher_msgBoxConfirmation(
						"Supprimer la sauvegarde des scores",
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

	/**
	 * Ferme la fenêtre des scores 
	 */
	@FXML
	public void HandleRetour() {
		Stage stage = (Stage) btn_retour.getScene().getWindow();
		stage.close();
	}

}

