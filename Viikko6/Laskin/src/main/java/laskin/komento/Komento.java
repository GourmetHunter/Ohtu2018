/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin.komento;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import laskin.Sovelluslogiikka;

/**
 *
 * @author Olli K. Kärki
 */
public abstract class Komento {

    protected TextField tuloskentta;
    protected TextField syotekentta;
    protected Sovelluslogiikka sovellus;
    protected Button nollaa;

    public Komento(Sovelluslogiikka sovellus, TextField tuloskentta, TextField syotekentta, Button nollaa) {
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
    }

    public abstract void suorita();

    public abstract void peru();

    protected int getArvo(TextField kentta) {
        try {
            return Integer.parseInt(kentta.getText());
        } catch (Exception e) {
            System.out.println("Error in getArvo() - " + kentta.getText() + " is not an Integer");
            return 0;
        }
    }

    protected void esitaTulos() {
        int tulos = sovellus.tulos();
        syotekentta.setText("");
        tuloskentta.setText("" + tulos);
        nollaa.disableProperty().set(tulos == 0);
    }
}
