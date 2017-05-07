/*
 * Partie.java                                            06/05/2017
 * Groupe  Adrien Bouyssou, Vincent Galinier, Kerian Georges, Arthur Pradier, Mickaël Queudet
 */

package othello;

import java.util.Arrays;

/**
 * Partie conçernant un plateau de cases et des joueurs
 * @author Vincent G. , Kerian G.
 */
public class Partie {

	/** Historique des cases dont l'état a changé au cours de la partie.
	 *  Le numéro de la ligne correspond au numéro du tour auquel l'action a été jouée.
	 *  La première colonne de chaque ligne correspond à la case sur
	 * 		laquelle le joueur a décidé de placer son pion.
	 * 		TODO : comment savoir la couleur du premier pion
	 *  Les cases suivantes sont des cases qui ont changées d'état,
	 *  passant du noir au blanc ou du blanc au noir.
	 */
	private Case[][] listeCoups;
	
	/** Joueur devant jouer le tour courant */
	private int doitJouer;
	
	/** Numéro du tour courant */
	private int tour;
	
	/** Booléen valant false si la partie est bloquée de manière irrémédiable */
	private boolean partieBloquee;
	
	/** Liste des joueurs disputant la partie */
	private Joueur[] listeJoueur;
	
	
	//TODO : Javadoc
	/** (constructeur d'état d'instance)
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
	
	//TODO : constructeur dans le cas où on charge une sauvegarde
	
	/** TODO : Javadoc */
	public void tourSuivant() {
		//TODO : Programmer la méthode
		tour++;
	}
	
	/** TODO : Javadoc */
	public void tourPrecedent() {
		//TODO : Programmer la méthode
		tour--;
	}
	
	/** TODO : Javadoc */
	private void actualiserListeCoups() {
		//TODO : Programmer la méthode
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
		//TODO : Programmer la méthode
		return null;
	}
	
	
}
