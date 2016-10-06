package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static jdk.internal.org.objectweb.asm.commons.GeneratorAdapter.OR;

/**
 * Created by Neak on 06.10.2016.
 */
public class AlgoKompress {

    public static void main(String args[]) throws IOException {

        String fileName = "D:\\NewCodes\\IntelliJ\\KompressAlgo\\src\\resources\\data128-1.txt";

        String s = readFile(fileName);
        String ld = isLeadingDigit(s);
        String o = komprimieren(s,ld);
        System.out.println("File komprimiert: "+o);
    }

    private static String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            System.out.println("File Original: "+stringBuilder.toString());
            return stringBuilder.toString();
        } finally {
            reader.close();
        }

    }

    public static String isLeadingDigit(final String value){
        final char c = value.charAt(0);
      if (c == '1') {
          return "1";
        }
        else return "0";
    }

    public static String komprimieren(String in, String ld)
    {

        // Wenn der übergebene Text weniger als 4 Zeichen hat,
        // können keine Zeichen sinnvoll zusammengefasst werden.
        if (in.length() < 4)
        {
            return in;
        }


        String out = ld+" ";
        int altIndex = 0;


        // Gehe nacheinander alle Zeichen der Zeichenkette durch und suche nach Wiederholungen:
        for (int index = 1; index <= in.length(); index++)
        {

            if (
                    index == in.length() ||                     // Wenn alle Zeichen betrachtet worden sind, muss nicht weiter gesucht werden.
                            in.charAt(altIndex) != in.charAt(index) ||  // Wenn das letzte Zeichen nicht dem aktuellen entspricht, muss nicht weiter gesucht werden.
                            index - altIndex == 128                     // Es können maximal 128 Zeichen zusammengefasst werden.
                    )
            {

                if (index - altIndex < 2 &&         // Wenn weniger als 4 Zeichen hintereinander gleich sein, können sie nicht sinnvoll zusammengefasst werden.
                        in.charAt(altIndex) != '#')     // außer wenn das Zeichen gleich # ist.)
                {
                    out += in.substring(altIndex, index);   // Die Zeichen werden unkomprimiert übernommen.
                }

                // Ansonsten können Zeichen zusammengefasst d.h. komprimiert werden:
                else
                {

                    out += ""+                             // Die Markierung für eine Wiederholung
                            ((index - altIndex)); //+    // Die Anzahl der Wiederholungen
                           // in.charAt(altIndex);             // Das Zeichen, das wiederholt wird

                }


                // Die Überprüfung auf sich wiederholende Zeichen wird neu angefangen.
                altIndex = index;


            }


        }


        // Die komprimierte Zeichenkette wird zurückgegeben:
        return out;


    }
}
