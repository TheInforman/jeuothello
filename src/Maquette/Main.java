/**
 * Main.java	08/05/2017
 * Groupe Othello PRADIER,QUEUDET, BOUYSSOU, GEORGES, GALINIER
 */
package Maquette;


import java.io.File;
import java.io.IOException;



import java.nio.file.Path;
import java.nio.file.Paths;


import Maquette.fenetres.PlateauController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import othello.Partie;
import outils.OutilFichier;


/**
 * Classe gérant le lancement des différentes fenêtres
 * @author Arthur
 * @author Vincent
 */
public class Main extends Application {
	
	private static Stage primaryStage;
    
	
    /**
     * Lancement de l'application
     */
    @SuppressWarnings("static-access")
	@Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Othello");

        showMenuPrincipal();
    }
    
    /**
     * Affiche la fenêtre Menu Principal
     */
    public static void showMenuPrincipal() {
        try {
            // chargement du fichier XML
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("fenetres/MenuPrincipal.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            // Création de la scene
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
    		// Charge la page FXML associée
    		FXMLLoader loader = new FXMLLoader();
        	loader.setLocation(Main.class.getResource("fenetres/ChoixDifficulte.fxml"));
        	AnchorPane page = (AnchorPane) loader.load();
        	
        	// Création de la scene
            Stage ChoixDifficulte = new Stage();
            ChoixDifficulte.setTitle("Othello: Difficulté");
            ChoixDifficulte.initOwner(primaryStage);
            Scene scene = new Scene(page);
            ChoixDifficulte.setScene(scene);
            
            // Affiche la fenêtre et ferme la fenêtre précédente
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
    		
    		
    		//Affiche la fenêtre et ferme la fenêtre précédente
    		primaryStage.close();
    		SaisiePseudo.show();
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * Affichage de la fenêtre saisiePseudoIA
     */
    public static void showSaisiePseudoIA() {
    	try {
    		//Chargement du fichier FXML
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(Main.class.getResource("fenetres/SaisiePseudoIA.fxml"));
    		AnchorPane page = (AnchorPane) loader.load();
    		//Creation de la Scene
    		Stage SaisiePseudoIA = new Stage();
    		SaisiePseudoIA.setTitle("Othello: Saisie des Pseudos");
    		SaisiePseudoIA.initOwner(primaryStage);
    		Scene scene = new Scene(page);
    		SaisiePseudoIA.setScene(scene);
    		
    		//Affiche la fenêtre et ferme la fenêtre précédente
    		primaryStage.close();
    		SaisiePseudoIA.show();
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
		
		
    }

    
    /**
     * Affichage d'une fenêtre modale d'aide
     */
    public static void showAide() {
    	try {
    		//Chargement du fichier XML de l'aide
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(Main.class.getResource("fenetres/Aide.fxml"));
    		AnchorPane page = (AnchorPane) loader.load();
    		
    		// création du Stage
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
    		
    		// création du Stage
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
    		
    		//Affiche la fenêtre et ferme la fenêtre précédente
    		primaryStage.close();
    		Plateau.show();
    		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    
    /**
     * Affichage de la fenêtre PlateauIA
     */
    public static void showPlateauIA() {
    	try {
    		//Chargement du fichier FXML
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(Main.class.getResource("fenetres/PlateauIA.fxml"));
    		AnchorPane page = (AnchorPane) loader.load();
    		//Creation de la Scene
    		Stage PlateauIA = new Stage();
    		PlateauIA.setTitle("Othello: Saisie des Pseudos");
    		PlateauIA.initOwner(primaryStage);
    		Scene scene = new Scene(page);
    		PlateauIA.setScene(scene);
    		
    		//Affiche la fenêtre et ferme la fenêtre précédente
    		primaryStage.close();
    		PlateauIA.show();
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
		
		
    }
    
    /**
     * Affichage d'un récapitulatif de la partie lorsqu'elle est terminée
     */
    public static void showRecapitulatif() {
    	try {
    		//chargement du fichier FXML
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(Main.class.getResource("fenetres/RecapitulatifPartie.fxml"));
    		AnchorPane page = (AnchorPane) loader.load();
    		//création de la scene
    		Stage Recapitulatif = new Stage();
    		Recapitulatif.setTitle("Récapitulatif de fin");
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
     * S'assure qu'il est possible d'accéder au répertoire Othello devant être
     * situé dans le répertoire par défaut de l'utilisateur.
     * Crée le répertoire Othello ci celui-ci n'existe pas.
     * 
     * @return true si le répertoire Othello existe sur la machine,
     * 		   si le repertoire n'a pas pu être créé, return false
     */
    public static boolean accederRepertoireOthello() {
    	if (!OutilFichier.isRepertoireOthelloExistant()) {

    		boolean repertoireCree = OutilFichier.creerRepertoireOthello();

    		if (!repertoireCree) {
    			BoitesMessage.afficher_msgBoxErreur(
    					"Création de répertoire impossible",
    					"Erreur dans la création du répertoire Othello",
    					"Le répertoire n'a pas pu être créé"
    							+ " à l'emplacement "
    							+ OutilFichier.getRepertoireParDefaut());
    			return false;
    		}
    	}
    	return true;
    }
    
    /**
     * Lorsqu'un utilisateur clique sur le bouton "charger une partie", ouvre
     * un explorateur de fichier grâce auquel il ira choisir sa sauvegarde
     */
    public static void selectionFichier() {
    	
    	if (!accederRepertoireOthello()) {
    		return;
    	}
    	
    	
    	
    	FileChooser fileChooser = new FileChooser();
    	
    	fileChooser.setTitle("Sélection de la Sauvegarde");
    	
    	fileChooser.getExtensionFilters().addAll(
    	         new ExtensionFilter("Sauvegarde Othello",
    	        		 "*" + OutilFichier.getExtensionSauvegarde()),
    	         new ExtensionFilter("All Files", "*.*")
    	         );
    	fileChooser.setInitialDirectory(
    			OutilFichier.getPathRepertoireOthello()
    			);
    	
    	File selectedFile = fileChooser.showOpenDialog(primaryStage);
    	 if (selectedFile != null) {
    		 
    		 Partie partieRestauree =
    			OutilFichier.restaurerPartie(
    					selectedFile.getAbsolutePath()
    					);
    		 
    		 Path cheminSauvegarde = Paths.get(selectedFile.getAbsolutePath());
    		 
    		 OutilFichier.supprimerSauvegarde(cheminSauvegarde);
    		 
    		 primaryStage.close();
    		 PlateauController.restaurerPartie(partieRestauree);
    		 Main.showPlateau();
    	 }
    }	
	
    /**
     * Lancement de l'application
     * @param args
     */
	public static void main(String[] args) {
		launch(args);
		
	}
	
}
