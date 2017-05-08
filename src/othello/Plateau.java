/*
 * Plateau.java                                            06/05/2017
 * Groupe  Adrien Bouyssou, Vincent Galinier, Kerian Georges, Arthur Pradier, Mickaël Queudet
 */

package othello;

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
	
	/** L'ensemble des cases constituant le plateau */
	public Case[][] tablier = new Case[HAUTEUR][LARGEUR];
	// -- Note : Java "row major" -> ligne en premier
	
	/** (constructeur d'état d'instance)
	 *  Plateau définit par son contenu.
	 * 	Crée un nouvel objet plateau et l'initialise avec deux pions de chaque
	 *  couleur au centre.
	 *  TODO : plus d'explications ?
	 */
	public Plateau() {
		for (int colonne = 0; colonne < LARGEUR; colonne++) {
			for (int ligne = 0; ligne < HAUTEUR; ligne ++) {
				tablier[ligne][colonne] = new Case(ligne, colonne);
			}
		}
		
		tablier[3][3].setCouleur(Case.COULEUR_BLANC);
		tablier[4][4].setCouleur(Case.COULEUR_BLANC);
		tablier[4][3].setCouleur(Case.COULEUR_NOIR);
		tablier[3][4].setCouleur(Case.COULEUR_NOIR);
	}
	
	//TODO : créer un constructeur dans le cas où l'on charge une sauvegarde
	
	//TODO : retrouver pourquoi on retourne une Case déjà.
	/**
	 * Applique l'action d'un joueur en retournan les pions de l'adversaire.
	 * 
	 * @param caseConcernee		la case sur laquelle un applique le coups
	 * @return		TODO compléter et trouver // c'est pour le archiver tour
	 */
	public Case appliquerCoups(Case caseConcernee) {
		//TODO : écrire le corps de la méthode
		return null;
	}
	
	/**
	 * Détermine l'ensemble des coups possibles d'un joueur pour un
	 * tour donné.
	 * 
	 * @param couleur	la couleur du joueur dont on veut déterminer
	 * 					les coups possibles
	 * @return		une liste de cases où le joueur peut poser ses pions
	 */
	public Case[] determinerCoupsPossibles(int couleur) {
		
		/* On détermine l'ensemble des cases vides */
		Case[] casesVides = casesVides();
		
		//TODO : Voir s'il n'y a pas moyen de faire autrement :
		/* Tableau des coups possibles */
		Case[] coupsPossibles = new Case[casesVides.length];
		
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
		return coupsPossibles;
	}
	
	/**
	 * Détermine si la case spécifiée en paramètre a au moins une paire
	 * @param case1
	 * @return
	 */
	public boolean aUnePaire(Case aCoupler, int couleur) {

		int couleurInverse = couleur == Case.COULEUR_BLANC ?
				Case.COULEUR_NOIR : Case.COULEUR_BLANC;

		int[][] tableauDeplacement = {{-1,0},{-1,1},{0,1},{1,1},
				{1,0},{1,-1},{0,-1},{-1,-1}};
		boolean aUnePaire = false;
		boolean arretRechercheDirection;  /* booléen passant à vrai lorsqu'on
		 * sait si la case n'a pas de paire
		 * dans une direction donnée
		 */
		
		int deplacementLigne,
			deplacementColonne;
		
		/* Pour chaque direction du tableau */
		for (int direction = 0; direction < tableauDeplacement.length
				&& !aUnePaire; direction++ ) {
			arretRechercheDirection = false;
			
			deplacementLigne = tableauDeplacement[direction][0];
			deplacementColonne = tableauDeplacement[direction][1];
			
			/* On "avance" d'un pas dans la direction donnée (largeur) */
			for (int ligne = aCoupler.getLigne() + deplacementLigne,
					colonne = aCoupler.getColonne() + deplacementColonne;
					
					(0 <= colonne && colonne < LARGEUR)
					&& (0 <= ligne && ligne < HAUTEUR)

					&& !arretRechercheDirection;

					colonne += deplacementColonne, ligne += deplacementLigne) {				
				
				/* Si case vide : pas de paire dans cette direction */
				if (tablier[ligne][colonne].getCouleur()
						== Case.COULEUR_NEUTRE ) {
					arretRechercheDirection = true;
				}

				/* Si la case courante est de la couleur 'couleur' et que
				 *    la case précédente est de la couleur inverse.
				 */
				if (tablier[ligne][colonne].getCouleur() == couleur ) {
					if (tablier[ligne - deplacementLigne]
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
	 *
	 * @param couleur	la couleur des pions que l'on recherche
	 * @return		l'ensemble des cases de la couleur passée en argument
	 */
	private Case[] casesVides(){

		//TODO : init à revoir ?
		Case[] casesVides = new Case[64];
		
		int indice = 0;

		for (int colonne = 0; colonne < LARGEUR; colonne++) {
			for (int ligne = 0; ligne < HAUTEUR; ligne ++) {
				if (tablier[ligne][colonne].getCouleur() == -1) {
					casesVides[indice] = tablier[ligne][colonne];
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
				if (tablier[ligne][colonne].getCouleur() == couleur) {
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
				texte += tablier[ligne][colonne].getCaractere() + " ";
			}
			texte += "|\n";
		}
		return texte;
	}
	
	
}
