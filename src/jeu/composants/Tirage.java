/* Tirage.java            16/01/2021
 * Pas de copyright ni copyleft
 */
package jeu.composants;

import java.util.ArrayList;

/**
 * <p>Représentation d'un Tirage.
 * Celui-ci est crée en fonction du niveau de jeu. </p>
 * @author Jordan LECLERC, Kevin DUFOUR
 */
public class Tirage {
    /* Compte à retrouver */
    private int aCalculer;

    /* OperandeDeBase initiale généré das un premier temps */
    private ArrayList<OperandeDeBase> operandeInitiales;

    /*====== CONSTRUCTEUR ======*/
    /**
     * <p> Construceur initialisant les paramètres
     * du Tirage.</p>
     * @param aCalculer le Compte à trouver
     * @param operandeInitiales les opérandes de départ
     */
    public Tirage(int aCalculer, ArrayList<OperandeDeBase> operandeInitiales) {
        this.aCalculer = aCalculer;
        this.operandeInitiales = operandeInitiales;
    }

    /*====== GETTER ======*/
    /**
     * @return le compte
     */
    public int getaCalculer() {
        return aCalculer;
    }

    /**
     * @return les opérandes initiales
     */
    public ArrayList<OperandeDeBase> getOperandeInitiales() {
        return operandeInitiales;
    }

    /**
     * @return les opérandes initiales
     */
    public ArrayList<Integer> getOperandeInitialesInteger() {
        ArrayList<Integer> listOperandes = new ArrayList<>();

        for (OperandeDeBase operande: getOperandeInitiales()) {
            listOperandes.add(operande.getOperande());
        }

        return listOperandes;
    }

    /*====== METHODES PUBLIC ======*/
    /**
     * <p>Ajoute une nouvelle opérande dans les initiales</p>>
     * @param aAjouter opérande à ajouter
     */
    public void ajouterOperande(OperandeDeBase aAjouter) {
        operandeInitiales.add(aAjouter);
    }

    /**
     * <p>Retire une nouvelle opérande dans les initiales</p>>
     * @param aRetirer opérande à retirer
     */
    public void retirerOperande(OperandeDeBase aRetirer) {
        for (int i = 0; i < operandeInitiales.size(); i++) {
            if(operandeInitiales.get(i).getOperande() == aRetirer.getOperande()) {
                operandeInitiales.remove(i);
                break;
            }
        }
    }

    /**
     * Verifie si une operande est présente dans le tableau
     * @return true si il est présent
     */
    public boolean estPresent(OperandeDeBase aVerifier) {
        for (int i = 0; i < operandeInitiales.size(); i++) {
            /* Si celui-ci est présent */
            if (operandeInitiales.get(i).getOperande() == aVerifier.getOperande()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        ArrayList<Integer> listOperandes = new ArrayList<>();

        for (OperandeDeBase operande: getOperandeInitiales()) {
            listOperandes.add(operande.getOperande());
        }

        return "Opérandes actuel : " + listOperandes + "\n"
                + "Compte a obtenir : " + getaCalculer() + "\n";
    }
}
