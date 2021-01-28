/**
 * TestBackTraking.Java         21/01/2021
 * No Copyright, ni Copyleft
 */

package jeu.test;

import jeu.composants.BackTracking;
import jeu.composants.Niveau;
import jeu.composants.Tirage;

import java.util.ArrayList;

/**
 * Test unitaire de la classe unitaire
 *  BackTracking.java
 * @author Jordan LECLERC, Kevin DUFOUR
 */
public class TestBackTracking {
    static Niveau niveau = new Niveau(1);
    static Tirage tirage = niveau.fabriquerTirage();


    public static void main(String[] args) {
        ArrayList<Integer> liste = new ArrayList<>();
        int aCalculer =777;

        liste.add(2);
        liste.add(1);
        liste.add(6);
        liste.add(2);
        liste.add(100);
        liste.add(3);

        while(true) {
            System.out.println(tirage.toString());
            System.out.println("Résolution du compte : ");

            BackTracking aResoudre = new BackTracking(tirage.getaCalculer(), tirage.getOperandeInitiales());
            aResoudre.resoudre(aResoudre.getaCalculer(), aResoudre.getOperandeInteger(), 0);
            //System.out.println(trouve);

            for (int e = 0; e < aResoudre.calculsFinaux.length; e++) {
                System.out.println(aResoudre.calculsFinaux[e]);
            }

            niveau = new Niveau(1);
            tirage = niveau.fabriquerTirage();

        }

        //BackTracking.resoudre(tirage.getaCalculer(), tirage.getOperandeInitialesInteger());
   }
}
