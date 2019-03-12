package views;

import controllers.LidController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.Lid;


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

        maakButton = new Button("Nieuw");
        verwijderButton = new Button("Verwijder");
        geboorteDatum = new DatePicker();
        rating = new TextField();


        GridPane lidInvoer = new GridPane();
        lidInvoer.setGridLinesVisible(false);
        lidInvoer.addRow(0, maakHBox("Voornaam", voornaam));

        lidInvoer.addRow(1, maakHBox("Tussenvoegsel", tussenVoegsel));
        lidInvoer.addRow(2, maakHBox("Achternaam", achternaam));
        lidInvoer.addRow(3, maakHBox("Geboortedatum", geboorteDatum));
        lidInvoer.addRow(4, maakHBox("Rating", rating));
        lidInvoer.addRow(5, maakHBox(verwijderButton, maakButton));

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(lidInvoer);
        borderPane.setCenter(lidListView);
        borderPane.setMaxSize(800, 1080);


        return borderPane;


    }

    private HBox maakHBox(String labelnaam, Node node) {
        HBox nieuweHBox = new HBox();
        nieuweHBox.setSpacing(20);
        nieuweHBox.setPadding(new Insets(15, 15, 15, 15));
        nieuweHBox.getChildren().addAll(new Label(labelnaam), node);

        return nieuweHBox;

    }

    private HBox maakHBox(Button button1, Button button2) {
        HBox nieuweHBox = new HBox();
        nieuweHBox.setSpacing(20);
        nieuweHBox.setPadding(new Insets(15, 15, 15, 15));
        nieuweHBox.getChildren().addAll(button1, button2);

        return nieuweHBox;

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


    public Pane getRoot(){
        return root;
    }


}




