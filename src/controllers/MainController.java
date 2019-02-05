package controllers;

import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import views.AboutView;

/**
 * Logica van het hoofdscherm
 *
 * @author HvA HBO-ICT
 */
public class MainController {

    private MenuItem laadMenuItem;
    private MenuItem bewaarMenuItem;
    private MenuItem afsluitenMenuItem;
    private MenuItem maakCompetitieMenuItem;
    private MenuItem maakWedstrijdMenuItem;
    private MenuItem maakTeamMenuItem;
    private MenuItem gebruikTeamMenuItem;
    private MenuItem toonWedstrijdenMenuItem;
    private MenuItem toonCompetitieStandMenuItem;
    private MenuItem overMenuItem;
    private Label melding;

    private AboutView aboutView;

    /**
     * Wanneer deze methode wordt aangeroepen, is het veilig om bijv. de menu-items aan te passen.
     */
    public void initialize() {
        AboutController aboutController = new AboutController();
        AboutView aboutView = new AboutView(aboutController);

        afsluitenMenuItem.setOnAction(t -> {
            System.out.println("Afsluiten aangeroepen.");

            melding.setText("Afsluiten aangeroepen.");

            System.exit(0);
        });

        overMenuItem.setOnAction(t -> {
            System.out.println("Over aangeroepen.");

            melding.setText("Over aangeroepen.");

            aboutView.show();
        });
    }

    public void setLaadMenuItem(MenuItem laadMenuItem) {
        this.laadMenuItem = laadMenuItem;
    }

    public void setBewaarMenuItem(MenuItem bewaarMenuItem) {
        this.bewaarMenuItem = bewaarMenuItem;
    }

    public void setAfsluitenMenuItem(MenuItem afsluitenMenuItem) {
        this.afsluitenMenuItem = afsluitenMenuItem;
    }

    public void setMaakCompetitieMenuItem(MenuItem maakCompetitieMenuItem) {
        this.maakCompetitieMenuItem = maakCompetitieMenuItem;
    }

    public void setMaakWedstrijdMenuItem(MenuItem maakWedstrijdMenuItem) {
        this.maakWedstrijdMenuItem = maakWedstrijdMenuItem;
    }

    public void setMaakTeamMenuItem(MenuItem maakTeamMenuItem) {
        this.maakTeamMenuItem = maakTeamMenuItem;
    }

    public void setGebruikTeamMenuItem(MenuItem gebruikTeamMenuItem) {
        this.gebruikTeamMenuItem = gebruikTeamMenuItem;
    }

    public void setToonWedstrijdenMenuItem(MenuItem toonWedstrijdenMenuItem) {
        this.toonWedstrijdenMenuItem = toonWedstrijdenMenuItem;
    }

    public void setToonCompetitieStandMenuItem(MenuItem toonCompetitieStandMenuItem) {
        this.toonCompetitieStandMenuItem = toonCompetitieStandMenuItem;
    }

    public void setOverMenuItem(MenuItem overMenuItem) {
        this.overMenuItem = overMenuItem;
    }

    public void setMelding(Label melding) {
        this.melding = melding;
    }
}
