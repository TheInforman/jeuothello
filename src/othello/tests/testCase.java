/*
 * testCase.java                                            06/05/2017
 * Tous droits r�serv�s � l'IUT de Rodez
 */
package othello.tests;

import othello.Case;

/**
 * Classe permettant de tester les diff�rentes m�thodes de la classe Case
 * 
 * @author Vincent Galinier
 * @author Adrien Bouyssou
 * @author Kerian Georges
 * @author Arthur Pradier
 * @author Micka�l Queudet 
 */
public class testCase {

	/**
	 * M�thode main qui lance les tests.
	 * 
	 * @param args inutilis�
	 */
	public static void main(String[] args) {
		System.out.println("-=-=-=-=- Test du constructeur de Case -=-=-=-=-");
		
		// test cr�ation case avec coordonn�es n�gatives
		System.out.println(testeurEgalite(new Case(-1, 0, 1), null));
		System.out.println(testeurEgalite(new Case(1, -2, 1), null));
		
		// test cr�ation de case avec des coordonn�es trop grandes
		System.out.println(testeurEgalite(new Case(9, 5, 1), null));
		System.out.println(testeurEgalite(new Case(4, 10, 1), null));
		
		// test cr�ation de case avec une couleur non-valide
		System.out.println(testeurEgalite(new Case(5, 3, -4), null));
		System.out.println(testeurEgalite(new Case(5, 3, 4), null));
	}
	
	/**
	 * Teste si les deux param�tres ont la m�me valeur
	 * 
	 * @param aTester La valeur a tester
	 * @param resAttendu La valeur attendu comme r�sultat
	 * @return <ul><li>true si le test est r�ussi</li>
	 *             <li>false si le test �chou</li>
	 *         </ul>
	 */
	private static boolean testeurEgalite(Case aTester, Case resAttendu) {
		return aTester.equals(resAttendu);
	}

}
