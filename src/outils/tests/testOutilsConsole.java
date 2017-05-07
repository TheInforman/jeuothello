/*
 * OutilsConsole.java                                            06/05/2017
 * Groupe  Adrien Bouyssou, Vincent Galinier, Kerian Georges, Arthur Pradier, Micka�l Queudet
 */

package outils.tests;

import static outils.OutilsConsole.*;

import othello.Plateau;

/**
 * Tests sur la m�thode outilsConsole
 * @author Vincent
 */
public class testOutilsConsole {

	/**
	 * M�thode principale
	 * @param args inutilis�
	 */
	public static void main(String[] args) {
		//testDemanderCase();
		testDemanderNom();
	}
	
	public static void testDemanderCase() {
		Plateau plateau = new Plateau();
		
		for (int nbIteration = 5; nbIteration != 0 ; nbIteration--) {
			System.out.println(demanderCase(plateau));
		}
	}
	
	public static void testDemanderNom() {
		for (int nbIteration = 5; nbIteration != 0 ; nbIteration--) {
			System.out.println("Nom retourn� : " + demanderNom());
		}
	}
}
