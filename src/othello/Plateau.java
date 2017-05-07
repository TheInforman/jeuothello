/*
 * Plateau.java                                            06/05/2017
 * Groupe  Adrien Bouyssou, Vincent Galinier, Kerian Georges, Arthur Pradier, Mickaël Queudet
 */

package othello;

import java.util.Arrays;

/**
 * Un plateau comprenant des cases autour du quel des joueurs disputent
 * une partie.
 * 
 * @author Vincent G.
 */
public class Plateau {
	
	/** La largeur du plateau */
	public static final int LARGEUR = 8;
	
	/** La hauteur du plateau */ 
	public static final int HAUTEUR = 8;
	
	/** L'ensemble des cases constituant le plateau */
	public Case[][] tablier = new Case[HAUTEUR][LARGEUR];
	// -- Note : Java "row major" -> ligne en premier
	
	/** (constructeur d'état d'instance)
	 *  Plateau définit par son contenu.
	 * 	Crée un nouvel objet plateau et l'initialise avec deux pions de chaque
	 *  couleur au centre.
	 *  TODO : plus d'explications ?
	 */
	public Plateau() {
		for (int colonne = 0; colonne < LARGEUR; colonne++) {
			for (int ligne = 0; ligne < HAUTEUR; ligne ++) {
				tablier[ligne][colonne] = new Case(ligne, colonne);
			}
		}
		
		tablier[3][3].setCouleur(Case.COULEUR_BLANC);
		tablier[4][4].setCouleur(Case.COULEUR_BLANC);
		tablier[4][3].setCouleur(Case.COULEUR_NOIR);
		tablier[3][4].setCouleur(Case.COULEUR_NOIR);
	}
	
	//TODO : créer un constructeur dans le cas où l'on charge une sauvegarde
	
	//TODO : retrouver pourquoi on retourne une Case déjà.
	/**
	 * Applique l'action d'un joueur en retournan les pions de l'adversaire.
	 * 
	 * @param caseConcernee		la case sur laquelle un applique le coups
	 * @return		TODO compléter et trouver // c'est pour le archiver tour
	 */
	public Case appliquerCoups(Case caseConcernee) {
		//TODO : écrire le corps de la méthode
		return null;
	}
	
	/**
	 * Détermine l'ensemble des coups possibles d'un joueur pour un
	 * tour donné.
	 * 
	 * @param couleur	la couleur du joueur dont on veut déterminer
	 * 					les coups possibles
	 * @return		une liste de cases où le joueur peut poser ses pions
	 */
	public Case[] determinerCoupsPossibles(int couleur) {
		//TODO : écrire le corps de la méthode
		
		/* On détermine quels pions posés sur
		 * le plateau appartiennent au joueur
		 */
		Case[] pionsDuJoueur = toutLesPions(couleur);
		
		/* Pour chaque pions on détermine s'il peut être couplé avec au moins
		 * une case vide sur laquelle le joueur pourra poser son pion.
		 */
		for (int i = 0; i < pionsDuJoueur.length; i++) {
			// TODO tableau ?
			//haut 	   : li - 1		col
			//droit	   : li			col + 1
			//bas	   : li + 1		col
			//gauche   : li			col -1
			
			//diag h-d : li - 1		col + 1
			//diag b-d : li + 1		col + 1
			//diag b-g : li + 1		col - 1
			//diag h-g : li - 1		col - 1
		}
		
		return null;
	}
	
	/**
	 * Renvoit un tableau contenant l'ensemble des cases sur lesquelles
	 * sont posés des pions d'une couleur donnée.
	 *
	 * @param couleur	la couleur des pions que l'on recherche
	 * @return		l'ensemble des cases de la couleur passée en argument
	 */
	private Case[] toutLesPions(int couleur){

		int indice = calculerNbPions(couleur);
		Case[] pionsDuJoueur = new Case[indice];
		
		indice = 0;

		for (int colonne = 0; colonne < LARGEUR; colonne++) {
			for (int ligne = 0; ligne < HAUTEUR; ligne ++) {
				if (tablier[colonne][ligne].getCouleur() == couleur) {
					pionsDuJoueur[indice] = tablier[colonne][ligne];
					indice ++;
				}
			}
		}
		
		return pionsDuJoueur;
	}
	
	/**
	 * Calcule le score d'un joueur
	 * @param couleur	la couleur du joueur dont on veut calculer le score
	 * @return		le score du joueur
	 */
	public int calculerNbPions(int couleur) {
		int nbPions = 0;
		for (int colonne = 0; colonne < LARGEUR; colonne++) {
			for (int ligne = 0; ligne < HAUTEUR; ligne ++) {
				if (tablier[colonne][ligne].getCouleur() == couleur) {
					nbPions ++;
				}
			}
		}
		return nbPions;
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {                                                                                                                 
		String texte = "";
		for (int colonne = 0; colonne < LARGEUR; colonne++) {
			texte += "|";
			for (int ligne = 0; ligne < HAUTEUR; ligne ++) {
				texte += tablier[ligne][colonne].getCaractere() + " ";
			}
			texte += "|\n";
		}
		return texte;
	}
	
	
}
