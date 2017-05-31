/* PlateauController		31/05/2017
 * info1 groupe Othello
 */
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

/**
 * Controller du plateau de jeu avec l'IA. A la différence de PlateauController, 
 * un seul joueur humain est présent. 
 * @author Arthur Pradier, Mickaël Queudet
 */
public class PlateauIAController {
	
	/** Image associée à une case noire */
	private static Image caseNoire =
			new Image("file:src/Maquette/Ressource/Jeton1.png");
	
	/** Image associée à une case blanche */
	private static Image caseBlanche =
			new Image("file:src/Maquette/Ressource/Jeton0.png");
	
	/** La partie actuelle */
	public static Partie partieCourante;
	
	/** la grille, partie visible du plateau, de taille 8*8 */
	@FXML
	public GridPane grid;
	
	/** Le score du joueur blanc */
	@FXML 
	public Label lbl_scoreBlanc;
	
	/** Le score du joueur noir */
	@FXML
	public Label lbl_scoreNoir;
	
	/** Le pseudo du joueur blanc */
	@FXML
	public Label lbl_blanc;
	
	/** Le pseudo du joueur noir */
	@FXML
	public Label lbl_noir;
	
	/** Bouton pour sauvegarder la partie actuelle au format.bin */
	@FXML
	public Button btn_sauvegarder;
	
	/**
	 * Méthode appelée après le chargement de la page 
	 */
	public void initialize() {

		Plateau plateauCourant = partieCourante.getPlateau();
		
		/* On détermine qui est le joueur blanc et qui est le joueur noir*/
		if (partieCourante.getListeJoueur()[0].getCouleur() == 0) {
			lbl_blanc.setText(partieCourante.getListeJoueur()[0].getNom());
			lbl_noir.setText(partieCourante.getListeJoueur()[1].getNom());
		} else {
			lbl_blanc.setText(partieCourante.getListeJoueur()[1].getNom());
			lbl_noir.setText(partieCourante.getListeJoueur()[0].getNom());
		}
		
		int numCols = Plateau.LARGEUR; //La largeur du plateau
		int numRows = Plateau.HAUTEUR; //La hauteur du plateau
		
		/* Calcule le score de chaque joueur, blanc puis noir */
		lbl_scoreBlanc.setText(String.valueOf(plateauCourant.calculerNbPions(0))); 
		lbl_scoreNoir.setText(String.valueOf(plateauCourant.calculerNbPions(1)));
		
		plateauCourant.determinerCoupsPossibles(partieCourante.getDoitJouer()); //init
		
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

		// Passage dans cette partie du code lorsque le joueur clique sur une case
		pane.setOnMouseClicked(e -> {
			
			System.out.printf("Case cliquée : [%d, %d]%n", colIndex, rowIndex);	//TODO suprimmer l'affichage console
			
			// Fais jouer un tour au joueur courant
			partieCourante.getPlateau().appliquerCoups(partieCourante.getPlateau().othellier[rowIndex][colIndex],
					partieCourante.getListeJoueur()[partieCourante.getDoitJouer()].getCouleur());
			
			//Le joueur courant reste le même tant que son coup n'est pas valide
			if (partieCourante.getPlateau().isActionEffectuer() == true){	
				partieCourante.tourSuivant(); //TODO méthode pour faire jouer son tour à l'ia avec une latence
				//mise à jour du tableau
				updateTableau(grid);	
			}

			System.out.println(partieCourante.getPlateau());
			partieCourante.getPlateau().setActionEffectuer(false);
			
			//calcul du score
			int nbBlanc = partieCourante.getPlateau().calculerNbPions(0);
			int nbNoir = partieCourante.getPlateau().calculerNbPions(1);
			
			setQuiDoitJouer(partieCourante.getDoitJouer());
			
			changerScore(nbBlanc, nbNoir); //mise à jour du score après le coup du joueur
			
			if(Plateau.coupsPossibles.isEmpty() ) {
				partieCourante.tourSuivant();
				System.out.println("TOUR PASSE");
				if(Plateau.coupsPossibles.isEmpty() ) {
					afficherRecapitulatif(nbBlanc, nbNoir);
					
				}
				
			}

		});

		grid.add(pane, colIndex, rowIndex);		
	}
	
	/**
	 * Ajout des images des pions sur le plateau.
	 * 
	 */
	public static void updateTableau(GridPane grid) {
		
		/* balayage du tableau */
		for (int i =0; i<8; i++) {
			for (int j=0; j<8; j++) {
				/* Si la case actuelle est blanche, on ajoute l'image d'un jeton blanc
				 * Si la case est noire, on ajoute l'image d'un jeton noir. 
				 * Sinon la case reste vide.
				 */
				switch (partieCourante.getPlateau().othellier[i][j].getCouleur()) {
					
					case 1 : ImageView Noir = new ImageView(caseNoire);
									   grid.add(Noir, j, i);
									   break;
									   
					case 0 : ImageView Blanc = new ImageView(caseBlanche);
									   grid.add(Blanc, j, i);
									   break;
				
				}
			}
		}
	}
	
	
	/**
	 * Met à jour le score de chaque joueur
	 */
	public void changerScore(int nbBlanc, int nbNoir){
		lbl_scoreBlanc.setText(String.valueOf(nbBlanc));
		lbl_scoreNoir.setText(String.valueOf(nbNoir));
	}
	
	/**
	 * Souligne le nom du joueur qui doit jouer
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
	public static void initPartieIA(String pseudo_J1){
			partieCourante = new Partie(
					new Joueur(pseudo_J1, 0),
					new Joueur(1));			
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