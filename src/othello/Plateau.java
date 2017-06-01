/*
 * Plateau.java                                            06/05/2017
 * Tous droits r�serv�s � l'IUT de Rodez
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
 * @author Micka�l Queudet 
 */
public class Plateau implements Serializable {

	private static final long serialVersionUID = 1L;


	/** La largeur du plateau */
	public static final int LARGEUR = 8;

	/** La hauteur du plateau */ 
	public static final int HAUTEUR = 8;

	/** L'ensemble des cases constituant le plateau */
	public Case[][] othellier = new Case[HAUTEUR][LARGEUR];

	/** Ensembles des coups possibles pour le joueur jouant  le tour courant */
	public static ArrayList<Case> coupsPossibles = new ArrayList<Case>();

	/** 
	 * Ensembles des pions retourner dans un tour avec 
	 * en premi�re position le pions choisis par le joueur
	 */
	private ArrayList<Case> tableauRetour; 
	
	/**  
	 * Ensemble des pions � retourner suite � l'action du joueur
	 */
	private ArrayList<Case> aRetourner; 
	
	/** True si le pion a �t� pos�, false sinon */
	private boolean actionEffectuee = false;
	
	/** 
	 * Tableau � deux dimensions repr�sentant les d�placement 
	 * dans toutes les directions possibles sur un plateau de jeu,
	 * c'est � dire les lignes, colonnes,  diagonales
	 */
	private static int[][] TABL_DEPLACEMENT = {{-1,0},{-1,1},{0,1},{1,1},
			{1,0},{1,-1},{0,-1},{-1,-1}};
	
	
	/** (constructeur d'�tat d'instance)
	 * 	Cr�e un nouvel objet plateau de 8x8 et l'initialise
	 *  avec deux pions de chaque couleur en damier sur le
	 *  carr� central du plateau.
	 *  Ceci est la configuration de d�part de toutes parties d'othello
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
		tableauRetour = new ArrayList<Case>() ;
		aRetourner = new ArrayList<Case>() ;
	}

	/**
	 * @return the coupsPossibles
	 */
	public ArrayList<Case> getCoupsPossibles() {
		return coupsPossibles;
	}

	
	/**
	 * retourne la valeur du boolean actionEffectuer
	 * @return actionEffectuer
	 */
	public boolean isActionEffectuee() {
		return actionEffectuee;
	}

	/**
	 * Change l'�tat du boolean � celui pass� en param�tre
	 * @param actionEffectuer
	 */
	public void setActionEffectuee(boolean actionEffectuer) {
		this.actionEffectuee = actionEffectuer;
	}
	
	/**
	 * Applique l'action d'un joueur en retournant les pions de l'adversaire.
	 * 
	 * @param caseConcernee		la case sur laquelle un applique le coups,
	 * 							il aura fallu au pr�alable v�rifier qu'un coup
	 * 							est applicable sur cette case
	 * @param couleur	la couleur du joueur portant le coups
	 * 
	 * @return			Un tableau de cases, la premi�re case �tant celle o� le
	 * 					joueur a plac� son pion, les suivantes sont les cases
	 *					correspondant aux pions qui ont chang�es de couleur
	 *					suite � l'action du joueur
	 *  				
	 *  				Note : Ce param�tre de retour sert dans le cas o� l'on
	 *  					   archive les coups jou�s
	 */
	public ArrayList<Case> appliquerCoups(Case caseConcernee, int couleur) {

		actionEffectuee = false ;
		
		/**
		 * ArrayList des pions � retourner, c'est � dire � changer 
		 * de leur couleur actuel � la couleur du joueur actuel
		 */
		aRetourner.clear();
		aRetourner = determinerPionsARetourner(caseConcernee,couleur);


		/* Test si la case souhait� est pr�sente
		   dans la liste des coups possibles */
		if(presentCoupPossibles(caseConcernee)){
			
			/* On pose un pion sur la caseConcernee */
			caseConcernee.setCouleur(couleur); 

			/* On change de couleur la liste des pions � retourner */
			for (int i = 0; i < aRetourner.size() && aRetourner.get(i) != null;
					i++) {
				 aRetourner.get(i).setCouleur(couleur);
			}
			
			tableauRetour.clear();
			tableauRetour.add(0, caseConcernee);
			
			for (int i = 0; i < aRetourner.size() ; i++) {
				
				tableauRetour.add(i+1,aRetourner.get(i));
			}
			actionEffectuee = true ;
			coupsPossibles.clear();
		}
		return tableauRetour;
	}


	/**
	 * V�rifie si la case d�sign�e par le joueur est une case jouable.
	 * 
	 * @param 	caseConcernee 	case d�signer par le joueur
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
	 *  M�thode qui d�termine les cases qui seront
	 *  � retourn�es suite au coup du joueur
	 *  
	 * @param caseCentrale � partir de laquelle on va faire les recherches
	 * 		  en lignes et diagonales pour d�terminer les pions � retourner
	 * @param couleur du pion pos� sur la caseCentrale par le joueur
	 * @return la liste des pions � retourner
	 */

	public ArrayList<Case> determinerPionsARetourner(Case caseCentrale,
													   int couleur) {
		
		ArrayList<Case> PionsARetourner = new ArrayList<Case>();
		
		/** int de d�placement le long des lignes */ 
		int deplacementLigne;
		
		/** int de d�placement le long des colonnes */ 
		int deplacementColonne;
		
		/** boolean d'arret de recherche dans une direction */

		boolean arretRechercheDirection;
		
		/** initialisation de l'indice du tableau directionnel */
		int indice;


		/** 
		 * Tableau � deux dimensions de Cases dans lequel on stocke
		 * l'ensemble des pions pr�sents dans les directions
		 * autour de la case centrale
		 */
		Case[][] tableauVueDirectionnel = new Case[8][8];

		/* Pour chaque direction du tableau de d�placement */
		for (int direction =0; direction<TABL_DEPLACEMENT.length; direction++){
			
			/* initialisation de chaque boucle de 
			   recherche des pions dans la direction */
			arretRechercheDirection = false;
			indice = 0;
			deplacementLigne = TABL_DEPLACEMENT[direction][0];
			deplacementColonne = TABL_DEPLACEMENT[direction][1];

			/* On se d�place d'une case dans la 
			   direction donn�e en ne sortant pas du plateau */
			for (int ligne = caseCentrale.getLigne() + deplacementLigne,
				 colonne = caseCentrale.getColonne() + deplacementColonne;

					(0 <= colonne && colonne < LARGEUR)
					&& (0 <= ligne && ligne < HAUTEUR)
					&& !arretRechercheDirection;

				 colonne += deplacementColonne, ligne += deplacementLigne) {
				
				/* Si la case est vide ou de la m�me couleur que le 
				   pion plac� on arr�te la recherche dans cette direction
				   sinon on ajoute la case aux tableau vue directionnel */
				if(othellier[ligne][colonne].getCouleur()==Case.COULEUR_NEUTRE
				   || othellier[ligne][colonne].getCouleur() == couleur) {	
					arretRechercheDirection = true;
				}

				tableauVueDirectionnel[direction][indice]
								= othellier[ligne][colonne];
				
				indice ++;
			}
		}
		
		indice = 0;
		
		/* Si la case � l'extr�mit� d'une des direction du tableau de vue
		   directionnel est de la m�me couleur que la case centrale, alors
		   tous les pions entre ces deux pions sont ajouter � la liste des
		   pions � retourn� */
		for (int i = 0; i < tableauVueDirectionnel.length; i++) {
			if(derniereCase(tableauVueDirectionnel[i]) != null &&
			   derniereCase(tableauVueDirectionnel[i]).getCouleur()==couleur){
				
				for (int j = 0; j < tableauVueDirectionnel[i].length
						&& tableauVueDirectionnel[i][j] != null; j++) {
					if(!(tableauVueDirectionnel[i][j].getCouleur() ==couleur)){
					PionsARetourner.add(tableauVueDirectionnel[i][j]);
					}
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
	 * @param ligneDeCases	le tableau o� l'on souhaite d�terminer le dernier 
	 * 						�l�ment non nul
	 * @return		la derni�re case non nulle du tableau pass� en param�tre
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
	 * D�termine si la case sp�cifi�e en param�tre a au moins une paire
	 * @param Case que l'on va chercher � coupl�e avec d'autre case
	 * @param couleur de la case � coupl�e
	 * @return true si il existe au moins une case coupl�e � la 
	 * 			case pass�e en param�tre, false sinon
	 */
	private boolean aUnePaire(Case aCoupler, int couleur) {

		int couleurInverse = couleur == Case.COULEUR_BLANC ?
				Case.COULEUR_NOIR : Case.COULEUR_BLANC;
		
		boolean aUnePaire = false;
		
		
		/**
		 * bool�en passant � vrai lorsqu'on sait si la case n'a pas de paire
		 * dans une direction donn�e
		 */
		boolean arretRechercheDirection;  

		/** int de d�placement le long des lignes */ 
		int deplacementLigne;
		
		/** int de d�placement le long des colonnes */ 
		int deplacementColonne;
		
		
		// TODO : transformer en une m�thode de recherche directionnel
		// (pour r�utiliser dans les diff�rentes m�thodes) que si on a le temps 
		
		/* Pour chaque direction du tableau */
		for (int direction = 0; direction < TABL_DEPLACEMENT.length
				&& !aUnePaire; direction++ ) {
			arretRechercheDirection = false;

			deplacementLigne = TABL_DEPLACEMENT[direction][0];
			deplacementColonne = TABL_DEPLACEMENT[direction][1];
			
			/* On se d�place d'une case dans la 
			   direction donn�e en ne sortant pas du plateau */
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
				 *    la case pr�c�dente est de la couleur inverse.
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
	 * Renvoi un tableau contenant l'ensemble des cases sur lesquelles
	 * sont pos�s des pions d'une couleur donn�e.
	 *
	 * @param couleur	la couleur des pions que l'on recherche
	 * @return	l'ensemble des cases vides pr�sent dans le plateau
	 */
	private ArrayList<Case> casesVides(){

		/**
		 * Arraylist contenant l'ensemble des cases vides 
		 * pr�sentes sur le plateau
		 */
		ArrayList<Case> casesVides = new ArrayList<Case>();

		for (int colonne = 0; colonne < LARGEUR; colonne++) {
			for (int ligne = 0; ligne < HAUTEUR; ligne ++) {
				if (othellier[ligne][colonne].getCouleur() == -1) {
					casesVides.add(othellier[ligne][colonne]);
				}
			}
		}
		return casesVides;
	}
	
	/**
	 * D�termine l'ensemble des coups possibles d'un joueur pour un
	 * tour donn�.
	 * 
	 * @param couleur	la couleur du joueur dont on veut d�terminer
	 * 					les coups possibles
	 * @return		une liste de cases o� le joueur peut poser ses pions
	 */
	public void determinerCoupsPossibles(int couleur) {

		/* On d�termine l'ensemble des cases vides */
		ArrayList<Case> casesVides = casesVides();

		/* Pour chaque cases on d�termine si elle peut �tre coupl�e avec au 
		 * moins une case sur laquelle un joueur a pos� son pion.
		 */
		for (int i =0; i < casesVides.size() && casesVides.get(i) !=null ;i++){

			if (aUnePaire(casesVides.get(i), couleur)) {
				coupsPossibles.add(casesVides.get(i));
			}
		}
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
