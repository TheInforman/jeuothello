/**
 * Main.java	08/05/2017
 * Groupe Othello PRADIER,QUEUDET, BOUYSSOU, GEORGES, GALINIER
 */
package Maquette;
	
import java.io.File;
import java.io.IOException;

import Maquette.fenetres.PlateauController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import othello.Joueur;
import othello.Partie;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * Classe g�rant le lancement des diff�rentes fen�tres
 * @author Arthur
 */
public class Main extends Application {
	
	private static Stage primaryStage;
    private BorderPane rootLayout;
    
	
    /**
     * Lancement de l'application
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Othello");

        showMenuPrincipal();
    }
    
    /**
     * Affiche la fen�tre Menu Principal
     */
    public static void showMenuPrincipal() {
        try {
            // chargement du fichier XML
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fenetres/MenuPrincipal.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            // Cr�ation de la scene
            Stage MenuPrincipal = new Stage();
            MenuPrincipal.setTitle("Othello");
            MenuPrincipal.initOwner(primaryStage);
            Scene scene = new Scene(page);
            MenuPrincipal.setScene(scene);

            MenuPrincipal.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Ouvre la fenetre CHoixDifficulte
     */
    public static void showChoixDifficulte() {
    	try {
    		// Charge la page FXML associ�e
    		FXMLLoader loader = new FXMLLoader();
        	loader.setLocation(Main.class.getResource("fenetres/ChoixDifficulte.fxml"));
        	AnchorPane page = (AnchorPane) loader.load();
        	
        	// Cr�ation de la scene
            Stage ChoixDifficulte = new Stage();
            ChoixDifficulte.setTitle("Othello: Difficult�");
            ChoixDifficulte.initOwner(primaryStage);
            Scene scene = new Scene(page);
            ChoixDifficulte.setScene(scene);
            
            // Affiche la fen�tre et ferme la fen�tre pr�c�dente
            primaryStage.close();
            ChoixDifficulte.show();
        	
    	} catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Ouvre la fenetre SaisiePseudo
     */
    public static void showSaisiePseudo() {
    	try {
    		//Chargement du fichier FXML
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(Main.class.getResource("fenetres/SaisiePseudo.fxml"));
    		AnchorPane page = (AnchorPane) loader.load();
    		//Creation de la Scene
    		Stage SaisiePseudo = new Stage();
    		SaisiePseudo.setTitle("Othello: Saisie des Pseudos");
    		SaisiePseudo.initOwner(primaryStage);
    		Scene scene = new Scene(page);
    		SaisiePseudo.setScene(scene);
    		
    		
    		//Affiche la fen�tre et ferme la fen�tre pr�c�dente
    		primaryStage.close();
    		SaisiePseudo.show();
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    

    
    /**
     * Affichage d'une fen�tre modale d'aide
     */
    public static void showAide() {
    	try {
    		//Chargement du fichier XML de l'aide
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(Main.class.getResource("fenetres/Aide.fxml"));
    		AnchorPane page = (AnchorPane) loader.load();
    		
    		// cr�ation du Stage
            Stage Aide = new Stage();
            Aide.setTitle("Othello: Aide");
            Aide.initOwner(primaryStage);
            Aide.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            Aide.setScene(scene);
            
            Aide.showAndWait();
            
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * Affiche les meilleurs scores des joueurs
     */
    public static void showScores() {
    	try {
    		//Chargement du fichier XML de l'aide
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(Main.class.getResource("fenetres/Scores.fxml"));
    		AnchorPane page = (AnchorPane) loader.load();
    		
    		// cr�ation du Stage
            Stage Scores = new Stage();
            Scores.setTitle("Othello: Scores");
            Scores.initOwner(primaryStage);
            Scores.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            Scores.setScene(scene);
            
            Scores.showAndWait();
            
            
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * Affiche le plateau de jeu
     */
    public static void showPlateau() {
		try {
			//Chargement du fichier FXML
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(Main.class.getResource("fenetres/PlateauJeu.fxml"));
    		AnchorPane page = (AnchorPane) loader.load();
    		//Creation de la Scene
    		Stage Plateau = new Stage();
    		Plateau.setTitle("Othello: jouez!");
    		Plateau.initOwner(primaryStage);
    		Scene scene = new Scene(page);
    		Plateau.setScene(scene);
    		
    		
    		//Affiche la fen�tre et ferme la fen�tre pr�c�dente
    		primaryStage.close();
    		Plateau.show();
    		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    
    /**
     * Affichage d'un r�capitulatif de la partie lorsqu'elle est termin�e
     */
    public static void showRecapitulatif() {
    	try {
    		//chargement du fichier FXML
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(Main.class.getResource("fenetres/RecapitulatifPartie.fxml"));
    		AnchorPane page = (AnchorPane) loader.load();
    		//cr�ation de la scene
    		Stage Recapitulatif = new Stage();
    		Recapitulatif.setTitle("R�capitulatif de fin");
    		Recapitulatif.initOwner(primaryStage);
    		Recapitulatif.initModality(Modality.APPLICATION_MODAL);
    		Scene scene = new Scene(page);
    		Recapitulatif.setScene(scene);
    		
    		Recapitulatif.showAndWait();
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * Lorsqu'un utilisateur clique sur le bouton "charger une partie", ouvre
     * un explorateur de fichier gr�ce auquel il ira choisir sa sauvegarde
     */
    public static void SelectionFichier() {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Choix de la Sauvegarde");
    	fileChooser.showOpenDialog(primaryStage);
    }
	
    /**
     * Lancement de l'application
     * @param args
     */
	public static void main(String[] args) {
		launch(args);
		
	}
}
