package views;

import controllers.PartijController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import models.Lid;
import models.Partij;
import javafx.scene.control.*;
import utils.CustomHBox;


public class PartijView {

    private Pane root;
    private PartijController controller;
    private ListView<Partij> partijListView;
    private ListView<Lid> ledenListView;
    private Button verwijderPartijButton;
    private Button voegSpelerToeButton;

    public PartijView(PartijController controller) {

        this.controller = controller;
        root = createRoot();
        setupController();
    }


    private Pane createRoot() {
        partijListView = new ListView<>();
        ledenListView = new ListView<>();
        verwijderPartijButton = new Button("Verwijder");
        voegSpelerToeButton = new Button("Voeg toe");

        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(20);
        flowPane.getChildren().add(CustomHBox.maakHBox(partijListView, ledenListView));
        flowPane.setAlignment(Pos.CENTER_LEFT);

        VBox vBox = new VBox();
        vBox.getChildren().add(CustomHBox.maakHBox(verwijderPartijButton, voegSpelerToeButton));
        vBox.setPadding(new Insets(350, 0, 0, 0));

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(flowPane);
        borderPane.setRight(vBox);
        borderPane.getCenter().setVisible(true);


        return borderPane;
    }

    private void setupController() {
        controller.setPartijListView(partijListView);
        controller.setLedenListView(ledenListView);
        controller.setVerwijderPartijButton(verwijderPartijButton);
        controller.setVoegSpelerToeButton(voegSpelerToeButton);

        controller.initialize();
    }

    public Pane getRoot() {
        return root;
    }


}

