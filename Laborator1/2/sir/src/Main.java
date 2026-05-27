import java.io.*;

public class Main
{
    public static void main(String[] args)
    {
        String linie;
        String continutTotal = "";
        BufferedReader br = null;

        try
        {
            br = new BufferedReader(new FileReader("text.txt"));
            while ((linie = br.readLine()) != null)
            {
                continutTotal += linie + "\n";
            }
            System.out.println("Continut: \n" + continutTotal);


            String textProcesat = continutTotal.replaceAll("[\\p{Punct}]", "");
            System.out.println("Text curatat: \n" + textProcesat);

            String litereMari = textProcesat.toUpperCase();
            System.out.println("Continutul scris cu litere mari: \n" + litereMari);

            String textFinal = litereMari.replaceAll("\\d", "");
            System.out.println("Continutul fara cifre: \n" + textFinal);

        }
        catch (FileNotFoundException e)
        {
            System.out.println("Eroare: Fisierul nu a fost gasit!");
        }
        catch (IOException e)
        {
            System.out.println("Eroare la citire!");
        }
    }
}