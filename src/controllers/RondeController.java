package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import models.Ronde;
import utils.StateManager;

import java.time.LocalDate;
import java.util.ArrayList;

public class RondeController {
    private Button maakButton;
    private ListView<Ronde> rondeListView;
    private Button afsluitenTempButton;
    private Button verwijderButton;
    private DatePicker speelDatum;
    private Ronde huidigRonde;





    private ObservableList<Ronde> rondes;



    public RondeController(){
        rondes = FXCollections.observableArrayList();
    }

    public void initialize(){
        rondeListView.setItems(rondes);

        maakButton.setOnAction(event -> {
            voegRondeToe();
        });

        rondeListView.getSelectionModel().selectedIndexProperty().addListener(event -> {
            huidigRonde = rondeListView.getSelectionModel().getSelectedItem();
        });

        verwijderButton.setOnAction(event -> {

            rondes.remove(huidigRonde);
        });

        afsluitenTempButton.setOnAction(event -> {
            System.out.println("Inhoud: " + rondes.toArray().length);
            StateManager.switchView(StateManager.GEEN_VIEW);
            System.out.println("Inhoud:   " + rondes.toArray().length);

        });



    }

    private boolean isErAlIetsIngevuld() {

        boolean waarheidWaarde;
        if (!(speelDatum.getValue() == null )) {
            waarheidWaarde = true;
        } else {
            waarheidWaarde = false;
            maakVeldenLeeg();
        }

        return waarheidWaarde;
    }

    private void maakVeldenLeeg() {
        speelDatum.setValue(null);
        rondeListView.getSelectionModel().select(null);


    }

    private void voegRondeToe() {
        if (isErAlIetsIngevuld()) {
            LocalDate speeldatum = speelDatum.getValue();
            if (speelDatum.getValue() != null) {
                rondes.add(new Ronde(speeldatum));
                StateManager.laatLabelZien("Ronde is aangemaakt");
                maakVeldenLeeg();
            } else {
                StateManager.laatLabelZien("Niet alle velden zijn ingevoerd.");
                markeerNietIngevuldeVelden(speelDatum);
            }
        }
    }

        private void markeerNietIngevuldeVelden (
                DatePicker speelDatum){
            ArrayList<Object> objectenArray = new ArrayList<>();
            objectenArray.add(speelDatum);

            for (Object object : objectenArray) {

                if (object instanceof DatePicker && ((DatePicker) object).getValue() == null) {
                    ((DatePicker) object).setStyle("-fx-border-color: #ff0000");
                }
            }
        }

    public void setMaakButton(Button maakButton){
        this.maakButton = maakButton;

    }

    public void setRondes(ObservableList<Ronde> rondes) {
        this.rondes = rondes;
    }

    public void setRondeListView(ListView<Ronde> rondeListView){
        this.rondeListView = rondeListView;
    }

    public void setAfsluitenTempButton(Button afsluitenTempButton) {
        this.afsluitenTempButton = afsluitenTempButton;
    }


    public void setSpeelDatum(DatePicker speelDatum) {
        this.speelDatum = speelDatum;
    }

    public void setVerwijderButton(Button verwijderButton) {
        this.verwijderButton = verwijderButton;
    }
}