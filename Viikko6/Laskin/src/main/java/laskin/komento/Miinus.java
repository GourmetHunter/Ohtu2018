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
public class Miinus extends Komento {

    public Miinus(Sovelluslogiikka sovellus, TextField tuloskentta, TextField syotekentta, Button nollaa) {
        super(sovellus, tuloskentta, syotekentta, nollaa);
    }

    @Override
    public void suorita() {
        sovellus.miinus(getArvo(syotekentta));
        esitaTulos();
        
    }

    @Override
    public void peru() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
