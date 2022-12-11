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
    public static boolean codeCorrect(String codMot, int lgCode, char[] tabCouleurs){ //todo : pas besoin de split en regex, AABBCC par exemple
        int verif=0;
        boolean correct=true;
        for (int i=0; i<lgCode; i++){  //incrémente verif de 1 chaque fois que le caractère est présent dans string
            char valeurChar= codMot.charAt(i);
            System.out.print(valeurChar);
            if (estPresent(tabCouleurs,valeurChar)) {
                verif++;
            }
        }
        if (verif!=lgCode-1){ //si verif n'a pas la meme valeur de la longueur du code, c'est faux
            System.out.print("les éléments de votre code ne sont pas tous présents dans les possibilitées \n");
            correct=false;
        }

        else if (tabCouleurs.length!=lgCode){ // verifie la taille du tableau
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
        int[]resultat=initTab(nbCouleurs,0);
        for (int i=0;i<cod.length;i++){
            resultat[cod[i]]++;
        }
        return resultat;
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
    public static int mancheHumain(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax){ //fait //todo : à tester
        int [] code=codeAleat(lgCode,tabCouleurs.length);   //notre code caché
        boolean trouver=false;                              //pour sortie de boucle si trouvée
        int [] reponse;                                     //pour mettre la réponse avant de l'envoyer à l'utilisateur
        int i;
        System.out.println("Vous êtes à la manche : "+numManche);
        for (i=0;i<nbEssaisMax || !trouver;i++){
            reponse=nbBienMalPlaces(propositionCodeHumain(i,lgCode,tabCouleurs),code,tabCouleurs.length);  //récupère la réponse de l'utilisateur
            if (reponse[0] == lgCode){
                System.out.print("bien joué le code était bien : " + entiersVersMot(code, tabCouleurs));
                trouver=true;
            }
            else {
                System.out.print("Il y a " +reponse[0]+ "bien placés et "+ reponse[1]+ "mal placés \n");
            }
        }
        return i;
    }

    //____________________________________________________________

    //...................................................................
    // FONCTIONS COMPLÉMENTAIRES SUR LES CODES POUR LA MANCHE ORDINATEUR
    //...................................................................

    /** prérequis : les éléments de cod sont des entiers de 0 à tabCouleurs.length-1
	résultat : le code cod sous forme de mot d'après le tableau tabCouleurs
    */
    public static String entiersVersMot(int[] cod, char[] tabCouleurs){//PAS FAIT faut sortir des mots
        String code = "";
        for (int i=0; i<cod.length-1; i++) {
            code += tabCouleurs[cod[i]] + ",";
        }
        code += tabCouleurs[cod[cod.length-1]];
        return code;
    }

    //___________________________________________________________________
    
    /** pré-requis : rep.length = 2
	action : si rep n'est pas correcte, affiche pourquoi, sachant que rep[0] et rep[1] sont
	         les nombres de bien et mal placés resp.
	résultat : vrai ssi rep est correct, c'est-à-dire rep[0] et rep[1] sont >= 0 et leur somme est <= lgCode
    */
    public static boolean repCorrecte(int[] rep, int lgCode){//fait
        boolean result = true;
        if (rep[0] < 0 || rep[1] < 0) {
            System.out.println("rep[0] ou rep[1] est negatif");
            result = false;
        } else if (rep[0] + rep[1] > lgCode) {
            System.out.println("rep[0] + rep[1] est plus grand que lgCode");
            result = false;
        } else {
            System.out.println("result: " + result);
        }
        return result;
    }

    //___________________________________________________________________
    
    /** pré-requis : aucun
	action : demande au joueur humain de saisir les nombres de bien et mal placés, 
                 avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
	résultat : les réponses du joueur humain dans un tableau à 2 entiers
    */
    public static int[] reponseHumain(int lgCode){ //todo : a finir
        int [] reponse = new int[]{0,0};
        Scanner scanner=new Scanner(System.in);
        String s ;
        boolean b = false;
        int lu = 0;
        while (!b){
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
        while(!b){
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
     des codes à valeurs de 0 à nbCouleurs-1) et retourne vrai si ce code existe,
     sinon met dans cod1 le code ne contenant que des "0" et retourne faux
     */
     // 4 4 4 3 : 4 couleurs différente
    public static boolean passeCodeSuivantLexico(int[] cod1, int  nbCouleurs){ //fait
        boolean existe = false;
        int compteur=0;
        for (int i=cod1.length-1; i>0 && !existe;i--){
            boolean b = cod1[i] < nbCouleurs;
            if (b){
                cod1[i]++;
                existe=true;
            }
            else{
                cod1[i]=0;
                compteur++;
            }
        }
        existe=compteur<cod1.length;
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
    public static boolean estCompat(int [] cod1, int [][] cod,int [][] rep, int nbCoups, int  nbCouleurs) {//TODO je comprend r
        boolean partielle = true;
        for (int i = 0; i < nbCoups && partielle; i++) {
                if (!(nbBienMalPlaces(cod1, cod[i], nbCouleurs) == rep[i])) {
                    partielle = false;
            }
        }
        return partielle;
    }



    //___________________________________________________________________

    /**CHANGE : renommage de passePropSuivante en passeCodeSuivantLexicoCompat,
     ajout du paramètre cod1 et modification des spécifications
     **************************************************************************
     pré-requis : cod est une matrice à cod1.length colonnes, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length,
     nbCoups < rep.length et les éléments de cod1 et de cod sont des entiers de 0 à nbCouleurs-1
     action/résultat : met dans cod1 le plus petit code (selon l'ordre lexicographique (dans l'ensemble
     des codes à valeurs de 0 à nbCouleurs-1) qui est à la fois plus grand que
     cod1 selon cet ordre et compatible avec les nbCoups premières lignes de cod et rep si ce code existe,
     sinon met dans cod1 le code ne contenant que des "0" et retourne faux
     */

    public static boolean passeCodeSuivantLexicoCompat(int [] cod1, int [][] cod,int [][] rep, int nbCoups, int  nbCouleurs){ //fait //todo : à test
        do{
            if (!passeCodeSuivantLexico(cod1,nbCouleurs)){
                return false;
            }
        }while (!estCompat(cod1, cod, rep,nbCoups,nbCouleurs));
        return true;
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
    public static int mancheOrdinateur(int lgCode,char[] tabCouleurs, int numManche, int nbEssaisMax) { //fait
        boolean finis=false;
        int nbressai=0;
        int resultat=0;
        int [] cod1= initTab(lgCode,0);
        int [][] codes= new int[lgCode][nbEssaisMax];
        int [] reponseactuelle=new int [2];
        int [][] reps = new int [2][nbEssaisMax];


        while (!finis){
            nbressai++;
            reponseactuelle=reponseHumain(lgCode);
            reps[0][nbressai]=reponseactuelle[0];
            reps[1][nbressai]=reponseactuelle[1];

            if (nbressai>nbEssaisMax || reponseactuelle[0]==lgCode) {
                finis=true;
                resultat=nbressai;
            }
            else if (!passeCodeSuivantLexicoCompat(cod1,codes,reps, nbressai,tabCouleurs.length)){
                finis=true;
                resultat=0;
            }
        }

        return resultat;
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
    public static int saisirEntierPositif(){//fait
        Scanner scanner=new Scanner(System.in);
        int nb;
        do {
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
        } while ((nb = scanner.nextInt()) <= 0);
        return nb;
    }

    //___________________________________________________________________
    
    /** pré-requis : aucun
	action : demande au joueur humain de saisir un entier pair strictement positif, 
                 avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
	résultat : l'entier pair strictement positif saisi
    */
    public static int saisirEntierPairPositif(){ //fait
        int result = saisirEntierPositif();
        if (result % 2 != 0) {
            System.out.println("Le nombre saisie est impair.");
            saisirEntierPairPositif();
            }
        return result;
    }

    //___________________________________________________________________
    
    /** pré-requis : aucun
	action : demande au joueur humain de saisir le nombre de couleurs (strictement positif),
                 puis les noms de couleurs aux initiales différentes, 
	         avec re-saisie éventuelle jusqu'à ce qu'elle soit correct
	résultat : le tableau des initiales des noms de couleurs saisis
    */
    public static char[] saisirCouleurs() { //a utiliser dans main
        Scanner scanner = new Scanner(System.in);
        boolean verif = true;
        int nbCouleurs = 0;
        System.out.print("nombre de couleur : ");
        nbCouleurs = saisirEntierPositif();
        char[] result = new char[nbCouleurs];
        String temp;
        for (int i = 0; i < nbCouleurs; ) {
            System.out.print("couleur n°" + (i+1));
            temp = scanner.next();
            if (!estPresent(result, temp.charAt(0))) {
                result[i] = temp.charAt(0);
                i++;
            } else {
                System.out.println("result en double");
            }
        }
        System.out.println(result);
        return result;
    }
    //___________________________________________________________________

    //.........................................................................
    // EXTENSION
    //.........................................................................

    /** pré-requis : cod est une matrice, rep est une matrice à 2 colonnes,
     0 <= nbCoups < cod.length, nbCoups < rep.length et
     les éléments de cod sont des entiers de 0 à tabCouleurs.length -1
     action : affiche les nbCoups premières lignes de cod (sous forme
     de mots en utilisant le tableau tabCouleurs) et de rep
     */
    public static void affichePlateau(int [][] cod, int [][] rep, int nbCoups, char[] tabCouleurs){
        System.out.println("nombre de coups: " + nbCoups);
        for (int i = 0; i < cod.length; i++) {
            System.out.print("L"+(i+1)+": ");
            for (int j = 0; j < cod[0].length; j++) {
                System.out.print(tabCouleurs[cod[i][j]-1] + "\t");
            }
            System.out.println();
        }
        for (int j = 0; j < cod[0].length+1; j++) {
            System.out.print("--\t");
        }
        System.out.println();
        for (int i = 0; i < rep.length; i++) {
            if(i==0){
                System.out.print("BP: ");
            }else{System.out.print("MP: ");}
            for (int j = 0; j < rep[0].length; j++) {
                System.out.print(rep[i][j] + "\t");
            }
            System.out.println();
        }

    }


    /** pré-requis : cod est une matrice, rep est une matrice à 2 colonnes,
            0 <= nbCoups < cod.length, nbCoups < rep.length,
    les éléments de cod sont des entiers de 0 à tabCouleurs.length -1
    et codMot est incorrect ou incompatible avec les nbCoups
    premières lignes de cod et de rep
    action : affiche les erreurs d’incorrection ou d’incompatibilité
    */
    public static void afficheErreurs (String codMot, int [][] cod, int [][] rep, int nbCoups, int lgCode, char[] tabCouleurs) {
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
        int lgCode, numManche, nbEssaisMax;
        char[] tabCouleurs;
        Scanner scanner=new Scanner(System.in);
        System.out.println("_________________________INITIALISATION PARTIE_________________________");
        System.out.println("saisie des couleurs");
        tabCouleurs = saisirCouleurs();
        System.out.print("saisie nombre de manche : ");
        numManche = saisirEntierPairPositif();
        System.out.print("saisie nombre d'essaie maximum : ");
        nbEssaisMax = saisirEntierPositif();
        System.out.print("saisie nombre de ligne : "); //TODO faire en sorte que le nombre de ligne sois - ou + que nombre de couleur
        lgCode = saisirEntierPositif();
        System.out.println("_______________________________CHOIX JEU_______________________________");
        System.out.print("Voulez vous est de codificateur (1) ou le décodeur (2) : ");
        int rep = scanner.nextInt();
        if(rep==1){
            System.out.print("Vous avez choisi codificateur, es bien ce que vous voulez ? (y/n) : ");
            String verif = scanner.next();
            if(Objects.equals(verif, "y")){
                System.out.println("_____________________________DEBUT PARTIE_____________________________");
                mancheOrdinateur(lgCode,tabCouleurs,numManche, nbEssaisMax);
            }else {
                main(args);
            }
        }else if (rep==2){
            System.out.print("Vous avez choisi décodeur, es bien ce que vous voulez ? (y/n) : ");
            String verif = scanner.next();
            if(Objects.equals(verif, "y")){
                System.out.println("_____________________________DEBUT PARTIE_____________________________");
                mancheHumain(lgCode,tabCouleurs,numManche, nbEssaisMax);
            }else {
                main(args);
            }
        }else {
            System.out.println("choisissez 1 ou 2");
            main(args);
        }
        System.out.print("Voulez vous rejouer ? (y/n) : ");
        String again = scanner.next();
        if(Objects.equals(again, "y")){
            main(args);
        }else {
            System.out.println("fin de partie");
        }

    } // fin main
    //___________________________________________________________________

}
// fin MasterMindBase
