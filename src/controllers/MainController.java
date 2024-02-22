package controllers;

import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import utils.StateManager;
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
    private MenuItem voegRondeToeMenuItem;
    private MenuItem maakRondeMenuItem;
    private MenuItem voegPartijToeMenuItem;
    private MenuItem maakPartijMenuItem;
    private MenuItem maakLidMenuItem;
    private MenuItem toonWedstrijdenMenuItem;
    private MenuItem toonCompetitieStandMenuItem;
    private MenuItem overMenuItem;
    private Label melding;

    private BorderPane borderPane;
    private AboutView aboutView;

    /**
     * Wanneer deze methode wordt aangeroepen, is het veilig om bijv. de menu-items aan te passen.
     */
    public void initialize() {
        StateManager.initialize(this);

        laadMenuItem.setOnAction(event -> {
            System.out.println("inladen leden: " + StateManager.getLedenRepository().load());
            System.out.println("inladen partijen: " + StateManager.getPartijenRepository().load());
            StateManager.getLedenRepository().load();
            StateManager.getPartijenRepository().load();
            System.out.println("\n");
            System.out.println("inladen leden: " + StateManager.getLedenRepository().load());
            System.out.println("inladen partijen: " + StateManager.getPartijenRepository().load());

        });

        bewaarMenuItem.setOnAction(event -> {
            System.out.println("Bewaren leden: " + StateManager.getLedenRepository().save());
            System.out.println("Bewaren Partijen: " + StateManager.getPartijenRepository().save());
            StateManager.getLedenRepository().save();
            StateManager.getPartijenRepository().save();

            System.out.println("\n");
            System.out.println("Bewaren leden: " + StateManager.getLedenRepository().save());
            System.out.println("Bewaren Partijen: " + StateManager.getPartijenRepository().save());

        });

        maakPartijMenuItem.setOnAction(event -> {
            StateManager.switchView(StateManager.PARTIJ_VIEW);
        });

        maakCompetitieMenuItem.setOnAction(event -> {
            StateManager.switchView(StateManager.COMPETITIE_VIEW);
        });

        maakLidMenuItem.setOnAction(event -> {
            StateManager.switchView(StateManager.LID_VIEW);
            System.out.println("Aangeroepen.");
            // System.out.println("Lengte-indicatie: " + StateManager.getLeden().toArray().length);

        });


        maakCompetitieMenuItem.setOnAction(event ->{
            StateManager.switchView(StateManager.COMPETITIE_VIEW);
            System.out.println("Aangeroepen.");

        });

        maakRondeMenuItem.setOnAction(event ->{
            StateManager.switchView(StateManager.RONDE_VIEW);
            System.out.println("Aangeroepen.");

        });
        afsluitenMenuItem.setOnAction(event -> {
            System.out.println("Afsluiten aangeroepen.");

            laatLabelZien("Afsluiten aangeroepen.");
            System.exit(0);
        });

        overMenuItem.setOnAction(event -> {
            AboutController aboutController = new AboutController();
            aboutView = new AboutView(aboutController);
            System.out.println("Over aangeroepen.");
            laatLabelZien("Over aangeroepen.");
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


    public void setVoegRondeToeMenuItem(MenuItem voegRondeToeMenuItem) {
        this.voegRondeToeMenuItem = voegRondeToeMenuItem;
    }

    public void setMaakRondeMenuItem(MenuItem maakRondeMenuItem) {
        this.maakRondeMenuItem = maakRondeMenuItem;
    }

    public void setVoegPartijToeMenuItem(MenuItem voegPartijToeMenuItem) {
        this.voegPartijToeMenuItem = voegPartijToeMenuItem;
    }

    public void setMaakPartijMenuItem(MenuItem maakPartijMenuItem) {
        this.maakPartijMenuItem = maakPartijMenuItem;
    }

    public void setMaakLidMenuItem(MenuItem maakLidMenuItem) {
        this.maakLidMenuItem = maakLidMenuItem;
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

    public void setLabel(Label melding) {
        this.melding = melding;

    }

    public void laatLabelZien(String bericht) {
        melding.setText(bericht);
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

}
