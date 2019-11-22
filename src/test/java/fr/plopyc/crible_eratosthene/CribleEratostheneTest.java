package fr.plopyc.crible_eratosthene;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


public class CribleEratostheneTest {

    private static final String DELIMITEUR = System.lineSeparator();
    private ByteArrayOutputStream testOut;
    private CalculateurNombrePremiers crible;

    @DataProvider
    public Object[][] donneesDeTestListeNombrePremiers() {
        return new Object[][]{
                {1L, List.of()},
                {2L, List.of(2L)},
                {3L, List.of(2L, 3L)},
                {4L, List.of(2L, 3L)},
                {5L, List.of(2L, 3L, 5L)},
                {100L, List.of(2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L, 23L, 29L, 31L, 37L, 41L, 43L, 47L, 53L, 59L, 61L, 67L, 71L, 73L, 79L, 83L, 89L, 97L)},
        };
    }

    private void initCrible(Long limite) {
        testOut = new ByteArrayOutputStream();
        crible = new CribleEratosthene(limite, testOut);
    }

    @Test(dataProvider = "donneesDeTestListeNombrePremiers")
    public void doit_renvoyer_liste_nombres_premiers(Long limite, List<Long> resultatAttendu) {
        // GIVEN
        initCrible(limite);

        // WHEN
        crible.calculerNombresPremiers();

        // THEN
        String resultatAttenduString = transformerEnString(resultatAttendu);
        assertThat(testOut.toString(StandardCharsets.UTF_8)).isEqualTo(resultatAttenduString);
    }

    private String transformerEnString(List<Long> resultatAttendu) {
        if(resultatAttendu.isEmpty()) return "";
        return resultatAttendu.stream().map(Object::toString).collect(Collectors.joining(DELIMITEUR)) + DELIMITEUR;
    }

    @DataProvider
    public Object[][] donneesDeTestListeNombresExclus() {
        return new Object[][]{
                {4L, List.of(4L)},
                {6L, List.of(4L, 6L)},
        };
    }

    @Test(dataProvider = "donneesDeTestListeNombresExclus")
    public void doit_retirer_les_multiples(Long limite, List<Long> nombresExclus) {
        // GIVEN
        initCrible(limite);

        // WHEN
        crible.calculerNombresPremiers();

        // THEN
        for (Long exclus : nombresExclus) {
            assertThat(testOut.toString()).doesNotContain(DELIMITEUR + exclus + DELIMITEUR);
        }
    }

}