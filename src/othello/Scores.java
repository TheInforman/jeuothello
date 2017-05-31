/* Scores.java		29/05/2017
 * IUT Rodez 
 */
package othello;

/** 
 * Gestion des 6 derniers scores des gagnants d'une partie. 
 * Les scores sont d�finis par le nom du joueur gagnant, son score
 * et la date � laquelle il a gagn� 
 * @author Arthur
 *
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
		
		for(int i= 0; i < tableauScore.length -1; i++) { //on ajoute un score dans la premi�re case vide disponible
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
			
			for(int i=0; i < tableauScore.length -1 ; i++) {
				tableauScore[i][0] = tableauScore[i+1][0];
				tableauScore[i][1] = tableauScore[i+1][1];
				tableauScore[i][2] = tableauScore[i+1][2];
			}
			tableauScore[5][0] = pseudo;
			tableauScore[5][1] = score;
			tableauScore[5][2] = date;
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
