package org.example;

import java.util.*;
import java.lang.*;

public class MasterMindBase {

    //.........................................................................
    // OUTILS DE BASE
    //.........................................................................
    
    // fonctions classiques sur les tableaux

    /** pré-requis : nb >= 0
	résultat : un tableau de nb entiers égaux à val
    */
    public static int[] initTab(int nb, int val){ //Fait
        int[] t = new int[nb];
        Arrays.fill(t, val);
        return t;
    }

    //______________________________________________
    
    /** pré-requis : aucun
	résultat : une copie de tab
    */
    public static int[] copieTab(int[] tab){ //Fait
        return tab;
    } //fait

    //______________________________________________
    
    /** pré-requis : aucun
	résultat : la liste des éléments de t entre parenthèses et séparés par des virgules
    */
    public static String listElem(char[] t){ //Fait
        String result = "(";
        for(int i=0; i<t.length; i++){
            if (i>0){
                result = result + "," + t[i];
            } else {
                result = result + t[i];
            }
        }
        result = result + ")";
        System.out.println(result);
        return result;
    }

    //______________________________________________
    
    /** pré-requis : aucun
	résultat : le plus grand indice d'une case de t contenant c s'il existe, -1 sinon
    */
    public static int plusGrandIndice(char[] t, char c){ //Fait
        int result = -1;
        for(int i=0; i<t.length; i++){
            if (t[i] == c){
                result = i;
            }
        }
        return result;
    }
    //______________________________________________

    /** pré-requis : aucun
	résultat : vrai ssi c est un élément de t
	stratégie : utilise la fonction plusGrandIndice
    */
    public static boolean estPresent(char[] t, char c){
        boolean result = false;
        for(int i=0; i<t.length; i++){
            if (t[i] == c){
                result = true;
            }
        }
        return result;
    }

    //______________________________________________
    
    /** pré-requis : aucun
	action : affiche un doublon et 2 de ses indices dans t s'il en existe
	résultat : vrai ssi les éléments de t sont différents
	stratégie : utilise la fonction plusGrandIndice
    */
    public static boolean elemDiff(char[] t){ //todo completur pour afficher doublon + indices
        boolean result = false;
        for(int i=0; i<t.length; i++){
            if ((plusGrandIndice(t, t[i])) != -1){
                result = true;
            }
        }
        return false;
    }
    
    //______________________________________________

    /** pré-requis : t1.length = t2.length
	résultat : vrai ssi t1 et t2 contiennent la même suite d'entiers
    */
    public static boolean sontEgaux(int[] t1, int[] t2){ //Fait
        for (int i=0; i<t1.length; i++){
            if(t1[i]!=t2[i]){
                return false;
            }
        }
        return true;
    }

    //______________________________________________

    // Dans toutes les fonctions suivantes, on a comme pré-requis implicites sur les paramètres lgCode, nbCouleurs et tabCouleurs :
    // lgCode > 0, nbCouleurs > 0, tabCouleurs.length > 0 et les éléments de tabCouleurs sont différents

    // fonctions sur les codes pour la manche Humain

    /** pré-requis : aucun
	résultat : un tableau de lgCode entiers choisis aléatoirement entre 0 et nbCouleurs-1
    */
    public static int[] codeAleat(int lgCode, int nbCouleurs){

        return new int[0];
    }

    //____________________________________________________________
    
    /** pré-requis : aucun
	action : si codMot n'est pas correct, affiche pourquoi
	résultat : vrai ssi codMot est correct, c'est-à-dire de longueur lgCode et ne contenant que des éléments de tabCouleurs
    */
    public static boolean codeCorrect(String codMot, int lgCode, char[] tabCouleurs){

        return false;
    }
   
    //____________________________________________________________
    
    /** pré-requis : les caractères de codMot sont des éléments de tabCouleurs
	résultat : le code codMot sous forme de tableau d'entiers en remplaçant chaque couleur par son indice dans tabCouleurs
    */
    public static int[] motVersEntiers(String codMot, char[] tabCouleurs){

        return new int[0];
    }

    //____________________________________________________________
    
    /** pré-requis : aucun
	action : demande au joueur humain de saisir la (nbCoups + 1)ème proposition de code sous forme de mot, avec re-saisie éventuelle jusqu'à ce 
	qu'elle soit correcte (le paramètre nbCoups ne sert que pour l'affichage)
	résultat : le code saisi sous forme de tableau d'entiers
    */
    public static int[] propositionCodeHumain(int nbCoups, int lgCode, char[] tabCouleurs){

        return new int[0];
    }

    //____________________________________________________________
    
    /** pré-requis : cod1.length = cod2.length
	résultat : le nombre d'éléments communs de cod1 et cod2 se trouvant au même indice
	Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 1 (le "0" à l'indice 3)
    */
    public static int nbBienPlaces(int[] cod1,int[] cod2){//fait
        int bonneplace=0;
        for(int i=0;i<cod1.length;i++){
            if (cod1[i]==cod2[i]){
                bonneplace++;
            }
        }
        return bonneplace;
    }

    //____________________________________________________________
    
    /** pré-requis : les éléments de cod sont des entiers de 0 à nbCouleurs-1
	résultat : un tableau de longueur nbCouleurs contenant à chaque indice i le nombre d'occurrences de i dans cod
	Par exemple, si cod = (1,0,2,0) et nbCouleurs = 6 la fonction retourne (2,1,1,0,0,0)
    */
    public static int[] tabFrequence(int[] cod, int nbCouleurs){
        int nbr=0;
        int [] codbis = new int [nbCouleurs];
        for (int i=0; i<cod.length;i++){ //compte le nombre d'occurence de i dans cod
            nbr=nbr+cod[i];
            codbis[i]=cod[i];
        }

        for(int i=0;i<cod.length/2;i++){  //met cod dans l'ordre décroissant
            for (int j=0;j<cod.length/2;j++){
                if (cod[j]>cod[i]){
                    int imax=cod[j];
                    cod[j]=cod[i];
                    cod[i]=imax;
                }
            }
        }


        return codbis;
    }

    //____________________________________________________________
    
    /** pré-requis : les éléments de cod1 et cod2 sont des entiers de 0 à nbCouleurs-1
	résultat : le nombre d'éléments communs de cod1 et cod2, indépendamment de leur position
	Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 3 (2 "0" et 1 "1")
    */
    public static int nbCommuns(int[] cod1,int[] cod2, int nbCouleurs){ //problème il va passer plusieurs fois sur la meme valeur cod2
        int nbenCommuns=0;
        int [] codbis = new int[cod2.length];
        for (int i=0; i<cod2.length;i++){
            codbis[i]=cod2[i];
        }
        for (int i=0;i<cod1.length;i++){
            for (int j=0;j<codbis.length;j++){
                if (cod1[i] == codbis[j]) {
                    nbenCommuns++;
                    codbis[j]=nbCouleurs+1;
                }
            }
        }
        return nbenCommuns;
    }

    //____________________________________________________________
    
    /** pré-requis : cod1.length = cod2.length et les éléments de cod1 et cod2 sont des entiers de 0 à nbCouleurs-1
	résultat : un tableau de 2 entiers contenant à l'indice 0 (resp. 1) le nombre d'éléments communs de cod1 et cod2
	se trouvant  (resp. ne se trouvant pas) au même indiceien placé (le "0" à l'indice 3)
	et 2 mal placés (1 "0" et 1 "1")
     Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne (1,2) : 1 b
    */
    public static int[] nbBienMalPlaces(int[] cod1,int[] cod2, int nbCouleurs){ //marche pas pour l'instant
        int [] nbBMplace={0,0};
        int [] codbis = new int [cod1.length];
        for (int i=0;i<nbCouleurs;i++){
            for (int j=0;j<nbCouleurs;j++){
                if (cod1[i]==cod2[j] && i==j){
                    nbBMplace[0]++;
                }
                else if (cod1[i]==cod2[j]){
                    nbBMplace[1]++;
                }
            }
        }
        return nbBMplace;
    }

    //____________________________________________________________

    //.........................................................................
    // MANCHEHUMAIN
    //.........................................................................

    /** pré-requis : numMache >= 1
	action : effectue la (numManche)ème manche où l'ordinateur est le codeur et l'humain le décodeur
	(le paramètre numManche ne sert que pour l'affichage)
	résultat : 
            - un nombre supérieur à nbEssaisMax, calculé à partir du dernier essai du joueur humain (cf. sujet), 
              s'il n'a toujours pas trouvé au bout du nombre maximum d'essais 
            - sinon le nombre de codes proposés par le joueur humain          
    */
    public static int mancheHumain(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax){

        return lgCode;
    }

    //____________________________________________________________

    //...................................................................
    // FONCTIONS COMPLÉMENTAIRES SUR LES CODES POUR LA MANCHE ORDINATEUR
    //...................................................................

    /** prérequis : les éléments de cod sont des entiers de 0 à tabCouleurs.length-1
	résultat : le code cod sous forme de mot d'après le tableau tabCouleurs
    */
    public static String entiersVersMot(int[] cod, char[] tabCouleurs){

        return null;
    }

    //___________________________________________________________________
    
    /** pré-requis : rep.length = 2
	action : si rep n'est pas  correcte, affiche pourquoi, sachant que rep[0] et rep[1] sont 
	         les nombres de bien et mal placés resp.
	résultat : vrai ssi rep est correct, c'est-à-dire rep[0] et rep[1] sont >= 0 et leur somme est <= lgCode
    */
    public static boolean repCorrecte(int[] rep, int lgCode){

        return false;
    }

    //___________________________________________________________________
    
    /** pré-requis : aucun
	action : demande au joueur humain de saisir les nombres de bien et mal placés, 
                 avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
	résultat : les réponses du joueur humain dans un tableau à 2 entiers
    */
    public static int[] reponseHumain(int lgCode){

        return new int[0];
    }

    //___________________________________________________________________
    
    /** pré-requis : les éléments de cod1 sont des entiers de 0 à nbCouleurs-1
	action : met dans cod1 le code qui le suit selon l'ordre lexicographique dans l'ensemble 
	         des codes de longueur cod1.length à valeurs de 0 à nbCouleurs-1, si ce code existe 
	résultat : vrai ssi l'action a pu être effectuée
    */
    public static boolean passeCodeSuivantLexico(int[] cod1, int  nbCouleurs){

        return false;
    }

    //___________________________________________________________________
    
    /** pré-requis : cod est une matrice, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length 
                     et  nbCoups < rep.length 
	résultat : vrai ssi cod[nbCoups] est compatible avec les nbCoups premières lignes de cod et de rep, 
	           c'est-à-dire que si cod[nbCoups] était le code secret, les réponses aux nbCoups premières 
	           propositions de cod seraient les nbCoups premières réponses de rep
    */
    public static boolean estCompat(int [][] cod, int [][] rep, int nbCoups, int nbCouleurs){

        return false;
    }

    //___________________________________________________________________
    
    /** pré-requis : cod est une matrice, rep est une matrice à 2 colonnes, 0 < nbCoups < cod.length 
                     et nbCoups < rep.length  
	action : met dans cod[nbCoups] le plus petit code (selon l'ordre lexicographique dans l'ensemble 
	  des codes de longueur cod[0].length à valeurs  de 0 à nbCouleurs-1) qui est à la fois plus grand que
	  cod[nbCoups-1] selon cet ordre et compatible avec les nbCoups premières lignes de cod et de rep,
	  si ce code existe 
	résultat : vrai ssi l'action a pu être effectuée
    */
    public static boolean passePropSuivante(int [][] cod,int [][] rep, int nbCoups, int nbCouleurs){

        return false;
    }

    //___________________________________________________________________
    
    // manche Ordinateur

    /** pré-requis : numManche >= 2
	action : effectue la (numManche)ème  manche où l'humain est le codeur et l'ordinateur le décodeur
	(le paramètre numManche ne sert que pour l'affichage)
	résultat : 
            - 0 si le programme détecte une erreur dans les réponses du joueur humain
            - un nombre supérieur à nbEssaisMax, calculé à partir du dernier essai de l'ordinateur (cf. sujet), 
              s'il n'a toujours pas trouvé au bout du nombre maximum d'essais 
            - sinon le nombre de codes proposés par l'ordinateur
    */
    public static int mancheOrdinateur(int lgCode,char[] tabCouleurs, int numManche, int nbEssaisMax) {

        return lgCode;
    }

    //___________________________________________________________________

    //.........................................................................
    // FONCTIONS DE SAISIE POUR LE PROGRAMME PRINCIPAL
    //.........................................................................


    /** pré-requis : aucun
	action : demande au joueur humain de saisir un entier strictement positif, 
                 avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
	résultat : l'entier strictement positif saisi
    */
    public static int saisirEntierPositif(){


        return 0;
    }

    //___________________________________________________________________
    
    /** pré-requis : aucun
	action : demande au joueur humain de saisir un entier pair strictement positif, 
                 avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
	résultat : l'entier pair strictement positif saisi
    */
    public static int saisirEntierPairPositif(){

        return 0;
    }

    //___________________________________________________________________
    
    /** pré-requis : aucun
	action : demande au joueur humain de saisir le nombre de couleurs (stricement positif), 
                 puis les noms de couleurs aux initiales différentes, 
	         avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
	résultat : le tableau des initiales des noms de couleurs saisis
    */
    public static char[] saisirCouleurs(){

        return new char[0];
    }

    //___________________________________________________________________

    //.........................................................................
    // PROGRAMME PRINCIPAL
    //.........................................................................
    

    /** action : demande à l'utilisateur de saisir les paramètres de la partie (lgCode, tabCouleurs, 
           nbManches, nbEssaisMax), 
	   effectue la partie et affiche le résultat (identité du gagnant ou match nul).
	   La longueur d'un code et le nombre de couleurs doivent être strictement positifs. 
	   Le nombre de manches doit être un nombre pair strictement positif.
	   Les initiales des noms de couleurs doivent être différentes. 
	   Toute donnée incorrecte doit être re-saisie jusqu'à ce qu'elle soit correcte.
    */
    public static void main (String[] args){
   
    } // fin main

    //___________________________________________________________________
    
} // fin MasterMindBase
