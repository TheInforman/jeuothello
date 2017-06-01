/**
 * 
 */
package outils;

import java.util.ArrayList;

import othello.Case;
import othello.Partie;
import othello.Plateau;

/**
 * @author Vincent
 *
 */
public class OutilsIA {
	
	/** Coordonnées des cases du plateau ayant un fort intérêt stratégique */
	private static final int[][] casesFortes = {{1,3}, {1,6},
			                             {3,1}, {3,3}, {3,4}, {3,5}, {3,6},
			                                    {3,8},
			                             {4,3}, {4,4}, {4,5}, {4,6},
			                             {5,3}, {5,4}, {5,5}, {5,6},
			                             {6,1}, {6,3}, {6,4}, {6,5}, {6,6},
		                                        {6,8},
		                                 {8,3}, {8,6}};
	
	/** Coordonnées des cases du plateau ayant un intérêt stratégique moyen */
	private static final int[][] casesMoyennes = {{1,4}, {1,5},
			                               {2,3}, {2,4}, {2,5}, {2,6},
			                               {3,2}, {3,7},
			                               {4,1}, {4,2}, {4,7}, {4,8},
			                               {5,1}, {5,2}, {5,7}, {5,8},
			                               {6,2}, {6,7},
			                               {7,3}, {7,4}, {7,5}, {7,6},
			                               {8,4}, {8,5}};
	
	/** Coordonnées des cases du plateau ayant un faible intérêt stratégique */
	private static final int[][] casesFaibles = {{1,2}, {1,7},
			                             {2,1}, {2,8},
			                             {7,1}, {7,8},
			                             {8,2}, {8,7}};
	
	/**
	 * Coordonnées des cases du plateau ayant
	 * un très faible intérêt stratégique
	 */
    private static final int[][] casesTresFaibles = {{2,2}, {2,7},
    		                                {7,2}, {7,7}};
    /** La couleur de l'ordinateur */
    private static final int COULEUR_IA = 1;
    
    /** La couleur du joueur humain */
    private static final int COULEUR_HUMAIN = 0;
	
    /**
     * TODO: JDOC
     * @param partieCourante
     * @return
     */
	public static Case strategieFacile(Partie partieCourante) {
		Plateau plateau = partieCourante.getPlateau();
		
		ArrayList<Case> coupsPossibles = plateau.getCoupsPossibles();
		
		//Case ayant le plus de pions retournés
		Case meilleurChoix;
		
		
		/* test de la première case comme initialisation */
		meilleurChoix = coupsPossibles.get(0);
		
		int nombrePionsRetournesMax = plateau.determinerPionsARetourner(meilleurChoix, COULEUR_IA).size();
		
		for (int i = 1; i < coupsPossibles.size()
				&& coupsPossibles.get(i) != null; i++) {
			
			if (coupsPossibles.size() > nombrePionsRetournesMax) {
				nombrePionsRetournesMax = plateau.determinerPionsARetourner(coupsPossibles.get(i), COULEUR_IA).size();
				meilleurChoix = coupsPossibles.get(i);
			}
		}
		return meilleurChoix;
	}
	
	/**
	 * TODO : JDOC
	 * @param partieCourante
	 * @return
	 */
	public static Case strategieNormale(Partie partieCourante) {
		Plateau plateau = partieCourante.getPlateau();

		/*
		 * La liste des coups jouables par l'IA
		 */
		ArrayList<Case> coupsPossibles = plateau.getCoupsPossibles();

		/* 
		 * Si il existe au moins deux coups possible alors traitement nominal:
		 * Recherche de la meilleure case à jouer
		 * 
		 */
		if (1 < coupsPossibles.size()) {

			/*
			 * Le niveau d'importance des cases ayant le même
			 * indice dans le tableau dynamique coupsPossibles
			 */
			int[] importanceCase = new int[coupsPossibles.size()];

			/*
			 * Le nombre de pions retournés par la case ayant le même
			 * indice dans le tableau dynamique coupsPossibles
			 */
			int[] retournePions = new int[coupsPossibles.size()];

			// compteur pour les boucles for
			int indice;

			/*
			 * Détermination des niveaux d'importances
			 * des cases de coupsPossibles
			 */
			for (indice = 0 ; indice < coupsPossibles.size() ; indice++) {
				importanceCase[indice] = rechercheImportanceCase(
						coupsPossibles.get(indice));
			}

			/*
			 * Tri des cases par niveaux d'importance
			 * Les niveaux les plus important en premier
			 * vers les plus faibles en dernier
			 */
			for (indice = 1 ; indice < coupsPossibles.size() ; indice++) {
				// Case à insérer dans la partie triée du tableau
				Case aInserer = coupsPossibles.get(indice);
				// niveau d'importance de la case à insérer
				int niveauAInserer = importanceCase[indice];

				// recherche de l'indice dans lequel insérer aInserer
				int etape;
				for (etape = 0 ;
						etape < indice &&
						importanceCase[etape] < niveauAInserer ;
						etape++);
				// empty body

				// Insertion de aInserer dans coupsPossibles et
				//     de niveauAInserer dans importanceCase
				for (int rang = indice ; etape < rang ; rang--) {
					// Décale toutes les cases de l'intervalle
					// [etape; indice[ d'un rang vers la droite
					// et des niveaux d'importances correspondants
					// dans le tableau importanceCase
					coupsPossibles.set(rang, coupsPossibles.get(rang - 1));
					importanceCase[rang] = importanceCase[rang - 1];
				}
				// Insertion de aInserer et de son niveau attribué à
				// l'indice etape dans le tableau correspondant
				coupsPossibles.set(etape, aInserer);
				importanceCase[etape] = niveauAInserer;
			}

			/* Recherche de l'indice de fin du premier niveau d'importance */
			for (indice = 1 ;
					importanceCase[indice - 1] == importanceCase[indice]
							&& indice < importanceCase.length;
					indice++);

			if (indice != 0) { 			// sinon un seul coup de haute importance
				// si plusieurs coups possible alors
				// tri par ordre de cases récupérées

				/* Recherche du nombre de cases récupérées
				 * en fonction de la case jouée
				 */
				for (int etape = coupsPossibles.size() ; etape <= indice ;
						etape++) {
					retournePions[etape] = plateau.determinerPionsARetourner(
							coupsPossibles.get(etape), COULEUR_IA).size();
				}

				/* Tri des cases importantes par quantité de pions retournés */
				for (int etape = 1 ; etape < indice ; etape++) {
					// La case à insérer pour l'étape et
					// son nombre de pions correspondant
					Case aInserer = coupsPossibles.get(etape);
					int pionsRetournesAInserer = retournePions[etape];

					/* Recherche du futur emplacement de aInserer */
					int futurIndice; // L'indice où sera inséré la case
					for (futurIndice = 0 ;
							futurIndice < etape &&
							retournePions[futurIndice] < pionsRetournesAInserer ;
							futurIndice++);
					// empty body

					/* Décalage des cases nécessaire à l'insertion */
					for (int i = etape ; futurIndice < i ; i--) {
						coupsPossibles.set(i, coupsPossibles.get(i - 1));
						retournePions[i] = retournePions[i - 1];
					}

					// Insertion de aInserer et du nombre associé dans retournePions
					coupsPossibles.set(futurIndice, aInserer);
					retournePions[futurIndice] = pionsRetournesAInserer;

					/* 
					 * Le meilleur choix est la première case du tableau
					 * (la case avec la plus grande importance stratégiques
					 * et qui retourne le plus de pions)
					 */

				}
			}
		}
		return  coupsPossibles.get(0);
	}
	
	private static int rechercheImportanceCase(Case aDeterminer) {
		/*
		 * L'importance de la Case aDeterminer
		 * == 0 si non déterminée
		 */
		int importance = 0;
		// Test parmis les cases fortes
    	for (int indice = 0 ; indice < casesFortes.length && importance == 0 ;
       		 indice++) {
       		if (aDeterminer.getLigne() == casesFortes[indice][0] &&
       		    aDeterminer.getColonne() == casesFortes[indice][1]) {
       			importance = 4;
       		}
       	}
    	
		// Test parmis les cases moyennes
    	for (int indice = 0 ; indice < casesMoyennes.length && importance == 0 ;
       		 indice++) {
       		if (aDeterminer.getLigne() == casesMoyennes[indice][0] &&
       		    aDeterminer.getColonne() == casesMoyennes[indice][1]) {
       			importance = 3;
       		}
       	}
    	
		// Test parmis les cases faibles
    	for (int indice = 0 ; indice < casesFaibles.length && importance == 0 ;
       		 indice++) {
       		if (aDeterminer.getLigne() == casesFaibles[indice][0] &&
       		    aDeterminer.getColonne() == casesFaibles[indice][1]) {
       			importance = 2;
       		}
       	}
    	
		// Test parmis les cases très faibles
    	for (int indice = 0 ; indice < casesTresFaibles.length && importance == 0 ;
       		 indice++) {
       		if (aDeterminer.getLigne() == casesTresFaibles[indice][0] &&
       		    aDeterminer.getColonne() == casesTresFaibles[indice][1]) {
       			importance = 1;
       		}
       	}
    	
    	/* Si importance est toujours égale à 0 alors
    	 *  aDeterminer a une très forte importance
    	 */
    	if (importance == 0) {
    		importance = 5;
    	}
    	System.out.println("Importance : " + importance );
		return importance;
	}
	
}
