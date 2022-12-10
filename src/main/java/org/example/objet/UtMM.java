package org.example.objet;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class UtMM {
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
        int [] tabcopie= new int[tab.length];
        System.arraycopy(tab, 0, tabcopie, 0, tab.length);
        return tabcopie;
    }

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
    public static boolean estPresent(char[] t, char c){ //fait
        boolean ilyest = plusGrandIndice(t, c) != -1;
        return ilyest;
    }

    //______________________________________________

    /** pré-requis : aucun
     action : affiche un doublon et 2 de ses indices dans t s'il en existe
     résultat : vrai ssi les éléments de t sont différents
     stratégie : utilise la fonction plusGrandIndice
     */
    public static boolean elemDiff(char[] t){ //todo : verifier si c'est vrm ça qui est voulu
        int [] indice = new int[3];
        boolean result = true;
        for(int i=0; i<t.length-1; i++){ //jmet -1 pour faire la plus petite boucle possible (efficace)
            if (plusGrandIndice(t,t[i])!=i){
                System.out.println("La valeur " + t[i] + "est présente à l'emplacement " + i + "et " + plusGrandIndice(t,t[i]) );
                result = false;
            }
        }
        return result;

    }

    //______________________________________________

    /** pré-requis : t1.length = t2.length
     résultat : vrai ssi t1 et t2 contiennent la même suite d'entiers
     */
    public static boolean sontEgaux(int[] t1, int[] t2){ //Fait
        boolean egaux=true;
        for (int i=0; i<t1.length; i++){
            if (t1[i] != t2[i]) {
                egaux = false;
                break;
            }
        }
        return egaux;
    }

    //______________________________________________

    // Dans toutes les fonctions suivantes, on a comme pré-requis implicites sur les paramètres lgCode, nbCouleurs et tabCouleurs :
    // lgCode > 0, nbCouleurs > 0, tabCouleurs.length > 0 et les éléments de tabCouleurs sont différents

    // fonctions sur les codes pour la manche Humain

    /** pré-requis : aucun
     résultat : un tableau de lgCode entiers choisis aléatoirement entre 0 et nbCouleurs-1
     */
    public static int[] codeAleat(int lgCode, int nbCouleurs){ //todo : à test
        int [] randomcode = initTab(lgCode,nbCouleurs-1);
        for (int i=0; i<lgCode;i++){
            Random r = new Random();
            randomcode[i] = r.nextInt(nbCouleurs);  //nombre aléatoire de type int supérieure ou égal à 0 et inférieur au nombre entier passé en paramètre
        }
        return randomcode;
    }

    //____________________________________________________________

    /** pré-requis : aucun
     action : si codMot n'est pas correct, affiche pourquoi
     résultat : vrai ssi codMot est correct, c'est-à-dire de longueur lgCode et ne contenant que des éléments de tabCouleurs
     */
    public static boolean codeCorrect(String codMot, int lgCode, char[] tabCouleurs){ //todo : à test + rajouter possibilité vert et violet avec plusGrandIndice ?
        int verif=0;
        boolean correct=true;
        for (int i=0; i<lgCode; i++){  //incrémente verif de 1 chaque fois que le caractère est présent dans string
            for (int j=0; j<lgCode;j++) {
                String[] listestrings = codMot.split(",");
                if (listestrings[i].charAt(0) == tabCouleurs[j]) {
                    verif++;
                }
            }
        }
        if (verif!=lgCode){ //si verif n'a pas la meme valeur de la longueur du code, c'est faux
            System.out.print("les éléments de votre code ne sont pas tous présents dans les possibilitées \n");
            correct=false;
        }

        else if (tabCouleurs.length!=lgCode){ // verifie la taille du tableau ^^
            System.out.print("La longueur de votre code n'est pas bonne \n");
            correct=false;
        }
        return correct;
    }

    //____________________________________________________________

    /**
     * pré-requis : les caractères de codMot sont des éléments de tabCouleurs
     * résultat : le code codMot sous forme de tableau d'entiers en remplaçant chaque couleur par son indice dans tabCouleurs
     */
    public static int[] motVersEntiers(String codMot, char[] tabCouleurs) { //todo : à test
        int[] resultat = new int[codMot.length()];
        int partielle = 0;
        for (int i = 0; i < codMot.length(); i++) {
            for (int j = 0; j < codMot.length() && partielle == 0; j++) {
                if (codMot.charAt(i) == tabCouleurs[j]) {
                    resultat[i] = j;
                    partielle++;
                }
            }
            partielle = 0;
        }

        return resultat;
    }
    //____________________________________________________________

    /** pré-requis : aucun
     action : demande au joueur humain de saisir la (nbCoups + 1)ème proposition de code sous forme de mot, avec re-saisie éventuelle jusqu'à ce
     qu'elle soit correcte (le paramètre nbCoups ne sert que pour l'affichage)
     résultat : le code saisi sous forme de tableau d'entiers
     */
    public static int[] propositionCodeHumain(int nbCoups, int lgCode, char[] tabCouleurs){ // todo : tester
        Scanner scanner=new Scanner(System.in);
        boolean codebon=false;
        System.out.print("Saisissez votre proposition n°"+nbCoups+1  +" (sous forme de mot séparés par une virgule) : \n ");
        String reponse = scanner.next();
        codebon=codeCorrect(reponse,lgCode,tabCouleurs);
        while (!codebon){
            System.out.print("Réécrivez votre code  (sous forme de mot séparés par une virgule) : ");
            reponse = scanner.next();
            codebon=codeCorrect(reponse,lgCode,tabCouleurs);
        }
        int [] tabentier;        //pas besoin de new, car créé dans motVersEntiers
        tabentier= motVersEntiers(reponse, tabCouleurs);
        return tabentier;
    }

    //____________________________________________________________

    /** pré-requis : cod1.length = cod2.length
     résultat : le nombre d'éléments communs de cod1 et cod2 se trouvant au même indice
     Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 1 (le "0" à l'indice 3)
     */
    public static int nbBienPlaces(int[] cod1, int[] cod2){//fait
        int bonneplace = 0;
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
    public static int[] tabFrequence(int[] cod, int nbCouleurs){  //fait
        int nbr=0;
        int [] codbis = new int [nbCouleurs];
        for (int i=0; i<nbCouleurs;i++){ //donne la valeur de l'ensemble des éléments de cod à nbr et met en place le tableau codbis
            if (i<cod.length) {
                nbr = nbr + cod[i];
                codbis[i] = cod[i];
            }
            else {
                codbis[i]=0;
            }
        }

        while(nbr-cod.length!=0){ //va comparer la somme des éléments de codbis (nbr) au nombre voulu (cod.length)
            int j=0;
            while (codbis[j]!=0){ // tant que celui ci est inférieur: on remplace le prochain 0 de codbis par 1
                j++;
            }
            codbis[j]=1;
            nbr++;
        }

        for(int i=0;i<codbis.length/2;i++){  //met codbis dans l'ordre décroissant
            for (int j=0;j<codbis.length/2;j++){
                if (cod[j]>codbis[i]){
                    int imax=codbis[j];
                    codbis[j]=codbis[i];
                    codbis[i]=imax;
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
    public static int nbCommuns(int[] cod1,int[] cod2, int nbCouleurs){ //TODO erreur dans le test je trouve pas quel est le problème
        int [] cod2bis = new int[cod2.length];
        if (cod1.length!=cod2.length){
            System.out.print("problème sur nbCommuns, la longueur des deux 'cod' est différente");
            return 0;
        }

        //cod2bis prend la valeur de cod2
        System.arraycopy(cod2, 0, cod2bis, 0, cod2.length);
        int i=0;
        int j=0;
        int nbenCommuns=0;
        while (i<cod1.length){  //compare cod1 avec cod2bis si meme valeur nbenCommuns
            while (j<cod1.length){
                if (cod2bis[i] == cod1[j]) {
                    nbenCommuns++;
                    cod2bis[i]=nbCouleurs+1;
                }
                j++;
            }
            j=0;
            i++;
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
    public static int[] nbBienMalPlaces(int[] cod1,int[] cod2, int nbCouleurs){
        int [] nbBMplace={0,0};
        int [] copie1=copieTab(cod1);
        int i=0;
        while (i<copie1.length) {
            if (copie1[i] == cod2[i]) {
                nbBMplace[0]++;
                copie1[i]=nbCouleurs;
                i++;
            }
        }
        int j=0;
        while (nbBMplace[0]+nbBMplace[1]<nbCommuns(cod1,cod2,nbCouleurs)){
            if (j<copie1.length){
                j++;
            }
            if (copie1[i]==cod2[j]){
                nbBMplace[1]++;
                i++;
                j=0;
            }
            if (j>=copie1.length){
                i++;
                j=0;
            }
        }
        return nbBMplace;
    }
}
