package org.example;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MasterMindBaseTest {

    @BeforeMethod
    public void setUp() {

    }

    @Test
    public void testInitTab() {
        int nb = 4;
        int val = 3;
        int[] verif = {3,3,3,3};
        assertEquals(verif, MasterMindBase.initTab(nb,val));
    }

    @Test
    public void testCopieTab() {
    }

    @Test
    public void testListElem() {
    }

    @Test
    public void testPlusGrandIndice() {
    }

    @Test
    public void testEstPresent() {
    }

    @Test
    public void testElemDiff() {
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
    }

    @Test
    public void testNbCommuns() {
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
}