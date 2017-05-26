package Maquette.fenetres;


import Maquette.Main;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
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
	private static Image caseVide = new Image("file:src/Maquette/Ressource/Jeton-1.png");
	
	/** Image associée à une case noire */
	private static Image caseNoire = new Image("file:src/Maquette/Ressource/Jeton1.png");
	
	/** Image associée à une case blanche */
	private static Image caseBlanche = new Image("file:src/Maquette/Ressource/Jeton0.png");
	
	/** Pseudo du joueur 1 */
	public static String pseudoJ1;
	
	/** Pseudo du joueur 2 */
	public static String pseudoJ2;
	
	public static Joueur player1 = new Joueur(pseudoJ1,0);
	public static Joueur player2 = new Joueur(pseudoJ2,1);
	
	public static Partie partieTest = new Partie(player1, player2);
	public static Plateau courant = partieTest.getPlateauDeJeu();
	 
	@FXML
	public GridPane grid;
	
	@FXML 
	public Label lbl_scoreBlanc;
	
	@FXML
	public Label lbl_scoreNoir;
	
	@FXML
	public Label lbl_blanc;
	
	@FXML
	public Label lbl_noir;
	
	// TODO: Rappel de la couleur du joueur et tirage aléatoire
	public void initialize() {
		int numCols = Plateau.LARGEUR;
		int numRows = Plateau.HAUTEUR;
		lbl_scoreBlanc.setText(String.valueOf(2));
		lbl_scoreNoir.setText(String.valueOf(2));
		determinerBlancs();
		courant.determinerCoupsPossibles(partieTest.getDoitJouer());//initialisation
		
		System.out.println(partieTest.getPlateauDeJeu());
		updateTableau(grid);
		setQuiDoitJouer(partieTest.getDoitJouer());
		
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
			setQuiDoitJouer(partieTest.getDoitJouer());
			// Fais jouer un tour au joueur un
			courant.appliquerCoups(courant.othellier[rowIndex][colIndex],
						  		   partieTest.getListeJoueur()[partieTest.getDoitJouer()].getCouleur());
			if (courant.isActionEffectuer() == true){	//Le joueur courant reste le même tant que son coup n'est pas valide
				partieTest.tourSuivant();
				//mise à jour du tableau
				updateTableau(grid);	
			}
			
			System.out.println(partieTest.getPlateauDeJeu());
			courant.setActionEffectuer(false);
			//calcul du score
			int nbBlanc = courant.calculerNbPions(0);
			int nbNoir = courant.calculerNbPions(1);
			System.out.println(partieTest.getDoitJouer());
			setQuiDoitJouer(partieTest.getDoitJouer());
			System.out.println("Score : " + nbBlanc + " à " + nbNoir );
			changerScore(nbBlanc, nbNoir);
			if(partieTest.getTour() == 60) {
				afficherRecapitulatif(nbBlanc, nbNoir);

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
	
	public static void debutPartie() {
		courant.determinerCoupsPossibles(partieTest.getDoitJouer());//initialisation
		System.out.println(partieTest.getPlateauDeJeu());
	}
	
	public void changerScore(int nbBlanc, int nbNoir){
		lbl_scoreBlanc.setText(String.valueOf(nbBlanc));
		lbl_scoreNoir.setText(String.valueOf(nbNoir));
	}
	
	public void setQuiDoitJouer(int joueur){
		if (joueur == 0){ 
			lbl_blanc.setUnderline(true);
			lbl_noir.setUnderline(false);
		} else{
			lbl_blanc.setUnderline(false);
			lbl_noir.setUnderline(true);
		}
	}
	
	public static void setPseudos(String pseuJ1, String pseuJ2){
		pseudoJ1 = pseuJ1;
		pseudoJ2 = pseuJ2;
	}
	/*
	public void afficherPseudos(){
		lbl_blanc.setText(pseudoJ1);
		lbl_noir.setText(pseudoJ2);
	}
	*/
	public void determinerBlancs(){
		// boolean J1commence = (Math.random() > 0.5) ? true : false;
		// Si le nombre est supérieur à 0.5 alors le joueur 1 a les blancs
		if (Math.random() > 0.5){
			lbl_blanc.setText(pseudoJ1);
			lbl_noir.setText(pseudoJ2);
			
		} else {
			lbl_blanc.setText(pseudoJ2);
			lbl_noir.setText(pseudoJ1);
		}
		
	}
	
	public void afficherRecapitulatif(int scoreBlanc, int scoreNoir) {
		// 0 = blancs
		// 1 = noirs
		String pseudoGagnant;
		int scoreGagnant;
		int gagnant = (scoreBlanc > scoreNoir) ? 0 : 1;
		System.out.println(gagnant);
		if (gagnant == 0){
			pseudoGagnant = lbl_blanc.getText();
			scoreGagnant = scoreBlanc;
		}
		else{
			pseudoGagnant = lbl_noir.getText();
			scoreGagnant = scoreNoir;
		}
		/*
		RecapitulatifController.setRecapitulatif(pseudoGagnant, scoreGagnant);
		TODO: Linker les récapitulatifs
		*/
		Main.showRecapitulatif();
		
	} 
}