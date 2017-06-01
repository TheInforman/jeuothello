/* Scores.java		29/05/2017
 * Tous droits r�serv�s � l'IUT de Rodez
 */
package othello;

import java.io.Serializable;
import java.util.Calendar;
import outils.OutilFichier;

/** 
 * Gestion des 6 derniers scores des gagnants d'une partie. 
 * Les scores sont d�finis par le nom du joueur gagnant, son score
 * et la date � laquelle il a gagn� 
 * 
 * @author Vincent Galinier
 * @author Adrien Bouyssou
 * @author Kerian Georges
 * @author Arthur Pradier
 * @author Micka�l Queudet 
 */
public class Scores implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String[][] tableauScore;
	
	/**
	 * Constructeur d'etat d'instance. Cr�ation d'un tableau vide aux 
	 * dimensions 6*3 auquel on ajoutera nos valeurs
	 */
	public Scores() {
		tableauScore = new String[6][3];
	}

	/**
	 * getter de l'objet scores
	 * @return le tableau � deux dimensions contenant les scores
	 */
	public String[][] getScore() {
		return tableauScore;
	}
	
	/**
	 * Ajoute le r�sultat d'une partie � l'historique des 
	 * scores qui contient au maximum les 6 derniers scores
	 * @param pseudo du joueur ayant r�aliser le score
	 * @param le score en tant que String
	 */
	public void ajoutScore(String pseudo, String score) {
		
		// d�termine si on a ou non ins�r� un score dans le tableau
		boolean scoreInsere = false; 
		
		//on ajoute un score dans la premi�re case vide disponible
		for(int i= 0; i < tableauScore.length; i++) { 
			if (tableauScore[i] == null) {
				tableauScore[i][0] = pseudo;
				tableauScore[i][1] = score;
				tableauScore[i][2] = determinerDate();
			}
		}
		/* si on a pas ins�r� de score apr�s le balayage, 
		 * on supprime  l'�l�ment le plus � gauche et on d�cale 
		 * tous les �l�ments du tableau vers la gauche, puis on 
		 * ins�re la nouvelle valeur dans la case la plus � droite
		 */
		if (!scoreInsere) {
			for(int i=tableauScore.length -1; i > 0 ; i--) {
				tableauScore[i][0] = tableauScore[i-1][0];
				tableauScore[i][1] = tableauScore[i-1][1];
				tableauScore[i][2] = tableauScore[i-1][2];
			}
			tableauScore[0][0] = pseudo;
			tableauScore[0][1] = score;

			tableauScore[0][2] = determinerDate();
		}
		// D�s que le score est retourn�, on enregistre le score courant (this)
		OutilFichier.enregistrerScores(this);
	}
	
	
	/** D�termine la date o� l'on a ins�r� le score sous la forme:
	 * jj/mm/aaaa � hhhmm
	 * @return la date de la sauvegarde
	 */
	private String determinerDate(){
		// R�cup�ration de la date 
		Calendar calendrierMaintenant = Calendar.getInstance();
		int heure = calendrierMaintenant.get(Calendar.HOUR_OF_DAY);
		int minute = calendrierMaintenant.get(Calendar.MINUTE);
		int jour = calendrierMaintenant.get(Calendar.DAY_OF_MONTH);
		int mois = calendrierMaintenant.get(Calendar.MONTH) + 1;
		int annee = calendrierMaintenant.get(Calendar.YEAR);


		return ((jour < 10) ? "0" + String.valueOf(jour) : String.valueOf(jour))
				+ "/" + ((mois < 10) ? "0" + String.valueOf(mois) : String.valueOf(mois))
				+ "/" + annee + " � "
				+ heure + "h" +
		((minute < 10) ? "0" + String.valueOf(minute) : String.valueOf(minute));
		
	}

	
}
