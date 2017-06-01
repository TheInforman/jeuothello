/* Scores.java		29/05/2017
 * Tous droits réservés à l'IUT de Rodez
 */
package othello;

/** 
 * Gestion des 6 derniers scores des gagnants d'une partie. 
 * Les scores sont définis par le nom du joueur gagnant, son score
 * et la date à laquelle il a gagné 
 * 
 * @author Vincent Galinier
 * @author Adrien Bouyssou
 * @author Kerian Georges
 * @author Arthur Pradier
 * @author Mickaël Queudet 
 */
public class Scores {
	
	String[][] tableauScore;
	
	/**
	 * Constructeur d'etat d'instance. Création d'un tableau vide aux 
	 * dimensions 6*3 auquel on ajoutera nos valeurs
	 */
	public Scores() {
		tableauScore = new String[6][3];
	}

	public void ajoutScore(String pseudo, String score, String date) {
		
		boolean scoreInsere = false; // détermine si on a ou non inséré un score dans le tableau
		
		for(int i= 0; i < tableauScore.length; i++) { //on ajoute un score dans la première case vide disponible
			if (tableauScore[i] == null) {
				tableauScore[i][0] = pseudo;
				tableauScore[i][1] = score;
				tableauScore[i][2] = date;
				scoreInsere = true;
			}
		}
		/* si on a pas inséré de score après le balayage, on supprime l'élément le plus à gauche et on décale 
		 * tous les éléments du tableau vers la gauche, puis on insère la nouvelle valeur dans la case la plus à droite
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
	 * @return le tableau à deux dimensions contenant les scores
	 */
	public String[][] getScore() {
		return tableauScore;
	}

	
}
