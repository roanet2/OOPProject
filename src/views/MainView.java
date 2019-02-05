package views;

import controllers.MainController;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * UI van het hoofdscherm
 *
 * @author HvA HBO-ICT
 */
public class MainView {

    private static final String TITEL = "Competitie";
    private static final int WIDTH = 550;
    private static final int HEIGHT = 320;

    private MenuBar menuBar;
    private BorderPane borderPane;

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

    private MainController controller;
    private Stage stage;

    /**
     * Maakt een instantie van de UI van het hoofdscherm
     *
     * @param controller Een instantie van de controller die nodig is om het hoofdscherm aan te sturen
     */
    public MainView(MainController controller) {
        this.controller = controller;

        stage = new Stage();
        stage.setTitle(TITEL);

        Parent root = createRoot();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(true);

        setupController();
    }

    private Parent createRoot() {
        menuBar = createMenuBar();
        borderPane = createBorderPane();

        return new VBox(menuBar, borderPane);
    }

    private MenuBar createMenuBar() {
        //Spel-menu
        Menu spelMenu = new Menu("Spel");

        laadMenuItem = new MenuItem("Laad");
        bewaarMenuItem = new MenuItem("Bewaar");
        afsluitenMenuItem = new MenuItem("Afsluiten");

        spelMenu.getItems().addAll(
                laadMenuItem,
                bewaarMenuItem,
                new SeparatorMenuItem(),
                afsluitenMenuItem
        );

        //Competitie-menu
        Menu competitieMenu = new Menu("Competitie");

        maakCompetitieMenuItem = new MenuItem("Maak competitie");
        maakTeamMenuItem = new MenuItem("Maak partij");
        gebruikTeamMenuItem = new MenuItem("Voeg partij toe aan competitie");

        competitieMenu.getItems().addAll(
                maakCompetitieMenuItem,
                maakTeamMenuItem,
                gebruikTeamMenuItem
        );

        //Wedstrijd-menu
        Menu wedstrijdMenu = new Menu("Wedstrijd");

        maakWedstrijdMenuItem = new MenuItem("Maak wedstrijduitslag");

        wedstrijdMenu.getItems().add(maakWedstrijdMenuItem);

        //Statistiek-menu
        Menu statistiekMenu = new Menu("Statistiek");

        toonWedstrijdenMenuItem = new MenuItem("Toon gespeelde wedstrijden");
        toonCompetitieStandMenuItem = new MenuItem("Toon competitiestand");

        statistiekMenu.getItems().addAll(
                toonWedstrijdenMenuItem,
                toonCompetitieStandMenuItem
        );

        //Help-menu
        Menu helpMenu = new Menu("Help");

        overMenuItem = new MenuItem("Over: " + TITEL);

        helpMenu.getItems().addAll(overMenuItem);

        //Menubalk
        return new MenuBar(
                spelMenu,
                competitieMenu,
                wedstrijdMenu,
                statistiekMenu,
                helpMenu
        );
    }

    private BorderPane createBorderPane() {
        melding = new Label();
        melding.setStyle("-fx-background-color: bisque");
        melding.setMaxWidth(Double.MAX_VALUE);
        melding.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(melding);

        return borderPane;
    }

    private void setupController() {
        controller.setLaadMenuItem(laadMenuItem);
        controller.setBewaarMenuItem(bewaarMenuItem);
        controller.setAfsluitenMenuItem(afsluitenMenuItem);
        controller.setMaakCompetitieMenuItem(maakCompetitieMenuItem);
        controller.setMaakWedstrijdMenuItem(maakWedstrijdMenuItem);
        controller.setMaakTeamMenuItem(maakTeamMenuItem);
        controller.setGebruikTeamMenuItem(gebruikTeamMenuItem);
        controller.setToonWedstrijdenMenuItem(toonWedstrijdenMenuItem);
        controller.setToonCompetitieStandMenuItem(toonCompetitieStandMenuItem);
        controller.setOverMenuItem(overMenuItem);

        controller.setMelding(melding);

        controller.initialize();
    }

    /**
     * Toon dit scherm in het midden van de hoofdmonitor
     */
    public void show() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        stage.setX((primaryScreenBounds.getWidth() - WIDTH) / 2f);
        stage.setY((primaryScreenBounds.getHeight() - HEIGHT) / 2f);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        stage.show();
    }
}
