import org.json.simple.*;
import java.io.*;

/**
 * Configurator of JSON-Files for Automat
 *
 * @Alexander; Matteo
 * @1.0
 */
public class jsonConfig extends Config
{
    // Variablen
    private int[][] uebergangstabelle;
    private int[] endzustaende;
    private char[] alphabet;
    private int start;

    public jsonConfig () {

    }

    public boolean readFile (String filePath) {
        JSONObject obj = new JSONObject();

        
        return false;
    }

    public boolean writeFile (String filePath, int[][] uebergangstabelle, int[] endzustaende, char[] alphabet, int start) {
        JSONObject obj = new JSONObject();

        obj.put("table", uebergangstabelle);
        obj.put("endzustaende", endzustaende);
        obj.put("alphabet", alphabet);
        obj.put("start", start);
        FileWriter file = null;
        
        try {
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter(filePath);
            file.write(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
        }
        
        return true;
    }

    public void setUebergangstabelle (int[][] uebergangstabelle) {
        this.uebergangstabelle = uebergangstabelle;
    }

    public void setEndzustaende (int[] endzustaende) {
        this.endzustaende = endzustaende;
    }

    public void setAlphabet (char[] alphabet) {
        this.alphabet = alphabet;
    }

    public void setStart (int start) {
        this.start = start;
    }

    public int[][] getUebergangstabelle () {
        return uebergangstabelle;
    }

    public int[] getEndzustaende () {
        return endzustaende;
    }

    public char[] getAlphabet () {
        return alphabet;
    }

    public int getStart () {
        return start;
    }
}
