/*
 * testPartie.java                                            06/05/2017
 * Tous droits r�serv�s � l'IUT de Rodez
 */
package othello.tests;

import othello.*;
import outils.*;


/**
 * Classe permettant de tester les diff�rentes m�thodes de la classe Partie
 * 
 * @author Vincent Galinier
 * @author Adrien Bouyssou
 * @author Kerian Georges
 * @author Arthur Pradier
 * @author Micka�l Queudet 
 */
public class testPartie {

	/**
	 * M�thode main qui lance les tests.
	 * 
	 * @param args inutilis�
	 */
	public static void main(String[] args) {
		testPartieJouable();
	}

	private static void testPartieJouable() {
		// Test primaire
		Joueur joueur1 = new Joueur("Joueur1", 0);
		Joueur joueur2 = new Joueur("Joueur2", 1);
		Partie partie = new Partie(joueur1, joueur2, 0);

		System.out.println(partie);
		partie.tourSuivant();
		System.out.println(partie);

		System.out.println("\n\n\n\n\n\n\n");

		// Test d�roulement de 2 tour 
		Joueur player1 = new Joueur("Test",0);
		Joueur player2 = new Joueur(1);
		Partie partieTest = new Partie(player1, player2, 0);
		Plateau courant = partieTest.getPlateau();

		// Fais jouer un tour au joueur un
		courant.determinerCoupsPossibles(0);
		System.out.println(partieTest.getPlateau());

		while(partieTest.getTour() < 60 ) {
			while( courant.isActionEffectuee() == false ){
				courant.appliquerCoups(OutilsConsole.demanderCase(courant),
						partieTest.getListeJoueur()[partieTest.getDoitJouer()].getCouleur());
			}
			partieTest.tourSuivant();
			System.out.println(partieTest.getPlateau());
			courant.setActionEffectuee(false);
		}


		int nbBlanc = courant.calculerNbPions(0);
		int nbNoir = courant.calculerNbPions(1);
		System.out.println("Score : " + nbBlanc + " � " + nbNoir );
	}

}
