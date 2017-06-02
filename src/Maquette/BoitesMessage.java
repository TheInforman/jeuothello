/**
 * BoitesMessage.java	31/05/2017
 */
package Maquette;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * @author Vincent
 *
 */
public class BoitesMessage {
	
	/**
	 * Affiche une msgBox d'erreur.
	 * 
	 * @param titre		le titre de la fenêtre
	 * @param Header	le header de la fenêtre
	 * @param Contenu	le contenu de la fenêtre
	 */
	public static void afficher_msgBoxErreur(String titre,
			String Header, String Contenu) {
		Alert fenetre = new Alert(AlertType.ERROR);
		fenetre.setTitle(titre);
		fenetre.setHeaderText(Header);
		fenetre.setContentText(Contenu);
		fenetre.showAndWait();
	}
	
	/**
	 * Affiche une msgBox d'information.
	 * 
	 * @param titre		le titre de la fenêtre
	 * @param Header	le header de la fenêtre
	 * @param Contenu	le contenu de la fenêtre
	 */
	public static void afficher_msgBoxInfo(String titre,
			String Header, String Contenu) {
		Alert fenetre = new Alert(AlertType.INFORMATION);
		fenetre.setTitle(titre);
		fenetre.setHeaderText(Header);
		fenetre.setContentText(Contenu);
		fenetre.showAndWait();
	}
	
	/**
	 * Affiche une msgBox de confirmation.
	 * 
	 * @param titre		le titre de la fenêtre
	 * @param Header	le header de la fenêtre
	 * @param Contenu	le contenu de la fenêtre
	 * @return Si l'utilisateur a appuyé sur OK ou non
	 */
	public static boolean afficher_msgBoxConfirmation(String titre,
			String Header, String Contenu) {
		Alert fenetre = new Alert(AlertType.CONFIRMATION);
		fenetre.setTitle(titre);
		fenetre.setHeaderText(Header);
		fenetre.setContentText(Contenu);

		Optional<ButtonType> resultat = fenetre.showAndWait();
		return resultat.get() == ButtonType.OK;
	}
}
