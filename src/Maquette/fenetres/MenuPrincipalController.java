/*
 * MenuPrincipalController.java			08/05/2017
 */
package Maquette.fenetres;


import Maquette.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Controleur de la fenetre Menu Principal
 * @author Arthur
 */
public class MenuPrincipalController {
	
	/** Bouton ouvrant la fenêtre de saisie des pseudos des joueusr */
	@FXML
	private Button SaisiePseudo;
	
	/** Bouton ouvrant la fenêtre du choix de la difficulté de l'IA */
	@FXML 
	private Button ChoixDifficulte;
	
	/** Bouton qui ferme l'application */
	@FXML 
	private Button Quitter;
	
	/** Bouton pour choisir la partie à charger */
	@FXML 
	private Button Charger;
	
	/** Une image contenant le titre "Othello" */
	@FXML
	private ImageView Titre ;
	
	private static Image ImageTitre = new Image ("/Maquette/Ressource/OthelloTitre.png");

	/**
	 * Constructeur de la classe
	 */
	public MenuPrincipalController() {
		
	}
	
	/**
	 * Appellée après le chargement de la fenêtre, insère une image de titre
	 *
	 */
	public void initialize() {
		Titre.setImage(ImageTitre);
	}
	 /**
     * Lorsque l'utilisateur clique sur le bouton Jouer contre l'IA, affiche la fenêtre
     * de choix de la difficulté
     */
    @FXML
    private void handleChoixDifficulte() {
    	Stage stage = (Stage) ChoixDifficulte.getScene().getWindow();
    	stage.close();
        Main.showChoixDifficulte();
    }

    /**
     * Lorsque l'utilisateur clique sur le bouton Jouer avec un ami, affiche
     * la fenetre de saisie des pseudo
     */
    @FXML
    private void handleSaisiePseudo() {
    	Stage stage = (Stage) SaisiePseudo.getScene().getWindow();
	    stage.close();
    	Main.showSaisiePseudo();
    }
    
    /**
     * Lorsqu'un utilisateur clique sur le bouton "charger une partie", ouvre
     * un explorateur de fichier grâce auquel il ira choisir sa sauvegarde
     */
    @FXML
    private void handleCharger() {
    	Main.selectionFichier((Stage) Charger.getScene().getWindow());
    }
    
    /**
     * Ferme l'application lorsqu'un joueur clique sur le bouton quitter
     */
    @FXML
    private void handleQuitter() {
    	Stage stage = (Stage) Quitter.getScene().getWindow();
	    stage.close();
    }
    
    
    /**
     * Affiche la fenêtre modale contenant les scores des joueurs
     */
    @FXML
    private void handleScores() {
    	Main.showScores();
    }
    
    /**
     * Affiche une fenêtre modale d'aide 
     */
    @FXML
    private void handleAide() {
    	Main.showAide();
    }
    
}
