/*
 * MenuPrincipalController.java			08/05/2017
 */
package Maquette.fenetres;


import Maquette.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controleur de la fenetre Menu Principal
 * @author Arthur
 */
public class MenuPrincipalController {
	
	/** Bouton ouvrant la fenêtre de saisie des pseudos des joueusr */
	@FXML
	public Button SaisiePseudoButton;
	
	/** Bouton ouvrant la fenêtre du choix de la difficulté de l'IA */
	@FXML 
	public Button ChoixDifficulte;
	
	/** Bouton qui ferme l'application */
	@FXML 
	public Button Quitter;
	
	/** Bouton pour choisir la partie à charger */
	@FXML 
	public Button Charger;

	/**
	 * Constructeur de la classe
	 */
	public MenuPrincipalController() {
		
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
    	Stage stage = (Stage) SaisiePseudoButton.getScene().getWindow();
	    stage.close();
    	Main.showSaisiePseudo();
    }
    
    /**
     * Ferme l'application lorsqu'un joueur clique sur le bouton quitter
     */
    @FXML
    private void handleQuitter() {
    	Stage stage = (Stage) SaisiePseudoButton.getScene().getWindow();
	    stage.close();
    }
    
    /**
     * Lorsqu'un utilisateur clique sur le bouton "charger une partie", ouvre
     * un explorateur de fichier grâce auquel il ira choisir sa sauvegarde
     */
    @FXML
    private void handleCharger() {
    	Main.selectionFichier((Stage) Quitter.getScene().getWindow());
    }
    
    /**
     * Affiche la fenêtre modale contenant les scores des joueurs
     */
    @FXML
    private void handleScores() {
    	Main.showScores();
    }
    
    /**
     * Affiche une fenetre modale d'aide 
     */
    @FXML
    private void handleAide() {
    	Main.showAide();
    }
    
}
