/*
 * testPlateau.java                                            06/05/2017
 * Groupe  Adrien Bouyssou, Vincent Galinier,
 * 		   Kerian Georges, Arthur Pradier, Micka�l Queudet
 */
package othello.tests;

import othello.Case;
import othello.Plateau;

/**
 * Classe permettant de tester les diff�rentes m�thodes de la classe Plateau
 * 
 * @author Vincent
 */
public class testPlateau {

	/**
	 * M�thode main qui lance les tests.
	 * 
	 * @param args inutilis�
	 */
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		Plateau plateau = new Plateau();
		System.out.println(plateau);

		plateau.appliquerCoups(plateau.othellier[5][4], Case.COULEUR_NOIR);
		System.out.println(plateau);
		
		plateau.appliquerCoups(plateau.othellier[5][5], Case.COULEUR_BLANC);
		System.out.println(plateau);
		
	}

}
