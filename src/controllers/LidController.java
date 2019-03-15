package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Lid;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class LidController {
    private static int keyPress = 11;
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
    private Button afsluitenTempButton;

    public void initialize() {

        leden = FXCollections.observableArrayList(StateManager.getLeden());
        lidListView.setItems(leden);

        maakButton.setOnAction(event -> {
            voegLidToe();

        });


        /**
         * Met deze handler wordt de handmatige verjaardag-datum-invoer in de datepicker geselecteerd. Net zoals dat
         * gebeurt wanneer er via het datepicker menu zelf een datum wordt gekozen.
         */
        geboorteDatum.setOnKeyReleased(event -> {
            System.out.println(keyPress);
            keyPress--;

            //TODO: ZORGEN DAT INPUT GELEZEN WORDT EN BIJ JUIST FORMAAT DE ENTER-TOETS WORDT INGEDRUKT

            if (keyPress == 0) {

                long delay = 0;
                Timer timer = new Timer();
                timer.schedule(enterKey(), delay);
                keyPress = 10;
                keyPress += 2;


            }

        });


        lidListView.getSelectionModel().selectedIndexProperty().addListener(event -> {
            huidigLid = lidListView.getSelectionModel().getSelectedItem();
        });

        verwijderButton.setOnAction(event -> {

            leden.remove(huidigLid);

        });

        afsluitenTempButton.setOnAction(event -> {
            System.out.println("Inhoud: " + leden.toArray().length);
            StateManager.switchView(StateManager.GEEN_VIEW);
            System.out.println("Inhoud: na  " + leden.toArray().length);

        });


    }

    private TimerTask enterKey() {
//        Field[] fields = java.awt.event.KeyEvent.class.getFields();
//        for (Field f : fields) {
//            if (Modifier.isStatic(f.getModifiers())) {
//                System.out.println(f);
//            }
//        }
        TimerTask timertask;
        timertask = new TimerTask() {
            @SuppressWarnings("deprecation")
            @Override
            public void run() {

                System.out.println("Indicatie");
                try {
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);

                } catch (AWTException e) {
                    e.printStackTrace();
                }

            }

        };

        return timertask;

    }

    private boolean isErAlIetsIngevuld() {

        boolean waarheidWaarde;
        if (!(voornaam.getText().trim().isEmpty() && tussenVoegsel.getText().trim().isEmpty() && achternaam.getText().
                trim().isEmpty() && geboorteDatum.getValue() == null && rating.getText().trim().isEmpty())) {
            waarheidWaarde = true;
        } else {
            waarheidWaarde = false;
            maakVeldenLeeg();
        }

        return waarheidWaarde;
    }

    private void voegLidToe() {
        if (isErAlIetsIngevuld()) {
            boolean exceptionVoorRating = false;
            String volledigenaam = getVolledigeNaam(voornaam, tussenVoegsel, achternaam);
            LocalDate geboortedat = geboorteDatum.getValue();
            int ratingg = 0;
            try {
                ratingg = Integer.parseInt(rating.getText());
            } catch (Exception exceptie) {
                // StateManager.geefAlertScherm(exceptie.toString());
                System.out.println(exceptie.toString());
                exceptionVoorRating = true;
            }


            if (!exceptionVoorRating && geboorteDatum.getValue() != null && !voornaam.getText().trim().isEmpty() &&
                    !achternaam.getText().trim().isEmpty()) {
                leden.add(new Lid(volledigenaam, geboortedat, ratingg));
                StateManager.laatLabelZien("Lid met bekende rating aangemaakt");
                maakVeldenLeeg();

            } else if (geboorteDatum.getValue() != null && !voornaam.getText().trim().isEmpty() &&
                    !achternaam.getText().trim().isEmpty()) {
                leden.add(new Lid(volledigenaam, geboortedat));
                StateManager.laatLabelZien("Lid zonder bekende rating aangemaakt");
                maakVeldenLeeg();

            } else {
                StateManager.laatLabelZien("Niet alle velden zijn ingevoerd.");
                markeerNietIngevuldeVelden(voornaam, achternaam, geboorteDatum);
            }


        }




    }

    private void markeerNietIngevuldeVelden(TextField voornaam, TextField achternaam,
                                            DatePicker geboorteDatum) {
        ArrayList<Object> objectenArray = new ArrayList<>();
        objectenArray.add(voornaam);
        objectenArray.add(achternaam);
        objectenArray.add(geboorteDatum);

        for (Object object : objectenArray) {
            if (object instanceof TextField && ((TextField) object).getText().trim().isEmpty()) {
                ((TextField) object).setStyle("-fx-text-box-border: #ff0000");


            } else if (object instanceof DatePicker && ((DatePicker) object).getValue() == null) {
                ((DatePicker) object).setStyle("-fx-border-color: #ff0000");


            } else if (object instanceof TextField && !((TextField) object).getText().trim().isEmpty()) {
                ((TextField) object).setStyle("-fx-text-box-border: #32CD32");


            } else if (object instanceof DatePicker && ((DatePicker) object).getValue() != null) {
                ((DatePicker) object).setStyle("-fx-border-color: #32CD32");
            }
        }

        tussenVoegsel.setStyle("-fx-text-box-border: #32CD32");
        rating.setStyle("-fx-text-box-border: #32CD32");


    }

    private void maakVeldenLeeg() {
        voornaam.clear();
        tussenVoegsel.clear();
        achternaam.clear();
        geboorteDatum.setValue(null);
        rating.clear();
        lidListView.getSelectionModel().select(null);

        voornaam.setStyle(null);
        tussenVoegsel.setStyle(null);
        achternaam.setStyle(null);
        geboorteDatum.setStyle(null);
        rating.setStyle(null);

    }


    private String getVolledigeNaam(TextField voornaam, TextField tussenVoegsel, TextField achternaam) {
        StringBuilder volledigeNaam = new StringBuilder();
        volledigeNaam.append(voornaam.getText().trim());
        volledigeNaam.append(" ");

        if (!tussenVoegsel.getText().trim().isEmpty()) {
            volledigeNaam.append(tussenVoegsel.getText().trim());
            volledigeNaam.append(" ");
        }
        volledigeNaam.append(achternaam.getText().trim());

        return volledigeNaam.toString();

    }


    public void setMaakButton(Button maakButton) {
        this.maakButton = maakButton;

    }


    public void setVerwijderButton(Button verwijderButton) {
        this.verwijderButton = verwijderButton;
    }

    public void setVoornaamTF(TextField voornaam) {
        this.voornaam = voornaam;

    }

    public void setAchternaamTF(TextField achternaam) {
        this.achternaam = achternaam;
    }

    public void setTussenVoegselTF(TextField tussenVoegsel) {
        this.tussenVoegsel = tussenVoegsel;
    }

    public void setGeboorteDatumDP(DatePicker geboorteDatum) {
        this.geboorteDatum = geboorteDatum;

    }

    public void setAfsluitButton(Button afsluitButton){{
        this.afsluitenTempButton = afsluitButton;
    }

    }

    public void setRatingTF(TextField rating) {
        this.rating = rating;
    }


    public void setLidListView(ListView<Lid> lidListView) {
        this.lidListView = lidListView;
    }
}
