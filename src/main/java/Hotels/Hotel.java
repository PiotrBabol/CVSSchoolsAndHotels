package Hotels;

import lombok.Data;
import org.apache.commons.csv.CSVRecord;

@Data
public class Hotel {
    private String nazwaWłasna;
    private String telefon;
    private String email;
    private String charakter;
    private String kategoriaObieku;
    private String rodzajObjektu;
    private String adres;


    public Hotel(String nazwaWłasna
            , String telefon
            , String email
            , String charakter
            , String kategoriaObieku
            , String rodzajObjektu
            , String adres) {
        this.nazwaWłasna = nazwaWłasna;
        this.telefon = telefon;
        this.email = email;
        this.charakter = charakter;
        this.kategoriaObieku = kategoriaObieku;
        this.rodzajObjektu = rodzajObjektu;
        this.adres = adres;
    }


    public static Hotel createHotel(CSVRecord c) {

        return new Hotel(c.get("Nazwa własna")
                , c.get("Telefon")
                , c.get("Email")
                , c.get("Charakter usług")
                , c.get("Kategoria obiektu")
                , c.get("Rodzaj obiektu")
                , c.get("Adres"));

    }
}
