package views;

import controllers.uitslagenSetterController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Partij;
import utils.CustomHBox;


public class uitslagenSetterView {

    public static final int WIDTH = 780;
    public static final int HEIGHT = 550;
    private static final String TITEL = "Uitslag registratie";

    private Stage stage;
    private CheckBox setWinstSpelerZwartCheckBox;
    private CheckBox setWinstSpelerWitCheckBox;
    private Text spelerZwartText;
    private Text spelerWitText;

    private uitslagenSetterController controller;
    private ListView<Partij> partijListViewExtension;
    private Button setRemiseButton;
    private Button setWinstButton;


    public uitslagenSetterView(uitslagenSetterController controller) {
        this.controller = controller;

        stage = new Stage();
        stage.setTitle(TITEL);
        Pane root = createRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(true);

        setUpController();

    }

    private Pane createRoot() {
        GridPane grid = new GridPane();
        partijListViewExtension = new ListView<>();
        setWinstSpelerZwartCheckBox = new CheckBox();
        setWinstSpelerWitCheckBox = new CheckBox();
        spelerZwartText = new Text();
        spelerWitText = new Text();
        spelerZwartText.setStyle("-fx-progress-color: #ff0000");
        spelerWitText.setStyle("-fx-color: grey");
        spelerZwartText.setText("speler op zwart ");
        spelerWitText.setText("speler op wit");


        setRemiseButton = new Button("Remise");
        setWinstButton = new Button("Winst");

        grid.setHgap(5);
        grid.add(setWinstSpelerZwartCheckBox, 1, 1);
        grid.add(spelerZwartText, 2, 1);
        grid.setVgap(20);
        grid.add(setWinstSpelerWitCheckBox, 1, 2);
        grid.add(spelerWitText, 2, 2);

        HBox buttonBox = CustomHBox.maakHBox(setWinstButton, setRemiseButton);
        grid.add(buttonBox, 2, 3);
        grid.setGridLinesVisible(false);


        BorderPane pane = new BorderPane();

        FlowPane flowPane = new FlowPane();
        flowPane.getChildren().add(partijListViewExtension);
        flowPane.getChildren().add(grid);

        flowPane.setPadding(new Insets(50, 25, 25, 25));
        flowPane.setHgap(20);
        flowPane.setAlignment(Pos.TOP_CENTER);

        pane.setCenter(flowPane);

        return pane;
    }

    private void setUpController() {
        controller.setPartijListViewExtension(partijListViewExtension);
        controller.setSetWinstSpelerWitCheckBox(setWinstSpelerWitCheckBox);
        controller.setSetWinstSpelerZwartCheckBox(setWinstSpelerZwartCheckBox);
        controller.setSpelerWitText(spelerWitText);
        controller.setSpelerZwartText(spelerZwartText);
        controller.setSetWinstButton(setWinstButton);
        controller.setSetRemiseButton(setRemiseButton);

        controller.initialize();

    }

    public void show() {
        MainView.show(stage, WIDTH, HEIGHT);
    }


}