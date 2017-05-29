/*
 * Joueur.java                                            06/05/2017
 * Groupe  Adrien Bouyssou, Vincent Galinier,
 * 		   Kerian Georges, Arthur Pradier, Mickaël Queudet
 */

package othello;

import java.io.Serializable;

import java.util.Arrays;

/**
 * Joueur du jeu de l'othello
 * @author Vincent G.
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
	
	/** Récupère la couleur de this */
	public int getCouleur() {
		return couleur;
	}
	
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
	 * Permet de passer son tour à un joueur par choix
	 */
	public void passerTour() {
		//TODO : Programmer la méthode
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Joueur [nom=" + nom + ", score=" + score + ", couleur=" + couleur + ", peutAgir=" + peutAgir + "]";
	}

	/**
	 * @return 		le nom
	 */
	public String getNom() {
		return nom;
	}
	
	
}
