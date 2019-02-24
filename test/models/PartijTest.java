package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * In deze test wordt gecontroleerd of het updaten van de rating correct werkt.
 * @author Ronald Vlaar
 */
public class PartijTest {

    private static final int ratingLidZonderBekendeRating = 1200;
    private static final int kFactorLidZonderBekendeRating = 20;
    private static final int ratingLidMetBekendeRating = 1500;
    private static final int kFactorLidMetBekendeRating = 40;

    private static Lid instanceLidZonderBekendeRating;
    private static Lid instanceLidMetBekendeRating;
    private static Partij instancePartij;


    @Before
    public void setUp() throws Exception {

        //Lid heeft geen bekende rating dus is de rating automatisch 1200
        instanceLidZonderBekendeRating = new Lid("Jan", LocalDate.of(2000, 2, 22));

        //Dit lid heeft wel een bekende rating maar is ook altijd jonger dan 18. Spelers jonger dan 18 en met een
        // rating lager dan 2300 horen namelijk de hoogste k-factor te krijgen (40)
        instanceLidMetBekendeRating = new Lid("Jaap", LocalDate.now(), 1500);

        instancePartij = new Partij(instanceLidMetBekendeRating, instanceLidZonderBekendeRating);


    }

    @After
    public void tearDown() throws Exception {
        instanceLidZonderBekendeRating = null;
        instanceLidMetBekendeRating = null;
        instancePartij = null;
    }

    @Test
    //Kloppen de ratings na aanmaak van de partij?
    public void getRating() {
        assertEquals(ratingLidMetBekendeRating, instanceLidMetBekendeRating.getRating());
        assertEquals(ratingLidZonderBekendeRating, instanceLidZonderBekendeRating.getRating());
    }

    @Test
    //Worden de winkansen van beide spelers juist berekend?
    public void berekenKansOpWinst() {

        instancePartij.berekenKansOpWinst(instanceLidMetBekendeRating, instanceLidZonderBekendeRating);

        double kansOpWinstSpelerMetBekendeRating = 1.0 / (1.0 + Math.pow(10, ((ratingLidZonderBekendeRating -
                ratingLidMetBekendeRating) / 400.0)));
        double kansOpWinstSpelerZonderBekendeRating = 1.0 / (1.0 + Math.pow(10, ((ratingLidMetBekendeRating
                - ratingLidZonderBekendeRating) / 400.0)));

        assertEquals(kansOpWinstSpelerZonderBekendeRating, instancePartij.getWinstVerwachtingSpelerWit(), 0);
        assertEquals(kansOpWinstSpelerMetBekendeRating, instancePartij.getWinstVerwachtingSpelerZwart(), 0);

    }


    @Test
    //Kloppen de nieuwe ratings na afloop van iedere partij?
    public void berekenNieuweRating() {

        instancePartij.setWinnaar(instanceLidMetBekendeRating);

        int nieuweRatingLidMetBekendeRating = (int) (ratingLidMetBekendeRating +
                (1 - instancePartij.getWinstVerwachtingSpelerZwart())
                        * kFactorLidMetBekendeRating);
        int nieuweRatingLidZonderBekendeRating = (int) (ratingLidZonderBekendeRating +
                (0 - instancePartij.getWinstVerwachtingSpelerWit())
                        * kFactorLidZonderBekendeRating);

        assertEquals(nieuweRatingLidMetBekendeRating, instancePartij.getSpelerZwart().getRating());
        assertEquals(nieuweRatingLidZonderBekendeRating, instancePartij.getSpelerWit().getRating());


    }

    @Test
    //Worden de gewonnen punten voor iedere speler correct teruggegeven?
    public void getGewonnenPunten() {

        instancePartij.setWinnaar(instanceLidMetBekendeRating);
        assertEquals(1.0, instancePartij.getGewonnenPunten(instanceLidMetBekendeRating), 0);
        assertEquals(0, instancePartij.getGewonnenPunten(instanceLidZonderBekendeRating), 0);


    }


}