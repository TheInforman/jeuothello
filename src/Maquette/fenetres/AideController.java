/* AideController.java		08/05/2017
 * info1 groupe Othello 
 */
package Maquette.fenetres;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * controlleur de la fenêtre d'aide
 * @author Arthur Pradier Mickaël Queudet
 */
public class AideController {

	@FXML
	private Text Aide; 
	
	String aideOthello = "Bienvenue dans l'aide de ce jeu d'Othello. Pour commencer à jouer, choisissez simplement ce que vous voulez faire. \n\nSi vous voulez jouer contre l'ordinateur,"
						+" cliquez sur \"jouer contre l'IA\", choisissez votre niveau de difficulté puis saisissez votre pseudo. \n\nSi vous préférez jouer avec un ami, cliquez sur " +
						 "\"Jouer avec un ami\". Chaque joueur devra entrer un pseudo. Une fois vos choix effectués, appuyez sur Valider. \n\nSi vous désirez reprendre une partie " +
						 "précédemment sauvegardée, cliquez sur \"charger une partie\" et sélectionnez la partie correspondante. Vous pouvez retrouver facilement votre fichier " +
						 "de sauvegarde: il est identifié par la date et l'heure ou vous avez sauvegardé.\n\n Une fois la partie lancée, l'objectif est de posséder le plus de pions" +
						 "possible à la fin de la partie. Pour poser un jeton, il vous suffit de cliquer sur la case où vous voulez le positionner. Le pion doit être placé de sorte" +
						 " à encercler un ou plusieurs pions adverses, et ce horizontalement, verticalement ou diagonalement. Lorsque vous placez un pion, tous les pions adverses " +
						 "ainsi encerclés seront changés en votre couleur. La partie se termine lorsque le plateau est plein ou si aucun des deux joueurs ne peut jouer son tour.\n\n" +
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
	 * Retourne au menu principal et ferme la fenêtre courante
	 */
	@FXML
	private void handleCloseAide() {
		Stage stage = (Stage) CloseHelp.getScene().getWindow(); //fermeture de la fenêtre ou le bouton est située
		stage.close();
	}

}
