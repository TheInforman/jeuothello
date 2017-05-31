/*
 * Partie.java                                            06/05/2017
 * Groupe  Adrien Bouyssou, Vincent Galinier,
 * 		   Kerian Georges, Arthur Pradier, Micka�l Queudet
 */

package othello;

import java.util.Arrays;

import java.io.Serializable;

/**
 * Partie con�ernant un plateau de cases et des joueurs
 * @author Vincent G. , Kerian G.
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
		
		plateauDeJeu = new Plateau();
		
		tour = 0;
		doitJouer = 0;
	}
	
	
	
	
	//TODO : constructeur dans le cas o� on charge une sauvegarde
	
	
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
		//TODO : Programmer la m�thode
		doitJouer = (doitJouer + 1) % 2 ;
		plateauDeJeu.determinerCoupsPossibles(listeJoueur[doitJouer].getCouleur());
		tour++;
	}
	
	/** TODO : Javadoc */
	public void tourPrecedent() {
		//TODO : Programmer la m�thode
		tour--;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Partie [listeCoups=" + Arrays.toString(historiqueCoups) + ", doitJouer=" + doitJouer + ", tour=" + tour
				+ ", \npartieBloquee=" + partieBloquee + ", \nJoueur1=" + Arrays.toString(listeJoueur) + "]";
		
	}
	
}
