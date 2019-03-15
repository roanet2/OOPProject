package controllers;

import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import utils.StateManager;
import views.AboutView;
import views.LidView;

import javax.swing.plaf.nimbus.State;

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

        AboutController aboutController = new AboutController();
        aboutView = new AboutView(aboutController);

        maakPartijMenuItem.setOnAction(event ->{
            StateManager.switchView(StateManager.PARTIJ_VIEW);
        });

        maakLidMenuItem.setOnAction(event ->{
            StateManager.switchView(StateManager.LID_VIEW);
            System.out.println("Aangeroepen.");

        });

        afsluitenMenuItem.setOnAction(event -> {
            System.out.println("Afsluiten aangeroepen.");

            laatLabelZien("Afsluiten aangeroepen.");
            System.exit(0);
        });

        overMenuItem.setOnAction(event -> {
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

    public void setVoegPartijToeMenuItem(MenuItem voegPartijToeMenuItem){
        this.voegPartijToeMenuItem = voegPartijToeMenuItem;
    }

    public void setMaakPartijMenuItem(MenuItem maakPartijMenuItem){
        this.maakPartijMenuItem = maakPartijMenuItem;
    }

    public void setMaakLidMenuItem(MenuItem maakLidMenuItem){
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

    public void laatLabelZien(String bericht){
        melding.setText(bericht);
    }
    public void setBorderPane(BorderPane borderPane){
        this.borderPane = borderPane;
    }

    public BorderPane getBorderPane(){
        return borderPane;
    }

}
