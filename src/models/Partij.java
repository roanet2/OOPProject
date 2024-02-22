package models;

import java.io.Serializable;

/**
 * Deze class representeert een Partij met spelers en een mogelijke winnaar/remise. Bij het aanmaken van de partij wordt
 * voor iedere speler ook een verwachtingwaarde berekent -aan hand van het ratingsverschil- volgens de Elo-methode.
 * Deze zorgt ervoor dat een speler bij een betere opponent een grotere toename ziet in zijn/haar rating bij winst
 * en vice versa.
 */
public class Partij implements Serializable {


    private Lid spelerZwart;
    private Lid spelerWit;
    private double winstVerwachtingSpelerZwart;
    private double winstVerwachtingSpelerWit;
    private Lid winnaar;
    private boolean remise;

    public Partij(Lid spelerZwart, Lid spelerWit) {
        this.spelerZwart = spelerZwart;
        this.spelerWit = spelerWit;

        berekenKansOpWinst(spelerZwart, spelerWit);
    }

    public boolean isGespeeld(){
        return winnaar != null || remise;
    }

    /**
     * De methode berekent volgens de Elo-standaard de kans dat een speler wint aan de hand van het ratingverschil onderling.
     * Degene met de laagste rating heeft daardoor dus logischerwijs ook de laagste winkans.
     *
     * @param spelerZwart De speler die met zwart speelt
     * @param spelerWit   De speler die met de witte schaakstukken speelt.
     */
    public void berekenKansOpWinst(Lid spelerZwart, Lid spelerWit) {
        winstVerwachtingSpelerZwart = 1.0 / (1.0 + Math.pow(10, ((spelerWit.getRating() - spelerZwart.getRating()) / 400.0)));
        winstVerwachtingSpelerWit = 1.0 / (1.0 + Math.pow(10, ((spelerZwart.getRating() - spelerWit.getRating()) / 400.0)));

    }

    public Lid getSpelerZwart() {
        return spelerZwart;
    }

    public Lid getSpelerWit() {
        return spelerWit;
    }

    public double getWinstVerwachtingSpelerZwart(){
        return winstVerwachtingSpelerZwart;
    }

    public double getWinstVerwachtingSpelerWit(){
        return winstVerwachtingSpelerWit;
    }


    public void setWinnaar(Lid winnaar) {
        this.winnaar = winnaar;
        berekenNieuweRating();
    }

    public void setRemise() {
        remise = true;
        berekenNieuweRating();
    }

    /**
     * Deze methode berekent de nieuwe rating aan hand van de uitslag en de winstverwachtingen en maakt gebruik van de
     * hulpmethode setNieuweRating() om de nieuwe op te slaan in de rating variabele die toebehoort aan een lid.
     */
    public void berekenNieuweRating() {
        int nieuweRatingSpelerZwart;
        int nieuweRatingSpelerWit;
        nieuweRatingSpelerZwart = (int)(getSpelerZwart().getRating() + (getGewonnenPunten(getSpelerZwart())
                - winstVerwachtingSpelerZwart) * getSpelerZwart().getKFactor());
        nieuweRatingSpelerWit =  (int)(getSpelerWit().getRating() +  (getGewonnenPunten(getSpelerWit())
                - winstVerwachtingSpelerWit) * getSpelerWit().getKFactor());

        setNieuweRating(nieuweRatingSpelerZwart, nieuweRatingSpelerWit);

    }

    public void setNieuweRating(int nieuweRatingSpelerZwart, int nieuweRatingSpelerWit) {
        getSpelerZwart().setNieuweRating(nieuweRatingSpelerZwart);
        getSpelerWit().setNieuweRating(nieuweRatingSpelerWit);

    }

    public double getGewonnenPunten(Lid lid) {

        double punten = 0;

        if (winnaar != null && lid.compareTo(winnaar) == 0) {
            punten = 1.0;

        } else if (remise) {
            punten = (0.5);
        }

        return punten;
    }

    @Override
    public String toString() {
        String informatie;
        if(winnaar != null){
            informatie = "*-*Opponenten (zwart-wit): " + spelerZwart.getNaam() + " - " +  spelerWit.getNaam() +
                    ", WINAAR = " + winnaar.getNaam();

        }else if(remise){
            informatie = "*-*Opponenten (zwart-wit): " + spelerZwart.getNaam() + " - " +  spelerWit.getNaam() +
                    ", REMISE" ;

        }else{
           informatie = "Opponenten (zwart-wit): " + spelerZwart.getNaam() + " - " +  spelerWit.getNaam();
        }

        return informatie;

    }
}