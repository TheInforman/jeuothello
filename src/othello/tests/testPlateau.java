/*
 * testPlateau.java                                            06/05/2017
 * Tous droits réservés à l'IUT de Rodez
 */
package othello.tests;

import othello.Case;
import othello.Plateau;

/**
 * Classe permettant de tester les différentes méthodes de la classe Plateau
 * 
 * @author Vincent Galinier
 * @author Adrien Bouyssou
 * @author Kerian Georges
 * @author Arthur Pradier
 * @author Mickaël Queudet 
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
//         2 |                |
//         3 |                |
//         4 |    * N N B     |
//         5 |                |
//         6 |                |
//         7 |                |
		
		plateau_1.setCase(4, 3, Case.COULEUR_NOIR);
		plateau_1.setCase(4, 4, Case.COULEUR_NOIR);
		plateau_1.setCase(4, 5, Case.COULEUR_BLANC);

		plateau_1.determinerCoupsPossibles(Case.COULEUR_BLANC);
		
		System.out.println(plateau_1);
		System.out.println("Doit renvoyer 4,2");
		

		
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
		
		plateau_2.setCase(3, 3, Case.COULEUR_NOIR);
		plateau_2.setCase(4, 4, Case.COULEUR_NOIR);
		plateau_2.setCase(3, 4, Case.COULEUR_BLANC);
		plateau_2.setCase(4, 3, Case.COULEUR_BLANC);
		plateau_2.determinerCoupsPossibles(Case.COULEUR_BLANC);
		
		System.out.println(plateau_2);
		System.out.println("Doit renvoyer 3,2\t 2,3\t 5,4\t 4,5");
			
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
		
		plateau_4.setCase(2, 1, Case.COULEUR_BLANC);
		plateau_4.setCase(2, 2, Case.COULEUR_BLANC);
		plateau_4.setCase(3, 2, Case.COULEUR_BLANC);
		plateau_4.setCase(3, 3, Case.COULEUR_BLANC);
		plateau_4.setCase(4, 2, Case.COULEUR_BLANC);
		
		plateau_4.setCase(2, 3, Case.COULEUR_NOIR);
		plateau_4.setCase(2, 4, Case.COULEUR_NOIR);
		plateau_4.setCase(2, 5, Case.COULEUR_NOIR);
		plateau_4.setCase(3, 4, Case.COULEUR_NOIR);
		plateau_4.setCase(4, 3, Case.COULEUR_NOIR);
		
		plateau_4.determinerCoupsPossibles(Case.COULEUR_BLANC);
		
		System.out.println(plateau_4);
		System.out.println("Doit renvoyer 1,3  5,3  1,4  4,4 "
				+" 5,4  1,5  3,5  2,6");
		
		
	}
	
	private static void enleverPionsDepart(Plateau plateau){
		plateau.setCase(3, 3, Case.COULEUR_NEUTRE);
		plateau.setCase(4, 4, Case.COULEUR_NEUTRE);
		plateau.setCase(4, 3, Case.COULEUR_NEUTRE);
		plateau.setCase(3, 4, Case.COULEUR_NEUTRE);

	}
}
