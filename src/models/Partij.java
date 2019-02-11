package models;
/**
 * Deze class representeert een Partij met spelers en een mogelijke winnaar/remise.
 */
public class Partij {


    private Lid spelerZwart;
    private Lid spelerWit;
    private Lid winnaar;
    private boolean remise;

    public Partij(Lid spelerZwart, Lid spelerWit) {
        this.spelerZwart = spelerZwart;
        this.spelerWit = spelerWit;
    }

    public Lid getSpelerZwart() {
        return spelerZwart;
    }

    public Lid getSpelerWit() {
        return spelerWit;
    }

    public void setWinnaar(Lid winnaar) {
        this.winnaar = winnaar;
    }

    public void setRemise(boolean remise) {
        this.remise = remise;
    }

    public double getGewonnenPunten() {

        double punten = 0;

        if (winnaar != null) {
            punten = 1.0;

        } else if (remise) {
            punten = (0.5);
        }

        return punten;
    }
}