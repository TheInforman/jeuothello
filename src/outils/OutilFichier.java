package outils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Calendar;

import othello.Partie;

public class OutilFichier {
	
    /**
     * TODO : Changer commentaires
     * Enregistre dans le fichier ayant pour nom NOM_FICHIER_HORAIRE
     * le tableau � 2 dimensions contenant les horaires de bus.
     * Ce tableau contient des entiers
     * @param table  tableau � 2 dimensions contenant des entiers
     * @return un bool�en �gal � vrai ssi la sauvegarde a bien �t� effecu�e
     */
    public static boolean enregistrerPartie(Partie aEnregistrer) {
    	
    	
		String nomFichier = nouveauNomFichier();

        boolean reussi = true;      // vrai ssi l'enregistrement a r�ussi
        
        // cr�ation et ouverture du fichier NOM_FICHIER_HORAIRE
        try(ObjectOutputStream fichier = new ObjectOutputStream(
                    new FileOutputStream(nomFichier))) {
                       
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
            // la donn�e pr�sente dans le fichier n'est pas un objet           
        } catch (ClassCastException erreur) {
        	System.err.println("ClassCastException");
            // la donn�e lue dans le fichier n'est pas un objet � 2 dimensions    
        } catch (IOException erreur) {
        	System.err.println("IOException");
        	System.err.println(erreur);
            // probl�me d'acc�s au fichier
        }
        return tampon;
    }

	/**
	 * TODO : JDOC
	 * @return
	 */
	public static String nouveauNomFichier() {
		Calendar calendrierMaintenant = Calendar.getInstance();
		int heure = calendrierMaintenant.get(Calendar.HOUR_OF_DAY);
		int minute = calendrierMaintenant.get(Calendar.MINUTE);
		int jour = calendrierMaintenant.get(Calendar.DAY_OF_MONTH);
		int mois = calendrierMaintenant.get(Calendar.MONTH) + 1;
		int annee = calendrierMaintenant.get(Calendar.YEAR);
		
		return "partieOthello_" + jour + "_" + mois + "_" + annee + "_"
				+ heure + "h" + minute + ".bin";
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
}
