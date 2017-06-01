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


		/* Test d'une partie en console */
		Joueur player1 = new Joueur("Test",0);
		Joueur player2 = new Joueur(1);
		Partie partieTest = new Partie(player2, player1, 0);
		Plateau courant = partieTest.getPlateau();
		
		/* boolean g�rant la fin de la partie */
		boolean partieEnCours = true;
		
		/* nombre de pion blanc et noir sous le contr�le des joueurs */
		int nbBlanc = 0;
		int nbNoir = 0;
		
		/* on d�termine les coups possibles pour le premier joueur */
		courant.determinerCoupsPossibles(partieTest.getDoitJouer());
		System.out.println(partieTest.getPlateau());
		
		// boucle de jeu d'une partie console
		while(partieEnCours) {
			/* test si les coordon�es entr�es sont correct, si oui applique 
			   le coup, sinon continue � demander des coordon�es */
			while( courant.isActionEffectuee() == false ){
				courant.appliquerCoups(OutilsConsole.demanderCase(courant),
						partieTest.getListeJoueur()[partieTest.getDoitJouer()]
								.getCouleur());
			}
			/* passe au tour suivant */
			partieTest.tourSuivant();
			
			/* affiche le plateau et les coups possibles */
			System.out.println(partieTest.getPlateau());
			
			/* r�initialise la condition pour poser les pions */
			courant.setActionEffectuee(false);
			
			/* test si le joueur peut jouer (si il existe des coups possibles)
			 s'il n'en existe pas, on passe le tour */
			if(partieTest.getPlateau().getCoupsPossibles().isEmpty()) {
				/* R�cup�re le pseudo du joueur jouant le tour actuel */
				String pseudoJoueur =
						partieTest.getListeJoueur()
						[partieTest.getDoitJouer()].getNom();
				/* on passe au tour suivant */
				partieTest.tourSuivant();

				/* Si deux fois d'affiler on ne peut pas jouer, on finit la partie
				   sinon on affiche un message comme quoi le joueur pr�c�dent
				    n'a pas pu jouer puis on affiche le plateau et les coups possibles */
				if(partieTest.getPlateau().getCoupsPossibles().isEmpty()) {
					partieEnCours = false;
				} else {
					System.out.println("Notification de Partie\n"
							+"Le tour de "+ pseudoJoueur + " �  �t� pass� "
							+ "car il ne pouvait pas agir.");
					System.out.println(partieTest.getPlateau());

				}

			}

			/* calcul le score */
			nbBlanc = courant.calculerNbPions(0);
			nbNoir = courant.calculerNbPions(1);
			System.out.println("Score : " + nbBlanc + " � " + nbNoir );
		}
		/* affiche le score final */
		System.out.println("\n\n\nScore final : " + nbBlanc + " � " + nbNoir );
	}
}
