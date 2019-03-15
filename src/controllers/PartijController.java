package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import models.Lid;
import models.Partij;

public class PartijController {

    private ObservableList<Partij> partijen;
    private ObservableList<Lid> leden;
    private Lid spelerZwart;
    private Lid spelerWit;
    private Button verwijderPartijButton;
    private Button voegSpelerToeButton;
    private ListView<Partij> partijListView;
    private ListView<Lid> ledenListView;


    public void initialize() {
        leden = FXCollections.observableArrayList(StateManager.getLeden());
        System.out.println(leden.toString());
        ledenListView.setItems(leden);
        partijen = FXCollections.observableArrayList(StateManager.getPartijen());
        partijListView.setItems(partijen);

//        ledenListView.getSelectionModel().selectedIndexProperty().addListener(event ->{
//            spelerZwart = ledenListView.getSelectionModel().getSelectedItems().get(0);
//            if(spelerZwart != null){
//                spelerWit = ledenListView.getSelectionModel().getSelectedItems().get(1);
//            }
//        });



    }


    public void setPartijListView(ListView<Partij> partijListView) {
        this.partijListView = partijListView;
    }

    public void setLedenListView(ListView<Lid> ledenListView) {
        this.ledenListView = ledenListView;
    }

    public void setVerwijderPartijButton(Button verwijderPartijButton) {
        this.verwijderPartijButton = verwijderPartijButton;

    }

    public void setVoegSpelerToeButton(Button voegSpelerToeButton) {
        this.voegSpelerToeButton = voegSpelerToeButton;
    }

}
