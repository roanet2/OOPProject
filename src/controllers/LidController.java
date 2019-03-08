package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import models.Lid;

public class LidController {
    private Button maakButton;
    private ListView<Lid> lidListView;

    private ObservableList<Lid> leden;

    public LidController(){
        leden = FXCollections.observableArrayList();
    }

    public void initialize(){
        lidListView.setItems(leden);

        maakButton.setOnAction(event -> {
            System.out.println("In Uitvoering");
        });

    }

    public void setMaakButton(Button maakButton){
        this.maakButton = maakButton;

    }

    public void setLidListView(ListView<Lid> lidListView){
        this.lidListView = lidListView;
    }
}
