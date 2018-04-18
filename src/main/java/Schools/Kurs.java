package Schools;

import lombok.Data;
import org.apache.commons.csv.CSVRecord;

@Data
public class Kurs {
    private String NazwaKierunku;
    private String PoziomKsztalcenia;
    private String ProfilKsztalcenia;
    private String TytulZawodowy;
    private String FormaKsztalcenia;
    private String Dyscyplina;
    private String institucja;

    public Kurs(String nazwaKierunku
            , String poziomKsztalcenia
            , String profilKsztalcenia
            , String tytulZawodowy
            , String formaKsztalcenia
            , String dyscyplina
            , String institucja) {
        NazwaKierunku = nazwaKierunku;
        PoziomKsztalcenia = poziomKsztalcenia;
        ProfilKsztalcenia = profilKsztalcenia;
        TytulZawodowy = tytulZawodowy;
        FormaKsztalcenia = formaKsztalcenia;
        Dyscyplina = dyscyplina;
        this.institucja = institucja;
    }


    public static Kurs createKurs(CSVRecord s) {
        /*Schools.Kurs kurs = new Schools.Kurs(s.get("Nazwa kierunku"),
                s.get("Poziom kształcenia"),
                s.get("Profil kształcenia"),
                s.get("Tytuł zawodowy"),
                s.get("Forma kształcenia"),
                s.get("Dyscyplina (Obszar/Dziedzina)"),
                s.get("Instytucja/jednostka"));
        return kurs;*/

        return new Kurs(
                s.get("Nazwa kierunku"),
                s.get("Poziom kształcenia"),
                s.get("Profil kształcenia"),
                s.get("Tytuł zawodowy"),
                s.get("Forma kształcenia"),
                s.get("Dyscyplina (Obszar/Dziedzina)"),
                s.get("Instytucja/jednostka")
        );
    }
}
