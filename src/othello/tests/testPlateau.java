/*
 * testPlateau.java                                            06/05/2017
 * Groupe  Adrien Bouyssou, Vincent Galinier,
 * 		   Kerian Georges, Arthur Pradier, Mickaël Queudet
 */
package othello.tests;

import othello.Case;
import othello.Plateau;

/**
 * Classe permettant de tester les différentes méthodes de la classe Plateau
 * 
 * @author Vincent
 */
public class testPlateau {
	
	/** Trait de séparation */
	private static String SEPARATION = "\n ========================"
			+ "====================== \n";
	
	/**
	 * Méthode main qui lance les tests.
	 * 
	 * @param args inutilisé
	 */
	public static void main(String[] args) {
		
		testDeterminerCoupsPossibles();
		
	}
	
	/**
	 * Tests sur la méthode déterminerCoupsPossibles
	 */
	private static void testDeterminerCoupsPossibles() {
		
		/* Plateau 1*/
		System.out.println(SEPARATION);
		Plateau plateau_1 = new Plateau();
		enleverPionsDepart(plateau_1);
		
//		      0 1 2 3 4 5 6 7 
//		   0 |                |
//		   1 |                |
//		   2 |                |
//		   3 |                |
//		   4 |                |
//		   5 |      * N N B   |
//		   6 |                |
//		   7 |                |
		
		plateau_1.othellier[4][3].setCouleur(Case.COULEUR_NOIR);
		plateau_1.othellier[4][4].setCouleur(Case.COULEUR_NOIR);
		plateau_1.othellier[4][5].setCouleur(Case.COULEUR_BLANC);
		
		plateau_1.determinerCoupsPossibles(Case.COULEUR_BLANC);
		
		System.out.println(plateau_1);
		//System.out.println("Doit renvoyer 4,2");
		

		
//		/* Plateau 2*/
		System.out.println(SEPARATION);
		Plateau plateau_2 = new Plateau();
		enleverPionsDepart(plateau_2);
		
//		      0 1 2 3 4 5 6 7 
//		   0 |                |
//		   1 |                |
//		   2 |                |
//		   3 |      N B       |
//		   4 |      B N       |
//		   5 |                |
//		   6 |                |
//		   7 |                |
		
		plateau_2.othellier[3][3].setCouleur(Case.COULEUR_NOIR);
		plateau_2.othellier[4][4].setCouleur(Case.COULEUR_NOIR);
		plateau_2.othellier[3][4].setCouleur(Case.COULEUR_BLANC);
		plateau_2.othellier[4][3].setCouleur(Case.COULEUR_BLANC);
		
		plateau_2.determinerCoupsPossibles(Case.COULEUR_BLANC);
		
		System.out.println(plateau_2);
		//System.out.println("Doit renvoyer 3,2\t 2,3\t 5,4\t 4,5");
		
//		/* Plateau 3*/
		System.out.println(SEPARATION);
		Plateau plateau_3 = new Plateau();
		enleverPionsDepart(plateau_3);
		
//		      0 1 2 3 4 5 6 7 
//		   0 |                |
//		   1 |                |
//		   2 |                |
//		   3 |                |
//		   4 |      B B N     |
//		   5 |                |
//		   6 |                |
//		   7 |                |
		
		plateau_3.othellier[4][5].setCouleur(Case.COULEUR_NOIR);
		plateau_3.othellier[4][3].setCouleur(Case.COULEUR_BLANC);
		plateau_3.othellier[4][4].setCouleur(Case.COULEUR_BLANC);
		
		plateau_3.determinerCoupsPossibles(Case.COULEUR_BLANC);
		
		System.out.println(plateau_3);
		//System.out.println("Doit renvoyer 4,6");
		
//		/* Plateau 4*/
		
		System.out.println(SEPARATION);
		Plateau plateau_4 = new Plateau();
		enleverPionsDepart(plateau_4);
		
//		      0 1 2 3 4 5 6 7 
//		   0 |                |
//		   1 |                |
//		   2 |  B B N N N     |
//		   3 |    B B N       |
//		   4 |    B N         |
//		   5 |                |
//		   6 |                |
//		   7 |                |
		

		plateau_4.othellier[2][1].setCouleur(Case.COULEUR_BLANC);
		plateau_4.othellier[2][2].setCouleur(Case.COULEUR_BLANC);
		plateau_4.othellier[3][2].setCouleur(Case.COULEUR_BLANC);
		plateau_4.othellier[3][3].setCouleur(Case.COULEUR_BLANC);
		plateau_4.othellier[4][2].setCouleur(Case.COULEUR_BLANC);
		
		plateau_4.othellier[2][3].setCouleur(Case.COULEUR_NOIR);
		plateau_4.othellier[2][4].setCouleur(Case.COULEUR_NOIR);
		plateau_4.othellier[2][5].setCouleur(Case.COULEUR_NOIR);
		plateau_4.othellier[3][4].setCouleur(Case.COULEUR_NOIR);
		plateau_4.othellier[4][3].setCouleur(Case.COULEUR_NOIR);
		
		plateau_4.determinerCoupsPossibles(Case.COULEUR_BLANC);
		
		System.out.println(plateau_4);
		System.out.println("Doit renvoyer");
		
		
		/* Plateau 5*/
		Plateau plateau_5 = new Plateau();
		
		/* Plateau 6*/
		Plateau plateau_6 = new Plateau();
		
		/* Plateau 7*/
		Plateau plateau_7 = new Plateau();
		
		/* Plateau 8*/
		Plateau plateau_8 = new Plateau();
		
	}
	
	private static void enleverPionsDepart(Plateau plateau){
		plateau.othellier[3][3].setCouleur(Case.COULEUR_NEUTRE);
		plateau.othellier[4][4].setCouleur(Case.COULEUR_NEUTRE);
		plateau.othellier[4][3].setCouleur(Case.COULEUR_NEUTRE);
		plateau.othellier[3][4].setCouleur(Case.COULEUR_NEUTRE);
	}
}
