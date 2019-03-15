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

    private static final String TITEL = "SchaakCompetitie";
    public static final int WIDTH = 780;
    public static final int HEIGHT = 550;

    private MenuBar menuBar;
    private BorderPane borderPane;

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
        voegRondeToeMenuItem = new MenuItem("Voeg ronde toe");


        competitieMenu.getItems().addAll(
                maakCompetitieMenuItem,
                voegRondeToeMenuItem
        );

        //Wedstrijd-menu
        Menu rondeMenu = new Menu("Ronde");

        maakRondeMenuItem = new MenuItem("Maak ronde");
        voegPartijToeMenuItem = new MenuItem("Voeg partij toe");

        rondeMenu.getItems().addAll(maakRondeMenuItem, voegPartijToeMenuItem);

        //Partij-menu
        Menu partijMenu = new Menu("Partij");
        maakPartijMenuItem = new MenuItem("Maak Partij");
        partijMenu.getItems().add(maakPartijMenuItem);

        //Lid-menu
        Menu lidMenu = new Menu("Lid");
        maakLidMenuItem = new MenuItem("Maak Lid");
        lidMenu.getItems().add(maakLidMenuItem);


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
                rondeMenu,
                partijMenu,
                lidMenu,
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
        controller.setVoegRondeToeMenuItem(voegRondeToeMenuItem);
        controller.setMaakRondeMenuItem(maakRondeMenuItem);
        controller.setVoegPartijToeMenuItem(voegPartijToeMenuItem);
        controller.setMaakPartijMenuItem(maakPartijMenuItem);
        controller.setMaakLidMenuItem(maakLidMenuItem);
        controller.setToonWedstrijdenMenuItem(toonWedstrijdenMenuItem);
        controller.setToonCompetitieStandMenuItem(toonCompetitieStandMenuItem);
        controller.setOverMenuItem(overMenuItem);

        controller.setLabel(melding);
        controller.setBorderPane(borderPane);

        controller.initialize();
    }

    /**
     * Toon dit scherm in het midden van de hoofdmonitor
     */
    public void show() {
        show(stage, WIDTH, HEIGHT);
    }

    static void show(Stage stage, int width, int height) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        stage.setX((primaryScreenBounds.getWidth() - width) /2f );
        stage.setY((primaryScreenBounds.getHeight() - height) / 2f);
        stage.setWidth(width);
        stage.setHeight(height);

        stage.show();
    }
}
