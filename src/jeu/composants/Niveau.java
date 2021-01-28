/* Niveau.java            16/01/2021
 * Pas de copyright ni copyleft
 */
package jeu.composants;

import java.util.ArrayList;

/**
 * <p>Definit un niveau de jeu en fonction
 * de sa difficulté</p>
 * @author Jordan LECLERC, Kevin DUFOUR
 */
public class Niveau {
    /** <p>Contient les différents niveaux de jeu possibles</p> */
    public final static int[] LISTE_NIVEAUX = {1};

    /** <p>Contient le nombre d'opérande d'un tirage </p> */
    public final static int NB_OPERANDE_TIRAGE = 6;

    /* Contient la difficulté du niveau de jeu.
     * Plus le chiffre est grand, plus le niveau est difficile.
     *  1 est le niveau de difficulté par défaut.
     */
    private int difficulte;

    /* Contient la liste des opérandes du niveau de jeu. */
    private ArrayList<OperandeDeBase> operandesUtilisables;

    /* Interval dans lequel le compte doit être obtenu. */
    private int minimumCompte;
    private int maximumCompte;

    /*====== CONSTRUCTEUR ======*/
    /**
     * <p>Consructeur du niveau de jeu
     * initialise l'objet en fonction du
     * niveau de difficulte choisi.</p>
     * <p>Voir la constante <em>LISTE_NIVEAUX</em>
     * pour consulter les difficultés possibles.</p>
     * @param difficulte la difficulté du jeu
     */
    public Niveau(int difficulte) {
        this.fabriquerNiveau(difficulte);
    }

    /*====== METHODES PRIVÉE ======*/
    /**
     * <p>paramètre le niveau en fonction
     * de la difficulté passé en argument.</p>
     * <p>Voir la constante <strong>LISTE_NIVEAUX</strong>
     * pour consulter les difficultés possibles.</p>
     * @param difficulte la difficulté du jeu
     */
    private void fabriquerNiveau(int difficulte) {
        /* Initialisation pour une difficulté 1 */
        switch(difficulte) {
            case 1:
                /* Interval du compte */
                this.minimumCompte = 101;
                this.maximumCompte = 999;
                this.operandesUtilisables = new ArrayList<>();

                /* Ajout des opérandes autorisés */
                for(int i = 1; i <= 10; i++) {
                    operandesUtilisables.add(new OperandeDeBase(i));
                }
                operandesUtilisables.add(new OperandeDeBase(25));
                operandesUtilisables.add(new OperandeDeBase(50));
                operandesUtilisables.add(new OperandeDeBase(75));
                operandesUtilisables.add(new OperandeDeBase(100));
        }
    }

    /**
     * <p>Compte le nombre de fois l'opérande aCherche
     * dans la collection donnée</p>
     * @param aChercher l'opérande à trouver
     * @param collection la collection dans laquelle cherchée
     * @return  le nombre de fois l'opérande cherché
     */
    private int countOperandes(int aChercher, ArrayList<OperandeDeBase> collection) {
        int count = 0;

        /* Balayage de la collection pour compter*/
        for (int i = 0; i < collection.size(); i++) {
            if (aChercher == collection.get(i).getOperande()) {
                count++;
            }
        }
        return count;
    }

    /*====== METHODES PUBLIC ======*/
    /**
     * <p>Génère une liste d'opérandes de base
     * et un résultat à obtenir en fonction du
     * niveau de jeu.</p>
     * @return Le Tirage adéquat.
     */
    public Tirage fabriquerTirage() {
        /* On choisi un nombre au hasard entre minimumCompte et maximumCompte */
        ArrayList<OperandeDeBase> operandeInitiales = new ArrayList<>();
        int tailleCollectionOperande = operandesUtilisables.size();
        int aCalculer =
                this.minimumCompte + (int)(Math.random() * ((this.maximumCompte - this.minimumCompte) + 1));

        /* On choisi des operandes au hasard */
        for (int i = 0; i < NB_OPERANDE_TIRAGE; i++) {
            int randOperande;

            /* On n'ajoute pas si il existe plus de 2 exemplaires */
            do {
                randOperande = (int)(Math.random() * ((tailleCollectionOperande)));
            } while (this.countOperandes(this.operandesUtilisables.get(randOperande).getOperande(),
                    operandeInitiales ) >= 2 );

            operandeInitiales.add(new OperandeDeBase(this.operandesUtilisables.get(randOperande).getOperande()));
        }
        return new Tirage(aCalculer, operandeInitiales);    //STUB
    }

    /**
     * <p>Génère une liste d'opérandes de base
     * et un résultat à obtenir en fonction du
     * niveau de jeu.</p>
     * @return Le Tirage adéquat.
     */
    public Tirage fabriquerTirage(ArrayList<OperandeDeBase> operandeInitiales, int aCalculer) {
        /*TODO la méthode  selectionner une seul fois si le nombre > 10 */

        return new Tirage(aCalculer, operandeInitiales);
    }

    /*====== GETTER ======*/
    /**
     * <p>Voir la constante <strong>LISTE_NIVEAUX</strong>
     * pour consulter les difficultés possibles.</p>
     * @return La difficulté du niveau de jeu.
     */
    public int getDifficulte() {
        return this.difficulte;
    }

    /**
     * @return La borne inférieur de l'interval du compte
     */
    public int getMinimumCompte() {
        return minimumCompte;
    }

    /**
     * @return La borne superieur de l'interval du compte
     */
    public int getMaximumCompte() {
        return maximumCompte;
    }
}
