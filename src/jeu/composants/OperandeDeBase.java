/* OperandeDeBase.java            16/01/2021
 * Pas de copyright ni copyleft
 */
package jeu.composants;

/**
 * <p>Représente une opérande.
 * cette opérande est un entier
 * utile pour l'élaboration d'un calcul elémentaire.</p>
 * @author Jordan LECLERC, Kevin DUFOUR
 */
public class OperandeDeBase {
    /* Entier représentant */
    private int operande;

    /* Sous format String */
    private String operandeStringer;

    /*====== CONSTRUCTEUR ======*/
    /**
     * <p>Constructeur initialisant l'opérande
     * de base en fonciton avec un entier.</p>
     * @param operande l'entier choisi
     */
    public OperandeDeBase(int operande) {
        this.operande = operande;
        this.operandeStringer = String.valueOf(operande);
    }

    /*====== GETTER ======*/
    /**
     * @return L'entier représentant l'opérande
     */
    public int getOperande() {
        return operande;
    }

    /**
     * @return L'entier représentant l'opérande sous format String
     */
    public String getOperandeStringer() {
        return operandeStringer;
    }
}
