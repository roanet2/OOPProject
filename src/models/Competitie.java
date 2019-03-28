package models;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Deze class representeert een Competitie, waaraan leden mee kunnen doen die in verschillende ronden voor kunnen komen
 * per dag kunnen maximaal 5 ronden worden gespeeld.
 */
public class Competitie {

    public static final int MAX_AANTAL_RONDEN_PER_DAG = 5;
    private String naam;
    private ArrayList<Lid> leden;
    private ArrayList<Ronde> ronden;
    private LocalDate beginDatum;
    private LocalDate eindDatum;


    public Competitie(String naam, LocalDate beginDatum, LocalDate eindDatum) {
        this.naam = naam;
        this.beginDatum = beginDatum;
        this.eindDatum = eindDatum;

        leden = new ArrayList<>();
        ronden = new ArrayList<>();

    }


    public void voegLidToe(Lid lid) {

        if (isNogGeenLid(lid)) {
            leden.add(lid);
        } else {
            System.out.println("Deze persoon doet al mee aan de competitie");
        }


    }

    public boolean isNogGeenLid(Lid lid) {

        boolean isNogGeenLid = true;

        for (Lid leden : leden) {
            if (leden.equals(lid)) {
                isNogGeenLid = false;
            }
        }


        return isNogGeenLid;

    }

    /**
     * Deze methode controleert of spelers uit een ronde zich hebben ingeschreven voor een competitie.
     *
     * @param ronde de ronde waarvan gecontroleerd wordt of de spelers in deze ronde een inschrijving voor de competitie
     *              hebben.
     * @return true indien alle spelers zich hebben ingeschreven en vice versa.
     */
    public boolean isGeenRondeMetSpelersZonderInschrijving(Ronde ronde) {

        boolean isGeenRondeMetSpelersZonderInschrijving = true;

        for (Partij partij : ronde.getPartijen()) {
            if (isNogGeenLid(partij.getSpelerZwart()) || isNogGeenLid(partij.getSpelerWit())) {
                isGeenRondeMetSpelersZonderInschrijving = false;
            }
        }
        return isGeenRondeMetSpelersZonderInschrijving;
    }


    /**
     * Deze methode zorgt ervoor dat een Ronde kan worden toegevoegd aan een competie. De ronde die de gebruiker probeert
     * toe te voegen zal alleen worden toegevoegd indien deze binnen de periode van begin- en einddatum van de competitie
     * zit en alle spelers in de ronde een inschrijving voor de competitie hebben.
     * Ook wordt er gekeken of het maximaal aantal ronden dat per dag kan worden gespeeld (5) niet al is bereikt.
     * Nadat een ronde is toegevoegd, krijgt het ronde Object van deze methode ook de ledenlijst van de competitie mee via
     * setOpToegevoegdAanCompetitie.
     *
     * @param ronde dit is de ronde die aan de competitie toegevoegd moet worden.
     */
    public void voegRondeToe(Ronde ronde) {

        if (isGeenRondeMetSpelersZonderInschrijving(ronde) &&
                (ronde.getSpeelDatum().isEqual(beginDatum) || ronde.getSpeelDatum().isAfter(beginDatum)) &&
                (ronde.getSpeelDatum().isEqual(eindDatum) || ronde.getSpeelDatum().isBefore(eindDatum)) &&
                getAantalIngeplandeRonden(ronde.getSpeelDatum()) < MAX_AANTAL_RONDEN_PER_DAG) {

            ronden.add(ronde);
            ronde.setOpToegevoegdAanCompetitie(leden);

        } else if (!ronde.getSpeelDatum().isAfter(beginDatum) && !ronde.getSpeelDatum().isEqual(beginDatum)) {

            System.out.println("De datum van de ronde is voor de eerste datum van de competitie en daarom is de ronde " +
                    "niet toegevoegd.");

        } else if (!ronde.getSpeelDatum().isBefore(eindDatum) && !ronde.getSpeelDatum().isEqual(eindDatum)) {

            System.out.println("De datum van de ronde is na de laatste dag van de competitie. De ronde kan daarom "
                    + "niet aan de competitie worden toegevoegd.");

        } else if (getAantalIngeplandeRonden(ronde.getSpeelDatum()) > MAX_AANTAL_RONDEN_PER_DAG) {

            System.out.println("maximale aantal ronden per dag (5) voor de datum van de ronde is in deze competitie " +
                    "al bereikt.");

        } else {
            System.out.println("De ronde die u probeert toe te voegen bevat spelers die zich niet hebben ingeschreven" +
                    " voor de competitie, de ronde kan hierom niet aan de competitie worden toegevoegd.");
        }


    }

    /**
     * Deze methode geeft het aantal ingeplande ronden voor een bepaalde datum terug.
     *
     * @param datum de datum waarnaar gekeken wordt bij het optellen van het aantal reeds ingeplande rondes.
     * @return aantal ingeplande rondes.
     */
    public int getAantalIngeplandeRonden(LocalDate datum) {

        int aantal = 0;
        for (Ronde ronde : ronden) {
            if (ronde.getSpeelDatum().isEqual(datum)) {
                aantal++;
            }

        }
        return aantal;

    }

    @Override
    public String toString() {
        return "Competitie: "+ naam +  " beginDatum: " + beginDatum + " eindDatum: " + eindDatum ;
    }

    public int getRondensize() {
        return ronden.size();
    }
}
