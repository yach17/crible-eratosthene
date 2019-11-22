package fr.plopyc.crible_eratosthene;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class CribleEratosthene implements CalculateurNombrePremiers {

    public static final String MESSAGE_ERREUR_ARGUMENT_INVALIDE = "Merci de donner le nombre le plus grand à passe au crible en argument";
    private PrintStream out;
    private final long limite;
    private final long limiteMultiples;

    CribleEratosthene(long limite, OutputStream out) {
        this.limite = limite;
        this.limiteMultiples = (long) Math.ceil(Math.sqrt(limite));
        this.out = new PrintStream(out);
    }

    @Override
    public void calculerNombresPremiers() {
        if( limite <= 1 ) return;

        if( limite == 2 ) {
            out.println("2");
            return;
        }

        List<Long> nombresPossibles = initialiserLaListe();
        calculerNombresPremiers(nombresPossibles);
    }

    private void calculerNombresPremiers(List<Long> nombresPossibles) {
        long next = nombresPossibles.get(0);
        addAndPrint( next);
        nombresPossibles = retirerLesMultiples(next, nombresPossibles);
        if(next <= limiteMultiples) {
            calculerNombresPremiers(nombresPossibles);
        }else{
            addAndPrint(nombresPossibles.toArray(new Long[0]));
        }
    }

    private void addAndPrint(Long... nombresPremiers) {
        for (Long next : nombresPremiers) {
            out.println(next);
        }
    }

    private List<Long> retirerLesMultiples(long nombrePremier, List<Long> longs) {
        Iterator<Long> iterator = longs.iterator();
        for (long i = nombrePremier; i <=limite ; i+=nombrePremier) {
            while(iterator.hasNext()) {
                long current = iterator.next();
                if( current == i ) {
                    iterator.remove();
                    break;
                }
                if( current > i ) {
                    break;
                }
            }
        }
        return longs;
    }

    private List<Long> initialiserLaListe() {
        return LongStream.range(2, limite+1).boxed().collect(Collectors.toList());
    }

}
