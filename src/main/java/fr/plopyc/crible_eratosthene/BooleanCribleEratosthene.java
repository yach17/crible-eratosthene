package fr.plopyc.crible_eratosthene;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class BooleanCribleEratosthene implements CalculateurNombrePremiers {
    private final int limite;
    private final int limiteMultiples;
    private final PrintStream out;
    private final boolean[] booleensPremiers;

    BooleanCribleEratosthene(Long limite, OutputStream out) {
        this.limite = Math.toIntExact(limite);
        this.limiteMultiples = (int) Math.ceil(Math.sqrt(limite));
        this.booleensPremiers = new boolean[this.limite+2];
        Arrays.fill(this.booleensPremiers, true);
        this.booleensPremiers[0] = this.booleensPremiers[1] = false;
        this.out = new PrintStream(out);
    }

    @Override
    public void calculerNombresPremiers() {
        for (int i = 2; i <= limiteMultiples; i++) {
            if (isPremier(i)) {
                afficher(i);
                retirerLesMultiplesDuNombre(i);
            }
        }
        afficherLesNombresPremiersRestants();
    }

    private void afficherLesNombresPremiersRestants() {
        for (int i = limiteMultiples+1; i <= limite; i++) {
            if(isPremier(i)) {
                afficher(i);
            }
        }
    }

    private void retirerLesMultiplesDuNombre(int i) {
        for (int j = i * 2; j <= limite; j += i) {
            setNonPremier(j);
        }
    }

    private void afficher(int i) {
        out.println(i);
    }

    private void setNonPremier(int j) {
        booleensPremiers[j] = false;
    }

    private boolean isPremier(int i) {
        return booleensPremiers[i];
    }
}
