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
 * Classe g�rant le lancement des diff�rentes fen�tres
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
     * Affichage de la fen�tre saisiePseudoIA
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
    		
    		//Affiche la fen�tre et ferme la fen�tre pr�c�dente
    		primaryStage.close();
    		SaisiePseudoIA.show();
    		
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
     * Affichage de la fen�tre PlateauIA
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
    		
    		//Affiche la fen�tre et ferme la fen�tre pr�c�dente
    		primaryStage.close();
    		PlateauIA.show();
    		
    	} catch (IOException e) {
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
     * S'assure qu'il est possible d'acc�der au r�pertoire Othello devant �tre
     * situ� dans le r�pertoire par d�faut de l'utilisateur.
     * Cr�e le r�pertoire Othello ci celui-ci n'existe pas.
     * 
     * @return true si le r�pertoire Othello existe sur la machine,
     * 		   si le repertoire n'a pas pu �tre cr��, return false
     */
    public static boolean accederRepertoireOthello() {
    	if (!OutilFichier.isRepertoireOthelloExistant()) {

    		boolean repertoireCree = OutilFichier.creerRepertoireOthello();

    		if (!repertoireCree) {
    			BoitesMessage.afficher_msgBoxErreur(
    					"Cr�ation de r�pertoire impossible",
    					"Erreur dans la cr�ation du r�pertoire Othello",
    					"Le r�pertoire n'a pas pu �tre cr��"
    							+ " � l'emplacement "
    							+ OutilFichier.getRepertoireParDefaut());
    			return false;
    		}
    	}
    	return true;
    }
    
    /**
     * Lorsqu'un utilisateur clique sur le bouton "charger une partie", ouvre
     * un explorateur de fichier gr�ce auquel il ira choisir sa sauvegarde
     */
    public static void selectionFichier() {
    	
    	if (!accederRepertoireOthello()) {
    		return;
    	}
    	
    	
    	
    	FileChooser fileChooser = new FileChooser();
    	
    	fileChooser.setTitle("S�lection de la Sauvegarde");
    	
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
