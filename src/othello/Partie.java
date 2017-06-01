/*
 * Partie.java                                            06/05/2017
 * Tous droits réservés à l'IUT de Rodez
 */

package othello;

import java.util.ArrayList;
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

	/** 
	 *  Historique des cases dont l'état a changé au cours de la partie.
	 *  Le numéro de la ligne correspond au numéro du tour auquel l'action
	 *  a été jouée.
	 *  La première colonne de chaque ligne correspond à la case sur
	 * 	laquelle le joueur a décidé de placer son pion.
	 *  Les cases suivantes sont des cases qui ont changées d'état,
	 *  passant du noir au blanc ou du blanc au noir.
	 */
	private ArrayList<ArrayList<Case>> historiqueCoups;

	/** Partie courante */
	private Plateau plateauDeJeu;

	/** Joueur devant jouer le tour courant */
	private int doitJouer;

	/** Numéro du tour courant */
	private int tour;

	/** Liste des joueurs disputant la partie */
	private Joueur[] listeJoueur = new Joueur[2];
	
	/**
	 * Entier décrivant le type de partie joué.
	 * 0 Correspond à une partie joueur contre joueur
	 * 1 Correspond à une partie contre Ordinateur en mode Facile
	 * 2 Correspond à une partie contre Ordinateur en mode Normal
	 */
	private int typeDePartie;

	/** (constructeur d'état d'instance)
	 * Place les joueurs dans la liste de joueur de la partie, stocke le type
	 * de partie et initialise les paramètres par défaut d'une partie
	 * @param premierJoueur joueur ayant la couleur blanche
	 * @param secondJoueur	joueur ayant la couleur noire
	 * @param typeDePartie détermine si la partie est joueur
	 * 	 	  contre ordinateur (et la difficulté) ou joueur contre joueur	
	 */
	public Partie(Joueur premierJoueur,
			      Joueur secondJoueur,
			      int typeDePartie) {
		
		this.typeDePartie = typeDePartie;	
		listeJoueur[1] = premierJoueur;
		listeJoueur[0] = secondJoueur;
		doitJouer = 1;
		tour = 0;
		
		plateauDeJeu = new Plateau();
		
		historiqueCoups = new ArrayList<ArrayList<Case>>();
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
	 * @return l'historique des coups et leurs conséquences de la partie
	 */
	public ArrayList<ArrayList<Case>> getHistoriqueCoups() {
		return historiqueCoups;
	}


	/**
	 * @return  typeDePartie
	 */
	public int getTypeDePartie() {
		return typeDePartie;
	}


	/** 
	 * Passe au tour suivant de la partie en changeant le joueur 
	 * qui doit jouer et déterminant ses coups possibles
	 */
	public void tourSuivant() {
		doitJouer = (doitJouer + 1) % 2 ;
		plateauDeJeu.determinerCoupsPossibles(
				                          listeJoueur[doitJouer].getCouleur());
		tour++;
	}

	/** 
	 * Revient au tour précédent en changeant le joueur qui joue
	 * dans une partie joueur contre joueur et détermine les coups possibles
	 */
	public void tourPrecedent() {
		doitJouer = (doitJouer + 1) % 2 ;
		plateauDeJeu.determinerCoupsPossibles(
				                          listeJoueur[doitJouer].getCouleur());
		this.actualiserHistoriqueCoups();
		tour--;
		this.annulationCoupPrecedent();
		this.actualiserHistoriqueCoups();
		Plateau.coupsPossibles.clear();
		doitJouer = (doitJouer + 1) % 2;
		plateauDeJeu.determinerCoupsPossibles(
										listeJoueur[doitJouer].getCouleur());		
	}

	/**
	 * Annule le coup jouer au tour précédent
	 */
	private void annulationCoupPrecedent() {
		for( int i = 0 ; i < historiqueCoups.get(tour).size(); i++){
			if(i == 0){
				historiqueCoups.get(tour).get(i).setCouleur(-1); ;	
			}else{
				historiqueCoups.get(tour)
							   .get(i)
							   .setCouleur(listeJoueur[doitJouer]
									   	   						.getCouleur());
			}
		}
	}


	/** 
	 * Supprime le dernier tour sauvegarder dans l'historique des coups
	 * suite au retour en arrière d'un coup du joueur
	 */
	private void actualiserHistoriqueCoups() {
		historiqueCoups.remove(tour);
	}

	/** 
	 * Ajoute la liste des pions retourner au tour précédent
	 * au tableau de l'historique des tours
	 */
	public void archiverTour( ArrayList<Case> aArchiver ){
		if(aArchiver != null){
			historiqueCoups.add(tour,new ArrayList<Case>(aArchiver));
		}
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String textePartie = "" ;
		textePartie += "Partie [listeCoups=" ;
		for(int i = 0 ; i < historiqueCoups.size() ; i++ ) {
			textePartie += "\nCase modifié au Tour n° " + (i+1) + "\n";
			textePartie +=  historiqueCoups.get(i);
			textePartie +=  ", doitJouer=" + doitJouer + ", tour=" + tour
					+ ", \nJoueur1=" + Arrays.toString(listeJoueur) + "]\n\n";
		}
		return textePartie ;
	}
}


