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

    public static void main(String[] args) {
        launch(args);
    }
}
