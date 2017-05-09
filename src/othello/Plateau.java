/*
 * Plateau.java                                            06/05/2017
 * Groupe  Adrien Bouyssou, Vincent Galinier,
 * 		   Kerian Georges, Arthur Pradier, Mickaël Queudet
 */

package othello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Un plateau comprenant des cases autour du quel des joueurs disputent
 * une partie.
 * 
 * @author Vincent G.
 */
public class Plateau {
	
	
	/** La largeur du plateau */
	public static final int LARGEUR = 8;
	
	/** La hauteur du plateau */ 
	public static final int HAUTEUR = 8;
	
	/** True si le pion a été poser, false sinon */
	private boolean actionEffectuer = false;
	
	/** L'ensemble des cases constituant le plateau */
	public Case[][] othellier = new Case[HAUTEUR][LARGEUR];
	// -- Note : Java "row major" -> ligne en premier TODO : delete ligne
	
	/** Ensembles des coups possibles pour le joueur jouant pendant le tour actuel */
	private Case[] coupsPossibles = new Case[64];
	
	private static int[][] TABL_DEPLACEMENT = {{-1,0},{-1,1},{0,1},{1,1},
			{1,0},{1,-1},{0,-1},{-1,-1}};
	
	/** (constructeur d'état d'instance)
	 *  Plateau définit par son contenu.
	 * 	Crée un nouvel objet plateau et l'initialise avec deux pions de chaque
	 *  couleur au centre.
	 *  TODO : plus d'explications ?
	 */
	public Plateau() {
		for (int colonne = 0; colonne < LARGEUR; colonne++) {
			for (int ligne = 0; ligne < HAUTEUR; ligne ++) {
				othellier[ligne][colonne] = new Case(ligne, colonne);
			}
		}
		
		othellier[3][3].setCouleur(Case.COULEUR_BLANC);
		othellier[4][4].setCouleur(Case.COULEUR_BLANC);
		othellier[4][3].setCouleur(Case.COULEUR_NOIR);
		othellier[3][4].setCouleur(Case.COULEUR_NOIR);
	}
	
	//TODO : créer un constructeur dans le cas où l'on charge une sauvegarde
	
	/**
	 * Applique l'action d'un joueur en retournant les pions de l'adversaire.
	 * TODO : savoir si on pose le premier pion dans cette méthode ou pas
	 * 
	 * @param caseConcernee		la case sur laquelle un applique le coups,
	 * 							il aura fallu au préalable vérifier qu'un coup
	 * 							est applicable sur cette case
	 * @param couleur	la couleur du joueur portant le coups
	 * 
	 * @return			Un tableau de cases, la remière case étant celle où le
	 * 					joueur a placé son pion, les suivantes sont les cases
	 *					correspondant aux pions qui ont changées de couleur
	 *					suite à l'action du joueur
	 *  				
	 *  				Note : Ce paramètre de retour sert dans le cas où l'on
	 *  					   archive les coups joués
	 */
	public Case[] appliquerCoups(Case caseConcernee, int couleur) {
		//TODO : écrire le corps de la méthode
		actionEffectuer = false ;
		Case[] aRetourner = determinerPionsARetourner(caseConcernee, couleur);
		
		if(presentCoupPossibles(caseConcernee)){
		/* On pose un pion sur la caseConcernee */
		caseConcernee.setCouleur(couleur); 
		
		for (int i = 0; i < aRetourner.length && aRetourner[i] != null ; i++) {
			aRetourner[i].setCouleur(couleur);
		}
		
		Case[] tableauRetour = new Case[aRetourner.length + 1];
		tableauRetour[0] = caseConcernee;
		for (int i = 1; i < tableauRetour.length && aRetourner[i-1] != null;
				i++) {
			tableauRetour[i] = aRetourner[i-1];
		}
		actionEffectuer = true ;
		}
		
		return null;
	}
	
	
	
	public boolean isActionEffectuer() {
		return actionEffectuer;
	}

	private boolean presentCoupPossibles(Case caseConcernee) {
		boolean present = false;
		int i = 0;
		while( i < coupsPossibles.length || present == true ){
			if(coupsPossibles[i] == null ){
				
			}else if(caseConcernee.getColonne() == coupsPossibles[i].getColonne() 
					&& caseConcernee.getLigne() == coupsPossibles[i].getLigne() )	{
				present = true;
			}
			i++;
		}
		return present;
	}

	/***
	 *  TODO : jdoc
	 * @param caseConcernee
	 * @param couleur
	 * @return
	 */
	private Case[] determinerPionsARetourner(Case caseCentrale, int couleur) {
		
		Case[] listePionsARetourner = new Case[21]; //TODO Valeur calculée
		//21 étant le nombre maximal de pions qu'il est possible de retourner
		//en un tour
		
		int deplacementLigne,
		deplacementColonne;
		
		boolean arretRechercheDirection;
		
		int indice;
		
		/* TODO : clarifier 
		 * Tableau dans lequel on va stocker l'ensemble des cases directement
		 * sur la ligne, la colonne et les diagonales de la case.
		 */
		Case[][] tableauVueDirectionnel = new Case[8][8];

		/* Pour chaque direction du tableau */
		for (int direction = 0; direction < TABL_DEPLACEMENT.length;
				direction++ ) {
			
			arretRechercheDirection = false;
			indice = 0;

			deplacementLigne = TABL_DEPLACEMENT[direction][0];
			deplacementColonne = TABL_DEPLACEMENT[direction][1];
			
			/* On "avance" d'un pas dans la direction donnée (largeur) */
			for (int ligne = caseCentrale.getLigne() + deplacementLigne,
					colonne = caseCentrale.getColonne() + deplacementColonne;
					
					(0 <= colonne && colonne < LARGEUR)
					&& (0 <= ligne && ligne < HAUTEUR)
					&& !arretRechercheDirection;
					
					colonne += deplacementColonne, ligne += deplacementLigne) {
				
				if (othellier[ligne][colonne].getCouleur()
						== Case.COULEUR_NEUTRE
						|| othellier[ligne][colonne].getCouleur()
						== couleur) {
					arretRechercheDirection = true;
				}
				
				tableauVueDirectionnel[direction][indice]
						= othellier[ligne][colonne];
				indice ++;
				
			}
		}
		
		for (int i = 0; i < tableauVueDirectionnel.length; i++) {
			for (int j = 0; j < tableauVueDirectionnel[i].length
					&& tableauVueDirectionnel[i][j] != null; j++) {
			}
			
		}
		
		indice = 0;
		
		for (int i = 0; i < tableauVueDirectionnel.length; i++) {
			if(derniereCase(tableauVueDirectionnel[i]) != null && derniereCase(tableauVueDirectionnel[i]).getCouleur()
					== couleur) {
				for (int j = 0; j < tableauVueDirectionnel[i].length
						&& tableauVueDirectionnel[i][j] != null; j++) {
					listePionsARetourner[indice] = tableauVueDirectionnel[i][j];
					indice ++;
				}
			}
		}
		
		return listePionsARetourner; //STUB 
	}
	
	/**
	 * Retourne le dernier element non null d'un tableau de case comportant au
	 * moins une valeur non nulle.
	 * 
	 * @param ligneDeCases	le tableau où l'on souhaite déterminer le dernier 
	 * 						élément non nul
	 * @return		la dernière case non nulle du tableau passé en paramère
	 */
	private Case derniereCase(Case[] ligneDeCases) {
		Case derniereCaseValide = null;
		
		for (int i = 0; i < ligneDeCases.length && ligneDeCases[i] != null;
				i++) {
			derniereCaseValide = ligneDeCases[i];
		}
		return derniereCaseValide;
	}

	/**
	 * Détermine l'ensemble des coups possibles d'un joueur pour un
	 * tour donné.
	 * 
	 * @param couleur	la couleur du joueur dont on veut déterminer
	 * 					les coups possibles
	 * @return		une liste de cases où le joueur peut poser ses pions
	 */
	public void determinerCoupsPossibles(int couleur) {
		
		/* On détermine l'ensemble des cases vides */
		Case[] casesVides = casesVides();
		
		//TODO : voir s'il n'y a pas un autre moyen de faire l'init ?
		/* Tableau des coups possibles */
		//Case[] coupsPossibles = new Case[casesVides.length];
		
		int indice = 0;
		
		/* Pour chaque cases on détermine si elle peut être couplé avec au moins
		 * une case sur laquelle un joueur a posé son pion.
		 */
		for (int i = 0; i < casesVides.length && casesVides[i] !=null ; i++) {
			
			if (aUnePaire(casesVides[i], couleur)) {
				coupsPossibles[indice] = casesVides[i];
				indice ++;
			}
		}
		//return coupsPossibles;
	}
	
	/**
	 * Détermine si la case spécifiée en paramètre a au moins une paire
	 * @param aCoupler
	 * @return
	 */
	private boolean aUnePaire(Case aCoupler, int couleur) {

		int couleurInverse = couleur == Case.COULEUR_BLANC ?
				Case.COULEUR_NOIR : Case.COULEUR_BLANC;

		boolean aUnePaire = false;
		boolean arretRechercheDirection;  /* booléen passant à vrai lorsqu'on
		 * sait si la case n'a pas de paire
		 * dans une direction donnée
		 */
		
		int deplacementLigne,
			deplacementColonne;
		
		/* Pour chaque direction du tableau */
		for (int direction = 0; direction < TABL_DEPLACEMENT.length
				&& !aUnePaire; direction++ ) {
			arretRechercheDirection = false;
			
			deplacementLigne = TABL_DEPLACEMENT[direction][0];
			deplacementColonne = TABL_DEPLACEMENT[direction][1];
			
			/* On "avance" d'un pas dans la direction donnée (largeur) */
			for (int ligne = aCoupler.getLigne() + deplacementLigne,
					colonne = aCoupler.getColonne() + deplacementColonne;
					
					(0 <= colonne && colonne < LARGEUR)
					&& (0 <= ligne && ligne < HAUTEUR)

					&& !arretRechercheDirection;

					colonne += deplacementColonne, ligne += deplacementLigne) {				
				
				/* Si case vide : pas de paire dans cette direction */
				if (othellier[ligne][colonne].getCouleur()
						== Case.COULEUR_NEUTRE ) {
					arretRechercheDirection = true;
				}

				/* Si la case courante est de la couleur 'couleur' et que
				 *    la case précédente est de la couleur inverse.
				 */
				if (othellier[ligne][colonne].getCouleur() == couleur ) {
					if (othellier[ligne - deplacementLigne]
							[colonne - deplacementColonne].getCouleur()
							== couleurInverse) {
						arretRechercheDirection = true;
						aUnePaire = true;
					} else {
						arretRechercheDirection = true;
					}
				}
			}
		}
		return aUnePaire;
	}

	/**
	 * Renvoit un tableau contenant l'ensemble des cases vides du plateau
	 * Le joueur pose un pion sur le plateau.
	 * 
	 * @param colonne	la colonne ou l'on poser le pion
	 * @param ligne		la ligne où l'on pose le pion
	 */
	/*public void poserPion(int colonne, int ligne, int couleur) {
		//othellier[colonne][ligne].setCouleur(couleur);
		appliquerCoups(othellier[colonne][ligne], couleur);
	}*/

	
	/**
	 * Renvoi un tableau contenant l'ensemble des cases sur lesquelles
	 * sont posés des pions d'une couleur donnée.
	 *
	 * @param couleur	la couleur des pions que l'on recherche
	 * @return		l'ensemble des cases de la couleur passée en argument
	 */
	private Case[] casesVides(){

		//TODO : voir s'il n'y a pas un autre moyen de faire l'init ?
		Case[] casesVides = new Case[64];
		
		int indice = 0;

		for (int colonne = 0; colonne < LARGEUR; colonne++) {
			for (int ligne = 0; ligne < HAUTEUR; ligne ++) {
				if (othellier[ligne][colonne].getCouleur() == -1) {
					casesVides[indice] = othellier[ligne][colonne];
					indice ++;
				}
			}
		}
		
		return casesVides;
	}
	
	/**
	 * Calcule le score d'un joueur
	 * @param couleur	la couleur du joueur dont on veut calculer le score
	 * @return		le score du joueur
	 */
	public int calculerNbPions(int couleur) {
		int nbPions = 0;
		for (int colonne = 0; colonne < LARGEUR; colonne++) {
			for (int ligne = 0; ligne < HAUTEUR; ligne ++) {
				if (othellier[ligne][colonne].getCouleur() == couleur) {
					nbPions ++;
				}
			}
		}
		return nbPions;
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {                                                                                                                 
		String texte = "   0 1 2 3 4 5 6 7 \n";
		for (int ligne = 0; ligne < HAUTEUR; ligne ++) {
			texte += ligne + " |";
			for (int colonne = 0; colonne < LARGEUR; colonne++) {
				texte += othellier[ligne][colonne].getCaractere() + " ";
			}
			texte += "|\n";
		}
		texte += "\n\n" + "coupsPossibles=" + Arrays.toString(coupsPossibles);
		return texte;
	}
	
	
}
