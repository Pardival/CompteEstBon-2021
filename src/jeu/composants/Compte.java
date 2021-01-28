/* Niveau.java            17/01/2021
 * Pas de copyright ni copyleft
 */
package jeu.composants;

/**
 * <p>Représente le compte à obtenir en fonction
 * du niveau de jeu ainsi que
 * du tirage.</p>
 * <p>Cette objet contient les informations principales pour lélaboration de vérification
 * et de recherche de solutions</p>
 * @author Jordan LECLERC, Kevin DUFOUR
 */
public class Compte {
    /* Le niveau de jeu */
    private Niveau niveauConfigurateur;

    /* Le tirage à résoudre en fonction du niveau de jeu */
    private  Tirage aResoudre;

    /*====== CONSTRUCTEUR ======*/
    /**
     * <p>Constructeur</p>
     * @param niveauConfigurateur Le niveau de jeu
     * @param aResoudre Le tirage à résoudre en fonction du niveau de jeu
     */
    public Compte(Niveau niveauConfigurateur, Tirage aResoudre) {
        this.niveauConfigurateur = niveauConfigurateur;
        this.aResoudre = aResoudre;
    }
}
