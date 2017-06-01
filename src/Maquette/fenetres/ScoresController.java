package Maquette.fenetres;

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
		Scores courant = OutilFichier.restaurerScores(
				OutilFichier.getRepertoireParDefaut() +"\\Othello\\scoresOthello.sothl");
		// Remplissage du tableau de score
		remplirScores(courant); 
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
	 * Permet de supprimer le fichier de sauvegarde
	 */
	@FXML
	private void handleSupprimer(){
		// TODO écrire le corps
	}
}
