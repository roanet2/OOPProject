package models;

import java.time.LocalDate;
import java.time.Period;

/**
 * Deze class representeert een lid met een naam, geboortedatum en eventuele rating.
 */
public class Lid implements Comparable<Lid> {

    private static final int RATING_DEFAULT = 1200;
    private String naam;
    private int rating;
    private LocalDate geboorteDatum;

    public Lid(String naam, LocalDate geboorteDatum) {
        this.naam = naam;
        this.geboorteDatum = geboorteDatum;
        rating = RATING_DEFAULT;
    }

    public Lid(String naam, LocalDate geboorteDatum, int rating) {
        this(naam, geboorteDatum);
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public int getLeefTijdInJaren() {
        LocalDate datumVandaag = LocalDate.now();
        return (Period.between(geboorteDatum, datumVandaag).getYears());
    }

    /**
     * Deze methode geeft de K-Factor van een speler terug.
     * Deze is afhankelijk van de rating en de leeftijd van een speler.
     *
     * @return
     */
    public int getKFactor() {
        int kFactor;

        if (getLeefTijdInJaren() < 18 && rating < 2400 && rating >= 2300 || getLeefTijdInJaren() >= 18 && rating < 2400) {
            kFactor = 20;
        } else if (getLeefTijdInJaren() < 18 && rating < 2300) {
            kFactor = 40;
        } else {
            kFactor = 10;
        }

        return kFactor;
    }

    public void setNieuweRating(int rating) {
        this.rating = rating;
    }

    public String getNaam() {
        return naam;
    }

    @Override
    public int compareTo(Lid anderLid) {
        return (naam.compareTo(anderLid.naam));
    }

    @Override
    public String toString() {
        return naam + " " + geboorteDatum + " Rating: " + rating;
    }
}
