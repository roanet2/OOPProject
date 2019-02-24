package models;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Deze class representeert een Ronde met een speeldatum en een verzameling partijen.
 */
public class Ronde {

    private LocalDate speelDatum;
    private ArrayList<Partij> partijen;
    private boolean isToegevoegdAanCompetitie;
    private ArrayList<Lid> ledenInCompetitie;


    public Ronde(LocalDate speelDatum) {

        this.speelDatum = speelDatum;
        partijen = new ArrayList<>();
    }

    public void setOpToegevoegdAanCompetitie(ArrayList<Lid> ledenIncompetitie) {

        this.ledenInCompetitie = ledenIncompetitie;
        isToegevoegdAanCompetitie = true;
    }


    public LocalDate getSpeelDatum() {

        return speelDatum;
    }

    /**
     * Deze methode zorgt ervoor dat een partij toegevoegd kan worden aan een Ronde. Indien de ronde al is toegevoegd
     * aan een competitie, dan wordt ook gekeken of de deelnemers van de partij ingeschreven zijn bij de competitie,
     * alvorens de partij aan deze rond wordt toegevoegd.
     *
     * @param partij de partij die toegevoegd wordt aan de ronde.
     */
    public void voegPartijToe(Partij partij) {

        if (isToegevoegdAanCompetitie) {

            Competitie temp = new Competitie("Middleman", LocalDate.now(), LocalDate.now());
            for (Lid lid : ledenInCompetitie) {
                temp.voegLidToe(lid);
            }

            StringBuilder melding = new StringBuilder();

            if (temp.isNogGeenLid(partij.getSpelerZwart()) && temp.isNogGeenLid(partij.getSpelerWit())) {
                melding.append("De ronde waaraan u een partij probeert toe te voegen komt al voor in een competitie\nwaar de " +
                        "volgende leden geen lid van zijn: ");
                melding.append(partij.getSpelerZwart().getNaam());
                melding.append(" & ");
                melding.append(partij.getSpelerWit().getNaam());

            } else if (temp.isNogGeenLid(partij.getSpelerZwart())) {
                melding.append("De ronde waaraan u een partij probeert toe te voegen komt al voor in een competitie\nwaar de " +
                        "volgende leden geen lid van zijn: ");
                melding.append(partij.getSpelerZwart().getNaam());

            } else if (temp.isNogGeenLid(partij.getSpelerZwart())) {
                melding.append("De ronde waaraan u een partij probeert toe te voegen komt al voor in een competitie\nwaar de " +
                        "volgende leden geen lid van zijn: ");
                melding.append(partij.getSpelerWit().getNaam());

            } else {
                melding.append("Partij met succes toegevoegd aan ronde!");
                partijen.add(partij);
            }

            System.out.println(melding);


        } else {
            partijen.add(partij);
        }


    }

    public ArrayList<Partij> getPartijen() {
        return partijen;
    }

}
