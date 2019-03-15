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

public class CompetitieView {

    private static final String TITEL = "Competitie";
    private Stage stage;
    private Pane root;
    private CompetitieController controller;

    private DatePicker beginDatum;
    private DatePicker EindDatum;
    private TextField Competitienaam;
    private ListView<Competitie> competitieListView;
    private Button maakButton;
    private Button verwijderButton;


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
        Competitienaam = new TextField();
        beginDatum = new DatePicker();
        EindDatum = new DatePicker();

        GridPane rondeInvoer = new GridPane();
        rondeInvoer.setGridLinesVisible(false);
        rondeInvoer.addRow(0, maakHBox("Competitienaam", Competitienaam));
        rondeInvoer.addRow(1, maakHBox("Begindatum:", beginDatum));
        rondeInvoer.addRow(2, maakHBox("Einddatum:", EindDatum));

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(rondeInvoer);
        borderPane.setCenter(competitieListView);

        return borderPane;


    }

    private HBox maakHBox(String labelnaam, Node node) {
        HBox nieuweHBox2 = new HBox();
        nieuweHBox2.setSpacing(10);
        nieuweHBox2.setPadding(new Insets(15, 15, 15, 15));
        nieuweHBox2.getChildren().addAll(new Label(labelnaam), node);

        return nieuweHBox2;

    }

    private void setupController() {
        controller.setMaakButton(maakButton);
        controller.setcompetitieListView(competitieListView);
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

