/*
 * testCase.java                                            06/05/2017
 * Tous droits réservés à l'IUT de Rodez
 */
package othello.tests;

import othello.Case;

/**
 * Classe permettant de tester les différentes méthodes de la classe Case
 * 
 * @author Vincent Galinier
 * @author Adrien Bouyssou
 * @author Kerian Georges
 * @author Arthur Pradier
 * @author Mickaël Queudet 
 */
public class testCase {

	/**
	 * Méthode main qui lance les tests.
	 * 
	 * @param args inutilisé
	 */
	public static void main(String[] args) {
		System.out.println("-=-=-=-=- Test du constructeur de Case -=-=-=-=-");

		
		// test création case avec coordonnées négatives
		System.out.print("La case par défaut initilisé aux coordonées "
				  + "-1 0 de couleur noire est correcte : ");
		System.out.println(caseCorrecte(new Case(-1, 0, 1)));
		
		System.out.print("La case par défaut initilisé aux coordonées "
				  + "1 -2 de couleur noire est correcte : ");
		System.out.println(caseCorrecte(new Case(1, -2, 1)));
		
		// test création de case avec des coordonnées trop grandes
		System.out.print("La case par défaut initilisé aux coordonées "
				  + "9 5 de couleur noire est correcte : ");
		System.out.println(caseCorrecte(new Case(9, 5, 1)));
		
		System.out.print("La case par défaut initilisé aux coordonées "
				  + "4 10 de couleur noire est correcte : ");
		System.out.println(caseCorrecte(new Case(4, 10, 1)));
		
		// test création de case avec une couleur non-valide
		System.out.print("La case par défaut initilisé aux coordonées "
				  + "5 3 de couleur invalide est correcte : ");
		System.out.println(caseCorrecte(new Case(5, 3, -4)));
		
		System.out.print("La case par défaut initilisé aux coordonées "
				  + "5 3 de couleur invalide est correcte : ");
		System.out.println(caseCorrecte(new Case(5, 3, 4)));
		
		//test création de case avec des coordonées valide et de chaque couleur
		System.out.print("La case par défaut initilisé aux coordonées "
				  + "5 3 de couleur noire  est correcte : ");
		System.out.println(caseCorrecte(new Case(5, 3, 1)));
		
		System.out.print("La case par défaut initilisé aux coordonées "
				  + "5 3 de couleur blanche est correcte : ");
		System.out.println(caseCorrecte(new Case(5, 3, 0)));
		
		System.out.print("La case par défaut initilisé aux coordonées "
				  + "5 3 de couleur neutre est correcte : ");
		System.out.println(caseCorrecte(new Case(5, 3, -1)));
		
		//test création d'une case avec la couleur par défaut
		System.out.print("La case par défaut initilisé aux coordonées "
						  + "x y est correcte : ");
		System.out.println(caseCorrecte(new Case(5, 3)));
	}
	
	/**
	 * Teste si les deux paramètres ont la même valeur
	 * 
	 * @param aTester La valeur a tester
	 * @param resAttendu La valeur attendu comme résultat
	 * @return <ul><li>true si le test est réussi</li>
	 *             <li>false si le test échoue</li>
	 *         </ul>
	 */
	private static boolean caseCorrecte(Case aTester) {
		boolean ok = false ;
		if(aTester.getColonne() < 8 && aTester.getColonne() >= 0 &&
		   aTester.getLigne() < 8 && aTester.getLigne() >= 0 &&
		   aTester.getCouleur() < 2 && aTester.getCouleur() > -2 ){
			ok = true;
		}
		return ok;
	}

}
