package Schools;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.*;


public class FileGetter1 {
    public static void main(String[] args) {
        ArrayList<Kurs> kursArray = new ArrayList<>();
        try (FileReader fReader = new FileReader("C:\\Users\\P B\\IdeaProjects\\SdaSredniozaawansowane2\\src\\main\\resources\\informatyka.csv")) {
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(fReader);

            for (CSVRecord s :
                    records) {
                Kurs kurs = Kurs.createKurs(s);
                kursArray.add(kurs);
            }

            System.out.println(kursArray);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        long infnumer = kursArray.stream().filter(e -> e.getNazwaKierunku().equals("Informatyka")).count();
        System.out.println(infnumer);

        int countingKierunki = 0;
        for (Kurs k :
                kursArray) {
            String str = k.getNazwaKierunku();
            if (str.equals("Informatyka")) {
                countingKierunki++;
            }
        }
        System.out.println();
        System.out.println(countingKierunki);
        System.out.println(" ****** ");

        //

       /* Map<String, ArrayList<String>> map = new HashMap<>();
        for (int i = 0; i < kursArray.size(); i++) {
            String str = kursArray.get(i).getInstitucja().split(";")[0];
            String str2 = kursArray.get(i).getInstitucja().split(";")[1];

            if (!str.isEmpty()) {
                if (map.containsKey(str)) {
                    ArrayList<String> arrayList = map.get(str);
                    arrayList.add(str2);
                    map.replace(str, arrayList);

                } else {
                    ArrayList<String> arrayList = new ArrayList<>();
                    arrayList.add(str2);
                    map.put(str, arrayList);
                }
            }
        }

        int countingStudia=0;
        for (Map.Entry<String,ArrayList<String>> entry:
             map.entrySet()) {
            countingStudia += map.get(entry.getKey()));
        }
        System.out.println(countingStudia);*/

        //

        Set<String> stringSet = new HashSet<>();
        for (Kurs aKursArray : kursArray) {
            String str = aKursArray.getInstitucja();
//            if (!stringSet.contains(str)) {
            stringSet.add(str);
//            }
        }
        System.out.println(stringSet.size());
        System.out.println(" ****** ");


        Set<String> stringSet1 = new HashSet<>();
        for (Kurs k :
                kursArray) {
            if (k.getPoziomKsztalcenia().equals("Drugiego stopnia")) {
                stringSet1.add(k.getInstitucja().split(";")[0]);
                /*if (k.getInstitucja().contains(";")) {
                    String key = k.getInstitucja().split(";")[0];
                    if (!stringSet1.contains(key)) {
                        counting2nd++;
                        stringSet1.add(k.getInstitucja());
                    }
                } else {
                    if (!stringSet1.contains(k.getInstitucja())) {
                        counting2nd++;
                        stringSet1.add(k.getInstitucja());
                    }
                }*/
            }
        }
        System.out.println(stringSet1.size());
        System.out.println(" ****** ");


        int counting1stand2nd = 0;
        Set<String> setOfOba = new HashSet<>();
        Set<String> setOfStacjonarnych = new HashSet<>();
        Set<String> setOfNieStacjonarnych = new HashSet<>();
        for (Kurs k :
                kursArray) {
            if (k.getPoziomKsztalcenia().equals("Pierwszego stopnia") || k.getPoziomKsztalcenia().equals("Drugiego stopnia")) {
                counting1stand2nd++;
                if (k.getFormaKsztalcenia().equals("Stacjonarne")) {
                    setOfStacjonarnych.add("" + counting1stand2nd);
                    counting1stand2nd++;
                }
                if (k.getFormaKsztalcenia().equals("Niestacjonarne")) {
                    setOfNieStacjonarnych.add("" + counting1stand2nd);
                    counting1stand2nd++;
                }
                if (k.getFormaKsztalcenia().equals("Niestacjonarne, Stacjonarne")) {
                    setOfOba.add("" + counting1stand2nd);
                    counting1stand2nd++;
                }
                // tak jest z nie nanoszeniem sie kolejnych kierunkow np jezeli kirunek informatyk jest w stacjonarnym to drugi juz sie nie utorz
                /*if(k.getFormaKsztalcenia().equals("Stacjonarne")){
                    setOfStacjonarnych.add(k.getNazwaKierunku());
                    counting1stand2nd++;
                }
                if(k.getFormaKsztalcenia().equals("Niestacjonarne")){
                    setOfNieStacjonarnych.add(k.getNazwaKierunku());
                    counting1stand2nd++;
                }
                if(k.getFormaKsztalcenia().equals("Niestacjonarne, Stacjonarne")){
                    setOfOba.add(k.getNazwaKierunku());
                    counting1stand2nd++;
                }*/

                // mozna to zrobic za pomocy map a nei setow
            }
        }
        System.out.println("oba : " + setOfOba.size());
        System.out.println("st : " + setOfStacjonarnych.size());
        System.out.println("niest : " + setOfNieStacjonarnych.size());
        System.out.println(counting1stand2nd);

        //print
        try {
            File file = new File("Resoults.csv");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            CSVPrinter printer = CSVFormat.RFC4180.withHeader("Opis", "Wartość").print(writer);

            Map<String,Integer> resoults = new HashMap<>();
            resoults.put("Ilość jest kierunków o nazwie Informatyka",countingKierunki);
            resoults.put("Ilość wydziałów oferuje studia informatyczna na dowolnm stopniu",stringSet.size());
            resoults.put("Ilość uczelni oferuje studia drugiego stopnia",stringSet1.size());
            resoults.put("Ile jest kierunków pierwszego i drugiego stopnia z podziałem na formę stacjonarną ", setOfStacjonarnych.size());
            resoults.put("Ile jest kierunków pierwszego i drugiego stopnia z podziałem na formę niestacjonarną ", setOfNieStacjonarnych.size());
            resoults.put("Ile jest kierunków pierwszego i drugiego stopnia z podziałem na formę stacjonarną i niestacjonarną ", setOfOba.size());

            for (Map.Entry<String, Integer> entry : resoults.entrySet()) {

                String object = entry.getKey();
                printer.printRecord(object,resoults.get(object));

//                printer.printRecords(object, objects.get(0), objects.get(1), objects.get(2), objects.get(3)); one after another

            }

            printer.flush();

            printer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
