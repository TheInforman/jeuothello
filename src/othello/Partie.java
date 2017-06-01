/*
 * Partie.java                                            06/05/2017
 * Tous droits réservés à l'IUT de Rodez
 */

package othello;

import java.util.Arrays;

import java.io.Serializable;

/**
 * Partie conçernant un plateau de cases et des joueurs
 * 
 * @author Vincent Galinier
 * @author Adrien Bouyssou
 * @author Kerian Georges
 * @author Arthur Pradier
 * @author Mickaël Queudet 
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
	
	/**
	 * Entier décrivant le type de partie joué.
	 * 0 Correspond à une partie joueur contre joueur
	 * 1 Correspond à une partie contre Ordinateur en mode Facile
	 * 2 Correspond à une partie contre Ordinateur en mode Normal
	 */
	private static int typeDePartie;



	//TODO : Javadoc
	/** (constructeur d'état d'instance)
	 * 
	 * @param premierJoueur
	 * @param secondJoueur
	 */
	public Partie(Joueur premierJoueur, Joueur secondJoueur, int typeDePartie) {
		listeJoueur[0] = premierJoueur;
		listeJoueur[1] = secondJoueur;

		this.typeDePartie = typeDePartie;	
		
		partieBloquee = false;

		plateauDeJeu = new Plateau();

		tour = 0;
		doitJouer = 0;
	}


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
		doitJouer = (doitJouer + 1) % 2 ;
		plateauDeJeu.determinerCoupsPossibles(listeJoueur[doitJouer].getCouleur());
		tour++;
	}

	/** 
	 * Revient au tour précédent en changeant le joueur qui joue
	 * dans une partie joueur contre joueur et détermine les coups possibles
	 */
	public void tourPrecedent() {
		doitJouer = (doitJouer + 1) % 2 ;
		plateauDeJeu.determinerCoupsPossibles(listeJoueur[doitJouer].getCouleur());
		tour--;
	}


	/** 
	 * Revient au tour précédent du joueur (2 tour en arrière) dans un partie 
	 * contre un ordinateur et détermine les coups possibles 
	 */
	public void tourPrecedentVSOrdi() {
		plateauDeJeu.determinerCoupsPossibles(listeJoueur[doitJouer].getCouleur());
		tour -= 2 ;
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

	/**
	 * @return  typeDePartie
	 */
	public static int getTypeDePartie() {
		return typeDePartie;
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
