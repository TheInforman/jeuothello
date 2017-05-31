/*
 * testJoueur.java                                            06/05/2017
 * Tous droits réservés à l'IUT de Rodez
 */
package othello.tests;

import othello.Joueur;

/**
 * Classe permettant de tester les différentes méthodes de la classe Joueur
 * 
 * @author Vincent Galinier
 * @author Adrien Bouyssou
 * @author Kerian Georges
 * @author Arthur Pradier
 * @author Mickaël Queudet 
 */
public class testJoueur {

	/**
	 * Méthode main qui lance les tests.
	 * 
	 * @param args inutilisé
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Joueur joueurTest = new Joueur("test", 0);
		
		System.out.println(joueurTest);

	}

}
