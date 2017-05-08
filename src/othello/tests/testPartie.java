/*
 * testPartie.java                                            06/05/2017
 * Groupe  Adrien Bouyssou, Vincent Galinier,
 * 		   Kerian Georges, Arthur Pradier, Micka�l Queudet
 */
package othello.tests;

import othello.*;
import outils.*;


/**
 * Classe permettant de tester les diff�rentes m�thodes de la classe Partie
 * 
 * @author Vincent
 */
public class testPartie {

	/**
	 * M�thode main qui lance les tests.
	 * 
	 * @param args inutilis�
	 */
	public static void main(String[] args) {
		// Test primaire
		Joueur joueur1 = new Joueur("Joueur1", 0);
		Joueur joueur2 = new Joueur("Joueur2", 1);
		Partie partie = new Partie(joueur1, joueur2);
		
		System.out.println(partie);
		partie.tourSuivant();
		System.out.println(partie);
		
		// Test d�roulement partie 2 tour (appliquer action pas encore prog)
		Joueur player1 = new Joueur("Test",0);
		Joueur player2 = new Joueur();
		Partie partieTest = new Partie(player1, player2);

		
		//faire la suite avec vincent car je pense qu'il faut modifier des m�thodes qu'il a �crit
		
	}

}
