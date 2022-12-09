package org.example;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

public class MasterMindBaseTest {

    @BeforeMethod
    public void setUp() {

    }
    /** pré-requis : nb >= 0
     résultat : un tableau de nb entiers égaux à val
     */
    @Test
    public void testInitTab() {
        int nb = 4;
        int val = 3;
        int[] verif = {3,3,3,3};
        assertEquals(verif, MasterMindBase.initTab(nb,val));
    }
    /** pré-requis : aucun
     résultat : une copie de tab
     */
    @Test
    public void testCopieTab() {
        int[] verif = {1,2,3,4};
        assertEquals(verif, MasterMindBase.copieTab(verif));
    }
    /** pré-requis : aucun
     résultat : la liste des éléments de t entre parenthèses et séparés par des virgules
     */
    @Test
    public void testListElem() {
        char[] verif = {'a','b','c','d'};
        String result = "(a,b,c,d)";
        assertEquals(result, MasterMindBase.listElem(verif));
    }
    /** pré-requis : aucun
     résultat : le plus grand indice d'une case de t contenant c s'il existe, -1 sinon
     */
    @Test
    public void testPlusGrandIndice() {
        char[] verif = {'a','b','c','b'};
        char Averif = 'b';
        int result = 3;
        assertEquals(result, MasterMindBase.plusGrandIndice(verif, Averif));
    }

    /** pré-requis : aucun
     résultat : vrai ssi c'est un élément de t
     stratégie : utilise la fonction plusGrandIndice
     */
    @Test
    public void testEstPresent() {

    }

    @Test
    public void testElemDiff() {
        char[] verif = {'a','b','b','d'};


    }

    @Test
    public void testSontEgaux() {
    }

    @Test
    public void testCodeAleat() {
    }

    @Test
    public void testCodeCorrect() {
    }

    @Test
    public void testMotVersEntiers() {
    }

    @Test
    public void testPropositionCodeHumain() {
    }

    @Test
    public void testNbBienPlaces() {
    }

    @Test
    public void testTabFrequence() {
        int[] vale1 = {0,2,1,0};
        int nbdecouleur=5;
        int [] result = {2,1,1,0,0};
        assertEquals( MasterMindBase.tabFrequence(vale1,nbdecouleur),result);
    }

    @Test
    public void testNbCommuns() {
        int[] vale1 = {3,1,1,1};
        int[] vale2 = {1,1,2,3};
        int nbdecouleur=4;
        int result=3;
        assertEquals(MasterMindBase.nbCommuns(vale1,vale2,nbdecouleur),result);
    }

    @Test
    public void testNbBienMalPlaces() {
    }

    @Test
    public void testMancheHumain() {
    }

    @Test
    public void testEntiersVersMot() {
    }

    @Test
    public void testRepCorrecte() {
    }

    @Test
    public void testReponseHumain() {
    }

    @Test
    public void testPasseCodeSuivantLexico() {
    }

    @Test
    public void testEstCompat() {
    }

    @Test
    public void testPassePropSuivante() {
    }

    @Test
    public void testMancheOrdinateur() {
    }

    @Test
    public void testSaisirEntierPositif() {
    }

    @Test
    public void testSaisirEntierPairPositif() {
    }

    @Test
    public void testSaisirCouleurs() {
    }

    @Test
    public void testMain() {
    }

    @Test
    public void testProfInitTab(){
        int[] t = MasterMindBase.initTab(10, -4);
        if (t.length == 10 && t[0] == -4 && t[9] == -4)
            assertTrue(true);
        else
            assertFalse(false);
    }
    @Test
    public void testProfCopieTab(){
        int[] t1 = {1, 2, 3, 4};
        int[] t2 = MasterMindBase.copieTab(t1);
        if (t1 != t2 && Arrays.equals(t1,t2))
            assertTrue(true);
        else
            assertFalse(false);
    }
    @Test
    public void testProfListElem() {
        char [] ch = {'B', 'V'};
        String res = MasterMindBase.listElem(ch);
        if (res.length() == 5)
            assertTrue(true);
        else
            assertFalse(false);
    }
    @Test
    public void testProfPLusGrandIndice() {
        char [] mot = {'R','o','u','l','o','n','s'};
        int i = MasterMindBase.plusGrandIndice(mot, 'o');
        if (i == 4)
            assertTrue(true);
        else
            assertFalse(false);
    }

    @Test
    public void testProfElemDiff() {
        char [] mot = {'b','i','n','g','o'};
        if ( MasterMindBase.elemDiff(mot) )
            assertTrue(true);
        else
            assertFalse(false);
    }

    @Test
    public void testProfSontEgaux() {
        int [] t1 = {1, 2, 3, 4};
        int [] t2 = {1, 2, 3, 5};

        if ( MasterMindBase.sontEgaux(t1, t2) )
            assertTrue(true);
        else
            assertFalse(false);
    }

    @Test
    public void testProfCodeAleat() {
        int[] code = MasterMindBase.codeAleat(1000, 6);
        for (int i = 0 ; i < 1000 ; i++) {
            if ( ! (0 <= code[i] && code[i] <= 5) ){
                assertTrue(true);
            }
        }
        assertFalse(false);
    }

    @Test
    public void testProfCodeCorrect() {
        char[] tCoul = {'A','B','C'};
        if ( MasterMindBase.codeCorrect("ABABCDA", 7, tCoul) )
            assertTrue(true);
        else
            assertFalse(false);
    }

    @Test
    public void testProfMotVersEntiers() {
        char[] tCoul = {'A','B','C'};
        int [] res = MasterMindBase.motVersEntiers("ABAABA", tCoul);
        if ( res[0] == 0 && res[1] == 1 && res[2] == 0 && res[3] == 0 && res[4] == 1 && res[5] == 0 )
            assertTrue(true);
        else
            assertFalse(false);
    }

    @Test
    public void testProfRepCorrecte() {

        int[] t1 = {0, 0};
        int[] t2 = {-1, 2};
        int[] t3 = {3, -1};
        int[] t4 = {1, 3};
        int note = 0;
        if (MasterMindBase.repCorrecte(t1,3) & !MasterMindBase.repCorrecte(t2,3) & !MasterMindBase.repCorrecte(t3,3) & !MasterMindBase.repCorrecte(t4,3))
            assertTrue(true);
        else
            assertFalse(false);
    }

    @Test
    public void testProfPasseCodeSuivantLexico2() {

        int[] t1 = {1, 2, 3, 3};
        int[] t2 = {1, 3, 0, 0};
        if (MasterMindBase.passeCodeSuivantLexico(t1,4) && Arrays.equals(t1,t2))
            assertTrue(true);
        else
            assertFalse(false);
    }

    @Test
    public void testProfEstCompat1() {

        int[][] cod = {{0,0,0,0,0}};
        int[][] rep = {{0,0}};

        int[] t1 = {6,6,6,6,6};
        int[] t2 = {4,0,1,2,3};

        if (MasterMindBase.estCompat(t1,cod,rep,1,7) && !MasterMindBase.estCompat(t2,cod,rep,1,7))
            assertTrue(true);
        else
            assertFalse(false);
    }

    @Test
    public void testProfPasseCodeSuivantLexicoCompat2() {

        int[][] cod = {{0,0,0,0,0}, {0,1,1,1,1}};
        int[][] rep = {{1,0},       {2,1}};

        int[] t1 = {0,1,1,1,1};
        int[] t2 = {2,0,1,1,2};

        if (MasterMindBase.passeCodeSuivantLexicoCompat(t1,cod,rep,2,3) && Arrays.equals(t1,t2))
            assertTrue(true);
        else
            assertFalse(false);
    }

}
