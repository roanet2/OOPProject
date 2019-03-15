package views;

import controllers.LidController;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import models.Lid;
import utils.CustomHBox;

public class LidView {

    private Pane root;
    private LidController controller;

    private TextField voornaam;
    private TextField tussenVoegsel;
    private TextField achternaam;
    private DatePicker geboorteDatum;
    private TextField rating;
    private ListView<Lid> lidListView;
    private Button maakButton;
    private Button verwijderButton;

    public LidView(LidController controller) {
        this.controller = controller;

        root = createRoot();
        setupController();

    }


    private Pane createRoot() {
        voornaam = new TextField();
        tussenVoegsel = new TextField();
        achternaam = new TextField();
        lidListView = new ListView<>();

        maakButton = new Button("Bewaar");
        verwijderButton = new Button("Verwijder");
        geboorteDatum = new DatePicker();
        rating = new TextField();


        GridPane lidInvoer = new GridPane();
        lidInvoer.setGridLinesVisible(false);
        lidInvoer.addRow(0, CustomHBox.maakHBox("Voornaam", voornaam));
        lidInvoer.addRow(1, CustomHBox.maakHBox("TussenVoegsel", tussenVoegsel));
        lidInvoer.addRow(2, CustomHBox.maakHBox("Achternaam", achternaam));
        lidInvoer.addRow(3, CustomHBox.maakHBox("Geboortedatum", geboorteDatum));
        lidInvoer.addRow(4, CustomHBox.maakHBox("Rating", rating));
        lidInvoer.addRow(5, CustomHBox.maakHBox(verwijderButton, maakButton));
        lidInvoer.addRow(8, new Label("* = Invoer verplicht"));

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(lidInvoer);
        borderPane.setCenter(lidListView);
        borderPane.setMaxSize(800, 1080);


        return borderPane;


    }

    private void setupController() {
        controller.setMaakButton(maakButton);
        controller.setVerwijderButton(verwijderButton);
        controller.setLidListView(lidListView);
        controller.setVoornaamTF(voornaam);
        controller.setTussenVoegselTF(tussenVoegsel);
        controller.setAchternaamTF(achternaam);
        controller.setGeboorteDatumDP(geboorteDatum);
        controller.setRatingTF(rating);

        controller.initialize();
    }


    public Pane getRoot() {
        return root;
    }


}




