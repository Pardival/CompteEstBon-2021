/* Tirage.java            25/01/2021
 * Pas de copyright ni copyleft
 */
package jeu.composants;

import java.util.ArrayList;

/**
 * <p>Classe permetant
 * la recherche en BackTracking
 * en fonction de différentes variables d'état,
 * de contraintes et du résultat à obtenir</p>
 * @author Jordan LECLERC, Kevin DUFOUR
 */
public class BackTracking {

    /* Operateur utilisables */
    private static char[] operateurs = new char[]{'+', '-', '*', '/'};
    private String[] calculs = new String[5];

    private int aCalculer;
    private ArrayList<OperandeDeBase> operande;

    /* Meilleur compte trouvé  */
    private int best;

    public String[] calculsFinaux = new String[5];
    //static int[] resultats = new int[5];

    /**
     * CONSTRUCTEUR
     * @param aCalculer
     * @param operande
     */
    public BackTracking(int aCalculer, ArrayList<OperandeDeBase> operande) {
        this.aCalculer = aCalculer;
        this.operande = operande;
    }

    /**
     * @return les opérandes initiales
     */
    public ArrayList<Integer> getOperandeInteger() {
        ArrayList<Integer> listOperandes = new ArrayList<>();

        for (OperandeDeBase operande: operande) {
            listOperandes.add(operande.getOperande());
        }
        return listOperandes;
    }

    /**
     * @return aCalculer
     */
    public int getaCalculer() {
        return aCalculer;
    }

    /**
     *
     * @param best le meilleur resultat
     */
    public void setBest(int best) {
        this.best = best;
    }

    /**
     * Permet de resoudre le compte
     * @param aCalculer le compt a trouver
     * @param operandes les opérandes autorisé
     * @param niveau le niveau ou nous somme dans l'arbre
     */
    public void resoudre(int aCalculer, ArrayList<Integer> operandes, int niveau ) {
        /* Contient le résutat temporaire */
        int tempo = 0;

        /* On boucle  récupère l'opérande gauche */
        for (int iOerandeGauche = 0; iOerandeGauche < operandes.size(); iOerandeGauche++) {
            /* On boucle  récupère l'opérande droite pour effectuer les potielles opréations */
            for (int iOperandeDroit = 0; iOperandeDroit < operandes.size() ; iOperandeDroit++ ) {
                /* Si l'opérande droite n'est pas la même que l'opérande gauche */
                if (iOperandeDroit != iOerandeGauche) {
                    /* On effectue toutes les operations possible entre les deux opérandes */
                    for(int iOperateur = 0; iOperateur < operateurs.length; iOperateur++) {
                        /* On vérifie quelle opération il faut faire (+ -, *, /) */
                        switch (operateurs[iOperateur]) {
                            case '+':
                                tempo = operandes.get(iOerandeGauche) + operandes.get(iOperandeDroit);
                                break;

                            case '-':
                                tempo = operandes.get(iOerandeGauche) - operandes.get(iOperandeDroit);
                                break;

                            case '*':
                                tempo = operandes.get(iOerandeGauche) * operandes.get(iOperandeDroit);
                                break;

                            case '/':
                                /* Si opérande droite n'est pas 0 */
                                if (operandes.get(iOperandeDroit) != 0) {
                                    /* Si la division euclidienne retourne 0  */
                                    if (operandes.get(iOerandeGauche) % operandes.get(iOperandeDroit) == 0) {
                                        /* On peut alors effectuer la division entière */
                                        tempo = operandes.get(iOerandeGauche) / operandes.get(iOperandeDroit);
                                    } else {
                                        tempo = aCalculer + 1;
                                    }
                                } else {
                                    tempo = aCalculer + 1;
                                }
                        }

                        /* Si tempo est la meilleur solution actuel */
                        if (tempo >= best && tempo <= aCalculer) {
                            /*On enregistre l'opération' */
                            calculs[niveau] = operandes.get(iOerandeGauche)
                                    + " " + operateurs[iOperateur] + " "
                                    + operandes.get(iOperandeDroit)
                                    + " " + '=' + " " + tempo;
                            //resultats[niveau] = tempo;
                            best = tempo;
                            calculsFinaux = calculs.clone();

                        /* Si non on vérifie que celui-ci est inferieur au compte et on continue
                        * a s'enfoncer dans l'arbre */
                        } else if (tempo < aCalculer && operandes.size() != 1) {
                            /* On supprime les opérandes gauche et droite utilisé */
                            ArrayList<Integer> nouvellesOperandes = (ArrayList<Integer>) operandes.clone();
                            nouvellesOperandes.set(iOerandeGauche,tempo);
                            nouvellesOperandes.remove(iOperandeDroit);

                            /* On enregistre la dernière opération opération' */
                            calculs[niveau] = operandes.get(iOerandeGauche)
                                    + " " + operateurs[iOperateur] + " "
                                    + operandes.get(iOperandeDroit)
                                    + " " + '=' + " " + tempo;
                            //resultats[niveau] = tempo;

                            niveau+=1;  // On passe à un niveau superieur
                            resoudre(aCalculer, nouvellesOperandes, niveau);
                            niveau-=1;  // On passe a un niveau inferieur
                            calculs[niveau] = null;     // On met a null la case du niveau superieur (non utilisée)
                            //resultats[niveau] = 10000;
                        }
                    }
                }
            }
        }
    }
}
