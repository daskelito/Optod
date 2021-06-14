import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class Exercise7 {

    private HashMap<String, String> dictonary = new HashMap<String, String>();
    
    public Exercise7(String filename) {
        readDictionary(filename,dictonary);
    }

    public static void readDictionary(String filename, Map<String, String> map) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"ISO-8859-1"));
            String[] parts;
            String english, swedish;
            String str = br.readLine();
            while (str != null) {
                parts = str.split(",");
                english = parts[2];
                swedish = parts[1];
                map.put(english, swedish);
                str = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("readDictionary: " + e);
        }
    }

    public void list() {
        String strList = dictonary.toString(); // format: "{ eng1=sve1, eng2=sve2, ...}"
        strList = strList.substring(1, strList.length() - 1);
        String[] parts = strList.split(",");

        System.out.println("------------------------------------------------");
        for (String str : parts) {
            System.out.println(str);
        }
        System.out.println("------------------------------------------------");
    }
    
    public void translate() {
    }

    public static int menu(String[] options) {
        int res = 0;
        String input;
        String message = "VÄLJ ETT ALTERNATIV\n";
        for (int i = 0; i < options.length; i++) {
            message += "\n" + (i + 1) + ". " + options[i];
        }

        do {
            try {
                input = JOptionPane.showInputDialog(message);
                if (input == null) {
                    return 0;
                }
                res = Integer.parseInt(input);
            } catch (NumberFormatException e) {
            }
        } while (res < 1 || res > options.length);

        return res;
    }

    public static void main(String[] args) {
        Exercise7 ex7 = new Exercise7("files/SkSvEn.txt");
        String[] menuOptions = {"Översätt ord", "Skriv ut ordlista"};
        int choice = Exercise7.menu(menuOptions);
        while (choice != 0) {
            switch (choice) {
                case 1:
                    ex7.translate();
                    break;
                case 2:
                    ex7.list();
                    break;
            }
            choice = Exercise7.menu(menuOptions);
        }
    }
}
