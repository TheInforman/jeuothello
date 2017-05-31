package Maquette.fenetres;


import Maquette.Main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import othello.Joueur;
import othello.Partie;
import othello.Plateau;
import outils.OutilFichier;


public class PlateauController {
	
	/** Image associée à une case noire */
	private static Image caseNoire =
			new Image("file:src/Maquette/Ressource/Jeton1.png");
	
	/** Image associée à une case blanche */
	private static Image caseBlanche =
			new Image("file:src/Maquette/Ressource/Jeton0.png");
	
	public static Partie partieCourante;
	
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
	
	@FXML
	public Button btn_sauvegarder;
	
	/**
	 * TODO : JDOC
	 */
	public void initialize() {

		Plateau plateauCourant = partieCourante.getPlateau();
		
		if (partieCourante.getListeJoueur()[0].getCouleur() == 0) {
			lbl_blanc.setText(partieCourante.getListeJoueur()[0].getNom());
			lbl_noir.setText(partieCourante.getListeJoueur()[1].getNom());
		} else {
			lbl_blanc.setText(partieCourante.getListeJoueur()[1].getNom());
			lbl_noir.setText(partieCourante.getListeJoueur()[0].getNom());
		}
		
		int numCols = Plateau.LARGEUR;
		int numRows = Plateau.HAUTEUR;
		
		
		lbl_scoreBlanc.setText(String.valueOf(plateauCourant.calculerNbPions(0)));
		lbl_scoreNoir.setText(String.valueOf(plateauCourant.calculerNbPions(1)));
		
		plateauCourant.determinerCoupsPossibles(partieCourante.getDoitJouer());

		
		System.out.println(plateauCourant);
		updateTableau(grid);
		setQuiDoitJouer(partieCourante.getDoitJouer());
		
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

	
	/**
	 * TODO : JDOC
	 */
	public void addPane(int colIndex, int rowIndex) {
		Pane pane = new Pane();	

		pane.setOnMouseClicked(e -> {
			System.out.printf("Case cliquée : [%d, %d]%n", colIndex, rowIndex);	

			
			// Fais jouer un tour au joueur un
			partieCourante.getPlateau().appliquerCoups(partieCourante.getPlateau().othellier[rowIndex][colIndex],
					partieCourante.getListeJoueur()[partieCourante.getDoitJouer()].getCouleur());
			
			//Le joueur courant reste le même tant que son coup n'est pas valide
			if (partieCourante.getPlateau().isActionEffectuer() == true){	
				
				partieCourante.tourSuivant();
				//mise à jour du tableau
				updateTableau(grid);	
				
			}

			System.out.println(partieCourante.getPlateau());
			
			partieCourante.getPlateau().setActionEffectuer(false);
			
			//calcul du score
			int nbBlanc = partieCourante.getPlateau().calculerNbPions(0);
			int nbNoir = partieCourante.getPlateau().calculerNbPions(1);
			
			System.out.println(partieCourante.getDoitJouer());
			// souligne le joueur qui doit jouer 
			setQuiDoitJouer(partieCourante.getDoitJouer());
			
			// actualise le score actuel de la partie
			changerScore(nbBlanc, nbNoir);
			
			if(partieCourante.getTour() == 60) {
				afficherRecapitulatif(nbBlanc, nbNoir);
			}

		});

		grid.add(pane, colIndex, rowIndex);		
	}
	
	/**
	 * TODO : JDOC
	 */
	public static void updateTableau(GridPane grid) {
		
		for (int i =0; i<8; i++) {
			for (int j=0; j<8; j++) {
				
				switch (partieCourante.getPlateau().othellier[i][j].getCouleur()) {
					
					case 1 : ImageView Noir = new ImageView(caseNoire);
									  /* Noir.setFitHeight(28);
									   Noir.setFitWidth(28);
									   Noir.setTranslateX(2);
									   Noir.setTranslateY(2);*/
									   grid.add(Noir, j, i);
									   break;
									   
					case 0 : ImageView Blanc = new ImageView(caseBlanche);
									   /*Blanc.setFitHeight(28);
									   Blanc.setFitWidth(28);
									   Blanc.setTranslateX(2);
									   Blanc.setTranslateY(2);*/
									   grid.add(Blanc, j, i);
									   break;
				
				}
			}
		}
	}
	
	
	/**
	 * TODO : JDOC
	 */
	public void changerScore(int nbBlanc, int nbNoir){
		lbl_scoreBlanc.setText(String.valueOf(nbBlanc));
		lbl_scoreNoir.setText(String.valueOf(nbNoir));
	}
	
	/**
	 * TODO : JDOC
	 */
	public void setQuiDoitJouer(int joueur){
		if (joueur == 0){ 
			lbl_blanc.setUnderline(true);
			lbl_noir.setUnderline(false);
		} else{
			lbl_blanc.setUnderline(false);
			lbl_noir.setUnderline(true);
		}
	}
	
	/**
	 * TODO : JDOC
	 */
	public static void initPartie(String pseudo_J1, String pseudo_J2){
		
		if (Math.random() > 0.5){
			
			partieCourante = new Partie(
					new Joueur(pseudo_J1, 0),
					new Joueur(pseudo_J2, 1)
					);			
		} else {
			
			partieCourante = new Partie(
					new Joueur(pseudo_J2, 0),
					new Joueur(pseudo_J1, 1)
					);
		}
	}
	
	/**
	 * TODO : JDOC
	 */
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
	
	/**
	 * TODO : JDOC
	 */
	@FXML
	private void enregistrerPartie() {
		System.out.println("Enregistrement de la partie");
		
		if (!OutilFichier.isRepertoireOthelloExistant()) {
    		System.out.println("Le répertoire Othello n'existe pas");
    		boolean repertoireCree = OutilFichier.creerRepertoireOthello();
    		if (repertoireCree) {
    			System.out.println("Répertoire créé avec succès");
    		} else {
    			System.out.println("Le répertoire n'a pas pu être créé"
    					+ " à l'emplacement "
    					+ OutilFichier.getRepertoireParDefaut());
    			return;
    		}
    	}
		
		OutilFichier.enregistrerPartie(partieCourante);
		//TODO : Quitter
	}
	
	/**
	 * TODO : JDOC
	 * TODO : Faire tout tourner autour de la partie
	 */
	public static void restaurerPartie(Partie aRestaurer){
		partieCourante = aRestaurer;
	}
}