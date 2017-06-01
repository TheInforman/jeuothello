/* PlateauController		08/05/2017
 * info1 groupe Othello
 */
package Maquette.fenetres;


import java.io.File;
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
import javafx.stage.Stage;
import othello.Joueur;
import othello.Partie;
import othello.Plateau;
import othello.Scores;
import outils.OutilFichier;

/**
 * Controller du plateau de jeu
 * @author Arthur Pradier, Micka�l Queudet
 */
public class PlateauController {


	/** Le gagnant � la fin de la partie */
	public static String pseudoGagnant;

	/** Le score du gagnant � la fin de la partie */
	public static int scoreGagnant;

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

	/** Bouton pour sauvegarder la partie actuelle au format.bin */
	@FXML
	public Button btn_sauvegarder;

	/** bouton pour retourner au menu principal */
	@FXML 
	public Button btn_menuPrincipal;

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
	 * Initialisation de la partie en fonction des pseudos des joueurs et de si la
	 * partie oppose deux joueurs ou un joueur et l'IA
	 */
	public static void initPartie(String pseudo_J1, String pseudo_J2,
			int typeDePartie){

		if (Math.random() > 0.5){

			partieCourante = new Partie(
					new Joueur(pseudo_J1, 0),
					new Joueur(pseudo_J2, 1),
					typeDePartie
					);			
		} else {

			partieCourante = new Partie(
					new Joueur(pseudo_J2, 0),
					new Joueur(pseudo_J1, 1),
					typeDePartie
					);
		}
	}


	/**
	 * Boucle active apr�s le chargement du programme qui 
	 * permet le clic sur le plateau
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
	 * V�rifie si le joueur courant peut jouer son tour. Si ce n'est pas le cas, 
	 * affiche une msgBox pour lui notifier que son tour a �t� pass�. 
	 * Si aucun des deux joueurs ne peut jouer d'affil�e, la partie se termine
	 */
	private void controleSiTourJouable() {
		if(Plateau.coupsPossibles.isEmpty() ) {
			tourSuivant();
			BoitesMessage.afficher_msgBoxInfo(
					"Notification de Partie",
					"Le tour a �t� pass�",
					"le joueur ne pouvait pas agir.");

			if(Plateau.coupsPossibles.isEmpty() ) {
				finPartie();
				System.out.println("coucou");
			}
		}
	}

	/**
	 * Passe au joueur suivant et actualise l'�tat du tableau
	 */
	private void tourSuivant() {
		partieCourante.tourSuivant();
		updateTableau(grid);	//mise � jour du tableau

		actualiserScore();
	}


	/**
	 * applique le coup sur la case cliqu�e, qui est pass�e en param�tre.
	 * n'a aucun effet sur l'objet graphique.
	 * @param rowIndex num�ro de ligne de la case o� le coup est � apliquer
	 * @param colIndex num�ro de colonne de la case o� le coup est � appliquer
	 */
	private void appliquerCoups(int rowIndex, int colIndex) {
		partieCourante.getPlateau().appliquerCoups(
				partieCourante.getPlateau().othellier[rowIndex][colIndex],
				partieCourante.getDoitJouer()
				);
	}


	/**
	 * Mets fin � la partie en affichant le r�capitulatif de fin
	 */
	private void finPartie() {
		enregistrerScores();
		afficherRecapitulatif(
				partieCourante.getPlateau().calculerNbPions(0),
				partieCourante.getPlateau().calculerNbPions(1)
				);
	}


	/**
	 * Actualise le label contenant le score de chaque joueur pour le faire
	 * correspondre au score actuel
	 */
	private void actualiserScore() {
		int nbBlanc = partieCourante.getPlateau().calculerNbPions(0);
		int nbNoir = partieCourante.getPlateau().calculerNbPions(1);
		System.out.println("Score : " + nbBlanc + " � " + nbNoir ); //Affichage console pour le debugging
		lbl_scoreBlanc.setText(String.valueOf(nbBlanc));
		lbl_scoreNoir.setText(String.valueOf(nbNoir));
	}


	/**
	 * Ajout des images des pions sur le plateau. afin de conrrespondre 
	 * � l'�tat actuel
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
	 * Actualise le score des deux joueurs
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
	 * D�termine quel joueur est le gagnant, puis appelle un r�capitulatif 
	 * affichant son pseudo et son score
	 */
	public void afficherRecapitulatif(int scoreBlanc, int scoreNoir) {
		// 0 = blancs
		// 1 = noirs
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

		System.out.println("Recapitulatif");
		Main.showRecapitulatif();
	}

	/**
	 * Enregistre la partie actuelle dans un r�pertoire par d�faut, notifie le joueur
	 * de la sauvegarde via une msgBox. Si le r�pertoire de sauvegarde d�sir� n'est pas accessible,
	 * on annule la sauvegarde.
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


		if (!Main.accederRepertoireOthello()) {
			return;
		}
		OutilFichier.enregistrerPartie(partieCourante);
		BoitesMessage.afficher_msgBoxInfo("Sauvegarde de la partie",
				"Partie sauvegard�e avec succ�s !",
				"Vous pourrez reprendre votre partie plus tard.");
		Stage stage = (Stage) btn_menuPrincipal.getScene().getWindow();
		stage.close();
		Main.showMenuPrincipal();

	}

	/**
	 * Quitte la partie actuelle sans sauvegarder et retourne au menu principal. 
	 * L'utilisateur devra confirmer son choix et sera averti des cons�quences
	 * possibles. 
	 */
	@FXML
	private void quitterPartie() {
		if (
				BoitesMessage.afficher_msgBoxConfirmation(
						"Revenir au Menu Principal",
						"Vous �tes sur le point de revenir au Menu Prncipal",
						"Souhaitez vous quittez la partie ?" +
								"\n L'avancement ne sera pas sauvegard� !"
						)) {
			Stage stage = (Stage) btn_menuPrincipal.getScene().getWindow();
			stage.close();
			Main.showMenuPrincipal();
		}
	}

	/**
	 * Appel� lors du chargement d'une partie. 
	 * Remplace le plateau vide g�n�r� au chargement par le plateau 
	 * r�cup�r� dans e fichier de sauvegarde
	 */
	public static void restaurerPartie(Partie aRestaurer){
		partieCourante = aRestaurer;
	}
	/**
	 * Permet, � la fin de la partie, d'enregistrer les scores
	 */
	private void enregistrerScores(){
		// Fichier de sauvegarde
		File file = new File(OutilFichier.getRepertoireParDefaut() +"\\Othello\\scoresOthello.sothl");

		// V�rification si le fichier de scores existe
		if(!file.exists()){
			// On cr�e l'objet Scores et on ajoute le score
			Scores courant = new Scores();
			 courant.ajoutScore(pseudoGagnant, String.valueOf(scoreGagnant));
		} else{
			// On restaure les scores
			Scores courant = OutilFichier.restaurerScores(
					OutilFichier.getRepertoireParDefaut() +"\\Othello\\scoresOthello.sothl");
			 courant.ajoutScore(pseudoGagnant, String.valueOf(scoreGagnant));
		}
	}

}