/*
 * Plateau.java                                            06/05/2017
 * Tous droits réservés à l'IUT de Rodez
 */

package othello;

import java.util.ArrayList;

import java.io.Serializable;


/**
 * Un plateau comprenant des cases autour du quel des joueurs disputent
 * une partie.
 * 
 * @author Vincent Galinier
 * @author Adrien Bouyssou
 * @author Kerian Georges
 * @author Arthur Pradier
 * @author Mickaël Queudet 
 */
public class Plateau implements Serializable {

	//TO DO: Expliquer à quoi ça sert
	private static final long serialVersionUID = 1L;


	/** La largeur du plateau */
	public static final int LARGEUR = 8;

	/** La hauteur du plateau */ 
	public static final int HAUTEUR = 8;

	/** True si le pion a été poser, false sinon */
	private boolean actionEffectuer = false;

	/** L'ensemble des cases constituant le plateau */
	public Case[][] othellier = new Case[HAUTEUR][LARGEUR];

	/** Ensembles des coups possibles pour le joueur jouant  le tour courant */
	public static ArrayList<Case> coupsPossibles = new ArrayList<Case>();

	/** 
	 * Tableau à deux dimensions représentant les déplacement 
	 * dans toutes les directions possibles sur un plateau de jeu,
	 * c'est à dire les lignes, colonnes,  diagonales
	 */
	private static int[][] TABL_DEPLACEMENT = {{-1,0},{-1,1},{0,1},{1,1},
			{1,0},{1,-1},{0,-1},{-1,-1}};



	/** (constructeur d'état d'instance)
	 * 	Crée un nouvel objet plateau de 8x8 et l'initialise
	 *  avec deux pions de chaque couleur en damier sur le
	 *  carré central du plateau.
	 *  Ceci est la configuration de départ de toutes parties d'othello
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


	/**
	 * Applique l'action d'un joueur en retournant les pions de l'adversaire.
	 * 
	 * @param caseConcernee		la case sur laquelle un applique le coups,
	 * 							il aura fallu au préalable vérifier qu'un coup
	 * 							est applicable sur cette case
	 * @param couleur	la couleur du joueur portant le coups
	 * 
	 * @return			Un tableau de cases, la première case étant celle où le
	 * 					joueur a placé son pion, les suivantes sont les cases
	 *					correspondant aux pions qui ont changées de couleur
	 *					suite à l'action du joueur
	 *  				
	 *  				Note : Ce paramètre de retour sert dans le cas où l'on
	 *  					   archive les coups joués
	 */
	public Case[] appliquerCoups(Case caseConcernee, int couleur) {
		// TO DO : commentaire détaillé
		actionEffectuer = false ;
		ArrayList<Case> aRetourner = determinerPionsARetourner(caseConcernee, couleur);

		if(presentCoupPossibles(caseConcernee)){
			/* On pose un pion sur la caseConcernee */
			caseConcernee.setCouleur(couleur); 

			for (int i = 0; i < aRetourner.size() && aRetourner.get(i) != null ;
					i++) {
				aRetourner.get(i).setCouleur(couleur);
			}

			Case[] tableauRetour = new Case[aRetourner.size() + 1];
			tableauRetour[0] = caseConcernee;
			for (int i = 1; i < tableauRetour.length && aRetourner.get(i-1) != null;
					i++) {
				tableauRetour[i] = aRetourner.get(i-1);
			}
			actionEffectuer = true ;
			coupsPossibles.clear();
		}else{
			System.out.println("Vous n'avez pas entré des coordonnées"
					+ "de case possible, veuillez réessayer.");
			//TODO : à enlever, pas de syso ici
		}
		return null;
	}


	/**
	 * retourne la valeur du boolean actionEffectuer
	 * @return actionEffectuer
	 */
	public boolean isActionEffectuer() {
		return actionEffectuer;
	}

	/**
	 * Change l'état du boolean à celui passé en paramètre
	 * @param actionEffectuer
	 */
	public void setActionEffectuer(boolean actionEffectuer) {
		this.actionEffectuer = actionEffectuer;
	}

	/**
	 * Vérifie si la case désignée par le joueur est une case jouable.
	 * 
	 * @param 	caseConcernee 	case désigner par le joueur
	 * @return 	true si elle appartient aux coups possibles, false sinon
	 */
	private boolean presentCoupPossibles(Case caseConcernee) {
		boolean present = false;

		for(int i = 0; i < coupsPossibles.size() ; i++ ){
			if( caseConcernee.equals(coupsPossibles.get(i))){
				present = true;
			}
		}
		return present;
	}

	/***
	 *  Méthode qui détermine les cases qui seront
	 *  à retournées suite au coup du joueur
	 *  
	 * @param caseCentrale à partir de laquelle on va faire les recherches
	 * 		  en lignes et diagonales pour déterminer les pions à retourner
	 * @param couleur du pion posé sur la caseCentrale par le joueur
	 * @return
	 */
	private ArrayList<Case> determinerPionsARetourner(Case caseCentrale, int couleur) {
		ArrayList<Case> PionsARetourner = new ArrayList<Case>();
		
		/** int de déplacement le long des lignes */ 
		int deplacementLigne;
		
		/** int de déplacement le long des colonnes */ 
		int deplacementColonne;
		
		/** boolean d'arret de recherche dans une direction */
		boolean arretRechercheDirection;
		
		/** initialisation de l'indice du tableau directionnel */
		int indice;

		/** 
		 * Tableau à deux dimensions de Cases dans lequel on stocke
		 * l'ensemble des pions présents dans les directions
		 * autour de la case centrale
		 */
		Case[][] tableauVueDirectionnel = new Case[8][8];

		/* Pour chaque direction du tableau de déplacement */
		for (int direction =0; direction<TABL_DEPLACEMENT.length; direction++){
			
			/* initialisation de chaque boucle de recherche */
			arretRechercheDirection = false;
			indice = 0;
			deplacementLigne = TABL_DEPLACEMENT[direction][0];
			deplacementColonne = TABL_DEPLACEMENT[direction][1];

			/* On se déplace d'une case dans la 
			   direction donnée en ne sortant pas du plateau */
			for (int ligne = caseCentrale.getLigne() + deplacementLigne,
				 colonne = caseCentrale.getColonne() + deplacementColonne;

				 (0 <= colonne && colonne < LARGEUR)
				 && (0 <= ligne && ligne < HAUTEUR)
				 && !arretRechercheDirection;

				 colonne += deplacementColonne, ligne += deplacementLigne) {
				
				/* Si la case est vide ou de la même couleur que le 
				   pion placé on arrête la recherche dans cette direction */
				if (othellier[ligne][colonne].getCouleur() == Case.COULEUR_NEUTRE
					|| othellier[ligne][colonne].getCouleur() == couleur) {
					
					arretRechercheDirection = true;
					
				}

				tableauVueDirectionnel[direction][indice] = othellier[ligne][colonne];
				indice ++;

			}
		}
		
		indice = 0;

		for (int i = 0; i < tableauVueDirectionnel.length; i++) {
			if(derniereCase(tableauVueDirectionnel[i]) != null && derniereCase(tableauVueDirectionnel[i]).getCouleur()
					== couleur) {
				for (int j = 0; j < tableauVueDirectionnel[i].length
						&& tableauVueDirectionnel[i][j] != null; j++) {
					PionsARetourner.add(tableauVueDirectionnel[i][j]) ;
					indice ++;
				}
			}
		}
		return PionsARetourner;
	}

	/**
	 * Retourne le dernier element non null d'un tableau de case comportant au
	 * moins une valeur non nulle.
	 * 
	 * @param ligneDeCases	le tableau où l'on souhaite déterminer le dernier 
	 * 						élément non nul
	 * @return		la dernière case non nulle du tableau passé en paramètre
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


		/* Pour chaque cases on détermine si elle peut être couplé avec au moins
		 * une case sur laquelle un joueur a posé son pion.
		 */
		for (int i = 0; i < casesVides.length && casesVides[i] !=null ; i++) {

			if (aUnePaire(casesVides[i], couleur)) {
				coupsPossibles.add(casesVides[i]);
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
		texte += "\n\ncoupsPossibles=\n" ;
		for(int taille = 0 ; taille < coupsPossibles.size() ; taille++){
			texte += coupsPossibles.get(taille) + "\n";
		}
		return texte;
	}


}
