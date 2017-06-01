/*
 * Joueur.java                                            06/05/2017
 * Tous droits réservés à l'IUT de Rodez
 */

package othello;

import java.io.Serializable;


/**
 * Joueur du jeu de l'othello
 * 
 * @author Vincent Galinier
 * @author Adrien Bouyssou
 * @author Kerian Georges
 * @author Arthur Pradier
 * @author Mickaël Queudet 
 */
public class Joueur implements Serializable {

	private static final long serialVersionUID = 1L;


	/** Nom du joueur */
	private String nom;

	/** Score du joueur */
	private int score;

	/** Couleur du joueur */
	private int couleur;

	/** Booléen égal à vrai si le joueur a pu agir au tour précédent,
	    faux sinon */
	private boolean peutAgir;

	/** (constructeur d'état d'instance)
	 * Joueur par défaut (ordinateur) auquel on attribue sa couleur
	 * @param couleur des pions du joueur
	 */
	public Joueur(int couleur) {
		this.nom = "Ordinateur";
		this.couleur = couleur;
		score = 0;
		peutAgir = true;
	}

	/** (constructeur d'état d'instance)
	 * Joueur définit par son nom et auquel on attribue sa couleur
	 * @param nom
	 * @param couleur
	 */
	public Joueur(String nom, int couleur) {
		this.nom = nom;
		this.couleur = couleur;
		score = 0;
		peutAgir = true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Joueur [nom=" + nom + ", score=" + score +
			   ", couleur=" + couleur + ", peutAgir=" + peutAgir + "]";
	}

	/**
	 * @return 		le nom
	 */
	public String getNom() {
		return nom;
	}

	/** Récupère la couleur de this */
	public int getCouleur() {
		return couleur;
	}

}
