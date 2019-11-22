package fr.plopyc.crible_eratosthene;

import java.io.PrintStream;
import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {

        Parametres parametres = new Parametres(args, System.out);
        PrintStream out = parametres.getOut();
        out.println(String.format("Recherche des nombres premiers jusqu'à %d avec le crible d'Ératosthène", parametres.getLimite()));

        // Début du chronomètre
        Instant start = Instant.now();

        CalculateurNombrePremiers crible = CalculateurNombrePremiersFactory.getCalculateur(parametres.isCribleBooleen(), parametres.getLimite(), parametres.getOut());
        crible.calculerNombresPremiers();

        Instant end = Instant.now();

        Duration dureeExecution = Duration.between(start, end);
        out.println(String.format("Durée du calcul : %s", dureeExecution.toString()));
    }

    static class Parametres {
        private boolean cribleBooleen;
        private long limite;
        private PrintStream out;

        Parametres(String[] args, PrintStream out) {
            this.out = out;
            if (args.length != 2) throw new IllegalArgumentException(CribleEratosthene.MESSAGE_ERREUR_ARGUMENT_INVALIDE);
                cribleBooleen = args[0].startsWith("bool") || args[0].startsWith("Bool") || args[0].equalsIgnoreCase("b");
            try {
                limite = Long.parseLong(args[1]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(CribleEratosthene.MESSAGE_ERREUR_ARGUMENT_INVALIDE + " : " + args[1] + " n'est pas un nombre.");
            }

        }

        boolean isCribleBooleen() {
            return cribleBooleen;
        }

        long getLimite() {
            return limite;
        }

        PrintStream getOut() {
            return out;
        }
    }
}
