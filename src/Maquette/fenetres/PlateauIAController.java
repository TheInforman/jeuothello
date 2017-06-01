/* PlateauController		31/05/2017
 * info1 groupe Othello
 */
package Maquette.fenetres;


import java.io.File;
import java.util.Optional;

import Maquette.BoitesMessage;
import Maquette.Main;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import othello.Case;
import othello.Joueur;
import othello.Partie;
import othello.Plateau;
import othello.Scores;
import outils.OutilFichier;
import outils.OutilsIA;

/**
 * Controller du plateau de jeu avec l'IA. A la diff�rence de PlateauController, 
 * un seul joueur humain est pr�sent. 
 * @author Arthur Pradier, Micka�l Queudet
 */
public class PlateauIAController {
	
	/** la grille, partie visible du plateau, de taille 8*8 */
	@FXML
	private GridPane grid;
	
	/** Le score du joueur blanc */
	@FXML 
	private Label lbl_scoreBlanc;
	
	/** Le score du joueur noir */
	@FXML
	private Label lbl_scoreNoir;
	
	/** Le pseudo du joueur blanc */
	@FXML
	private Label lbl_blanc;
	
	/** Le pseudo du joueur noir */
	@FXML
	private Label lbl_noir;
	
	/** Bouton pour sauvegarder la partie actuelle au format .othl */
	@FXML
	private Button btn_sauvegarder;
	
	/** bouton pour retourner au menu principal */
	@FXML 
	private Button btn_menuPrincipal;
	
	/** Bouton pour faire jouer l'IA */
	@FXML
	private Button btn_jouerIA;
	
	/** Image associ�e � une case noire */
	private static Image caseNoire =
			new Image("file:src/Maquette/Ressource/Jeton1.png");
	
	/** Image associ�e � une case blanche */
	private static Image caseBlanche =
			new Image("file:src/Maquette/Ressource/Jeton0.png");
	
	/** Image associ�e � une case vide */
	private static Image caseVide =
			new Image("file:src/Maquette/Ressource/Jeton-1.png");
	
	/** Score du joueur */
	public static int scoreJoueur;
	
	/** Pseudo du joueur */
	public static String pseudoJoueur;
	
	/** L'identificateur du joueur gagnant */
	public static String pseudoGagnant;
	
	/** Le score du joueur gagnant */
	public static int scoreGagnant;
	

	
	/** La partie actuelle */
	public static Partie partieCourante;
	
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
		lbl_scoreBlanc.setText(
				String.valueOf(plateauCourant.calculerNbPions(0))
				); 
		lbl_scoreNoir.setText(
				String.valueOf(plateauCourant.calculerNbPions(1))
				);
		
		plateauCourant.determinerCoupsPossibles(partieCourante.getDoitJouer());
		
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
				addPane(i,j);	//ajout d'un panneau cliquable sur chaque case			
			}
		}
	}
	
	/**
	 * Boucle active apr�s le chargement du programme qui 
	 * permet le clic sur le plateau. Ajoute un panneau cliquable � chaque appel
	 * Initialisation de la partie avec un joueur et un ordinateur.
	 */
	public static void initPartieIA(String pseudo_J1, int typeDePartie){
			partieCourante = new Partie(
					new Joueur(pseudo_J1, OutilsIA.COULEUR_HUMAIN),
					new Joueur("Ordinateur", OutilsIA.COULEUR_IA),
					typeDePartie);			
	}


	/**
	 * Boucle active apr�s le chargement du programme qui 
	 * permet le clic sur le plateau
	 */
	public void addPane(int colIndex, int rowIndex) {
		
		Pane pane = new Pane();	

		/* Passage dans cette partie du code lorsque
		 * le joueur clique sur une case
		 */
		pane.setOnMouseClicked(e -> {	
			
			if(partieCourante.getDoitJouer() != OutilsIA.COULEUR_HUMAIN) {
				return;
			}
			
			appliquerCoups(rowIndex,colIndex);
			
			//On passe au tour suivant si le coups a pu �tre effectu�
			if (partieCourante.getPlateau().isActionEffectuee()){	
				tourSuivant();
			}
			
			partieCourante.getPlateau().setActionEffectuee(false);
	
			controleSiTourJouable();
		});

		grid.add(pane, colIndex, rowIndex);		
	}

	
	/**
	 * Joue un coups sur une case dont les coordonn�es
	 * sont pass�es en param�tre.
	 * 
	 * @param ligne		la ligne o� l'on applique le coup
	 * @param colonne	la colonne o� l'on applique le coup
	 */
	private void appliquerCoups(int ligne, int colonne) {
		partieCourante.archiverTour(
			partieCourante.getPlateau().appliquerCoups(
					partieCourante.getPlateau().othellier[ligne][colonne],
					partieCourante.getDoitJouer()
					)
		);
	}

	/**
	 * Passe un tour en incr�mentant le nombre de tour de la partieCourante,
	 * en mettant � jour le tableau de pions (graphiquement), en actualisant 
	 * les scores et en d�terminant qui doit jouer le tour suivant.
	 */
	private void tourSuivant() {
		partieCourante.tourSuivant();
		updateTableau(grid);	//mise � jour du tableau
		
		actualiserScore();
		setQuiDoitJouer(partieCourante.getDoitJouer());
	}
	
	/**
	 * Ajout des images des pions sur le plateau.
	 * @param grid le gridpane � mettre � jour
	 */
	public void updateTableau(GridPane grid) {
		
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
									  
					case -1 : ImageView Vide = new ImageView(caseVide);
							  grid.add(Vide, j, i);
							  break;
				
				}
				addPane(j,i); /* ajout d'un panneau cliquable pour toutes les cases apr�s 
								chaque mise � jour */
			}
		}
	}
	
	/**
	 * Actualise les labels de score des deux joueurs.
	 */
	private void actualiserScore() {
		int nbBlanc = partieCourante.getPlateau().calculerNbPions(0);
		int nbNoir = partieCourante.getPlateau().calculerNbPions(1);
		lbl_scoreBlanc.setText(String.valueOf(nbBlanc));
		lbl_scoreNoir.setText(String.valueOf(nbNoir));
	}
	
	/**
	 * Souligne le nom du joueur qui doit jouer
	 * 
	 * @param joueur 	le joueur qui joue le tour courant
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
	 * V�rifie si le joueur courant peut jouer son tour. Si ce n'est pas le cas, 
	 * affiche une msgBox pour lui notifier que son tour a �t� pass�. 
	 * Si aucun des deux joueurs ne peut jouer d'affil�e, la partie se termine
	 */
	private void controleSiTourJouable() {
		if(Plateau.coupsPossibles.isEmpty() ) {
			/* R�cup�re le pseudo du joueur jouant le tour actuel */
			String pseudoJoueur =
					partieCourante.getListeJoueur()
					[partieCourante.getDoitJouer()].getNom();
			tourSuivant();
			
			if(Plateau.coupsPossibles.isEmpty() ) {
				finPartie();
			} else {
				BoitesMessage.afficher_msgBoxInfo(
						"Notification de Partie",
						"Le tour a �t� pass�",
						pseudoJoueur + " ne pouvait pas agir.");
			}
		}
	}
	

	/**
	 * D�clenche les actions de fin de partie en sauvegardant les
	 * scores et en affichant un r�capitulatif
	 */
	private void finPartie() {
        scoreJoueur = Integer.valueOf(lbl_scoreBlanc.getText());
        pseudoJoueur = lbl_blanc.getText();
		enregistrerScores(pseudoJoueur, scoreJoueur);
		afficherRecapitulatif(
				partieCourante.getPlateau().calculerNbPions(0),
				partieCourante.getPlateau().calculerNbPions(1)
				);
		
	}


	/**
	 * Afficher une fen�tre r�capitulative de fin de partie,
	 * comprenant le pseudo du gagnant ainsi que son score.
	 * 
	 * @param scoreBlanc	le score du joueur blanc � la fin de la partie
	 * @param scoreNoir		le score du joueur noir � la fin de la partie
	 */
	public void afficherRecapitulatif(int scoreBlanc, int scoreNoir) {
		// 0 = blancs
		// 1 = noirs
		
		int gagnant = (scoreBlanc > scoreNoir) ? 0 : 1;
		if (gagnant == 0){
			pseudoGagnant = lbl_blanc.getText();
			scoreGagnant = scoreBlanc;
		}
		else{
			pseudoGagnant = lbl_noir.getText();
			scoreGagnant = scoreNoir;
		}
		Main.showRecapitulatif();
	}
	
	/**
	 * Permet de reprendre une partie gr�ce � un objet
	 * partie pass� en param�tre.
	 * Utilis� lors de la restauration d'une sauvegarde.
	 * 
	 * @param aRestaurer	 la partie que l'on restaurer
	 */
	public static void restaurerPartie(Partie aRestaurer){
		partieCourante = aRestaurer;
	}
	
	/**
	 * Permet, � la fin de la partie, d'enregistrer les scores.
	 * 
	 * @param pseudoGagnant		le pseudo du gagnant
	 * @param scoreGagnant		le score du gagnant
	 */
	private void enregistrerScores(String pseudoGagnant, int scoreGagnant){
		// Fichier de sauvegarde
		File file = new File(OutilFichier.getEmplacementSaveScores());

		// V�rification si le fichier de scores existe
		if(!file.exists()){
			// On cr�e l'objet Scores et on ajoute le score
			Scores courant = new Scores();
			 courant.ajoutScore(pseudoGagnant, String.valueOf(scoreGagnant));
		} else{
			// On restaure les scores
			Scores courant = OutilFichier.restaurerScores(
					OutilFichier.getEmplacementSaveScores());
			 courant.ajoutScore(pseudoGagnant, String.valueOf(scoreGagnant));
		}
	}

	/** 
	 * Ferme la fen�tre courante et renvoie au menu principal 
	 */
	@FXML 
	private void handleMenuPrincipal () {
		Alert confirmation = new Alert(AlertType.CONFIRMATION);
		confirmation.setTitle("Confirmation");
		confirmation.setHeaderText("Retour au menu principal");
		confirmation.setContentText(
				"�tes vous sur de vouloir retourner au menu principal? \n" + 
				"Votre partie ne sera pas sauvegard�e");
		Optional<ButtonType> result = confirmation.showAndWait();
		if (result.get() == ButtonType.OK) {
			Stage stage = (Stage) btn_menuPrincipal.getScene().getWindow();
			stage.close();
			Main.showMenuPrincipal();
		}
	}
	
	/**
	 * Fait jouer le tour courant par l'IA si c'est le tour de celle-ci.
	 */
	@FXML
	public void JouerTourIA() {
		
		if(partieCourante.getDoitJouer() != OutilsIA.COULEUR_IA) {
			//la m�thode s'arr�te ici si ce n'est pas le tour de l'ordinateur
			return; 
		}
		
		Case meilleurChoix;
		
		if(partieCourante.getTypeDePartie() == 1) {
			meilleurChoix = OutilsIA.strategieFacile(partieCourante);
		} else {
			meilleurChoix =  OutilsIA.strategieNormale(partieCourante);
		}
		
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
	 * Enregistre la partie 
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
		
		/* Renvoit au menu principal */
		Stage stage = (Stage) btn_menuPrincipal.getScene().getWindow();
		stage.close();
		Main.showMenuPrincipal();
	}
	
	/** Retour un tour en arri�re */
	@FXML
	public void tourPrecedent() {
		if (partieCourante.getTour() > 0) {
			partieCourante.tourPrecedent();
			updateTableau(grid);	//mise � jour du tableau	
			actualiserScore();
			setQuiDoitJouer(partieCourante.getDoitJouer());
		}
		
	}
}