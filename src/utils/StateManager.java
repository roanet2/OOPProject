package utils;

import controllers.*;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import models.Competitie;
import models.Lid;
import models.Partij;
import views.CompetitieView;
import views.LidView;
import views.PartijView;
import views.RondeView;

import java.util.ArrayList;

public class StateManager {

    private static LidController lidController;
    private static LidView lidView;
    public static final int GEEN_VIEW = 0;
    public static final int LID_VIEW = 1;
    public static final int PARTIJ_VIEW = 2;
    public  static final  int  COMPETITIE_VIEW = 3;
    public  static final  int  RONDE_VIEW = 4;

    private static MainController mainController;
    private static ArrayList<Lid> leden;
    private static ArrayList<Partij> partijen;
    private static ArrayList<Competitie> competities;


    public static void initialize(MainController mainController) {
        StateManager.mainController = mainController;
        leden = new ArrayList<>();
        partijen = new ArrayList<>();
        competities = new ArrayList<>();

        lidController = new LidController();
        lidView = new LidView(lidController);

    }

    public static void switchView(int view) {
        switch (view) {
            case GEEN_VIEW:
                laatLabelZien("");
                maakCentrePaneLeeg();
                break;

            case LID_VIEW:
                laatLabelZien("Aanmaken leden");
                setCentrePane(lidView.getRoot());
                break;

            case PARTIJ_VIEW:
                PartijController partijController = new PartijController();
                PartijView partijView = new PartijView(partijController);
                laatLabelZien("Aanmaken partijen");
                setCentrePane(partijView.getRoot());
                break;

            case COMPETITIE_VIEW:
                CompetitieController competitieController = new CompetitieController();
                CompetitieView competitieView = new CompetitieView(competitieController);
                laatLabelZien("Aanmaken Competitie");
                setCentrePane(competitieView.getRoot());
                break;
            case RONDE_VIEW:
               RondeController rondeController = new RondeController();
                RondeView rondeView = new  RondeView(rondeController);
                laatLabelZien("Aanmaken Rondes");
                setCentrePane(rondeView.getRoot());
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
        System.out.println("leden: " + leden.toArray().length);
        return leden;
    }

    public static ArrayList<Partij> getPartijen(){
        return partijen;
    }

    public  static ArrayList<Competitie> getCompetities(){ return  competities;}

}