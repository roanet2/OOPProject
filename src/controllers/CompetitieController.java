
package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import models.Competitie;

public class CompetitieController {


    private Button maakButton;
    private ListView<Competitie> competitieListView;

    private ObservableList<Competitie> rondes;

    public CompetitieController(){
        rondes = FXCollections.observableArrayList();
    }

    public void initialize(){
        competitieListView.setItems(rondes);

        maakButton.setOnAction(event -> {
            System.out.println("In Uitvoering");
        });

    }

    public void setMaakButton(Button maakButton){
        this.maakButton = maakButton;

    }

    public void setcompetitieListView(ListView<Competitie> competitieListView){
        this.competitieListView = competitieListView;
    }
}
