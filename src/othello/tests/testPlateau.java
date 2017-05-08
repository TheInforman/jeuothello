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

	/**
	 * Méthode main qui lance les tests.
	 * 
	 * @param args inutilisé
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
