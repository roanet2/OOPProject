package controllers;

import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import models.Partij;
import utils.StateManager;


public class uitslagenSetterController {

    private CheckBox setWinstSpelerZwartCheckBox;
    private CheckBox setWinstSpelerWitCheckBox;
    private Text spelerZwartText;
    private Text spelerWitText;
    private Partij huidigePartij = null;
    private ListView<Partij> partijListViewExtension;
    private Button setRemiseButton;
    private Button setWinstButton;

    private ObservableList<Partij> partijen;

    public void initialize() {

        partijen = FXCollections.observableArrayList(PartijController.getPartijenObservable());
        partijListViewExtension.setItems(partijen);

        partijListViewExtension.getSelectionModel().selectedIndexProperty().addListener(event -> {
            huidigePartij = partijListViewExtension.getSelectionModel().getSelectedItem();

            spelerZwartText.setText(huidigePartij.getSpelerZwart().getNaam());
            spelerWitText.setText(huidigePartij.getSpelerWit().getNaam());


        });



        setWinstButton.setOnAction(event -> {
            verwerkWinnaar();
            maakVeldenLeeg();
        });

        setRemiseButton.setOnAction(event -> {
            verwerkRemise();
            maakVeldenLeeg();
        });


    }


    private void maakVeldenLeeg() {
        setWinstSpelerZwartCheckBox.setSelected(false);
        setWinstSpelerWitCheckBox.setSelected(false);
        partijListViewExtension.refresh();
    }


    private void verwerkWinnaar() {
        if (huidigePartij != null && setWinstSpelerWitCheckBox.isSelected() && !setWinstSpelerZwartCheckBox.isSelected()) {
            partijen.get(partijListViewExtension.getSelectionModel().getSelectedIndex()).
                    setWinnaar(huidigePartij.getSpelerWit());
            System.out.println("Speler wit is nu winnaar");
        } else if (huidigePartij != null && setWinstSpelerZwartCheckBox.isSelected() && !setWinstSpelerWitCheckBox.isSelected()) {
            partijen.get(partijListViewExtension.getSelectionModel().getSelectedIndex()).
                    setWinnaar(huidigePartij.getSpelerZwart());
            System.out.println("Speler zwart is nu winnaar");
        } else {
            StateManager.geefAlertScherm("Selecteer eerst een winnaar");
        }
    }

    private void verwerkRemise() {
        if (huidigePartij != null) {
            partijen.get(partijListViewExtension.getSelectionModel().
                    getSelectedIndex()).setRemise();
        }
    }


    public void setSetWinstSpelerZwartCheckBox(CheckBox spelerZwartCheckBox) {
        this.setWinstSpelerZwartCheckBox = spelerZwartCheckBox;
    }

    public void setSetWinstSpelerWitCheckBox(CheckBox spelerWitCheckBox) {
        this.setWinstSpelerWitCheckBox = spelerWitCheckBox;
    }

    public void setSpelerZwartText(Text spelerZwartText) {
        this.spelerZwartText = spelerZwartText;
    }

    public void setSpelerWitText(Text spelerWitText) {
        this.spelerWitText = spelerWitText;
    }

    public void setPartijListViewExtension(ListView<Partij> partijListViewExtension) {
        this.partijListViewExtension = partijListViewExtension;
    }

    public void setSetRemiseButton(Button setRemiseButton) {
        this.setRemiseButton = setRemiseButton;
    }

    public void setSetWinstButton(Button setWinstButton) {
        this.setWinstButton = setWinstButton;
    }

}
