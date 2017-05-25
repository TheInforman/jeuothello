package Maquette.fenetres;


import Maquette.Main;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import othello.Case;
import othello.Joueur;
import othello.Partie;
import othello.Plateau;


public class PlateauController {

	/** Image associée à une case vide */
	private static Image caseVide = new Image("file:///C:/Users/Pierre/Documents/Travail/Info/Java/workspace/MaquetteOthello1/src/Maquette/Ressource/Jeton-1.png");
	
	/** Image associée à une case noire */
	private static Image caseNoire = new Image("file:///C:/Users/Pierre/Documents/Travail/Info/Java/workspace/MaquetteOthello1/src/Maquette/Ressource/Jeton0.png");
	
	/** Image associée à une case blanche */
	private static Image caseBlanche = new Image("file:///C:/Users/Pierre/Documents/Travail/Info/Java/workspace/MaquetteOthello1/src/Maquette/Ressource/Jeton1.png");
	
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
			colConstraints.setHgrow(Priority.NEVER);
			grid.getColumnConstraints().add(colConstraints);
		}

		for (int i = 0 ; i < numRows ; i++) {
			RowConstraints rowConstraints = new RowConstraints();
			rowConstraints.setVgrow(Priority.NEVER);
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
			courant.appliquerCoups(courant.othellier[rowIndex][colIndex],
						  		   partieTest.getListeJoueur()[partieTest.getDoitJouer()].getCouleur());
			if (courant.isActionEffectuer() == true){	//Le joueur courant reste le même tant que son coup n'est pas valide
				partieTest.tourSuivant();
				//mise à jour du tableau*/
				updateTableau(grid);	
			}
			
			System.out.println(partieTest.getPlateauDeJeu());
			courant.setActionEffectuer(false);
			System.out.println(partieTest.getDoitJouer());
			//calcul du score
			int nbBlanc = courant.calculerNbPions(0);
			int nbNoir = courant.calculerNbPions(1);
			System.out.println("Score : " + nbBlanc + " à " + nbNoir );
			if(partieTest.getTour() == 60) {
				Main.showRecapitulatif();
			}
				
		});
		
		grid.add(pane, colIndex, rowIndex);		
	}
	
	public static void updateTableau(GridPane grid) {
		
		for (int i =0; i<8; i++) {
			for (int j=0; j<8; j++) {
				
				switch (courant.othellier[i][j].getCouleur()) {
					
					case 1 : ImageView Noir = new ImageView(caseNoire);
									   Noir.setFitHeight(28);
									   Noir.setFitWidth(28);
									   Noir.setTranslateX(2);
									   Noir.setTranslateY(2);
									   grid.add(Noir, j, i);
									   break;
									   
					case 0 : ImageView Blanc = new ImageView(caseBlanche);
									   Blanc.setFitHeight(28);
									   Blanc.setFitWidth(28);
									   Blanc.setTranslateX(2);
									   Blanc.setTranslateY(2);
									   grid.add(Blanc, j, i);
									   break;
				
				}
			}
		}
	}
}