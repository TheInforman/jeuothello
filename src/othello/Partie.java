/*
 * Partie.java                                            06/05/2017
 * Groupe  Adrien Bouyssou, Vincent Galinier,
 * 		   Kerian Georges, Arthur Pradier, Mickaël Queudet
 */

package othello;

import java.util.Arrays;

import java.io.Serializable;

/**
 * Partie conçernant un plateau de cases et des joueurs
 * @author Vincent G. , Kerian G.
 */
public class Partie implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** Historique des cases dont l'état a changé au cours de la partie.
	 *  Le numéro de la ligne correspond au numéro du tour auquel l'action a été jouée.
	 *  La première colonne de chaque ligne correspond à la case sur
	 * 		laquelle le joueur a décidé de placer son pion.
	 *  Les cases suivantes sont des cases qui ont changées d'état,
	 *  passant du noir au blanc ou du blanc au noir.
	 */
	private Case[][] historiqueCoups;
	
	/** Partie courante */
	private Plateau plateauDeJeu;
	
	/** Joueur devant jouer le tour courant */
	private int doitJouer;
	
	/** Numéro du tour courant */
	private int tour;
	
	/** Booléen valant false si la partie est bloquée de manière irrémédiable */
	private boolean partieBloquee;
	
	/** Liste des joueurs disputant la partie */
	private Joueur[] listeJoueur = new Joueur[2];
	


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
		
		plateauDeJeu = new Plateau();
		
		tour = 0;
		doitJouer = 0;
	}
	
	
	
	
	//TODO : constructeur dans le cas où on charge une sauvegarde
	
	
	/**
	 * @return le numéro du joueur qui doit jouer
	 */
	public int getDoitJouer() {
		return doitJouer;
	}

	/**
	 * @return le numéro du tour
	 */
	public int getTour() {
		return tour;
	}

	
	/**
	 * @return la liste des joueurs de la partie
	 */
	public Joueur[] getListeJoueur() {
		return listeJoueur;
	}


	
	
	/**
	 * @return le plateau de jeu de la partie
	 */
	public Plateau getPlateau() {
		return plateauDeJeu;
	}
	
	
	/** 
	 * Passe au tour suivant 
	 */
	public void tourSuivant() {
		//TODO : Programmer la méthode
		doitJouer = (doitJouer + 1) % 2 ;
		plateauDeJeu.determinerCoupsPossibles(listeJoueur[doitJouer].getCouleur());
		tour++;
	}
	
	/** TODO : Javadoc */
	public void tourPrecedent() {
		//TODO : Programmer la méthode
		tour--;
	}
	
	/** TODO : Javadoc */
	@SuppressWarnings("unused")
	private void actualiserHisto() {
		//TODO : Programmer la méthode
	}
	
	/** TODO : Javadoc */
	public void archiverTour( Case[] plateauPrecedent ){
		//TODO : Programmer la méthode
		// on utilisera le tableau retourner par appliquer coup
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Partie [listeCoups=" + Arrays.toString(historiqueCoups) + ", doitJouer=" + doitJouer + ", tour=" + tour
				+ ", \npartieBloquee=" + partieBloquee + ", \nJoueur1=" + Arrays.toString(listeJoueur) + "]";
		
	}
	
}
