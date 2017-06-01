package othello.tests;

import othello.Scores;

public class testScores {

	/**
	 * Tests unitaires de la classe score
	 * @param args
	 */
	public static void main(String[] args) {
		Scores lesScores = new Scores();
		/*
		lesScores.ajoutScore("Test", "25", "30/05/2015" );
		lesScores.ajoutScore("Coucou", "30", "30/05/2015" );
		lesScores.ajoutScore("Roger", "12", "30/05/2015" );
		lesScores.ajoutScore("Pierre", "35", "30/05/2015" );
		lesScores.ajoutScore("Jean-Yves", "28", "30/05/2015" );
		lesScores.ajoutScore("Maurice", "29", "30/05/2015" );
		
		// à l'insertion de ce 7eme élément, on obtient bien le résultat voulu
		lesScores.ajoutScore("Dernier", "14", "30/05/2015" ); 
*/
		String[][] test = lesScores.getScore();
		
		for(int i=0; i < test.length ; i++) {
			for(int j=0; j < test[i].length ; j++) {
				System.out.println(test[i][j]);
			}
		}
		
	}

}
