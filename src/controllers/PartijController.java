package controllers;

import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import models.Lid;
import models.Partij;
import utils.StateManager;
import views.uitslagenSetterView;

import javax.swing.event.ChangeListener;


public class PartijController {

    //   private ListProperty<Partij> partijListProperty;
    private static ObservableList<Partij> partijenObservable;
    private ObservableList<Lid> leden;
    private Lid spelerZwart = null;
    private Lid spelerWit = null;
    private Button verwijderPartijButton;
    private Button voegPartijToeButton;
    private ListView<Partij> partijListView;
    private ListView<Lid> ledenListView;
    private Partij huidigePartij = null;
    private uitslagenSetterView uitslagenSetterView;

    // private ListProperty<Lid> geselecteerdeLeden;

    static ObservableList<Partij> getPartijenObservable() {
        return partijenObservable;
    }

    public void initialize() {
        //geselecteerdeLeden = new SimpleListProperty<>();

//        partijListProperty = new SimpleListProperty<>();
//        partijListProperty.set(FXCollections.observableArrayList(StateManager.getPartijen()));
//        partijListView.itemsProperty().bind(partijListProperty);

        partijenObservable = FXCollections.observableArrayList(StateManager.getPartijenRepository().getAll());
        partijListView.setItems(partijenObservable);

        partijenObservable.addListener((ListChangeListener<Partij>) change -> {
            while (change.next()) {
                if (change.wasRemoved()) {
                    StateManager.getPartijenRepository().remove(change.getRemoved().get(0));
                }
                if (change.wasAdded()) {
                    StateManager.getPartijenRepository().add(change.getAddedSubList().get(0));
                }


            }

        });



        huidigePartij = partijListView.getSelectionModel().getSelectedItem();


        partijListView.setOnMouseClicked(event -> {
            System.out.println(event.getClickCount());
            if (event.getClickCount() == 2) {
                System.out.println("Clickcount bereikt");
                uitslagenSetterController controller = new uitslagenSetterController();
                uitslagenSetterView = new uitslagenSetterView(controller);
                uitslagenSetterView.show();
            }
            System.out.println("Mouse Clicked");
        });


        leden = FXCollections.observableArrayList(StateManager.getLedenRepository().getAll());
        FXCollections.sort(leden);
        ledenListView.setItems(leden);


        ledenListView.getSelectionModel().selectedIndexProperty().addListener(event -> {

            if (spelerZwart == null) {
                spelerZwart = ledenListView.getSelectionModel().getSelectedItem();

            } else if (!(ledenListView.getSelectionModel().getSelectedItem().equals(spelerZwart)) && spelerZwart != null && spelerWit == null) {
                spelerWit = ledenListView.getSelectionModel().getSelectedItem();

            }

            if (spelerZwart != null) {
                System.out.println("z: " + spelerZwart.toString());
            }
            if (spelerWit != null && spelerZwart != null) {
                System.out.println("SpelerZwart: " + spelerZwart.getNaam());
                System.out.println("SpelerWit: " + spelerWit.getNaam());
                System.out.println("\n");
                System.out.println(spelerZwart.toString());
                System.out.println(spelerWit.toString());
            }


        });

        voegPartijToeButton.setOnAction(event -> {
            voegPartijToe();
            // refreshPartijListPropery();
            maakVeldenLeeg();
        });

        verwijderPartijButton.setOnAction(event -> {
            partijenObservable.remove(partijListView.getSelectionModel().getSelectedItem());
//            FXCollections.observableArrayList(StateManager.getPartijen().remove(partijListView.getSelectionModel()
//                    .getSelectedItem()));
            //  refreshPartijListPropery();
        });


    }

    private void maakVeldenLeeg() {
        spelerZwart = null;
        spelerWit = null;
        ledenListView.getSelectionModel().select(null);
    }

    private void voegPartijToe() {
        if (spelerZwart != null && spelerWit != null) {
            partijenObservable.add(new Partij(spelerZwart, spelerWit));
            // FXCollections.observableArrayList(StateManager.getPartijen().add(new Partij(spelerZwart, spelerWit)));
            StateManager.laatLabelZien("Partij aangemaakt en toegevoegd");
        }
    }

//    private void refreshPartijListPropery() {
//        partijListProperty.set(FXCollections.observableArrayList(StateManager.getPartijen()));
//
//    }


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
        this.voegPartijToeButton = voegSpelerToeButton;
    }

}
