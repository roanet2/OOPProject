package views;

import controllers.CompetitieController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Competitie;
import utils.CustomHBox;

public class    CompetitieView {

    private static final String TITEL = "Competitie";
    private Stage stage;
    private Pane root;
    private CompetitieController controller;

    private DatePicker beginDatum;
    private DatePicker eindDatum;
    private TextField competitieNaam;
    private ListView<Competitie> competitieListView;
    private Button maakButton;
    private Button verwijderButton;
    private Button afsluitenTempButton;

    public CompetitieView(CompetitieController controller) {
        this.controller = controller;

//        stage = new Stage();
//        stage.setTitle(TITEL);
//
//        Parent root = createRoot();
//        Scene scene = new Scene(root);
//
//        stage.setScene(scene);
//        stage.setResizable(true);

        root = createRoot();
        setupController();

    }

    private Pane createRoot() {
        competitieListView = new ListView<>();

        maakButton = new Button("Nieuw");
        verwijderButton = new Button("Verwijder");
        competitieNaam = new TextField();
        beginDatum = new DatePicker();
        eindDatum = new DatePicker();
        afsluitenTempButton = new  Button("Afsluiten");

        GridPane CompetitieInvoer = new GridPane();
        CompetitieInvoer.setGridLinesVisible(false);
        CompetitieInvoer.addRow(0, CustomHBox.maakHBox("Competitienaam", competitieNaam));
        CompetitieInvoer.addRow(1, CustomHBox.maakHBox("Begindatum:", beginDatum));
        CompetitieInvoer.addRow(2, CustomHBox.maakHBox("Einddatum:", eindDatum));
        CompetitieInvoer.addRow(3,CustomHBox.maakHBox(verwijderButton, maakButton));
        CompetitieInvoer.addRow(4,CustomHBox.maakHBox("exit",afsluitenTempButton));
        CompetitieInvoer.addRow(8, new Label("* = Invoer verplicht"));


        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(CompetitieInvoer);
        borderPane.setCenter(competitieListView);

        borderPane.setMaxSize(800, 1080);

        return borderPane;


    }

    private HBox maakHBox(String labelnaam, Node node) {
        HBox nieuweHBox = new HBox();
        nieuweHBox.setSpacing(10);
        nieuweHBox.setPadding(new Insets(15, 15, 15, 15));
        nieuweHBox.getChildren().addAll(new Label(labelnaam), node);

        return nieuweHBox;

    }

    private void setupController() {
        controller.setMaakButton(maakButton);
        controller.setCompetitieNaam(competitieNaam);
        controller.setcompetitieListView(competitieListView);
        controller.setVerwijderButton(verwijderButton);
        controller.setBeginDatum(beginDatum);
        controller.setEindDatum(eindDatum);
        controller.setAfsluitenTempButton(afsluitenTempButton);
        controller.initialize();
    }


    public Pane getRoot(){
        return root;
    }
//    public void show() {
//        stage.show();
//    }
//


}

