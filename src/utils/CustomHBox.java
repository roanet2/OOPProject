package utils;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

    public class CustomHBox{

    private static final int MIN_WIDTH = 80;

    public static HBox maakHBox(String labelnaam, Node node) {
        HBox nieuweHBox = new HBox();
        Label label = new Label(labelnaam);

        label.setMinWidth(MIN_WIDTH);
        nieuweHBox.setSpacing((int) nieuweHBox.getWidth() - node.getScaleX() - label.getWidth() + 20);
        nieuweHBox.setPadding(new Insets(15, 15, 15, 15));
        nieuweHBox.getChildren().addAll(label, node);

        return nieuweHBox;

    }

    public static HBox maakHBox(Node node1, Node node2) {
        HBox nieuweHBox = new HBox();
        nieuweHBox.setSpacing(20);
        nieuweHBox.setPadding(new Insets(15, 15, 15, 15));
        nieuweHBox.getChildren().addAll(node1, node2);

        return nieuweHBox;

    }

}
