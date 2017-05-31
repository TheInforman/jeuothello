/* PlateauController		31/05/2017
 * info1 groupe Othello
 */
package Maquette.fenetres;


import java.util.ArrayList;

import Maquette.BoitesMessage;
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
import othello.Case;
import othello.Joueur;
import othello.Partie;
import othello.Plateau;
import outils.OutilFichier;

/**
 * Controller du plateau de jeu avec l'IA. A la diff�rence de PlateauController, 
 * un seul joueur humain est pr�sent. 
 * @author Arthur Pradier, Micka�l Queudet
 */
public class PlateauIAController {
	
	/** Image associ�e � une case noire */
	private static Image caseNoire =
			new Image("file:src/Maquette/Ressource/Jeton1.png");
	
	/** Image associ�e � une case blanche */
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
	
	/** Bouton pour sauvegarder la partie actuelle au format .othl */
	@FXML
	public Button btn_sauvegarder;
	
	/** bouton pour retourner au menu principal */
	@FXML 
	public Button btn_menuPrincipal;
	
	/** Bouton pour faire jouer l'IA */
	@FXML
	public Button btn_jouerIA;
	
	/**
	 * M�thode appel�e apr�s le chargement de la page 
	 */
	public void initialize() {

		Plateau plateauCourant = partieCourante.getPlateau();
		
		/* On d�termine qui est le joueur blanc et qui est le joueur noir*/
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
	public static void initPartieIA(String pseudo_J1){
			partieCourante = new Partie(
					new Joueur(pseudo_J1, 0),
					new Joueur("Ordinateur",1));			
	}


	/**
	 * TODO : JDOC
	 */
	public void addPane(int colIndex, int rowIndex) {

		Pane pane = new Pane();	

		// Passage dans cette partie du code lorsque le joueur clique sur une case
		pane.setOnMouseClicked(e -> {	
			
			appliquerCoups(rowIndex,colIndex);
			
			//On passe au tour suivant si le coups a pu �tre effectu�
			if (partieCourante.getPlateau().isActionEffectuee()){	
				tourSuivant();
			}
			
			partieCourante.getPlateau().setActionEffectuee(false);
			
			// souligne le joueur qui doit jouer 
			setQuiDoitJouer(partieCourante.getDoitJouer());
	
			controleSiTourJouable();
		});

		grid.add(pane, colIndex, rowIndex);		
	}
	
	/**
	 * 
	 */
	private void controleSiTourJouable() {
		if(Plateau.coupsPossibles.isEmpty() ) {
			tourSuivant();
			BoitesMessage.afficher_msgBoxInfo(
					"Notification de Partie",
					"Le tour a �t� pass�",
					"Le joueur ne pouvait pas agir.");
			
			if(Plateau.coupsPossibles.isEmpty() ) {
				finPartie();
			}
		}
	}
	
	/**
	 * TODO : JAVADOC
	 */
	private void tourSuivant() {
		partieCourante.tourSuivant();
		updateTableau(grid);	//mise � jour du tableau
		
		actualiserScore();
	}


	/**
	 * TODO : JAVADOC
	 * @param rowIndex
	 * @param colIndex
	 */
	private void appliquerCoups(int rowIndex, int colIndex) {
		partieCourante.getPlateau().appliquerCoups(
				partieCourante.getPlateau().othellier[rowIndex][colIndex],
				partieCourante.getDoitJouer()
				);
	}


	/**
	 * TODO : Javadoc
	 */
	private void finPartie() {
		afficherRecapitulatif(
				partieCourante.getPlateau().calculerNbPions(0),
				partieCourante.getPlateau().calculerNbPions(1)
				);
		
	}


	/**
	 * TODO : JAVAOC
	 */
	private void actualiserScore() {
		int nbBlanc = partieCourante.getPlateau().calculerNbPions(0);
		int nbNoir = partieCourante.getPlateau().calculerNbPions(1);
		System.out.println("Score : " + nbBlanc + " � " + nbNoir ); //Affichage console pour le debugging
		lbl_scoreBlanc.setText(String.valueOf(nbBlanc));
		lbl_scoreNoir.setText(String.valueOf(nbNoir));
	}

	
	/**
	 * Ajout des images des pions sur le plateau.
	 * 
	 */
	public static void updateTableau(GridPane grid) {
		
		/* balayage du tableau */
		for (int i =0; i<8; i++) {
			for (int j=0; j<8; j++) {
				/* Si la case actuelle est blanche,
				 * on ajoute l'image d'un jeton blanc
				 * Si la case est noire, 
				 * on ajoute l'image d'un jeton noir. 
				 * Sinon la case reste vide.
				 */
				switch (partieCourante.getPlateau().othellier[i][j]
						.getCouleur()) {
					
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
	 * Met � jour le score de chaque joueur
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
	
	public void jouerTourIASDQSDSQd() {
		Plateau plateau = partieCourante.getPlateau();
		
		
		/* Case ayant le plus de pions retourn�s */
		Case meilleurChoix;
		/* Liste des pions retourn�s par meilleurChoix*/
		Case[] listePionsRetournesMax = new Case[21];
		
		/* test de la premi�re case comme initialisation */
		meilleurChoix = plateau.coupsPossibles.get(0);
		listePionsRetournesMax = plateau.determinerPionsARetourner(meilleurChoix,1);
		
		/*for (int i = 0 ;
				i < plateau.coupsPossibles.size() &&
				plateau.coupsPossibles.get(i) != null &&
				    // teste si la prochaine case dans la liste des coups
				    // possibles rapporte plus de pions que la case d�j�
				    // stock�e
				listePionsRetournesMax.length < plateau.determinerPionsARetourner.length(
						plateau.coupsPossibles.get(i), 1) ;
			i++) {
			// stocke la case si elle est plus efficace
			meilleurChoix = plateau.coupsPossibles.get(i);
			listePionsRetournesMax = plateau.determinerPionsARetourner(
					plateau.coupsPossibles.get(i), 1);
		}*/
		
		/* L'IA joue meilleurChoix */
		plateau.appliquerCoups(meilleurChoix, 1);
		
		/* Passe au tour suivant */
		//tourSuivant();
	}
	
	/**
	 * TODO : JDOC
	 */
	@FXML
	public void JouerTourIA() {
		
		if(partieCourante.getDoitJouer() != 1) {
			return;
		}
		
		Plateau plateau = partieCourante.getPlateau();
		
		ArrayList<Case> coupsPossibles = plateau.getCoupsPossibles();
		
		//Case ayant le plus de pions retourn�s
		Case meilleurChoix;
		
		
		/* test de la premi�re case comme initialisation */
		meilleurChoix = coupsPossibles.get(0);
		
		int nombrePionsRetournesMax = plateau.determinerPionsARetourner(meilleurChoix,1).length;
		
		for (int i = 1; i < coupsPossibles.size()
				&& coupsPossibles.get(i) != null; i++) {
			
			if (coupsPossibles.size() > nombrePionsRetournesMax) {
				nombrePionsRetournesMax = plateau.determinerPionsARetourner(coupsPossibles.get(i), 1).length;
				meilleurChoix = coupsPossibles.get(i);
			}
		}
		
		
		
		System.out.println(meilleurChoix);
		System.out.println(partieCourante);
		
		
		
		
		appliquerCoups(meilleurChoix.getLigne(),meilleurChoix.getColonne());
		
		//On passe au tour suivant si le coups a pu �tre effectu�
		if (partieCourante.getPlateau().isActionEffectuee()){	
			tourSuivant();
		}
		
		partieCourante.getPlateau().setActionEffectuee(false);
		
		// souligne le joueur qui doit jouer 
		setQuiDoitJouer(partieCourante.getDoitJouer());

		controleSiTourJouable();
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
		TODO: Linker les r�capitulatifs
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
    		System.out.println("Le r�pertoire Othello n'existe pas");
    		boolean repertoireCree = OutilFichier.creerRepertoireOthello();
    		if (repertoireCree) {
    			System.out.println("R�pertoire cr�� avec succ�s");
    		} else {
    			System.out.println("Le r�pertoire n'a pas pu �tre cr��"
    					+ " � l'emplacement "
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