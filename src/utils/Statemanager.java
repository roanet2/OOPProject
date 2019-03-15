package utils;

import controllers.LidController;
import controllers.MainController;
import controllers.PartijController;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import models.Lid;
import views.LidView;
import views.PartijView;

import java.util.ArrayList;

public class StateManager {

    public static final int GEEN_VIEW = 0;
    public static final int LID_VIEW = 1;
    public static final int PARTIJ_VIEW = 2;

    private static MainController mainController;
    private static ArrayList<Lid> leden;


    public static void initialize(MainController mainController) {
        StateManager.mainController = mainController;
        leden = new ArrayList<>();

    }

    public static void switchView(int view) {
        switch (view) {
            case GEEN_VIEW:
                laatLabelZien("");
                maakCentrePaneLeeg();
                break;

            case LID_VIEW:
                LidController lidController = new LidController();
                LidView lidView = new LidView(lidController);
                laatLabelZien("Aanmaken leden");
                setCentrePane(lidView.getRoot());
                break;

            case PARTIJ_VIEW:
                PartijController partijController = new PartijController();
                PartijView partijView = new PartijView(partijController);
                laatLabelZien("Aanmaken partijen");
                setCentrePane(partijView.getRoot());
        }
    }


    public static void laatLabelZien(String labelnaam) {
        mainController.laatLabelZien(labelnaam);

    }

    public static void geefAlertScherm(String melding) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informatie");
        alert.setHeaderText("Lees de informatie!");
        alert.setContentText(melding);
        alert.showAndWait();


    }

    public static void setCentrePane(Pane pane) {
        mainController.getBorderPane().setCenter(pane);


    }

    private static void maakCentrePaneLeeg() {
        mainController.getBorderPane().setCenter(null);
    }

    public static ArrayList<Lid> getLeden() {
        return leden;
    }



}
