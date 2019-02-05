package controllers;

import javafx.scene.control.TextArea;

/**
 * Logica van het "over"-scherm
 *
 * @author HvA HBO-ICT
 */
public class AboutController {

    private TextArea melding;

    /**
     * Wanneer deze methode wordt aangeroepen, is het veilig om bijv. "melding" aan te passen.
     */
    public void initialize() {
        melding.setText("Deze applicatie is gemaakt door <studentnaam>, <studentnummer>, <klas>!");
    }

    public void setMelding(TextArea melding) {
        this.melding = melding;
    }
}
