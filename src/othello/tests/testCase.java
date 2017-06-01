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
		System.out.print("La case par d�faut initilis� aux coordon�es "
				  + "-1 0 de couleur noire est correcte : ");
		System.out.println(caseCorrecte(new Case(-1, 0, 1)));
		
		System.out.print("La case par d�faut initilis� aux coordon�es "
				  + "1 -2 de couleur noire est correcte : ");
		System.out.println(caseCorrecte(new Case(1, -2, 1)));
		
		// test cr�ation de case avec des coordonn�es trop grandes
		System.out.print("La case par d�faut initilis� aux coordon�es "
				  + "9 5 de couleur noire est correcte : ");
		System.out.println(caseCorrecte(new Case(9, 5, 1)));
		
		System.out.print("La case par d�faut initilis� aux coordon�es "
				  + "4 10 de couleur noire est correcte : ");
		System.out.println(caseCorrecte(new Case(4, 10, 1)));
		
		// test cr�ation de case avec une couleur non-valide
		System.out.print("La case par d�faut initilis� aux coordon�es "
				  + "5 3 de couleur invalide est correcte : ");
		System.out.println(caseCorrecte(new Case(5, 3, -4)));
		
		System.out.print("La case par d�faut initilis� aux coordon�es "
				  + "5 3 de couleur invalide est correcte : ");
		System.out.println(caseCorrecte(new Case(5, 3, 4)));
		
		//test cr�ation de case avec des coordon�es valide et de chaque couleur
		System.out.print("La case par d�faut initilis� aux coordon�es "
				  + "5 3 de couleur noire  est correcte : ");
		System.out.println(caseCorrecte(new Case(5, 3, 1)));
		
		System.out.print("La case par d�faut initilis� aux coordon�es "
				  + "5 3 de couleur blanche est correcte : ");
		System.out.println(caseCorrecte(new Case(5, 3, 0)));
		
		System.out.print("La case par d�faut initilis� aux coordon�es "
				  + "5 3 de couleur neutre est correcte : ");
		System.out.println(caseCorrecte(new Case(5, 3, -1)));
		
		//test cr�ation d'une case avec la couleur par d�faut
		System.out.print("La case par d�faut initilis� aux coordon�es "
						  + "x y est correcte : ");
		System.out.println(caseCorrecte(new Case(5, 3)));
	}
	
	/**
	 * Teste si les deux param�tres ont la m�me valeur
	 * 
	 * @param aTester La valeur a tester
	 * @param resAttendu La valeur attendu comme r�sultat
	 * @return <ul><li>true si le test est r�ussi</li>
	 *             <li>false si le test �choue</li>
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
