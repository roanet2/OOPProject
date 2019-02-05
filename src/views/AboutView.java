package views;

import controllers.AboutController;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * UI van het "over"-scherm
 *
 * @author HvA HBO-ICT
 */
public class AboutView {

    private static final String TITEL = "Over Competitie";
    private static final String UITLEG = "Met deze applicatie kun je een competitie beheren.\n";

    private static final int WIDTH = 450;
    private static final int HEIGHT = 300;

    private TextArea melding;

    private AboutController controller;
    private Stage stage;

    /**
     * Maakt een instantie van de UI van het hoofdscherm
     *
     * @param controller Een instantie van de controller die nodig is om het hoofdscherm aan te sturen
     */
    public AboutView(AboutController controller) {
        this.controller = controller;

        stage = new Stage();
        stage.setTitle(TITEL);

        Parent root = createRoot();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(true);

        setupController();
    }

    private Parent createRoot() {
        melding = new TextArea();
        melding.setStyle("-fx-background-color: bisque");
        melding.setMaxWidth(Double.MAX_VALUE);
        melding.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        melding.setEditable(false);
        melding.setText(UITLEG);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(melding);

        return borderPane;
    }

    private void setupController() {
        controller.setMelding(melding);

        controller.initialize();
    }

    /**
     * Toon dit scherm in het midden van de hoofdmonitor
     */
    public void show() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        stage.setX((primaryScreenBounds.getWidth() - WIDTH) / 2f);
        stage.setY((primaryScreenBounds.getHeight() - HEIGHT) / 2f);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        stage.show();
    }
}
