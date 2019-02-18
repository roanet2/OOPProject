package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 *
 * @author Roan Ettema
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
        instanceCompetitie = new Competitie("Schaaktoernooi 1", LocalDate.of(2019, 2, 10),
                LocalDate.of(2019, 2, 15));
        instanceLID1 = new Lid("Roan Ettema", LocalDate.of(1998, 4, 22), 2450);
        instanceLID2 = new Lid("Ronald Vlaar", LocalDate.of(2000,  12, 22), 2450);
        instanceGeenLid = new Lid("Levin Ettema", LocalDate.of(2000, 10, 21), 0);
        instanceRonde1 = new Ronde(LocalDate.of(2018, 2, 22));
        instanceRonde2 = new Ronde(LocalDate.of(2018, 2, 23));
        instancePartijMetLid = new Partij(instanceLID1, instanceLID2);
        instancePartijGeenLid = new Partij(instanceLID1, instanceGeenLid);

        instanceCompetitie.voegLidToe(instanceLID1);
        instanceCompetitie.voegLidToe(instanceLID2);
        instanceRonde1.voegPartijToe(instancePartijMetLid);
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
        System.out.println("Test is wel Lid");
        boolean expResult = true;
        boolean result =  instanceCompetitie.isNogGeenLid(instanceLID1);
        assertEquals(expResult, result);

        result =  instanceCompetitie.isNogGeenLid(instanceLID2);
        assertEquals(expResult, result);

        System.out.println("Test is geen Lid");
        result = instanceCompetitie.isNogGeenLid(instanceGeenLid);
        assertFalse(result);

    }

    @Test
    public void isGeenRondeMetSpelersZonderInschrijving() {
        System.out.println("Test partij met leden");
        boolean expResult = true;
        boolean result = instanceCompetitie.isGeenRondeMetSpelersZonderInschrijving(instanceRonde1);
        assertEquals(expResult, result);

        System.out.println("Test partij geen lid");
        result = instanceCompetitie.isGeenRondeMetSpelersZonderInschrijving(instanceRonde2);
        assertEquals(expResult, result);




    }
}