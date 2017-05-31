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
		
		int nombrePionsRetournesMax = plateau.determinerPionsARetourner(meilleurChoix,1).size();
		
		for (int i = 1; i < coupsPossibles.size()
				&& coupsPossibles.get(i) != null; i++) {
			
			if (coupsPossibles.size() > nombrePionsRetournesMax) {
				nombrePionsRetournesMax = plateau.determinerPionsARetourner(coupsPossibles.get(i), 1).size();
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
		Case meilleurChoix;
		
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

    	/*
    	 * Détermination des niveaux d'importances des cases de coupsPossibles
    	 */
    	for (int indice = 0 ; indice < coupsPossibles.size() ; indice++) {
    		importanceCase[indice] = rechercheImportanceCase(
    				                               coupsPossibles.get(indice));
    	}

    	/*
    	 * Tri des cases par niveaux d'importance
    	 * Les niveaux les plus important en premier
    	 * vers les plus faibles en dernier
    	 */
    	for (int indice = 1 ; indice < coupsPossibles.size() ; indice++) {
    		// Case à insérer dans la partie triée du tableau
    		Case aInserer = coupsPossibles.get(indice);
    		// niveau d'importance de la case à insérer
    		int niveauAInserer = importanceCase[indice];
    		
    		// recherche de l'indice dans lequel insérer aInserer
    		for (int etape = 0 ;
    			 etape < indice &&
    			 importanceCase[etape] < niveauAInserer ;
    			 etape++);
    		// empty body
    		
    		// Insertion de aInserer dans coupsPossibles et
    		//     de niveauAInserer dans importanceCase
    		// TODO algorithme d'insertion
    	}
    	
    	} else if (coupsPossibles.size() == 1) {
    		/* Si il n'existe qu'un coups possible alors l'IA joue ce coups */
    		meilleurChoix = coupsPossibles.get(0);
    	}
    	
		return null;
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
       		if (aDeterminer.getLigne() == casesFortes[indice][1] &&
       		    aDeterminer.getColonne() == casesFortes[indice][2]) {
       			importance = 4;
       		}
       	}
    	
		// Test parmis les cases moyennes
    	for (int indice = 0 ; indice < casesMoyennes.length && importance == 0 ;
       		 indice++) {
       		if (aDeterminer.getLigne() == casesMoyennes[indice][1] &&
       		    aDeterminer.getColonne() == casesMoyennes[indice][2]) {
       			importance = 3;
       		}
       	}
    	
		// Test parmis les cases faibles
    	for (int indice = 0 ; indice < casesFaibles.length && importance == 0 ;
       		 indice++) {
       		if (aDeterminer.getLigne() == casesFaibles[indice][1] &&
       		    aDeterminer.getColonne() == casesFaibles[indice][2]) {
       			importance = 2;
       		}
       	}
    	
		// Test parmis les cases très faibles
    	for (int indice = 0 ; indice < casesFortes.length && importance == 0 ;
       		 indice++) {
       		if (aDeterminer.getLigne() == casesTresFaibles[indice][1] &&
       		    aDeterminer.getColonne() == casesTresFaibles[indice][2]) {
       			importance = 1;
       		}
       	}
    	
    	/* Si importance est toujours égale à 0 alors
    	 *  aDeterminer a une très forte importance
    	 */
    	if (importance == 0) {
    		importance = 5;
    	}
    	
		return importance;
	}
	
}
