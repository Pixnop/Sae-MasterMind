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
        int [] tabcopie= new int[tab.length];
        for(int i=0;i<tab.length;i++){
            tabcopie[i]=tab[i];
        }
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
        if (plusGrandIndice(t,c)!=-1){
            return true;
        }
        else {return false;}
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
            if(t1[i]!=t2[i]){
                egaux=false;
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
        int [] randomcode = new int[lgCode];
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
    public static boolean codeCorrect(String codMot, int lgCode, char[] tabCouleurs){ //todo : à test
        int verif=0;
        for (int i=0; i<lgCode; i++){  //incrémente verif de 1 chaque fois que le caractère est présent dans string
             if (codMot.indexOf(tabCouleurs[i])!=-1){
                 verif++;
             }
        }
        if (verif!=lgCode){ //si verif n'a pas la meme valeur de la longueur du code, c'est faux
            System.out.print("les éléments de codMot ne sont pas tous présents dans le tableau");
            return false;
        }

        if (tabCouleurs.length!=lgCode){ // verifie la taille du tableau ^^
            System.out.print("la longueur de ton tableau n'est pas bonne");
            return false;
        }
        return true;
    }
   
    //____________________________________________________________
    
    /**
     * pré-requis : les caractères de codMot sont des éléments de tabCouleurs
     * résultat : le code codMot sous forme de tableau d'entiers en remplaçant chaque couleur par son indice dans tabCouleurs
     */
    public static char[] motVersEntiers(String codMot, char[] tabCouleurs) { //todo : à test
        char[] resultat = new char[codMot.length()];
        int partielle = 0;
        for (int i = 0; i < codMot.length(); i++) {
            for (int j = 0; j < codMot.length() && partielle == 0; j++) {
                if (codMot.charAt(i) == tabCouleurs[j]) {
                    resultat[i] = (char) j;
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
    public static int[] propositionCodeHumain(int nbCoups, int lgCode, char[] tabCouleurs){ //rajouter resaisie avec fonction codeCorrect
        Scanner scanner=new Scanner(System.in);
        System.out.print("Saisissez votre " + nbCoups+1 + "ème proposition (sous forme de mot séparés par une virgule) : \n ");
        String reponse = scanner.next();
        //if ()
        //codMot.charAt(i);

        return new int[0];
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

        for (int i=0; i<cod2.length;i++){ //cod2bis prend la valeur de cod2
            cod2bis[i]=cod2[i];
        }
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

    /** pré-requis : numManche >= 1
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
    public static int[] reponseHumain(int lgCode){ //todo : a finir
        int [] reponse = new int[]{0,0};
        Scanner scanner = new Scanner(System.in);
        String s ;
        boolean b = false;
        int lu = 0;
        while (b == false){
            System.out.println("Saisissez le nombre de bien placés : ");  //reponse [0] sera le nombre de bien placés
            s = scanner.next();
            try{
                lu = Integer.parseInt(s);
            }
            catch(NumberFormatException ex){
                System.out.println("Ce n'est pas un entier. Recommencez s'il vous plaît \n");
            }
            if (lu >= 0 && lu<=lgCode){
                b = true;
                reponse[0] = lu;
                }
            else {
                System.out.println("La réponse n'est pas dans l'intervalle 0 - " + lgCode);
            }
        }

        b=false;
        while(b==false){
            System.out.println("Saisissez le nombre de mal placés : ");
            s = scanner.next();
            try{
                lu = Integer.parseInt(s);
            }
            catch(NumberFormatException ex){
                System.out.println("Ce n'est pas un entier. Recommencez s'il vous plaît \n");
            }
            if (lu>=0 && lu<=lgCode-reponse[0]){
                b = true;
                reponse[1] = lu;
            }
            else {
                System.out.println("La réponse n'est pas compatible avec le nombre de bien placés \n ");
            }
        }
        return reponse;
    }

    //___________________________________________________________________

    /**CHANGE : action si le code suivant n'existe pas
     *************************************************
     pré-requis : les éléments de cod1 sont des entiers de 0 à nbCouleurs-1
     action/résultat : met dans cod1 le code qui le suit selon l'ordre lexicographique (dans l'ensemble
     des codes à valeurs  de 0 à nbCouleurs-1) et retourne vrai si ce code existe, 
     sinon met dans cod1 le code ne contenant que des "0" et retourne faux
     */
     // 4 4 4 3 : 4 couleurs différente
    public static boolean passeCodeSuivantLexico(int[] cod1, int  nbCouleurs){
        boolean existe = false;
        for (int i=cod1.length-1; i>0;i--){
            boolean b = cod1[i] < nbCouleurs - 1;
            if (b){
                cod1[i]++;
                existe=true;
            }
        }
        for (int i=0; i<cod1.length;i++){
            cod1[i]=0;
        }
        return existe;
    }

    //___________________________________________________________________


    /**CHANGE : ajout du paramètre cod1 et modification des spécifications
     *********************************************************************
     pré-requis : cod est une matrice à cod1.length colonnes, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length,
     nbCoups < rep.length et les éléments de cod1 et de cod sont des entiers de 0 à nbCouleurs-1
     résultat : vrai ssi cod1 est compatible avec les nbCoups premières lignes de cod et de rep,
     c'est-à-dire que si cod1 était le code secret, les réponses aux nbCoups premières
     propositions de cod seraient les nbCoups premières réponses de rep resp.
     */
    public static boolean estCompat(int [] cod1, int [][] cod,int [][] rep, int nbCoups, int  nbCouleurs){
        
        return false; //jai rajouté
    }

    //___________________________________________________________________

    /**CHANGE : renommage de passePropSuivante en passeCodeSuivantLexicoCompat,
     ajout du paramètre cod1 et modification des spécifications
     **************************************************************************
     pré-requis : cod est une matrice à cod1.length colonnes, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length,
     nbCoups < rep.length et les éléments de cod1 et de cod sont des entiers de 0 à nbCouleurs-1
     action/résultat : met dans cod1 le plus petit code (selon l'ordre lexicographique (dans l'ensemble
     des codes à valeurs  de 0 à nbCouleurs-1) qui est à la fois plus grand que
     cod1 selon cet ordre et compatible avec les nbCoups premières lignes de cod et rep si ce code existe,
     sinon met dans cod1 le code ne contenant que des "0" et retourne faux
     */
    public static boolean passeCodeSuivantLexicoCompat(int [] cod1, int [][] cod,int [][] rep, int nbCoups, int  nbCouleurs){
    return false; //jai rajouté
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


    /**CHANGE : ajout de : le nombre d'essais maximum doit être strictement positif
     ******************************************************************************
     action : demande à l'utilisateur de saisir les paramètres de la partie (lgCode, tabCouleurs,
     nbManches, nbEssaisMax),
     effectue la partie et affiche le résultat (identité du gagnant ou match nul).
     La longueur d'un code, le nombre de couleurs et le nombre d'essais maximum doivent être strictement positifs.
     Le nombre de manches doit être un nombre pair strictement positif.
     Les initiales des noms de couleurs doivent être différentes.
     Toute donnée incorrecte doit être re-saisie jusqu'à ce qu'elle soit correcte.
     */
    public static void main(String[] args){


    } // fin main
    //___________________________________________________________________
    
}
// fin MasterMindBase
