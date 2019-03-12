package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import models.Lid;
import org.w3c.dom.Text;
import utils.StateManager;

import java.time.LocalDate;

public class LidController {
    private TextField voornaam;
    private TextField tussenVoegsel;
    private TextField achternaam;
    private DatePicker geboorteDatum;
    private TextField rating;
    private ListView<Lid> lidListView;
    private Button maakButton;
    private Button verwijderButton;
    private Lid huidigLid;

    private ObservableList<Lid> leden;


    public void initialize(){
        leden = FXCollections.observableArrayList(StateManager.getLeden());
        lidListView.setItems(leden);

        maakButton.setOnAction(event -> {
            voegLidToe();

        });

    }

    private void voegLidToe(){
        String volledigenaam = getVolledigeNaam(voornaam, tussenVoegsel, achternaam);
        LocalDate geboortedat = geboorteDatum.getValue();
        int ratingg = 0;
        try{
            ratingg = Integer.parseInt(rating.getText());
        }catch(Exception e){
            System.out.println(e.toString());
        }


        if(geboortedat != null && ratingg != 0){
            leden.add(new Lid(volledigenaam, geboortedat, ratingg));
            StateManager.laatLabelZien("Lid met rating aangemaakt");
            maakVeldenLeeg();

        }else{
            leden.add(new Lid(volledigenaam, geboortedat));
            StateManager.laatLabelZien("Lid zonder rating aangemaakt");
            maakVeldenLeeg();
        }

    }

    private void maakVeldenLeeg(){
        voornaam.clear();
        tussenVoegsel.clear();
        achternaam.clear();
        geboorteDatum.setValue(null);
        rating.clear();
        lidListView.getSelectionModel().select(null);


    }


    private String getVolledigeNaam(TextField voornaam, TextField tussenVoegsel, TextField achternaam){
        StringBuilder volledigeNaam = new StringBuilder();
        volledigeNaam.append(voornaam.getText());
        volledigeNaam.append(" ");

        if(tussenVoegsel.getText() != null){
            volledigeNaam.append(tussenVoegsel.getText());
            volledigeNaam.append(" ");
        }
        volledigeNaam.append(achternaam.getText());

        return volledigeNaam.toString();

    }

    public void setMaakButton(Button maakButton){
        this.maakButton = maakButton;

    }

    public void setVerwijderButton(Button verwijderButton){
        this.verwijderButton = verwijderButton;
    }

    public void setVoornaamTF(TextField voornaam){
        this.voornaam = voornaam;

    }

    public void setAchternaamTF(TextField achternaam){
        this.achternaam = achternaam;
    }

    public void setTussenVoegselTF(TextField tussenVoegsel){
        this.tussenVoegsel = tussenVoegsel;
    }

    public void setGeboorteDatumDP(DatePicker geboorteDatum){
        this.geboorteDatum = geboorteDatum;

    }

    public void setRatingTF(TextField rating){
        this.rating = rating;
    }


    public void setLidListView(ListView<Lid> lidListView){
        this.lidListView = lidListView;
    }
}
