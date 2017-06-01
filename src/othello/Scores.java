/* Scores.java		29/05/2017
 * Tous droits r�serv�s � l'IUT de Rodez
 */
package othello;

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
public class Scores {
	
	String[][] tableauScore;
	
	/**
	 * Constructeur d'etat d'instance. Cr�ation d'un tableau vide aux 
	 * dimensions 6*3 auquel on ajoutera nos valeurs
	 */
	public Scores() {
		tableauScore = new String[6][3];
	}

	public void ajoutScore(String pseudo, String score, String date) {
		
		boolean scoreInsere = false; // d�termine si on a ou non ins�r� un score dans le tableau
		
		for(int i= 0; i < tableauScore.length; i++) { //on ajoute un score dans la premi�re case vide disponible
			if (tableauScore[i] == null) {
				tableauScore[i][0] = pseudo;
				tableauScore[i][1] = score;
				tableauScore[i][2] = date;
				scoreInsere = true;
			}
		}
		/* si on a pas ins�r� de score apr�s le balayage, on supprime l'�l�ment le plus � gauche et on d�cale 
		 * tous les �l�ments du tableau vers la gauche, puis on ins�re la nouvelle valeur dans la case la plus � droite
		 */
		if (!scoreInsere) {
			
			for(int i=tableauScore.length -1; i > 0 ; i--) {
				tableauScore[i][0] = tableauScore[i-1][0];
				tableauScore[i][1] = tableauScore[i-1][1];
				tableauScore[i][2] = tableauScore[i-1][2];
			}
			tableauScore[0][0] = pseudo;
			tableauScore[0][1] = score;
			tableauScore[0][2] = date;
		}
		
	}
	
	/**
	 * getteur de l'objet scores
	 * @return le tableau � deux dimensions contenant les scores
	 */
	public String[][] getScore() {
		return tableauScore;
	}

	
}
