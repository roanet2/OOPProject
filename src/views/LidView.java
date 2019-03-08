package views;

import controllers.LidController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.Lid;

public class LidView {

    private static final String TITEL = "Lid";
    private Stage stage;
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
        voornaam = new TextField();
        tussenVoegsel = new TextField();
        achternaam = new TextField();
        lidListView = new ListView<>();

        maakButton = new Button("Nieuw");
        verwijderButton = new Button("Verwijder");
        geboorteDatum = new DatePicker();


        GridPane lidInvoer = new GridPane();
        lidInvoer.setGridLinesVisible(false);
        lidInvoer.addRow(0, maakHBox("Voornaam", voornaam));
        lidInvoer.addRow(1, maakHBox("Tussenvoegsel", tussenVoegsel));
        lidInvoer.addRow(2, maakHBox("Achternaam", achternaam));
        lidInvoer.addRow(3, maakHBox("Geboortedatum", geboorteDatum));

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(lidInvoer);
        borderPane.setCenter(lidListView);

        return borderPane;


    }

    private HBox maakHBox(String labelnaam, Node node) {
        HBox nieuweHBox = new HBox();
        nieuweHBox.setSpacing(15);
        nieuweHBox.setPadding(new Insets(15, 15, 15, 15));
        nieuweHBox.getChildren().addAll(new Label(labelnaam), node);

        return nieuweHBox;

    }

    private void setupController() {
        controller.setMaakButton(maakButton);
        controller.setLidListView(lidListView);
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




