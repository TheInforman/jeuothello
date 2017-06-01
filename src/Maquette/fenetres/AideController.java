/* AideController.java		08/05/2017
 * info1 groupe Othello 
 */
package Maquette.fenetres;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * controlleur de la fen�tre d'aide
 * @author Arthur Pradier Micka�l Queudet
 */
public class AideController {

	@FXML
	private Text Aide; 
	
	String aideOthello = "Bienvenue dans l'aide de ce jeu d'Othello. Pour commencer � jouer, choisissez simplement ce que vous voulez faire. \n\nSi vous voulez jouer contre l'ordinateur,"
						+" cliquez sur \"jouer contre l'IA\", choisissez votre niveau de difficult� puis saisissez votre pseudo. \n\nSi vous pr�f�rez jouer avec un ami, cliquez sur " +
						 "\"Jouer avec un ami\". Chaque joueur devra entrer un pseudo. Une fois vos choix effectu�s, appuyez sur Valider. \n\nSi vous d�sirez reprendre une partie " +
						 "pr�c�demment sauvegard�e, cliquez sur \"charger une partie\" et s�lectionnez la partie correspondante. Vous pouvez retrouver facilement votre fichier " +
						 "de sauvegarde: il est identifi� par la date et l'heure ou vous avez sauvegard�.\n\n Une fois la partie lanc�e, l'objectif est de poss�der le plus de pions" +
						 "possible � la fin de la partie. Pour poser un jeton, il vous suffit de cliquer sur la case o� vous voulez le positionner. Le pion doit �tre plac� de sorte" +
						 " � encercler un ou plusieurs pions adverses, et ce horizontalement, verticalement ou diagonalement. Lorsque vous placez un pion, tous les pions adverses " +
						 "ainsi encercl�s seront chang�s en votre couleur. La partie se termine lorsque le plateau est plein ou si aucun des deux joueurs ne peut jouer son tour.\n\n" +
						 "Quand vous jouez contre l'IA, vous devez lui indiquer manuellement qu'elle doit jouer en cliquant sur le bouton \"IA\"\n\n" + 
						 "Pour en savoir plus, vous pouvez vous rendre sur www.ffothello.org";
	
	/** Bouton de fermeture de l'aide */
	@FXML
	private Button CloseHelp;
	
	/**
	 * Constructeur de l'objet
	 */
	public AideController() {
		
	}
	
	public void initialize () {
		Aide.setText(aideOthello);
	}
	
	/**
	 * Retourne au menu principal et ferme la fen�tre courante
	 */
	@FXML
	private void handleCloseAide() {
		Stage stage = (Stage) CloseHelp.getScene().getWindow(); //fermeture de la fen�tre ou le bouton est situ�e
		stage.close();
	}

}
