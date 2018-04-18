package Hotels;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FileEntry {
    public static void main(String[] args) {
        ArrayList<Hotel> hotelArrayList = new ArrayList<>();
        try (FileReader fileReader = new FileReader("C:\\Users\\P B\\IdeaProjects\\SdaSredniozaawansowane2\\src\\main\\resources\\hotele.csv")) {
            Iterable<CSVRecord> in = CSVFormat.RFC4180.withDelimiter(';').withFirstRecordAsHeader().parse(fileReader);

            for (CSVRecord c :
                    in) {
                Hotel hotel = Hotel.createHotel(c);
                hotelArrayList.add(hotel);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Zadanie5(hotelArrayList);

        Zadanie6(hotelArrayList);

        Map<String, Integer> mapWithPostal = new HashMap<>();

        for (Hotel h :
                hotelArrayList) {
            String postalCodeOfHotel = h.getAdres().split(" ")[0]; // zwraca np. 97-300
            String keyStringWithPostal = MapOfPostalCodes.MapGen().get(postalCodeOfHotel); // zwraca np. lodzkie
            String keyForTheCurrentMap =new StringBuilder().append(keyStringWithPostal).append(" ").append(h.getKategoriaObieku()).toString() ;

            if (!mapWithPostal.containsKey(keyForTheCurrentMap)) {
                mapWithPostal.put(keyForTheCurrentMap, 1);
            } else {
                int temp = mapWithPostal.get(keyForTheCurrentMap);
                mapWithPostal.replace(keyForTheCurrentMap, temp + 1);
            }

        }
        // mozna zrobić kategorie enum zeby nie było ** wjednym a w drugim II :D

        System.out.println("7. Ile jest obiektów z każdej kategorii z podziałem na województwa?\n" +
                "(wymaga dodatkowego pliku z koda mi pocztowymi)");

        for (String s :
                mapWithPostal.keySet()) {

            System.out.println(s + ": " + Integer.valueOf(mapWithPostal.get(s)));
        }

        System.out.println(" ****** ");


//        //map within a map //////////////////////////
//       Map<String, Integer> stringIntegerMap = new HashMap<>();
//
//       Map<String, Integer> stringIntegerMap = null;
//
//        for (Hotel h :
//                hotelArrayList) {
//           stringIntegerMap.put(h.getCharakter(),1);
//            if (!stringMapMap.containsKey(h.getRodzajObjektu())) {
//                Map<String,Integer>  tempMap= new HashMap<>();
//                tempMap.put(h.getCharakter(), 1);
//                stringMapMap.put(h.getRodzajObjektu(), tempMap);
//                tempMap.clear();
//            } else {
//                if (!stringMapMap.get(h.getRodzajObjektu()).containsKey(h.getCharakter())) {
//                    Map<String,Integer>  tempMap= new HashMap<>();
//                    tempMap = stringMapMap.get(h.getRodzajObjektu());
//                    tempMap.put(h.getRodzajObjektu(),1);
//                    stringMapMap.put(h.getRodzajObjektu(),tempMap);
//                    tempMap.clear();
//                } else {
//                    Map<String,Integer>  tempMap= new HashMap<>();
//                    tempMap = stringMapMap.get(h.getRodzajObjektu());
//                    stringMapMap.get(h.getRodzajObjektu()).replace(h.getCharakter(), stringIntegerMap.get(h.getCharakter()) + 1);
//                }
//                stringMapMap.replace(h.getRodzajObjektu(), stringMapMap.get(h.getRodzajObjektu()).replace(h.getCharakter(), stringIntegerMap.get(h.getCharakter()) + 1));
//
//            }
//
//        }
//
//        for (String s :
//                stringMapMap.keySet()) {
//
//            System.out.println(s + ": " + stringIntegerMap.get("sezonowy")  + stringIntegerMap.get("stały"));
//        }
    }

    private static void Zadanie5(ArrayList<Hotel> hotelArrayList) {
        Map<String, Integer> mapOfObjects = new HashMap<>();


        for (Hotel h :
                hotelArrayList) {
            if (!h.getRodzajObjektu().isEmpty()) {
                if (!mapOfObjects.containsKey(h.getRodzajObjektu())) {
                    mapOfObjects.put(h.getRodzajObjektu(), 1);
                } else {
                    mapOfObjects.replace(h.getRodzajObjektu(), mapOfObjects.get(h.getRodzajObjektu()) + 1);
                }
            }
        }

        System.out.println("5. Ile jest obiektów z każdego rodzaju?");
        for (String s :
                mapOfObjects.keySet()) {

            System.out.println(s + ": " + Integer.valueOf(mapOfObjects.get(s)));
        }
        System.out.println(" ****** ");
    }

    private static void Zadanie6(ArrayList<Hotel> hotelArrayList) {
        Map<String, Integer> stringMapMap = new HashMap<>();
        for (Hotel h :
                hotelArrayList) {
            if (!h.getRodzajObjektu().isEmpty()) {
                String str = h.getRodzajObjektu() + " : " + h.getCharakter();
                if (!stringMapMap.containsKey(str)) {
                    stringMapMap.put(str, 1);
                } else {
                    stringMapMap.replace(str, stringMapMap.get(str) + 1);
                }
            }
        }

        System.out.println("6. Ile jest obiektów z każdego rodzaju z podziałem na charakter stały i sezonowy?");
        for (String s :
                stringMapMap.keySet()) {

            System.out.println(s + " : " + stringMapMap.get(s));
        }
        System.out.println(" ****** ");
    }
}
