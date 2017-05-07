/*
 * Joueur.java                                            06/05/2017
 * Groupe  Adrien Bouyssou, Vincent Galinier, Kerian Georges, Arthur Pradier, Mickaël Queudet
 */

package othello;

import java.util.Arrays;

/**
 * Joueur du jeu de l'othello
 * @author Vincent G.
 */
public class Joueur {
	
	/** Nom du joueur */
	private String nom;
	
	/** Score du joueur */
	private int score;
	
	/** Couleur du joueur */
	private int couleur;
	
	/** Boolen égal à vrai si le joueur a pu agir au tour précédent, faux sinon */
	private boolean peutAgir;
	
	/** Ensembles des coups possibles du joueur pour un tour donné */
	private Case[] coupsPossibles;
	
	/** (constructeur d'état d'instance)
	 * Joueur définit par son nom et sa couleur
	 */
	public Joueur() {
		this.nom = "Ordinateur";
		this.couleur = Case.COULEUR_NOIR;
		score = 0;
		peutAgir = true;
	}
	
	/** (constructeur d'état d'instance)
	 * Joueur définit par son nom et sa couleur
	 * @param nom
	 * @param couleur
	 */
	public Joueur(String nom, int couleur) {
		this.nom = nom;
		this.couleur = couleur;
		score = 0;
		peutAgir = true;
	}
	
	//TODO : constructeur dans le cas ou on charge une sauvegarde
	
	/**
	 * Le joueur pose un pion sur le plateau
	 * @param colonne	la colonne ou l'on poser le pion
	 * @param ligne		la ligne où l'on pose le pion
	 */
	public void poserPion(int colonne, int ligne) {
		//TODO : Programmer la méthode
	}

	public void paserTour() {
		//TODO : Programmer la méthode	
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Joueur [nom=" + nom + ", score=" + score + ", couleur=" + couleur + ", peutAgir=" + peutAgir
				+ ", \ncoupsPossibles=" + Arrays.toString(coupsPossibles) + "]";
	}
}
