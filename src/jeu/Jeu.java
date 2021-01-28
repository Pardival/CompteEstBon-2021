/* Jeu.java            16/01/2021
 * Pas de copyright ni copyleft
 */
package jeu;

import jeu.composants.BackTracking;
import jeu.composants.Niveau;
import jeu.composants.OperandeDeBase;
import jeu.composants.Tirage;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.Scanner;

/** <p>Classe principal réunnissant tout les composants utiles
 *  pour former le je du compte est bon. </p>
 * @author Jordan LECLERC, Kevin DUFOUR
 */
public class Jeu {

    /**
     * Ce qui est affiché au lancement du jeu
     */
    public static void afficherRunning() {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+             LE COMPTE EST BON             +");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+                 VERSION 1                 +");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+    PAR JORDAN LECLERC ET KEVIN DUFOUR     +");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println();
        System.out.println();
    }

    /**
     * Affiche le menu du jeu
     */
    public static void afficherMenuPrincipal() {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+                  ACCUEIL                  +");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+ A) Jouer                                  +");
        System.out.println("+ B) Voir les règles du jeu                 +");
        System.out.println("+ Q) Quitter                                +");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    }

    /**
     * Système de sélection dans le menu principal
     */
    public static char selectionnerMenuPrincipal() {
        /* Saisie du charactère */
        Scanner scanner = new Scanner(System.in);
        System.out.print("Veuillez choisir un choix : ");

        return scanner.next().charAt(0);
    }

    /**
     * Affiche les règles du jeu
     */
    public static void afficherRègleDujeu() {
        System.out.println();
        System.out.println();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+                REGLE DU JEU               +");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+ Le joueur dispose d'un certain temps pour +");
        System.out.println("+ essayer d'obtenir un nombre               +");
        System.out.println("+ le plus proche du compte. Le joueur a     +");
        System.out.println("+ la possibilite d'effectuer des opérations +");
        System.out.println("+ arithmétiques avec lesopérandes  que le   +");
        System.out.println("+ jeu fournit afin de se rapprocher au mieu +");
        System.out.println("+ du compte.                                +");
        System.out.println("+                                           +");
        System.out.println("+ Le joueur doit présenter les calculs et   +");
        System.out.println("+ et les résultats de ses calculs dans un   +");
        System.out.println("+ ordre coherent : il faut presenter le     +");
        System.out.println("+ calcul conduisant a un resultat avant de  +");
        System.out.println("+ pouvoir utiliser ce resultat comme        +");
        System.out.println("+ operande lor d'une operation.             +");
        System.out.println("+                                           +");
        System.out.println("+ ATTENTION : Toute erreur de calcul de la  +");
        System.out.println("+ part du joueur entraine un score nul.      +");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println();
        System.out.println();
    }

    /**
     * Affiche les modes de jeux possible
     */
    public static void afficherModeDeJeu() {
        System.out.println();
        System.out.println();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+                MODE DE JEU                +");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+ A) Partie individuelle                    +");
        System.out.println("+ B) Demander la résolution d'un compte     +");
        System.out.println("+ Q) Retour au menu principal               +");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    }

    /**
     * Permet de choisir le mode de jeu
     */
    public static char selectionnerModeDeJeu() {
        /* Saisie du charactère */
        Scanner scanner = new Scanner(System.in);
        System.out.print("Veuillez choisir un mode de jeu : ");

        return scanner.next().charAt(0);
    }

    /**
     * <p>Permet de mettre en pause le déroulement du programme</p>
     */
    public static void suivant() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Appuyer sur Entrer pour continuer... ");
        scanner.nextLine();
    }


    public static void creerPartie() {
        Niveau niveau = new Niveau(1);
        Tirage tirage = niveau.fabriquerTirage();
        Scanner scanner = new Scanner(System.in);
        String operation = null;
        OperandeDeBase operandeGauche = null;
        OperandeDeBase operandeDroit = null;
        OperandeDeBase operandeCalculee = null;
        String[] operateur;
        boolean  ok = false;
        boolean reg;

        /* Résolution de l'ordinateur */
        BackTracking aResoudre = new BackTracking(tirage.getaCalculer(), tirage.getOperandeInitiales());
        aResoudre.resoudre(aResoudre.getaCalculer(), aResoudre.getOperandeInteger(), 0);
        aResoudre.setBest(Integer.MIN_VALUE);

        System.out.println(tirage.toString());
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Entrer une opération : ");

        for(int i = 0; i < Niveau.NB_OPERANDE_TIRAGE; i++) {
            operation = scanner.nextLine();
            reg = Pattern.matches("\s*([0-9]+)\s*(\\+|-|/|\\*)\s*([0-9]+)\s*=\s*([0-9]+)\s*", operation);

            if (reg) {
                String cleanOperation = operation.replaceAll("\\s","");
                String[] operandesString = cleanOperation.split("\\+|-|\\*|/|=");
                 operateur = cleanOperation.split("([0-9]+)");
                 operandeGauche = new OperandeDeBase(Integer.parseInt(operandesString[0]));
                 operandeDroit = new OperandeDeBase(Integer.parseInt(operandesString[1]));
                 operandeCalculee = new OperandeDeBase(Integer.parseInt(operandesString[2]));
            } else {
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("+ PERDU => l'entrée est fausse              +");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                break;
            }

            // verifier si les operandes sont dans le tirage
            if (tirage.estPresent(operandeGauche) == false) {
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("+ PERDU => l'operande gauche n'existe pas   +");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                break;
            }

            if(tirage.estPresent(operandeDroit) == false) {
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("+ PERDU => l'operande droite n'existe pas   +");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                break;
            }

            int calcul = 0;
            if (operateur[1].equals("+")) {
                calcul = operandeGauche.getOperande() + operandeDroit.getOperande();
            } else if (operateur[1].equals("-")) {
                calcul = operandeGauche.getOperande() - operandeDroit.getOperande();
            } else if (operateur[1].equals("*")) {
                calcul = operandeGauche.getOperande() * operandeDroit.getOperande();
            } else if (operateur[1].equals("/")) {
                if (operandeDroit.getOperande() != 0) {
                    if (operandeGauche.getOperande() % operandeDroit.getOperande() == 0) {
                        calcul = operandeGauche.getOperande() / operandeDroit.getOperande();
                    } else {
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("+ PERDU => On ne peut pas diviser pas 0     +");
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                        break;
                    }
                } else {
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("+ PERDU => La diision a un reste            +");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                    break;
                }
            } else {
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("+ PERDU => l'operateur nest pas bon  +");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                break;
            }

            // verifier si l'opértion est bonne
            if (calcul != operandeCalculee.getOperande()) {
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("+ PERDU => le résultat est faux             +");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                break;
            }

            // Supprimer les operande gauche et opérande droite du tirage
            tirage.retirerOperande(operandeDroit);
            tirage.retirerOperande(operandeGauche);

            // Rajouter Operande calculée dans le tirage
            tirage.ajouterOperande(operandeCalculee);

            if (operandeCalculee.getOperande() == tirage.getaCalculer()) {
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("+ BRAVO VOUS AVEZ TROUVÉ LE COMPTE          +");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                break;
            }

            if (tirage.getOperandeInitiales().size() == 1) {
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("+ FIN DU COMPTE                             +");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                break;
            }
            System.out.println(tirage.toString());
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Entrer une opération : ");
        }

        System.out.println();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+ SOLUTION DE L'ORDINATEUR                  +");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");

        for (int e = 0; e < aResoudre.calculsFinaux.length; e++) {
            System.out.println(aResoudre.calculsFinaux[e]);
        }
    }

    public static void resoudreCompte() {
        int aCalculer;
        int autorise;
        ArrayList<OperandeDeBase> operandes = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        Niveau niveau = new Niveau(1);
        Tirage tirage = niveau.fabriquerTirage();
        //System.out.println(tirage.toString());

        System.out.println("Veuillez choisir le compte à obtenir : ");
        aCalculer = scanner.nextInt();

        System.out.println();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println();

        for (int i = 0; i < Niveau.NB_OPERANDE_TIRAGE; i++) {
            int rang = i+1;
            System.out.println("Veuillez choisir l'operande " + rang + " : ");
            autorise = scanner.nextInt();

            operandes.add(new OperandeDeBase(autorise));
        }

        System.out.println();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println();
        System.out.println("Opérande autorisées : " + operandes.toString());
        System.out.println("Compte à calculer : " + aCalculer);
        System.out.println();
        System.out.println("Résolution du compte : ");

        BackTracking aResoudre = new BackTracking(aCalculer, operandes);
        aResoudre.resoudre(aResoudre.getaCalculer(), aResoudre.getOperandeInteger(), 0);
        aResoudre.setBest(Integer.MIN_VALUE);

        for (int e = 0; e < aResoudre.calculsFinaux.length; e++) {
            System.out.println(aResoudre.calculsFinaux[e]);
        }
    }

    /**
     * <p>Methode principale du jeu,
     * contient la boucle de jeu.</p>
     * @param args non utilisé
     */
    public static void main(String[] args) {
        char choixMenuPrincipal = 'Z';      /* Valeur par défaut */
        char choixMenuModeDeJeu = 'Z';      /* Valeur par défaut */

        afficherRunning();
        afficherMenuPrincipal();

        /* Tant que le joueur ne veut pas quitter le jeu */
        while (choixMenuPrincipal != 'Q' && choixMenuPrincipal != 'q') {
            choixMenuPrincipal = selectionnerMenuPrincipal();

            switch(choixMenuPrincipal) {
                /* Processus lors d'une partie */
                case 'A':
                case 'a':
                    choixMenuModeDeJeu = 'Z';
                    afficherModeDeJeu();

                    /* Tant que le joueur ne veut pas retourner au menu principal */
                    while (choixMenuModeDeJeu != 'Q' && choixMenuModeDeJeu != 'q') {
                        choixMenuModeDeJeu = selectionnerModeDeJeu();

                        switch(choixMenuModeDeJeu) {
                            case 'A':
                            case 'a':
                                creerPartie();
                                suivant();
                                afficherModeDeJeu();
                                break;

                            case 'B':
                            case 'b':
                                resoudreCompte();
                                suivant();
                                afficherModeDeJeu();
                        }
                    }
                    System.out.println();
                    System.out.println();
                    afficherMenuPrincipal();
                    break;
                /* Processus d'affichage des rèlges */
                case 'B':
                case 'b':
                    afficherRègleDujeu();

                    /* mise en pause du jeu */
                    suivant();

                    System.out.println();
                    System.out.println();
                    afficherMenuPrincipal();
            }
        }
    }
}
