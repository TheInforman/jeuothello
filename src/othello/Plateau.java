/*
 * Plateau.java                                            06/05/2017
 * Groupe  Adrien Bouyssou, Vincent Galinier, Kerian Georges, Arthur Pradier, Micka�l Queudet
 */

package othello;

import java.util.Arrays;

/**
 * Un plateau comprenant des cases autour du quel des joueurs disputent une partie
 * @author Vincent G.
 */
public class Plateau {
	
	/** La largeur du plateau */
	public static final int LARGEUR = 8;
	
	/** La hauteur du plateau */ 
	public static final int HAUTEUR = 8;
	
	/** L'ensemble des cases constituant le plateau */
	public Case[][] tableauCase = new Case[HAUTEUR][LARGEUR];
	//TODO : le tableau est-il public ?   -- Note : Java "row major" -> ligne en premier
	
	/** (constructeur d'�tat d'instance)
	 *  Plateau d�finit par son contenu.
	 * 	Cr�e un nouvel objet plateau et l'initialise avec deux pions de chaque
	 *  couleur au centre.
	 *  TODO : plus d'explications ?
	 */
	public Plateau() {
		for (int colonne = 0; colonne < LARGEUR; colonne++) {
			for (int ligne = 0; ligne < HAUTEUR; ligne ++) {
				tableauCase[ligne][colonne] = new Case(ligne, colonne);
			}
		}
		
		tableauCase[3][3].setCouleur(Case.COULEUR_BLANC);
		tableauCase[4][4].setCouleur(Case.COULEUR_BLANC);
		tableauCase[4][3].setCouleur(Case.COULEUR_NOIR);
		tableauCase[3][4].setCouleur(Case.COULEUR_NOIR);
	}
	
	//TODO : cr�er un constructeur dans le cas ou l'on charge une sauvegarde
	
	//TODO : retrouver pourquoi on retourne une Case d�j�.
	/**
	 * Applique l'action d'un joueur en retournan les pions de l'adversaire.
	 * 
	 * @param caseConcernee		la case sur laquelle un applique le coups
	 * @return		TODO compl�ter et trouver
	 */
	public Case appliquerCoups(Case caseConcernee) {
		//TODO : �crire le corps de la m�thode
		return null;
	}
	
	public Case[] determinerCoupsPossibles() {
		//TODO : �crire le corps de la m�thode
		return null;
	}
	
	
	//TODO : modifier toString
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String texte = "";
		for (int colonne = 0; colonne < LARGEUR; colonne++) {
			texte += "|";
			for (int ligne = 0; ligne < HAUTEUR; ligne ++) {
				texte += tableauCase[ligne][colonne].getCaractere() + " ";
			}
			texte += "|\n";
		}
		return texte;
	}
	
	
}
