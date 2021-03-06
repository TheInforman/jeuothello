/*
 * Main.java	08/05/2017
 * Groupe Othello PRADIER,QUEUDET, BOUYSSOU, GEORGES, GALINIER
 */
package Maquette;


import java.io.File;
import java.io.IOException;


import java.nio.file.Path;
import java.nio.file.Paths;
import Maquette.fenetres.PlateauController;
import Maquette.fenetres.PlateauIAController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import othello.Partie;
import outils.OutilFichier;


/**
 * Classe g�rant le lancement des diff�rentes fen�tres
 * @author Arthur
 * @author Vincent
 */
public class Main extends Application {
	
    /** La scene principale de l'application */
    private static Stage primaryStage;
    
	
    /**
     * Lancement de l'application
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Othello");
        this.primaryStage.getIcons().add(new Image("/Maquette/Ressource/OthelloLogo.png"));

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
            MenuPrincipal.getIcons().add(new Image("/Maquette/Ressource/OthelloLogo.png"));

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
            ChoixDifficulte.getIcons().add(new Image("/Maquette/Ressource/OthelloLogo.png"));
            
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
    		SaisiePseudo.getIcons().add(new Image("/Maquette/Ressource/OthelloLogo.png"));
    		
    		
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
    		SaisiePseudoIA.getIcons().add(new Image("/Maquette/Ressource/OthelloLogo.png"));
    		
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
            Aide.getIcons().add(new Image("/Maquette/Ressource/OthelloLogo.png"));
            
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
            Scores.getIcons().add(new Image("/Maquette/Ressource/OthelloLogo.png"));
            
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
    		Plateau.getIcons().add(new Image("/Maquette/Ressource/OthelloLogo.png"));
    		
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
    		PlateauIA.setTitle("Othello: Jouez!");
    		PlateauIA.initOwner(primaryStage);
    		Scene scene = new Scene(page);
    		PlateauIA.setScene(scene);
    		PlateauIA.getIcons().add(new Image("/Maquette/Ressource/OthelloLogo.png"));
    		
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
    		Recapitulatif.getIcons().add(new Image("/Maquette/Ressource/OthelloLogo.png"));
    		
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
     * @param menuPrincipal le stage depuis lequel on choisit nos fichiers
     */
    public static void selectionFichier(Stage menuPrincipal) {
    	
    	if (!accederRepertoireOthello()) {
    		return;
    	}
    	
    	
    	
    	FileChooser fileChooser = new FileChooser();
    	
    	fileChooser.setTitle("S�lection de la Sauvegarde");
    	
    	fileChooser.getExtensionFilters().addAll(
    	         new ExtensionFilter("Sauvegarde Othello",
    	        		 "*" + OutilFichier.getExtensionSauvegardePartie()),
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
    		 
    		 /**/
    		 menuPrincipal.close();
    		 // test du type de partie
    		 if (partieRestauree.getTypeDePartie() == 0) {
    			 PlateauController.restaurerPartie(partieRestauree);
    			 Main.showPlateau();
    		 } else { // sinon la partie est une partie humain vs machine
    			 PlateauIAController.restaurerPartie(partieRestauree);
    			 Main.showPlateauIA();
    		 }
    		 
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
