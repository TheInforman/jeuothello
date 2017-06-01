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

public class OutilFichier {

	private static final String NOM_REPERTOIRE = "Othello";
	private static final String PREFIXE_SAUVEGARDE_PARTIE = "sauvegardeOthello_";
	private static final String PREFIXE_SAUVEGARDE_SCORES = "scoresOthello";
	private static final String EXTENSION_SAUVEGARDE_PARTIE = ".othl";
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
	 * TODO : Changer commentaires
	 * Enregistre dans le fichier ayant pour nom NOM_FICHIER_HORAIRE
	 * le tableau � 2 dimensions contenant les horaires de bus.
	 * Ce tableau contient des entiers
	 * @param table  tableau � 2 dimensions contenant des entiers
	 * @return un bool�en �gal � vrai ssi la sauvegarde a bien �t� effecu�e
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
	 * TODO : Changer Commentaires
	 * Restauration des horaires de bus.
	 * Les horaires sont suppos�s �tre pr�sents dans le fichier de nom 
	 * NOM_FICHIER_HORAIRE, sous la forme d'un tableau � 2 dimensions contenant
	 * des valeurs de type entier
	 * @return un tableau � 2 dimensions contenant des entiers
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

		return PREFIXE_SAUVEGARDE_PARTIE + jour + "_" + mois + "_" + annee + "_"
		+ heure + "h" + minute + EXTENSION_SAUVEGARDE_PARTIE;
	}

	/**
	 * Cr�e un nom de fichier en .sothl constitu� d'une constante ainsi
	 * que de l'heure, de la minute, du mois et de l'ann�e courant.
	 * 
	 * @return		un nom de fichier de sauvegarde
	 */
	public static String nouveauNomFichierScores() {
		Calendar calendrierMaintenant = Calendar.getInstance();
		int heure = calendrierMaintenant.get(Calendar.HOUR_OF_DAY);
		int minute = calendrierMaintenant.get(Calendar.MINUTE);
		int jour = calendrierMaintenant.get(Calendar.DAY_OF_MONTH);
		int mois = calendrierMaintenant.get(Calendar.MONTH) + 1;
		int annee = calendrierMaintenant.get(Calendar.YEAR);

		return PREFIXE_SAUVEGARDE_SCORES + jour + "_" + mois + "_" + annee + "_"
		+ heure + "h" + minute + EXTENSION_SAUVEGARDE_SCORES;
	}

	/**
	 * @return		vrai si le r�pertoire NOM_REPERTOIRE existe dans le
	 * 				r�pertoire par d�faut, faux sinon
	 */
	public static boolean isRepertoireOthelloExistant() {
		String repertoireDefaut = getRepertoireParDefaut().getAbsolutePath()
				+ "\\" + NOM_REPERTOIRE;
		System.out.println(repertoireDefaut);

		Path path = Paths.get(repertoireDefaut);

		return Files.exists(path);
	}

	/**
	 * TODO : JDOC
	 * @return
	 */
	public static void supprimerSauvegarde(Path nomSauvegarde) {
		try {
			Files.delete(nomSauvegarde);
		} catch (NoSuchFileException x) {
			System.err.format("%s: no such" + " file or directory%n", nomSauvegarde);
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
	 * @return		false si le r�pertoire n'a pas pu �tre cr��,
	 * 				true sinon
	 */
	public static boolean creerRepertoireOthello() {
		return(new File(getRepertoireParDefaut().getAbsolutePath()
				+ "\\" + NOM_REPERTOIRE)).mkdirs();
	}

	/**
	 * Retourne l'emplacement de fichiers par d�faut de l'utilisateur
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

	public static File getPathRepertoireOthello() {
		return(new File(getRepertoireParDefaut().getAbsolutePath()
				+ "\\" + NOM_REPERTOIRE));
	}
	/**
	 * Enregistre l'objet {@code Scores} pass� en param�tre
	 * @param aEnregistrer Scores � enregistrer
	 * @return un bool�en �gal � vrai ssi la sauvegarde a bien �t� effecu�e
	 */
	public static boolean enregistrerScores(Scores aEnregistrer) {

		String nomFichier = PREFIXE_SAUVEGARDE_SCORES + ".sothl";

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
}
