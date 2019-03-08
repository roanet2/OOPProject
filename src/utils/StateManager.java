package utils;

import controllers.LidController;
import controllers.MainController;
import javafx.scene.layout.Pane;
import models.Lid;
import views.LidView;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;

public class StateManager {

    public static final int GEEN_VIEW = 0;
    public static final int LID_VIEW = 1;

    private static MainController mainController;
    private static ArrayList<Lid> leden;


    public static void initialize(MainController mainController){
        StateManager.mainController = mainController;
        leden = new ArrayList<>();

    }

    public static void switchView(int view){
        switch (view){
            case GEEN_VIEW:
                laatLabelZien("");
                maakCentrePaneLeeg();

             break;
            case LID_VIEW:
                LidController lidController = new LidController();
                LidView lidView = new LidView(lidController);
                laatLabelZien("Aanmaken leden");
                setCentrePane(lidView.getRoot());

        }
    }



    public static void laatLabelZien(String labelnaam){
        mainController.laatLabelZien(labelnaam);

    }

    public static void setCentrePane(Pane pane){
        mainController.getBorderPane().setCenter(pane);
    }

    private static void maakCentrePaneLeeg() {
       mainController.getBorderPane().setCenter(null);
    }




}
