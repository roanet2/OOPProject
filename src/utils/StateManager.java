package utils;

import Repo.LedenObjectRepository;
import Repo.PartijObjectRepository;
import Repo.Repository;
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

    public static final int GEEN_VIEW = 0;
    public static final int LID_VIEW = 1;
    public static final int PARTIJ_VIEW = 2;
    public  static final  int  COMPETITIE_VIEW = 3;
    public  static final  int  RONDE_VIEW = 4;


    private static LidController lidController;
    private static LidView lidView;
    private static PartijController partijController;
    private static PartijView partijView;
    private static CompetitieController competitieController;
    private static CompetitieView competitieView;
    private static MainController mainController;
    private static Repository<Lid> leden;
    private static Repository<Partij> partijen;
    private static ArrayList<Competitie> competities;


    public static void initialize(MainController mainController) {
        StateManager.mainController = mainController;
        partijen = new PartijObjectRepository();
        leden = new LedenObjectRepository();
        // leden = new ArrayList<>();
        competities = new ArrayList<>();


//        competitieController = new CompetitieController();
//        competitieView = new CompetitieView(competitieController);

    }

    public static void switchView(int view) {
        switch (view) {
            case GEEN_VIEW:
                laatLabelZien("");
                maakCentrePaneLeeg();
                break;

            case LID_VIEW:
                lidController = new LidController();
                lidView = new LidView(lidController);
                laatLabelZien("Aanmaken leden");
                lidView = new LidView(lidController);
                setCentrePane(lidView.getRoot());
                break;

            case PARTIJ_VIEW:
                partijController = new PartijController();
                partijView = new PartijView(partijController);
                laatLabelZien("Aanmaken partijen");
                partijView = new PartijView(partijController);
                setCentrePane(partijView.getRoot());
                break;

            case COMPETITIE_VIEW:
                laatLabelZien("Aanmaken partijen");
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

    private static void setCentrePane(Pane pane) {
        mainController.getBorderPane().setCenter(pane);


    }

    private static void maakCentrePaneLeeg() {
        mainController.getBorderPane().setCenter(null);
    }

    public static Repository<Lid> getLedenRepository() {
        System.out.println("Leden l: " + leden.getAll().toArray().length);
        return leden;
    }

    public static Repository<Partij> getPartijenRepository() {
        System.out.println("Partij l: " + partijen.getAll().toArray().length);
        return partijen;
    }

    public  static ArrayList<Competitie> getCompetities(){ return  competities;}

}