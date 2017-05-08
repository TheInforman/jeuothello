/*
 * testPartie.java                                            06/05/2017
 * Groupe  Adrien Bouyssou, Vincent Galinier,
 * 		   Kerian Georges, Arthur Pradier, Mickaël Queudet
 */
package othello.tests;

import othello.*;
import outils.*;


/**
 * Classe permettant de tester les différentes méthodes de la classe Partie
 * 
 * @author Vincent
 */
public class testPartie {

	/**
	 * Méthode main qui lance les tests.
	 * 
	 * @param args inutilisé
	 */
	public static void main(String[] args) {
		// Test primaire
		Joueur joueur1 = new Joueur("Joueur1", 0);
		Joueur joueur2 = new Joueur("Joueur2", 1);
		Partie partie = new Partie(joueur1, joueur2);
		
		System.out.println(partie);
		partie.tourSuivant();
		System.out.println(partie);
		
		// Test déroulement partie 2 tour (appliquer action pas encore prog)
		Joueur player1 = new Joueur("Test",0);
		Joueur player2 = new Joueur();
		Partie partieTest = new Partie(player1, player2);

		
		//faire la suite avec vincent car je pense qu'il faut modifier des méthodes qu'il a écrit
		
	}

}
