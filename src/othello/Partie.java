/*
 * Partie.java                                            06/05/2017
 * Tous droits r�serv�s � l'IUT de Rodez
 */

package othello;

import java.util.Arrays;

import java.io.Serializable;

/**
 * Partie con�ernant un plateau de cases et des joueurs
 * 
 * @author Vincent Galinier
 * @author Adrien Bouyssou
 * @author Kerian Georges
 * @author Arthur Pradier
 * @author Micka�l Queudet 
 */
public class Partie implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Historique des cases dont l'�tat a chang� au cours de la partie.
	 *  Le num�ro de la ligne correspond au num�ro du tour auquel l'action a �t� jou�e.
	 *  La premi�re colonne de chaque ligne correspond � la case sur
	 * 		laquelle le joueur a d�cid� de placer son pion.
	 *  Les cases suivantes sont des cases qui ont chang�es d'�tat,
	 *  passant du noir au blanc ou du blanc au noir.
	 */
	private Case[][] historiqueCoups;

	/** Partie courante */
	private Plateau plateauDeJeu;

	/** Joueur devant jouer le tour courant */
	private int doitJouer;

	/** Num�ro du tour courant */
	private int tour;

	/** Bool�en valant false si la partie est bloqu�e de mani�re irr�m�diable */
	private boolean partieBloquee;

	/** Liste des joueurs disputant la partie */
	private Joueur[] listeJoueur = new Joueur[2];
	
	/**
	 * Entier d�crivant le type de partie jou�.
	 * 0 Correspond � une partie joueur contre joueur
	 * 1 Correspond � une partie contre Ordinateur en mode Facile
	 * 2 Correspond � une partie contre Ordinateur en mode Normal
	 */
	private static int typeDePartie;



	//TODO : Javadoc
	/** (constructeur d'�tat d'instance)
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
	 * @return le num�ro du joueur qui doit jouer
	 */
	public int getDoitJouer() {
		return doitJouer;
	}

	/**
	 * @return le num�ro du tour
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
	 * Revient au tour pr�c�dent en changeant le joueur qui joue
	 * dans une partie joueur contre joueur et d�termine les coups possibles
	 */
	public void tourPrecedent() {
		doitJouer = (doitJouer + 1) % 2 ;
		plateauDeJeu.determinerCoupsPossibles(listeJoueur[doitJouer].getCouleur());
		tour--;
	}


	/** 
	 * Revient au tour pr�c�dent du joueur (2 tour en arri�re) dans un partie 
	 * contre un ordinateur et d�termine les coups possibles 
	 */
	public void tourPrecedentVSOrdi() {
		plateauDeJeu.determinerCoupsPossibles(listeJoueur[doitJouer].getCouleur());
		tour -= 2 ;
	}

	/** TODO : Javadoc */
	@SuppressWarnings("unused")
	private void actualiserHisto() {
		//TODO : Programmer la m�thode
	}

	/** TODO : Javadoc */
	public void archiverTour( Case[] plateauPrecedent ){
		//TODO : Programmer la m�thode
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
