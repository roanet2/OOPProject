package models;

import java.time.LocalDate;

/**
 * Deze class representeert een lid met een naam, geboortedatum en eventuele rating.
 */
public class Lid implements Comparable<Lid>{

    private String naam;
    private int rating;
    private LocalDate geboorteDatum;

    public Lid(String naam, LocalDate geboorteDatum){
        this.naam = naam;
        this.geboorteDatum = geboorteDatum;
    }

    public Lid(String naam, LocalDate geboorteDatum, int rating){
        this(naam, geboorteDatum);
        this.rating = rating;
    }

    public void setNieuweRating(int rating){
        this.rating = rating;
    }

    public String getNaam(){
        return naam;
    }

    @Override
    public int compareTo(Lid anderLid) {
        return (naam.compareTo(anderLid.naam));
    }
}
