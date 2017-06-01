/*
 * testJoueur.java                                            06/05/2017
 * Tous droits r�serv�s � l'IUT de Rodez
 */
package othello.tests;

import othello.Joueur;

/**
 * Classe permettant de tester les diff�rentes m�thodes de la classe Joueur
 * 
 * @author Vincent Galinier
 * @author Adrien Bouyssou
 * @author Kerian Georges
 * @author Arthur Pradier
 * @author Micka�l Queudet 
 */
public class testJoueur {

	/**
	 * M�thode main qui lance les tests.
	 * 
	 * @param args inutilis�
	 */
	public static void main(String[] args) {
		
		System.out.println("\n-=-=-=-=- Test du constructeur de Joueur -=-=-=-=-\n");
		
		// test d'un joueur avec une couleur invalide
		System.out.println(testeurEgalite(new Joueur(-5), null));
		System.out.println(testeurEgalite(new Joueur(5), null));
		
		// test d'un joueur avec un nom invalide
		System.out.println(testeurEgalite(new Joueur(null, 1), null));
		System.out.println(testeurEgalite(new Joueur("", 1), null));
		
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
	private static boolean testeurEgalite(Joueur aTester, Joueur resAttendu) {
		return aTester.equals(resAttendu);
	}
}
