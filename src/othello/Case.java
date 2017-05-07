/*
 * Case.java                                            06/05/2017
 * Groupe  Adrien Bouyssou, Vincent Galinier, Kerian Georges,
 *         Arthur Pradier, Mickaël Queudet
 */

package othello;

/**
 * Case constituant le plateau de jeu
 * @author Vincent G.
 */
public class Case {
	/** Entier associé à une case vide */
	public static final int COULEUR_NEUTRE = -1;
	
	/** Entier associé à une case où est présent un pion blanc */
	public static final int COULEUR_BLANC = 0;
	
	/** Entier associé à une case où est présent un pion noir */
	public static final int COULEUR_NOIR = 1;
	
	/** Couleur de la case */
	private int couleur;
	
	/** numero de la case */
	private int ligne;
	
	/** numero de la colonne */
	private int colonne;
	
	
	//TODO où est la correspondance numéro de couleur - couleur ?
	
    /** (constructeur d'état d'instance)
     * Case définie par sa position et sa couleur
     * @param ligne		la ligne
     * @param colonne	la colonne
     */
	public Case(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		couleur = COULEUR_NEUTRE;
	}

    /** (constructeur d'état d'instance)
     * Case définie par sa position et sa couleur
     * @param ligne		la ligne
     * @param colonne	la colonne
     * @param couleur	la couleur
     */
	public Case(int ligne, int colonne, int couleur) {
		this.ligne = ligne;
		this.colonne = colonne;
		couleur = COULEUR_NEUTRE;
	}
	
	/**
	 * @return la couleur
	 */
	public int getCouleur() {
		return couleur;
	}
	
	/**
	 * Assigne à la case une nouvelle couleur si celle-ci est valide
	 * @param nouvelleCouleur		la nouvelle couleur de la case
	 */
	public void setCouleur(int nouvelleCouleur) {
		if (couleurValide(nouvelleCouleur)){
			couleur = nouvelleCouleur;
		}
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
	
	private boolean couleurValide(int couleurATester) {
		switch (couleurATester) {
			case COULEUR_NEUTRE : //fallsthrough
			case COULEUR_BLANC  : //fallsthrough
			case COULEUR_NOIR   :	return true;
			default : return false;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Case [couleur=" + couleur + ", ligne=" + ligne + ", colonne=" + colonne + "]";
	}
	
	/**
	 * 
	 * @return		le caractère associé à la couleur de la case
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
	
	
}
