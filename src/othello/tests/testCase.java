/*
 * testCase.java                                            06/05/2017
 * Groupe  Adrien Bouyssou, Vincent Galinier,
 * 		   Kerian Georges, Arthur Pradier, Mickaël Queudet
 * 	Juste pour tester
 */
package othello.tests;

import othello.Case;

/**
 * Classe permettant de tester les différentes méthodes de la classe Case
 * 
 * @author Vincent
 */
public class testCase {

	/**
	 * Méthode main qui lance les tests.
	 * 
	 * @param args inutilisé
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Case caseNeutre = new Case(1,1,-1);
		Case caseBlanche = new Case(1,2,0);
		Case caseNoire = new Case(1,3,1);
		Case caseIncorrect = new Case(1,4,5);
		
		System.out.println(caseNeutre);
		System.out.println(caseBlanche);
		System.out.println(caseNoire);
		System.out.println(caseIncorrect);

		
	}

}
