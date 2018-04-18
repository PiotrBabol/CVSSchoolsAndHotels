package Hotels;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MapOfPostalCodes {
    public static void main(String[] args) {
        System.out.println(MapGen());



    }

    public static Map<String,String> MapGen() {
        Map<String, String> mapOfPostalCodes = new HashMap<>();
        String str;
        try (FileReader fileReader = new FileReader("C:\\Users\\P B\\IdeaProjects\\SdaSredniozaawansowane2\\src\\main\\resources\\kody.txt")) {

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((str = bufferedReader.readLine()) != null) {
                String[] words = str.split(" ");
                if(words.length>=1) {
                    mapOfPostalCodes.put(words[0], words[1]);
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapOfPostalCodes;
    }
}
