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
		System.out.println(testeurEgalite(new Case(-1, 0, 1), null));
		System.out.println(testeurEgalite(new Case(1, -2, 1), null));
		
		// test création de case avec des coordonnées trop grandes
		System.out.println(testeurEgalite(new Case(9, 5, 1), null));
		System.out.println(testeurEgalite(new Case(4, 10, 1), null));
		
		// test création de case avec une couleur non-valide
		System.out.println(testeurEgalite(new Case(5, 3, -4), null));
		System.out.println(testeurEgalite(new Case(5, 3, 4), null));
	}
	
	/**
	 * Teste si les deux paramètres ont la même valeur
	 * 
	 * @param aTester La valeur a tester
	 * @param resAttendu La valeur attendu comme résultat
	 * @return <ul><li>true si le test est réussi</li>
	 *             <li>false si le test échou</li>
	 *         </ul>
	 */
	private static boolean testeurEgalite(Case aTester, Case resAttendu) {
		return aTester.equals(resAttendu);
	}

}
