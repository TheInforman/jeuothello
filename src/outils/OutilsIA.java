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
	
	/** Tableau à deux dimensions représentant l'imortance des cases */
	private static final int[][] tableauStratImportance =
		{
				{ 5,-1, 4, 3, 3, 4,-1, 5},
				{-1,-2, 0, 0, 0, 0,-2,-1},
				{ 4, 0, 1, 2, 2, 1, 0, 4},
				{ 3, 0, 2, 0, 0, 2, 0, 3},
				{ 3, 0, 2, 0, 0, 2, 0, 3},
				{ 4, 0, 1, 2, 2, 1, 0, 4},
				{-1,-2, 0, 0, 0, 0,-2,-1},
				{ 5,-1, 4, 3, 3, 4,-1, 5}
		};
	
	/**
	 * Coordonnées des cases du plateau ayant
	 * un très faible intérêt stratégique
	 */
    private static final int[][] casesTresFaibles = {{2,2}, {2,7},
    		                                {7,2}, {7,7}};
    /** La couleur de l'ordinateur */
    public static final int COULEUR_IA = 0;
    
    /** La couleur du joueur humain */
    public static final int COULEUR_HUMAIN = 1;
	
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
		
		int nombrePionsRetournesMax = plateau.determinerPionsARetourner(
				                             meilleurChoix, COULEUR_IA).size();
		
		for (int i = 1; i < coupsPossibles.size()
				&& coupsPossibles.get(i) != null; i++) {
			
			if (coupsPossibles.size() > nombrePionsRetournesMax) {
				nombrePionsRetournesMax = plateau.determinerPionsARetourner(
						             coupsPossibles.get(i), COULEUR_IA).size();
				meilleurChoix = coupsPossibles.get(i);
			}
		}
		return meilleurChoix;
	}
	
	public static Case strategieNormale(Partie partieCourante) {
		
		Plateau plateau = partieCourante.getPlateau();
		
		ArrayList<Case> coupsPossibles = plateau.getCoupsPossibles();
		
		//Case ayant le plus de pions retournés
		Case meilleurChoix;
		
		/* 
		 * Si il existe au moins deux coups possible alors traitement nominal:
		 * Recherche de la meilleure case à jouer
		 */
		if (coupsPossibles.size() == 1) {
			meilleurChoix = coupsPossibles.get(0);
		} else {
			
			int tableauImportanceCases[] = new int[coupsPossibles.size()];
			
			/* Recherche de l'importance des cases */
			for (int indice = 0;
				 indice < tableauImportanceCases.length;
				 indice++) {
				tableauImportanceCases[indice] =
						rechercheImportance(coupsPossibles.get(indice));
			}
			
			/*
			 * Tri des cases par niveaux d'importance
			 * Les niveaux les plus important en premier
			 * vers les plus faibles en dernier
			 */
			for (int i = 0; i < tableauImportanceCases.length; i++) {
				for (int j = 0; j < tableauImportanceCases.length; j++) {
					if (tableauImportanceCases[i] > tableauImportanceCases[j]) {
						
						int temporaire = tableauImportanceCases[i];
						tableauImportanceCases[i] = tableauImportanceCases[j];
						tableauImportanceCases[j] = temporaire;
						
						Case caseTemp = coupsPossibles.get(i);
						coupsPossibles.set(i, coupsPossibles.get(j));
						coupsPossibles.set(j, caseTemp);
					}
				}
			}
			for (int i = 0; i < tableauImportanceCases.length; i++) {
				System.out.print(tableauImportanceCases[i] + " ");
			}
			System.out.println("Joue une priorité " +
			                   tableauImportanceCases[0]);
			
			
			/* Récupération du nombre de cases de plus haute importance */
			int nbCasesImportantes = 1;
			for (int i = 1; i < tableauImportanceCases.length; i++) {
				if (tableauImportanceCases[i] == tableauImportanceCases[0]) {
					nbCasesImportantes++;
				}
			}
			
			if(nbCasesImportantes != 1) {
				
				/* Nb de pions retournés par pions importants*/
				int nbPionsRetournesMax = plateau.determinerPionsARetourner(
						             coupsPossibles.get(0), COULEUR_IA).size();
				meilleurChoix = coupsPossibles.get(0);
				
				for (int i = 1; i < nbCasesImportantes; i++) {
					int nbPionsRetournesCaseI =
							                 plateau.determinerPionsARetourner(
							                             coupsPossibles.get(i),
							                                COULEUR_IA).size();
					if ( nbPionsRetournesMax < nbPionsRetournesCaseI) {
						nbPionsRetournesMax = nbPionsRetournesCaseI;
						meilleurChoix = coupsPossibles.get(i);
					}
				}
			}
			
		}
		return coupsPossibles.get(0);
	}
	
	/**
	 * TODO : JDOC
	 * @param aDeterminer
	 * @return
	 */
	private static int rechercheImportance(Case aDeterminer) {
		return tableauStratImportance[aDeterminer.getLigne()]
				                   [aDeterminer.getColonne()];
	}
	
	/**
	 * TODO : JDOC
	 * @param aDeterminer
	 * @return
	 */
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
    	for (int indice = 0 ;
             indice < casesMoyennes.length && importance == 0 ;
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
    	for (int indice = 0 ;
             indice < casesTresFaibles.length && importance == 0 ;
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
    	System.out.println("Importance :" + importance);
		return importance;
	}
	
}
