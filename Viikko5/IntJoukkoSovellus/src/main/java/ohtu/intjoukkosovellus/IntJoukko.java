package ohtu.intjoukkosovellus;

import java.security.InvalidParameterException;

public class IntJoukko {

    public final static int OLETUSKAPASITEETTI = 5;
    public final static int OLETUSKASVATUS = 5;

    private int taulukonKasvattaja;
    private int[] lukuJono;
    private int alikoidenLukuMaara;

    public IntJoukko() {
        lukuJono = new int[OLETUSKAPASITEETTI];
        this.taulukonKasvattaja = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        this();
        if (kapasiteetti < 0) {
            throw new InvalidParameterException("Parametri (1) kapasiteetti oli " + kapasiteetti + " pitäisi olla suurempi tai yhtäsuuri kuin 0.");
        } else {
            lukuJono = new int[kapasiteetti];
        }
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        this(kapasiteetti);
        if (kasvatuskoko <= 0) {
            throw new InvalidParameterException("Parametri (2) kasvatuskoko oli " + kasvatuskoko + " pitäisi olla suurempi kuin 0.");
        } else {
            this.taulukonKasvattaja = kasvatuskoko;
        }

    }

    public boolean lisaa(int luku) {
        if (alikoidenLukuMaara == 0 || !kuuluu(luku)) {
            lukuJono[alikoidenLukuMaara] = luku;
            alikoidenLukuMaara++;
            kasvataArrayta();
            return true;
        }
        return false;
    }

    private void kasvataArrayta() {
        if (alikoidenLukuMaara == lukuJono.length) {
            int[] taulukkoOld = lukuJono;
            lukuJono = new int[alikoidenLukuMaara + taulukonKasvattaja];
            kopioiTaulukko(taulukkoOld, lukuJono);
        }
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alikoidenLukuMaara; i++) {
            if (luku == lukuJono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int index = get(luku);
        if (index != -1) {
            cleanArray(index);
            return true;
        }
        return false;
    }

    private int get(int luku) {
        int r = -1;
        for (int i = 0; i < alikoidenLukuMaara; i++) {
            if (luku == lukuJono[i]) {
                r = i;
                break;
            }
        }
        return r;
    }

    private void cleanArray(int from) {
        for (int i = from; i < alikoidenLukuMaara - 1; i++) {
            int help = lukuJono[i];
            lukuJono[i] = lukuJono[i + 1];
            lukuJono[i + 1] = help;
        }
        alikoidenLukuMaara--;
    }

    public int mahtuvuus() {
        return alikoidenLukuMaara;
    }

    @Override
    public String toString() {
        String helpString = "";
        if (alikoidenLukuMaara > 0) {
            for (int i = 0; i < alikoidenLukuMaara - 1; i++) {
                helpString += lukuJono[i] + ", ";
            }
            helpString += lukuJono[alikoidenLukuMaara - 1];
        }
        return "{" + helpString + "}";
    }

    public int[] toIntArray() {
        int[] taulu = new int[alikoidenLukuMaara];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukuJono[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko newTaulu = new IntJoukko();
        lisaaTaulukko(newTaulu, a.toIntArray());
        lisaaTaulukko(newTaulu, b.toIntArray());
        return newTaulu;
    }

    private static void lisaaTaulukko(IntJoukko joukko, int[] taulukko) {
        for (int i = 0; i < taulukko.length; i++) {
            joukko.lisaa(taulukko[i]);
        }
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        return yhdistaJoukkoJosKuuluu(a, b, true);
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        return yhdistaJoukkoJosKuuluu(a, b, false);
    }

    private static IntJoukko yhdistaJoukkoJosKuuluu(IntJoukko a, IntJoukko b, boolean condition) {
        IntJoukko newTaulu = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            if (b.kuuluu(aTaulu[i]) == condition) {
                newTaulu.lisaa(aTaulu[i]);
            }
        }
        return newTaulu;
    }

}
