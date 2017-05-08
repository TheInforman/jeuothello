/*
 * testPlateau.java                                            06/05/2017
 * Groupe  Adrien Bouyssou, Vincent Galinier, Kerian Georges, Arthur Pradier, Mickaël Queudet
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
		
		/*
		System.out.println("Le joueur noir peut poser ses pions en : ");
		
		Case[] tableauCoupsPossibles = plateau.determinerCoupsPossibles(Case.COULEUR_NOIR);
		for (int i = 0; i < tableauCoupsPossibles.length; i++) {
			if (tableauCoupsPossibles[i] != null) {
				System.out.println(tableauCoupsPossibles[i]);
			}
		}*/
		
		System.out.println("\n");
		
		plateau.tablier[5][4].setCouleur(Case.COULEUR_BLANC);

		System.out.println(plateau);
		
		System.out.println("Le joueur noir peut poser ses pions en : ");
		
		Case[] tableauCoupsPossibles = plateau.determinerCoupsPossibles(Case.COULEUR_NOIR);
		for (int i = 0; i < tableauCoupsPossibles.length; i++) {
			if (tableauCoupsPossibles[i] != null) {
				System.out.println(tableauCoupsPossibles[i]);
			}
		}
		
		/*
		System.out.println(plateau.aUnePaire(plateau.tablier[2][2], Case.COULEUR_NOIR));
		System.out.println(plateau.aUnePaire(plateau.tablier[2][3], Case.COULEUR_NOIR));
		System.out.println(plateau.aUnePaire(plateau.tablier[2][4], Case.COULEUR_NOIR));
		System.out.println(plateau.aUnePaire(plateau.tablier[2][5], Case.COULEUR_NOIR));
		
		System.out.println(plateau.aUnePaire(plateau.tablier[2][2], Case.COULEUR_BLANC));
		System.out.println(plateau.aUnePaire(plateau.tablier[2][3], Case.COULEUR_BLANC));
		System.out.println(plateau.aUnePaire(plateau.tablier[2][4], Case.COULEUR_BLANC));
		System.out.println(plateau.aUnePaire(plateau.tablier[2][5], Case.COULEUR_BLANC));
		*/
		
	}

}
