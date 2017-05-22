package Maquette.fenetres;


import Maquette.Main;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import othello.Case;
import othello.Joueur;
import othello.Partie;
import othello.Plateau;
import outils.OutilsConsole;


public class PlateauController {


	public static Joueur player1 = new Joueur("Test",0);
	public static Joueur player2 = new Joueur();
	public static Partie partieTest = new Partie(player1, player2);
	public static Plateau courant = partieTest.getPlateauDeJeu();
	
	@FXML
	public GridPane grid;
	
	public void initialize() {
		int numCols = 8;
		int numRows = 8 ;

		for (int i = 0 ; i < numCols ; i++) {
			ColumnConstraints colConstraints = new ColumnConstraints();
			colConstraints.setHgrow(Priority.SOMETIMES);
			grid.getColumnConstraints().add(colConstraints);
		}

		for (int i = 0 ; i < numRows ; i++) {
			RowConstraints rowConstraints = new RowConstraints();
			rowConstraints.setVgrow(Priority.SOMETIMES);
			grid.getRowConstraints().add(rowConstraints);
		}
		for (int i = 0 ; i < numCols ; i++) {
			for (int j = 0; j < numRows; j++) {
				addPane(i,j);				
			}
		
		}
	}

	
	public void addPane(int colIndex, int rowIndex) {
		Pane pane = new Pane();	
		
		
		pane.setOnMouseClicked(e -> {
			System.out.printf("Case cliquée : [%d, %d]%n", colIndex, rowIndex);	
			// Fais jouer un tour au joueur un
			courant.determinerCoupsPossibles(0);//initialisation
			System.out.println(partieTest.getPlateauDeJeu());
			//TODO boucle permettant de déterminer si la partie est terminée ou non
			//TODO boucle pour s'assurer qu'un joueur a bien joué son tour 
			courant.appliquerCoups(courant.othellier[rowIndex][colIndex],
						  		   partieTest.getListeJoueur()[partieTest.getDoitJouer()].getCouleur());
			if (courant.isActionEffectuer() == true){			
			partieTest.tourSuivant();
			}
			System.out.println(partieTest.getPlateauDeJeu());
			courant.setActionEffectuer(false);
			System.out.println(partieTest.getDoitJouer());
				
			//calcul du score
			int nbBlanc = courant.calculerNbPions(0);
			int nbNoir = courant.calculerNbPions(1);
			System.out.println("Score : " + nbBlanc + " à " + nbNoir );
			if(partieTest.getTour() == 2) {
				Main.showRecapitulatif();
			}
				
		});
		
		grid.add(pane, colIndex, rowIndex);		
	}
	

}