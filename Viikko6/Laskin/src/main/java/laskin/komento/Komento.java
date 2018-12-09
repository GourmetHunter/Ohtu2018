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
    
    private Button nollaa;
    private Button undo;
    
    private String oldTulos;
    private boolean oldNollaa;
    
    public Komento(Sovelluslogiikka sovellus, TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo) {
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.oldTulos = "";
        this.oldNollaa = false;
    }
    
    public abstract void suorita();
    
    public void peru() {
        tuloskentta.setText(oldTulos);
        syotekentta.setText("");
        nollaa.disableProperty().set(oldNollaa);
        undo.disableProperty().set(true);
    }
    
    protected int getArvo(TextField kentta) {
        try {
            return Integer.parseInt(kentta.getText());
        } catch (Exception e) {
            System.out.println("Error in getArvo() - " + kentta.getText() + " is not an Integer");
            return 0;
        }
    }
    
    protected void esitaTulos() {
        this.tallennaVanha();
        int tulos = sovellus.tulos();
        syotekentta.setText("");
        tuloskentta.setText("" + tulos);
        nollaa.disableProperty().set(tulos == 0);
        undo.disableProperty().set(false);
    }
    
    private void tallennaVanha() {
        oldTulos = tuloskentta.getText();
        oldNollaa = nollaa.disableProperty().get();
    }
    
}
