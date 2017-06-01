/* OutilFichier.java							25/05/2017
 * Tous droits r�serv�s � l'IUT de Rodez
 */
package outils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

import javax.swing.filechooser.FileSystemView;

import othello.Partie;
import othello.Scores;

/**
 * Classe d'outils permettant :
 *     - de sauvegarder une partie au format ".othl"
 *     - de sauvegarder des Scores au format ".sothl"
 *     - de restaurer une partie enregistr�e
 *     - de restaurer des Scores enregistr�s      
 * 
 * @author Vincent Galinier
 * @author Adrien Bouyssou
 * @author Kerian Georges
 * @author Arthur Pradier
 * @author Micka�l Queudet 
 */
public class OutilFichier {

	/** Nom du r�pertoire dans lequel seront stock�es les sauvegardes
	 *                          de l'Othello */
	private static final String NOM_REPERTOIRE = "Othello";
	
	/** Pr�fixe d'une sauvegarde de partie */
	private static final String PREFIXE_SAUVEGARDE_PARTIE =
			                                              "sauvegardeOthello_";
	
	/** Pr�fixe d'une sauvegarde de Scores */
	private static final String PREFIXE_SAUVEGARDE_SCORES = "scoresOthello";
	
	/** Extension d'une sauvegarde de partie */
	private static final String EXTENSION_SAUVEGARDE_PARTIE = ".othl";
	
	/** Extension d'une sauvegarde de scores */
	private static final String EXTENSION_SAUVEGARDE_SCORES = ".sothl";

	/**
	 * @return 		le prefixe de sauvegarde
	 */
	public static String getPrefixeSauvegardePartie() {
		return PREFIXE_SAUVEGARDE_PARTIE;
	}

	/**
	 * @return		l'extension des fichiers de sauvegarde
	 */
	public static String getExtensionSauvegardePartie() {
		return EXTENSION_SAUVEGARDE_PARTIE;
	}





	/**
	 * Cr�e un nom de fichier en .othl constitu� d'une constante ainsi
	 * que de l'heure, de la minute, du mois et de l'ann�e courant.
	 * 
	 * @return		un nom de fichier de sauvegarde
	 */
	public static String nouveauNomFichierPartie() {
		Calendar calendrierMaintenant = Calendar.getInstance();
		int heure = calendrierMaintenant.get(Calendar.HOUR_OF_DAY);
		int minute = calendrierMaintenant.get(Calendar.MINUTE);
		int jour = calendrierMaintenant.get(Calendar.DAY_OF_MONTH);
		int mois = calendrierMaintenant.get(Calendar.MONTH) + 1;
		int annee = calendrierMaintenant.get(Calendar.YEAR);

		return PREFIXE_SAUVEGARDE_PARTIE + jour + "_" + mois + "_" + annee +
			   "_" + heure + "h" + minute + EXTENSION_SAUVEGARDE_PARTIE;
	}

	/**
	 * V�rifie si le r�pertoire {@link NOM_REPERTOIRE} existe 
	 * @return		vrai si le r�pertoire {@link NOM_REPERTOIRE} existe dans le
	 * 				r�pertoire par d�faut, faux sinon
	 */
	public static boolean isRepertoireOthelloExistant() {
		String repertoireDefaut = getRepertoireParDefaut().getAbsolutePath()
				+ "\\" + NOM_REPERTOIRE;

		Path path = Paths.get(repertoireDefaut);

		return Files.exists(path);
	}

	/**
	 * Supprime une sauvegarde (qu'elle soit de Partie ou de Scores)
	 * @param nomSauvegarde {@code Path} o� est la sauvegarde
	 */
	public static void supprimerSauvegarde(Path nomSauvegarde) {
		try {
			Files.delete(nomSauvegarde);
		} catch (NoSuchFileException x) {
			System.err.format("%s: no such" + " file or directory%n",
					          nomSauvegarde);
		} catch (DirectoryNotEmptyException x) {
			System.err.format("%s not empty%n", nomSauvegarde);
		} catch (IOException x) {
			// File permission problems are caught here.
			System.err.println(x);
		}
	}

	/**
	 * Cr�e le r�pertoire "Othello" dans le r�pertoire
	 * par d�faut de l'utilisateur.
	 * 
	 * @return		{@code false} si le r�pertoire n'a pas pu �tre cr��,
	 * 				{@code true} sinon
	 */
	public static boolean creerRepertoireOthello() {
		return(new File(getRepertoireParDefaut().getAbsolutePath()
				+ "\\" + NOM_REPERTOIRE)).mkdirs();
	}

	/**
	 * Retourne l'emplacement de fichier par d�faut de l'utilisateur
	 * @return emplacementParDefaut	l'emplacement par d�faut
	 */
	public static File getRepertoireParDefaut() {
		File emplacementParDefaut =
				FileSystemView.getFileSystemView()
				.getDefaultDirectory();
		return emplacementParDefaut;
	}

	/**
	 * @return	le nom de r�pertoire dans lequel s'effectuent les sauvegardes
	 */
	public static String getNomRepertoire() {
		return NOM_REPERTOIRE;
	}

	/**
	 * 
	 * @return le r�pertoire par d�faut o� se trouvent les sauvegardes
	 */
	public static File getPathRepertoireOthello() {
		return(new File(getRepertoireParDefaut().getAbsolutePath()
				+ "\\" + NOM_REPERTOIRE));
	}
	/**
	 * 
	 * @return emplacement de sauvegarde des scores
	 */
	public static String getEmplacementSaveScores(){
		return (getRepertoireParDefaut() +"\\Othello\\scoresOthello.sothl");
	}
	/**
	 * Enregistre un objet {@code Partie} dans un fichier binaire en ".othl"
	 * @return {@code true} si la partie a bien �t� enregistr�e, {@code false} sinon
	 */
	public static boolean enregistrerScores(Scores aEnregistrer) {

		String nomFichier = PREFIXE_SAUVEGARDE_SCORES + EXTENSION_SAUVEGARDE_SCORES;

		boolean reussi = true;      // vrai ssi l'enregistrement a r�ussi

		// cr�ation et ouverture du fichier NOM_FICHIER_HORAIRE
		try(ObjectOutputStream fichier = new ObjectOutputStream(
				new FileOutputStream(
						getPathRepertoireOthello().getAbsolutePath()
						+ "\\"
						+ nomFichier))) {

			// on �crit l'objet argument dans le fichier
			fichier.writeObject(aEnregistrer);  
		}  catch (IOException erreur) {

			// une erreur s'est produite lors de l'acc�s au fichier
			reussi = false;
		}
		return reussi;        
	}
	/**
	 * Restauration des scores de l'utilisateur.
	 * Les scores est suppos� �tre dans un fichier "scoresOthello.sothl" 
	 * @param nomFichier fichier de scores 
	 * @return un objet de type {@code Scores}
	 *         ou bien la valeur null si un probl�me emp�che l'acc�s au fichier
	 */
	public static Scores restaurerScores(String nomFichier)  {

		// objet tampon dans lequel est plac� l'objet lu dans le fichier  
		Scores tampon = null;  

		// ouverture du fichier et lecture de l'objet qu'il contient
		try(ObjectInputStream fichier = new ObjectInputStream(
				new FileInputStream(nomFichier))) {           

			// lecture de l'objet contenu dans le fichier
			tampon = (Scores) fichier.readObject();

		} catch (ClassNotFoundException erreur) {
			System.err.println("ClassNotFoundException");
			System.err.println(erreur);
			// la donn�e pr�sente dans le fichier n'est pas un objet           
		} catch (ClassCastException erreur) {
			System.err.println("ClassCastException");
			System.err.println(erreur);
			// la donn�e lue dans le fichier n'est pas un objet � 2 dimensions    
		} catch (IOException erreur) {
			System.err.println("IOException");
			System.err.println(erreur);
			// probl�me d'acc�s au fichier
		}
		return tampon;
	}
	
	/**
	 * Enregistre un objet {@code Partie} dans un fichier binaire en ".othl"
	 * @return {@code true} si la partie a bien �t� enregistr�e, {@code false} sinon
	 */
	public static boolean enregistrerPartie(Partie aEnregistrer) {
		String nomFichier = nouveauNomFichierPartie();

		boolean reussi = true;      // vrai ssi l'enregistrement a r�ussi

		// cr�ation et ouverture du fichier NOM_FICHIER_HORAIRE
		try(ObjectOutputStream fichier = new ObjectOutputStream(
				new FileOutputStream(
						getPathRepertoireOthello().getAbsolutePath()
						+ "\\"
						+ nomFichier))) {

			// on �crit l'objet argument dans le fichier
			fichier.writeObject(aEnregistrer);  
		}  catch (IOException erreur) {

			// une erreur s'est produite lors de l'acc�s au fichier
			reussi = false;
		}
		return reussi;        
	}
	
	/**
	 * Restaure une partie � partir d'un fichier ".othl"
	 * @return un objet {@code Partie} restaur� � partir de la sauvegarde
	 *         ou bien la valeur null si un probl�me emp�che l'acc�s au fichier
	 */
	public static Partie restaurerPartie(String nomFichier)  {

		// objet tampon dans lequel est plac� l'objet lu dans le fichier  
		Partie tampon = null;  

		// ouverture du fichier et lecture de l'objet qu'il contient
		try(ObjectInputStream fichier = new ObjectInputStream(
				new FileInputStream(nomFichier))) {           

			// lecture de l'objet contenu dans le fichier
			tampon = (Partie) fichier.readObject();

		} catch (ClassNotFoundException erreur) {
			System.err.println("ClassNotFoundException");
			System.err.println(erreur);
			// la donn�e pr�sente dans le fichier n'est pas un objet           
		} catch (ClassCastException erreur) {
			System.err.println("ClassCastException");
			System.err.println(erreur);
			// la donn�e lue dans le fichier n'est pas un objet � 2 dimensions    
		} catch (IOException erreur) {
			System.err.println("IOException");
			System.err.println(erreur);
			// probl�me d'acc�s au fichier
		}
		return tampon;
	}
}
