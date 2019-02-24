import controllers.MainController;
import views.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Startpunt van de applicatie
 *
 * @author HvA HBO-ICT
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MainController mainController = new MainController();
        MainView mainView = new MainView(mainController);
        mainView.show();
    }

    public static void main(String[] args) { launch(args); }

}


// TODO
// Zorgen dat de rating (her)berekent wordt naargelang de uitslag van het spel, en de rating van de
// spelers voordat het potje begon. //
// TODO
// maken van de toString()'s indien relevant voor de java FX applicatie. //



