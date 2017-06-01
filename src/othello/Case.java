/*
 * Case.java                                            06/05/2017
 * Tous droits r�serv�s � l'IUT de Rodez
 */

package othello;

import java.io.Serializable;

/**
 * Case constituant le plateau de jeu
 * 
 * @author Vincent Galinier
 * @author Adrien Bouyssou
 * @author Kerian Georges
 * @author Arthur Pradier
 * @author Micka�l Queudet 
 */
public class Case implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Entier associ� � une case vide */
	public static final int COULEUR_NEUTRE = -1;

	/** Entier associ� � une case o� est pr�sent un pion blanc */
	public static final int COULEUR_BLANC = 0;

	/** Entier associ� � une case o� est pr�sent un pion noir */
	public static final int COULEUR_NOIR = 1;

	/** Couleur de la case */
	private int couleur;

	/** num�ro de la case */
	private int ligne;

	/** num�ro de la colonne */
	private int colonne;

	/** (constructeur d'�tat d'instance)
	 * Cr�ation d'une case vide aux coordon�es indiqu�es
	 * @param ligne		la ligne
	 * @param colonne	la colonne
	 */
	public Case(int ligne, int colonne) {
		// v�rifie que les coordon�es sont valide
		if ((0 <= ligne || ligne <= 7) &&
			(0 <= colonne || colonne <= 7)) {
			this.ligne = ligne;
			this.colonne = colonne;
			couleur = COULEUR_NEUTRE;
		}
	}

	/** (constructeur d'�tat d'instance)
	 * Case d'une case aux coordon�es indiqu�es 
	 * � laquelle on attribue une couleur
	 * @param ligne		la ligne
	 * @param colonne	la colonne
	 * @param couleur	la couleur
	 */
	public Case(int ligne, int colonne, int couleur) {
		// v�rifie que les coordon�es sont valides
		if ((0 <= ligne || ligne <= 7) &&
			(0 <= colonne || colonne <= 7) &&
			(-1 <= couleur || couleur <= 1)) {
			this.ligne = ligne;
			this.colonne = colonne;
			setCouleur(couleur);
		}
	}

	/**
	 * @return la couleur
	 */
	public int getCouleur() {
		return couleur;
	}

	/**
	 * Assigne � la case une nouvelle couleur
	 * @param nouvelleCouleur	la nouvelle couleur de la case
	 */
	public void setCouleur(int nouvelleCouleur) {
			couleur = nouvelleCouleur;
	}

	/**
	 * @return la ligne
	 */
	public int getLigne() {
		return ligne;
	}

	/**
	 * @return la colonne
	 */
	public int getColonne() {
		return colonne;
	}
	
	/**
	 * M�thode utilis� pour obtenir un affichage console
	 * @return	le caract�re associ� � la couleur de la case
	 */
	public char getCaractere() {
		char caractere = ' ';
		switch(couleur) {
		case 0 : caractere = 'B';
		break;
		case 1 : caractere = 'N';
		break;
		default : caractere = ' ';
		break;
		}

		return caractere;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Case [couleur=" + getCaractere() + ", ligne=" + ligne
				+ ", colonne=" + colonne + "]";
	}


}
