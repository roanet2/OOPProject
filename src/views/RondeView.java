package views;


import controllers.RondeController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Ronde;
import models.Competitie;
import utils.CustomHBox;

public class RondeView {

    private static final String TITEL = "Ronde";
    private Stage stage;
    private Pane root;
    private RondeController controller;

    private DatePicker speelDatum;
    private TextField rondeNummer;
    private ListView<Ronde> rondeListView;
    private Button maakButton;
    private Button verwijderButton;
    private Button afsluitenTempButton;


    public RondeView(RondeController controller) {
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
        rondeListView = new ListView<>();

        maakButton = new Button("Nieuw");
        verwijderButton = new Button("Verwijder");
        speelDatum = new DatePicker();
        rondeNummer = new TextField();
        afsluitenTempButton = new Button("sluiten");

        GridPane rondeInvoer = new GridPane();
        rondeInvoer.setGridLinesVisible(false);
        rondeInvoer.addRow(0, CustomHBox.maakHBox("Ronde nummer", rondeNummer));
        rondeInvoer.addRow(1, CustomHBox.maakHBox("Speeldatum", speelDatum));
        rondeInvoer.addRow(2, CustomHBox.maakHBox(maakButton,verwijderButton) );
        BorderPane borderPane = new BorderPane();

        borderPane.setLeft(rondeInvoer);
        borderPane.setCenter(rondeListView);

        //Tijdelijke afsluit-button
        borderPane.setRight(afsluitenTempButton);
        //



        return borderPane;

    }

    private HBox maakHBox(String labelnaam, Node node) {
        HBox nieuweHBox1 = new HBox();
        nieuweHBox1.setSpacing(20);
        nieuweHBox1.setPadding(new Insets(15, 15, 15, 15));
        nieuweHBox1.getChildren().addAll(new Label(labelnaam), node);

        return nieuweHBox1;

    }

    private void setupController() {
        controller.setMaakButton(maakButton);
        controller.setVerwijderButton(verwijderButton);
        controller.setRondeListView(rondeListView);
        controller.setAfsluitenTempButton(afsluitenTempButton);
        controller.setSpeelDatum(speelDatum);
        controller.initialize();
    }



    public Pane getRoot() {
        return root;
    }
//    public void show() {
//        stage.show();
//    }
}

