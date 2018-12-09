package laskin;

import java.util.HashMap;
import java.util.Map;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import laskin.komento.Komento;
import laskin.komento.Miinus;
import laskin.komento.Nollaa;
import laskin.komento.Plus;

public class Tapahtumankuuntelija implements EventHandler {

    private TextField tuloskentta;
    private TextField syotekentta;
    private Button plus;
    private Button miinus;
    private Button nollaa;
    private Button undo;
    private Sovelluslogiikka sovellus;

    private Map<Button, Komento> komennot;
    private Komento edellinen;

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.plus = plus;
        this.miinus = miinus;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        this.komennot = new HashMap<>();
        this.komennot.put(plus, new Plus(sovellus, tuloskentta, syotekentta, nollaa));
        this.komennot.put(miinus, new Miinus(sovellus, tuloskentta, syotekentta, nollaa));
        this.komennot.put(nollaa, new Nollaa(sovellus, tuloskentta, syotekentta, nollaa));
    }

    @Override
    public void handle(Event event) {
        if (event.getTarget() != undo) {
            Komento komento = komennot.get((Button) event.getTarget());
            komento.suorita();
            edellinen = komento;
        } else if (edellinen != null) {
            edellinen.peru();
            edellinen = null;
        }
    }

}
