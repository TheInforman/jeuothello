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
