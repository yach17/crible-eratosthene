package fr.plopyc.crible_eratosthene;

import java.io.OutputStream;

public class CalculateurNombrePremiersFactory {
    private CalculateurNombrePremiersFactory() {
    }

    public static CalculateurNombrePremiers getCalculateur(boolean bool, Long limite, OutputStream out){
        return bool ? new BooleanCribleEratosthene(limite, out)
                : new CribleEratosthene(limite, out);
    }
}
