
package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import models.Competitie;
import utils.StateManager;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompetitieController {


    private DatePicker beginDatum;
    private DatePicker eindDatum;
    private TextField competitieNaam;
    private ListView<Competitie> competitieListView;
    private Button maakButton;
    private Button verwijderButton;
    private Competitie huidigeCompetitie;
    private ObservableList<Competitie> competities;
    private Button afsluitenTempButton;



    public void initialize() {

        competities = FXCollections.observableArrayList(StateManager.getCompetities());
        competitieListView.setItems(competities);

        maakButton.setOnAction(event -> {
            voegCompetitieToe();

        });
        competitieListView.setItems(competities);

        maakButton.setOnAction(event -> {
            voegCompetitieToe();
        });


        competitieListView.getSelectionModel().selectedIndexProperty().addListener(event -> {
            huidigeCompetitie = competitieListView.getSelectionModel().getSelectedItem();
        });

        verwijderButton.setOnAction(event -> {
            competities.remove(huidigeCompetitie);
        });

        afsluitenTempButton.setOnAction(event -> {
            System.out.println("Inhoud:  " + competities.toArray().length);
            StateManager.switchView(StateManager.GEEN_VIEW);
            System.out.println("Inhoud: na  " + competities.toArray().length);
        });
    }
        private boolean isErAlIetsIngevuld() {

            boolean waarheidWaarde;
            if (!(competitieNaam.getText().trim().isEmpty() && beginDatum.getValue() == null  && eindDatum.getValue() == null )) {
                waarheidWaarde = true;
            } else {
                waarheidWaarde = false;
                maakVeldenLeeg();
            }

            return waarheidWaarde;
        }



        private void voegCompetitieToe() {
            if (isErAlIetsIngevuld()) {
                LocalDate begindatum = beginDatum.getValue();
                LocalDate einddatum = eindDatum.getValue();
                String competitienaam = competitieNaam.getText();

                if (eindDatum.getValue() != null && beginDatum.getValue() != null && !competitieNaam.getText().trim().isEmpty())
                {
                    competities.add(new Competitie(competitienaam, begindatum, einddatum));
                    StateManager.laatLabelZien("Competitie is aangemaakt");
                    maakVeldenLeeg();
                }
               else {
                    StateManager.laatLabelZien("Niet alle velden zijn ingevoerd.");
                    markeerNietIngevuldeVelden(competitieNaam, beginDatum, eindDatum );
                }

            }

        }




        private void maakVeldenLeeg() {
            competitieNaam.clear();
            eindDatum.setValue(null);
            beginDatum.setValue(null);

            competitieListView.getSelectionModel().select(null);

            competitieNaam.setStyle(null);
            beginDatum.setStyle(null);
            eindDatum.setStyle(null);


        }

        private void markeerNietIngevuldeVelden(TextField competitieNaam, DatePicker beginDatum,
                DatePicker eindDatum){

            ArrayList<Object> objectArray = new ArrayList<>();
            objectArray.add(competitieNaam);
            objectArray.add(beginDatum);
            objectArray.add(eindDatum);

            for (Object object : objectArray) {
                if (object instanceof TextField && ((TextField) object).getText().trim().isEmpty()) {
                    ((TextField) object).setStyle("-fx-text-box-border: #ff0000");


                } else if (object instanceof DatePicker && ((DatePicker) object).getValue() == null) {
                    ((DatePicker) object).setStyle("-fx-border-color: #ff0000");


                } else if (object instanceof TextField && !((TextField) object).getText().trim().isEmpty()) {
                    ((TextField) object).setStyle("-fx-text-box-border: #32CD33");


                } else if (object instanceof DatePicker && ((DatePicker) object).getValue() != null) {
                    ((DatePicker) object).setStyle("-fx-border-color: #32CD33");
                }
            }


        }


    public void setAfsluitenTempButton(Button afsluitenTempButton) {
        this.afsluitenTempButton = afsluitenTempButton;
    }

    public void setMaakButton(Button maakButton){
        this.maakButton = maakButton;

    }

    public void setBeginDatum(DatePicker beginDatum) {
        this.beginDatum = beginDatum;
    }

    public void setEindDatum(DatePicker eindDatum) {
        this.eindDatum = eindDatum;
    }

    public void setCompetitieNaam(TextField competitieNaam) {
        this.competitieNaam = competitieNaam;
    }

    public void setVerwijderButton(Button verwijderButton) {
        this.verwijderButton = verwijderButton;
    }

    public void setcompetitieListView(ListView<Competitie> competitieListView){
        this.competitieListView = competitieListView;
    }
}
