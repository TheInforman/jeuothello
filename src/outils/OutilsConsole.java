/*
 * OutilsConsole.java                                            06/05/2017
 * Groupe  Adrien Bouyssou, Vincent Galinier, Kerian Georges, Arthur Pradier, Micka�l Queudet
 */

package outils;

import java.util.Scanner;


import othello.Case;
import othello.Plateau;

/**
 * Ensemble d'outils permettant de r�cup�rer les intentions de l'utilisateur.
 * Cette classe est vou�e � �tre remplac�e par une interface graphique dans le futur.
 * 
 * @author Vincent G.
 */
public class OutilsConsole {
	
	/** Objet Scanner pour permettre � l'utlisateur d'effectuer des saisies */
	private static Scanner clavier = new Scanner(System.in);
	
	/**
	 * Demande le nom de l'utilisateur
	 * @return		le nom choisi par l'utilisateur
	 */
	public static String demanderNom() {
		String reponse = "";
		
		while(reponse == "") {
			System.out.print("Entrez votre nom : ");
			reponse = clavier.nextLine();
			if (!nomValide(reponse)) {
				System.out.println("Ce nom n'est pas valide");
				reponse = "";
			}
		}		
		return reponse;
	}
	
	/**
	 * V�rifier si le nom sp�cifi� en param�tre est valide
	 * @param aVerifier		le nom que l'on souhaite v�rifier
	 * @return
	 */
	private static boolean nomValide(String aVerifier) {
		if (aVerifier.length() == 0 || aVerifier.length() > 16){
			return false;
		}
		return true;
	}
	
	/**
	 * Demande une ligne et une colonne � l'utilisateur
	 * @param plateauCourant	le plateau sur lequel se d�roule
	 * 							la partie
	 * @return		la case choisie par l'utilisateur
	 */
	public static Case demanderCase(Plateau plateauCourant) {
		/* Demande de la ligne */
		int ligne = -1;
		while(ligne == -1) {
			System.out.print("Entrez un num�ro de ligne : ");
			ligne = clavier.nextInt();
			if (ligne < 0 || ligne > Plateau.HAUTEUR ) {
				System.out.println("Ce num�ro n'est pas valide");
				ligne = -1;
			}
			clavier.nextLine();
		}
		
		/* Demande de la colonne */
		int colonne = -1;
		while(colonne == -1) {
			System.out.print("Entrez un num�ro de colonne : ");
			colonne = clavier.nextInt();
			if (colonne < 0 || colonne > Plateau.LARGEUR ) {
				System.out.println("Ce num�ro n'est pas valide");
				colonne = -1;
			}
			clavier.nextLine();
		}
		
		return plateauCourant.tableauCase[ligne][colonne];
	}
}
