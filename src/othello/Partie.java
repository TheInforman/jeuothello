/*
 * Partie.java                                            06/05/2017
 * Groupe  Adrien Bouyssou, Vincent Galinier, Kerian Georges, Arthur Pradier, Micka�l Queudet
 */

package othello;

import java.util.Arrays;

/**
 * Partie con�ernant un plateau de cases et des joueurs
 * @author Vincent G. , Kerian G.
 */
public class Partie {

	/** Historique des cases dont l'�tat a chang� au cours de la partie.
	 *  Le num�ro de la ligne correspond au num�ro du tour auquel l'action a �t� jou�e.
	 *  La premi�re colonne de chaque ligne correspond � la case sur
	 * 		laquelle le joueur a d�cid� de placer son pion.
	 * 		TODO : comment savoir la couleur du premier pion
	 *  Les cases suivantes sont des cases qui ont chang�es d'�tat,
	 *  passant du noir au blanc ou du blanc au noir.
	 */
	private Case[][] listeCoups;
	
	/** Joueur devant jouer le tour courant */
	private int doitJouer;
	
	/** Num�ro du tour courant */
	private int tour;
	
	/** Bool�en valant false si la partie est bloqu�e de mani�re irr�m�diable */
	private boolean partieBloquee;
	
	/** Liste des joueurs disputant la partie */
	private Joueur[] listeJoueur;
	
	
	//TODO : Javadoc
	/** (constructeur d'�tat d'instance)
	 * 
	 * @param premierJoueur
	 * @param secondJoueur
	 */
	public Partie(Joueur premierJoueur, Joueur secondJoueur) {
		listeJoueur[0] = premierJoueur;
		listeJoueur[1] = secondJoueur;
		
		partieBloquee = false;
		
		tour = 0;
		doitJouer = 0;
	}
	
	//TODO : constructeur dans le cas o� on charge une sauvegarde
	
	/** TODO : Javadoc */
	public void tourSuivant() {
		//TODO : Programmer la m�thode
		tour++;
	}
	
	/** TODO : Javadoc */
	public void tourPrecedent() {
		//TODO : Programmer la m�thode
		tour--;
	}
	
	/** TODO : Javadoc */
	private void actualiserListeCoups() {
		//TODO : Programmer la m�thode
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Partie [listeCoups=" + Arrays.toString(listeCoups) + ", doitJouer=" + doitJouer + ", tour=" + tour
				+ ", partieBloquee=" + partieBloquee + ", listeJoueur=" + Arrays.toString(listeJoueur) + "]";
	}

	/** TODO : Javadoc */
	public Case determinerCoupsPossibles(int couleurDuJoueur) {
		//TODO : Programmer la m�thode
		return null;
	}
	
	
}
