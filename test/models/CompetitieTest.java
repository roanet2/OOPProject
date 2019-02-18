package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 *
 * @author Roan Ettema & Ronald Vlaar
 */

public class CompetitieTest {


    private  static  Competitie instanceCompetitie;
    private  static  Lid instanceLID1;
    private  static  Lid instanceLID2;
    private  static  Lid instanceGeenLid;
    private  static  Partij  instancePartijMetLid;
    private  static  Partij  instancePartijGeenLid;
    private  static  Ronde instanceRonde1;
    private  static  Ronde instanceRonde2;



    @Before
    public void setUp() throws Exception {
        //Aanmaken competitie
        instanceCompetitie = new Competitie("Schaaktoernooi 1", LocalDate.of(2019, 2, 10),
                LocalDate.of(2019, 2, 15));

        //Aanmaken leden die in deze test zullen figureren als ingeschreven leden van de hierboven aangemaakte competitie
        instanceLID1 = new Lid("Roan Ettema", LocalDate.of(1998, 4, 22), 2450);
        instanceLID2 = new Lid("Ronald Vlaar", LocalDate.of(2000,  12, 22), 2450);

        //Aanmaken van het lid dat niet ingeschreven en dus niet toegevoegd wordt aan de instanceCompetitie
        instanceGeenLid = new Lid("Levin Ettema", LocalDate.of(2000, 10, 21), 0);

        instanceRonde1 = new Ronde(LocalDate.of(2018, 2, 22));
        instanceRonde2 = new Ronde(LocalDate.of(2018, 2, 23));

        //Partij met uitsluitend leden die ook ingeschreven staan bij de competitie
        instancePartijMetLid = new Partij(instanceLID1, instanceLID2);
        instancePartijGeenLid = new Partij(instanceLID1, instanceGeenLid);

        //Het toevoegen van deze leden gebeurt hieronder
        instanceCompetitie.voegLidToe(instanceLID1);
        instanceCompetitie.voegLidToe(instanceLID2);

        //Ronde1 is in dit testplan de ronde die alleen partijen bevat waarvan alle spelers een ingschrijving hebben
        //bij de competitie. De ronde zal dus succesvol toegevoegd moeten kunnen worden
        instanceRonde1.voegPartijToe(instancePartijMetLid);

        //Ronde2 bevat een partij met een speler zonder inschrijving en moet dus niet toegevoegd kunnen worden aan
        //de competitie
        instanceRonde2.voegPartijToe(instancePartijGeenLid);

    }

    @After
    public void tearDown() throws Exception {

        instanceLID1 = null;
        instanceLID2 = null;
        instanceGeenLid = null;
        instanceRonde1 = null;
        instanceRonde2 = null;
        instancePartijMetLid = null;
        instancePartijGeenLid = null;
        instanceCompetitie = null;
    }

    @Test
    public void isNogGeenLid() {
        System.out.println("\nTest methode isNogGeenLid():");
        System.out.println("Verwachtingswaarde false, want het lid is ook lid van de competitie");
        boolean result =  instanceCompetitie.isNogGeenLid(instanceLID1);
        assertFalse(result);

        result =  instanceCompetitie.isNogGeenLid(instanceLID2);
        assertFalse(result);

        System.out.println("Verwachtingswaarde true, immmers is het lid geen lid van de competitie en " +
                "zou dus moeten gelden dat methode isNogGeenLid == true");
        result = instanceCompetitie.isNogGeenLid(instanceGeenLid);
        assertTrue(result);

    }

    @Test
    public void isGeenRondeMetSpelersZonderInschrijving() {

        System.out.println("Test methode isGeenRondeMetSpelersZonderInschrijving():");
        System.out.println("Test met een ronde waarbij de spelers die voorkomen in de partijen lid zijn van de competitie");
        boolean expResultTrue = true;
        boolean result = instanceCompetitie.isGeenRondeMetSpelersZonderInschrijving(instanceRonde1);
        assertEquals(expResultTrue, result);

        System.out.println("Test met een ronde waarbij niet alle spelers die voorkomen in de partijen, ook lid zijn van " +
                "de competitie");
        boolean expResultFalse = false;
        result = instanceCompetitie.isGeenRondeMetSpelersZonderInschrijving(instanceRonde2);
        assertEquals(expResultFalse, result);




    }
}